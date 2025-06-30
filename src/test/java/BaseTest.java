import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.edge.*;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.*;
import org.openqa.selenium.support.ui.*;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Parameters;

import java.net.*;
import java.time.Duration;
import java.util.*;

public class BaseTest {

    private static final ThreadLocal<WebDriver> threadDriver = new ThreadLocal<>();
    private WebDriverWait wait;
    private FluentWait<WebDriver> fluentWait;
    private Actions actions;

    public WebDriver getDriver() {
        return threadDriver.get();
    }

    @BeforeSuite
    public void setupClass() {
        WebDriverManager.chromedriver().setup();
        WebDriverManager.edgedriver().setup();
        WebDriverManager.firefoxdriver().setup();
    }

    @BeforeMethod
    @Parameters({"BaseURL"})
    public void launchBrowser(String baseUrl) {
        try {
            threadDriver.set(pickBrowser(System.getProperty("browser")));
            getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            getDriver().manage().window().maximize();
            wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5)); // updated constructor
            fluentWait = new FluentWait<>(getDriver())
                    .withTimeout(Duration.ofSeconds(5))
                    .pollingEvery(Duration.ofMillis(150));
            actions = new Actions(getDriver());
            navigateToPage(baseUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();  // Log or handle the exception
        }
    }

    @AfterMethod
    public void tearDown() {
        if (threadDriver.get() != null) {
            threadDriver.get().quit();
            threadDriver.remove();
        }
    }

    public void navigateToPage(String url) {
        getDriver().get(url);
    }

    public void provideEmail(String email) {
        WebElement emailField = getDriver().findElement(By.cssSelector("input[type='email']"));
        emailField.clear();
        emailField.sendKeys(email);
    }

    public void providePassword(String password) {
        WebElement passwordField = getDriver().findElement(By.cssSelector("input[type='password']"));
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void clickLogin() {
        WebElement loginButton = getDriver().findElement(By.cssSelector("button[type='submit']"));
        loginButton.click();
    }

    public void logOut() {
        WebElement logoutButton = getDriver().findElement(By.cssSelector("button[data-title='Log out']"));
        logoutButton.click();
    }

    public static WebDriver pickBrowser(String browser) throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        String gridUrl = "http://192.168.55.103:4444"; // replace with your grid URL

        switch (browser) {
            case "firefox":
                return new FirefoxDriver();

            case "MicrosoftEdge":
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--remote-allow-origins=*");
                return new EdgeDriver(edgeOptions);

            case "grid-edge":
                caps.setCapability("browserName", "MicrosoftEdge");
                return new RemoteWebDriver(new URL(gridUrl), caps);

            case "grid-firefox":
                caps.setCapability("browserName", "firefox");
                return new RemoteWebDriver(new URL(gridUrl), caps);

            case "grid-chrome":
                caps.setCapability("browserName", "chrome");
                return new RemoteWebDriver(new URL(gridUrl), caps);

            case "cloud":
                return lambdaTest();

            default:
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--remote-allow-origins=*");
                return new ChromeDriver(chromeOptions);
        }
    }

    public static WebDriver lambdaTest() throws MalformedURLException {
        String hubURL = "https://hub.lambdatest.com/wd/hub";

        ChromeOptions browserOptions = new ChromeOptions();
        browserOptions.setPlatformName("Windows 10");
        browserOptions.setBrowserVersion("125");
        HashMap<String, Object> ltOptions = new HashMap<>();
        ltOptions.put("username", "honeydeepsingh17");
        ltOptions.put("accessKey", "9TduC15viZzSB99ToUdQWOzTJ7VUrYbM9i5OGEoNZDFX8fdp66");
        ltOptions.put("project", "Untitled");
        ltOptions.put("w3c", true);
        ltOptions.put("plugin", "java-testNG");
        browserOptions.setCapability("LT:Options", ltOptions);

        return new RemoteWebDriver(new URL(hubURL), browserOptions);
    }
}
