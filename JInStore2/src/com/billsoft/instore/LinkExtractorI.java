package com.billsoft.instore;

public interface LinkExtractorI {
    /**
     * feed url to the bot
     * 
     * @param url
     *            a string of entry url
     * @return a string of url-hash
     */
    public String feed(String url);

    /**
     * retrieve urls for given feed-url, inject them to dB
     * 
     * @return number of injected rows
     */
    public int inject();

    /**
     * marked given hash of url to certain status
     * 
     * @param hash
     *            a string of feed-url-hash
     */
    public void mark(String hash);

    /**
     * return all the matching urls
     * 
     * @return an array of string
     */
    public String[] query();
}
