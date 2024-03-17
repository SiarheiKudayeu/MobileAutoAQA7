import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverInit {
    public ThreadLocal<AppiumDriver> driver =new ThreadLocal<>();
    ///Views
    //android.widget.TextView[@content-desc="TextClock"]
    //android.widget.TextView[4]
    public AppiumDriver getDriver(){
        try {
            initialDriver();
        }
        catch (MalformedURLException e){
            System.out.println(e.getMessage());
        }
        return this.driver.get();
    }
    public void setDriver(AppiumDriver driver){this.driver.set(driver);}

    public void initialDriver() throws MalformedURLException {
        startAppiumService(4723);
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android11");
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
        desiredCapabilities.setCapability("appPackage", "io.appium.android.apis");
        desiredCapabilities.setCapability("appActivity", "io.appium.android.apis.ApiDemos");
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
        desiredCapabilities.setCapability(MobileCapabilityType.UDID, "emulator-5554");
        setDriver(new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), desiredCapabilities));

    }

    public void startAppiumService(int portNumber){
        AppiumServiceBuilder builder = new AppiumServiceBuilder();
        builder.withIPAddress("127.0.0.1");
        builder.usingPort(portNumber);
        AppiumDriverLocalService service = AppiumDriverLocalService.buildService(builder);
        service.start();
    }
}
