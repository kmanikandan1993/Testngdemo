package org.demo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Parameter {
	static WebDriver driver;
	static long startTime;
	
	@BeforeClass(groups="common")
	public static void launch() {
		System.out.println("Before class");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		
		driver.get("https://www.flipkart.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}
	
	@BeforeMethod(groups="common")
	public void beforeMethod(String username,String password) {
		
		System.out.println("Before Method");	
	    startTime = System.currentTimeMillis();

	}
	
	
	@Test(groups= "smoke")
	@Parameters({"username","password"})
	public void mobile1(String username,String password) throws Throwable  {
					   
			   WebElement button = driver.findElement(By.xpath("(//input[@class='_2IX_2- VJZDxU'])"));
			   button.sendKeys(username);
			   WebElement pass = driver.findElement(By.xpath("(//input[@type='password'])[1]"));
			   pass.sendKeys(password);
			   WebElement mani = driver.findElement(By.xpath("(//button[@type='submit'])[1]"));
			   mani.click(); 
			  
			   
			
	    	} 
	
		   

			@AfterMethod
			public void Method() {
				
				long endTime = System.currentTimeMillis();
				
			    long tt = endTime - startTime;
				
			    System.out.println("After Method");
				System.out.println("Time taken is :"+ tt);

			}
			
			@AfterClass
			public void close() {
				
				   DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HHmm");  
				   LocalDateTime now = LocalDateTime.now();  
				   String format = dtf.format(now);
				   System.out.println(format);
				      
				
				System.out.println("After class");

				
				//driver.quit();

			}
		}

