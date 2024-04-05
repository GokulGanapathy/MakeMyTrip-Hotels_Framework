package Tests;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import PageObjects.HomePage;
import PageObjects.SearchResult;
import PageObjects.popUpAd;
import TestComponents.BaseTest;
import TestComponents.Retry;

public class HotelsListTest extends BaseTest{
	
	List<Integer> prices;
	
	@Test(groups="MakeMyTripAutomationTest", retryAnalyzer=Retry.class)
	public void hotelsList() throws InterruptedException {
		
		WebDriver driver = invokeTest("Chrome","https://www.makemytrip.com/");
		
		popUpAd popupAd = new popUpAd(driver);
		popupAd.closeAd();
		
		HomePage homePage = new HomePage(driver);
		homePage.chooseHotelsOption();
		homePage.selectCity("Bangalore");
		homePage.CheckIn("April", "20");
		homePage.CheckOut("April", "25");
		homePage.roomAndGuestSelection("2", "5", "1","14");
		SearchResult searchResult = homePage.search();
		
		searchResult.totalPropertiesCount();
		searchResult.getHotelsNameAndPrice();
		String cheapestHotel= searchResult.getCheapestHotel();
		System.out.println(cheapestHotel);
		
		prices=searchResult.getListOfPrices();
		
	}
	
	@Test (groups= {"listScoreTest"}, dependsOnMethods= {"hotelsList"},retryAnalyzer=Retry.class)
	public void javaTest() {
		int score=0,k=3;
		List <Integer> listA = new ArrayList<>();
		for(int i=0;i<prices.size();++i) {
			listA.add(prices.get(i)%10);
		}
		
		Collections.sort(listA);
		System.out.println(listA);
		
		for(int i=0;i<k;++i) {
			int a = Collections.max(listA);
			listA.remove(listA.indexOf(a));
			
			int b = Collections.max(listA);
			listA.remove(listA.indexOf(b));
			
			score = score +(a&b);
			System.out.print(score);
			System.out.println(listA);
			
		}
		System.out.println("The resultant of max Score from the list after "+k+" Operations is : "+score);
	}
}
