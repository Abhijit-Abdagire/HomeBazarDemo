package com.homeBazarTestCases;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import com.homeBazar.Utilities.ReadConfig;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;

/*  
 * Author -       Abhijit Abdagire
 * Creation Date- 19-09-2022
 *  */
public class HomeBazarBaseClass {
	
	 static WebDriver driver;
	 ReadConfig readConfig = new ReadConfig();
	
	
	public String baseURL = readConfig.getURL();
	public String browser = readConfig.getBrowserName();
	
	public Logger log;
	
	@BeforeClass
	public void setUp() {
		switch(browser) {
		case "chrome" :
			System.setProperty("webdriver.chrome.driver", ".\\Drivers\\chromedriver.exe");
			driver = new ChromeDriver();
			break;
		case "firefox":
			System.setProperty("webdriver.gecko.driver", ".\\Drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		driver.get(baseURL);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	
	}

	public static void compareImages(String elePath,String expectedImg,String actualScreenshotImg) throws IOException {
		
		WebElement ele = driver.findElement(By.xpath(elePath));
	   BufferedImage expectImg = ImageIO.read(new File(System.getProperty("user.dir")+"\\Images/"+expectedImg));
		
		Screenshot scr = new AShot().coordsProvider(new WebDriverCoordsProvider()).takeScreenshot(driver, ele);
		
		BufferedImage scrToImg = scr.getImage();
		
		ImageIO.write(scrToImg,"png",new File (System.getProperty("user.dir")+"\\ActualScreenshotImages/"+actualScreenshotImg));
		
		ImageDiffer diff = new ImageDiffer();
		
		ImageDiff result = diff.makeDiff(scrToImg, expectImg);
		
		Assert.assertEquals(result.hasDiff(), false);
		
	
	}
	public static void takeScreenshot(String testName) {
		File source = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		try {
		File destination = new File(System.getProperty("user.dir")+"\\Screenshots/"+testName+".png");
		
			FileHandler.copy(source, destination);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	@AfterClass
	public static void tearDown() {
		driver.quit();
	}
}
