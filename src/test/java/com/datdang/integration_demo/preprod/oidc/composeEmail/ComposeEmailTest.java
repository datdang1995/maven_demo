package com.datdang.integration_demo.preprod.oidc.composeEmail;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.datdang.integration_demo.base.TestBase;
import com.datdang.integration_demo.usecases.ComposeEmailUseCase;

public class ComposeEmailTest extends TestBase {

    public ComposeEmailTest(WebDriver webDriver, WebDriverWait wait) {
        super(webDriver, wait);
    }

    @Test
    public void testComposeEmail() {
        String username = System.getProperty("username");
        String password = System.getProperty("password");

        testUseCase(new ComposeEmailUseCase(
                "http://localhost:2023/",
                username,
                password,
                "firstname"));
    }
}
