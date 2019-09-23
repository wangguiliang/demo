package com.wgl.demo.junittest;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

/**
 * Description:
 *
 * @author: wangguiliang
 * @date:2019-08-28
 */
@RunWith(Parameterized.class)
public class PrimeNumberCheckerTest extends TestBase{

    private static int i = 0;

    private Integer inputNumber;
    private Boolean expectedResult;

    // Each parameter should be placed as an argument here
    // Every time runner triggers, it will pass the arguments
    // from parameters we defined in primeNumbers() method
    public PrimeNumberCheckerTest(Integer inputNumber,
                                  Boolean expectedResult) {
        this.inputNumber = inputNumber;
        this.expectedResult = expectedResult;
    }


     @Parameterized.Parameters
    public static Collection primeNumbers() {
         return Arrays.asList(new Object[][]{
             {2,true},
             {6,false},
             {9,true},
             {22,false},
             {23,true}
         });
     }

    @Test
    public void test() {
        System.out.println("expectedResult:"+expectedResult+",inputNumber:"+inputNumber+"." + i++);
        Assert.assertEquals(expectedResult,
                validate(inputNumber));

    }

    public Boolean validate(final Integer primeNumber) {
        for (int i = 2; i < (primeNumber / 2); i++) {
            if (primeNumber % i == 0) {
                System.out.println("primeNumber:"+primeNumber+",i="+i);
                return false;
            }
        }
        return true;
    }

}
