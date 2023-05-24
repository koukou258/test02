package com.javays.product.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author：黄宇航
 * @Version：1.0
 * @Date：2023/4/23-19:30
 * @Since：jdk1.8
 * @Description：
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProRemittanceInfo {
    private Integer remittanceId;
    private Integer productLineId;
    private String bankName;
    private String bankSWIFT;
    private String bankCode;
    private String bankCnaps;
    private String collectionBankArea;
    private String collectionBankCity;
    private String collectionBankName;
    private String collectionBankAccount;
    private String accountAddress;
    private String management;
}
