package com.billsoft.triptrasvc.request;

import javax.xml.bind.annotation.XmlElement;

public class GenericAuthRequest {

    @XmlElement
    private String token;

    public String getToken() {
        return token;
    }

    public GenericAuthRequest(String token) {
        this.token = token;
    }
    
    

}
