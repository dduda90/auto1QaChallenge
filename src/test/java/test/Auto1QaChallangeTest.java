package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class Auto1QaChallangeTest {

    private WebDriver driver;

    @BeforeMethod
    public void beforeTest() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        driver = new ChromeDriver(chromeOptions);
    }

    @Test
    public void SearchEngineTest() {
        driver.navigate().to("https://www.autohero.com/de/search/");
        driver.findElement(By.xpath("//button[@id='firstRegistrationFilter']")).click();
        driver.findElement(By.xpath("//div[@aria-labelledby='firstRegistrationFilter']/..//select[@id='rangeStart']/option[@data-qa-selector-value='2015']")).click();
        driver.findElement(By.xpath("//select[@id='sortBy']/option[@data-qa-selector-value='offerPrice.amountMinorUnits.desc']")).click();

        String actualValueFromFirstRegistrationFilter = driver.findElement(By.xpath("//li[@data-qa-selector='active-filter'][@data-qa-selector-value='Ab 2015']/button")).getText();
        String expectedValueFromFirstRegistrationFilter = "Erstzulassung: Ab 2015";
        assertEquals(actualValueFromFirstRegistrationFilter, expectedValueFromFirstRegistrationFilter);

        String actualPageTitle = driver.getCurrentUrl();
        assertTrue(actualPageTitle.contains("?sort=PRICE_DESC&yearMin=2015"));

    }

    @AfterMethod
    public void afterTest() {
        driver.close();
        driver.quit();
    }
}