package com.wgl.demo.test;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Description:
 *
 * @author: wangguiliang
 * @date:2019-05-12
 */
public class OptionalTest {

    /**
     * 参考：https://www.cnblogs.com/yw0219/p/7354938.html
     *
     * 这篇应用：http://www.importnew.com/26066.html     不错
     */
    @Test
    public void test() {
        Optional<String> name = Optional.of("Trump");
        try {
            Optional<String> someNull = Optional.of(null);
        } catch (NullPointerException npe) {
            System.out.println("of()不能传null");
            npe.getMessage();
        }

        Optional empty = Optional.ofNullable(null);

        System.out.println(Optional.ofNullable("aaa").map(v -> v.toUpperCase()).orElse("no value"));
        //ofNullable与of的唯一区别是ofNullable可以传入Null

        if (name.isPresent()) {
            System.out.println(name.get());
        }

        try {
            System.out.println(empty.get());
        } catch (NoSuchElementException nsee) {
            System.out.println(nsee.getMessage());
            nsee.printStackTrace();
        }

        name.ifPresent(value->{
            System.out.println("name is "+value);
        });

        System.out.println(empty.orElse("there is no value !"));
        System.out.println(name.orElse("there is some value"));

        System.out.println(empty.orElseGet(()->"Default value"));
        System.out.println(name.orElseGet(String::new));

        try {
            empty.orElseThrow(IllegalArgumentException::new);
        } catch (Throwable ex) {
            System.out.println("error:" + ex.getMessage());
        }

        System.out.println(name.map(value -> value.toUpperCase()).orElse("no value found"));//map可以返回任意类型，这里返回的是String

        System.out.println(name.flatMap(value -> Optional.of(value.toUpperCase())).map(t -> t + "a").orElse("no value found"));

        List<String> names = Arrays.asList("a", "bb", "ccc");

        names.stream().forEach(s->{
            System.out.println(Optional.of(s).filter(v -> v.length() < 2).orElse("THe length is >= 2"));
        });

    }



}
