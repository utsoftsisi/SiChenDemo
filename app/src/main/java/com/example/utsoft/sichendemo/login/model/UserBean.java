package com.example.utsoft.sichendemo.login.model;

/**
 * Created by chensi on 2017/3/21.
 */

public class UserBean {

    private String name;
    private String city;

    public UserBean(String name, String city) {
        this.name = name;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
