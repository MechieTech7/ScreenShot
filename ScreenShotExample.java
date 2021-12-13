package Task;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ScreenShotExample {


    public void screenShot() throws IOException, AWTException {

        WebDriver driver = null;
        FileInputStream inputStream = new FileInputStream("C:\\Users\\lokesh.chandramurthy\\IdeaProjects\\BasicTest\\src\\main\\java\\SeleniumBasics\\config.properties");
        Properties properties = new Properties();
        properties.load(inputStream);
        String browser = properties.getProperty("browser");

        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();

        } else if (browser.equalsIgnoreCase("Firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }

        driver.get("https://www.google.co.in/");
        driver.findElement(By.name("q")).sendKeys("Dhoni" + Keys.ENTER);

        //It Returns specific portion
        TakesScreenshot screenShot = (TakesScreenshot) driver;
        File sourceFile =  screenShot.getScreenshotAs(OutputType.FILE);
        File pathLocation = new File("C:\\Users\\lokesh.chandramurthy\\IdeaProjects\\BasicTestScreenshot.jpeg");
        FileHandler.copy(sourceFile,pathLocation);

        //Robot class returns entire screen
       /* Robot robot = new Robot();
        Dimension fullScreen = Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle rectangle = new Rectangle(fullScreen);
        BufferedImage image = robot.createScreenCapture(rectangle);
        File pathLocation = new File("C:\\Users\\lokesh.chandramurthy\\IdeaProjects\\BasicTestRobot.jpeg");
        ImageIO.write(image,"png",pathLocation); */




        driver.close();
    }

    public static void main(String[] args) throws IOException, AWTException {
        ScreenShotExample callMethod = new ScreenShotExample();
        callMethod.screenShot();
    }
}

