package pages.POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TaskPage extends BasePage{

    private By confirmDeleteButton = By.xpath("//span[text()='Удалить']");
    private By editTaskButton = By.xpath("//span[@aria-label='Редактировать задачу']");
    private By headingTaskTextArea = By.xpath("//textarea[@placeholder='Заголовок']");
    private By saveButton = By.xpath("//span[text()='Сохранить']");
    private By showMoreList = By.xpath("//span[@aria-label='Показать больше']/div/button");
    private By deleteTaskButton = By.xpath("//span[text()='Удалить задачу']");
    public TaskPage(WebDriver driver) {
        this.driver = driver;
    }
    public void deleteTask() {
        driver.findElement(showMoreList).click();
        driver.findElement(deleteTaskButton).click();
        driver.findElement(confirmDeleteButton).click();
    }
    public void editTask(String newHeading){
        driver.findElement(editTaskButton).click();
        driver.findElement(headingTaskTextArea).clear();
        driver.findElement(headingTaskTextArea).sendKeys(newHeading);
        driver.findElement(saveButton).click();
    }

}
