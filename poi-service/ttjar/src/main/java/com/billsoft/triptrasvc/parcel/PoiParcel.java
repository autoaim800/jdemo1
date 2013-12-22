package com.billsoft.triptrasvc.parcel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "poi")
@XmlAccessorType(XmlAccessType.FIELD)
public class PoiParcel {

    @XmlElement
    private int parentId;

    @XmlElement
    private int id;

    public PoiParcel(int parentId) {
        this.parentId = parentId;
        this.id = -1;
    }

    public PoiParcel(int parentId, int id) {
        this.parentId = parentId;
        this.id = id;
    }

    public PoiParcel() {
        this.parentId = -1;
        this.id = -1;
    }

    public int getParentId() {
        return this.parentId;
    }

    public int getId() {
        return id;
    }

    @XmlElement(name = "image")
    @XmlElementWrapper(name = "images", required = true)
    private String[] images = new String[] { "111", "222" };

    @XmlElement(name = "video")
    @XmlElementWrapper(name = "videos", required = true)
    private String[] videos = new String[] { "444", "333" };

    @XmlElement
    private String title = "a brief title";

    @XmlElement
    private String description = "a very long description";

    @XmlElement
    private String category = "a short category name";

    @XmlElement
    private int categoryId = 100;

}
