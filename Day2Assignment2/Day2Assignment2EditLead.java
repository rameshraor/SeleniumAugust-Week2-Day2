package week2.Day2Assignment2;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Day2Assignment2EditLead {

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
	
	public void searchByFirstName(ChromeDriver fndLeadDriver) {
		
		System.out.println("In searchByFirstName method");
		
		// Step 6-01: Enter First Name
		WebElement txtFirstName = fndLeadDriver.findElement(By.xpath("(//input[@name='firstName'])[3]"));
		txtFirstName.clear();
		txtFirstName.sendKeys("Ramesh");
		
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
	
	
	public String updateCompanyName(ChromeDriver fndLeadDriver) {
		
		// Step 10-01: Update the Company Name
		//WebElement txtCompName = fndLeadDriver.findElement(By.xpath("//input[@id='updateLeadForm_companyName']"));
		//String origCompName = txtCompName.getText();
		
		//String origCompName = fndLeadDriver.findElement(By.xpath("//input[@id='updateLeadForm_companyName']")).getText().toString();
		String origCompName = fndLeadDriver.findElement(By.xpath("//input[@id='updateLeadForm_companyName']")).getAttribute("value");
		System.out.println("The original company name is " + origCompName);
		
		WebElement txtCompName = fndLeadDriver.findElement(By.xpath("//input[@id='updateLeadForm_companyName']"));
		txtCompName.clear();
		txtCompName.sendKeys("CTS");
		
		// Step 10-02: Click on Update Button
		fndLeadDriver.findElement(By.xpath("(//input[@class='smallSubmit'])[1]")).click();

		return origCompName;
	}
	
	
	public static void main(String[] args) {

		Day2Assignment2EditLead myEntryObj = new Day2Assignment2EditLead();
		
		
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

		// Step 5: Screen # 4 ---> Click on "Email ID" --> Click on "Find Leads"
		mainDriver.findElement(By.xpath("//span[text()='Name and ID']")).click();

		// Step 6: Screen # 4 ---> Search for Lead, by First Name
		myEntryObj.searchByFirstName(mainDriver);
		
		// Step 7: Check if the Phone number search brings up 1 or more results
		Boolean isPresent = mainDriver.findElements(By.xpath("//div[@class='x-grid3-cell-inner x-grid3-col-partyId']")).size() > 0;
		if (isPresent) {
			
			// Step 8: Screen # 4 ---> Select the 1st result, and capture the Lead ID  --> Takes to "View Lead" screen
			String firstLeadIDinSearch = myEntryObj.selectFirstLead(mainDriver);
			System.out.println("Selected the element, and returned the value --> " + firstLeadIDinSearch);

			// Step 9: Screen # 5 ---> "View Lead" Screen --> Click on Edit Lead  -->  Takes to "Edit Lead Screen"
			mainDriver.findElement(By.xpath("//a[text()='Edit']")).click();
			
			// Step 10: Screen # 6 ---> "Edit Lead" Screen --> Change the Company Name, Click Update, and confirm that the changed name appears
			String origCompNameR = myEntryObj.updateCompanyName(mainDriver);
			
			// Step 11: Screen # 7 ---> "View Lead" Screen Verify if the company name is updated
						String pgTitle = mainDriver.getTitle().toString();
			System.out.println("pgTitle = " + pgTitle);
			String expTitle = "View Lead | opentaps CRM";
			System.out.println("expTitle = " + expTitle);
			if (pgTitle.equals(expTitle)) {
				System.out.println("Correct page loaded. Page title is --> " + mainDriver.getTitle());
			} else {
				System.out.println("WRONG page loaded. Page title is --> " + mainDriver.getTitle());
			}
			
			//
			// Step 13: Screen # 7 ---> Check if the company name has been updated
			System.out.println("Now AFTER the Edit, the page title is --> " + mainDriver.getTitle());
			String chgCompName = mainDriver.findElement(By.xpath("//span[@id='viewLead_companyName_sp']")).getText();
			System.out.println("The new Company name is " + chgCompName);
			
			// Step 14: Check if the original company name has been updated/changed
			if (chgCompName.equals(origCompNameR)) {
				
				System.out.println("Original name and the Duplicate name are same");
			} else {
				System.out.println("The Company name has been updated successfully!");
			}
			
			mainDriver.close();

		
		} else {
			
			System.out.println("There are no search results; terminating the execution here");
			mainDriver.close();
			
		}
		
	}

}
