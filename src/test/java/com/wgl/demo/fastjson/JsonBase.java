package com.wgl.demo.fastjson;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Before;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Description:
 *
 * @author: wangguiliang
 * @date:2019-08-29
 */
public class JsonBase {

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

    protected User user;

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

}
