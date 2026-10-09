import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class GoogleSearchTestOnIE {
    private WebDriver driver;
    @Before
    public void setUp() {
        System.setProperty("webdriver.ie.driver",
                "src/test/resources/drivers/IEDriverServer.exe");
        // TODO: 09.10.2026 naprawić  //DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
        //caps.setCapability(
         //       InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
          //      true);
//Uruchomienie programu Internet Explorer
        //driver = new InternetExplorerDriver(caps);

        //Zmaksymalizuj okno przeglądarki
        driver.manage().window().maximize();
//Przejdź do Google
        driver.get("http://www.google.com");
    }
    @Test
    public void testGoogleSearch() {
//Znajdź pole tekstowe za pomocą jego nazwy
        WebElement element = driver.findElement(By.name("q"));
// Wpisz coś do wyszukiwania
        element.sendKeys("Selenium i testowanie aplikacji. Receptury");
// Teraz prześlij formularz. WebDriver znajdzie
// formularz odpowiadający elementowi
        element.submit();
// Strona wyszukiwania w Google jest dynamicznie renderowana za pomocą JavaScript.
// Czekamy na załadowanie się strony. Timeout po 10 sekundach
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().toLowerCase()
                        .startsWith("Selenium i testowanie aplikacji. receptury");
            }
        });
        assertEquals("Selenium i testowanie aplikacji. Receptury - Szukaj w Google",
                driver.getTitle());
    }
    @After
    public void tearDown() throws Exception {
// Zamknij przeglądarkę
        driver.quit();
    }
}
