import org.junit.*;
import ru.my.test.pages.OpenPage;
import ru.my.test.pages.SearchPage;

public class UserSearchTests extends TestBase {

    @Before
    public void open() {
        OpenPage OpenPage = new OpenPage(driver);
        OpenPage.open();
    }

    @After
    public void logout() {
    }

    @Test
    public void test_01() throws Exception {
        SearchAndCheck("iPhone XS 256gb", true, "По дате");
    }

    public void SearchAndCheck(String name, boolean ischeckboxPhotoOnly, String sortOrder) throws Exception {
        SearchPage MainPage = new SearchPage(driver);
        MainPage.SearchAndCheck(name, ischeckboxPhotoOnly, sortOrder);
    }

}
