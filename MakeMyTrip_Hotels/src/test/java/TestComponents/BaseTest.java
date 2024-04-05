package TestComponents;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import PageObjects.HomePage;
import PageObjects.popUpAd;

public class BaseTest {
	
	WebDriver driver;
	
	public void instantiateBrowser(String browser) {
		
		if(browser.equalsIgnoreCase("Chrome")) {
			driver = new ChromeDriver();
		}
		else if(browser.equalsIgnoreCase("FireFox")) {
			driver = new FirefoxDriver();
		}
		else if(browser.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();
		//return driver;
	}
	
	public WebDriver invokeTest(String browser,String url) {
		instantiateBrowser(browser);
		driver.get(url);
		return driver;
	}
	
	public String getScreenShot(String testCaseName,WebDriver driver) throws IOException {
		
		TakesScreenshot ts = (TakesScreenshot)driver;
		File screenshot = ts.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshot, new File(System.getProperty("user.dir")+"//Reports//"+testCaseName+".jpg"));
		return System.getProperty("user.dir")+"//Reports//"+testCaseName+".jpg";
	}
	
	@AfterMethod(alwaysRun=true)
	public void killSession() {
		driver.quit();
	}
	
	
	
	/*public static void main(String args[]) {
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
		
		driver.get("https://www.makemytrip.com/");
		
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.cssSelector("iframe[id*='webklipper']")));
		
		//driver.switchTo().frame(driver.findElement(By.cssSelector("iframe[id*='webklipper']")));
		
		js.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("a[class='close'] i")));
		
		driver.switchTo().defaultContent();
		
	}*/

}
