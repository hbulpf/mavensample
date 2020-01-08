
package dev.lpf.db.c3p0.demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * JDBC 工具类
 */
public class C3p0Utils {
    private static final Logger LOGGER = LoggerFactory.getLogger("dev.lpf.db.c3p0.demo.C3p0Utils");

    /**
     * 通过标识名来创建相应连接池
     */
    static ComboPooledDataSource dataSource = new ComboPooledDataSource("mysql");

    /**
     * Gets connection.
     * 从连接池中取用一个连接
     * @return the connection
     */
    public static Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (Exception e) {
            LOGGER.error("Exception in C3p0Utils!\n" + e.getMessage());
        }
        return null;
    }

    /**
     * 释放连接回连接池
     *
     * @param conn the conn
     * @param pst  the pst
     * @param rs   the rs
     */
    public static void close(Connection conn, PreparedStatement pst, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                LOGGER.error("Exception in C3p0Utils!\n" + e.getMessage());
            }
        }
        if (pst != null) {
            try {
                pst.close();
            } catch (SQLException e) {
                LOGGER.error("Exception in C3p0Utils!\n" + e.getMessage());
            }
        }

        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                LOGGER.error("Exception in C3p0Utils!\n" + e.getMessage());
            }
        }
    }
}