package svg;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import javax.swing.*;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SvgElements {

    public static void main(String[] args) throws ParseException {

        WebDriverManager.chromedriver().setup();

        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().window().fullscreen();

        //New cases and deaths
        driver.get("https://www.google.com/search?q=covid+cas" +
                "es+in+india&oq=covid+&aqs=chrome.2.69i57j0i131i433i457i512j0i131i433i51" +
                "2j0i402l2j0i131i433i512j0i433i512j0i131i433i512l2j0i433i512.2817j0j4&sourceid=chrome&ie=UTF-8");

        List<WebElement> list = driver.findElements(By.xpath("//*[name()='svg' and @class='uch-psvg']/" +
                "*[local-name()='g']/*[local-name()='rect']"));

        Actions ac = new Actions(driver);
//        for (WebElement element : list) {
//            ac.moveToElement(element).perform();
//            String covidCases = driver.findElement(By.xpath("//div[@class='ExnoTd']")).getText();
//            System.out.println(covidCases);
//        }


        //Tests
        WebElement element = driver.findElement(By.xpath("(//*[name()='svg' and @class='uch-psvg'])[2]"));

        int xOffset = ((element.getSize().getWidth()) / 2) - element.getSize().getWidth();
        int yOffset = ((element.getSize().getHeight()) / 2) - element.getSize().getHeight();

        String startDate = "20-03-2020";
        SimpleDateFormat format = new SimpleDateFormat("DD-MM-YYYY");
        Date d1 = format.parse(startDate);
        Date d2 = new Date();
        long daysDifference = TimeUnit.MILLISECONDS.toDays(d2.getTime() - d1.getTime());

        System.out.println(xOffset + " : " + yOffset);

        for (int i = 0; i < daysDifference; i++) {
            ac.moveToElement(element, xOffset + i, yOffset).perform();
            String s = driver.findElement(By.xpath("//table[@class='swWWne']/tbody")).getText();
            System.out.println(s);
        }

        driver.quit();
    }
}
