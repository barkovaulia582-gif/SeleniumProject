package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TaskPage extends BasePage {

    @FindBy(xpath = "//span[@aria-label='Показать больше']/div/button")
    private WebElement showMoreButton;

    @FindBy(xpath = "//span[text()='Удалить задачу']")
    private WebElement deleteTaskButton;

    @FindBy(xpath = "//span[text()='Удалить']")
    private WebElement confirmDeleteButton;

    @FindBy(xpath = "//span[@aria-label='Редактировать задачу']")
    private WebElement editTaskButton;

    @FindBy(xpath = "//textarea[@placeholder='Заголовок']")
    private WebElement headingTextArea;

    @FindBy(xpath = "//span[text()='Сохранить']")
    private WebElement saveButton;

    public TaskPage(WebDriver driver) {
        super(driver);
    }

    public void deleteTask() {
        showMoreButton.click();
        deleteTaskButton.click();
        confirmDeleteButton.click();
    }

    public void editTask(String newHeading) {
        editTaskButton.click();
        headingTextArea.clear();
        headingTextArea.sendKeys(newHeading);
        saveButton.click();
    }
}