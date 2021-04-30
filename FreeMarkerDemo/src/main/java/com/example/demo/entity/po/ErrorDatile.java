package com.example.demo.entity.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: HanXu
 * on 2021/4/30
 * Class description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDatile {
    private String domain;
    private String appid;
    private String errorCode;
    private String errorInfo;
    private String errorCount;
}
