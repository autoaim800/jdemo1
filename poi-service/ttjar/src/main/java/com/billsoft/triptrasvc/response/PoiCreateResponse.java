package com.billsoft.triptrasvc.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "poi_create_response")
@XmlAccessorType(XmlAccessType.FIELD)
public class PoiCreateResponse {

    @XmlElement(name = "id")
    @XmlElementWrapper(name = "ids", required = true)
    private int[] ids;

    public PoiCreateResponse() {
    }

    public PoiCreateResponse(int[] poiIds) {
        this.ids = poiIds;
    }

}
