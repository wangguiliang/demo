package com.wgl.demo.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

/**
 * Description:
 *
 * @author: wangguiliang
 * @date:2019-07-23
 */
public class JsonTest {

    @Test
    public void test1() {
        String str = "[\"aaa\",\"bbb\"]";
        JSONArray jsonArray = JSON.parseArray(str);
        System.out.println(jsonArray);
        System.out.println(jsonArray.get(0));
        System.out.println(jsonArray.get(1));
    }

    @Test
    public void test2() {
        A a = new A("1", "2");
        System.out.println(a);
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    class A{
        private String a;
        private String b;
    }

}
