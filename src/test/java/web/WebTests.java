package web;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Story;
import org.example.webPages.LoginPage;
import org.example.webPages.MyPostsPage;
import org.example.webPages.PostPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class WebTests {
    WebDriver driver;
    LoginPage loginPage;
    MyPostsPage myPostsPage;
    PostPage postPage;


    @BeforeAll
    static void registerDriver() {
        WebDriverManager.chromedriver().setup();
    }


    @BeforeEach
    void initDriver() throws InterruptedException {
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        myPostsPage = new MyPostsPage(driver);
        postPage = new PostPage(driver);

    }

    @Test
    @Story("Авторизация пользователя ")
    @DisplayName("Тест_1: Авторизация пользователя с валидными Логином и Паролем ")
    public void test1() throws InterruptedException {
        loginPage.login("test22","4d42bf9c18");
        myPostsPage.checkChangeUrl();
    }

    @Test
    @Story("Авторизация пользователя ")
    @DisplayName("Тест_2: Авторизация пользователя с пустыми Логином и Паролем ")
    public void test2() throws InterruptedException {
        loginPage.inputLogin("");
        loginPage.inputPassword("");
        loginPage.clickLogin();
        loginPage.checkUrl();//проверяем что остались на той же странице
    }
    @Test
    @Story("Авторизация пользователя ")
    @DisplayName("Тест_3: Авторизация пользователя с невалидным Логином")
    public void test3() throws InterruptedException {
        loginPage.inputLogin("2344*?*:?:?");
        loginPage.inputPassword("555555");
        loginPage.clickLogin();
        loginPage.checkUrl();//проверяем что остались на той же странице
    }
    @Test
    @Story("Авторизация пользователя ")
    @DisplayName("Тест_4: Авторизация пользователя с невалидным количеством символом в поле Логин")
    public void test4() throws InterruptedException {
        loginPage.inputLogin("ап");
        loginPage.inputPassword("555555");
        loginPage.clickLogin();
        loginPage.checkUrl();//проверяем что остались на той же странице
    }
    @Test
    @Story("Лента своих постов")
    @DisplayName("Тест_5: Переход к следущей странице в ленте своих постов")
    public void test5() throws InterruptedException {
        loginPage.login("test22","4d42bf9c18");
        myPostsPage.moveToNextPage();
        myPostsPage.checkMoveToNextPage();
    }
    @Test
    @Story("Лента своих постов")
    @DisplayName("Тест_6: У превью поста отображается картинка, заголовок и описание")
    public void test6() throws InterruptedException {
        loginPage.login("test22","4d42bf9c18");
        myPostsPage.checkPosts();

    }
    @Test
    @Story("Лента своих постов")
    @DisplayName("Тест_7: Переход на предыдущую страницу ленты своих постов ")
    public void test7() throws InterruptedException {
        loginPage.login("test22","4d42bf9c18");
        myPostsPage.moveToPreviousPage();
        myPostsPage.checkMoveToPreviousPage();

    }
    @Test
    @Story("Лента своих постов")
    @DisplayName("Тест_8: Переход на страницу конкретного поста ")
    public void test8() throws InterruptedException {
        loginPage.login("test22", "4d42bf9c18");
        myPostsPage.clickPost();
        postPage.checkMoveToPostPage();

    }

    @Test
    @Story("Лента своих постов")
    @DisplayName("Тест_9: Сортировка постов от старыз к новым ")
    public void test9() throws InterruptedException {
        loginPage.login("test22", "4d42bf9c18");
        myPostsPage.clickOrder();
        myPostsPage.checkOrderOn();

    }
    @Test
    @Story("Лента своих постов")
    @DisplayName("Тест_10: Переход к странице чужих постов")
    public void test10() throws InterruptedException {
        loginPage.login("test22", "4d42bf9c18");
        myPostsPage.moveNotMyPostsPage();
        myPostsPage.checkMoveNotMyPostsPage();
    }
    @Test
    @Story("Лента своих постов")
    @DisplayName("Тест_11: Переход к домашней странице")
    public void test11() throws InterruptedException {
        loginPage.login("test22", "4d42bf9c18");
        myPostsPage.moveNotMyPostsPage();
        myPostsPage.moveToHomePage();
        myPostsPage.checkMoveToHomePage();
    }
        @AfterEach
    void tearDown() {
        driver.quit();
    }

}
