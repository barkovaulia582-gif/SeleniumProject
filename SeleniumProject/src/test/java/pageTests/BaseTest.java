package pageTests;

import helpers.PropertyReader;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import helpers.Utils;
import pages.LoginPage;
import pages.TasksPage;

import java.time.Duration;


public class BaseTest {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected String baseUrl;
    protected String seleniumHubUrl;
    protected String login;
    protected String password;
    protected LoginPage loginPage;
    protected TasksPage tasksPage;

    @BeforeEach
    public void baseSetUp() {
        loadProperties();
        initializeDriver();
        initializePages();
        doAuth();
    }

    private void loadProperties() {
        baseUrl = PropertyReader.getProperty("baseUrl");
        seleniumHubUrl = PropertyReader.getProperty("seleniumHubUrl");
        login = PropertyReader.getProperty("correctLogin");
        password = PropertyReader.getProperty("correctPassword");
    }

    private void initializeDriver() {
        try {
            driver = Utils.setUpRemoteWebDriver(seleniumHubUrl, "chrome");
            wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
            driver.manage().window().maximize();
        } catch (Exception e) {
            throw new RuntimeException("Не удалось инициализировать WebDriver", e);
        }
    }
    private void initializePages() {
        loginPage = new LoginPage(driver);
        tasksPage = new TasksPage(driver);
    }

    private void doAuth() {
        driver.get(baseUrl);
    }
    @AfterEach
    public void baseTearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
    protected void takeScreenshot(String testName) {
        Utils.takeScreenShot(testName, driver);
    }
}
