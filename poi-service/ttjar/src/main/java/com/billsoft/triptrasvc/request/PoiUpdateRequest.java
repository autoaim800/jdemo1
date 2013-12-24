package com.billsoft.triptrasvc.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.billsoft.triptrasvc.parcel.PoiParcel;

@XmlRootElement(name = "poi_update_request")
@XmlAccessorType(XmlAccessType.FIELD)
public class PoiUpdateRequest extends GenericAuthRequest {

    public PoiUpdateRequest(PoiParcel[] parcels, String token) {
        super(token);
        this.pois = parcels;
    }

    public PoiUpdateRequest() {
        super(null);
    }

    @XmlElement
    @XmlElementWrapper(name = "pois", required = true)
    private PoiParcel[] pois;

    public PoiParcel[] getPois() {
        return pois;
    }

}
