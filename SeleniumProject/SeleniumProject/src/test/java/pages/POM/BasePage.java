package pages.POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BasePage {

    protected WebDriver driver;



    private final By userNameField = By.xpath("//*[@id='username']");
    private final By passwordField = By.xpath("//*[@id='password']");
    private final By loginButton = By.xpath("//button[@data-test='login-button']");
    private void enterUserName(String userName) {
        driver.findElement(userNameField).sendKeys(userName);
    }

    private void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    private void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    public void login(String username, String password) {
        enterUserName(username);
        enterPassword(password);
        clickLoginButton();
    }
}
