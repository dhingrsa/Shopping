import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.apache.commons.io.FileUtils;

public class TestCase1 {
  public static void main(String[] args) throws InterruptedException, IOException {

    System.setProperty("webdriver.chrome.driver","C:\\chromedriver.exe");	
	WebDriver driver=new ChromeDriver();
	driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
	driver.manage().window().maximize();
	driver.get("http://automationpractice.com/index.php"); //Navigate to web site
	driver.findElement(By.id("search_query_top")).sendKeys("Printed Summer Dress"); //Search for Printed Summer Dress
	driver.findElement(By.name("submit_search")).click();
	Select dropdown=new Select(driver.findElement(By.id("selectProductSort"))); //Select the cheapest dress
	dropdown.selectByVisibleText("Price: Lowest first");
    driver.findElement(By.xpath("//body/div[@id='page']/div[2]/div[1]/div[3]/div[2]/ul[1]/li[1]/div[1]/div[2]/h5[1]/a[1]")).click();
	for(int i=1;i<2;i++) //Change the Quantity from 1 to 2
	{
		driver.findElement(By.className("icon-plus")).click();
	}
	WebElement Size = driver.findElement(By.id("group_1")); //Change the Size from S to M
	Select dropdown2=new Select(Size); 
	dropdown2.selectByValue("2");
	driver.findElement(By.id("color_15")).click(); //Change the colour from Yellow to Green
	driver.findElement(By.xpath("//span[contains(text(),'Add to cart')]")).click(); //Select Add to Cart
	driver.findElement(By.xpath("//body[1]/div[1]/div[1]/header[1]/div[3]/div[1]/div[1]/div[4]/div[1]/div[2]/div[4]/a[1]/span[1]")).click();//Select Proceed to Checkout
	Assert.assertEquals(driver.findElement(By.id("total_price_container")).getText(), "$34.80");//Assert that the price equals $34.80
	File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE); //Take a screenshot of the cart and attach it along with your code
	FileUtils.copyFile(src, new File("D://Parts Trader/screenshot.png"));
	driver.quit();
	
	}

}
