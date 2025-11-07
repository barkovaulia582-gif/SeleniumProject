package pageTests;

import base.PropertyReader;
import pages.POM.TaskPage;
import pages.POM.TasksPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.BaseTest;




import static helpers.Utils.*;

public class TasksPageTest extends BaseTest {


    private static String heading;
    private static String newHeading;
    private static String description;
    private static String tasksPageUrl;


    private WebDriver driver;
    WebDriverWait wait;

    public TasksPageTest(WebDriver driver) {
        this.driver = driver;
    }

    @BeforeEach
    @Override
    public void baseSetUp() {
        tasksPageUrl = PropertyReader.getProperty("tasksPageUrl");
        heading = PropertyReader.getProperty("headingForTask");
        description = PropertyReader.getProperty("descriptionForTask");
        newHeading = PropertyReader.getProperty("headingForEditTask");
        super.baseSetUp();
        driver.get(tasksPageUrl);
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


}
