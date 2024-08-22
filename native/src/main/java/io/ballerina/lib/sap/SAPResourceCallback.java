/*
 * Copyright (c) 2024, WSO2 LLC. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 LLC. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package io.ballerina.lib.sap;

import io.ballerina.runtime.api.async.Callback;
import io.ballerina.runtime.api.values.BError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;

public class SAPResourceCallback implements Callback {

    private static final Logger logger = LoggerFactory.getLogger(Client.class);
    private final CountDownLatch countDownLatch;

    public SAPResourceCallback(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void notifySuccess(Object o) {
        if (o instanceof BError) {
            logger.error("Error occurred: " + o);
        }
        countDownLatch.countDown();
    }

    @Override
    public void notifyFailure(BError bError) {
        countDownLatch.countDown();
        logger.error("Error occurred: " + bError.toString());
        System.exit(1);
    }
}
