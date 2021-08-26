package week2.Day2Assignment2;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Day2Assignment2DeleteLead {

	public ChromeDriver ConfigureDriver() {
		
		// Below are standard steps to be included (except the URL)

		// Step 0-01: Download and set the path
		WebDriverManager.chromedriver().setup();
		// Step 0-02: Launch the Chrome browser
		ChromeDriver driver = new ChromeDriver();
		// Step 0-03: Load the URL
		driver.get("http://leaftaps.com/opentaps/control/main");
		// Step 0-04: Maximize the window
		driver.manage().window().maximize();
		// Step 0-05: waits for 10 secs if the element is not in the DOM
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		return driver;
	}
	
	public void EnterFieldsInLoginScreen(ChromeDriver loginDriver) {					// LOGIN Screen
		
		// Step 1-01: Locate the Username and enter the username as demosalesmanager
		loginDriver.findElement(By.xpath("//input[@id='username']")).sendKeys("demosalesmanager");
		
		// Step 1-02: Locate the password(webelement) and enter the password as crmsfa
		loginDriver.findElement(By.xpath("//input[@id='password']")).sendKeys("crmsfa");

		// Step 1-03: Locate the Login button and click on it
		loginDriver.findElement(By.xpath("//input[@class='decorativeSubmit']")).click();

	}
	
	public void searchByPhoneNumber(ChromeDriver fndLeadDriver) {
		
		// Step 6-01: Enter Phone Country Code
		WebElement txtPhCtryCode = fndLeadDriver.findElement(By.xpath("//input[@name='phoneCountryCode']"));
		txtPhCtryCode.clear();
		txtPhCtryCode.sendKeys("+1");

		// Step 6-02: Enter Phone Area Code
		fndLeadDriver.findElement(By.xpath("//input[@name='phoneAreaCode']")).sendKeys("609");
		
		// Step 6-03: Enter the Phone number
		fndLeadDriver.findElement(By.xpath("//input[@name='phoneNumber']")).sendKeys("523-6213");
		
		// Step 6-03: Click on Find Leads Button
		fndLeadDriver.findElement(By.xpath("//button[text()='Find Leads']")).click();

	}
	
	public String selectFirstLead(ChromeDriver fndLeadDriver) {
		
		// Step 7-01: Find the 1st result in the page, and get the Lead ID
		WebElement elementFirstLeadID = fndLeadDriver.findElement(By.xpath("(//div[@class='x-grid3-cell-inner x-grid3-col-partyId'])[1]/a"));
		String firstLeadID = elementFirstLeadID.getText();
		System.out.println("Lead ID for the 1st search result : " + firstLeadID);

		// Step 7-01: Click the Lead ID for the 1st search result 
		WebElement elementFirstLeadID1 = fndLeadDriver.findElement(By.xpath("(//div[@class='x-grid3-cell-inner x-grid3-col-partyId'])[1]/a"));
		elementFirstLeadID1.click();
		System.out.println("Clicked the first lead ID");
		
		return firstLeadID;

	}
	

	public void findLeadByID(ChromeDriver fndLeadDriver, String leadID) {
		
		// Step 11-01: Search for the Lead ID that was deleted
		fndLeadDriver.findElement(By.xpath("//input[@name='id']")).sendKeys(leadID);

		// Step 11-02: Click on Find Leads Button
		fndLeadDriver.findElement(By.xpath("//button[text()='Find Leads']")).click();
		
		// Step 11-03: Check the message displayed
		String msgInTable = fndLeadDriver.findElement(By.xpath("//div[@class='x-toolbar x-small-editor']/div")).getText();
				System.out.println("The message displayed in the table is -->  " + msgInTable);
		
		if (msgInTable == "No records to display") {
			System.out.println("The Record for the Lead ID " + leadID + " has been deleted successfully");
		} else {
			System.out.println("Oops!!! Something has gone wrong!!!!!");
		}
	}
	
	
	public static void main(String[] args) {

		Day2Assignment2DeleteLead myEntryObj = new Day2Assignment2DeleteLead();
		
		
		// Step 0: Configure the Driver
		ChromeDriver mainDriver = myEntryObj.ConfigureDriver();

		// Step 1: Screen # 1 ---> Initial Login screen
		myEntryObj.EnterFieldsInLoginScreen(mainDriver);

		// Step 2: Screen # 2 ---> Click on "CRM/SFA" --> This will take to "my Home" screen
		mainDriver.findElement(By.xpath("//div[@id='button']/div")).click();

		// Step 3: Screen # 3 ---> "myHome" screen --> Click on Leads
		mainDriver.findElement(By.xpath("//a[text()='Leads']")).click();
		
		// Step 4: Screen # 4 ---> "Leads" screen --> Click on "Find Leads"
		mainDriver.findElement(By.xpath("//a[text()='Find Leads']")).click();

		// Step 5: Screen # 4 ---> Click on "Phone" --> Click on "Find Leads"
		mainDriver.findElement(By.xpath("//span[text()='Phone']")).click();

		// Step 6: Screen # 4 ---> Search for Lead, by Phone Number
		myEntryObj.searchByPhoneNumber(mainDriver);
		
		// Step 7: Check if the Phone number search brings up 1 or more results
		Boolean isPresent = mainDriver.findElements(By.xpath("//div[@class='x-grid3-cell-inner x-grid3-col-partyId']")).size() > 0;
		if (isPresent) {
			
			// Step 8: Screen # 4 ---> Select the 1st result, and capture the Lead ID  --> Takes to "View Lead" screen
			String firstLeadIDinSearch = myEntryObj.selectFirstLead(mainDriver);
			System.out.println("Selected the element, and returned the value --> " + firstLeadIDinSearch);

			// Step 9: Screen # 5 ---> "View Lead" Screen --> Delete the lead
			System.out.println("Now the page title is --> " + mainDriver.getTitle());
			mainDriver.findElement(By.xpath("//a[@class='subMenuButtonDangerous']")).click();
			
			// Step 10: Screen # 4 ---> "Leads" screen --> Click on "Find Leads"
			mainDriver.findElement(By.xpath("//a[text()='Find Leads']")).click();

			// Step 11: Screen # 4 ---> Click on "Phone" --> Click on "Find Leads"
			myEntryObj.findLeadByID(mainDriver, firstLeadIDinSearch);
			
			mainDriver.close();

		
		} else {
			
			System.out.println("There are no search results; terminating the execution here");
			mainDriver.close();
			
		}
		
	}

}
