package com.gn.model;

import java.util.Date;

public class Member {
    private int index;
    private int memberId;
    private int accountId;
    private String fullName;
    private String gender;
    private Date birthday;
    private String phone;
    private String address;
    private String taxCode;
    private String career;
    private String email;
    private String site;
    private String brief;
    private String intro;

    public Member(int index, int memberId, int accountId, String fullName, String gender, Date birthday, String phone, String address, String taxCode, String career, String email, String site, String brief, String intro) {
        this.index = index;
        this.memberId = memberId;
        this.accountId = accountId;
        this.fullName = fullName;
        this.gender = gender;
        this.birthday = birthday;
        this.phone = phone;
        this.address = address;
        this.taxCode = taxCode;
        this.career = career;
        this.email = email;
        this.site = site;
        this.brief = brief;
        this.intro = intro;
    }

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

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
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
