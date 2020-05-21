
package dev.db.c3p0.demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 数据库操作工具
 */
public class DBUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(DBUtils.class.getName());

    private static void closeSource(Connection conn, PreparedStatement st, ResultSet rs) {
        C3p0Utils.close(conn, st, rs);
    }

    /**
     * Realse source.
     *
     * @param vo the vo
     */
    public static void closeSource(DBUtilObject vo) {
        if (vo != null) {
            closeSource(vo.conn, vo.preparedStatement, vo.rs);
        }
    }

    /**
     * 执行查询
     *
     * 查询操作完成后，因为还需提取结果集中信息，所以仍保持连接，
     * 在结果集使用完后才通过DBUtils.closeSource()手动释放连接
     * @param vo the vo
     * @throws Exception the exception
     */
    public static void executeQuery(DBUtilObject vo) throws Exception {
        try {
            vo.rs = vo.preparedStatement.executeQuery();
        } catch (SQLException e) {
            closeSource(vo);
            throw new Exception("err.user.dao.jdbc: " + e.getMessage());
        }
    }

    /**
     * Execute update.
     *
     * 而update操作完成后就可以直接释放连接了，所以在方法末尾直接调用了realseSourse()
     * @param vo the vo
     */
    //
    public static void executeUpdate(DBUtilObject vo) throws Exception {
        Connection conn = vo.conn;
        PreparedStatement st = vo.preparedStatement;
        try {
            st.executeUpdate();
        } catch (SQLException e) {
            closeSource(conn, st, null);
            LOGGER.error("SQL语法有误: {}", e);
            throw new Exception("err.user.dao.jdbc: " + e.getMessage());
        }
        closeSource(conn, st, null);

    }
}
