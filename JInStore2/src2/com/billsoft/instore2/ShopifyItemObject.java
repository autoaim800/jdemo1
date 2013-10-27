package com.billsoft.instore2;

public class ShopifyItemObject {

    private String category;

    private String desc;

    private String handle;

    @Override
    public String toString() {
        return "ShopifyItemObject [category=" + category + ",\n   handle=" + handle
                + ",\n   imgSrc=" + imgSrc + ",\n   price=" + price + ",\n   prodCode=" + prodCode
                + ",\n   title=" + title + ",\n   type=" + type + ",\n   url=" + url
                + ",\n   vendor=" + vendor + ",\n   hash=" + hash + ",\n   desc=" + desc + "]";
    }

    private String imgSrc;

    private String price;

    private String prodCode;

    private String title;

    private String type;

    private String url;

    private String vendor;

    private String hash;

    public ShopifyItemObject(String itemUrl, String handle, String category, String productType,
            String productTitle, String htmlDesc, String variantPrice, String productCode,
            String vendor, String imageSrc) {
        this.url = itemUrl;
        this.hash = GenericBuilder.sha1(url);
        this.handle = handle;
        this.category = category;
        this.type = productType;
        this.title = productTitle;
        this.desc = htmlDesc;
        this.price = variantPrice;
        this.prodCode = productCode;
        this.vendor = vendor;
        this.imgSrc = imageSrc;
    }

    public String getHash() {
        return hash;
    }

    public String getCategory() {
        return category;
    }

    public String getDesc() {
        return desc;
    }

    public String getHandle() {
        return handle;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public String getPrice() {
        return price;
    }

    public String getProdCode() {
        return prodCode;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }

    public String getVendor() {
        return vendor;
    }

}
