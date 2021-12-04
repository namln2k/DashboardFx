package com.gn.database;

import com.gn.model.Member;
import com.gn.model.Partner;
import com.gn.model.TableData;
import com.gn.model.Transaction;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DbUtil {

    /**
     * Đối tượng JDBCController
     */
    private final JdbcController controller = new JdbcController();
    /**
     * Đối tượng dùng để kết nối với database
     */
    private final Connection connection;

    public DbUtil() {
        connection = controller.ConnectionData();
    }

    public static void main(String[] args) throws SQLException {
        DbUtil dbUtil = new DbUtil();
//        List<Transaction> transactions = dbUtil.getDataTransaction();
//        System.out.println(transactions.get(0).toString());
        Transaction transaction = new Transaction(6, 1, 2, "LMHT", new Date(), 1000000000, "chi", "noi dung giao dich", 2);
//        dbUtil.updateTransaction(transaction);
//        dbUtil.deleteTransaction(2);
        System.out.println(dbUtil.getAccountMember("sonnv123", "123456"));
    }

    public List<Transaction> getDataTransaction() {
        List<Transaction> transactions = new ArrayList<>();
        try {
            String query = "SELECT * FROM transaction";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                transactions.add(new Transaction(resultSet.getInt("transaction_id"), resultSet.getInt("member_id"), resultSet.getInt("partner_id"), resultSet.getString("project_name"), resultSet.getDate("start_time"), resultSet.getLong("total_money"), resultSet.getString("action"), resultSet.getString("content"), resultSet.getInt("status")));
            }
//            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return transactions;
    }

    public List<TableData> getDataTable() {
        List<TableData> data = new ArrayList<>();
        try {
            String sqlQuery = "SELECT t.transaction_id, a.username, m.fullname, t.project_name, p.name, t.total_money, t.start_time, t.action, t.content, t.status\n"
                    + "FROM transaction t, member m, partner p, account a\n"
                    + "where t.member_id = m.member_id and t.partner_id = p.partner_id and a.account_id = m.account_id";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sqlQuery);
            int index = 0;
            while (rs.next()) {
                index++;
                data.add(new TableData(rs.getInt("transaction_id"), index, rs.getString("username"), rs.getString("fullname"), rs.getString("project_name"), rs.getString("name"), rs.getLong("total_money"), rs.getDate("start_time"), rs.getString("action"), rs.getString("content"), rs.getInt("status")));
            }
//            connection.close();
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int getPartnerId(String partnerName) {
        try {
            String sqlQuery = "SELECT partner_id FROM partner WHERE name = ?";
            PreparedStatement stmt = connection.prepareStatement(sqlQuery);
            stmt.setString(1, partnerName);
            ResultSet rs = stmt.executeQuery();
            if (rs.next())
                return rs.getInt("partner_id");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void addTransaction(Transaction transaction) {
        try {
            String sqlQuery = "INSERT INTO `csms`.`transaction` " + "(`member_id`, `partner_id`, `project_name`, `start_time`, " + "`total_money`, `action`, `content`, `status`) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement stmt = connection.prepareStatement(sqlQuery);
            // fill data
            stmt.setInt(1, transaction.getMemberId());
            stmt.setInt(2, transaction.getPartnerId());
            stmt.setString(3, transaction.getProjectName());
            stmt.setDate(4, new java.sql.Date(transaction.getStartTime().getTime()));
            stmt.setLong(5, transaction.getTotalMoney());
            stmt.setString(6, transaction.getAction());
            stmt.setString(7, transaction.getContent());
            stmt.setInt(8, transaction.getStatus());

            stmt.execute();
//            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateTransaction(Transaction transaction) {
        try {
            String sqlQuery = "UPDATE `csms`.`transaction` SET " + "`member_id` = ?, `partner_id` = ?, `project_name` = ?, `start_time` = ?, " + "`total_money` = ?, `action` = ?, `content` = ?, `status` = ? WHERE (`transaction_id` = ?);";
            PreparedStatement stmt = connection.prepareStatement(sqlQuery);
            // fill data
            stmt.setInt(1, transaction.getMemberId());
            stmt.setInt(2, transaction.getPartnerId());
            stmt.setString(3, transaction.getProjectName());
            stmt.setDate(4, new java.sql.Date(transaction.getStartTime().getTime()));
            stmt.setLong(5, transaction.getTotalMoney());
            stmt.setString(6, transaction.getAction());
            stmt.setString(7, transaction.getContent());
            stmt.setInt(8, transaction.getStatus());
            stmt.setInt(9, transaction.getTransactionId());

            stmt.execute();
//            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteTransaction(int transactionId) {
        try {
            String sqlQuery = "DELETE FROM `csms`.`transaction` WHERE (`transaction_id` = ?);";
            PreparedStatement stmt = connection.prepareStatement(sqlQuery);
            stmt.setInt(1, transactionId);

            stmt.execute();
//            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Member getAccountMember(String username, String password) {

        try {
            String sqlQuery = "SELECT member.* \n" +
                    "FROM member \n" +
                    "WHERE member.account_id  = (SELECT account_id FROM account WHERE username = ? and password = ?)";
            PreparedStatement stmt = connection.prepareStatement(sqlQuery);
            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            Member member;
            if (rs.next()) {
                member = new Member(
                        rs.getInt("member_id"),
                        rs.getInt("account_id"),
                        rs.getString("fullname"),
                        rs.getString("gender"),
                        rs.getDate("birthday"),
                        rs.getString("phone_number"),
                        rs.getString("address"),
                        rs.getString("tax_code"));
            } else {
                member = new Member();
            }
//            connection.close();
            return member;
        } catch (Exception e) {
            e.printStackTrace();
            return new Member();
        }
    }

    public List<Partner> getListPartner() {
        List<Partner> partners = new ArrayList<>();
        try {
            String sqlQuery = "SELECT * FROM partner";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sqlQuery);
            while (rs.next()) {
                partners.add(new Partner(
                        rs.getInt("partner_id"),
                        rs.getString("name"),
                        rs.getString("phone_number"),
                        rs.getString("address")
                ));
            }
//            connection.close();
            return partners;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<TableData> searchTransaction(String projectName, String partner) {
        List<TableData> tableData = new ArrayList<>();
        try {
            String sqlQuery = "SELECT t.transaction_id, a.username, m.fullname, t.project_name, p.name, t.total_money, t.start_time, t.action, t.content, t.status\n"
                    + "FROM transaction t, member m, partner p, account a\n"
                    + "where t.member_id = m.member_id and t.partner_id = p.partner_id "
                    + "and a.account_id = m.account_id and t.project_name like \"%?%\" and p.name like \"%?%\"";
            PreparedStatement stmt = connection.prepareStatement(sqlQuery);
            stmt.setString(1, projectName);
            stmt.setString(2, partner);
            ResultSet rs = stmt.executeQuery();
            int index = 0;
            while (rs.next()) {
                index++;
                tableData.add(new TableData(
                        rs.getInt("transaction_id"),
                        index,
                        rs.getString("username"),
                        rs.getString("fullname"),
                        rs.getString("project_name"),
                        rs.getString("name"),
                        rs.getLong("total_money"),
                        rs.getDate("start_time"),
                        rs.getString("action"),
                        rs.getString("content"),
                        rs.getInt("status")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tableData;
    }
}
