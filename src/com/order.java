package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by coldplay on 17-4-24.
 */
public class order {
    private String fromusr;
    private String fromphonenum;
    private String fromaddress;
    private String touser;
    private String tophonenum;
    private String toaddress;
    private String ordernum;

    private final static String JDBC_DRIVE = "com.mysql.jdbc.Drive";
    private final static String DB_URL = "com:mysql://localhost:3306/coldplay";

    private final static String USER = "root";
    private final static String PASS = "liaozhou1998";

    private Connection conn = null;
    private Statement stmt = null;

    private String sql = "";
    private ResultSet rs = null;

    public order(String fromuser, String fromphonenum, String fromaddress, String touser, String tophonenum, String toaddress, String ordernum) {
        this.fromusr = fromuser;
        this.fromphonenum = fromphonenum;
        this.fromaddress = fromaddress;
        this.touser = touser;
        this.tophonenum = tophonenum;
        this.toaddress = toaddress;

        this.ordernum = ordernum;
    }

    public order(String ordernum) {
        this.ordernum = ordernum;
        GetInSQL();
    }

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

    public ResultSet GetInSQL() {
        try {
            conn = GetConn();
            if (conn != null)
                stmt = conn.createStatement();
            else return null;

            sql = "SELECT * FROM orders;";
            rs = stmt.executeQuery(sql);

            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return rs;
    }
}
