package com.wgl.demo.test;

import org.junit.Test;
import org.springframework.util.AlternativeJdkIdGenerator;

/**
 * Description:
 *
 * @author: wangguiliang
 * @date:2019-05-24
 */
public class UUIDTest {

    @Test
    public void test() {

        AlternativeJdkIdGenerator alternativeJdkIdGenerator = new AlternativeJdkIdGenerator();
        for (int i = 0; i < 10; i++) {
            System.out.println(alternativeJdkIdGenerator.generateId().toString());
        }

    }

}
