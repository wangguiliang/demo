package com.wgl.demo.test;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Description:
 *
 * @author: wangguiliang
 * @date:2019-06-24
 */
public class StringBuilderTest {

    @Test
    public void test() {


        String a = "aaa/xxx/sss/aaa.com";
        int pom = a.lastIndexOf("/");
        a = a.substring(0, pom+1);
        System.out.println(a);
    }

    @org.junit.Test
    public void timeTest() {
        String startTImeStr = "2019-06-25T16:26:40Z";
        String usedTimeStr = "00:01:16";
        String finishTimeStr = "2019-06-25T16:27:56Z";


        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");

        startTImeStr = startTImeStr.replace("T", " ")
                .replace("Z", " ");
        System.out.println(startTImeStr);

        finishTimeStr = finishTimeStr.replace("T", " ").replace("Z", " ");


        try {
            Date startTime = formatter.parse(startTImeStr);
            Date finishTime = formatter.parse(finishTimeStr);
            Date usedTime = format.parse(usedTimeStr);

            System.out.println(startTime.toString());
            System.out.println(finishTime.toString());
            System.out.println(usedTime.toString());
        } catch (ParseException pe) {
            System.out.println(pe.toString());
            System.out.println("异常！");
        }




    }

    @Test
    public void test2() {
        String time = "2019-07-05 17:00:00";
        StringBuilder sb = new StringBuilder(time);
        stringTest(time);
        objectTest(sb);

        System.out.println(time);
        System.out.println(sb.toString());
    }

    private void stringTest(String time) {
        System.out.println("stringTest");
        time += "aaa";
        System.out.println(time);
    }

    private void objectTest(StringBuilder sb) {
        System.out.println("objectTest");
        sb.append("cccc");
        sb = new StringBuilder("aaaa");
        System.out.println(sb.toString());
        sb.append("bbb");
        System.out.println(sb);
    }

    @Test
    public void test3() {
        StringBuilder sb = new StringBuilder("abcdefg");

        System.out.println(sb.toString().length());

        sb.delete(0, sb.toString().length());
        System.out.println(sb.toString());

    }

    @Test
    public void test4() {
        StringBuilder sb = new StringBuilder("abcdefg");
        sb.insert(1, "123");
        System.out.println(sb.toString());

    }

    @Test
    public void test5() {
        A a = new A();
        a.setA("aa");
        System.out.println(a.getA().getClass());
    }

    class A{
        private String a;

        public String getA() {
            return a;
        }

        public void setA(String a) {
            this.a = a;
        }
    }


}
