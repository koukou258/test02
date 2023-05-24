package com.javays.product.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author：黄宇航
 * @Version：1.0
 * @Date：2023/4/27-21:49
 * @Since：jdk1.8
 * @Description：
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class proJingzhi {
    Integer productId;
    String productname;
    String netWorthCompany;
    String netWorthBenchmark;
    String growthRate;
}
