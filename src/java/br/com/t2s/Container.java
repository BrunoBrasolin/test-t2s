/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.t2s;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author bruno
 */
public class Container {

    private String container;
    private String client;
    private int type;
    private int status;
    private int category;

    public Container(String container, String client, int type, int status, int category) {
        this.container = container;
        this.client = client;
        this.type = type;
        this.status = status;
        this.category = category;
    }

    public static ArrayList<Container> getList() {
        ArrayList<Container> list = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = DBListener.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM containers;");

            while (rs.next()) {
                list.add(new Container(
                        rs.getString("cd_container"),
                        rs.getString("nm_client"),
                        rs.getInt("cd_type"),
                        rs.getInt("cd_status"),
                        rs.getInt("cd_category")
                ));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                conn.close();
                stmt.close();
                rs.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        return list;
    }

    public static void insert(String container, String client, int type, int status, int category) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DBListener.getConnection();
            stmt = conn.prepareStatement("INSERT INTO containers VALUES (?,?,?,?,?);");
            stmt.setString(1, container);
            stmt.setString(2, client);
            stmt.setInt(3, type);
            stmt.setInt(4, status);
            stmt.setInt(5, category);

            stmt.execute();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                conn.close();
                stmt.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void delete(String container) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DBListener.getConnection();
            stmt = conn.prepareStatement("DELETE FROM containers WHERE cd_container = ?;");
            stmt.setString(1, container);

            stmt.execute();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                conn.close();
                stmt.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    public String getContainer() {
        return container;
    }

    public void setContainer(String container) {
        this.container = container;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

}
