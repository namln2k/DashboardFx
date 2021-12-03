package com.gn.model;

public class Partner {
    private int partnerId;
    private String name;
    private String phone;
    private String address;

    public Partner() {
    }

    public Partner(int partnerId, String name, String phone, String address) {
        this.partnerId = partnerId;
        this.name = name;
        this.phone = phone;
        this.address = address;
    }

    public int getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(int partnerId) {
        this.partnerId = partnerId;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
