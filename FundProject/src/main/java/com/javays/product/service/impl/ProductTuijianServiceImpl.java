package com.javays.product.service.impl;

import com.javays.base.bean.PageInfo;
import com.javays.base.bean.ReturnEntity;
import com.javays.product.bean.ProTJInfo;
import com.javays.product.dao.ProductTuijianDao;
import com.javays.product.dao.impl.ProductTuijianDaoImpl;
import com.javays.product.service.ProductTuijianService;

import java.util.List;

/**
 * @Author：黄宇航
 * @Version：1.0
 * @Date：2023/4/28-11:10
 * @Since：jdk1.8
 * @Description：
 */
public class ProductTuijianServiceImpl implements ProductTuijianService {
    ProductTuijianDao productTuijianDao = new ProductTuijianDaoImpl();
    @Override
    public ReturnEntity<PageInfo> showProductTJByPage(String currentPage, String pageSize, ProTJInfo proTJInfo) {
        Integer count = productTuijianDao.count(proTJInfo);
        PageInfo pageInfo = new PageInfo(pageSize, currentPage, count);
        List<ProTJInfo> list = productTuijianDao.selectproductTuijianByPage(pageInfo, proTJInfo);
        pageInfo.setDataList(list);

        ReturnEntity returnEntity = new ReturnEntity();
        returnEntity.setDataObj(pageInfo);
        return returnEntity;
    }

    @Override
    public ReturnEntity saveProductBase(ProTJInfo proTJInfo) {
        Boolean flag = productTuijianDao.insertProductTJ(proTJInfo);

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
    public ReturnEntity updateProductTJ(ProTJInfo proTJInfo) {
        Boolean flag = productTuijianDao.updataProductTJ(proTJInfo);

        ReturnEntity returnEntity = new ReturnEntity();
        if (flag) {
            returnEntity.setMessage("修改成功");
            returnEntity.setCode(200);
        } else {
            returnEntity.setMessage("修改失败");
        }
        return returnEntity;
    }
}
