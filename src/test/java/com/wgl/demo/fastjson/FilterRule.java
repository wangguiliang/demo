package com.wgl.demo.fastjson;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description:
 *
 * @author: wangguiliang
 * @date:2019-09-16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilterRule {

    /**
     * 字段Id
     */
    private int id;

    /**
     * 表对象名
     */
    private String name;

    /**
     * 字段类型
     */
    private String type;

    /**
     * 字段名
     */
    private String field;

    /**
     * 操作符
     */
    private String operatSign;

    /**
     * 值
     */
    private String value;

    /**
     * 操作
     */
    private String opt;
}
