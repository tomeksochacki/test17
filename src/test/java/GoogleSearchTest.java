import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chromium.ChromiumDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class GoogleSearchTest {

        private WebDriver driver;
        @Before
        public void setUp() {
// Uruchom nowy egzemplarz przeglądarki Firefox
            driver = new ChromeDriver();
//Zmaksymalizuj okno przeglądarki
            driver.manage().window().maximize();
// Przejdź do serwisu Google
            driver.get("http://www.google.com");
        }
        @Test
        public void testGoogleSearch() {
// Znajdź element wprowadzania tekstu za pomocą jego nazwy
            WebElement element = driver.findElement(By.name("q"));
// Wyczyść tekst zapisany w elemencie
            element.clear();
// Wpisz informacje do szukania
            element.sendKeys("Selenium testing tools cookbook");
// Prześlij formularz
            element.submit();
// Wyszukiwanie w Google jest renderowane dynamicznie za pomocą JavaScript.
// Poczekaj na załadowanie strony. Timeout po 10 sekundach
            new WebDriverWait(driver, Duration.ofSeconds(10)).until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver d) {
                    return d.getTitle().toLowerCase()
                            .startsWith("selenium testing tools cookbook");
                }
            });
            assertEquals("Selenium testing tools cookbook - Google Search",
                    driver.getTitle());
        }
        @After
        public void tearDown() throws Exception {
// Zamknij przeglądarkę
            driver.quit();
        }

}
