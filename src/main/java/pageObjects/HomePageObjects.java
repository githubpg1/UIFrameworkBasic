package pageObjects;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Hello world!
 *
 */
public class HomePageObjects {
	WebDriver driver;

	public HomePageObjects(WebDriver driver) {
		this.driver = driver;
		// This initElements method will create all WebElements
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//span[text()=\"Hi\"]")
	WebElement languageHindi;

	@FindBy(xpath = "//span[@class=\"MuiButton-startIcon MuiButton-iconSizeSmall\"]")
	WebElement language;

	@FindBy(xpath = "//span[text()=\"Eng\"]")
	WebElement languageEnglish;

	@FindBy(xpath = "//div[@class=\"leagueBar_button\"]//span[text()=\"All\"]")
	WebElement tabAll;

	@FindBy(tagName = "a")
	List<WebElement> aTag;

	@FindBy(id = "scrollable-auto-tab-1")
	WebElement cricketTab;

	@FindBy(xpath = "//div[@class=\"content\"]")
	public WebElement contentSegment;

	@FindBy(xpath = "//div[@class=\"card card-highlights-home \"]")
	List<WebElement> highlights;

	@FindBy(xpath = "//span[text()=\"Read More\"]")
	List<WebElement> readmore;

	//@FindBy(xpath = "//p[@class=\"StoryName\"]")
	@FindBy(xpath="//div[@id=\"story3\"]//div[starts-with(@class,\"mainDiv\")]")
	List<WebElement> webStoriesHome;
	
	@FindBy(xpath="//h2[text()=\"WebStories\"]")
	WebElement webStoryHeading;

	@FindBy(xpath = "//div[@class=\"letterbox\"]//p")
	List<WebElement> stories;

	@FindBy(xpath="//img[@alt=\"Harry Kane enters history books!\"]")
	WebElement firstStory;
	
	@FindBy(xpath = "/button[@aria-label=\"Next page\"]")
	WebElement nextStory;

	@FindBy(xpath = "//div[@class=\"i-amphtml-glass-pane\"]")
	WebElement ad;
	
	@FindBy(id="story3")
	//@FindBy(css=".mssp-1.add-padding")
	WebElement scrollStory;
	
	@FindBy(xpath="//button[@class=\"ms-scroll-right-btn\"]")
	WebElement rightButton;
	
	@FindBy(xpath="//div[@class=\"ms-player-scrollable-area\"]")
	WebElement scrollableArea;
	
	@FindBy(css ="div[class=\"i-amphtml-fill-content i-amphtml-story-player-shadow-root-intermediary\"]")
	WebElement shadow;
	
	@FindBy(css="div:nth-child(1) > iframe:nth-child(3)")
	WebElement frame;

	public void setLanguage(String lang) {

		if (lang.equalsIgnoreCase("Hindi"))
			languageHindi.click();
		else if (lang.equalsIgnoreCase("English"))
			languageEnglish.click();
	}

	public void selectLanguage(String lang) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(language));
		language.click();
	}

	public void selectAllTab() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(tabAll));
		tabAll.click();
	}

	public Iterator<WebElement> findLinkElement() {

		Iterator<WebElement> it = aTag.iterator();
		return it;
	}

	public void clickCricketTab() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(cricketTab));
		cricketTab.click();
	}

	public Boolean verifyCricketSelected() {
		String select = cricketTab.getAttribute("aria-selected");
		if (select.equalsIgnoreCase("true"))
			return true;
		else
			return false;
	}
	
	public void getText() {
		
		
		
	}

	public void verifyStoryHeading() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		System.out.println("Checking web story before "+ webStoriesHome);
		wait.until(ExpectedConditions.visibilityOf(webStoryHeading));
		/*
		 * System.out.println("Checking web story after 11 "+ webStoriesHome);
		 * System.out.println("Heading "+webStoryHeading.getText()+"and "
		 * +webStoryHeading); System.out.println("Firststory "+firstStory);
		 * JavascriptExecutor je =(JavascriptExecutor)driver;
		 * je.executeScript("arguments[0].scrollIntoView(true);", webStoryHeading);
		 * wait.until(ExpectedConditions.visibilityOf(firstStory));
		 * System.out.println("Checking web story after "+ webStoriesHome);
		 * firstStory.click(); Thread.sleep(3000);
		 */
	}
	
	public void scrollStory() throws InterruptedException {
		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(3));
		wait.until(ExpectedConditions.visibilityOf(scrollStory));
		JavascriptExecutor js = (JavascriptExecutor)driver; 
		js.executeScript("arguments[0].scrollIntoView(true);", scrollStory);
		
		/*
		 * Actions a = new Actions(driver); a.moveToElement(scrollStory, 100,
		 * 0).build().perform();
		 */
		int i=0;
		
		while(true) {
			i++;
			
			//scrollableArea.click();
			Actions action = new Actions(driver);
			action.moveByOffset(590, 338).click().build().perform();
			Thread.sleep(10000);
			action.sendKeys(Keys.ARROW_RIGHT);
			//scrollableArea.sendKeys(Keys.ARROW_RIGHT);
			System.out.println("Value of i "+ i);
			if(i<90) {
				break;
			}
		}
		/*
		 * for(int i=0;i<300;i++) { js.executeScript("arguments[0].click();",
		 * rightButton);
		 * wait.until(ExpectedConditions.elementToBeClickable(rightButton));
		 * System.out.println("inside the loop "+i);
		 * 
		 * }
		 */
		 
		//rightButton.click();
		//js.executeScript(driver.execute_script("arguments[0].scrollIntoView()", scrollStory);
		//js.executeScript("document.getElementById('gvLocationHorizontalRail').scrollLeft += 250", "");
		//js.executeScript("document.querySelector(scrollStory).scrollLeft=1000");
	}
	public void getStories() throws InterruptedException {
		// int i=0;
		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(3));
		wait.until(ExpectedConditions.visibilityOf(scrollStory));
		JavascriptExecutor js = (JavascriptExecutor)driver; 
		js.executeScript("arguments[0].scrollIntoView(true);", scrollStory);
		firstStory.click();
		Thread.sleep(3000);
		
		//This Element is inside single shadow DOM.
		
		SearchContext shadowroot = shadow.getShadowRoot();
		Thread.sleep(1000);
		WebElement iframe= shadowroot.findElement(By.cssSelector(" div:nth-child(1) > iframe:nth-child(3)"));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframe));
		
		List<WebElement> please = driver.findElements(By.xpath("//div[@class=\"letterbox\"]//p"));
		
		System.out.println("storiess "+ please);
		int count = 0;
		for (int i =0; i<please.size();i++) {
			count++;
			//String cssSelectorForHost1 = ".i-amphtml-fill-content.i-amphtml-story-player-shadow-root-intermediary";
			Thread.sleep(1000);
			 shadowroot = shadow.getShadowRoot();
			Thread.sleep(1000);
			 iframe= shadowroot.findElement(By.cssSelector(" div:nth-child(1) > iframe:nth-child(3)"));
			
		
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframe));
			
			System.out.println("Stories " + stories.get(i));
			String storyText = stories.get(i).getText();
			// System.out.println(i+" "+storyText);
			System.out.println("Home story " + count+ " "+ storyText);
			
		}
		//wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframe));
		nextStory.click();
		
		/*if (ad.isDisplayed() != true) {
			System.out.println("No ad found");
			for (WebElement st : stories) {
				System.out.println("First story " + st.getText());
				nextButton.click();
			}*/
		
	}
	
	public void clickNextStory() {
		
		
	}
}
