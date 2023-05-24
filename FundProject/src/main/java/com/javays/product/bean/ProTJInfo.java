package com.javays.product.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author：黄宇航
 * @Version：1.0
 * @Date：2023/4/28-11:15
 * @Since：jdk1.8
 * @Description：
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProTJInfo {
    private Integer productid;
    private Integer tuijiankey;
    private String productname;
    private String recommendDegree;
    private String investmentAdviserFlag;
    private String startingFlag;
    private String onLineSubscribe;
    private String recommendMessage;
    private String recommendState;


}
