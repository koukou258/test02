package com.javays.product.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductLineInfo {

    private Integer productlineId;
    private Integer productlineChannel;
    private Integer productlineType;
    private String productlineChineseName;
    private String productlineEnglishName;

  
}
