package com.javays.base.bean;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ReturnEntity<T> {

    private Integer code;// 请求状态
    private String message;// 提示
    private T dataObj;// 查询到实体类对象
    private List<T> dataList;// 查询到集合对象

    // 重载构造方法
    public ReturnEntity(Integer code, String message, T dataObj, List<T> dataList) {
        this.code = code;
        this.message = message;
        this.dataObj = dataObj;
        this.dataList = dataList;
    }
    public ReturnEntity(Integer code, String message, List<T> dataList) {
        this.code = 200;
        this.message = message;
        this.dataList = dataList;
    }
    public ReturnEntity(Integer code, String message, T dataObj) {
        this.code = 200;
        this.message = message;
        this.dataObj = dataObj;
    }
    public ReturnEntity(Integer code, String message) {
        this.code = 200;
        this.message = message;
    }
}
