package com.billsoft.triptrasvc.parcel;

import javax.xml.bind.annotation.XmlElement;

public class GenericAuthRequest {

    @XmlElement
    private String token;

    public GenericAuthRequest(String token) {
        this.token = token;
    }

}
