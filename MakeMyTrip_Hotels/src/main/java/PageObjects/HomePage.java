package PageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
	
	WebDriver driver;
	JavascriptExecutor js;
	WebDriverWait wait;
	
	public HomePage(WebDriver driver) {
		this.driver=driver;
		js =(JavascriptExecutor)driver;
		wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="li[data-cy='menu_Hotels'] a")
	WebElement hotel;
	
	@FindBy(css="#city")
	WebElement city;
	
	@FindBy(css="div[class*='searchInput'] input")
	WebElement cityInput;
	
	@FindBy(xpath="//li[@role='option']")
	List<WebElement> suggestions;
	
	By suggestionCity = By.cssSelector(".sr_city");
	
	@FindBy(css=".DayPicker-Month:nth-child(1)")
	WebElement calandar;
	
	@FindBy(xpath="//div[contains(@class,'DayPicker-Day')]")
	List<WebElement> calandarDays;
	
	@FindBy(css=".DayPicker-NavButton--next")
	WebElement nextMonthBtn;
	
	By calandarMonYr = By.cssSelector(".DayPicker-Caption div");
	
	@FindBy(css="span[data-testid='room_count']")
	WebElement rooms;
	
	@FindBy(css="li[data-cy*='GuestSelect']")
	List<WebElement> dropDown;
	
	@FindBy(css="span[data-testid='adult_count']")
	WebElement adultCount;
	
	@FindBy(css="span[data-testid='children_count']")
	WebElement childrenCount;
	
	@FindBy(css="div[class*='slctAge'] div div[class*='DrpDown']")
	List<WebElement> childAgeDPs;
	
	@FindBy(xpath="//button[text()='Apply']")
	WebElement applyBtn;
	
	@FindBy(xpath="//button[text()='Search']")
	WebElement search;
	
	
	
	public void chooseHotelsOption() {
		hotel.click();
	}
	
	public void selectCity(String cityName) throws InterruptedException {
		city.click();
		cityInput.sendKeys(cityName);
		Thread.sleep(5000);
		for(int i=0;i<suggestions.size();++i) {
			String city = suggestions.get(i).findElement(suggestionCity).getText();
			if(city.contains(cityName)) {
				suggestions.get(i).findElement(suggestionCity).click();
				break;
			}
		}
	}
	
	public void CheckIn(String month,String Date) {
		CalandarOperation(month,Date);
	}
	
	public void CheckOut(String month,String Date) {
		CalandarOperation(month,Date);
	}
	
	public void roomAndGuestSelection(String room,String adults,String Child,String childAge) {
		rooms.click();
		dropDownSelect(room);
		adultCount.click();
		dropDownSelect(adults);
		childrenCount.click();
		if(!Child.equals(null)) {
			dropDownSelect(Child);
			if(!applyBtn.isEnabled()) {
				for(int i=0;i<childAgeDPs.size();++i) {
					childAgeDPs.get(i).click();
					if(Integer.parseInt(childAge)>=18) {
						System.err.println("Child Age should be less than 18");
					}
					else {
						dropDownSelect(childAge);
					}
				}
			}
			js.executeScript("arguments[0].click()", applyBtn);
		}
	}
	
	public SearchResult search() {
		search.click();
		SearchResult searchResult = new SearchResult(driver);
		return searchResult;
	}
	
	public void CalandarOperation(String month,String Date) {
		while(!calandar.findElement(calandarMonYr).getText().contains(month)) {
			nextMonthBtn.click();
		}
		for(int i=0;i<calandarDays.size();++i) {
			if(calandarDays.get(i).getText().contains(Date)) {
				calandarDays.get(i).click();
				break;
			}
		}
	}
	
	public void dropDownSelect(String count) {
		for(int i=0;i<dropDown.size();++i) {
			if(dropDown.get(i).getText().contains(count)) {
				js.executeScript("arguments[0].click();", dropDown.get(i));
				break;
			}
		}
	}
}
