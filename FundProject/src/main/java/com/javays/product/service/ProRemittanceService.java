package com.javays.product.service;

import com.javays.base.bean.ReturnEntity;
import com.javays.product.bean.ProRemittanceInfo;

/**
 * @Author：黄宇航
 * @Version：1.0
 * @Date：2023/4/27-18:00
 * @Since：jdk1.8
 * @Description：
 */
public interface ProRemittanceService {

    ReturnEntity<ProRemittanceInfo> selectProRemittanceInfoByProductlineId(Integer productlineId);

    /**
     * 添加
     * @param proRemittanceInfo
     * @return
     */
    ReturnEntity saveRemittance(ProRemittanceInfo proRemittanceInfo );

    /**
     * 修改
     * @param proRemittanceInfo
     * @return
     */
    ReturnEntity updateRemittance(ProRemittanceInfo proRemittanceInfo );



}
