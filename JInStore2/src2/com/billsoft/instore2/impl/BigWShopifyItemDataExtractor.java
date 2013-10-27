package com.billsoft.instore2.impl;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.billsoft.instore2.Dbable;
import com.billsoft.instore2.GenericBuilder;
import com.billsoft.instore2.ShopifyItemDataExtractable;
import com.billsoft.instore2.ShopifyItemObject;

public class BigWShopifyItemDataExtractor extends GenericBuilder implements
        ShopifyItemDataExtractable {

    public static void main(String[] args) {
        String itemUrl = "http://www.bigw.com.au/entertainment/books/teen/bpnBIGW_0000000122781/skulduggery-pleasant-faceless-ones-book-3";
        itemUrl = "http://www.bigw.com.au/toys/lego-construction/lego/star-wars/bpnBIGW_0000000351390/lego-galaxy-squad-crater-creeper-70706";

        BigWShopifyItemDataExtractor bde = new BigWShopifyItemDataExtractor(null, itemUrl);
        ShopifyItemObject obj = bde.buildItemObject();
        System.out.println(obj.toString());
    }

    private Document doc;

    private String url;

    public BigWShopifyItemDataExtractor(Dbable dB, String itemUrl) {
        super(dB);
        url = itemUrl;

    }

    @Override
    public ShopifyItemObject buildItemObject() {

        doc = httpGetDoc(url);
        if (null == doc) {
            error("null doc from " + url);
            return null;
        }

        // handle
        String handle = extractHandle();
        // original category
        String category = extractCategory();
        // product type, optional
        String type = extractProductType();
        // product title
        String title = extractProductTitle();
        // product short description
        String desc = extractShortDesc();
        // product price
        String price = extractPrice();
        // product code
        String pcode = extractProductCode();
        if (null == pcode) {
            error("no found pcode");
            return null;
        }
        // product vendor
        String vendor = extractVendor();
        if (null == vendor) {
            error("no found vendor");
            return null;
        }

        // img src
        String imgSrc = extractImageSrc();
        return new ShopifyItemObject(url, handle, category, type, title, desc, price, pcode,
                vendor, imgSrc);
    }

    @Override
    public String extractCategory() {
        List<String> crumbs = extractNavCrumb();
        if (null != crumbs && crumbs.size() > 3) {
            return String.format("%s/%s/%s", crumbs.get(0), crumbs.get(1), crumbs.get(2));
        }
        return null;
    }

    @Override
    public String extractHandle() {
        String[] subs = url.split("/");
        if (subs.length > 2) {
            return subs[subs.length - 1];
        }
        return null;
    }

    @Override
    public String extractImageSrc() {
        Element imgDiv = doc.getElementById("product-detail-image-large");
        if (null == imgDiv) {
            error("no found div#product-detail-image-large on " + url);
            return null;
        }
        Element repImgDiv = imgDiv.getElementById("replaceImage");
        if (null == repImgDiv) {
            error("no found div#replaceImage on " + url);
            return null;
        }
        Elements imgNodes = repImgDiv.getElementsByTag(NODE_NAME_IMG);
        if (null == imgNodes || imgNodes.size() < 1) {
            error("no found img node under div#replaceImage on " + url);
            return null;
        }
        for (Element imgNode : imgNodes) {
            if (imgNode.hasAttr(NODE_ATTR_SRC)) {
                return imgNode.attr(NODE_ATTR_SRC);
            }
        }

        error("no found img with src on " + url);
        return null;
    }

    private List<String> extractNavCrumb() {
        Element breadCrumb = doc.getElementById("standard-breadcrumb");
        if (null == breadCrumb) {
            error("no found breadcrumb on " + url);
            return null;
        }
        Elements anchors = breadCrumb.getElementsByTag(NODE_NAME_ANCHOR);
        List<String> crumbs = new ArrayList<String>();
        for (Element anchor : anchors) {
            String text = anchor.text();
            crumbs.add(text.trim());
        }
        return crumbs;
    }

    @Override
    public String extractPrice() {
        String priceStr = null;
        Element skuDetailSlot = doc.getElementById("sku_details_slot");
        if (null == skuDetailSlot) {
            error("no found sku details slot on " + url);
            return null;
        }
        Elements spans = skuDetailSlot.getElementsByTag(NODE_NAME_SPAN);
        if (null == spans) {
            error("no found sku_details_slot/spans on " + url);
            return null;
        }
        for (Element span : spans) {
            if (span.hasAttr("class") && "price left".equals(span.attr("class"))) {
                priceStr = span.text();
                priceStr = priceStr.trim();
                if (priceStr.startsWith("$")) {
                    priceStr = priceStr.substring(1);
                }
                // 10,000 => 10000
                return priceStr.replace(",", "");
            }
        }
        // out of for spans
        return null;
    }

    @Override
    public String extractProductCode() {
        return extractKeyValueFromSpec(new String[] { "Sku Code", "Product Code" });
    }

    @Override
    public String extractProductTitle() {
        Element productTitleNode = doc.getElementById("product_title");
        if (null == productTitleNode) {
            error("no found product title on " + url);
            return null;
        }
        if (null == productTitleNode.text()) {
            return null;
        }
        return productTitleNode.text().trim();
    }

    @Override
    public String extractProductType() {
        List<String> crumbs = extractNavCrumb();
        if (null != crumbs && crumbs.size() > 4) {
            return crumbs.get(3);
        }
        return null;
    }

    @Override
    public String extractShortDesc() {
        Element shortDescNode = doc.getElementById("prod-short-desc");
        if (null == shortDescNode) {
            error("no found short desc on " + url);
            return null;
        }
        return shortDescNode.html();
    }

    @Override
    public String extractVendor() {
        return extractKeyValueFromSpec(new String[] { "Brand Name", "Publisher", "Author" });
    }

    /**
     * peek specification in given ss order, return the first found key's value
     * 
     * @param ss
     *            an array of string of keys
     * @return a string of value, or null for no found
     */
    private String extractKeyValueFromSpec(String[] ss) {
        for (String s : ss) {
            String ret = extractKeyValueFromSpec(s);
            if (null != ret) {
                return ret;
            }
        }
        return null;
    }

    private String extractKeyValueFromSpec(String specKey) {
        info("extractKeyValueFromSpec() " + specKey);
        Element specNode = doc.getElementById("Specification");
        if (null == specNode) {
            error("no found specification on " + url);
            return null;
        }
        Elements specDivs = specNode.select("div.specification-column1");
        if (null == specDivs) {
            error("no found div.specification-column1");
            return null;
        }
        Element brandNameDiv = null;
        StringBuilder sb = new StringBuilder();
        for (Element div : specDivs) {
            Element pNode = div.child(0);
            if (null == pNode || null == pNode.text()) {
                continue;
            }
            String pNodeText = pNode.text().trim();
            if (pNodeText.endsWith(specKey)) {
                brandNameDiv = div;
                break;
            } else {
                sb.append(pNodeText).append("|");
            }
        }
        // out of for each spec-divs
        if (null == brandNameDiv) {
            error("no found " + specKey + " on " + url);
            error("only found " + sb.toString() + " instead");
            return null;
        }
        Element brandValueDiv = brandNameDiv.nextElementSibling();
        if (null == brandValueDiv || null == brandValueDiv.text()) {
            error("no found " + specKey + "'s value column on " + url);
            return null;
        }
        String ret = brandValueDiv.text().trim();

        info("found " + specKey + "=" + ret);
        return ret;
    }

}
