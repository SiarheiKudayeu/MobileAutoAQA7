import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;

import java.time.Duration;

public class Swiper {
    public AppiumDriver driver;

    public Swiper(AppiumDriver driver) {
        this.driver = driver;
    }

    enum Directions {
        UP, DOWN
    }

    private void swipe(int startX, int startY, int finishX, int finishY) {
        new TouchAction<>(driver)
                .press(PointOption.point(startX, startY))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                .moveTo(PointOption.point(finishX, finishY))
                .release()
                .perform();
    }

    public void swipe(Directions directions) {
        int startX = 0;
        int startY = 0;
        int finishX = 0;
        int finishY = 0;
        Dimension screenSize = driver.manage().window().getSize();
        switch (directions) {
            case DOWN:
                startX = screenSize.width / 2;
                startY = (int) (screenSize.height * 0.8);
                finishX = screenSize.width / 2;
                finishY = (int) (screenSize.height * 0.2);
                break;
            case UP:
                startX = screenSize.width / 2;
                startY = (int) (screenSize.height * 0.2);
                finishX = screenSize.width / 2;
                finishY = (int) (screenSize.height * 0.8);
                break;
        }
        swipe(startX, startY, finishX, finishY);
    }

    public void swipe(Directions directions, int countOfSwipe) {
        for (int i = 0; i < countOfSwipe; i++) {
            swipe(directions);
        }
    }

    public void swipeUntilElementFound(Directions directions, By locator){
        while (driver.findElements(locator).isEmpty()){
            swipe(directions);
        }
    }
}
