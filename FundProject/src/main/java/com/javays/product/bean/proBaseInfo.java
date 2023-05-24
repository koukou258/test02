package com.javays.product.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author：黄宇航
 * @Version：1.0
 * @Date：2023/4/26-14:57
 * @Since：jdk1.8
 * @Description：
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class proBaseInfo {
    private Integer productId;
    private Integer productLineId;
    private String productLineChineseName;
    //    -- 产品系列
    private String manageorgan;
    //    -- 管理机构
    private String productname;
    //    -- 产品名称
    private String productType;
    //    -- 二级分类
    private String yearProfit;
    //    -- 年化收益率
    private String currencyType;
    //    -- 货币类型
    private String opentime;
    //            -- 开发时间
    private String fundManage;
    //    -- 基金管理费率
    private String offerToBuy;
    //    -- 认购费率
    private String startingmoney;
    //    -- 起投金额
    private String collectType;
    //    -- 认购费收取方式
    private String collectCycle;
    //    -- 赎回周期
    private String collectStartMoney;
    //    -- 赎回起始金额
    private String collectMoney;
    //    -- 赎回费
    private String lockPeriod;
    //  -- 锁定期
    private String adminid;
    //    -- 审核人
    private String examinemassage;
    private String examinestate;
    private String createtime;
    private String edittime;
    private String subscribe;
    

}
