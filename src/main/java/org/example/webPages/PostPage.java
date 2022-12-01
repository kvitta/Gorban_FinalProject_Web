package org.example.webPages;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PostPage extends BaseView {
    public PostPage(WebDriver driver) {super(driver);
    }
        @FindBy(xpath = "//div[contains(@class,\"button-block\")]")
        private WebElement buttonsBlock;

    @Step("Проверяем что перешли страницу поста")
    public PostPage checkMoveToPostPage() throws InterruptedException {
        ExpectedConditions.elementToBeClickable(buttonsBlock);
        Assertions.assertTrue(buttonsBlock.isEnabled());
        return this;
    }

}
