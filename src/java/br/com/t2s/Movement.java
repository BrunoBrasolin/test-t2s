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
public class Movement {

    private long rowid;
    private String container;
    private int movement_type;
    private String start_date;
    private String end_date;

    public Movement(long rowid, String container, int movement_type, String start_date, String end_date) {
        this.rowid = rowid;
        this.container = container;
        this.movement_type = movement_type;
        this.start_date = start_date;
        this.end_date = end_date;
    }

    public static ArrayList<Movement> getList() {
        ArrayList<Movement> list = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = DBListener.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT rowid, * FROM movements;");

            while (rs.next()) {
                list.add(new Movement(
                        rs.getLong("rowid"),
                        rs.getString("cd_container"),
                        rs.getInt("cd_type_movement"),
                        rs.getString("start_date"),
                        rs.getString("end_date")
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

    public static void insert(String container, int movement_type, String start_date, String end_date) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DBListener.getConnection();
            stmt = conn.prepareStatement("INSERT INTO movements VALUES (?,?,?,?);");
            stmt.setString(1, container);
            stmt.setInt(2, movement_type);
            stmt.setString(3, start_date);
            stmt.setString(4, end_date);

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

    public static void delete(long rowid) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DBListener.getConnection();
            stmt = conn.prepareStatement("DELETE FROM movements WHERE rowid = ?;");
            stmt.setLong(1, rowid);

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

    public static String showMovementName(int type) {
        switch (type) {
            case 0:
                return "Embarque";
            case 1:
                return "Desmbarque";
            case 2:
                return "Gate In";
            case 3:
                return "Gate Out";
            case 4:
                return "Posicionamento Pilha";
            case 5:
                return "Pesagem";
            case 6:
                return "Scanner";
            default:
                return "Erro ao buscar nome do tipo de movimentação";
        }
    }

    public long getRowid() {
        return rowid;
    }

    public void setRowid(long rowid) {
        this.rowid = rowid;
    }

    public String getContainer() {
        return container;
    }

    public void setContainer(String container) {
        this.container = container;
    }

    public int getMovement_type() {
        return movement_type;
    }

    public void setMovement_type(int movement_type) {
        this.movement_type = movement_type;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

}
