import io.appium.java_client.AppiumDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FirstTest {
    AppiumDriver driver;
    PageObjectClass pageObjectClass;

    @BeforeMethod
    public void precondition() {
        driver = new DriverInit2().getDriver();
        pageObjectClass = new PageObjectClass(driver);
    }

    @AfterMethod
    public void stopDriver() {
        driver.quit();
    }

    @Test
    public void testClock() {
        pageObjectClass.clickOnViewButton();
        pageObjectClass.openTextClock();
        int secondsToWait = 5;
        int startSeconds = pageObjectClass.getCurrentSeconds();
        try {
            Thread.sleep(secondsToWait * 1000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        int finishSeconds = pageObjectClass.getCurrentSeconds();
        Assert.assertEquals(finishSeconds - startSeconds, secondsToWait
                , "Timer works incorrect!!!!");
    }
    @Test
    public void checkContext(){
        pageObjectClass.clickOnViewButton();
        pageObjectClass.openWebView2();
        pageObjectClass.writeTextToWeb();
    }
}
