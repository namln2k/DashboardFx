package com.gn.model;

import java.util.Date;

public class Member {
//    TODO: Thêm các trường:
//          - Career (Nghề nghiệp)
//          - Email
//          - Site (Kiểu github/tweet/...)
//          - Brief (Sơ lược)
//          - Intro (Giới thiệu)
    private int memberId;
    private int accountId;
    private String fullName;
    private String gender;
    private Date birthday;
    private String phone;
    private String address;
    private String taxCode;

    public Member() {
        this.memberId = 0;
    }

    public Member(int memberId, int accountId, String fullName, String gender, Date birthday, String phone, String address, String taxCode) {
        this.memberId = memberId;
        this.accountId = accountId;
        this.fullName = fullName;
        this.gender = gender;
        this.birthday = birthday;
        this.phone = phone;
        this.address = address;
        this.taxCode = taxCode;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
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

    public String getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }
}
