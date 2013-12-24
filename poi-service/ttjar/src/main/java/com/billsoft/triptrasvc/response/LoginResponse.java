package com.billsoft.triptrasvc.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "login")
@XmlAccessorType(XmlAccessType.FIELD)
public class LoginResponse {
    @XmlElement
    private int id;

    public int getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    @XmlElement
    private String token;

    public LoginResponse() {
    }

    public LoginResponse(int id, String token) {
        super();
        this.id = id;
        this.token = token;
    }

    @Override
    public String toString() {
        return "LoginResponse [id=" + id + ", token=" + token + "]";
    }
    
    

}
