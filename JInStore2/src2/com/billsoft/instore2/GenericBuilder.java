package com.billsoft.instore2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class GenericBuilder {

    public class CacheBuilder {

        private File file = null;

        public CacheBuilder(String cacheDir, String hash) {
            File dir = new File(cacheDir);
            if (!dir.exists()) {
                dir.mkdir();
            }
            String[] subs = new String[3];
            String filename = null;
            if (hash.length() > 8) {
                subs[0] = hash.substring(0, 2);
                subs[1] = hash.substring(2, 4);
                subs[2] = hash.substring(4, 6);
                filename = hash.substring(6);
                for (String s : subs) {
                    dir = new File(dir, s);
                }

                if (!dir.exists()) {
                    dir.mkdirs();
                }

                file = new File(dir, filename);
            }

        }

        public File getFile() {
            return file;
        }

        public boolean exists() {
            if (null == file) {
                return false;
            }
            return file.exists();
        }

        public boolean dump(String raw) {
            FileWriter fw = null;
            boolean ret = false;
            try {
                fw = new FileWriter(file);
                fw.write(raw);
                ret = true;
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                if (null != fw) {
                    try {
                        fw.close();
                    } catch (IOException e) {
                        // ignored
                    }
                    fw = null;
                }
            }
            return ret;
        }
    }

    public static final String CACHE_DIR = "_cache";
    public static final String CREATE_TABLE_ITEMS = "create table t_shopify_items ("
            + "host varchar(45)," + "hash varchar(45)," + "handle varchar(120),"
            + "item_cat varchar(120)," + "item_type varchar(45)," + "item_title varchar(120),"
            + "item_body text," + "product_code varchar(45)," + "vendor varchar(45),"
            + "variant_price float(6,2)," + "img_src text," + "primary key(host, hash));";
    public static final String CREATE_TABLE_URLS = "create table t_urls " + "(host varchar(45), "
            + "hash varchar(45), " + "url text, " + "status int default 0, "
            + "primary key (host, hash));";
    public static final String NODE_ATTR_CLASS = "class";
    public static final String NODE_ATTR_ONCLICK= "onclick";
    public static final String NODE_ATTR_HREF = "href";
    public static final String NODE_ATTR_SRC = "src";
    public static final String NODE_NAME_ANCHOR = "a";
    public static final String NODE_NAME_DIV = "div";
    public static final String NODE_NAME_IMG = "img";

    public static final String NODE_NAME_P = "p";
    public static final String NODE_NAME_SPAN = "span";

    public static final String NODE_NAME_UL = "ul";

    private static final String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_6_8) AppleWebKit/534.30 (KHTML, like Gecko) Chrome/12.0.742.112 Safari/534.30";

    public static String byteArrayToHexString(byte[] b) {
        String result = "";
        for (int i = 0; i < b.length; i++) {
            result += Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1);
        }
        return result;
    }

    public static void error(String s) {
        System.err.println(String.format("error: %s", s));
    }

    public static void info(String s) {
        System.err.println(String.format("info : %s", s));
    }

    public static String[] list2ss(List<String> pending) {
        String[] os = new String[pending.size()];
        pending.toArray(os);
        return os;
    }

    public static String sha1(String raw) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] digest = md.digest(raw.getBytes());
            return byteArrayToHexString(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected Dbable db;

    protected boolean useZip = false;

    public GenericBuilder(Dbable dB) {
        db = dB;
    }

    private Document httpDoc(Connection.Method method, String url) {
        info(url);

        String hash = sha1(url);
        // prepare cache
        CacheBuilder cb = new CacheBuilder(CACHE_DIR, hash);
        if (cb.exists()) {
            try {
                info("read from cache " + cb.getFile().getAbsolutePath());
                return Jsoup.parse(cb.getFile(), null);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return null;
            }
        }

        Connection connection = Jsoup.connect(url);
        simulateChrome(connection);
        for (int i = 0; i < 3; i++) {
            try {
                if (Connection.Method.GET == method) {
                    // retrieve the doc
                    Document doc = connection.get();
                    // dump the doc
                    cb.dump(doc.html());

                    return doc;
                }
            } catch (SocketTimeoutException e) {
                // ignored
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                break;
            }
        }
        return null;
    }

    /**
     * download given url use method:GET then parse into object:Document
     * 
     * @param url
     *            a string of url
     * @return an object of <code>Document</code>
     */
    public Document httpGetDoc(String url) {
        return httpDoc(Connection.Method.GET, url);
    }

    /**
     * configure the connection
     * 
     * @param conn
     *            an object of <code>connection</code>
     */
    private void simulateChrome(Connection conn) {
        conn.userAgent(USER_AGENT);
        if (this.useZip) {
            conn.header("accept-encoding", "gzip");
        }
        conn.timeout(9000);
    }

}
