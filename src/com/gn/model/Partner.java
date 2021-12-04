package com.gn.model;

public class Partner {
    private int index;
    private int partnerId;
    private String name;
    private String phone;
    private String address;

    public Partner() {
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Partner(int index, int partnerId, String name, String phone, String address) {
        this.index = index;
        this.partnerId = partnerId;
        this.name = name;
        this.phone = phone;
        this.address = address;
    }

    public Partner(String name, String phone, String address) {
        this.name = name;
        this.phone = phone;
        this.address = address;
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
