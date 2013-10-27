package com.billsoft.instore2.impl;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.billsoft.instore2.Dbable;
import com.billsoft.instore2.GenericBuilder;
import com.billsoft.instore2.LinkExtractable;
import com.billsoft.instore2.ShopifyItemDataExtractable;
import com.billsoft.instore2.ShopifyItemObject;

public class BigWLinkExtractor extends GenericBuilder implements LinkExtractable {

    private static final String BASE_URL = "http://www.bigw.com.au";

    private static final String ENTRY_URL = "http://www.bigw.com.au/bigw/home.jsp";
    private static final String HOST_NAME = "www.bigw.com.au";

    private static final int STA_CAT1_INIT = 0;
    private static final int STA_CAT2_INIT = 10;
    /**
     * item link has been extracted from the category link
     */
    private static final int STA_CAT2_PROCESSED = 11;
    /**
     * item link has just been initialized
     */
    private static final int STA_ITEM_INIT = 20;
    /**
     * item link has been processed
     */
    private static final int STA_ITEM_PROCESSED = 30;
    /**
     * no found sub category from this category1 link
     */
    private static final int STA_SUBCAT_NEGATIVE = 2;
    /**
     * found sub category from this category1 link
     */
    private static final int STA_SUBCAT_POSITIVE = 1;
    private static final String TABLE_NAME = "t_urls";
    private static final String TEXT_CATEGORIES = "Categories";

    public BigWLinkExtractor(Dbable db) {
        super(db);
        if (!db.existsTable(TABLE_NAME)) {
            db.createTable(CREATE_TABLE_URLS);
            db.createTable(CREATE_TABLE_ITEMS);
        }
        useZip = true;
    }

    /**
     * extract level-1 category links
     * 
     * @return an integer of total number of extracted links
     * 
     */
    private int extractCat1Links() {
        Set<String> hs = new HashSet<String>();

        Document doc = httpGetDoc(ENTRY_URL);
        if (null == doc) {
            error("no found doc from root-entry");
            return 0;
        }
        Element menuNode = doc.getElementById("menu");
        Elements anchors = menuNode.getElementsByTag(NODE_NAME_ANCHOR);
        for (Element anchor : anchors) {
            String href = anchor.attr(NODE_ATTR_HREF);
            if (null == href || 1 > href.length()) {
                continue;
            }
            href = prepareUrl(href);
            if (isValidCatUrl(href)) {
                hs.add(href);
            }
        }

        insertHashSetCat1(hs);
        return hs.size();
    }

    /**
     * extract level-2 category links
     */
    private void extractCat2Links() {
        String sql = String.format("select url from t_urls where status = %s", STA_CAT1_INIT);
        String[] cat1Urls = db.queryColumnSs(sql, 1);
        if (null == cat1Urls) {
            error("no found cat1 urls");
            return;
        }
        for (String url : cat1Urls) {
            if (injectSubCatUrls(url) > 0) {
                // mark the url as found sub
                markUrl(url, STA_SUBCAT_POSITIVE);
            } else {
                // mark the url as no found sub
                markUrl(url, STA_SUBCAT_NEGATIVE);
            }
        }
    }

    @Override
    public void extractCategoryLinks() {
        extractCat1Links();
        extractCat2Links();
    }

    @Override
    public void extractItemData() {
        String sql = String.format("select url from t_urls where status = %s and host = \"%s\"",
                STA_ITEM_INIT, HOST_NAME);
        String[] itemUrls = db.queryColumnSs(sql, 1);
        for (String url : itemUrls) {

            ShopifyItemDataExtractable ide = new BigWShopifyItemDataExtractor(db, url);
            ShopifyItemObject sio = ide.buildItemObject();

            if (null == sio) {
                error("failed to build data object from: " + url);
                continue;
            }

            if (insertShopifyItem(sio)) {
                markUrl(url, STA_ITEM_PROCESSED);
            } else {
                error("failed to process item " + url);
            }
        }
        // out of for each itemUrls
    }

    @Override
    public void extractItemLinks() {
        String sql = String
                .format("select url from t_urls where host = \"%s\" "
                        + "and (status = %s or status = %s)", HOST_NAME, STA_SUBCAT_NEGATIVE,
                        STA_CAT2_INIT);
        String[] catUrls = db.queryColumnSs(sql, 1);
        for (String url : catUrls) {
            if (injectItemUrls(url) > 0) {
                // mark the cat url as extracted
                markUrl(url, STA_CAT2_PROCESSED);
            }
        }
    }

    /**
     * extract item urls from given category url and inject them into dB
     * 
     * @param url
     *            a string of url
     * @return an integer of total extracted url number
     */
    private int injectItemUrls(String url) {
        Document doc = httpGetDoc(url);
        Element divMain = doc.getElementById("main");
        if (null == divMain) {
            // no found div#main
            return 0;
        }
        Elements anchors = divMain.getElementsByTag(NODE_NAME_ANCHOR);
        if (null == anchors) {
            // div#main has no anchor
            return 0;
        }
        Set<String> pending = new HashSet<String>();
        for (Element anchor : anchors) {
            String href = anchor.attr(NODE_ATTR_HREF);
            if (null == href) {
                continue;
            }
            href = prepareItemUrl(href);
            if (isValidItemUrl(href)) {
                pending.add(href);
            }
        }
        insertHashSetItem1(pending);
        return pending.size();
    }

