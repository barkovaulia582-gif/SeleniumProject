package pageTests;

import helpers.PropertyReader;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;


import java.net.MalformedURLException;


import static helpers.Utils.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginPageTest extends BaseTest {


    private static String loginPageUrl;
    private static String testUserDashBoardUrl;
    private static String wrongLoginOrPasswordMessage;
    private static String correctEmailRequired;


    private final By wrongDataMessage = By.xpath("//*[text()='Некорректное имя пользователя или пароль.']");

    @BeforeEach
    @Override
    public void baseSetUp() {

        loginPageUrl = PropertyReader.getProperty("loginPageUrl");
        testUserDashBoardUrl = PropertyReader.getProperty("testUserDashBoardUrl");
        wrongLoginOrPasswordMessage = PropertyReader.getProperty("wrongLoginOrPasswordMessage");
        correctEmailRequired = PropertyReader.getProperty("correctEmailRequired");
        driver.get(loginPageUrl);
    }

    @DisplayName("Test with correct login and password")
    @Test
    public void correctDataLoginTest() {
        loginPage.login(login, password);
        wait.until(ExpectedConditions.urlToBe(testUserDashBoardUrl));
        assertEquals(testUserDashBoardUrl, driver.getCurrentUrl());
    }



    @DisplayName("Remember me test")
    @Test
    public void rememberMeTest() {
        loginPage.setRememberMeCheckbox(true);
        loginPage.login(login, password);
        wait.until(ExpectedConditions.urlToBe(testUserDashBoardUrl));
        driver.get(loginPageUrl);
        wait.until(ExpectedConditions.urlToBe(testUserDashBoardUrl));
        assertEquals(testUserDashBoardUrl, driver.getCurrentUrl());
    }



     @DisplayName("Test with correct login and incorrect password")
    @Test
    public void incorrectPasswordTest() {
        String wrongPassword = "wrongPas";
        loginPage.login(login, wrongPassword);
        wait.until(ExpectedConditions.visibilityOf(loginPage.getErrorMessage()));
        assertEquals(wrongLoginOrPasswordMessage, loginPage.getErrorMessageText());
    }

    @DisplayName("Test with correct password and incorrect login")
    @Test
    public void incorrectLoginTest() {
        String wrongLogin = "wrongLog";
        loginPage.login(wrongLogin, password);

        wait.until(ExpectedConditions.visibilityOf(loginPage.getErrorMessage()));
        assertEquals(wrongLoginOrPasswordMessage, loginPage.getErrorMessageText());
    }


}
