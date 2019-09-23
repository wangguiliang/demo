package com.wgl.demo.junittest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Description:
 *
 * @author: wangguiliang
 * @date:2019-08-28
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        JunitTest.class,
        JunitTest2.class
})
public class JunitTestSuite {

    public static int i = 0;
}
