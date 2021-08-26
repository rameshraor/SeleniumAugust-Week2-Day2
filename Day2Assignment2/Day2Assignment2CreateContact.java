package week2.Day2Assignment2;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Day2Assignment2CreateContact {

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
	
	public void EnterFieldsInCreateContactScreen(ChromeDriver crContactDriver) {					// CREATE CONTACT Screen
		
		// Step 5-01: Enter FirstName
		crContactDriver.findElement(By.xpath("//input[@id='firstNameField']")).sendKeys("Ramesh");

		// Step 5-02: Enter LastName
		crContactDriver.findElement(By.xpath("//input[@id='lastNameField']")).sendKeys("LNU");
		
		// Step 5-03: Enter the First Name Local
		crContactDriver.findElement(By.xpath("//input[@id='createContactForm_firstNameLocal']")).sendKeys("John");

		// Step 5-04: Enter the Last Name Local
		crContactDriver.findElement(By.xpath("//input[@id='createContactForm_lastNameLocal']")).sendKeys("Doe");

		// Step 5-05: Enter the Salutation
		crContactDriver.findElement(By.xpath("//input[@id='createContactForm_personalTitle']")).sendKeys("Mr.");

		// Step 5-06: Enter the DOB
		crContactDriver.findElement(By.xpath("//input[@id='createContactForm_birthDate']")).sendKeys("1/31/76");

		// Step 5-07: Enter the Prof Title
		crContactDriver.findElement(By.xpath("//input[@id='createContactForm_generalProfTitle']")).sendKeys("Director");

		// Step 5-08: Enter the Department
		crContactDriver.findElement(By.xpath("//input[@id='createContactForm_departmentName']")).sendKeys("Quality Assurance");

		// Step 5-09: Enter the Preferred Currency
		WebElement currencyID = crContactDriver.findElement(By.xpath("//select[@id='createContactForm_preferredCurrencyUomId']"));
		Select dropdowncurrencyID = new Select(currencyID);
		dropdowncurrencyID.selectByValue("USD");
		
		// Step 5-10: Enter the Initial Account
		crContactDriver.findElement(By.xpath("//input[@id='createContactForm_accountPartyId']")).sendKeys("CitiBank");
		
		// Step 5-11: Enter the Description
		WebElement descBox = crContactDriver.findElement(By.xpath("//textarea[@id='createContactForm_description']"));
		descBox.clear();
		descBox.sendKeys("The Lead will be responsible for Driving all the activities related to the "
				+ "Quality Assurance activities across all the projects. He/She will also be responsible "
				+ "for designing Test Strtagey, Test Management, and Status reporting");

		// Step 5-12: Enter the Important Note
		WebElement importantNote = crContactDriver.findElement(By.xpath("//textarea[@id='createContactForm_importantNote']"));
		importantNote.clear();
		importantNote.sendKeys("Please note that this position would require a person with extensive QA expertise and  "
				+ "Hands on experience with multiple Test management / Automation tools.");

		// Step 5-13: Enter the country code
		WebElement countryCode = crContactDriver.findElement(By.xpath("//input[@id='createContactForm_primaryPhoneCountryCode']"));
		countryCode.clear();
		countryCode.sendKeys("+1");

		// Step 5-14: Enter the Phone Number Area code
		crContactDriver.findElement(By.xpath("//input[@id='createContactForm_primaryPhoneAreaCode']")).sendKeys("609");

		// Step 5-15: Enter the Phone Number
		crContactDriver.findElement(By.xpath("//input[@id='createContactForm_primaryPhoneNumber']")).sendKeys("523-6213");

		// Step 5-16: Enter the Phone Number Extension
		crContactDriver.findElement(By.xpath("//input[@id='createContactForm_primaryPhoneExtension']")).sendKeys("122");

		// Step 5-17: Enter the "Ask for"
		crContactDriver.findElement(By.xpath("//input[@id='createContactForm_primaryPhoneAskForName']")).sendKeys("Mr. Q");

		// Step 5-18: Enter the Email ID
		crContactDriver.findElement(By.xpath("//input[@id='createContactForm_primaryEmail']")).sendKeys("ramesh.r@gmail.com");
		
		// Step 5-19: Enter the "To Name"  -->   This is auto-gen, leave it as it is
		String toName = crContactDriver.findElement(By.xpath("//input[@id='generalToNameField']")).getText();
		System.out.println("Auto generated name : " + toName);

		// Step 5-20: Enter the "Attention Name"
		crContactDriver.findElement(By.xpath("//input[@id='createContactForm_generalAttnName']")).sendKeys("Ramesh R");

		// Step 5-21: Enter the Address line 1
		crContactDriver.findElement(By.xpath("//input[@id='createContactForm_generalAddress1']")).sendKeys("1008 Reading Rd");

		// Step 5-22: Enter the Address line 2
		crContactDriver.findElement(By.xpath("//input[@id='createContactForm_generalAddress2']")).sendKeys("1Suite # 3307");

		// Step 5-23: Enter the City name
		crContactDriver.findElement(By.xpath("//input[@id='createContactForm_generalCity']")).sendKeys("Edison");

		// Step 5-24: Enter the State name
		WebElement addressState = crContactDriver.findElement(By.xpath("//select[@id='createContactForm_generalStateProvinceGeoId']"));
		Select dropdownState = new Select(addressState);
		dropdownState.selectByVisibleText("New Jersey");
		
		// Step 5-25: Enter the Zip Code
		crContactDriver.findElement(By.xpath("//input[@id='createContactForm_generalPostalCode']")).sendKeys("08817");

		// Step 5-26: Enter the Zip Code Extension
		crContactDriver.findElement(By.xpath("//input[@id='createContactForm_generalPostalCodeExt']")).sendKeys("2314");

	}
	
	public static void main(String[] args) {

		Day2Assignment2CreateContact myEntryObj = new Day2Assignment2CreateContact();
		
		
		// Step 0: Configure the Driver
		ChromeDriver mainDriver = myEntryObj.ConfigureDriver();

		// Step 1: Screen # 1 ---> Initial Login screen
		myEntryObj.EnterFieldsInLoginScreen(mainDriver);

		// Step 2: Screen # 2 ---> Click on "CRM/SFA" --> This will take to "my Home" screen
		mainDriver.findElement(By.xpath("//div[@id='button']/div")).click();

		// Step 3: Screen # 3 ---> "myHome" screen --> Click on Contacts
		mainDriver.findElement(By.xpath("//a[text()='Contacts']")).click();
		
		// Step 4: Screen # 4 ---> "Contacts" screen --> Click on "Create Contact"
		mainDriver.findElement(By.xpath("//a[text()='Create Contact']")).click();

		// Step 5: Screen # 5 ---> "Create Contact" screen
		myEntryObj.EnterFieldsInCreateContactScreen(mainDriver);

		// Step 6: Click "Create Contact" button	-->   This will take to "View Contact" screen
		mainDriver.findElement(By.xpath("//input[@class='smallSubmit']")).click();
		
		// Step 7: Screen # 6 ---> Click on Edit	-->   This will take to "Edit Contact" screen
		mainDriver.findElement(By.xpath("//a[text()='Edit']")).click();
		
		// Step 8: Screen # 8 ---> Clear the "Descriptions field
		mainDriver.findElement(By.xpath("//textarea[@id='updateContactForm_description']")).clear();
		
		// Step 8: Click the Update button  -->  This will take to a new page
		mainDriver.findElement(By.xpath("(//input[@class='smallSubmit'])[1]")).click();
		
		// Step 9: Screen # 8 ---> Get the title of the resultant page
		String pageTitle = mainDriver.getTitle();
		System.out.println("We are now finally in the page : " + pageTitle);
	}

}
