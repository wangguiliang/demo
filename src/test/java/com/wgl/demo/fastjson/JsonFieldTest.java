package com.wgl.demo.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

/**
 * Description:
 *
 * @author: wangguiliang
 * @date:2019-08-29
 */
public class JsonFieldTest {


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class User {
        /**
         * 名称
         * name定义名称
         */
        @JSONField(ordinal = 0,name = "userName",label = "aaa")
        private String name;
        /**
         * 值
         * serializeUsing支持序列化的时候自定义逻辑。
         * alternateNames支持反序列化的时候，其他名字也可以映射进来
         */
        @JSONField(ordinal = 1,serializeUsing = UserAgeSerializer.class,alternateNames = {"ageA","nianling"})
        private Integer age;

        /**
         * 日期
         */
        @JSONField(ordinal = 2, format = "yyyy-MM-dd")
        private Date date;


        /**
         * json字符串
         * jsonDirect的作用是将json作为对象输出，而不是字符串的内容。
         * SerializerFeature序列化的时候会做一些处理，比如这里，jsonStr为null，正常不显示。但是加了WriteMapNullValue，就会显示为null。
         */
        @JSONField(ordinal = 3,jsonDirect = true,serialzeFeatures = SerializerFeature.WriteMapNullValue)
        private String jsonStr;

    }

    private User user;

    private String jsonString;


    @Before
    public void before() {
        user = new User("aa", 28,new Date(),"{}");
        jsonString = "{\"date\":\"1567054519301\",\"userName\":\"aa\",\"ageA\":28}";
    }


    @Test
    public void test1() {
        System.out.println(JSON.toJSONString(user));
    }

    @Test
    public void test2() {
        System.out.println(JSON.parseObject(jsonString, User.class));

    }

    @Test
    public void test3() {
        user = new User("aa", 28,new Date(),null);
        System.out.println(JSON.toJSONString(user));
    }







}
