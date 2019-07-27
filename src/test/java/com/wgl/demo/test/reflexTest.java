package com.wgl.demo.test;

import org.junit.Test;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Description:
 *
 * @author: wangguiliang
 * @date:2019-07-08
 */
public class reflexTest {


    @Test
    public void test1() {
        A a = new A();
        a.setA("aa");
        a.setB("bb");
        a.setC("cc");

        Class c = a.getClass();
        Field[] fields = c.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            field.setAccessible(true);
            try {
                System.out.println("key:"+field.getName());
                System.out.println("value:"+field.get(a));
                System.out.println("declaringClass:" + field.getDeclaringClass());
                System.out.println("type:" + field.getType());
            } catch (IllegalAccessException iae) {
                System.out.println("error");
            }

        }
    }

    class A{
        private String a;
        private String b;
        private String c;

        public String getA() {
            return a;
        }

        public void setA(String a) {
            this.a = a;
        }

        public String getB() {
            return b;
        }

        public void setB(String b) {
            this.b = b;
        }

        public String getC() {
            return c;
        }

        public void setC(String c) {
            this.c = c;
        }
    }

    @Test
    public void test2() {
        try {
            long time1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2019-07-09 10:00:00").getTime();

            long time2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2019-07-09 00:00:00").getTime();

            long time3 = new SimpleDateFormat("yyyy-MM-dd").parse("2019-07-09").getTime();

            System.out.println(time1);
            System.out.println(time2);
            System.out.println(time3);
        } catch (ParseException pe) {
            System.out.println("error");
        }


    }

    @Test
    public void test3() {
        Integer a = 1;
        System.out.println(a.toString());
    }

    @Test
    public void test4() {
        long a = 1562601600000L;
        System.out.println(new Date(a).toString());
    }

}
