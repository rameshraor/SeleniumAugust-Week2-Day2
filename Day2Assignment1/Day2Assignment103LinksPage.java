package week2.Day2Assignment1;

import java.awt.Dimension;
import java.awt.List;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebElement;

public class Day2Assignment103LinksPage {

	public ChromeDriver ConfigureDriver() {

		// Below are standard steps to be included (except the URL)

		System.out.println("In Method # 0 - ConfigureDriver");

		// Step 0-01: Download and set the path
		WebDriverManager.chromedriver().setup();
		// Step 0-02: Launch the Chrome browser
		ChromeDriver driver = new ChromeDriver();
		// Step 0-03: Load the URL
		driver.get("http://leafground.com/pages/Link.html");
		// Step 0-04: Maximize the window
		driver.manage().window().maximize();
		// Step 0-05: waits for 10 secs if the element is not in the DOM
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		return driver;
	}

	public void GoToHomePage(ChromeDriver link1Driver) {

		System.out.println("In Method # 1 - GoToHomePage");

		// Step 1-01: Click the 1st ;link ---> to take to home page
		link1Driver.findElement(By.xpath("(//a[@href='../home.html'])[2]")).click();

		// Step 1-02: Go back to main screen
		link1Driver.navigate().back();

	}

	public void FindLink(ChromeDriver link2Driver) {

		System.out.println("In Method # 2 - FindLink");

		// Step 2-01: Get the URL and print
		String str2ndLink = link2Driver.findElement(By.xpath("(//a[@link='blue'])[2]")).getAttribute("href");

		System.out.println("The URL is --> " + str2ndLink);

	}

	public void verifyBrokenLink(ChromeDriver link3Driver) throws MalformedURLException, IOException {

		System.out.println("In Method # 3 - verifyBrokenLink");

		// Step 3-01: Get the URL from the 'a' Tag
		String url3rdLink = link3Driver.findElement(By.xpath("(//a)[4]")).getAttribute("href");

		// Step 3-02: Check if the URL is NULL or empty
		if (url3rdLink == null || url3rdLink.isEmpty()) {

			System.out.println("URL is either not configured for anchor tag or it is empty");

		} else {

			// Step 3-03: If the URL is NOT Null/Empty, check if it is a valid link

			// Define a new connection, set the request method to be Header, and establish
			// connection
			HttpURLConnection huc = (HttpURLConnection) (new URL(url3rdLink).openConnection());
			huc.setRequestMethod("HEAD");
			huc.connect();

			// Get the Response code from the connection, and determine if the URL is valid
			// or broken
			int respCode = huc.getResponseCode();

			if (respCode >= 400) {
				System.out.println("The URL ( " + url3rdLink + " ) is a broken link");
			} else {
				System.out.println("The URL ( " + url3rdLink + " ) is a valid link");
			}

		}
	}

	public void GoToHomePageAgain(ChromeDriver link4Driver) {

		System.out.println("In Method # 4 - GoToHomePageAgain");

		// Step 4-01: Click the 1st ;link ---> to take to home page
		link4Driver.findElement(By.xpath("//label[text()='(Interact with same link name)']/preceding-sibling::a"))
				.click();

		// Step 4-02: Go back to main screen
		link4Driver.navigate().back();

	}

	@SuppressWarnings("deprecation")
	public void CountNbrLinksInPage(ChromeDriver link5Driver) { 

		// List lstLinksInPage = (List) link5Driver.findElements(By.tagName("a"));
		// Dimension lstSize = lstLinksInPage.size();
		// System.out.println("The number of links in the page is : " + lstSize);

		List links = (List) link5Driver.findElements(By.xpath("//a")); // Identify the number of Link on webpage and
																		// assign into Webelement List
		Dimension linkCount = links.size(); // Count the total Link list on Web Page
		System.out.println("Total Number of link count on webpage = " + linkCount); // Print the total count of links on
																					// webpage

	}

	public static void main(String[] args) throws MalformedURLException, IOException {

		// Define a new object
		Day2Assignment103LinksPage myLinksObj = new Day2Assignment103LinksPage();

		/*
		 * Configure the Driver for the EDIT screen page
		 */
		ChromeDriver mainDriver = myLinksObj.ConfigureDriver();

		// Action # 1 (Go To Home Page)
		myLinksObj.GoToHomePage(mainDriver);

		// Action # 2 (Find where am supposed to go without clicking me?)
		myLinksObj.FindLink(mainDriver);

		// Action # 3 (Verify am I broken?)
		myLinksObj.verifyBrokenLink(mainDriver);

		// Action # 4 (Go To Home Page - Once Again)
		myLinksObj.GoToHomePageAgain(mainDriver);

		// Action # 5 (How many links are available in this page?)
		myLinksObj.CountNbrLinksInPage(mainDriver);
	}

}
