package facebooksignuptest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FacebookSignUpTest {

    
     String baseURL = "https://www.facebook.com/";
 	WebDriver driver;
 	@BeforeClass
 	public void initialization () {
         WebDriverManager.chromedriver().setup();
          driver = new ChromeDriver();
          driver.get("https://www.facebook.com/");
          driver.manage().window().maximize();
          driver.manage().deleteAllCookies();
 	}
    @Test(priority = 1)
    public void navigateToFacebook() {
        // Navigate to Facebook
        driver.get("https://www.facebook.com/");
        // Verify redirection to Facebook homepage
        Assert.assertEquals(driver.getCurrentUrl(), baseURL);
    }

    @Test(priority = 2)
    public void signUpOnFacebook() throws InterruptedException {
        
        WebElement CreateNewAccountButton = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[1]/div/div/div/div[2]/div/div[1]/form/div[5]/a")); // Assuming this is the sub-menu element
        CreateNewAccountButton.click(); // Click on the "Home" button in the submenu
        Thread.sleep(5000); // Wait for 5 seconds (you may replace this with explicit waits)


    	
        // Fill in the sign-up form
        WebElement firstNameField = driver.findElement(By.name("firstname"));
        WebElement lastNameField = driver.findElement(By.name("lastname"));
        WebElement emailField = driver.findElement(By.name("reg_email__"));
        WebElement passwordField = driver.findElement(By.name("reg_passwd__"));
        WebElement birthDay = driver.findElement(By.name("birthday_day"));
        WebElement birthMonth = driver.findElement(By.name("birthday_month"));
        WebElement birthYear = driver.findElement(By.name("birthday_year"));
  
        
        
        
        WebElement gender = driver.findElement(By.xpath("//input[@type='radio' and @value='2']")); // Change value for Female

        firstNameField.sendKeys("Test");
        lastNameField.sendKeys("User");
        emailField.sendKeys("testuser@test.com");
        passwordField.sendKeys("StrongPassword123");
        birthDay.sendKeys("11");
        birthMonth.sendKeys("May");
        birthYear.sendKeys("1985");
        gender.click();

        // Click on the "Sign Up" button
        WebElement signUpButton = driver.findElement(By.name("websubmit"));
        signUpButton.click();

        // Wait for the page to load and verify successful registration
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.facebook.com/");
    }


     
    @AfterClass
    public void tearDown() {
        // Close the browser
        driver.quit();
    }
}