package com.datdang.integration_demo.base;

public class TestUtils {
    public void waitFor(int seconds) {
        try {
            Thread.sleep(1000 * seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
