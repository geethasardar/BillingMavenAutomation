package com.elisity.aws.Maven;

import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MonthlyAmazonBillingTest {

	@Test
	public void main() throws InterruptedException {
	//public void Billing() throws InterruptedException {
		//Open the browser
		WebDriver driver = new ChromeDriver();
		//set the URL
		driver.get("https://www.aws.com");
		Thread.sleep(5000);
		System.out.print("Successful opening the web page \n");
		
		//System.out.print("Selecting My Account \n");
		WebDriverWait wait = new WebDriverWait(driver, 50);
		WebElement accountelement = wait.until(ExpectedConditions.visibilityOfElementLocated (By.xpath("//*[@id=\"m-nav\"]/div[1]/div[2]/a[4]/i")));
		accountelement.click();
		Thread.sleep(500);
		//System.out.print("Successful Selected My Account \n");
		
		//System.out.print("Selecting AWS Management Console \n");
		WebElement awsmgmt = driver.findElement(By.xpath("/html/body/div[27]/ul/li[1]/a"));
		awsmgmt.click();
		Thread.sleep(3000);
		System.out.print("Successfully Selecting AWS Management Console \n");
		
		//Enter email address
		System.out.print("Entering account email \n");
		WebElement email = driver.findElement(By.xpath("//*[@id=\"resolving_input\"]"));
		email.sendKeys("vasusardar@hotmail.com");
		//System.out.print("Successfully Entering account email \n");
		
		WebElement next = driver.findElement(By.id("next_button"));
		next.click();
		Thread.sleep(2000);
		
		
		WebElement passwd = driver.findElement(By.xpath("//*[@id=\"ap_password\"]"));
		passwd.sendKeys("welCome00!");
		Thread.sleep(2000);
		
		WebElement signin = driver.findElement(By.id("signInSubmit-input"));
		signin.click();
		System.out.print("Successfully Login into account  \n");

		// verify signin successful 
		String signinhdr = driver.getTitle();
		if(signinhdr.equals("AWS Management Console")) {
			//System.out.println("AWS Management Console Page successful");
		}else {
			driver.close();
		}

		Thread.sleep(3000);
		
		
		// Finding Billing
		WebElement user = driver.findElement(By.xpath("//*[@id=\"nav-usernameMenu\"]"));
		user.click();

		driver.findElement(By.xpath("//*[@id=\"aws-billing-console\"]")).click();
		Thread.sleep(3000);
		
		// Verifying billing page 
		String billinghdr = driver.getTitle();
		if(billinghdr.equals("Billing Management Console")) {
			//System.out.println("Billing Management Console Page successful");
		}else {
			driver.close();
		}
		
		Thread.sleep(3000);
		
		//Getting Cost Explorer
		WebElement costexp = driver.findElement(By.cssSelector("div.awsui-util-container:nth-child(2) > div:nth-child(1) > div:nth-child(1) > awsui-button:nth-child(2) > a:nth-child(1) > span:nth-child(1)"));
		costexp.click();
		Thread.sleep(2000);
		
		//Verifying Cost Reports page was opened successfully
		String costhdr = driver.getTitle();
		if(costhdr.equals("Cost Reports")) {
			//System.out.println("Cost Reports Page opened successful");
		}else {
			driver.close();
		}
		Thread.sleep(3000);
		
		// Selecting Billing Details
		WebElement montlycost = driver.findElement(By.cssSelector("month-summary.ng-isolate-scope > div:nth-child(1) > div:nth-child(1) > card:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1)"));
		System.out.println("WARNING: Month to date costs is: "+ montlycost.getText());

		WebElement forecastamt = driver.findElement(By.cssSelector("month-summary.ng-isolate-scope > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > card:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1)"));
		System.out.println("WARNING: Forecasted month end costs is: "+ forecastamt.getText());
		
		//signing out
		driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[4]/div/div[1]/div[4]/a[2]/div[1]")).click();
		Thread.sleep(500);
		WebElement signout = driver.findElement(By.cssSelector("#aws-console-logout"));
		signout.click();
		//System.out.println("Successfully Signed out");
		
		//closing the browser
		driver.close();
		//System.out.println("Successfully Closed the browser");
	}
	
}
