import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.File;

public class LoginTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginTest.class);
    private WebDriver driver;
    

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        LOGGER.info("Przeglądarka została uruchomiona.");
    }

    @Test
    public void testSuccessfulLogin() {
        driver.get("https://example.com");
        LOGGER.info("Otwarto stronę główną.");

        // Twoja asercja, która np. celowo nie wychodzi
        org.testng.Assert.fail("Celowe niepowodzenie testu, aby sprawdzić screenshot.");
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        // Jeśli test się nie powiódł, wykonaj screenshot i wyślij do ReportPortal
        if (result.getStatus() == ITestResult.FAILURE && driver != null) {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            // Specjalny format logu rozpoznawany przez agenta ReportPortal do przesyłania plików
            LOGGER.error("RP_MESSAGE#FILE#{}#{}", screenshot.getAbsolutePath(), "Zrzut ekranu po niepowodzeniu testu.");
        }

        if (driver != null) {
            driver.quit();
            LOGGER.info("Przeglądarka została zamknięta.");
        }
    }

}
