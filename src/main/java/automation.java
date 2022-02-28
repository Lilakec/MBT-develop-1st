
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.offset.PointOption;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import org.testng.Assert;

import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.net.MalformedURLException;

import org.graphwalker.core.machine.ExecutionContext;
import org.graphwalker.java.annotation.GraphWalker;
import org.graphwalker.java.annotation.BeforeExecution;
import org.graphwalker.java.annotation.AfterExecution;

@GraphWalker(value = "random(edge_coverage(100))", start = "v_personalizedMainPage")
public class automation extends ExecutionContext implements tbClient {

    AndroidDriver<AndroidElement> driver;
    DesiredCapabilities DC = new DesiredCapabilities();
    private boolean fromFrsToPb = false;

    @BeforeExecution
    public void setUpApp (){

        DC.setCapability(MobileCapabilityType.DEVICE_NAME, "TPG5T18328000501");
        DC.setCapability("platformName", "android");
        DC.setCapability("appPackage", "com.baidu.tieba");
        DC.setCapability("appActivity", ".tblauncher.MainTabActivity");

        try {
            driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"),DC);
        } catch (MalformedURLException e) {
            System.out.println(e.getMessage());
        }

        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        Assert.assertNotEquals(null, driver);

        driver.findElementById("com.baidu.tieba:id/private_yes").click();
        driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout[5]/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.ImageView").click();
        driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View[3]/android.view.View/android.view.View[2]/android.view.View/android.view.View/android.view.View/android.widget.Button").click();

        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @AfterExecution
    public void tearDown() {
        driver.quit();
    }

    public boolean byElementIsExist(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (Exception e) {
            // TODO: handle exception
            return false;
        }
    }

    public void swipeUp() {
        AndroidTouchAction action = new AndroidTouchAction(driver);
        int width = driver.manage().window().getSize().getWidth();
        int height = driver.manage().window().getSize().getHeight();
        action.press(PointOption.point(width/2, height*7/8)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
                .moveTo(PointOption.point(width/2, height/8)).release().perform();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void swipeDown() {
        AndroidTouchAction action = new AndroidTouchAction(driver);
        int width = driver.manage().window().getSize().getWidth();
        int height = driver.manage().window().getSize().getHeight();
        action.press(PointOption.point(width/2, height/8)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
                .moveTo(PointOption.point(width/2, height*7/8)).release().perform();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void swipeLeft() {
        AndroidTouchAction action = new AndroidTouchAction(driver);
        int width = driver.manage().window().getSize().getWidth();
        int height = driver.manage().window().getSize().getHeight();
        action.press(PointOption.point(width*7/8, height/2)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                .moveTo(PointOption.point(width/8, height/2)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1))).release().perform();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void swipeRight() {
        AndroidTouchAction action = new AndroidTouchAction(driver);
        int width = driver.manage().window().getSize().getWidth();
        int height = driver.manage().window().getSize().getHeight();
        action.press(PointOption.point(width/8, height/2)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                .moveTo(PointOption.point(width*7/8, height/2)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1))).release().perform();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void multiTouch(int x1, int y1, int x2, int y2, int x3, int y3) {
        MultiTouchAction multiAction = new MultiTouchAction(driver);
        AndroidTouchAction action1 = new AndroidTouchAction(driver);
        AndroidTouchAction action2 = new AndroidTouchAction(driver);
        AndroidTouchAction action3 = new AndroidTouchAction(driver);
        AndroidTouchAction action4 = new AndroidTouchAction(driver);
        AndroidTouchAction action5 = new AndroidTouchAction(driver);
        action1.longPress(PointOption.point(x1, y1)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                .release();
        action2.longPress(PointOption.point(x2, y2)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                .release();
        action3.longPress(PointOption.point(x3, y3)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                .release();
        multiAction.add(action1).add(action2).add(action3).add(action4).add(action5).perform();
    }

    public String getToast() {
        return driver.findElementByXPath("//*[@class='android.widget.Toast']").getText();
    }

    @Override
    public void e_backToFrsFromImg() {
        driver.findElementById("com.baidu.tieba:id/widget_navi_back_button").click();
    }

    @Override
    public void e_changeSortTypePb() {
        while(!byElementIsExist(By.id("com.baidu.tieba:id/pb_sort_switch_btn"))) {
            swipeUp();
        }
        driver.findElementById("com.baidu.tieba:id/pb_sort_switch_btn").click();
    }

    @Override
    public void v_thePublisher() {
        Assert.assertTrue(driver.findElementById("com.baidu.tieba:id/publish_btn").isDisplayed());
    }

    @Override
    public void e_enterFrsPage() {
        driver.findElementById("com.baidu.tieba:id/pb_nav_title_forum_name").click();
    }

    @Override
    public void e_backToMainPage() {
        if (byElementIsExist(By.id("com.baidu.tieba:id/home_page_search_icon")) || byElementIsExist(By.id("com.baidu.tieba:id/search"))){
            swipeLeft();
        } else {
            driver.findElementById("com.baidu.tieba:id/widget_navi_back_button").click();
        }
    }

    @Override
    public void e_pbClickMore() {
        driver.findElementById("com.baidu.tieba:id/navigationBarBtnMore").click();
    }

    @Override
    public void e_showAllReply() {
        while(!byElementIsExist(By.id("com.baidu.tieba:id/reply_title"))) {
            swipeUp();
        }
        driver.findElementById("com.baidu.tieba:id/reply_title").click();
    }

    @Override
    public void v_frsMore() {
        Assert.assertTrue(driver.findElementById("com.baidu.tieba:id/btnShareCancel").isDisplayed());
    }

    @Override
    public void v_decorChoose() {
        Assert.assertTrue(driver.findElementById("com.baidu.tieba:id/uninterested_btn").isDisplayed());
    }

    @Override
    public void e_uninterestBtnClick() {
        driver.findElementById("com.baidu.tieba:id/uninterested_btn").click();
    }

    @Override
    public void e_goToPbPage() {
        if (!byElementIsExist(By.id("com.baidu.tieba:id/home_page_search_icon")) && !byElementIsExist(By.id("com.baidu.tieba:id/search"))){
            fromFrsToPb = true;
        }
        if(!byElementIsExist(By.id("com.baidu.tieba:id/navigationBarBtnMore"))) {
            while (byElementIsExist(By.id("com.baidu.tieba:id/video_duration")) || !byElementIsExist(By.id("com.baidu.tieba:id/thread_info_commont_num"))) {
                swipeUp();
            }
            driver.findElementById("com.baidu.tieba:id/thread_info_commont_num").click();
        }
    }

    @Override
    public void v_pbPage() {
        Assert.assertTrue(driver.findElementById("com.baidu.tieba:id/navigationBarBtnMore").isDisplayed());
    }

    @Override
    public void e_clickPublishBtn() {
        while (!byElementIsExist(By.id("com.baidu.tieba:id/publish_btn"))) {
            swipeDown();
        }
        driver.findElementById("com.baidu.tieba:id/publish_btn").click();
    }

    @Override
    public void v_personalizedMainPage() {
        boolean isMain = false;
        if (byElementIsExist(By.id("com.baidu.tieba:id/home_page_search_icon")) || byElementIsExist(By.id("com.baidu.tieba:id/search"))){
            isMain = true;
        }
        Assert.assertEquals(isMain,true);
    }

    @Override
    public void e_backToMainFromImg() {
        driver.findElementById("com.baidu.tieba:id/widget_navi_back_button").click();
    }

    @Override
    public void v_pbMore() {
        Assert.assertTrue(driver.findElementById("com.baidu.tieba:id/pb_more_cancel").isDisplayed());
    }

    @Override
    public void v_imgPage() {
        Assert.assertTrue(driver.findElementById("com.baidu.tieba:id/host_all_switch_btn").isDisplayed());
    }

    @Override
    public void e_frsCancelShare() {
        driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.TextView[2]").click();
    }

    @Override
    public void e_clickDecor() {
        while (!byElementIsExist(By.id("com.baidu.tieba:id/decor_item_right_id"))) {
            swipeUp();
        }
        driver.findElementById("com.baidu.tieba:id/decor_item_right_id").click();
    }

    @Override
    public void v_frsPageShare() {
        Assert.assertTrue(driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.TextView[2]").isDisplayed());
    }

    @Override
    public void e_closeThePublisher() {
        driver.findElementById("com.baidu.tieba:id/publish_btn").click();
    }

    @Override
    public void e_frsClickMore() {
        while (!byElementIsExist(By.xpath("//android.widget.ImageView[@content-desc=\"分享\"]"))) {
            swipeDown();
        }
        driver.findElementByAccessibilityId("分享").click();
    }

    @Override
    public void e_onlyTheOwner() {
        while(!byElementIsExist(By.id("com.baidu.tieba:id/floor_owner_reply"))) {
            swipeUp();
        }
        driver.findElementById("com.baidu.tieba:id/floor_owner_reply").click();
    }

    @Override
    public void e_backToFrsPage() {
        driver.findElementById("com.baidu.tieba:id/widget_navi_back_button").click();
    }

    @Override
    public void e_backToPbPage() {
        driver.findElementById("com.baidu.tieba:id/widget_navi_back_button").click();
        if(fromFrsToPb){
            e_goToPbPage();
        }
    }

    @Override
    public void v_frsPage() {
        boolean pagefrs = false;
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        swipeDown();
        if(byElementIsExist(By.id("com.baidu.tieba:id/navi_right_button")) || byElementIsExist(By.id("com.baidu.tieba:id/forum_name"))){
            pagefrs = true;
        }
        Assert.assertEquals(pagefrs,true);
    }

    @Override
    public void e_enterImgPage() {
        while (!byElementIsExist(By.id("com.baidu.tieba:id/thread_card_img_singal")) && !byElementIsExist(By.id("com.baidu.tieba:id/thread_card_img_more_one"))) {
            swipeUp();
        }
        if(byElementIsExist(By.id("com.baidu.tieba:id/thread_card_img_singal"))){
            driver.findElementById("com.baidu.tieba:id/thread_card_img_singal").click();
        } else {
            driver.findElementById("com.baidu.tieba:id/thread_card_img_more_one").click();
        }
    }

    @Override
    public void e_changeToOtherTab() {
        swipeRight();
    }

    @Override
    public void e_clickCancelPb() {
        driver.findElementById("com.baidu.tieba:id/pb_more_cancel").click();
    }

    @Override
    public void v_showOwnerReply() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (!byElementIsExist(By.id("com.baidu.tieba:id/floor_owner_reply"))){
            swipeUp();
        }
        Assert.assertEquals(driver.findElementById("com.baidu.tieba:id/floor_owner_reply").isDisplayed(),true);
    }

    @Override
    public void e_frsClickShare() {
        while(!byElementIsExist(By.id("com.baidu.tieba:id/share_num_img"))){
            swipeUp();
        }
        driver.findElementById("com.baidu.tieba:id/share_num_img").click();
    }

    @Override
    public void v_NewVertex() {

    }

    @Override
    public void e_goToFrsPage() {
        while(!byElementIsExist(By.id("com.baidu.tieba:id/forum_head_barname"))) {
            swipeUp();
        }
        driver.findElementById("com.baidu.tieba:id/forum_head_barname").click();
    }

    @Override
    public void e_reFresh() {
        swipeDown();
    }

    @Override
    public void e_clickCancelFrs() {
        driver.findElementById("com.baidu.tieba:id/btnShareCancel").click();
    }

    @Override
    public void v_otherTab() {
        boolean isMain = false;
        if (byElementIsExist(By.id("com.baidu.tieba:id/home_page_search_icon")) || byElementIsExist(By.id("com.baidu.tieba:id/search"))){
            isMain = true;
        }
        Assert.assertEquals(isMain,true);
    }

    @Override
    public void e_NewEdge() {

    }

}
