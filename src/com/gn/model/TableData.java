package com.gn.model;

import java.util.Date;

public class TableData {
    private int transactionId;
    private int index;
    private String username;
    private String fullName;
    private String project;
    private String partner;
    private long money;
    private Date time;
    private String action;
    private String content;
    private String status;

    public TableData() {
        this.index = 0;
    }

    public TableData(int transactionId, int index, String username, String fullName, String project, String partner, long money, Date time, String action, String content, int status) {
        this.transactionId = transactionId;
        this.index = index;
        this.username = username;
        this.fullName = fullName;
        this.project = project;
        this.partner = partner;
        this.money = money;
        this.time = time;
        this.action = action;
        this.content = content;
        this.status = status == 1 ? "Completed" : "Pending";
    }

    public TableData(int index, String username, String fullName, String project, String partner, long money, Date time, String action, String content, int status) {
        this.index = index;
        this.username = username;
        this.fullName = fullName;
        this.project = project;
        this.partner = partner;
        this.money = money;
        this.time = time;
        this.action = action;
        this.content = content;
        this.status = status == 1 ? "Completed" : "Pending";
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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

    public void setStatus(int status) {
        this.status = status == 1 ? "Completed" : "Pending";
    }
}
