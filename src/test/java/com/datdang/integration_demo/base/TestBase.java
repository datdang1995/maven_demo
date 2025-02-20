package com.datdang.integration_demo.base;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;

import org.junit.After;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

@RunWith(Parameterized.class)
public abstract class TestBase {
    private WebDriver webDriver;
    private WebDriverWait wait;

    public TestBase(WebDriver webDriver, WebDriverWait wait) {
        this.webDriver = webDriver;
        this.wait = wait;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Boolean runHeadlessTest = true;
        int size = SupportedPlatform.values().length;
        Object[][] parameters = new Object[size][2];
        for (int i = 0; i < size; i++) {
            SupportedPlatform platform = SupportedPlatform.values()[i];
            Object[] config = new Object[2];
            switch (platform) {
                case CHROME:
                    config = configTestForChrome(runHeadlessTest);
                    break;
                case FIREFOX:
                    config = configTestForFirefox(runHeadlessTest);
                    break;
                default:
                    throw new UnsupportedPlatformException();
            }
            parameters[i] = config;
        }

        return Arrays.asList(parameters);
    }

    public void testUseCase(UseCase useCase) {
        useCase.execute(webDriver, wait);
    }

    private static Object[] configTestForChrome(Boolean headless) {
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        if (headless) {
            options.addArguments("--headless=new");
        }
        ChromeDriver webDriver = new ChromeDriver(options);
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofMillis(20000));
        Object[] config = new Object[2];
        config[0] = webDriver;
        config[1] = wait;
        return config;
    }

    private static Object[] configTestForFirefox(Boolean headless) {
        FirefoxOptions options = new FirefoxOptions();
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        if (headless) {
            options.addArguments("--headless");
        }
        FirefoxDriver webDriver = new FirefoxDriver(options);
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofMillis(20000));
        Object[] config = new Object[2];
        config[0] = webDriver;
        config[1] = wait;
        return config;
    }

    private void dispose() {
        webDriver.quit();
    }

    @After
    public void tearDown() {
        dispose();
    }
}
