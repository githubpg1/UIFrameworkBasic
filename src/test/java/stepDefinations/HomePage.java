package stepDefinations;

import static org.junit.Assert.assertTrue;

import java.awt.Window;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.DataProvider;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.CricketTabObjects;
import pageObjects.HomePageObjects;
import resources.BaseClass;

/**
 * Unit test for simple App.
 */
public class HomePage extends BaseClass {
	WebDriver driver;
	String defaultUrl;
	String hindiUrl;
	HomePageObjects home;
	HttpURLConnection huc;
	String actualUrl;
	int respCode = 200;
	CricketTabObjects cricket;
	String parentTab;

	@Before
	public void initializeBrowser() throws IOException {

		driver = initializeDriver();
		defaultUrl = prop.getProperty("defaultUrl", "default url not found");
		hindiUrl = prop.getProperty("hindiUrl", "hindi url not found");
		System.out.println("URL is" + prop.getProperty("defaultUrl"));
		driver.get(defaultUrl);
		home = new HomePageObjects(driver);
		cricket = new CricketTabObjects(driver);
	}

	@Given("User lands on the {string} home page")
	public void user_lands_on_the_home_page(String link) {
		actualUrl = driver.getCurrentUrl();
		if (link.equalsIgnoreCase("default"))
			Assert.assertEquals("Home page Url doesn't match", defaultUrl, actualUrl);

		else if (link.equalsIgnoreCase("hindi"))
			Assert.assertEquals("Home page Url doesn't match", hindiUrl, actualUrl);
	}

	@Given("Select the language {string}")
	public void select_the_language(String lang) {

		home.selectLanguage(lang);
		actualUrl = driver.getCurrentUrl();

	}

	@Given("Verify user is on Home > All Page")
	public void verify_user_is_on_home_all_page() {
		home.selectAllTab();
	}

	@When("User clicks on each Thumbnails")
	public void user_clicks_on_each_thumbnails() {
		System.out.println("Pass");
	}

	@Then("Verify links are not broken")
	public void verify_links_are_not_broken() {
		huc = null;
		Iterator<WebElement> it = home.findLinkElement();

		while (it.hasNext()) {
			String url = it.next().getAttribute("href");
			System.out.println(url);

			if (url == null || url.isEmpty()) {
				System.out.println("URL is either not configured for anchor tag or it is empty");
				continue;
			}

			try {
				huc = (HttpURLConnection) (new URL(url).openConnection());

				// The HEAD method is identical to GET except that the server MUST NOT return a message-body in the response.
				huc.setRequestMethod("HEAD");

				huc.connect();

				respCode = huc.getResponseCode();

				if (respCode >= 400) {
					System.out.println(url + " is a broken link");
				} else {
					System.out.println(url + " is a valid link");
				}

			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	@Given("User clicks on the cricket tab")
	public void user_clicks_on_the_cricket_tab() {
		select_the_language("Hindi");
		home.clickCricketTab();
	}

	@Given("Verify Cricket Tab is selected")
	public void verify_cricket_tab_is_selected() {

		Assert.assertTrue(home.verifyCricketSelected());

	}

	@When("User scrolls to the 3rd page")
	public void user_scrolls_to_the_3rd_page() throws InterruptedException {

		parentTab = driver.getWindowHandle();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Long v = (Long) js.executeScript("return window.pageYOffset;");
		System.out.println("value of scroll " + v);
		// js.executeScript("arguments[0].scrollIntoView(true);", home.contentSegment);
		long d = 0;
		int count = 0;
		for (int i = 0; i < 3; i++) {

			js.executeScript("window.scrollTo(0,10000)");

			d = (long) js.executeScript("return window.pageYOffset;");
			System.out.println("value after scroll " + d);
			Thread.sleep(2000);
			count++;
		}

		Assert.assertTrue(count == 3);
	}

	@When("User clicks the links")
	public void user_clicks_the_links() {

		System.out.println("hee");
		cricket.clickThumbnails();
	}

	@Then("Verify Cricket Links are not broken")
	public void verify_cricket_links_are_not_broken() {
		huc = null;
		Iterator<WebElement> it = cricket.verifyThumbnailLink();

		while (it.hasNext()) {
			String url = it.next().getAttribute("href");
			System.out.println(url);

			if (url == null || url.isEmpty()) {
				System.out.println("URL is either not configured for anchor tag or it is empty");
				continue;
			}

			try {
				huc = (HttpURLConnection) (new URL(url).openConnection());

				huc.setRequestMethod("HEAD");

				huc.connect();

				respCode = huc.getResponseCode();

				if (respCode >= 400) {
					System.out.println(url + " is a broken link");
				} else {
					System.out.println(url + " is a valid link");
				}

			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	@Given("Verify Web Stories Heading is Visible")
	public void verify_web_stories_heading_is_visible() throws InterruptedException {
		home.verifyStoryHeading();
	}

	@Given("Capture Text on each Web Story")
	public void capture_text_on_each_web_story() throws InterruptedException {
		//home.scrollStory();
	}

	@When("User clicks on any web story")
	public void user_clicks_on_any_web_story() {
	}

	@Then("Capture text on each stories of the WebStory")
	public void capture_text_on_each_stories_of_the_web_story() throws InterruptedException {
		home.getStories();
	}
	

	@Then("User clicks next webstory button")
	public void user_clicks_next_webstory_button() {
	}

	/*
	 * @Then("switch to parent tab") public void switch_to_parent_tab() {
	 * 
	 * driver.switchTo().window(parentTab);
	 * 
	 * }
	 */
	

	@After
	public void tearDown() throws InterruptedException {
		Thread.sleep(15000);
		driver.quit();
	}

}
