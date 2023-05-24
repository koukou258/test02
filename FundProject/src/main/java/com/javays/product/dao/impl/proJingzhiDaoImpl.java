package com.javays.product.dao.impl;

import com.javays.base.util.JDBCUtils;
import com.javays.product.bean.proJingzhi;
import com.javays.product.dao.proJingzhiDao;

/**
 * @Author：黄宇航
 * @Version：1.0
 * @Date：2023/4/27-21:55
 * @Since：jdk1.8
 * @Description：
 */
public class proJingzhiDaoImpl implements proJingzhiDao {

    /**
     * 回显净值数据
     * @param productId
     * @return
     */
    @Override
    public proJingzhi selectNetWorthDaoByProductId(Integer productId) {
        String sql = "select  jz.netWorthCompany,jz.netWorthBenchmark,jz.growthRate,bs.product_name from pro_base bs left join pro_jingzhi jz on bs.product_name =jz.product_name where bs.product_id = ?";
        return JDBCUtils.get(sql, proJingzhi.class, productId);
    }

    @Override
    public Boolean insertNetWorth(proJingzhi proJingzhi) {
        String sql = "insert into pro_jingzhi values(?,?,?,?,?)";
        return JDBCUtils.update(sql,proJingzhi.getProductId(), proJingzhi.getProductname(), proJingzhi.getNetWorthCompany(),
                proJingzhi.getNetWorthBenchmark(), proJingzhi.getGrowthRate()) > 0;
    }

    @Override
    public Boolean updateNetWorth(proJingzhi proJingzhi) {
        String sql = "update pro_jingzhi set netWorthCompany =?,netWorthBenchmark=?,growthRate=?";
        return JDBCUtils.update(sql,proJingzhi.getNetWorthCompany(),
                proJingzhi.getNetWorthBenchmark(), proJingzhi.getGrowthRate()) > 0;
    }
}
