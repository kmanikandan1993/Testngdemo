package org.demo;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DemoTestng {
	static WebDriver driver;
	static long startTime;
	
	@BeforeClass(groups="common")
	public static void launch() {
		System.out.println("Before class");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://www.flipkart.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	}
	
	
	@BeforeMethod(groups="common")
public void startTime() {
		System.out.println("Before method");
		startTime = System.currentTimeMillis();
	}
	
	@AfterMethod(groups="common")
	public void endTime() {
		System.out.println("After method");
		long endTime =System.currentTimeMillis();
		long tt = endTime-startTime;
		System.out.println("time taken;"+tt);
	}
	
	@Test(priority = -1,groups="smoke")
	public void login() throws InterruptedException{
		Thread.sleep(2000);
			try {
			
			WebElement button = driver.findElement(By.xpath("//button[text()='✕']"));
			Thread.sleep(2000);
			button.isDisplayed();
			Thread.sleep(2000);
			button.click();
		} catch (Exception e) {
			
		System.out.println("pop up not displayed");	
		}
		
		WebElement bar = driver.findElement(By.name("q"));
		bar.sendKeys("realme mobile",Keys.ENTER);
	}
	static String MobileName;
	@Test(priority = 0,enabled = false)
	public void click() throws InterruptedException{
		Thread.sleep(2000);
	WebElement mobileName =	driver.findElement(By.xpath("(//div[contains(text(),'realme 8 (Cyber Black, 128 GB)')])[1]"));
	 MobileName = mobileName.getText();
	 System.out.println(MobileName);
	 Thread.sleep(2000);
	mobileName.click();
	}
	
	@Test(priority = 1,groups="regression")
	public void window() throws InterruptedException{
		String parentURL =driver.getWindowHandle();
		
		Set<String> childURL = driver.getWindowHandles();
  for(String child : childURL) {
	  if (!parentURL.equals(child)) {
		driver.switchTo().window(child);
	}
  }	
	
	//@Test(priority = 2)
//	public void text() {
		
//WebElement newMobileName=driver.findElement(By.xpath("//span[contains(text(),'realme')]"));

//String newMobile = newMobileName.getText();	
//System.out.println(newMobile);

//Assert.assertTrue(newMobile.equals(MobileName));
		
//	}
	@AfterClass(groups="common")
	public static void quit() throws IOException {
		System.out.println("After class");
		
		DateTimeFormatter dtf =DateTimeFormatter.ofPattern("Hmm");
		LocalDateTime ldt =	LocalDateTime.now();
		String format=dtf.format(ldt);
		System.out.println(format);
		
		
		System.out.println("After class");
		
		
		TakesScreenshot tk = (TakesScreenshot)driver;
		File source=tk.getScreenshotAs(OutputType.FILE);
		File dest = new File (".//target//report"+format+".png");
		FileUtils.copyFile(source, dest);
		
		 //driver.quit();
	
	}

	}


