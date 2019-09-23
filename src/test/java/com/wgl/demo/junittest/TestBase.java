/*
 * Copyright (c) 2019. Baidu, Inc. All Rights Reserved.
 */

package com.wgl.demo.junittest;

import org.junit.Before;
import org.springframework.test.context.TestContextManager;

/**
 * Description:
 *
 * @author: wangguiliang
 * @date:2019-09-19
 */
public class TestBase {

    private TestContextManager testContextManager;

    @Before
    public void setUpContext() throws Exception {
        this.testContextManager = new TestContextManager(getClass());
        this.testContextManager.prepareTestInstance(this);
    }


}
