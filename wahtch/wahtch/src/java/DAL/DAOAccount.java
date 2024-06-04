/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import entity.Account;
import entity.SecurityQuestion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author admin
 */
public class DAOAccount {

    private Connection conn;
    private List<Account> acc;
    private List<SecurityQuestion> sq;

    public List<Account> getAcc() {
        return acc;
    }

    public List<SecurityQuestion> getSq() {
        return sq;
    }

    public void setSq(List<SecurityQuestion> sq) {
        this.sq = sq;
    }

    public void setAcc(List<Account> acc) {
        this.acc = acc;
    }

    public DAOAccount() {
        conn = new DBContext().connection;
    }

    public Account loginAccount(String userName, String pass) {
        Account a = new Account(); // Khởi tạo giá trị ban đầu là null
        String sql = "SELECT [id]\n"
                + "      ,[acc_name]\n"
                + "      ,[email]\n"
                + "      ,[adress]\n" // Sửa tên cột
                + "      ,[cus_password]\n"
                + "      ,[full_name]\n"
                + "      ,[phone]\n"
                + "      ,[role_id]\n"
                + "      ,[avatar]\n"
                + "      ,[ques_id]\n"
                + "      ,[answer]\n"
                + "  FROM [Account]\n"
                + "  WHERE [acc_name] = ? AND [cus_password] = ?"; // Sử dụng tham số để tránh lỗ hổng SQL Injection
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, userName);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) { // Sử dụng if thay vì while vì chỉ cần lấy một tài khoản duy nhất
                int id = rs.getInt(1);
                String acc_name = rs.getString(2);
                String email = rs.getString(3);
                String address = rs.getString(4);
                String cus_password = rs.getString(5);
                String full_name = rs.getString(6);
                String phone = rs.getString(7);
                int role_id = rs.getInt(8);
                String avatar = rs.getString(9);
                int ques_id = rs.getInt(10);
                String answer = rs.getString(11);
                a = new Account(id, acc_name, email, address, cus_password, full_name, phone, avatar, answer, role_id, ques_id);
            }
        } catch (SQLException e) {
            // Xử lý ngoại lệ
        }
        return a;
    }

    public Account getAccountbyId(int cusid) {
        Account a = new Account(); // Khởi tạo giá trị ban đầu là null
        String sql = "SELECT [id]\n"
                + "      ,[acc_name]\n"
                + "      ,[email]\n"
                + "      ,[adress]\n" // Sửa tên cột
                + "      ,[cus_password]\n"
                + "      ,[full_name]\n"
                + "      ,[phone]\n"
                + "      ,[role_id]\n"
                + "      ,[avatar]\n"
                + "      ,[ques_id]\n"
                + "      ,[answer]\n"
                + "  FROM [Account]\n"
                + "  WHERE [id] = ?"; // Sử dụng tham số để tránh lỗ hổng SQL Injection
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, cusid);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) { // Sử dụng if thay vì while vì chỉ cần lấy một tài khoản duy nhất
                int id = rs.getInt(1);
                String acc_name = rs.getString(2);
                String email = rs.getString(3);
                String address = rs.getString(4);
                String cus_password = rs.getString(5);
                String full_name = rs.getString(6);
                String phone = rs.getString(7);
                int role_id = rs.getInt(8);
                String avatar = rs.getString(9);
                int ques_id = rs.getInt(10);
                String answer = rs.getString(11);
                a = new Account(id, acc_name, email, address, cus_password, full_name, phone, avatar, answer, role_id, ques_id);
            }
        } catch (SQLException e) {
            // Xử lý ngoại lệ
        }
        return a;
    }

    public void getAllAccount() {
        String sql = "SELECT * FROM Account";
        acc = new Vector<Account>();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String acc_name = rs.getString(2);
                String email = rs.getString(3);
                String address = rs.getString(4);
                String cus_password = rs.getString(5);
                String full_name = rs.getString(6);
                String phone = rs.getString(7);
                int role_id = rs.getInt(8); // Thay đổi chỉ số index
                String avatar = rs.getString(9); // Thay đổi chỉ số index
                int ques_id = rs.getInt(10); // Thay đổi chỉ số index
                String answer = rs.getString(11); // Thay đổi chỉ số index
                acc.add(new Account(id, acc_name, email, address, cus_password, full_name, phone, avatar, answer, role_id, ques_id));
            }
        } catch (SQLException e) {
            // Xử lý ngoại lệ
        }
    }

    public void insertAccount(String acc_name, String email, String pass, int roleid) {
        String sql = "INSERT INTO [dbo].[Account]\n"
                + "           ([acc_name]\n"
                + "           ,[email]\n"
                + "           ,[adress]\n"
                + "           ,[cus_password]\n"
                + "           ,[full_name]\n"
                + "           ,[role_id])\n"
                + "     VALUES\n"
                + "           (?,?,'',?,'',?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, acc_name);
            ps.setString(2, email);
            ps.setString(3, pass);
            ps.setInt(4, roleid);
            ps.executeUpdate();
        } catch (SQLException e) {
        }
    }

    public void insertAccountbyGoogle(String email) {
        String sql = "INSERT INTO [dbo].[Account]\n"
                + "           ([acc_name]\n"
                + "           ,[email]\n"
                + "           ,[adress]\n"
                + "           ,[cus_password]\n"
                + "           ,[full_name]\n"
                + "           ,[role_id])\n"
                + "     VALUES\n"
                + "           (?,?,'','1','',1)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, email);
            ps.executeUpdate();
        } catch (SQLException e) {
        }
    }

    public void changetAccountInfo(String email, String adress, String fullname, String phone, String ava, int sqid, String ans, int id) {
        String sql = "UPDATE [dbo].[Account]\n"
                + "   SET [email] = ?\n"
                + "      ,[adress] = ?\n"
                + "      ,[full_name] = ?\n"
                + "      ,[phone] = ?\n"
                + "      ,[avatar] = ?\n"
                + "      ,[ques_id] = ?\n"
                + "      ,[answer] = ?\n"
                + " WHERE id = ? ";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, adress);
            ps.setString(3, fullname);
            ps.setString(4, phone);
            ps.setString(5, ava);
            ps.setInt(6, sqid);
            ps.setString(7, ans);
            ps.setInt(8, id);
            ps.executeUpdate();
        } catch (SQLException e) {
        }
    }

    public void changetAccountPass(String email, String phone, String newpass) {
        String sql = "UPDATE [Account]\n"
                + "SET [cus_password] = ?\n"
                + "WHERE [email] = ? and [phone] = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, newpass);
            ps.setString(2, email);
            ps.setString(3, phone);
            ps.executeUpdate();
        } catch (SQLException e) {
        }
    }
