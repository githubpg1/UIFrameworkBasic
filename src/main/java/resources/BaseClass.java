package resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseClass {

	public Properties prop;
	
	public WebDriver initializeDriver() throws IOException {
	
	System.setProperty("webdriver.chrome.driver", "/Users/prerana/chromedriver");
	WebDriver driver = new ChromeDriver();
	driver.manage().window().maximize();
	System.out.println("checling driver"+ driver);
	FileInputStream fis = new FileInputStream("/Users/prerana/eclipse-workspace/SportsTak/src/main/java/resources/data.properties");
	prop = new Properties();
	prop.load(fis);
	//prop.getProperty("url", "url not found");
	
	return driver;
	}
}
