package week2.Day2Assignment1;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebElement;

public class Day2Assignment101EditPage {

	public ChromeDriver ConfigureDriver() {

		// Below are standard steps to be included (except the URL)

		// Step 0-01: Download and set the path
		WebDriverManager.chromedriver().setup();
		// Step 0-02: Launch the Chrome browser
		ChromeDriver driver = new ChromeDriver();
		// Step 0-03: Load the URL
		driver.get("http://leafground.com/pages/Edit.html");
		// Step 0-04: Maximize the window
		driver.manage().window().maximize();
		// Step 0-05: waits for 10 secs if the element is not in the DOM
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		return driver;
	}

	public void EnterFieldsInEditScreen(ChromeDriver editDriver) { // EDIT Screen

		// Step 1-01: Enter Email address
		editDriver.findElement(By.xpath("//input[@id='email']")).sendKeys("ramesh@gmail.com");

		// Step 1-02: Enter Text to be appended
		WebElement appendText = editDriver.findElement(By.xpath("//input[@value='Append ']"));
		appendText.sendKeys("This Text is appended with the existing text");
		appendText.sendKeys(Keys.TAB);

		// Step 1-03: Get Text from this field
		// String text3rdTxtBox =
		// editDriver.findElement(By.xpath("//input[@value='TestLeaf']")).getText();
		String text3rdTxtBox = editDriver.findElement(By.xpath("//input[@value='TestLeaf']")).getAttribute("value");
		System.out.println("Text in the 3rd Text box is --> " + text3rdTxtBox);

		// Step 1-04: Clear the default text from this field
		editDriver.findElement(By.xpath("//input[@value='Clear me!!']")).clear();

		// Step 1-05: Confirm if the text box is not ediatble (i.e., disabled)
		boolean isTxtBoxEditable = editDriver.findElement(By.xpath("//label[@for='disabled']/following-sibling::input"))
				.isEnabled();
		if (isTxtBoxEditable) {
			System.out.println("The Text box is Editable");
		} else {
			System.out.println("The Text box is NOT Editable");
		}

	}

	public static void main(String[] args) {

		// Define a new object
		Day2Assignment101EditPage myEditObj = new Day2Assignment101EditPage();

		/*
		 * Configure the Driver for the EDIT screen page
		 */
		ChromeDriver mainDriver = myEditObj.ConfigureDriver();

		/*
		 * Screen # 5 ---> "Edit" screen
		 */
		myEditObj.EnterFieldsInEditScreen(mainDriver);

	}

}