    /**
     * extract sub-category links from given url
     * 
     * @param url
     *            a string of url
     * @return an integer of total extracted link number
     */
    private int injectSubCatUrls(String url) {
        Set<String> pending = new HashSet<String>();

        Document doc = httpGetDoc(url);

        if (null == doc) {
            error("no found doc");
            return 0;
        }
        Element catSection = doc.getElementById("left-nav-dept-body");
        if (null == catSection) {
            // likely the page does not contain any left-nav-dept-body
            return 0;
        }
        Elements h4s = catSection.getElementsByTag("h4");
        if (null == h4s) {
            // a left-nav does not contain any h4
            return 0;
        }
        for (Element h4 : h4s) {
            if (TEXT_CATEGORIES.equals(h4.text())) {
                org.jsoup.nodes.Node firstChild = h4.childNode(0);
                if (NODE_NAME_ANCHOR.equals(firstChild.nodeName())) {
                    Element sibling = h4.nextElementSibling();
                    if (NODE_NAME_UL.equals(sibling.nodeName())) {
                        Elements anchors = sibling.getElementsByTag(NODE_NAME_ANCHOR);
                        for (Element anchor : anchors) {
                            String href = anchor.attr(NODE_ATTR_HREF);
                            if (null != href) {
                                href = this.prepareUrl(href);
                                if (isValidCatUrl(href)) {
                                    pending.add(href);
                                }
                            }
                        }
                        // out of for each anchor
                    }
                    // out of if node_name_ul
                }
                // out of if node_name_anchor
            }
            // out of if text_categories
        }
        // out for each h4s
        insertHashSetCat2(pending);

        return pending.size();
    }

    private int insertHashSetCat1(Set<String> hs) {
        String sql = String.format(
                "insert into t_urls (host, hash, url, status) values (?, ?, ?, %s)", STA_CAT1_INIT);
        return insertHashSetN(hs, sql);
    }

    /**
     * @param hs
     *            a set of url string
     * @return an integer of total executed sql number
     */
    private int insertHashSetCat2(Set<String> hs) {
        String sql = String.format(
                "insert into t_urls (host, hash, url, status) values (?, ?, ?, %s)", STA_CAT2_INIT);
        return insertHashSetN(hs, sql);
    }

    private int insertHashSetItem1(Set<String> hs) {
        String sql = String.format(
                "insert into t_urls (host, hash, url, status) values (?, ?, ?, %s)", STA_ITEM_INIT);
        return insertHashSetN(hs, sql);
    }

    /**
     * @param hs
     * @param sql
     * @return an integer of total executed sql number
     */
    private int insertHashSetN(Set<String> hs, String sql) {
        int sum = 0;
        for (String url : hs) {
            String hash = sha1(url);
            if (db.updatePss(sql, new String[] { HOST_NAME, hash, url })) {
                sum++;
            }
        }
        return sum;
    }

    /**
     * <pre>
     * create table t_shopify_items (
     *     host varchar(45),
     *     hash varchar(45), 
     *     handle varchar(120), 
     *     item_cat varchar(120), 
     *     item_type varchar(45), 
     *     item_title varchar(120),
     *     item_body text,
     *     product_code varchar(45),
     *     vendor varchar(45),
     *     variant_price float(6,2),
     *     img_src text,
     *     primary key(host, hash)
     * );
     * </pre>
     * 
     * @return a boolean indicates whether there is any exception
     */
    private boolean insertShopifyItem(ShopifyItemObject sio) {
        String tpl = "insert into t_shopify_items (host, hash, handle, "
                + "item_cat, item_type, item_title, " + "item_body, product_code, vendor, "
                + "variant_price, img_src) values (" + "?, ?, ?,   ?, %s, ?,   ?, ?, ?,   %s, ?)";
        String ptype;
        if (null == sio.getType()) {
            info("null product-type for " + sio.getUrl());
            ptype = "null";
        } else {
            ptype = String.format("\"%s\"", sio.getType());
        }

        String sql = String.format(tpl, ptype, sio.getPrice());

        String imgSrc = prepareUrl(sio.getImgSrc());

        String[] ctx = new String[] { HOST_NAME, sio.getHash(), sio.getHandle(), sio.getCategory(),
                sio.getTitle(), sio.getDesc(), sio.getProdCode(), sio.getVendor(), imgSrc };

        return db.updatePss(sql, ctx);
    }

    private boolean isValidCatUrl(String href) {
        if (href.startsWith(BASE_URL)) {
            try {
                URL url = new URL(href);
                String path = url.getPath();
                if (path.startsWith("/bigw/") || path.startsWith("/buying-guide/")
                        || path.startsWith("/offer/") || path.startsWith("/list/")) {
                    return false;
                }
                return true;
            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    private boolean isValidItemUrl(String href) {
        if (href.startsWith(BASE_URL)) {
            try {
                URL url = new URL(href);
                String path = url.getPath();
                if (path.startsWith("/bigw/") || path.startsWith("/buying-guide/")
                        || path.startsWith("/offer/") || path.startsWith("/list/")) {
                    return false;
                }
                return true;
            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    /**
     * mark given url as terminated, no need for further extraction
     * 
     * @param url
     *            a string of url
     * @param newStatus
     *            an integer of status
     */
    private void markUrl(String url, int newStatus) {
        String hash = sha1(url);
        String tpl = "update t_urls set status = %s where host = \"%s\" and hash = \"%s\" ";
        String sql = String.format(tpl, newStatus, HOST_NAME, hash);
        db.update(sql);
    }

    private String prepareItemUrl(String href) {
        href = prepareUrl(href);
        if (href.endsWith("#Reviews")) {
            return href.substring(0, href.length() - 8);
        }
        return href;
    }

    /**
     * prepare the format of the given href
     * 
     * @param href
     *            a string of href
     * @return a string of prepared href
     */
    protected String prepareUrl(String href) {
        if (href.startsWith("/")) {
            return String.format("%s%s", BASE_URL, href);
        }
        return href;
    }

}
