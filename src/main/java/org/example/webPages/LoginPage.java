package org.example.webPages;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BaseView {// наследуемся от BaseView

    public LoginPage(WebDriver driver) {// создаем конструктор
        super(driver);
    }
    // ОПИСЫВАЕМ НЕОБХОДИМЫЕ ПОЛЯ ( В ИДЕАЛЕ ВСЕ,ЧТО ЕСТЬ НА СТРАНИЦЕ)
    @FindBy(xpath = "//input[@type=\"text\"]")// поле емайл
    private WebElement usernameField;

    @FindBy(xpath = "//input[@type=\"password\"]")// поле пароль
    private WebElement passwordField;

    @FindBy(xpath = "//span[@class=\"mdc-button__label\"]")// кнопка зарегестрироваться
    private WebElement loginButton;

   @Step ("Авторизация пользователя")
    public MyPostsPage login(String login, String password) throws InterruptedException {// на вход передаем логин и пароль для позитивного теста
        // метод для регистрации( включает все действия)- для негативных сценариев каждое действие в отдельный метод
        usernameField.click();
        usernameField.sendKeys(login);// вбиваем логин
        passwordField.click();
        passwordField.sendKeys(password);//вбиваем пароль
        loginButton.click();//кликаем
        Thread.sleep(3000);
        return new MyPostsPage(driver);
    }
    @Step("Заполняем поле Логин")
    public LoginPage inputLogin (String login){
        usernameField.click();
        usernameField.sendKeys(login);
        return this;
    }
    @Step("Заполняем поле Пароль")
    public LoginPage inputPassword(String password){
        passwordField.click();
        passwordField.sendKeys(password);
        return this;
    }
    @Step("Кликаем на кнопку Login")
    public MyPostsPage clickLogin() throws InterruptedException {
        loginButton.click();
//        Thread.sleep(2000);
        return new MyPostsPage(driver);
    }

    @Step("Проверяем что остались на той же странице(по url)")
    public void checkUrl(){
        Assertions.assertTrue(driver.getCurrentUrl().contains("login"));
    }

}

