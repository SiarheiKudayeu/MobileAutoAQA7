import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;

public class PageObjectClass {
    AppiumDriver driver;
    Swiper swiper;

    WebDriverWait wait;

    public PageObjectClass(AppiumDriver driver) {
        this.driver = driver;
        swiper = new Swiper(driver);
        wait = new WebDriverWait(driver, 10);
    }
    public enum Context{
        NATIVE, WEB
    }

    public void clickOnViewButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.AccessibilityId("Views"))).click();
    }

    public void scrollDown() {
        swiper.swipe(Swiper.Directions.DOWN);
    }

    public void scrollDown(int count) {
        for (int i = 0; i < count; i++) {
            scrollDown();
        }
    }

    public void openWebView2() {
        By textClock = MobileBy.AccessibilityId("WebView2");
        swiper.swipeUntilElementFound(Swiper.Directions.DOWN, textClock);
        driver.findElement(textClock).click();
    }
    public void openTextClock() {
        By textClock = MobileBy.AccessibilityId("TextClock");
        swiper.swipeUntilElementFound(Swiper.Directions.DOWN, textClock);
        driver.findElement(textClock).click();
    }

    public void printContextToConsole(){
    /*    try {
            Thread.sleep(2000);
        } catch (InterruptedException e){
            System.out.println(e.getMessage());
        }*/
        //i_am_a_textbox
        //android:id/content
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView")));
        Set<String> contexts = driver.getContextHandles();
        for(String context: contexts) {
            System.out.println(context);
        }

    }
    public void switchContext(Context context){
        switch (context){
            case WEB -> driver.context("WEBVIEW_io.appium.android.apis");
            case NATIVE -> driver.context("NATIVE_APP");
        }
    }

    public void writeTextToWeb(){
        System.out.println(driver.getContext());
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView")));
        switchContext(Context.WEB);
        System.out.println(driver.getContext());
        // adb
    }

    public int getCurrentSeconds() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[4]")));
        String time = driver.findElement(By.xpath("//android.widget.TextView[4]")).getText();
        //09:39:50 AM
        time = (time.split("\\s")[0]).split(":")[2];
        return Integer.parseInt(time);
    }

}
