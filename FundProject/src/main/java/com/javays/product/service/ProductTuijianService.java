package com.javays.product.service;

import com.javays.base.bean.PageInfo;
import com.javays.base.bean.ReturnEntity;
import com.javays.product.bean.ProTJInfo;

/**
 * @Author：黄宇航
 * @Version：1.0
 * @Date：2023/4/28-11:10
 * @Since：jdk1.8
 * @Description：
 */
public interface ProductTuijianService {
    ReturnEntity<PageInfo> showProductTJByPage(String currentPage, String pageSize, ProTJInfo proTJInfo);

    ReturnEntity saveProductBase(ProTJInfo proTJInfo);

    ReturnEntity updateProductTJ(ProTJInfo proTJInfo);
}
