package com.datdang.integration_demo.robots;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.datdang.integration_demo.base.CoreRobot;

public class HomeRobot extends CoreRobot {

    public HomeRobot(WebDriver webDriver, WebDriverWait wait) {
        super(webDriver, wait);
    }

    public void navigateToTestSite(String url) {
        webDriver.navigate().to(url);
    }
}
