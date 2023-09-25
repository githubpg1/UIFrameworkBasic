package pageObjects;

import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CricketTabObjects {

	WebDriver driver;
	
	public CricketTabObjects(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath="//a[contains(@href,\"yuvraj\")]")
	WebElement thumbnail;
	
	@FindBy(xpath="//a/h2/parent::a")
	List<WebElement> cricketThumbnails;
	
	
	public Boolean verifyThumbnail() {
		
		if(thumbnail.isDisplayed()==true)
			return true;
		else
			return false;
		
	}
	
	public void clickThumbnails() {
		for(WebElement el:cricketThumbnails) {
			String clickThumbnail = el.getAttribute("href");
			//String clicklnk = Keys.chord(Keys.CONTROL,Keys.ENTER);
			//String keyString =   Keys.CONTROL+Keys.SHIFT.toString()+Keys.ENTER.toString();
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.open('"+clickThumbnail+"');");
			//el.sendKeys(keyString);
		}
	}
	
	public Iterator<WebElement> verifyThumbnailLink() {
		
		Iterator<WebElement> it= cricketThumbnails.iterator();
		return it;
	}
		

	
}
