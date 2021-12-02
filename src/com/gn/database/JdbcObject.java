package com.gn.database;

public class JdbcObject {
    /**
     * IP của database
     */
    private String sServerIP;
    /**
     * Username login của database
     */
    private String sUserId;
    /**
     * Password login của database
     */
    private String sPwd;
    /**
     * Tên của database
     */
    private String sDatabase;
    /**
     * Giá trị mặc định "net.sourceforge.jtds.jdbc.Driver"
     */
    private String sClass;
    /**
     * Cổng kết nối tới database
     */
    private String sPort;

    /**
     * Phương thức khởi tạo đối tượng chứa thông tin kết nối tới database
     * @param sServerIp IP của database
     * @param sUserId Username login của database
     * @param sPwd Password login của database
     * @param sDatabase Tên của database
     * @param sPort Cổng kết nối tới database
     */
    public JdbcObject(String sServerIp, String sUserId, String sPwd, String sDatabase, String sPort) {
        this.sServerIP = sServerIp;
        this.sUserId = sUserId;
        this.sPwd = sPwd;
        this.sDatabase = sDatabase;
        this.sClass = "com.mysql.cj.jdbc.Driver";
        this.sPort = sPort;
    }

    public String getsServerIP() {
        return sServerIP;
    }

    public String getsUserId() {
        return sUserId;
    }

    public String getsPwd() {
        return sPwd;
    }

    public String getsDatabase() {
        return sDatabase;
    }

    public String getsClass() {
        return sClass;
    }

    public String getsPort() {
        return sPort;
    }
}
