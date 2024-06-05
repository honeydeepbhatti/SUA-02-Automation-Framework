import org.testng.annotations.Test;

public class addSongToPlaylist extends BaseTest {

    @Test
    public void addSongToPlaylistTest() throws InterruptedException {

        provideEmail("demo@koel.dev");s
        Thread.sleep(3000);

        providePassword("demo");
        Thread.sleep(3000);

        clickLogin();
        Thread.sleep(3000);


    }


}
