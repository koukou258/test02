package com.javays.product.service.impl;

import com.javays.base.bean.ReturnEntity;
import com.javays.product.bean.proJingzhi;
import com.javays.product.dao.impl.proJingzhiDaoImpl;
import com.javays.product.dao.proJingzhiDao;
import com.javays.product.service.proJingzhiService;

/**
 * @Author：黄宇航
 * @Version：1.0
 * @Date：2023/4/27-21:54
 * @Since：jdk1.8
 * @Description：
 */
public class proJingzhiServiceImpl implements proJingzhiService {
    proJingzhiDao proJingzhiDao =new proJingzhiDaoImpl();

    /**
     * 回显净值数据
     * @param productId
     * @return
     */
    @Override
    public ReturnEntity<proJingzhi> selectNetWorthByProductId(Integer productId) {
        proJingzhi proJingzhi = proJingzhiDao.selectNetWorthDaoByProductId(productId);
        ReturnEntity returnEntity = new ReturnEntity();
        returnEntity.setDataObj(proJingzhi);
        return returnEntity;
    }

    @Override
    public ReturnEntity saveNetWorth(proJingzhi proJingzhi) {
        Boolean aBoolean = proJingzhiDao.insertNetWorth(proJingzhi);
        String message = aBoolean ? "添加成功" : "添加失败";
        ReturnEntity returnEntity = new ReturnEntity();
        returnEntity.setMessage(message);

        return returnEntity;
    }

    @Override
    public ReturnEntity updateNetWorth(proJingzhi proJingzhi) {
        Boolean aBoolean = proJingzhiDao.updateNetWorth(proJingzhi);
        String message = aBoolean ? "修改成功" : "修改失败";
        ReturnEntity returnEntity = new ReturnEntity();
        returnEntity.setMessage(message);

        return returnEntity;
    }
}
