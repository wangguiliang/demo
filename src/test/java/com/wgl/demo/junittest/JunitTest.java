package com.wgl.demo.junittest;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Description:
 *
 * @author: wangguiliang
 * @date:2019-08-28
 */
public class JunitTest {


    /**
     * 真没想到，@BeforeClass执行顺序是倒叙
     */
    @BeforeClass
    public static void dadadastart1() {
        System.out.println("before class dadadastart1"+JunitTestSuite.i++);
    }

    @BeforeClass
    public static void dadadastart2() {
        System.out.println("before class dadadastart2"+JunitTestSuite.i++);
    }


    @BeforeClass
    public static void dadadastart3() {
        System.out.println("before class dadadastart3"+JunitTestSuite.i++);
    }

    @Before
    public void lalalastart() {
        System.out.println("before lalalastart"+JunitTestSuite.i++);
    }

    @Ignore(value = "test1 innored")
    @Test
    public void test1() {
        System.out.println("test1 self"+JunitTestSuite.i++);
    }

    @Test(timeout = 10)
    public void test2() {
        System.out.println("test2 self"+JunitTestSuite.i++);
        int i = 0;
        while (true) {
            try {
                Thread.sleep(9);
            } catch (InterruptedException ie) {
                System.out.println("error:"+ie.getMessage());
            }

            System.out.print(i++);
        }
    }

    @Test(expected = RuntimeException.class)
    public void test3() {
        System.out.println("test3 seft" + JunitTestSuite.i++);
        throw new RuntimeException("aaa");
    }
    @After
    public void dododoend() {
        System.out.println("after dododoend"+JunitTestSuite.i++);
    }

    @AfterClass
    public static void kekekeend1() {
        System.out.println("after class kekekeend1"+JunitTestSuite.i++);
    }

    @AfterClass
    public static void kekekeend2() {
        System.out.println("after class kekekeend2"+JunitTestSuite.i++);
    }

    @AfterClass
    public static void kekekeend3() {
        System.out.println("after class kekekeend3"+JunitTestSuite.i++);
    }




}
