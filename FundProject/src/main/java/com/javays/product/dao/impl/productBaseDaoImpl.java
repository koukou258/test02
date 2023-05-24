package com.javays.product.dao.impl;

import com.javays.base.bean.PageInfo;
import com.javays.base.util.JDBCUtils;
import com.javays.product.bean.proBaseInfo;
import com.javays.product.dao.productBaseDao;

import java.util.List;

/**
 * @Author：黄宇航
 * @Version：1.0
 * @Date：2023/4/26-15:48
 * @Since：jdk1.8
 * @Description：
 */
public class productBaseDaoImpl implements productBaseDao {

    /**
     * 分页
     *
     * @param pageInfo
     * @param proBaseInfo
     * @return
     */
    @Override
    public List<proBaseInfo> selectProductBaseByPage(PageInfo pageInfo, proBaseInfo proBaseInfo) {
        StringBuffer sql = new StringBuffer("select pl.* , ri.productline_chinese_name  from  pro_base pl " +
                " left join pro_product_line ri " +
                " on pl.productline_id = ri.productline_id where 1=1 ");
        if (proBaseInfo != null) {
            if (proBaseInfo.getProductType() != null && !"".equals(proBaseInfo.getProductType())) {
                sql.append(" and pl.productType = " + proBaseInfo.getProductType());
            }
            if (proBaseInfo.getProductname() != null && !"".equals(proBaseInfo.getProductname())) {
                sql.append(" and pl.product_name like '%" + proBaseInfo.getProductname() + "%' ");
            }
            if (proBaseInfo.getExaminestate() != null && !"".equals(proBaseInfo.getExaminestate())) {
                sql.append(" and pl.examinestate = " + proBaseInfo.getExaminestate());
            }
        }
        sql.append(" limit ?,? ");
        return JDBCUtils.query(sql.toString(), proBaseInfo.class, pageInfo.getStartIndex(), pageInfo.getPageSize());
    }

    /**
     * 查数据条数
     *
     * @param proBaseInfo
     * @return
     */
    @Override
    public Integer count(proBaseInfo proBaseInfo) {
        StringBuffer sql = new StringBuffer(" select count(1) from  pro_base pl where 1=1");
        if (proBaseInfo != null) {
            if (proBaseInfo.getProductType() != null && !"".equals(proBaseInfo.getProductType())) {
                sql.append(" and pl.productType = " + proBaseInfo.getProductType());
            }
            if (proBaseInfo.getProductname() != null && !"".equals(proBaseInfo.getProductname())) {
                sql.append(" and pl.product_name like '%" + proBaseInfo.getProductname() + "%' ");
            }
            if (proBaseInfo.getExaminestate() != null && !"".equals(proBaseInfo.getExaminestate())) {
                sql.append(" and pl.examinestate = " + proBaseInfo.getExaminestate());
            }
        }

        return JDBCUtils.count(sql.toString());
    }

    @Override
    public Boolean insertProductBase(proBaseInfo proBaseInfo) {
        String sql = "insert into pro_base values(null,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        // 如果添加的表没有主键、数据库是 mysql8.0 不要用insert方法实现添加；添加数据调用 update
        return JDBCUtils.insert(sql, Integer.valueOf(proBaseInfo.getProductLineId()), proBaseInfo.getProductLineChineseName(),
                proBaseInfo.getManageorgan(), proBaseInfo.getProductname(),
                Integer.valueOf(proBaseInfo.getProductType()), proBaseInfo.getYearProfit(), Integer.valueOf(proBaseInfo.getCurrencyType()),
                proBaseInfo.getOpentime(), proBaseInfo.getFundManage(), proBaseInfo.getOfferToBuy(),
                proBaseInfo.getStartingmoney(), Integer.valueOf(proBaseInfo.getCollectType()), proBaseInfo.getCollectCycle(),
                proBaseInfo.getCollectStartMoney(), proBaseInfo.getCollectMoney(),
                proBaseInfo.getLockPeriod(), Integer.valueOf(proBaseInfo.getAdminid()), proBaseInfo.getExaminemassage(), Integer.valueOf(proBaseInfo.getExaminestate()),
                proBaseInfo.getCreatetime(), proBaseInfo.getEdittime(), Integer.valueOf(proBaseInfo.getSubscribe())) > 0;
    }

    @Override
    public Boolean updateProductBase(proBaseInfo proBaseInfo) {
        String sql = "update pro_base set productline_id=?,productLineChineseName=?,manageorgan=?,product_name=?," +
                "productType=?,yearProfit=?,currencytype=?,opentime=?,fundmanage=?,offertobuy=?,StartingMoney=?,collecttype=?," +
                "collectcycle=?, collectstartmoney = ?,collectmoney=?,lockperiod=?,adminid=?,examinemassage=?,examinestate=?,createtime=?,edittime=?,subscribe=? where product_id = ?";
        return JDBCUtils.update(sql, Integer.valueOf(proBaseInfo.getProductLineId()), proBaseInfo.getProductLineChineseName(),
                proBaseInfo.getManageorgan(), proBaseInfo.getProductname(),
                Integer.valueOf(proBaseInfo.getProductType()), proBaseInfo.getYearProfit(), Integer.valueOf(proBaseInfo.getCurrencyType()),
                proBaseInfo.getOpentime(), proBaseInfo.getFundManage(), proBaseInfo.getOfferToBuy(),
                proBaseInfo.getStartingmoney(), Integer.valueOf(proBaseInfo.getCollectType()), proBaseInfo.getCollectCycle(),
                proBaseInfo.getCollectStartMoney(), proBaseInfo.getCollectMoney(),
                proBaseInfo.getLockPeriod(), Integer.valueOf(proBaseInfo.getAdminid()), proBaseInfo.getExaminemassage(), Integer.valueOf(proBaseInfo.getExaminestate()),
                proBaseInfo.getCreatetime(), proBaseInfo.getEdittime(), Integer.valueOf(proBaseInfo.getSubscribe()), proBaseInfo.getProductId()) > 0;

    }




}

