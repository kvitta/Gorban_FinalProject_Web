package org.example.webPages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
// базовый Page со всеми настройками, здась объявляем все необъодимое// все Page будут от него наследоваться
public class BaseView {// View- общее между Page и блоком
    public WebDriver driver;// объявляем драйвер
    public WebDriverWait webDriverWait;// объявляем WebDriverWait
    Actions actions;// объявляем Actions

    public BaseView(WebDriver driver) {// в конструктор передаем драйвер
        this.driver = driver;// инициализируем его
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(15));// инициализируем WebDriverWait
        actions = new Actions(driver);// инициализируем Actions
        driver.get("https://test-stand.gb.ru/");
        PageFactory.initElements(driver, this);// передаем ему на вход драйвер и страницу
        // PageFactory каждый раз когда мы обращаемся к элементу Selenium обновляет его и если элемента нет- кладет в переменную null
        // это позволяет решить проблему пропадающих переиодически элементов или динамических жлементов на странице
    }
}
