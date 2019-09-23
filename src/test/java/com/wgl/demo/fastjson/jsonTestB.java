package com.wgl.demo.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

/**
 * Description:
 *
 * @author: wangguiliang
 * @date:2019-08-29
 */
public class jsonTestB {

    private String jsonString = "{\"date\":\"1567054519301\",\"userName\":\"aa\",\"ageA\":28}";



    @Test
    public void test1() {
        JSONObject jsonObject = JSON.parseObject(jsonString);
    }

}
