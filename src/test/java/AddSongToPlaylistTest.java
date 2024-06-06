
import org.openqa.selenium.By;;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class AddSongToPlaylistTest extends BaseTest {

    private WebDriver driver;

    @Test
    public void songAddToPlaylistTest() throws InterruptedException {
        String expectedSongAddedSuccessMessage = "Added 1 song into \"All Time Favorites.\"";

        navigateToPage();

        provideEmail("demo@koel.dev");
        Thread.sleep(2000);

        providePassword("demo");
        Thread.sleep(2000);

        clickLogin();
        Thread.sleep(2000);

        searchSong("Dark");
        Thread.sleep(2000);

        clickViewAllSongsButton();
        Thread.sleep(2000);

        selectFirstSong();
        Thread.sleep(2000);

        clickAddToPlaylistButton();
        Thread.sleep(2000);

        choosePlaylist();
        Thread.sleep(2000);

        addToPlaylistSuccessfulMessage();
        Thread.sleep(2000);

        Assert.assertEquals(addToPlaylistSuccessfulMessage(), expectedSongAddedSuccessMessage);


    }

    public void searchSong(String songName) {
        WebElement searchField = driver.findElement(By.cssSelector("//form#searchForm input[type='search']"));
        searchField.click();
        searchField.sendKeys(songName);

    }
    
  public void clickViewAllSongsButton() {
        WebElement viewAllSongsButton = driver.findElement(By.xpath("//button[@data-testid='view-all-songs-btn']"));
        viewAllSongsButton.click();

  }

  public void selectFirstSong(){
        WebElement firstSongSelect = driver.findElement(By.xpath("//div[@data-testid='song-item][1]"));

        firstSongSelect.click();

    }

    public void clickAddToPlaylistButton(){
        WebElement addToPlaylistButton = driver.findElement(By.xpath("//*[@id='queueWrapper']/header/main/div[2]/div[2]/div/section/ul/li[6]"));
        addToPlaylistButton.click();
    }


    public void choosePlaylist(){
        WebElement addToButton = driver.findElement(By.xpath("//h1[normalize-space()='All Time Favorites']"));
        addToButton.click();
    }

    public String addToPlaylistSuccessfulMessage() {
        WebElement notification = driver.findElement(By.cssSelector("div.message.successful>main"));
        return notification.getText();


    }
}


