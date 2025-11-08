package pageTests;

import helpers.PropertyReader;
import org.junit.jupiter.api.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.TaskPage;


import static org.junit.jupiter.api.Assertions.*;

public class TasksPageTest extends BaseTest {

    private String heading;
    private String newHeading;
    private String description;
    private String tasksPageUrl;

    @BeforeEach
    public void setUp() {
        tasksPageUrl = PropertyReader.getProperty("tasksPageUrl");
        heading = PropertyReader.getProperty("headingForTask");
        description = PropertyReader.getProperty("descriptionForTask");
        newHeading = PropertyReader.getProperty("headingForEditTask");

        driver.get(tasksPageUrl);
    }

    @DisplayName("Test with correct task creation")
    @Test
    public void testTaskCreation() {

        tasksPage.createTask(heading, description);


        wait.until(ExpectedConditions.elementToBeClickable(
                org.openqa.selenium.By.xpath("//p[@class='c_placeholder__bbc']")
        ));


        driver.get(tasksPageUrl);


        tasksPage.openTaskByHeading(heading);


        assertTrue(driver.getCurrentUrl().contains(heading),
                "URL должен содержать заголовок задачи");
    }

    @DisplayName("Edit task")
    @Test
    public void testEditTask() {
        tasksPage.createTask(heading, description);
        tasksPage.openTaskByHeading(heading);
        TaskPage taskPage = new TaskPage(driver);
        taskPage.editTask(newHeading);

        assertTrue(driver.getCurrentUrl().contains(newHeading),
                "URL должен содержать новый заголовок задачи после редактирования");
    }
}