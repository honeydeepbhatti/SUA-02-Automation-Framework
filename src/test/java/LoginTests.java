import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTests extends BaseTest {
    @Test
    public void loginEmptyEmailPassword() {


        String url = "https://demo.koel.dev/";
        driver.get(url);
        Assert.assertEquals(driver.getCurrentUrl(), url);
    }

    @Test
    public void loginSuccess() throws InterruptedException {

        provideEmail("demo@koel.dev");
        Thread.sleep(3000);

        providePassword("demo");
        Thread.sleep(3000);

        clickLogin();
        Thread.sleep(3000);

        WebElement profileAvatar = driver.findElement(By.cssSelector("a.view-profile img"));

        Assert.assertTrue(profileAvatar.isDisplayed());
    }
}
