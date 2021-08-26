package week2.Day2Assignment3;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Day2Assignment3RedBus {

	public ChromeDriver configureDriver() {
		
		// Below are standard steps to be included (except the URL)

		// Step 0-01: Download and set the path
		WebDriverManager.chromedriver().setup();
		// Step 0-02: Launch the Chrome browser
		ChromeDriver driver = new ChromeDriver();
		// Step 0-03: Load the URL
		driver.get("https://www.redbus.in/");
		// Step 0-04: Maximize the window
		driver.manage().window().maximize();
		// Step 0-05: waits for 20 secs if the element is not in the DOM
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		return driver;
	}
	
	public static void main(String[] args) throws InterruptedException {
		
		// Instantiate the RebBus class
		Day2Assignment3RedBus myEntryObj = new Day2Assignment3RedBus();
				
		// Step 0: Configure the Driver
		ChromeDriver mainDriver = myEntryObj.configureDriver();
		
		// Step 1: Enter search criteria in 1st screen, and search for buses
		myEntryObj.enterSearchCriteria(mainDriver);
		
		// Step 2: After the results page is loaded, close the 2 pop up screens that get overlaid on the search results
		myEntryObj.closePopupScreens(mainDriver);
		
		// Step 3: Scroll up the screen - till "Filters" so that the web elements can be captured
		WebElement elemFilters = mainDriver.findElement(By.xpath("//div[text()='FILTERS']"));
		myEntryObj.scrollUpScreen(mainDriver, elemFilters);
		
		// Step 4: Capture the Web Elements for Sleeper and AC checkboxes
		WebElement chkbxSleeper = mainDriver.findElement(By.xpath("(//label[@for='bt_SLEEPER'])[1]"));
		WebElement chkbxAC = mainDriver.findElement(By.xpath("//input[@id='bt_AC']/following-sibling::label[1]"));
		WebElement chkbxNonAC = mainDriver.findElement(By.xpath("//input[@id='bt_NONAC']/following-sibling::label[1]"));
		
		// Step 3: Get & Print the total search results for the initial search criteria - ALL
		myEntryObj.printNbrBusesInSearch(mainDriver, "All");

		// Step 4: Select the Sleeper bus
		chkbxSleeper.click();
		System.out.println("Sleeper bus Selected");
		Thread.sleep(1000);
		
		// Step 5: Get & Print the total search results for the initial search criteria - SLEEPER
		myEntryObj.printNbrBusesInSearch(mainDriver, "Sleeper");
		
		// Step 6: De-Select the Sleeper bus, and Select A/C buses
		chkbxSleeper.click();
		System.out.println("Sleeper bus De-selected");
		Thread.sleep(5000);
		
		// Step 7: Scroll up the screen - in order to select A/c
		myEntryObj.scrollUpScreen(mainDriver, elemFilters);
		
		// Step 8: Select the A/c buses
		chkbxAC.click();
		System.out.println("A/C bus Selected");
		Thread.sleep(2000);
		
		// Step 9: Get & Print the total search results for the initial search criteria - A/C
		myEntryObj.printNbrBusesInSearch(mainDriver, "A/c");
		
		// Step 10: Scroll up the screen - in order to select Non-A/c
		myEntryObj.scrollUpScreen(mainDriver, elemFilters);
		
		// Step 8: Select the Non-A/c buses
		chkbxNonAC.click();
		System.out.println("NON A/C bus - Also Selected");
		Thread.sleep(2000);
		
		// Step 7: Get & Print the total search results for the initial search criteria - [A/c + Non-A/c]
		myEntryObj.printNbrBusesInSearch(mainDriver, "A/c + Non-A/c");
		
	}


private void enterSearchCriteria(ChromeDriver srchDriver) throws InterruptedException {
		
		// Step 1-01: Enter Source City
		WebElement citySrc = srchDriver.findElement(By.xpath("//input[@id='src']"));
		citySrc.clear();
		citySrc.sendKeys("Chennai");
		Thread.sleep(1000);
		citySrc.sendKeys(Keys.ENTER);
		
		// Step 1-02: Enter Destination City
		WebElement cityDest = srchDriver.findElement(By.xpath("//input[@id='dest']"));
		cityDest.clear();
		cityDest.sendKeys("Bengaluru");
		Thread.sleep(1000);
		cityDest.sendKeys(Keys.ENTER);

		// Step 1-03: Click on Date Selector (Calendar) for onward travel date
		srchDriver.findElement(By.xpath("//input[@id='onward_cal']")).click();
		System.out.println("Clicked the date calendar for selecting the onward travel date");
		
		// Step 1-04: Find the Web element for the date picker (calendar view), and click the date that you want
		//    here I have selected the header, and navigated to the 6th row (week), and selected the 5th day in that week
		// Alternate method given below: to select the first available Wednesday date
		//        mainDriver.findElement(By.xpath("//td[@class='we day']")).click();
		srchDriver.findElement(By.xpath("//tr[@class='rb-monthHeader']/following-sibling::tr[6]/td[6]")).click();
		System.out.println("Selected the onward travel date");
		Thread.sleep(1000);
		
		// Step 1-05: Click "Search Buses"
		srchDriver.findElement(By.xpath("//button[@id='search_btn']")).click();
		System.out.println("Clicked the 'Search Buses' Link");
		Thread.sleep(5000);
		
	}

	private void closePopupScreens(ChromeDriver closePopDriver) throws InterruptedException {
		
		// Step 2-01: Close the 1st Pop-Up window
		closePopDriver.findElement(By.xpath("//i[@class='icon icon-close c-fs']")).click();
		System.out.println("Closed the 1st Pop-up window");
		Thread.sleep(3000);

		// Step 2-02: Close the 2nd Pop-Up window
		closePopDriver.findElement(By.xpath("//i[@class='icon icon-close']")).click();
		System.out.println("Closed the 2nd Pop-up window");
		Thread.sleep(3000);
		
	}
	
	private void scrollUpScreen(ChromeDriver scrollDriver, WebElement elemFilters) throws InterruptedException {
		
		scrollDriver.executeScript("arguments[0].scrollIntoView();", elemFilters);
		System.out.println("Scrolled up the screen till 'Filters'");
		Thread.sleep(2000);
		
	}
	
	private void printNbrBusesInSearch(ChromeDriver mainDriver, String srchType) throws InterruptedException {
		
		String nbrBusesResult = mainDriver.findElement(By.xpath("//span[@class='f-bold busFound']")).getText();
		System.out.println("# of buses - " + srchType + " Buses : " + nbrBusesResult);
		Thread.sleep(1000);
		
	}


}
