package rozetka.com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by val on 03.05.2018.
 */

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;

    private String url = "http://rozetka.com.ua";

    @FindBy(id = "3361")
    private WebElement phoneTvElectronics;

    @FindBy(id = "5300")
    private WebElement householdProducts;

    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, 25, 1000);
    }

    public void openUrl() {driver.get(url);}
    public void clickPhoneTvElectronics() {phoneTvElectronics.click();}
    public void clickHouseholdProducts() {
        householdProducts.click();
        String xpathSelector = "//ul[contains(@class, 'breadcrumbs-catalog')]//span[contains(@class, 'breadcrumbs-catalog-title') and contains(text(),'Товары для дома')]";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathSelector)));
    }
}
