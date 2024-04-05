package PageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class popUpAd {
	
	WebDriver driver;
	WebDriverWait wait;
	JavascriptExecutor js;
	
	public popUpAd(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		js =(JavascriptExecutor)driver;
		wait = new WebDriverWait(driver,Duration.ofSeconds(20));
	}
	
	By adFrame = By.cssSelector("iframe[id*='webklipper']");
	
	@FindBy(css="iframe[id*='webklipper']")
	WebElement adFrameEle;
	
	@FindBy(css="a[class='close'] i")
	WebElement closeIcon;
	
	public void closeAd() {
		
		//try {
			wait.until(ExpectedConditions.visibilityOf(adFrameEle));
			driver.switchTo().frame(adFrameEle);
			//wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(adFrame));
			js.executeScript("arguments[0].click();", closeIcon);
			driver.switchTo().defaultContent();
		/*}
		catch(Exception E) {
		}*/
	}

}
