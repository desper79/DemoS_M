import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.my.test.pages.ConfProperties;

public class TestBase {
    public WebDriver driver;

    @Before
    public void start()  {
        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));
        driver = new ChromeDriver();
    }

    @After
    public void tearDown() {
        driver.quit();
        driver = null;
    }

}