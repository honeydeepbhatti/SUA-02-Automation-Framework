package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class ProfilePage extends BasePage {
    public ProfilePage(WebDriver givenDriver) {
        super(givenDriver);
    }

    By emailField = By.cssSelector("input[type='email']");
    By passwordField = By.cssSelector("input[type='password']");
    By loginButton = By.cssSelector("button[type='submit']");
    By profileAvatar = By.cssSelector("img[alt='Avatar of Koel']");
    By currentPassword = By.cssSelector("input[name='current_password']");
    By editName = By.cssSelector("input[id='inputProfileName']");
    By saveChangesButton = By.cssSelector("button[class='btn-submit']");
    By successMessage = By.xpath("//*[@id='app']/ul/li/div/main");

    public void provideEmail(String email) {
        findElement(emailField).clear();
        findElement(emailField).sendKeys(email);
    }

    public void providePassword(String password) {
        findElement(passwordField).clear();
        findElement(passwordField).sendKeys(password);
    }

    public void clickLogin() {
        findElement(loginButton).click();
    }

    public void clickOnProfileAvatar() {
        findElement(profileAvatar).click();
    }

    public void enterCurrentPassword(String currentPassword) {
        WebElement passwordField = findElement(this.currentPassword);
        passwordField.clear();
        passwordField.sendKeys(currentPassword);
    }

    public void setEditProfileName(String newName) {
        WebElement nameField = findElement(editName);
        nameField.clear();
        nameField.sendKeys(newName);
    }

    public void saveChanges() {
        findElement(saveChangesButton).click();
    }

    public void verifySuccessMessage() {
        WebElement successMessageElement = findElement(successMessage);
        Assert.assertTrue(successMessageElement.isDisplayed());
    }
}
