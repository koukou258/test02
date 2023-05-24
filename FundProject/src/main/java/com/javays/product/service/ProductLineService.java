package com.javays.product.service;

import com.javays.base.bean.ReturnEntity;
import com.javays.product.bean.ProductLineInfo;

public interface ProductLineService {

    /**
     * 分页查询
     *
     * @param currentPage
     * @param pageSize
     * @param productLineInfo
     * @return
     */
    ReturnEntity showProductLineByPage(String currentPage, String pageSize, ProductLineInfo productLineInfo);

    /**
     * 添加
     *
     * @param productLineInfo
     * @return
     */
    ReturnEntity saveProductLine(ProductLineInfo productLineInfo);

    /**
     * 修改
     *
     * @param productLineInfo
     * @return
     */
    ReturnEntity updateProductLine(ProductLineInfo productLineInfo);

}
