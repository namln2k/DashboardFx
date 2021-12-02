package com.gn.database;

import java.sql.Connection;

public class JdbcController {
    /**
     * Đối tượng JDBCModel chứa các thông tin để kết nối
     */
    JdbcModel jdbcModel = new JdbcModel();

    /**
     * Phương thức khởi tạo kết nối tới database
     *
     * @return Connection
     */
    public Connection ConnectionData() {
        return jdbcModel.getConnectionOf();
    }
}
