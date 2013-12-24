package com.billsoft.triptrasvc.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.billsoft.triptrasvc.parcel.PoiParcel;

@XmlRootElement(name = "poi_create_request")
@XmlAccessorType(XmlAccessType.FIELD)
public class PoiCreateRequest extends GenericAuthRequest {

    public PoiCreateRequest(PoiParcel[] parcels, String token) {
        super(token);
        this.pois = parcels;
    }

    public PoiCreateRequest() {
        super(null);
    }

    @XmlElement(type = PoiParcel.class)
    @XmlElementWrapper(name = "pois", required = true)
    private PoiParcel[] pois;

    public PoiParcel[] getPois() {
        return pois;
    }

}
