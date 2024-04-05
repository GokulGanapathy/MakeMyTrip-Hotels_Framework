package PageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchResult {
	
	WebDriver driver;
	
	public SearchResult(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="#seoH1DontRemoveContainer h1")
	WebElement hotelsCountShown;
	
	@FindBy(css=".listingRowOuter")
	List<WebElement> hotelsList;
	
	By hotelName=By.cssSelector("#hlistpg_hotel_name span");
	
	By price=By.id("hlistpg_hotel_shown_price");
	
	public void totalPropertiesCount() {
		System.out.println(hotelsCountShown.getText());
		
		System.out.println("Number of Hotels from Search Result : "+hotelsList.size());
	}
	
	List<String> hotelsName = new ArrayList<>();
	List<String> hotelsPrice = new ArrayList<>();
	List<Integer> hotelsPriceInt = new ArrayList<>();
	public void getHotelsNameAndPrice() {
		
		for(int i=0;i<hotelsList.size();++i) {
			hotelsName.add(hotelsList.get(i).findElement(hotelName).getText());
			hotelsPrice.add(hotelsList.get(i).findElement(price).getText());
			String pri = hotelsList.get(i).findElement(price).getText();//.split(" ")[1];
			hotelsPriceInt.add(convertPriceStringToInt(pri));
		}
		System.out.println(hotelsName);
		System.out.println(hotelsPrice);
		System.out.println(hotelsPriceInt);
	}
	
	public String getCheapestHotel() {
		int min = hotelsPriceInt.get(0);
		int minIdx=0;
		for(int i=0;i<hotelsPrice.size();++i) {
			if(hotelsPriceInt.get(i)<=min) {
				min=hotelsPriceInt.get(i);
				minIdx=i;
			}
		}
		return "The Cheapest Hotel is : "+hotelsName.get(minIdx)+" and its Price is :"+hotelsPrice.get(minIdx);
	}
	
	public int convertPriceStringToInt(String pri) {
		int priceInt=0;
		for(int i=0;i<pri.length();++i) {
			if(Character.isDigit(pri.charAt(i))) {
				priceInt = (priceInt*10)+Integer.parseInt(Character.toString(pri.charAt(i)));
			}
		}
		return priceInt;
	}
	
	public List<Integer> getListOfPrices(){
		return hotelsPriceInt;
	}
}
