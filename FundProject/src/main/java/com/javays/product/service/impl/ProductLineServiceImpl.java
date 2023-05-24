package com.javays.product.service.impl;

import com.javays.base.bean.PageInfo;
import com.javays.base.bean.ReturnEntity;
import com.javays.product.bean.ProductLineInfo;
import com.javays.product.bean.vo.ProductLineRemittance;
import com.javays.product.dao.ProductLineDao;
import com.javays.product.dao.impl.ProductLineDaoImpl;
import com.javays.product.service.ProductLineService;

import java.util.List;

public class ProductLineServiceImpl implements ProductLineService {
  
    ProductLineDao productLineDao = new ProductLineDaoImpl();

    @Override
    public ReturnEntity showProductLineByPage(String currentPage, String pageSize, ProductLineInfo productLineInfo) {
        Integer count = productLineDao.count(productLineInfo);
        PageInfo pageInfo = new PageInfo(pageSize, currentPage, count);
        List<ProductLineRemittance> list = productLineDao.selectProductLineByPage(pageInfo, productLineInfo);
        pageInfo.setDataList(list);


        ReturnEntity returnEntity = new ReturnEntity();
        returnEntity.setDataObj(pageInfo);
        return returnEntity;

    }


    @Override
    public ReturnEntity saveProductLine(ProductLineInfo productLineInfo) {
        // 添加产品系列
        Boolean flag = productLineDao.insertProductLine(productLineInfo);

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
    public ReturnEntity updateProductLine(ProductLineInfo productLineInfo) {
        Boolean flag = productLineDao.updateProductLine(productLineInfo);

        ReturnEntity returnEntity = new ReturnEntity();
        if (flag) {
            returnEntity.setMessage("修改成功");
        } else {
            returnEntity.setMessage("修改失败");
        }
        return returnEntity;
    }
}
