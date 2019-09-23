package com.wgl.demo.fastjson;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * Description:
 *
 * @author: wangguiliang
 * @date:2019-08-29
 */
public class UserAgeSerializer implements ObjectSerializer {
    @Override
    public void write(JSONSerializer jsonSerializer, Object o, Object o1, Type type, int i) throws IOException {
        Integer value = (Integer) o;
        jsonSerializer.write(++value);
    }
}
