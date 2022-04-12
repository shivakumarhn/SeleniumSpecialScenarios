package svg;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class Selenium4 {

    WebDriver driver;


    @BeforeClass
    public void setupChrome() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @BeforeMethod
    public void setupDriver() {
        driver = new ChromeDriver();
    }

    //#1) Capture screenshot of specific web element:
    @Test(enabled = false)
    public void screenShot() throws IOException {
        WebElement logo = driver.findElement(By.xpath("//div[@id=’divLogo’]//img"));
        File file = logo.getScreenshotAs(OutputType.FILE);
        File destFile = new File("logo.png");
        FileUtils.copyFile(file, destFile);
    }

    //#2) Open the new tab on the browser:
    @Test(enabled = false)
    public void newTab() {
        driver.get("https://www.google.com");
        driver.switchTo().newWindow(WindowType.TAB);
        driver.navigate().to("https://www.crmpro.com/");
    }

    //#3) Open a new window on the browser:
    @Test()
    public void newWindow() {
        driver.get("https://www.google.com");
        driver.switchTo().newWindow(WindowType.WINDOW);
        driver.navigate().to("https://www.crmpro.com/");
        Wait wait = new FluentWait(driver).withTimeout(Duration.ofSeconds(3));
    }

    @AfterMethod
    public void tear_Down() {
        driver.quit();
    }

    public static void main(String[] args) throws IOException {


    }

}
