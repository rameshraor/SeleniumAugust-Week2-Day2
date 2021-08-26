package week2.Day2Assignment1;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebElement;

public class Day2Assignment102ButtonsPage {

	public ChromeDriver ConfigureDriver() {

		// Below are standard steps to be included (except the URL)

		// Step 0-01: Download and set the path
		WebDriverManager.chromedriver().setup();
		// Step 0-02: Launch the Chrome browser
		ChromeDriver driver = new ChromeDriver();
		// Step 0-03: Load the URL
		driver.get("http://leafground.com/pages/Button.html");
		// Step 0-04: Maximize the window
		driver.manage().window().maximize();
		// Step 0-05: waits for 10 secs if the element is not in the DOM
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		return driver;
	}

	public void ClickButtonToHomeScreen(ChromeDriver homeDriver) { // Click Home BUTTONS

		// Step 1-01: Click "Go to Home Page" button
		homeDriver.findElement(By.xpath("//label[@for='home']/following-sibling::button")).click();

	}
	
	
	public void OtherButtonsScreen(ChromeDriver othDriver) { // Work with other BUTTONS

		// Step 2-01: Find the coordinates of the button
		WebElement btnFindPosition = othDriver.findElement(By.xpath("//button[@id='position']"));
		Point btnLoc1 = btnFindPosition.getLocation();
		Dimension btnSize1 = btnFindPosition.getSize();
		System.out.println("Button-1 position  -->  " + btnLoc1);
		System.out.println("Button-1 Size  -->  " + btnSize1);

		// Step 2-02: Get color of the button
		WebElement btnFindColor = othDriver.findElement(By.xpath("//button[@id='color']"));
		String btnTxtColor = btnFindColor.getCssValue("color");
		String btnBackColor = btnFindColor.getCssValue("background-color");
		System.out.println("Button Text Color  -->  " + btnTxtColor);
		System.out.println("Button Background Color  -->  " + btnBackColor);

		// Step 2-03: Find the coordinates of the button
		WebElement btnFindSize = othDriver.findElement(By.xpath("//button[@id='size']"));
		Point btnLoc2 = btnFindSize.getLocation();
		Dimension btnSize2 = btnFindSize.getSize();
		System.out.println("Button-2 position  -->  " + btnLoc2);
		System.out.println("Button-2 Size  -->  " + btnSize2);

	}

	public static void main(String[] args) {

		// Define a new object - for clicking and going to Home Page
		//   Configure the Driver for the "Click to Home" button
		//       Call the method to click the Home button ----> to take to home page
		Day2Assignment102ButtonsPage myClickToHomeButtonsObj = new Day2Assignment102ButtonsPage();
		ChromeDriver mainHomeDriver = myClickToHomeButtonsObj.ConfigureDriver();
		myClickToHomeButtonsObj.ClickButtonToHomeScreen(mainHomeDriver);
		

		// Define a new object - for clicking and going to Home Page
		//   Configure the Driver for the "Click to Home" button
		//       Call the method to click the Home button ----> to take to home page
		Day2Assignment102ButtonsPage myOtherButtonsObj = new Day2Assignment102ButtonsPage();
		ChromeDriver OthButtonsDriver = myOtherButtonsObj.ConfigureDriver();
		myOtherButtonsObj.OtherButtonsScreen(OthButtonsDriver);



	}

}
