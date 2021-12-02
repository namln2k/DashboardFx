package com.gn.database;

import com.gn.model.Transaction;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DbUtil {

    /**
     * Đối tượng JDBCController
     */
    private JdbcController controller = new JdbcController();
    /**
     * Đối tượng dùng để kết nối với database
     */
    private Connection connection;

    public DbUtil() {
        connection = controller.ConnectionData();
    }

    public List<Transaction> getDataTransaction() throws SQLException {
        List<Transaction> transactions = new ArrayList<>();
        try {
            String query = "SELECT * FROM transaction";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                transactions.add(new Transaction(
                        resultSet.getInt("transaction_id"),
                        resultSet.getInt("member_id"),
                        resultSet.getInt("partner_id"),
                        resultSet.getString("project_name"),
                        resultSet.getDate("start_time"),
                        resultSet.getLong("total_money"),
                        resultSet.getString("action"),
                        resultSet.getString("content"),
                        resultSet.getInt("status")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return transactions;
    }

    public static void main(String[] args) throws SQLException {
        DbUtil dbUtil = new DbUtil();
        List<Transaction> transactions = dbUtil.getDataTransaction();
        System.out.println(transactions.get(0).toString());
    }
}
