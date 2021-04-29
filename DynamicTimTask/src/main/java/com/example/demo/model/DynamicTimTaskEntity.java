package com.example.demo.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 * 
 */
@Data
@TableName("test_dynamic_tim_task")
public class DynamicTimTaskEntity implements Serializable {
    /**
     * 主键ID
     */
    private Long id;

    /**
     * 业务名称
     */
    private String name;

    /**
     * 业务code，唯一
     */
    private String code;

    /**
     * 业务执行周期
     */
    private String cron;

    private Date inserttime;

    private Date updatetime;

    /**
     * 逻辑删除，1 正常，0 删除
     */
    private Boolean isactive;

    private static final long serialVersionUID = 1L;
}