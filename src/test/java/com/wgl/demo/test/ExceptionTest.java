package com.wgl.demo.test;

import org.junit.Test;

/**
 * Description:
 *
 * @author: wangguiliang
 * @date:2019-05-12
 */
public class ExceptionTest {

    @Test
    public void test() {

        try {
            System.out.println("A");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("B");
            try {
                throw new BusinessException("a");
            } catch (Exception e) {
                e.printStackTrace();
            }

            System.out.println("C");

        }

    }
}
