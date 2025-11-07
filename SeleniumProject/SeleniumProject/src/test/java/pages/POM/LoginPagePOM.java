package pages.POM;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPagePOM extends  BasePage {

    private final By checkBox = By.xpath("//*[@id='rg-checkbox-0']");
    private final By forgotPasswordLink = By.xpath("//div[not(contains(@class, " +
            "'login-page__padded-panel'))]/flexible-link/a/span[text()='Сбросить пароль'][1]");

    public LoginPagePOM(WebDriver driver) {
        this.driver = driver;
    }

    public void setCheckBox() {
        if(!driver.findElement(checkBox).isEnabled()) {
            driver.findElement(checkBox).click();
        }
    }

    public void uncheckCheckBox() {
        if(driver.findElement(checkBox).isEnabled()) {
            driver.findElement(checkBox).click();
        }
    }

    public void forgotPassword() {
        driver.findElement(forgotPasswordLink).click();
    }
}
