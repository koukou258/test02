package com.javays.product.service.impl;

import com.javays.base.bean.PageInfo;
import com.javays.base.bean.ReturnEntity;
import com.javays.product.bean.proBaseInfo;
import com.javays.product.dao.impl.productBaseDaoImpl;
import com.javays.product.dao.productBaseDao;
import com.javays.product.service.productBaseService;

import java.util.List;

/**
 * @Author：黄宇航
 * @Version：1.0
 * @Date：2023/4/26-15:44
 * @Since：jdk1.8
 * @Description：
 */
public class productBaseServiceImpl implements productBaseService {
    productBaseDao productBaseDao = new productBaseDaoImpl();

    @Override
    public ReturnEntity<PageInfo> showProductBaseByPage(String currentPage, String pageSize, proBaseInfo proBaseInfo) {
        Integer count = productBaseDao.count(proBaseInfo);
        PageInfo pageInfo = new PageInfo(pageSize, currentPage, count);
        List<proBaseInfo> list = productBaseDao.selectProductBaseByPage(pageInfo, proBaseInfo);
        pageInfo.setDataList(list);

        ReturnEntity returnEntity = new ReturnEntity();
        returnEntity.setDataObj(pageInfo);
        return returnEntity;


    }

    @Override
    public ReturnEntity saveProductBase(proBaseInfo proBaseInfo) {
        // 添加产品系列
        Boolean flag = productBaseDao.insertProductBase(proBaseInfo);

        ReturnEntity returnEntity = new ReturnEntity();
        if (flag) {
            returnEntity.setMessage("添加成功");
            returnEntity.setCode(200);
        } else {
            returnEntity.setMessage("添加失败");
        }
        return returnEntity;
    }

    @Override
    public ReturnEntity updateProductBase(proBaseInfo proBaseInfo) {
        Boolean flag = productBaseDao.updateProductBase(proBaseInfo);

        ReturnEntity returnEntity = new ReturnEntity();
        if (flag) {
            returnEntity.setMessage("修改成功");
        } else {
            returnEntity.setMessage("修改失败");
        }
        return returnEntity;
    }


}
