import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.ProfilePage;

public class ProfileTests extends BaseTest {


    @DataProvider(name = "ProfileNameChangeTest")
    public static Object[] getDataFromDataProvider() {
        return new Object[]{"Deep", "ABC", "USER"};


    }

    @Test(dataProvider = "ProfileNameChangeTest")

    public void profileNameChangeTest(String currentName, String newName) throws InterruptedException {

        provideEmail("demo@koel.dev");
        Thread.sleep(1000);

        providePassword("demo");
        Thread.sleep(1000);

        clickLogin();
        Thread.sleep(1000);

        clickOnProfileAvatar();
        Thread.sleep(2000);


        enterCurrentPassword("demo");
        Thread.sleep(1000);

        editName("Deep");
        Thread.sleep(1000);

        saveChanges();
        Thread.sleep(1000);

        verifySuccessMessage();
        Thread.sleep(2000);


    }

    public void clickOnProfileAvatar() {
        WebElement profileIcon = driver.findElement(By.cssSelector("img[alt='Avatar of Koel']"));
        profileIcon.click();
    }

    public void editName(String newName) {
        WebElement editNameField = driver.findElement(By.cssSelector("input[id='inputProfileName']"));
        editNameField.click();
        editNameField.sendKeys(newName);

    }

    public void saveChanges() {

        WebElement saveChanges = driver.findElement(By.cssSelector("button[class='btn-submit']"));
        saveChanges.click();
    }

    public void enterCurrentPassword(String currentPassword) {
        WebElement currentPasswordField = driver.findElement(By.cssSelector("input[name='current_password']"));
        currentPasswordField.click();
        currentPasswordField.clear();
        currentPasswordField.sendKeys(currentPassword);

    }

    public void verifySuccessMessage() {
        WebElement profileUpdate = driver.findElement(By.xpath("//*[@id='app']/ul/li/div/main"));
        Assert.assertTrue(profileUpdate.isDisplayed());
    }

    @Test
    public void profileTests() {
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);

        ProfilePage profilePage = new ProfilePage(driver);


        provideEmail("demo@koel.dev");
        providePassword("demo");
        clickLogin();
        clickOnProfileAvatar();
        editName("Deep");
        saveChanges();
        verifySuccessMessage();
        //Assert.assertTrue(profilePage.verifySuccessMessage().isDisplayed());

    }
}
