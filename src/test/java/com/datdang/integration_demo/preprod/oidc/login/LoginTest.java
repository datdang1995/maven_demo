package com.datdang.integration_demo.preprod.oidc.login;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.datdang.integration_demo.base.TestBase;
import com.datdang.integration_demo.usecases.LoginUseCase;

public class LoginTest extends TestBase {

    public LoginTest(WebDriver webDriver, WebDriverWait wait) {
        super(webDriver, wait);
    }

    @Test
    public void testLogin() {
        String username = System.getProperty("username");
        String password = System.getProperty("password");

        testUseCase(new LoginUseCase(
                "http://localhost:2023/",
                username,
                password));
    }
}
