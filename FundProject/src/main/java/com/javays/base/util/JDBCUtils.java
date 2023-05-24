package com.javays.base.util;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.commons.dbutils.*;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @Author：liulei
 * @Version：1.0
 * @Date：2023/3/13-15:09
 * @Since：jdk1.8
 * @Description：
 */
public class JDBCUtils {

    private static DruidDataSource druidDataSource;

    static {
        try {
            InputStream resourceAsStream = JDBCUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            Properties properties = new Properties();
            properties.load(resourceAsStream);
            druidDataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Connection connection = getConnection();
        System.out.println(connection);
    }


    /**
     * 获取数据库连接
     *
     * @return 连接对象
     */
    public static Connection getConnection() {
        try {
//            //加载驱动（1.8可以自动加载）
//            Class.forName(JDBCConstants.DRIVER);
//            //通过驱动管理器获取数据库的连接对象
//            return DriverManager.getConnection(JDBCConstants.URl, JDBCConstants.USER,
//                    JDBCConstants.PASSWORD);
            return druidDataSource.getConnection();
        }  catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 判断数据库是否连接成功？
     *
     * @param connection 连接对象
     * @return 是否成功？
     */
    public static boolean isConnection(Connection connection) {
        try {
            if (connection != null && !connection.isClosed()) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    /**
     * 执行增删改
     *
     * @param sql    sql语句
     * @param params 可变参数
     * @return 受影响的行数
     */
    public static int update(String sql, Object... params) {
        int i = 0;
        //获取连接对象
        Connection connection = getConnection();
        //先判断连接是否成功？
        if (isConnection(connection)) {
            try {
                i = new QueryRunner().update(connection, sql, params);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                DbUtils.closeQuietly(connection);
            }
        }
        return i;
    }

    /**
     * 执行增删改（事务）
     *
     * @param sql        SQL语句
     * @param connection 连接对象
     * @param params     可变参数
     * @return 受影响的行数
     */
    public static int update(String sql, Connection connection, Object... params) {
        int i = 0;
        //先判断连接是否成功？
        if (isConnection(connection)) {
            try {
                i = new QueryRunner().update(connection, sql, params);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return i;
    }

    /**
     * 新增，并返回自增ID
     *
     * @param sql    SQL语句
     * @param params 可变参数
     * @return 自增ID
     */
    public static int insert(String sql, Object... params) {
        int id = 0;
        //获取连接对象
        Connection connection = getConnection();
        //先判断连接是否成功？
        if (isConnection(connection)) {
            try {
                id = new QueryRunner().insert(connection, sql, new ScalarHandler<Long>(), params).intValue();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                DbUtils.closeQuietly(connection);
            }
        }
        return id;
    }

    /**
     * 添加（事务）
     *
     * @param sql
     * @param connection
     * @param params
     * @return
     */
    public static int insert(String sql, Connection connection, Object... params) {
        int id = 0;
        //先判断连接是否成功？
        if (isConnection(connection)) {
            try {
                id = new QueryRunner().insert(connection, sql, new ScalarHandler<Long>(), params).intValue();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return id;
    }

    /**
     * 获取单个对象
     *
     * @param sql    sql语句
     * @param clazz  实体类对应的类型
     * @param params 可变参数
     * @param <T>    使用泛型传递需要实体类的类型
     * @return 泛型对象
     */
    public static <T> T get(String sql, Class<T> clazz, Object... params) {
        T t = null;
        //获取连接对象
        Connection connection = getConnection();
        //先判断
        if (isConnection(connection)) {
            try {
                //解决下划线的问题
                RowProcessor processor = new BasicRowProcessor(new GenerousBeanProcessor());
                t = new QueryRunner().query(connection, sql, new BeanHandler<>(clazz, processor), params);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                DbUtils.closeQuietly(connection);
            }
        }
        return t;
    }

    /**
     * 查询多条数据
     *
     * @param sql    sql语句
     * @param clazz  实体类对应的类型
     * @param params 可变参数
     * @param <T>    使用泛型传递需要实体类的类型
     * @return 集合
     */
    public static <T> List<T> query(String sql, Class<T> clazz, Object... params) {
        List<T> list = new ArrayList<>(10);
        //获取连接对象
        Connection connection = getConnection();
        //先判断
        if (isConnection(connection)) {
            try {
                //解决下划线的问题
                RowProcessor processor = new BasicRowProcessor(new GenerousBeanProcessor());
                list = new QueryRunner().query(connection, sql, new BeanListHandler<>(clazz, processor), params);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                DbUtils.closeQuietly(connection);
            }
        }
        return list;
    }

    /**
     * 统计条数
     *
     * @param sql    sql语句
     * @param params 可变参数
     * @return 统计条数
     */
    public static int count(String sql, Object... params) {
        int count = 0;
        Connection connection = getConnection();
        if (isConnection(connection)) {
            try {
                count = new QueryRunner().query(connection, sql, new ScalarHandler<Long>(), params).intValue();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                DbUtils.closeQuietly(connection);
            }
        }
        return count;
    }
}
