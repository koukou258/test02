package com.javays.product.dao;

import com.javays.product.bean.proJingzhi;

/**
 * @Author：黄宇航
 * @Version：1.0
 * @Date：2023/4/27-21:55
 * @Since：jdk1.8
 * @Description：
 */
public interface proJingzhiDao {

    proJingzhi selectNetWorthDaoByProductId(Integer productId);

    Boolean insertNetWorth(proJingzhi proJingzhi);

    Boolean updateNetWorth(proJingzhi proJingzhi);
}
