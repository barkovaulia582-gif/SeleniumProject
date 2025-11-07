package pageTests;

import base.BaseTest;
import base.PropertyReader;
import pages.POM.LoginPagePOM;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;


import static helpers.Utils.*;

public class LoginPageTest extends BaseTest {


    private static String loginPageUrl;
    private static String testUserDashBoardUrl;
    private static String wrongLoginOrPasswordMessage;
    private static String correctEmailRequired;

    private WebDriver driver;
    private WebDriverWait wait;
    private final By wrongDataMessage = By.xpath("//*[text()='Некорректное имя пользователя или пароль.']");

   

    @BeforeEach
    @Override
    public void baseSetUp() {
        // Загружаем специфичные для теста properties
        loginPageUrl = PropertyReader.getProperty("loginPageUrl");
        testUserDashBoardUrl = PropertyReader.getProperty("testUserDashBoardUrl");
        wrongLoginOrPasswordMessage = PropertyReader.getProperty("wrongLoginOrPasswordMessage");
        correctEmailRequired=PropertyReader.getProperty("correctEmailRequired");
        super.baseSetUp();
        driver.get(loginPageUrl);
    }
    @Override
    protected boolean requiresLogin() {
        return false;
    }



    @DisplayName("Test with correct login and password")
    @Test
    public void CorrectDataLoginTest() {
        String testName = "CorrectLoginTest";
        LoginPagePOM loginPage = new LoginPagePOM(driver);
        loginPage.login(login, password);

        wait.until(ExpectedConditions.urlToBe(testUserDashBoardUrl));
        Assertions.assertEquals(testUserDashBoardUrl, driver.getCurrentUrl());

        if (!driver.getCurrentUrl().equals(testUserDashBoardUrl)) {
            takeScreenshot(testName);
        }
    }
   


    @DisplayName("Remember me test")
    @Test
    public void RememberMeTest() throws MalformedURLException {
        String testName = "RememberMeTest";
        LoginPagePOM loginPage = new LoginPagePOM(driver);
        loginPage.setCheckBox();
        loginPage.login(login, password);

        wait.until(ExpectedConditions.urlToBe(testUserDashBoardUrl));

        driver.get(loginPageUrl);

        wait.until(ExpectedConditions.urlToBe(testUserDashBoardUrl));

        Assertions.assertEquals(driver.getCurrentUrl(), testUserDashBoardUrl);

        if (!driver.getCurrentUrl().equals(testUserDashBoardUrl)) {
            takeScreenShot(testName, driver);
        }
    }



    @DisplayName("Test with correct login and incorrect password")
    @Test
    public void IncorrectPasswordTest() throws MalformedURLException {
        String testName = "IncorrectPasswordTest";
        LoginPagePOM loginPage = new LoginPagePOM(driver);
        String wrongPassword = "wrongPas";
        loginPage.login(login, wrongPassword);
        wait.until(ExpectedConditions.textToBe(wrongDataMessage, wrongLoginOrPasswordMessage));
        Assertions.assertEquals(wrongLoginOrPasswordMessage, driver.findElement(wrongDataMessage).getText());

        if (!wrongLoginOrPasswordMessage.equals(driver.findElement(wrongDataMessage).getText())) {
            takeScreenShot(testName, driver);
        }
    }

    @DisplayName("Test with correct password and incorrect login")
    @Test
    public void IncorrectLoginTest() throws MalformedURLException {
        String testName = "IncorrectLoginTest";
        LoginPagePOM loginPage = new LoginPagePOM(driver);
        String wrongLogin = "wrongLog";
        loginPage.login(wrongLogin, password);
        wait.until(ExpectedConditions.textToBe(wrongDataMessage, wrongLoginOrPasswordMessage));
        Assertions.assertEquals(wrongLoginOrPasswordMessage, driver.findElement(wrongDataMessage).getText());
        if (!wrongLoginOrPasswordMessage.equals(driver.findElement(wrongDataMessage).getText())) {
            takeScreenShot(testName, driver);
        }
    }

    @DisplayName("Forgot password, incorrect Email test")
    @Test
    public void ForgotPasswordWithIncorrectEmailTest() throws MalformedURLException {
        String testName = "ForgotPasswordWithIncorrectEmailTest";
        LoginPagePOM loginPage = new LoginPagePOM(driver);
        loginPage.forgotPassword();

        wait.until(ExpectedConditions.urlContains("/hub/auth/restore"));

        String incorrectEmail = "incorrectEmail";
        driver.findElement(By.name("email")).sendKeys(incorrectEmail);

        By incorrectEmailMessage = By.xpath("//*[text()=" +
                "'Требуется корректный адрес электронной почты']");
        wait.until(ExpectedConditions.textToBe(incorrectEmailMessage,
                correctEmailRequired));

        Assertions.assertEquals(correctEmailRequired, driver.findElement(incorrectEmailMessage).getText());

        if (!correctEmailRequired.equals(driver.findElement(incorrectEmailMessage).getText())) {
            takeScreenShot(testName, driver);
        }
    }

}
