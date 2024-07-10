package com.datdang.integration_demo.usecases;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.datdang.integration_demo.base.UseCase;
import com.datdang.integration_demo.robots.ComposerRobot;
import com.datdang.integration_demo.robots.MailboxDashboardRobot;

public class ComposeEmailUseCase extends UseCase {
    String testUrl;
    String username;
    String password;
    String additionalReceipent;

    public ComposeEmailUseCase(String testUrl, String username, String password, String additionalReceipent) {
        this.testUrl = testUrl;
        this.username = username;
        this.password = password;
        this.additionalReceipent = additionalReceipent;
    }

    @Override
    public void execute(WebDriver webDriver, WebDriverWait wait) {
        MailboxDashboardRobot mailboxDashboardRobot = new MailboxDashboardRobot(webDriver, wait);
        ComposerRobot composerRobot = new ComposerRobot(webDriver, wait);

        LoginUseCase loginUseCase = new LoginUseCase(testUrl, username, password);
        loginUseCase.execute(webDriver, wait);

        mailboxDashboardRobot.openComposer();

        composerRobot.addReceipients(new ArrayList<String>(List.of(username, additionalReceipent)));
        composerRobot.addSubject("Test subject");
        composerRobot.addContent("Test content");
        composerRobot.clickSend();

        mailboxDashboardRobot.waitForSendEmailSuccessToast();
    }

}
