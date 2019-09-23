package com.wgl.demo.fastjson;

import com.alibaba.fastjson.JSONPath;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Description:
 *
 * @author: wangguiliang
 * @date:2019-08-29
 */
public class JSONPathTest {

    @Data
    @Builder
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

        private List<String> list;

    }

    private User user;

    @Before
    public void before() {
        List<String> list = new ArrayList() {
            {
                add("a");
                add("b");
                add("c");
                add("d");
            }
        };
        user = User.builder().name("aa").age(1).date(new Date()).jsonStr("{}").list(list).build();
    }

    @Test
    public void test1() {
        System.out.println(user.toString());
        System.out.println(JSONPath.eval(user,"$.userName"));
        System.out.println(JSONPath.eval(user,"$.list[1,3]"));
        System.out.println(JSONPath.eval(user,"$.list[1:3:2]"));
        System.out.println(JSONPath.eval(user,"$.list.size()"));
        System.out.println(JSONPath.eval(user,"$.list[-1]"));
        System.out.println(JSONPath.eval(user,"$.list[:-2]"));
        System.out.println(JSONPath.eval(user,"$.list[1:]"));


        Assert.assertTrue(JSONPath.contains(user, "$.userName"));

        Assert.assertTrue(JSONPath.containsValue(user,"$.age",1));
    }

    @Test
    public void test2() {

        List<User> users = new ArrayList<>(3);
        users.add(User.builder().age(1).build());
        users.add(User.builder().age(2).build());
        users.add(User.builder().age(3).build());

        // 还可以将一个对象list中的一个属性，拿出来作为一个新的list
        List<Integer> list = (List<Integer>)JSONPath.eval(users, "$.age");
        System.out.println(list);

        List<User> filterUsers = (List<User>)JSONPath.eval(users, "[0,1]");
        System.out.println(filterUsers);

        List<User> filterUsers2 = (List<User>)JSONPath.eval(users, "[0:2]");
        System.out.println(filterUsers2);

        List<User> filterUsers3 = (List<User>)JSONPath.eval(users, "[age in (3)]");
        System.out.println(filterUsers3);
    }

    @Test
    public void test3() {

        // 真就不理解这么做有毛用。
        System.out.println(user.toString());
        JSONPath.set(user, "age", 123456); //将id字段修改为123456
        System.out.println(user.toString());

    }

}
