package com.gn.model;


import java.util.Date;

public class Transaction {
    private int transactionId;
    private int memberId;
    private int partnerId;
    private String projectName;
    private Date startTime;
    private long totalMoney;
    private String action;
    private String content;
    private int status;

    public Transaction(int transactionId, int memberId, int partnerId, String projectName, Date startTime, long totalMoney, String action, String content, int status) {
        this.transactionId = transactionId;
        this.memberId = memberId;
        this.partnerId = partnerId;
        this.projectName = projectName;
        this.startTime = startTime;
        this.totalMoney = totalMoney;
        this.action = action;
        this.content = content;
        this.status = status;
    }

    public Transaction(int memberId, int partnerId, String projectName, Date startTime, long totalMoney, String action, String content, int status) {
        this.memberId = memberId;
        this.partnerId = partnerId;
        this.projectName = projectName;
        this.startTime = startTime;
        this.totalMoney = totalMoney;
        this.action = action;
        this.content = content;
        this.status = status;
    }

    public Transaction() {
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public int getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(int partnerId) {
        this.partnerId = partnerId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public long getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(long totalMoney) {
        this.totalMoney = totalMoney;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", memberId=" + memberId +
                ", partnerId=" + partnerId +
                ", projectName='" + projectName + '\'' +
                ", startTime=" + startTime +
                ", totalMoney=" + totalMoney +
                ", action='" + action + '\'' +
                ", content='" + content + '\'' +
                ", status=" + status +
                '}';
    }
}
