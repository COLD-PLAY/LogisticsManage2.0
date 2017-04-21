package com;

import com.sun.org.apache.regexp.internal.RE;

import javax.swing.plaf.nimbus.State;
import java.sql.*;

/**
 * Created by coldplay on 17-4-21.
 */
public class user {
    private String username;
    private String password;
    private String phonenum;
    private String address;

    private final static String JDBC_DRIVE = "com.mysql.jdbc.Drive";
    private final static String DB_URL = "com:mysql://localhost:3306/coldplay";

    private final static String USER = "root";
    private final static String PASS = "liaozhou1998";

    private Connection conn = null;
    private Statement stmt = null;

    public String sql = "";

//    public user(String us, String pa, String ph, String ad) {
//        this.username = us;
//        this.password = pa;
//        this.phonenum = ph;
//        this.address = ad;
//    }

    public user(String us) {
        this.username = us;
        GetInSQL();
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getPhonenum() {
        return this.phonenum;
    }

    public String getAddress() {
        return this.address;
    }

    // 获取连接
    public Connection GetConn() {
        Connection conn = null;

        try {
            Class.forName(JDBC_DRIVE);

            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // 添加用户到数据库
    public boolean AddToSQL() {
        try {
            conn = GetConn();
            if (conn != null)
                stmt = conn.createStatement();
            else return false;

            sql = "SELECT * FROM user WHERE username='" + this.username + "';";
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                return false;
            }

            sql = "INSERT INTO user VALUE('" + this.username + "', '" + this.password + "', '" + this.phonenum + "', '" + this.address + "');";
            stmt.execute(sql);

            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    // 从数据库得到用户信息
    public boolean GetInSQL() {
        try {
            conn = GetConn();
            if (conn != null)
                stmt = conn.createStatement();
            else return false;

            sql = "SELECT * FROM user WHERE username='" + this.username + "';";
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                this.password = rs.getString("password");
                this.phonenum = rs.getString("phonenum");
                this.address = rs.getString("address");
            }
            else return false;

            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    // 升级用户信息
    public boolean UpdateToSQL() {
        try {
            conn = GetConn();
            if (conn != null)
                stmt = conn.createStatement();
            else return false;

            sql = "UPDATE FROM user SET password='" + this.password + "', phonenum='" + this.phonenum + "', address=" + this.address + "' WHERE username='" + this.username + "';";
            stmt.execute(sql);

            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    // 注销用户
    public boolean DeleteInSQL() {
        try {
            conn = GetConn();
            if (conn != null)
                stmt = conn.createStatement();
            else return false;

            sql = "DELETE FROM user WHERE username='" + username + "';";
            stmt.execute(sql);

            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
