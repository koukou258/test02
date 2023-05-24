package com.javays.product.dao;

import com.javays.base.bean.PageInfo;
import com.javays.product.bean.ProductLineInfo;
import com.javays.product.bean.vo.ProductLineRemittance;

import java.util.List;

public interface ProductLineDao {

    /**
     * 分页条件查询
     *
     * @param pageInfo        分页对象
     * @param productLineInfo 条件查询的对象
     * @return
     */
    List<ProductLineRemittance> selectProductLineByPage(PageInfo pageInfo, ProductLineInfo productLineInfo);

    /**
     * 统计表中的总数据
     *
     * @param productLineInfo 条件查询的对象
     * @return
     */
    Integer count(ProductLineInfo productLineInfo);

    /**
     * 添加产品系列
     *
     * @param productLineInfo
     * @return
     */
    Boolean insertProductLine(ProductLineInfo productLineInfo);

    /**
     * 修改产品系列
     *
     * @param productLineInfo
     * @return
     */
    Boolean updateProductLine(ProductLineInfo productLineInfo);

}
