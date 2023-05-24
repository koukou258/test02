package com.javays.product.service.impl;

import com.javays.base.bean.ReturnEntity;
import com.javays.product.bean.ProRemittanceInfo;
import com.javays.product.dao.ProRemittanceDao;
import com.javays.product.dao.impl.ProRemittanceDaoImpl;
import com.javays.product.service.ProRemittanceService;

/**
 * @Author：黄宇航
 * @Version：1.0
 * @Date：2023/4/27-18:00
 * @Since：jdk1.8
 * @Description：
 */
public class ProRemittanceServiceImpl implements ProRemittanceService {

    ProRemittanceDao proRemittanceDao = new ProRemittanceDaoImpl();

    @Override
    public ReturnEntity<ProRemittanceInfo> selectProRemittanceInfoByProductlineId(Integer productlineId) {
        ProRemittanceInfo proRemittanceInfo = proRemittanceDao.selectProRemittanceInfoByProductId(productlineId);
        ReturnEntity returnEntity = new ReturnEntity();
        returnEntity.setDataObj(proRemittanceInfo);
        return returnEntity;
    }

    @Override
    public ReturnEntity saveRemittance(ProRemittanceInfo proRemittanceInfo) {
        Boolean aBoolean = proRemittanceDao.insertRemittance(proRemittanceInfo);
        String message = aBoolean ? "添加成功" : "添加失败";
        ReturnEntity returnEntity = new ReturnEntity();
        returnEntity.setMessage(message);

        return returnEntity;
    }

    @Override
    public ReturnEntity updateRemittance(ProRemittanceInfo proRemittanceInfo) {
        Boolean aBoolean = proRemittanceDao.updateRemittance(proRemittanceInfo);
        String message = aBoolean ? "修改成功" : "修改成功";
        ReturnEntity returnEntity = new ReturnEntity();
        returnEntity.setMessage(message);

        return returnEntity;
    }
}
