package demo;

import java.util.List;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import dev.failsafe.internal.util.Durations;

import java.util.logging.Level;
import io.github.bonigarcia.wdm.WebDriverManager;


public class TestCases {
    ChromeDriver driver;
    public TestCases()
    {
        System.out.println("Constructor: TestCases");

        WebDriverManager.chromedriver().timeout(30).setup();
        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        // Set log level and type
        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);

        // Set path for log file
        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "chromedriver.log");

        driver = new ChromeDriver(options);

        // Set browser to maximize and wait
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    }

    public void endTest()
    {
        System.out.println("End Test: TestCases");
        driver.close();
        driver.quit();

    }

    
    public  void testCase01(){
        System.out.println("Start Test case: testCase01");
        driver.get("https://www.makemytrip.com/");
        String currentUrl = driver.getCurrentUrl();
        System.out.println(currentUrl);
        // Verify if the text contains "makemytrip."
    if (currentUrl.contains("makemytrip.")) {
        System.out.println("Text 'makemytrip.' is present in the URL.");
    } else {
        System.out.println("Text 'makemytrip.' is NOT present in the URL.");
    }
        System.out.println("end Test case: testCase01");
    }

    public void testCase02() throws InterruptedException{
        System.out.println("Start Test Case: testCase02");
        driver.get("https://www.makemytrip.com/");
        driver.findElement(By.xpath("//li[contains(text(),'One Way')]")).click();
        //click on input text in From Option
        //send key 'BLR'
        driver.findElement(By.id("fromCity")).sendKeys("blr");
        //click on suggestion which contains BLR;
        WebElement suggetionfrom = driver.findElement(By.xpath("//p[contains(@class,'font14 appendBottom5 blackText') and contains(text(),'Bengaluru')]"));
        Actions action = new Actions(driver);
        action.click(suggetionfrom).perform();
        Thread.sleep(3000);

        //click on input text in To Option
        //send key 'DEL'
        driver.findElement(By.id("toCity")).sendKeys("del");
        //click on suggestion which contains New Delhi;
        WebElement suggetionTo = driver.findElement(By.xpath("//p[contains(@class,'blackText') and contains(text(),'New Delhi')]"));
        action.click(suggetionTo).perform();
        Thread.sleep(5000);

        //select the date 29th of next month

        driver.findElement(By.xpath("//label[@for='departure']/parent::div")).click();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,125)", "");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//div[@aria-label='Fri Mar 29 2024']")).click();

        //click on search button
        driver.findElement(By.xpath("//li[contains(text(),'One Way')]")).click();
        driver.findElement(By.xpath("//a[contains(text(),'Search')]")).click();

        Thread.sleep(3000);

        //get price per adult
        Thread.sleep(8000);
        driver.findElement(By.xpath("//button[contains(text(),'OKAY, GOT IT!')]")).click();
        List<WebElement> priceElements = driver.findElements(By.xpath("//div[contains(@class,'clusterViewPrice')]"));
        
        Thread.sleep(20000);
        System.out.println(priceElements.size());
        js.executeScript("window.scrollBy(0,600)", "");
        for(WebElement price : priceElements){
            
            System.out.println(price.getText());
        }
        

        System.out.println("end Test case: testCase02");
    }

    public void testCase03() throws InterruptedException{
        Actions action = new Actions(driver);
        System.out.println("Start Test Case: testCase03");
        driver.get("https://www.makemytrip.com/");
        // click on train
        driver.findElement(By.className("menu_Trains")).click();

        //send key 'YPR'
        Thread.sleep(4000);
        driver.findElement(By.id("fromCity")).click();
        driver.findElement(By.xpath("//input[@placeholder='From']")).sendKeys("ypr");
        Thread.sleep(4000);
        // select the Bangalore option
        driver.findElement(By.xpath("//span[@class='sr_city blackText' and contains(text(),'Bangalore')]")).click();

        //send keys 'NDLS'
        Thread.sleep(4000);
        driver.findElement(By.xpath("//label[@for='toCity']/parent::div")).click();
        Thread.sleep(4000);
        driver.findElement(By.xpath("//input[@placeholder='To']")).sendKeys("ndls");
        
        //select the New Delhi option
        WebElement suggestion = driver.findElement(By.xpath("//span[@class='sr_city blackText' and contains(text(),'Delhi')]"));
        Thread.sleep(4000);
        action.moveToElement(suggestion).click().perform();

        // select date 
        
        driver.findElement(By.xpath("//label[@class='atoBold makeFlex column']/parent::div")).click();
        Thread.sleep(4000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,150)", "");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//div[@aria-label='Fri Mar 29 2024']")).click();

        //select class type
        Thread.sleep(4000);
        driver.findElement(By.xpath("//label[@for='travelClass']/parent::div")).click();
        Thread.sleep(4000);
        driver.findElement(By.xpath("//label[@for='travelClass']/parent::div")).click();
        WebElement selectClass3A = driver.findElement(By.xpath("//li[contains(text(),'Third AC') and @data-cy='3A']"));
        action.moveToElement(selectClass3A).click().perform();

        //click on select button
        Thread.sleep(4000);
        driver.findElement(By.xpath("//a[contains(text(),'Search')]")).click();

        Thread.sleep(3000);

        List<WebElement> priceOfTrain3AC = driver.findElements(By.xpath("//div[@class='flex-column flex m-r-15']//div[@class='rail-class' and contains(text(),'3A')]//parent::div/following-sibling::div[@class='ticket-price justify-flex-end']"));
        Thread.sleep(10000);
        for(WebElement price : priceOfTrain3AC){
            System.out.println(price.getText());
        }
        



        System.out.println("end Test case: testCase03");
    }

    public void testCase04() throws InterruptedException {
        
        Actions action = new Actions(driver);
        System.out.println("Start Test Case: testCase04");
        driver.get("https://www.makemytrip.com/");

        //click on bus
        driver.findElement(By.className("menu_Buses")).click();

        //send key 'bangl'
        Thread.sleep(4000);
        driver.findElement(By.id("fromCity")).click();
        driver.findElement(By.xpath("//input[@placeholder='From']")).sendKeys("bangl");
        Thread.sleep(4000);
        // select the Bangalore option
        driver.findElement(By.xpath("//span[@class='sr_city blackText' and contains(text(),'Bangalore')]")).click();

        //send keys 'del'
        Thread.sleep(4000);
        
        driver.findElement(By.xpath("//label[@for='toCity']/parent::div")).click();
        Thread.sleep(4000);
        driver.findElement(By.xpath("//input[@placeholder='To']")).sendKeys("del");
        
        //select the New Delhi option
        WebElement suggestion = driver.findElement(By.xpath("//span[@class='sr_city blackText' and contains(text(),'Delhi')]"));
        Thread.sleep(4000);
        action.moveToElement(suggestion).click().perform();

        // select date 
        
        driver.findElement(By.xpath("//label[@for='travelDate']/parent::div")).click();
        Thread.sleep(4000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,150)", "");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//div[@aria-label='Fri Mar 29 2024']")).click();

        //click on search button
        Thread.sleep(4000);
        driver.findElement(By.id("search_button")).click();

        //validate "No buses found for 29 Mar"
        System.out.println("validate No buses found for 29 Mar");
        String noBusMsg = driver.findElement(By.className("error-title")).getText();

        if(noBusMsg.contains("No buses found for 29 Mar")){
            System.out.println("verified" );
        }
        else
        System.out.println("not verified");



        System.out.println("end Test case: testCase04");
    }


}
