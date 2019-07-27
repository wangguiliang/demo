package com.wgl.demo.test;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Description:
 *
 * @author: wangguiliang
 * @date:2019-05-31
 */
public class PatternTest {


    @Test
    public void test() {
        String regex = "formkey=\"(.*?)\"";
        String str = "activiti:formkey=\"123\"aaaaactiviti:formkey=\"456\"ffffactiviti:formkey=\"789\"";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);

        while (matcher.find()) {
            System.out.println(matcher.group());
        }

    }



    @Test
    public void testtest() {
        String test = "hahahhehe sendCode\":\"12367890123rsdfsdfsdfdsahahhehe sendCode\":\"632551415343rsdfsdfsdfds";
        test = regMatch(test, "sendCode\":\"([\\d]{8})([\\d]{3})");

    }

    private static String regMatch(String withinText, String regString) {
        String code = null;
        Pattern pattern = Pattern.compile(regString);
        Matcher matcher = pattern.matcher(withinText);
        if (matcher.find()) {
            matcher.reset();
            while (matcher.find()) {
                code = matcher.group(1);
                System.err.println("aaaa" + code);
                code = matcher.group(0);
                System.err.println("bbbb" + code);
                code = matcher.group(2);
                System.err.println("ccc" + code);
            }
        }
        return code;
    }


    @Test
    public void testT() {
        Object o = "111";
        Long l = Long.parseLong((String)o);
        System.out.println(l+1);
    }

}
