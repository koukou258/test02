package com.javays.product.service;

import com.javays.base.bean.PageInfo;
import com.javays.base.bean.ReturnEntity;
import com.javays.product.bean.proBaseInfo;

/**
 * @Author：黄宇航
 * @Version：1.0
 * @Date：2023/4/26-15:40
 * @Since：jdk1.8
 * @Description：
 */
public interface productBaseService {

    ReturnEntity<PageInfo> showProductBaseByPage(String currentPage, String pageSize, proBaseInfo proBaseInfo);

    ReturnEntity saveProductBase(proBaseInfo proBaseInfo);

    ReturnEntity updateProductBase(proBaseInfo proBaseInfo);

}
