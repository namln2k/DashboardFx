package com.gn.model;

import java.util.Date;

public class Transaction {
    int stt;
    String username, fullname, project, partner;
    long money;
    Date time;
    String action, content, status;

    public Transaction(int stt, String username, String fullname, String project, String partner, long money, Date time, String action, String content, String status) {
        this.stt = stt;
        this.username = username;
        this.fullname = fullname;
        this.project = project;
        this.partner = partner;
        this.money = money;
        this.time = time;
        this.action = action;
        this.content = content;
        this.status = status;
    }

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getPartner() {
        return partner;
    }

    public void setPartner(String partner) {
        this.partner = partner;
    }

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
