package org.example.webPages;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

//a[@class="logo svelte-1rc85o5"] Home  в хедере
//div[@class="svelte-1rc85o5"] Блок "About,Contacts,Hello"
//button[@id="SMUI-form-field-1"] тумблер not my posts
//button[@id="SMUI-form-field-1"and @aria-checked="true"]- активированный тумблер not my posts
//div[@class="mdc-form-field"]//span[text ()= "Order"]/parent::label/preceding-sibling::button  Кнопка order
//div[@class="mdc-form-field"]//span[text ()= "Order"]/parent::label/preceding-sibling::button[@aria-pressed="false"] Кнопка order - включена сортировка от старых к новым
//a[@href and text() = "Next Page"] - next page
//a[@href and text() = "Previous Page"] - Previous Page
//a[contains(@class,"disabled")] - Disabled Previous Page
//div[@class = "content"] список превь постов
//div[@class = "content"]/div/a[1] - первый из списка пост
//div[@class = "content"]/div/a[1]/img - картинка у поста
//div[@class = "content"]/div/a[1]/h2 - заголовок у поста
//div[@class = "content"]/div/a[1]/div - описание поста



public class MyPostsPage extends BaseView{

    public MyPostsPage(WebDriver driver) {super(driver);
    }
    @FindBy(xpath = "//a[@class=\"logo svelte-1rc85o5\"]")// Home  в хедере
    private WebElement homeHref;

    @FindBy(xpath = "//ul[@class=\"svelte-1rc85o5\"")// Блок "About,Contacts,Hello"
    private WebElement blockInHerders;

    @FindBy(xpath = "//button[@id=\"SMUI-form-field-1\"]")// тумблер Not my Posts
    private WebElement radioButtonNotMyPosts;


    @FindBy(xpath = " //button[@id=\"SMUI-form-field-1\"and @aria-checked=\"true\"]")// актиированный тумблер Not my Posts
    private WebElement radioButtonNotMyPostsON;
    @FindBy(xpath = "//div[@class=\"mdc-form-field\"]//button[@aria-pressed=\"true\"]")//Кнопка order - включена сортировка от старых к новым
    private WebElement buttonOrderOn;

    @FindBy(xpath = "//div[@class=\"mdc-form-field\"]//span[text ()= \"Order\"]/parent::label/preceding-sibling::button")// Кнопка order
    private WebElement buttonOrder;
    @FindBy(xpath = "//a[@href and text() = \"Next Page\"]")//next page
    private WebElement hrefNextPage;

    @FindBy(xpath = "//a[@href and text() = \"Previous Page\"]")//Previous Page
    private WebElement hrefPreviousPage;

    @FindBy(xpath = "//div[@class = \"content\"]/div[1]]")// ListOfPosts
    private WebElement listOfPosts;

    @FindBy(xpath = "//div[@class = \"content\"]/div/a[1]/img")//картинка поста
    private WebElement img;

    @FindBy(xpath = "//div[@class = \"content\"]/div/a[1]/h2")//заголовок поста
    private WebElement title;

    @FindBy(xpath = "//div[@class = \"content\"]/div/a[1]/div")//описание поста
    private WebElement description;

    @FindBy(xpath = "//div[@class = \"content\"]/div/a[1]/div")//задизейбленная предыдущая страница
    private WebElement disabledPreviousPage;

    @FindBy(xpath = "//div[@class = \"content\"]/div/a[1]")//задизейбленная предыдущая страница
    private WebElement firstPost;

    @Step("Проверяем что после авторизации перешли на страницу постов (по url)")
    public void checkChangeUrl() throws InterruptedException {
        Assertions.assertFalse(driver.getCurrentUrl().contains("login"));
    }

    @Step("Проверяем что на странице постов отображены превью постов (по элементу)")
    public void checkPosts(){
        Assertions.assertTrue(title.isEnabled());
        Assertions.assertTrue(img.isEnabled());
        Assertions.assertTrue(description.isEnabled());
    }
    @Step("Переходим на следущую страницу")
    public MyPostsPage moveToNextPage() {
        hrefNextPage.click();
        return this;
    }
    @Step("Проверяем что перешли на  следущую страницу")
    public void checkMoveToNextPage() throws InterruptedException {
        Thread.sleep(2000);
        ExpectedConditions.elementToBeClickable(hrefPreviousPage);
        Assertions.assertTrue(hrefPreviousPage.isEnabled());
    }
    @Step("Переходим на предыдущую страницу")
    public MyPostsPage moveToPreviousPage() throws InterruptedException {
        hrefNextPage.click();
        Thread.sleep(2000);
        ExpectedConditions.elementToBeClickable(hrefPreviousPage);
        hrefPreviousPage.click();
        return this;}

    @Step("Проверяем перход на предыдущую страницу")
    public MyPostsPage checkMoveToPreviousPage() {
       Assertions.assertTrue(disabledPreviousPage.isEnabled());
        return this;}

    @Step("проваливаемся в пост")
    public PostPage clickPost() throws InterruptedException {
        ExpectedConditions.visibilityOf(img);
        firstPost.click();
        return new PostPage(driver);}

    @Step("Включаем сортировка от старых постов к новым")
    public MyPostsPage clickOrder() throws InterruptedException {
        ExpectedConditions.visibilityOf(buttonOrder);
        buttonOrder.click();
        return this;}

    @Step("Проверяем что посты отсортированны от старых к новым")
    public MyPostsPage checkOrderOn() throws InterruptedException {
        ExpectedConditions.visibilityOf(buttonOrderOn);
        Assertions.assertTrue(Boolean.parseBoolean(String.valueOf(buttonOrder.getAttribute("aria-pressed").equals("true"))));
        return this;}

    @Step("Переходим к странице чужих постов")
    public MyPostsPage moveNotMyPostsPage() throws InterruptedException {
        ExpectedConditions.visibilityOf(radioButtonNotMyPosts);
        radioButtonNotMyPosts.click();
        return this;}
    @Step("Проверяем что перешли к ленте чужих постов")
    public MyPostsPage checkMoveNotMyPostsPage() throws InterruptedException {
        ExpectedConditions.visibilityOf(radioButtonNotMyPostsON);
        Assertions.assertTrue(Boolean.parseBoolean(String.valueOf(radioButtonNotMyPosts.getAttribute("aria-checked").equals("true"))));
        return this;}

    @Step("переход на домашнюю страницу через home в хедере")
    public MyPostsPage moveToHomePage() throws InterruptedException {
        ExpectedConditions.visibilityOf(homeHref);
        homeHref.click();
        Assertions.assertTrue(Boolean.parseBoolean(String.valueOf(radioButtonNotMyPosts.getAttribute("aria-checked").equals("true"))));
        return this;}
    @Step("Проверка перехода на домашнюю страницу через home в хедере")
    public MyPostsPage checkMoveToHomePage() throws InterruptedException {
        ExpectedConditions.visibilityOf(radioButtonNotMyPosts);
        Assertions.assertTrue(radioButtonNotMyPostsON.isEnabled());
        return this;}
}

