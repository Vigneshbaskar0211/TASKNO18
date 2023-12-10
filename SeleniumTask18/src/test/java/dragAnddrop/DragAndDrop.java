package dragAnddrop;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DragAndDrop {
    private WebDriver driver;
    private String baseUrl = "https://jqueryui.com/droppable/";

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void performDragAndDrop() throws InterruptedException {
        driver.get(baseUrl);
        driver.switchTo().frame(driver.findElement(By.className("demo-frame")));

        WebElement sourceElement = driver.findElement(By.id("draggable"));
        WebElement targetElement = driver.findElement(By.id("droppable"));

        // Perform drag and drop operation using Actions class
        Actions actions = new Actions(driver);
        actions.dragAndDrop(sourceElement, targetElement).build().perform();


        // Verify color property of target element's CSS after drop
        String targetColor = targetElement.getCssValue("background-color");
        System.out.println("Actual Color: " + targetColor); // Print the actual color for reference

        // Update the assertion with the actual color value obtained
        Assert.assertEquals(targetColor, "rgba(255, 250, 144, 1)", "Drop color does not match!");


        // Verify text change of target element after drop
        String droppedText = targetElement.getText();
        Assert.assertEquals(droppedText, "Dropped!", "Text not changed after drop!");
        
        Thread.sleep(5000);
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
