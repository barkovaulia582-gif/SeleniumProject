package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TasksPage extends BasePage {

    @FindBy(xpath = "//span[text()='Новая задача']")
    private WebElement newTaskButton;

    @FindBy(xpath = "//*[@placeholder='Заголовок']")
    private WebElement headingTaskField;

    @FindBy(xpath = "//*[@class='c_placeholder__bbc']")
    private WebElement descriptionTaskField;

    @FindBy(xpath = "//button[@class='ring-ui-button_bc66 ring-ui-heightS_de02 ring-ui-primary_ea44']")
    private WebElement createTaskButton;

    public TasksPage(WebDriver driver) {
        super(driver);
    }


    public void clickNewTaskButton() {
        newTaskButton.click();
    }

    public void enterTaskHeading(String heading) {
        headingTaskField.clear();
        headingTaskField.sendKeys(heading);
    }

    public void enterTaskDescription(String description) {
        descriptionTaskField.clear();
        descriptionTaskField.sendKeys(description);
    }

    public void clickCreateTaskButton() {
        createTaskButton.click();
    }

    public void createTask(String heading, String description) {
        clickNewTaskButton();
        enterTaskHeading(heading);
        enterTaskDescription(description);
        clickCreateTaskButton();
    }

    public void openTaskByHeading(String heading) {
        String xpath = "//span/span/a[contains(@href, '" + heading + "')]";
        driver.findElement(org.openqa.selenium.By.xpath(xpath)).click();
    }
}
