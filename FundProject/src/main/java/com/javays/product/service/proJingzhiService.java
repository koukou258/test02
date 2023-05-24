package com.javays.product.service;

import com.javays.base.bean.ReturnEntity;
import com.javays.product.bean.proJingzhi;

/**
 * @Author：黄宇航
 * @Version：1.0
 * @Date：2023/4/27-21:53
 * @Since：jdk1.8
 * @Description：
 */
public interface proJingzhiService {
    ReturnEntity<proJingzhi> selectNetWorthByProductId(Integer productId);

    ReturnEntity saveNetWorth(proJingzhi proJingzhi);

    ReturnEntity updateNetWorth(proJingzhi proJingzhi);
}
