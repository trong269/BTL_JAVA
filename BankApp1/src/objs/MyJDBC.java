package objs;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class MyJDBC {
    private static String DB_URL = "jdbc:sqlserver://nhanguyen.database.windows.net:1433;database=bankjava;user=nhanguye@nhanguyen;password=123456;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
    private static String DB_Username = "nhanguyen";
    private static String DB_Password = "123456";

    public static void register(String username, String password, String CCCD, String OTP, String phoneNumber, String STK) throws SQLException {
        if (!checkUser(username, CCCD, phoneNumber)) {
            try (Connection conn = DriverManager.getConnection(DB_URL, DB_Username, DB_Password)) {
                String sql = "INSERT INTO users (username, password, balance, CCCD, OTP, phoneNumber, STK) VALUES (?, ?, ?, ?, ?, ?, ?)";
                try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setString(1, username);
                    pstmt.setString(2, password);
                    pstmt.setBigDecimal(3, BigDecimal.ZERO);
                    pstmt.setString(4, CCCD);
                    pstmt.setString(5, OTP);
                    pstmt.setString(6, phoneNumber);
                    pstmt.setString(7, STK);
                    pstmt.executeUpdate();
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "User already exists!");
        }
    }

    public static boolean checkUser(String username, String CCCD, String phoneNumber) throws SQLException {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_Username, DB_Password)) {
            String sql = "SELECT COUNT(*) FROM users WHERE username = ? OR CCCD = ? OR phoneNumber = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, username);
                pstmt.setString(2, CCCD);
                pstmt.setString(3, phoneNumber);
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        return rs.getInt(1) > 0;
                    }
                }
            }
        }
        return false;
    }

    // Các phương thức CRUD khác...

    public static User validLogin(String username, String password) throws SQLException {
        Connection cnt = DriverManager.getConnection(DB_URL, DB_Username, DB_Password);
        PreparedStatement ppst = cnt.prepareStatement("SELECT * FROM users WHERE username=? AND password=?");
        ppst.setString(1, username);
        ppst.setString(2, password);
        ResultSet rs = ppst.executeQuery();
        if (rs.next()) {
            int id = rs.getInt("id");
            String CCCD = rs.getString("CCCD");
            String OTP = rs.getString("OTP");
            String phoneNumber = rs.getString("phoneNumber");
            BigDecimal currentBalance = rs.getBigDecimal("balance");
            String STK = rs.getString("STK");
            return new User(id, username, password, currentBalance, CCCD, OTP, phoneNumber, STK);
        } else {
            return null;
        }
    }

    public static boolean resetPassword(String CCCD, String phoneNumber, String newPassword) throws SQLException {
        Connection cnt = DriverManager.getConnection(DB_URL, DB_Username, DB_Password);
        PreparedStatement ppst = cnt.prepareStatement("SELECT * FROM users WHERE CCCD=? AND phoneNumber=?");
        ppst.setString(1, CCCD);
        ppst.setString(2, phoneNumber);
        ResultSet rs = ppst.executeQuery();
        if (!rs.next()) {
            return false;
        }
        int id = rs.getInt("id");
        PreparedStatement ppst1 = cnt.prepareStatement("UPDATE users SET password=? WHERE id=?");
        ppst1.setString(1, newPassword);
        ppst1.setInt(2, id);
        ppst1.executeUpdate();
        return true;
    }

    public static ArrayList<Transaction> getPastTransaction(User user) {
        ArrayList<Transaction> pastList = new ArrayList<>();
        try {
            Connection cnt = DriverManager.getConnection(DB_URL, DB_Username, DB_Password);
            PreparedStatement ppst = cnt.prepareStatement("SELECT * FROM transactions WHERE user_id=? ORDER BY trans_date DESC");
            ppst.setInt(1, user.getId());
            ResultSet rs = ppst.executeQuery();
            while (rs.next()) {
                Transaction transaction = new Transaction(user.getId(), rs.getString("trans_type"), rs.getBigDecimal("trans_amount"), rs.getDate("trans_date"));
                pastList.add(transaction);
            }
        } catch (SQLException e) {

        }
        return pastList;
    }

    public static boolean addTransaction(Transaction transaction) {
        try {
            Connection cnt = DriverManager.getConnection(DB_URL, DB_Username, DB_Password);
            PreparedStatement ppst = cnt.prepareStatement("INSERT INTO transactions(user_id, trans_type, trans_amount, trans_date) VALUES(?,?,?,GETDATE())");
            ppst.setInt(1, transaction.getUserId());
            ppst.setString(2, transaction.getTrans_type());
            ppst.setBigDecimal(3, transaction.getTrans_amount());
            ppst.executeUpdate();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static boolean getBalance(User user){
        try{
            Connection cnt=DriverManager.getConnection(DB_URL, DB_Username, DB_Password);
            PreparedStatement ppst=cnt.prepareStatement("SELECT balance FROM users WHERE id=?");
            ppst.setInt(1, user.getId());
            ResultSet rs = ppst.executeQuery();
            if(rs.next()){
                user.setcurrentBalance(rs.getBigDecimal("balance"));
                return true;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public static boolean updateBalance(User user) {
        try {
            Connection cnt = DriverManager.getConnection(DB_URL, DB_Username, DB_Password);
            PreparedStatement ppst = cnt.prepareStatement("UPDATE users Set balance=? WHERE id = ?");
            ppst.setBigDecimal(1, user.getCurrentBalance());
            ppst.setInt(2, user.getId());
            ppst.executeUpdate();

            return true;
        } catch (SQLException e) {}

        return false;
    }

    public static boolean transfer(User user, String transferUsername, float amount) {
        try {
            Connection cnt = DriverManager.getConnection(DB_URL, DB_Username, DB_Password);
            PreparedStatement ppst = cnt.prepareStatement("SELECT * FROM users WHERE username=?");
            ppst.setString(1, transferUsername);
            ResultSet rs = ppst.executeQuery();
            while (rs.next()) {
                int userId = rs.getInt("id");
                String password = rs.getString("password");
                BigDecimal balance = rs.getBigDecimal("balance");
                String CCCD = rs.getString("CCCD");
                String phoneNumber = rs.getString("phoneNumber");
                String STK = rs.getString("STK");
                String OTP = rs.getString("OTP");
                User transferUser = new User(userId, transferUsername, password, balance, CCCD, OTP, phoneNumber, STK);
                Transaction sentTrans = new Transaction(user.getId(), "Transfer", BigDecimal.valueOf(-amount), null);
                Transaction reTrans = new Transaction(userId, "Transfer", BigDecimal.valueOf(amount), null);
                //update receiver
                transferUser.setcurrentBalance(transferUser.getCurrentBalance().add(BigDecimal.valueOf(amount)));
                updateBalance(transferUser);
                //update sendUser
                user.setcurrentBalance(user.getCurrentBalance().subtract(BigDecimal.valueOf(amount)));
                updateBalance(user);
                //add these Transaction to Database
                addTransaction(sentTrans);
                addTransaction(reTrans);
                return true;
            }
        } catch (SQLException e) {

        }
        return false;
    }

    public static boolean checkOTP(User user, String OTP) {
        try {
            int userId = user.getId();
            Connection cnt = DriverManager.getConnection(DB_URL, DB_Username, DB_Password);
            PreparedStatement ppst = cnt.prepareStatement("SELECT * from users WHERE id=?");
            ppst.setInt(1, userId);
            ResultSet rs = ppst.executeQuery();
            if (rs.next()) {
                String otpUser = rs.getString("ÖTP");
                if (OTP.equals(otpUser)) {
                    return true;
                } else {

                    return false;
                }
            }
        } catch (SQLException e) {

        }
        return false;
    }

}
