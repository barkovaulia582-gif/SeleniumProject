package helpers;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

public class Utils {
    public static WebDriver setUpRemoteWebDriver(String url, String browser) throws MalformedURLException {
        String correctedUrl = url;
        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            correctedUrl = "http://" + url;
            System.out.println("Автокоррекция URL: " + correctedUrl);
        }

        URL hubUrl = new URL(correctedUrl);
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setBrowserName(browser);

        return new RemoteWebDriver(hubUrl, caps);
    }

    public static void takeScreenShot(String fileName, WebDriver driver) {
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File srcFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

        try {

            File screenshotsDir = new File("src/test/resources/Screenshots");
            if (!screenshotsDir.exists()) {
                screenshotsDir.mkdirs();
            }

            File destFile = new File(screenshotsDir, fileName + ".png");
            FileUtils.copyFile(srcFile, destFile);

            System.out.println("Скриншот сохранен: " + destFile.getAbsolutePath());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Properties loadProperties() throws IOException {
        File file = new File("src/test/resources/test.properties");
        Properties prop = new Properties();
        prop.load(new FileReader(file));
        return prop;
    }
}