package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {


    @FindBy(id = "username")
    private WebElement usernameField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(xpath = "//button[@data-test='login-button']")
    private WebElement loginButton;

    @FindBy(id = "rg-checkbox-0")
    private WebElement rememberMeCheckbox;

    @FindBy(xpath = "//div[not(contains(@class, 'login-page__padded-panel'))]/flexible-link/a/span[text()='Сбросить пароль'][1]")
    private WebElement forgotPasswordLink;

    @FindBy(xpath = "//*[text()='Некорректное имя пользователя или пароль.']")
    private WebElement errorMessage;

    public LoginPage(WebDriver driver) {
        super(driver);
    }


    public WebElement getUsernameField() {
        return usernameField;
    }

    public WebElement getPasswordField() {
        return passwordField;
    }

    public WebElement getLoginButton() {
        return loginButton;
    }

    public WebElement getErrorMessage() {
        return errorMessage;
    }


    public void enterUsername(String username) {
        usernameField.clear();
        usernameField.sendKeys(username);
    }

    public void enterPassword(String password) {
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void clickLoginButton() {
        loginButton.click();
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }

    public void setRememberMeCheckbox(boolean enabled) {
        if (rememberMeCheckbox.isSelected() != enabled) {
            rememberMeCheckbox.click();
        }
    }

    public void clickForgotPassword() {
        forgotPasswordLink.click();
    }

    public String getErrorMessageText() {
        return errorMessage.getText();
    }
}