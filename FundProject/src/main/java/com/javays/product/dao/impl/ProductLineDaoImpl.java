package com.javays.product.dao.impl;

import com.javays.base.bean.PageInfo;
import com.javays.base.util.JDBCUtils;
import com.javays.product.bean.ProductLineInfo;
import com.javays.product.bean.vo.ProductLineRemittance;
import com.javays.product.dao.ProductLineDao;

import java.util.List;

public class ProductLineDaoImpl implements ProductLineDao {
    @Override
    public List<ProductLineRemittance> selectProductLineByPage(PageInfo pageInfo, ProductLineInfo productLineInfo) {
        StringBuffer sql = new StringBuffer("select pl.*,ri.management from  pro_product_line pl " +
                " left join pro_Remittance_info ri " +
                " on pl.productline_id = ri.product_line_id where 1=1 ");
        if (productLineInfo != null) {
            if (productLineInfo.getProductlineChineseName() != null && !"".equals(productLineInfo.getProductlineChineseName())) {
                sql.append(" and pl.productline_chinese_name like '%" + productLineInfo.getProductlineChineseName() + "%' ");
            }
            if (productLineInfo.getProductlineEnglishName() != null && !"".equals(productLineInfo.getProductlineEnglishName())) {
                sql.append(" and pl.productline_english_name like '%" + productLineInfo.getProductlineEnglishName() + "%' ");
            }
        }
        sql.append(" limit ?,? ");
        return JDBCUtils.query(sql.toString(), ProductLineRemittance.class, pageInfo.getStartIndex(), pageInfo.getPageSize());
    }

    @Override
    public Integer count(ProductLineInfo productLineInfo) {
        StringBuffer sql = new StringBuffer("select count(1) from  pro_product_line pl " +
                " left join pro_Remittance_info ri " +
                " on pl.productline_id = ri.product_line_id where 1=1 ");
        if (productLineInfo != null) {
            if (productLineInfo.getProductlineChineseName() != null && !"".equals(productLineInfo.getProductlineChineseName())) {
                sql.append(" and pl.productline_chinese_name like '%" + productLineInfo.getProductlineChineseName() + "%' ");
            }
            if (productLineInfo.getProductlineEnglishName() != null && !"".equals(productLineInfo.getProductlineEnglishName())) {
                sql.append(" and pl.productline_english_name like '%" + productLineInfo.getProductlineEnglishName() + "%' ");
            }
        }
        return JDBCUtils.count(sql.toString());
    }

    @Override
    public Boolean insertProductLine(ProductLineInfo productLineInfo) {
        String sql = "insert into pro_product_line values(null,?,?,?,?)";
        // 如果添加的表没有主键、数据库是 mysql8.0 不要用insert方法实现添加；添加数据调用 update
        return JDBCUtils.insert(sql, productLineInfo.getProductlineChannel(), productLineInfo.getProductlineType(), productLineInfo.getProductlineChineseName(), productLineInfo.getProductlineEnglishName()) > 0;
    }

    @Override
    public Boolean updateProductLine(ProductLineInfo productLineInfo) {
        String sql = "update pro_product_line set productline_channel=?,productline_type=?," +
                "productline_chinese_name=?,productline_english_name=? where productline_id = ?";
        return JDBCUtils.update(sql, productLineInfo.getProductlineChannel(), productLineInfo.getProductlineType(), productLineInfo.getProductlineChineseName(),
                productLineInfo.getProductlineEnglishName(), productLineInfo.getProductlineId()) > 0;
    }
}
