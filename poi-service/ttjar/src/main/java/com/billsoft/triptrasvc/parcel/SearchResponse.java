package com.billsoft.triptrasvc.parcel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "search_result")
@XmlAccessorType(XmlAccessType.FIELD)
public class SearchResponse {

    @Override
    public String toString() {
        return "SearchResponse [pois=" + pois + ", page=" + page + ", size=" + size + ", count="
                + count + "]";
    }

    public SearchResponse() {

    }

    public SearchResponse(PoiParcel[] pois, int page, int count) {
        this.pois = pois;
        this.page = page;
        this.size = pois.length;
        this.count = count;
    }

    @XmlElement(name="poi", type = PoiParcel.class)
    @XmlElementWrapper(name = "pois", required = true)
    private PoiParcel[] pois;

    @XmlElement
    private int page;

    @XmlElement
    private int size;

    @XmlElement
    private int count;
}