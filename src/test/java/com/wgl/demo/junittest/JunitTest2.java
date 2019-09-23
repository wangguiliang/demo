package com.wgl.demo.junittest;

import org.junit.Test;

/**
 * Description:
 *
 * @author: wangguiliang
 * @date:2019-08-28
 */
public class JunitTest2 {


    @Test
    public void test1() {
        System.out.println("junitTest2 test1 self"+JunitTestSuite.i++);
    }
}
