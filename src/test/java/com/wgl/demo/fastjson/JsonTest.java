package com.wgl.demo.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

import java.util.Date;
import java.util.stream.Collectors;

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

    @Test
    public void test3() {
        String filterRule = "[" +
                "{" +
                "\"id\": 1," +
                "\"name\": \"aa\"," +
                "\"field\": \"aa\"," +
                "\"type\": \"string\"," +
                "\"operatSign\": \"+\"," +
                "\"value\": \"asf\"," +
                "\"opt\": \"a\"" +
                "}," +
                "{" +
                "\"id\": 1," +
                "\"name\": \"bb\"," +
                "\"field\": \"bb\"," +
                "\"type\": \"number\"," +
                "\"operatSign\": \"-\"," +
                "\"value\": \"421.421\"," +
                "\"opt\": \"b\"" +
                "}" +
                "]";

        System.out.println(filterRule);

        JSONArray jsonArray = JSONObject.parseArray(filterRule);
        System.out.println("【jsonArray】:"+jsonArray.toString());

        jsonArray.stream().forEach(ja -> {
            ((JSONObject)ja).put("data","2");
        });
        System.out.println(jsonArray);

        // 明白了，JSONArray里面的Object是JSONObject，可以直接转，当做JSONObject用
        for (Object o : jsonArray) {
            JSONObject jsonObject = (JSONObject) o;
            jsonObject.put("time", "1");
            System.out.println("2"+jsonObject);
            System.out.println(o);
        }
        System.out.println(jsonArray.toString());


        Object o = jsonArray.get(0);
        System.out.println(o.toString());
        FilterRule filterRule1 = JSON.parseObject(o.toString(), FilterRule.class);
        System.out.println(filterRule1.toString());
//        for (Object o : jsonArray) {
//            System.out.println(o.toString());
//            FilterRule filterRule1 = JSON.parseObject(o.toString(), FilterRule.class);
//            System.out.println(filterRule1.toString());
//        }
        System.out.println(jsonArray.toString());

//        List<FilterRule> filterRules = JSONObject.parseArray(filterRule, FilterRule.class);
//        System.out.println(filterRules.size());
    }

    @Test
    public void test4() {

        String json = "{\"label\":\"aa\",\"value\":\"1\"}";

        JSONObject intelResult = JSON.parseObject(json);
        String label = intelResult.getString("label");
        String value = intelResult.getString("value");

        System.out.println(label+","+value);

        intelResult.put("dataTime", new Date().getTime() + "");

        System.out.println(intelResult.toJSONString());
        System.out.println(intelResult.toString());


    }

//    @Data
//    @AllArgsConstructor
//    @NoArgsConstructor
//    class FilterRule {
//
//        /**
//         * 字段Id
//         */
//        private int id;
//
//        /**
//         * 表对象名
//         */
//        private String name;
//
//        /**
//         * 字段类型
//         */
//        private String type;
//
//        /**
//         * 字段名
//         */
//        private String field;
//
//        /**
//         * 操作符
//         */
//        private String operatSign;
//
//        /**
//         * 值
//         */
//        private String value;
//
//        /**
//         * 操作
//         */
//        private String opt;
//
//    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    class A{
        private String a;
        private String b;
    }



}
