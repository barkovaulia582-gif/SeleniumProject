package pages.pageTests;

import pages.POM.LoginPagePOM;
import pages.POM.TaskPage;
import pages.POM.TasksPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.net.MalformedURLException;
import java.time.Duration;
import java.util.Properties;



import static pages.UtilsForLoginPageTest.Utils.*;

public class TasksPageTest {

    private static String login;
    private static String password;
    private static String heading;
    private static String newHeading;
    private static String description;
    private static String seleniumHubUrl;
    private static String loginPageUrl;
    private static String tasksPageUrl;
    private static String testUserDashBoardUrl;

    private WebDriver driver;
    WebDriverWait wait;

    @BeforeAll
    public static void beforeAll() throws IOException {
        Properties properties = loadProperties();

        heading = properties.getProperty("headingForTask");
        description = properties.getProperty("descriptionForTask");
        seleniumHubUrl = properties.getProperty("seleniumHubUrl");
        loginPageUrl = properties.getProperty("loginPageUrl");
        login = properties.getProperty("correctLogin");
        password = properties.getProperty("correctPassword");
        tasksPageUrl = properties.getProperty("tasksPageUrl");
        testUserDashBoardUrl = properties.getProperty("testUserDashBoardUrl");
        newHeading = properties.getProperty("headingForEditTask");
    }

    @BeforeEach
    public void setUp() throws MalformedURLException {
        driver = setUpRemoteWebDriver(seleniumHubUrl, "chrome");
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.get(loginPageUrl);

        LoginPagePOM loginPage = new LoginPagePOM(driver);
        loginPage.login(login, password);

        wait.until(ExpectedConditions.urlToBe(testUserDashBoardUrl));
    }

    @DisplayName("Test with correct task creation")
    @Test
    public void testTaskCreation() {
        String testName = "testTaskCreation";

        driver.get(tasksPageUrl);

        TasksPage tasksPage = new TasksPage(driver);
        tasksPage.createTask(heading, description);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[@class=" +
                "'c_placeholder__bbc']")));
        driver.get(tasksPageUrl);

        tasksPage.lookTestTask(heading);
        Assertions.assertTrue(driver.getCurrentUrl().contains(heading));

        if (!driver.getCurrentUrl().contains(heading)) {
            takeScreenShot(testName, driver);
        }
    }

    @DisplayName("Edit task")
    @Test
    public void testEditTask() {
        String testName = "testEditTask";

        driver.get(tasksPageUrl);

        TasksPage tasksPage = new TasksPage(driver);
        tasksPage.createTask(heading, description);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[@class=" +
                "'c_placeholder__bbc']")));
        driver.get(tasksPageUrl);

        tasksPage.lookTestTask(heading);
        TaskPage taskPage = new TaskPage(driver);
        taskPage.editTask(newHeading);
        Assertions.assertTrue(driver.getCurrentUrl().contains(newHeading));

        if (!driver.getCurrentUrl().contains(heading)) {
            takeScreenShot(testName, driver);
        }
    }

    @AfterEach
    public void tearDown() {
        TaskPage taskPage = new TaskPage(driver);
        taskPage.deleteTask();
        driver.quit();
    }
}
