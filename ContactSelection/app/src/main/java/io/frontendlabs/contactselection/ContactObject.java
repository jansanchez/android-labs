package io.frontendlabs.contactselection;

import java.io.Serializable;

/**
 * Created by Jan Sanchez on 15/04/2015.
 */
public class ContactObject implements Serializable{

    private static final long serialVersionUID = 1L;

    private String name;
    private String phone;
    private String website;

    public ContactObject(String name, String phone, String website) {
        this.name = name;
        this.phone = phone;
        this.website = website;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }


    public ContactObject() {
    }
}
