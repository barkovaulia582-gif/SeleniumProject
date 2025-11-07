package pages.POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TasksPage extends BasePage {
    private By createTaskButton = By.xpath("//button[@class='ring-ui-button_bc66 ring-ui-heightS_de02 " +
            "ring-ui-primary_ea44']");
    private By newTaskButton = By.xpath("//span[text()='Новая задача']");
    private By headingTask = By.xpath("//*[@placeholder='Заголовок']");
    private By descriptionTask = By.xpath("//*[@class='c_placeholder__bbc']");



    public TasksPage(WebDriver driver) {
        this.driver = driver;
    }

    public void createTask(String heading, String description) {
        driver.findElement(newTaskButton).click();
        driver.findElement(headingTask).sendKeys(heading);
        driver.findElement(descriptionTask).sendKeys(description);
        driver.findElement(createTaskButton).click();
    }

    public void lookTestTask(String heading) {
        By lookTestTaskButton = By.xpath("//span/span/a[contains(@href, '" + heading + "')]");
        driver.findElement(lookTestTaskButton).click();
    }
}

