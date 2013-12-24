package com.billsoft.triptrasvc.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "login")
@XmlAccessorType(XmlAccessType.FIELD)
public class LoginRequest extends GenericRequest {

    @XmlElement
    private String username;

    @XmlElement
    private String passhash;

    public LoginRequest(String username, String passhash) {
        super();
        this.username = username;
        this.passhash = passhash;
    }

    public LoginRequest() {
        super();
    }

    public String getUsername() {
        return username;
    }

    public String getPasshash() {
        return passhash;
    }

}
