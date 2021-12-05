package com.gn.database;

import com.gn.model.*;

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
        Transaction transaction = new Transaction(6, 1, 2, "LMHT", new Date(), 1000000000, "chi", "noi dung giao dich", 2);
//        dbUtil.updateTransaction(transaction);
//        dbUtil.deleteTransaction(2);
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
            String sqlQuery = "SELECT t.transaction_id, a.username, m.fullname, t.project_name, p.name, t.total_money, t.start_time, t.action, t.content, t.status\n" + "FROM transaction t, member m, partner p, account a\n" + "where t.member_id = m.member_id and t.partner_id = p.partner_id and a.account_id = m.account_id";
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
            if (rs.next()) return rs.getInt("partner_id");
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
            stmt.setString(3, transaction.getProjectName() == null ? "" : transaction.getProjectName());
            stmt.setDate(4, transaction.getStartTime() == null ? null : new java.sql.Date(transaction.getStartTime().getTime()));
            stmt.setLong(5, transaction.getTotalMoney());
            stmt.setString(6, transaction.getAction() == null ? "" : transaction.getAction());
            stmt.setString(7, transaction.getContent() == null ? "" : transaction.getContent());
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
            stmt.setString(3, transaction.getProjectName() == null ? "" : transaction.getProjectName());
            stmt.setDate(4, transaction.getStartTime() == null ? null : new java.sql.Date(transaction.getStartTime().getTime()));
            stmt.setLong(5, transaction.getTotalMoney());
            stmt.setString(6, transaction.getAction() == null ? "" : transaction.getAction());
            stmt.setString(7, transaction.getContent() == null ? "" : transaction.getContent());
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
            String sqlQuery = "SELECT member.* \n" + "FROM member \n" + "WHERE member.account_id  = (SELECT account_id FROM account WHERE username = ? and password = ?)";
            PreparedStatement stmt = connection.prepareStatement(sqlQuery);
            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            Member member;
            if (rs.next()) {
                member = new Member(1, rs.getInt("member_id"), rs.getInt("account_id"), rs.getString("fullname"), rs.getString("gender"), rs.getDate("birthday"), rs.getString("phone_number"), rs.getString("address"), rs.getString("tax_code"), rs.getString("career"), rs.getString("email"), rs.getString("site"), rs.getString("brief"), rs.getString("intro"));
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
            int index = 0;
            while (rs.next()) {
                index++;
                partners.add(new Partner(index, rs.getInt("partner_id"), rs.getString("name"), rs.getString("phone_number"), rs.getString("address")));
            }
//            connection.close();
            return partners;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

//    TODO 0: Sửa hàm searchTransaction, tìm kiếm theo 2 trường startTime và endTime

    public List<TableData> searchTransaction(String projectName, String partner, String username, Date startTime, Date endTime) {
        List<TableData> tableData = new ArrayList<>();
        java.sql.Date start = null;
        java.sql.Date end = null;
        if (startTime != null) {
            start = new java.sql.Date(startTime.getTime());
        }
        if (endTime != null) {
            end = new java.sql.Date(endTime.getTime());
        }
        try {
            String sqlQuery;
            PreparedStatement stmt;
            if (start != null && end != null) {
                sqlQuery = "SELECT t.transaction_id, a.username, m.fullname, t.project_name, p.name, t.total_money, t.start_time, t.action, t.content, t.status\n"
                        + "FROM transaction t, member m, partner p, account a\n" + "where t.member_id = m.member_id and t.partner_id = p.partner_id "
                        + "and a.account_id = m.account_id and t.project_name like ? and p.name like ? and a.username like ? "
                        + "and t.start_time > ? and t.start_time < ?";
                stmt = connection.prepareStatement(sqlQuery);
                stmt.setString(1, "%" + projectName + "%");
                stmt.setString(2, "%" + partner + "%");
                stmt.setString(3, "%" + username + "%");
                stmt.setDate(4, start);
                stmt.setDate(5, end);
            } else if (start == null && end != null) {
                sqlQuery = "SELECT t.transaction_id, a.username, m.fullname, t.project_name, p.name, t.total_money, t.start_time, t.action, t.content, t.status\n"
                        + "FROM transaction t, member m, partner p, account a\n" + "where t.member_id = m.member_id and t.partner_id = p.partner_id "
                        + "and a.account_id = m.account_id and t.project_name like ? and p.name like ? and a.username like ? "
                        + "and t.start_time < ?";
                stmt = connection.prepareStatement(sqlQuery);
                stmt.setString(1, "%" + projectName + "%");
                stmt.setString(2, "%" + partner + "%");
                stmt.setString(3, "%" + username + "%");
                stmt.setDate(4, end);
            } else if (start != null) {
                sqlQuery = "SELECT t.transaction_id, a.username, m.fullname, t.project_name, p.name, t.total_money, t.start_time, t.action, t.content, t.status\n"
                        + "FROM transaction t, member m, partner p, account a\n" + "where t.member_id = m.member_id and t.partner_id = p.partner_id "
                        + "and a.account_id = m.account_id and t.project_name like ? and p.name like ? and a.username like ? "
                        + "and t.start_time > ?";
                stmt = connection.prepareStatement(sqlQuery);
                stmt.setString(1, "%" + projectName + "%");
                stmt.setString(2, "%" + partner + "%");
                stmt.setString(3, "%" + username + "%");
                stmt.setDate(4, start);
            } else {
                sqlQuery = "SELECT t.transaction_id, a.username, m.fullname, t.project_name, p.name, t.total_money, t.start_time, t.action, t.content, t.status\n"
                        + "FROM transaction t, member m, partner p, account a\n" + "where t.member_id = m.member_id and t.partner_id = p.partner_id "
                        + "and a.account_id = m.account_id and t.project_name like ? and p.name like ? and a.username like ? ";
                stmt = connection.prepareStatement(sqlQuery);
                stmt.setString(1, "%" + projectName + "%");
                stmt.setString(2, "%" + partner + "%");
                stmt.setString(3, "%" + username + "%");
            }
            ResultSet rs = stmt.executeQuery();
            int index = 0;
            while (rs.next()) {
                index++;
                tableData.add(new TableData(rs.getInt("transaction_id"), index, rs.getString("username"), rs.getString("fullname"), rs.getString("project_name"), rs.getString("name"), rs.getLong("total_money"), rs.getDate("start_time"), rs.getString("action"), rs.getString("content"), rs.getInt("status")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tableData;
    }

    public List<Member> getListMember() {
        List<Member> memberData = new ArrayList<>();
        try {
            String sqlQuery = "SELECT * FROM member";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sqlQuery);
            int index = 0;
            while (rs.next()) {
                index++;
                memberData.add(new Member(index, rs.getInt("member_id"), rs.getInt("account_id"), rs.getString("fullname"), rs.getString("gender"), rs.getDate("birthday"), rs.getString("phone_number"), rs.getString("address"), rs.getString("tax_code"), rs.getString("career"), rs.getString("email"), rs.getString("site"), rs.getString("brief"), rs.getString("intro")));
            }
            return memberData;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void deleteMember(int memberId) {
        try {
            String sqlQuery = "DELETE FROM `csms`.`member` WHERE (`member_id` = ?);";
            PreparedStatement stmt = connection.prepareStatement(sqlQuery);
            stmt.setInt(1, memberId);

            stmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deletePartner(int partnerId) {
        try {
            String sqlQuery = "DELETE FROM `csms`.`partner` WHERE (`partner_id` = ?);";
            PreparedStatement stmt = connection.prepareStatement(sqlQuery);
            stmt.setInt(1, partnerId);

            stmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addMember(int accountId) {
        try {
            String sqlQuery = "INSERT INTO `csms`.`member` (`account_id`) VALUES (?);";
            PreparedStatement stmt = connection.prepareStatement(sqlQuery);
            stmt.setInt(1, accountId);
            stmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addPartner(Partner partner) {
        try {
            String sqlQuery = "INSERT INTO `csms`.`partner` (`name`, `phone_number`, `address`) " + "VALUES (?, ?, ?);";
            PreparedStatement stmt = connection.prepareStatement(sqlQuery);
            stmt.setString(1, partner.getName() == null ? "" : partner.getName());
            stmt.setString(2, partner.getPhone() == null ? "" : partner.getPhone());
            stmt.setString(3, partner.getAddress() == null ? "" : partner.getAddress());
            stmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updatePartner(Partner partner) {
        try {
            String sqlQuery = "UPDATE `csms`.`partner` " + "SET `name` = ?, `phone_number` = ?, `address` = ?" + "WHERE (`partner_id` = ?);";
            PreparedStatement stmt = connection.prepareStatement(sqlQuery);
            stmt.setString(1, partner.getName() == null ? "" : partner.getName());
            stmt.setString(2, partner.getPhone() == null ? "" : partner.getPhone());
            stmt.setString(3, partner.getAddress() == null ? "" : partner.getAddress());
            stmt.setInt(4, partner.getPartnerId());

            stmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int addAccount(Account account) {
        try {
            String sqlQuery = "INSERT INTO `csms`.`account` (`username`, `password`, `role`) " + "VALUES (?, ?, ?);";
            PreparedStatement stmt = connection.prepareStatement(sqlQuery);
            stmt.setString(1, account.getUsername());
            stmt.setString(2, account.getPassword());
            stmt.setInt(3, 1);

            stmt.execute();

            String query = "SELECT account_id FROM account ORDER BY account_id desc";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            if (rs.next()) {
                int accountId = rs.getInt("account_id");
                addMember(accountId);
                return accountId;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void updateMember(Member member) {
        try {
            String sqlQuery = "UPDATE `csms`.`member` " + "SET `fullname` = ?, `gender` = ?, `birthday` = ?, " + "`phone_number` = ?, `address` = ?, `tax_code` = ?, `career` = ?, " + "`email` = ?, `site` = ?, `brief` = ?, `intro` = ? WHERE (`account_id` = ?);";
            PreparedStatement stmt = connection.prepareStatement(sqlQuery);
            stmt.setString(1, member.getFullName() == null ? "" : member.getFullName());
            stmt.setString(2, member.getGender() == null ? "" : member.getGender());
            stmt.setDate(3, member.getBirthday() == null ? null : new java.sql.Date(member.getBirthday().getTime()));
            stmt.setString(4, member.getPhone() == null ? "" : member.getPhone());
            stmt.setString(5, member.getAddress() == null ? "" : member.getAddress());
            stmt.setString(6, member.getTaxCode() == null ? "" : member.getTaxCode());
            stmt.setString(7, member.getCareer() == null ? "" : member.getCareer());
            stmt.setString(8, member.getEmail() == null ? "" : member.getEmail());
            stmt.setString(9, member.getSite() == null ? "" : member.getSite());
            stmt.setString(10, member.getBrief() == null ? "" : member.getBrief());
            stmt.setString(11, member.getIntro() == null ? "" : member.getIntro());
            stmt.setInt(12, member.getAccountId());

            stmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<String> getListUsername() {
        List<String> listUsername = new ArrayList<>();
        try {
            String sqlQuery = "Select username from account";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sqlQuery);
            while (rs.next()) {
                listUsername.add(rs.getString("username"));
            }
            return listUsername;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
