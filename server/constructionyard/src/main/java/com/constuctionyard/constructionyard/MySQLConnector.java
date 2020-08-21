package com.constuctionyard.constructionyard;

import java.sql.*;
import java.util.*;

public class MySQLConnector {
    private String connString;
    private String username;
    private String password;

    public MySQLConnector(String host, String port, String database, String username, String password) {
        this.username = username;
        this.password = password;
        this.connString = "jdbc:mysql://" + host + ":" + port + "/" + database + "?autoReconnect=true&useSSL=false";
    }

    public List<Report> GetReports() {
        List<Report> reportList = new ArrayList<Report>();

        try{
            Connection conn = DriverManager.getConnection(this.connString, this.username, this.password);
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM reports");
            while(rs.next()) {
                Report tempReport = new Report(rs.getString(2));
                tempReport.setID(rs.getInt(1));
                reportList.add(tempReport);
            }
            conn.close();
            
        } catch(Exception e) {
            System.out.println(e);
        }

        return reportList;
    }

    public List<Report> GetReport(int id) {
        List<Report> reportList = new ArrayList<Report>();

        try{
            Connection conn = DriverManager.getConnection(this.connString, this.username, this.password);
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM reports WHERE id=" + id);
            while(rs.next()) {
                Report tempReport = new Report(rs.getString(2));
                tempReport.setID(rs.getInt(1));
                reportList.add(tempReport);
                break;
            }
            conn.close();
            
        } catch(Exception e) {
            System.out.println(e);
        }
        return reportList;
    }

    public int CreateReport(Report report) {
        int id = 0;

        try{
            Connection conn = DriverManager.getConnection(this.connString, this.username, this.password);
            PreparedStatement statement = conn.prepareStatement("INSERT INTO reports (report) VALUES(?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, report.getText());
            id = statement.executeUpdate();
            conn.close();
            
        } catch(Exception e) {
            System.out.println(e);
        }

        return id;
    }
}