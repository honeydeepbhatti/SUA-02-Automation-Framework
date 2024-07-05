import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;
import java.net.URI;
import java.time.Duration;

public class BaseTest {

    public WebDriver driver;

    public String url = "https://demo.koel.dev/";

    public Object[][] getDataFromDataProviders(){
      return new Object[][] {
              {"invalid@gmail.com","invalidPass"},
              {"demo@koel.dev","invalidPass"},
              {"invalidEmail@demo.dev","demo"},
              {"",""},
      };
    }


    @BeforeSuite
    static void setupClass() {
       // WebDriverManager.chromedriver().setup();
        //WebDriverManager.edgedriver().setup();
    }

    @BeforeMethod
    @Parameters({"BaseURL"})
    public void launchBrowser(String baseUrl) throws MalformedURLException {
       //EdgeOptions options = new EdgeOptions();
        //options.addArguments("--remote-allow-origins=*");
        //options.addArguments("--disable-notifications");

        //driver = new EdgeDriver(options);
        driver = pickBrowser(System.getProperty("browser"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        navigateToPage(baseUrl);

    }
    @AfterMethod
    public void closeBrowser(){
        driver.quit();
    }


    public void navigateToPage(String baseUrl){

        driver.get(url);

    }

    public void provideEmail(String email){

        WebElement emailField = driver.findElement(By.cssSelector("input[type='email']"));
        emailField.clear();
        emailField.sendKeys(email);

    }

    public void providePassword(String password){

        WebElement passwordField = driver.findElement(By.cssSelector("input[type='password']"));
        passwordField.clear();
        passwordField.sendKeys(password);

    }

    public void clickLogin(){

        WebElement loginButton = driver.findElement(By.cssSelector("button[type='submit']"));
        loginButton.click();

    }

    public void logOut(){

        WebElement logoutButton = driver.findElement(By.cssSelector("button[data-title='Log out']"));
        logoutButton.click();

    }


        public static WebDriver pickBrowser(String browser) throws MalformedURLException {

        DesiredCapabilities caps = new DesiredCapabilities();
        String gridURL = "http://10.0.0.188:4444";

        switch (browser) {
            case "Firefox":
                WebDriverManager.firefoxdriver().setup();
                return driver = new FirefoxDriver();

            case "MicrosoftEdge":
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--remote-allow-origins=*");
                return driver = new EdgeDriver(edgeOptions);

            case "grid-edge":
                caps.setCapability("browserName", "MicrosoftEdge");
                return driver = new RemoteWebDriver(URI.create(gridURL).toURL(), caps);

            case "grid-firefox":
                caps.setCapability("browserName", "firefox");
                return driver = new RemoteWebDriver(URI.create(gridURL).toURL(), caps);

            default:
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--remote-allow-origins=*");
                return driver = new ChromeDriver(chromeOptions);
        }
    }
}