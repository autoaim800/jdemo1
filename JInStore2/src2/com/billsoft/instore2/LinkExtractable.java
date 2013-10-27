package com.billsoft.instore2;

public interface LinkExtractable {

    /**
     * extract all category links from main entry url
     */
    void extractCategoryLinks();

    /**
     * extract item data from item url
     */
    void extractItemData();

    /**
     * extract item links from category url
     */
    void extractItemLinks();

}
