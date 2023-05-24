package com.javays.product.dao.impl;

import com.javays.base.bean.PageInfo;
import com.javays.base.util.JDBCUtils;
import com.javays.product.bean.ProTJInfo;
import com.javays.product.dao.ProductTuijianDao;

import java.util.List;

/**
 * @Author：黄宇航
 * @Version：1.0
 * @Date：2023/4/28-11:11
 * @Since：jdk1.8
 * @Description：
 */
public class ProductTuijianDaoImpl implements ProductTuijianDao {
    /**
     * 计算分页数据总条数
     *
     * @param proTJInfo
     * @return
     */
    @Override
    public Integer count(ProTJInfo proTJInfo) {
        StringBuffer sql = new StringBuffer(" select count(1) from  pro_tuijian pl where 1=1");
        if (proTJInfo != null) {
            if (proTJInfo.getProductname() != null && !"".equals(proTJInfo.getProductname())) {
                sql.append(" and pl.product_name like '%" + proTJInfo.getProductname() + "%' ");
            }
        }
        return JDBCUtils.count(sql.toString());
    }

    /**
     * 分页查询
     *
     * @param pageInfo
     * @param proTJInfo
     * @return
     */
    @Override
    public List<ProTJInfo> selectproductTuijianByPage(PageInfo pageInfo, ProTJInfo proTJInfo) {
        StringBuffer sql = new StringBuffer("select ri.*," +
                " pl.product_name  from  pro_base pl join pro_tuijian ri on pl.product_id = ri.product_id where 1=1 ");
        if (proTJInfo != null) {
            if (proTJInfo.getProductname() != null && !"".equals(proTJInfo.getProductname())) {
                sql.append(" and pl.product_name like '%" + proTJInfo.getProductname() + "%' ");
            }
        }
        sql.append("  ORDER BY ri.recommendState limit ?,?");
        return JDBCUtils.query(sql.toString(), ProTJInfo.class, pageInfo.getStartIndex(), pageInfo.getPageSize());
    }

    /**
     * 添加产品推荐
     *
     * @param proTJInfo
     * @return
     */
    @Override
    public Boolean insertProductTJ(ProTJInfo proTJInfo) {
        String sql = "insert into pro_tuijian values(?,?,?,?,?,?,?,?,null)";
        // 如果添加的表没有主键、数据库是 mysql8.0 不要用insert方法实现添加；添加数据调用 update
        return JDBCUtils.insert(sql, proTJInfo.getRecommendDegree(), proTJInfo.getInvestmentAdviserFlag(), proTJInfo.getStartingFlag(), proTJInfo.getOnLineSubscribe(), proTJInfo.getRecommendMessage(),
                proTJInfo.getRecommendState(), proTJInfo.getProductname(), proTJInfo.getProductid()) > 0;
    }

    @Override
    public Boolean updataProductTJ(ProTJInfo proTJInfo) {
        String sql = "update pro_tuijian set recommenddegree = ?,investmentadviserflag = ?,startingFlag = ?,onlinesubscribe = ?," +
                "recommendmessage = ?,recommendState = ?,product_name = ?,product_id = ? where tuijian_key = ?";
        return JDBCUtils.update(sql, proTJInfo.getRecommendDegree(), proTJInfo.getInvestmentAdviserFlag(),
                proTJInfo.getStartingFlag(), proTJInfo.getOnLineSubscribe(), proTJInfo.getRecommendMessage(), proTJInfo.getProductid(),proTJInfo.getProductname(), proTJInfo.getProductid(), proTJInfo.getTuijiankey()
        ) > 0;
    }
}
