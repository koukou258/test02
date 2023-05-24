package com.javays.product.dao;

import com.javays.base.bean.PageInfo;
import com.javays.product.bean.proBaseInfo;

import java.util.List;

/**
 * @Author：黄宇航
 * @Version：1.0
 * @Date：2023/4/26-15:47
 * @Since：jdk1.8
 * @Description：
 */
public interface productBaseDao {
    List<proBaseInfo> selectProductBaseByPage(PageInfo pageInfo, proBaseInfo proBaseInfo);

    Integer count(proBaseInfo proBaseInfo);

    Boolean insertProductBase(proBaseInfo proBaseInfo);

    Boolean updateProductBase(proBaseInfo proBaseInfo);


}
