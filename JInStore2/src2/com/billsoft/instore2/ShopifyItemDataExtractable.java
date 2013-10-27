package com.billsoft.instore2;

public interface ShopifyItemDataExtractable {

    ShopifyItemObject buildItemObject();

    String extractHandle();

    String extractShortDesc();

    String extractPrice();

    String extractProductCode();

    String extractCategory();

    String extractProductType();

    String extractProductTitle();

    String extractVendor();

    String extractImageSrc();

}
