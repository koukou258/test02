package com.javays.product.dao.impl;

import com.javays.base.util.JDBCUtils;
import com.javays.product.bean.ProRemittanceInfo;
import com.javays.product.dao.ProRemittanceDao;

/**
 * @Author：黄宇航
 * @Version：1.0
 * @Date：2023/4/27-17:49
 * @Since：jdk1.8
 * @Description：
 */
public class ProRemittanceDaoImpl implements ProRemittanceDao {

    @Override
    public ProRemittanceInfo selectProRemittanceInfoByProductId(Integer ProductId) {
        String sql = "select  * from pro_remittance_info where product_line_id = ?";
        return JDBCUtils.get(sql, ProRemittanceInfo.class, ProductId);

    }

    @Override
    public Boolean insertRemittance(ProRemittanceInfo remittanceInfo) {
        String sql = "insert into pro_remittance_info values(null,?,?,?,?,?,?,?,?,?,?,?)";
        return JDBCUtils.insert(sql, remittanceInfo.getProductLineId(), remittanceInfo.getBankName(), remittanceInfo.getBankSWIFT(),
                remittanceInfo.getBankCode(), remittanceInfo.getBankCnaps(), remittanceInfo.getCollectionBankArea(),
                remittanceInfo.getCollectionBankCity(), remittanceInfo.getCollectionBankName(), remittanceInfo.getCollectionBankAccount(),
                remittanceInfo.getAccountAddress(), remittanceInfo.getManagement()) > 0;
    }


    @Override
    public Boolean updateRemittance(ProRemittanceInfo remittanceInfo) {
        String sql = "update pro_remittance_info set bank_name = ?, bank_SWIFT = ?, bank_code = ?," +
                " bank_cnaps = ?, collection_bank_area = ?, collection_bank_city = ?," +
                " collection_bank_name = ?, collection_bank_account = ?, account_address = ?, management = ?" +
                " where remittance_id = ?";
        return JDBCUtils.update(sql, remittanceInfo.getBankName(), remittanceInfo.getBankSWIFT(),remittanceInfo.getBankCode(), remittanceInfo.getBankCnaps(),
                remittanceInfo.getCollectionBankArea(), remittanceInfo.getCollectionBankCity(), remittanceInfo.getCollectionBankName(),
                remittanceInfo.getCollectionBankAccount(), remittanceInfo.getAccountAddress(), remittanceInfo.getManagement(), remittanceInfo.getProductLineId()) > 0;
    }
}
