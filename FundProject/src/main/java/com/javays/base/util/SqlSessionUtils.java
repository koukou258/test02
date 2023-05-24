package com.javays.base.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Author：YanShuai
 * @Version：1.0
 * @Description：
 **/
public class SqlSessionUtils {
    private static final SqlSessionFactory sqlSessionFactory;
    private static final ThreadLocal<SqlSession> local = new ThreadLocal<>();
    private SqlSessionUtils() {
    }

    static {
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        InputStream resourceAsStream;
        try {
            resourceAsStream = Resources.getResourceAsStream("mybatis-config.xml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        sqlSessionFactory = sqlSessionFactoryBuilder.build(resourceAsStream);
    }

    /**
     * 获取SqlSession对象 并与当前线程绑定
     * @return
     */

    public static SqlSession openSession() {
        SqlSession sqlSession = local.get();
        return sqlSession == null ? sqlSessionFactory.openSession() : sqlSession;
    }

    /**
     * 关闭连接 移除和线程的绑定
     * @param sqlSession
     */
    public static void close(SqlSession sqlSession){
        if (sqlSession != null){
            sqlSession.close();
        }
        local.remove();
    }
}