public static void main(String[] args) {
        DAOAccount dao = new DAOAccount();
        Account acc = dao.loginAccountbyGoogle("huyttu123@gmail.com");

        if (acc == null) {
            System.out.println("that bai");
        }

    }
    public Account loginAccountbyGoogle(String userName) {
        Account a = null; // Khởi tạo giá trị ban đầu là null
        String sql = "SELECT *\n"
                + "FROM Account\n"
                + "WHERE acc_name = ? and email = ?\n";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, userName);
            ps.setString(2, userName);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) { // Sử dụng if thay vì while vì chỉ cần lấy một tài khoản duy nhất
                int id = rs.getInt(1);
                String acc_name = rs.getString(2);
                String email = rs.getString(3);
                String address = rs.getString(4);
                String cus_password = rs.getString(5);
                String full_name = rs.getString(6);
                String phone = rs.getString(7);
                int role_id = rs.getInt(8); // Sửa chỉ số index
                String avatar = rs.getString(9);
                int ques_id = rs.getInt(10);
                String answer = rs.getString(11);
                a = new Account(id, acc_name, email, address, cus_password, full_name, phone, avatar, answer, role_id, ques_id);
            }
        } catch (SQLException e) {
            // Xử lý ngoại lệ
        }
        return a;
    }

    

    public Account getAccountformemailandphone(String fgemail, String fgphone) {
        Account a = null; // Khởi tạo giá trị ban đầu là null
        String sql = "SELECT *\n"
                + "FROM Account\n"
                + "WHERE [email] = ? and [phone] = ? \n";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, fgemail);
            ps.setString(2, fgphone);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) { // Sử dụng if thay vì while vì chỉ cần lấy một tài khoản duy nhất
                int id = rs.getInt(1);
                String acc_name = rs.getString(2);
                String email = rs.getString(3);
                String address = rs.getString(4);
                String cus_password = rs.getString(5);
                String full_name = rs.getString(6);
                String phone = rs.getString(7);
                int role_id = rs.getInt(8); // Sửa chỉ số index
                String avatar = rs.getString(9);
                int ques_id = rs.getInt(10);
                String answer = rs.getString(11);
                a = new Account(id, acc_name, email, address, cus_password, full_name, phone, avatar, answer, role_id, ques_id);
            }
        } catch (SQLException e) {
            // Xử lý ngoại lệ
        }
        return a;
    }

    public void getQuestion() {
        String sql = "SELECT [id]\n"
                + "      ,[ques_name]\n"
                + "  FROM [dbo].[SecurityQuestions]";
        sq = new Vector<SecurityQuestion>();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String acc_name = rs.getString(2);

                sq.add(new SecurityQuestion(id, acc_name));
            }
        } catch (SQLException e) {

        }
    }

}
