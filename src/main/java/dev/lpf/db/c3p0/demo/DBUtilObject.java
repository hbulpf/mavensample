package dev.lpf.db.c3p0.demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * DBUtil 实例
 *
 */
public class DBUtilObject {
    public Connection conn = null;
    public PreparedStatement preparedStatement = null;
    public ResultSet rs = null;
    public DBUtilObject() {
        super();
    }
}
