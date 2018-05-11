package execution;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;

public class Driver {

	public static AndroidDriver driver;	
	WebDriverWait wait;
	
	public static void main(String[] args) throws InterruptedException {		
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("platformName", "Android");
		cap.setCapability("deviceName", "ce031603cd396b2703");
		cap.setCapability("platformVersion", "6.0.1");	
		cap.setCapability("noReset", "True");
//		cap.setCapability("appPackage", "com.whatsapp");
//		cap.setCapability("appActivity", "com.whatsapp.Main -a");
		cap.setCapability("appPackage", "com.abercrombie.abercrombie");
		cap.setCapability("appActivity", ".ui.splash.SplashScreenActivity");
		try {
			driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), cap);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}			
		
		WebDriverWait wait = new WebDriverWait(driver, 90);
		
		//To select hamburger icon 
		driver.findElementByAndroidUIAutomator("UiSelector().description(\"More options\")").click();
		//To select New group button
		List<WebElement> Menu = driver.findElements(By.className("android.widget.LinearLayout"));
		Menu.get(0).click();
		//To select search button
		driver.findElement(By.id("menuitem_search")).click();
		//To enter contact name "contact"
		driver.findElement(By.id("search_src_text")).sendKeys("contact");	
		//To select the second result "contact1"
		List<WebElement> Contacts = driver.findElements(By.className("android.widget.RelativeLayout"));
		Contacts.get(1).click();		
		//To select next button
		driver.findElement(By.id("next_btn")).click(); 
		//To add a name to the group
		driver.findElement(By.id("group_name")).sendKeys("103");		 
		//To select the ok button
		driver.findElement(By.id("ok_btn")).click(); 		
		//To enter the first message across the group
		WebElement entry = driver.findElement(By.id("entry"));
		wait.until(ExpectedConditions.visibilityOf(entry));
		entry.sendKeys("Let me add Sri Sai");	
		//To select the first message across the group
		driver.findElement(By.id("send")).click(); 
		//To navigate to group info
		driver.findElement(By.id("conversation_contact")).click(); 
		//To select add a participant button
		driver.findElement(By.id("add_participant_text")).click();
		//To select search icon
		driver.findElement(By.id("menuitem_search")).click();
		//To enter contact2 in search bar
		driver.findElement(By.id("search_src_text")).sendKeys("Contact2");
		//To select contact2 from results
		Contacts.get(0).click();
		//To display the pop up message in console
		String message = driver.findElement(By.id("message")).getText();
		System.out.println("message displayed is: " +message);
		//To select ok button
		driver.findElement(By.id("button1")).click();
		Thread.sleep(3000);
		//To select back button
		driver.findElementByAndroidUIAutomator("UiSelector().description(\"Navigate up\")").click();
		 //Create object of TouchAction class.
		TouchAction Action = new TouchAction(driver);
		//Create action chain using TouchAction class reference to perform long press action on microphone button to send 10secs recorded audio
		Action.longPress(driver.findElement(By.id("voice_note_btn"))).waitAction(10000).release().perform();
		//To forward the recorded audio
		driver.findElement(By.id("forward")).click();
		//To select the search icon
		driver.findElement(By.id("menuitem_search")).click();
		//To enter a contact
		driver.findElement(By.id("search_src_text")).sendKeys("contact");		
		//To select contact1
		List<WebElement> Contact = driver.findElements(By.className("android.widget.RelativeLayout"));
		Contact.get(1).click();		
		//To select send button
		driver.findElement(By.id("send")).click(); 
		//To navigate back
		driver.findElement(By.id("back")).click(); 
		//To select the created group from chats
		driver.findElement(By.xpath("//*[@text='103']")).click();
		//To navigate to group info
		driver.findElement(By.id("conversation_contact")).click(); 
		//To display when was the group created and by whom
		String when = driver.findElement(By.id("conversation_contact_status")).getText();
		System.out.println("This group is: " +when);
		
		//To change the group name
		driver.findElement(By.id("change_subject_btn")).click();
		driver.findElement(By.id("edit_text")).sendKeys("104");
		driver.findElement(By.id("ok_btn")).click();
	
		//To display the no.of participants in the group
		String count = driver.findElement(By.id("participants_title")).getText();
		String[] Number = count.split(" ");
		System.out.println("No.of participants are: " +Number[0]);
		//To Mute the Group
		driver.findElement(By.id("mute_switch")).click();
		List<WebElement> radio = driver.findElements(By.id("text1"));
		radio.get(1).click();
		driver.findElement(By.id("button1")).click();
		// To Scroll Up
		Dimension size = driver.manage().window().getSize();		  
		  int starty = (int) (size.height * 0.90);
		  int endy = (int) (size.height * 0.60);
		  int startx = size.width / 2;
		  
		 //Swipe from Bottom to Top.
		  driver.swipe(startx, starty, startx, endy, 3000);	 
		  Thread.sleep(3000);
		
		//Select a participant to remove	  
		WebElement Remov = driver.findElement(By.xpath("//*[@text='Contact1']"));		
		wait.until(ExpectedConditions.visibilityOf(Remov));		
		Remov.click();
		
		//Select remove contact
		List<WebElement> Remove = driver.findElements(By.className("android.widget.LinearLayout"));
		Remove.get(4).click();	
		//Select ok to the pop up
		driver.findElement(By.id("button1")).click();		
		Thread.sleep(3000);
		//Select Exit group
		driver.findElement(By.id("exit_group_btn")).click();
		driver.findElement(By.id("button1")).click();
		Thread.sleep(3000);
		//Select delete group
		driver.findElement(By.id("exit_group_btn")).click();
		driver.findElement(By.id("button1")).click();
}
	
	

}
