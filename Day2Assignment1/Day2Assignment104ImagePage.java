package week2.Day2Assignment1;

import java.awt.Dimension;
import java.awt.List;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.online.HttpClient;
import io.netty.handler.codec.http.HttpResponse;

import org.openqa.selenium.WebElement;

public class Day2Assignment104ImagePage {

	public ChromeDriver ConfigureDriver() {

		// Below are standard steps to be included (except the URL)

		System.out.println("In Method # 0 - ConfigureDriver");

		// Step 0-01: Download and set the path
		WebDriverManager.chromedriver().setup();
		// Step 0-02: Launch the Chrome browser
		ChromeDriver driver = new ChromeDriver();
		// Step 0-03: Load the URL
		driver.get("http://leafground.com/pages/Image.html");
		// Step 0-04: Maximize the window
		driver.manage().window().maximize();
		// Step 0-05: waits for 10 secs if the element is not in the DOM
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		return driver;
	}

	public void ImageForHomePage(ChromeDriver Image1Driver) {

		System.out.println("In Method # 1 - GoToHomePage");

		// Step 1-01: Click the 1st image ---> to take to home page
		Image1Driver
				.findElement(By.xpath("//label[text()='Click on this image to go home page']/following-sibling::img"))
				.click();

		// Step 1-02: Go back to main screen
		Image1Driver.navigate().back();

	}

	public void FindIfImageBroken(ChromeDriver link2Driver) {

		System.out.println("In Method # 2 - FindIfImageBroken");

		// Step 2-01: Find the image web element
		WebElement img2ndInPage = link2Driver
				.findElement(By.xpath("//label[text()='Am I Broken Image?']/following-sibling::img"));

		try {
			org.apache.hc.client5.http.classic.HttpClient client = HttpClientBuilder.create().build();
			HttpGet request = new HttpGet(img2ndInPage.getAttribute("src"));
			org.apache.hc.core5.http.HttpResponse response = client.execute(request);

			/* For valid images, the HttpStatus will be 200 */

			if (response.getCode() != 200) {
				System.out.print("The response code is : " + response.getCode());
				System.out.println(img2ndInPage.getAttribute("outerHTML") + " is broken.");

			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception");
		}

	}

	public void ClickImageGoToHome(ChromeDriver link3Driver) throws MalformedURLException, IOException {

		System.out.println("In Method # 3 - verifyBrokenLink");

		// Step 3-01: Find the image web element
		WebElement img2ndInPage = link3Driver
				.findElement(By.xpath("//label[text()='Click me using Keyboard or Mouse']/following-sibling::img"));

		// Step 3-02: Click on the image web element
		img2ndInPage.click();

		// Step 3-03: verify that the click takes to the home page
		if (link3Driver.getTitle().equals("TestLeaf - Selenium Playground")) {
			System.out.println("We are taken to TestLeaf - Selenium Playground page");
		} else {
			System.out.println("We are NOT in TestLeaf - Selenium Playground");
		}

	}


	public static void main(String[] args) throws MalformedURLException, IOException {

		// Define a new object
		Day2Assignment104ImagePage myImageObj = new Day2Assignment104ImagePage();

		/*
		 * Configure the Driver for the EDIT screen page
		 */
		ChromeDriver mainDriver = myImageObj.ConfigureDriver();

		// Action # 1 (Go To Home Page)
		myImageObj.ImageForHomePage(mainDriver);

		// Action # 2 (Am I Broken Image?)
		myImageObj.FindIfImageBroken(mainDriver);
		
		// Action # 3 (Click me using Keyboard or Mouse)
		myImageObj.ClickImageGoToHome(mainDriver);

	}

}
