package br.com.t2s;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Web application lifecycle listener.
 *
 * @author bruno
 */
public class DBListener implements ServletContextListener {

    private static final String CLASS_NAME = "org.sqlite.JDBC";
    private static final String DB_URL = "jdbc:sqlite:D:\\www\\sqlite\\databases\\database.db";

    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(DB_URL);
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Connection conn = null;
        Statement stmt = null;

        try {
            Class.forName(CLASS_NAME);
            conn = DBListener.getConnection();
            stmt = conn.createStatement();

            String createContainer = ""
                    + "CREATE TABLE IF NOT EXISTS containers("
                    + "cd_container VARCAHR(11) NOT NULL PRIMARY KEY UNIQUE,"
                    + "nm_client VARCHAR(255) NOT NULL,"
                    + "cd_type int NOT NULL,"
                    + "cd_status int NOT NULL,"
                    + "cd_category int NOT NULL"
                    + ");";
            String createMovement = ""
                    + "CREATE TABLE IF NOT EXISTS movements("
                    + "cd_container VARCAHR(11) NOT NULL,"
                    + "cd_type_movement int NOT NULL,"
                    + "start_date DATETIME NOT NULL,"
                    + "end_date DATETIME NOT NULL"
                    + ");";

            stmt.execute(createContainer);
            stmt.execute(createMovement);
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

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
