import org.testng.annotations.DataProvider;

public class TestDataSets {

    @DataProvider(name = "NegativeLoginTestData")
    public static Object[][] getDataFromDataProviders(){
        return new Object[][] {
                {"invalid@email.com","invalidPass"},
                {"demo@koel.dev", "invalidPass"},
                {"invalidEmail@koel.dev","demo"},
                {"",""}
        };
    }
    @DataProvider(name = "ProfileNameChangeTestData")
    public static Object[][] getProfileNameChangeTestData() {
        return new Object[][] {
                {"ABC"},
                {"CDS"},
                {"HGF"},
        };
    }

}