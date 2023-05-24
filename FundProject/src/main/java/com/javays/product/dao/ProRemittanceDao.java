package com.javays.product.dao;

import com.javays.product.bean.ProRemittanceInfo;

/**
 * @Author：黄宇航
 * @Version：1.0
 * @Date：2023/4/25-19:46
 * @Since：jdk1.8
 * @Description：
 */
public interface ProRemittanceDao {
    /**
     * 汇款信息回显数据
     * @param ProductId
     * @return
     */
    ProRemittanceInfo selectProRemittanceInfoByProductId(Integer ProductId);

    /**
     * 汇款信息添加
     */
    Boolean insertRemittance(ProRemittanceInfo remittanceInfo);

    /**
     * 修改
     * @param remittanceInfo
     * @return
     */
    Boolean updateRemittance(ProRemittanceInfo remittanceInfo);

}
