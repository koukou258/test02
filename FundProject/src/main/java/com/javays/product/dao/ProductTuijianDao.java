package com.javays.product.dao;

import com.javays.base.bean.PageInfo;
import com.javays.product.bean.ProTJInfo;

import java.util.List;

/**
 * @Author：黄宇航
 * @Version：1.0
 * @Date：2023/4/28-11:11
 * @Since：jdk1.8
 * @Description：
 */
public interface ProductTuijianDao {
    //得到推荐产品条数
    Integer count(ProTJInfo proTJInfo);

    //分页查询
    List<ProTJInfo> selectproductTuijianByPage(PageInfo pageInfo, ProTJInfo proTJInfo);
//新增
    Boolean insertProductTJ(ProTJInfo proTJInfo);

    //修改
    Boolean updataProductTJ(ProTJInfo proTJInfo);
}
