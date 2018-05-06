package rozetka.com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by val on 04.05.2018.
 */

public class HouseholdProductPage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(linkText = "Бытовая химия")
    private WebElement chemicals;

    public HouseholdProductPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, 25, 1000);
    }

    public void clickChemicals() {
        chemicals.click();
        String xpathSelector = "//ul[contains(@class, 'breadcrumbs-catalog')]//span[contains(@class, 'breadcrumbs-catalog-title') and text()='Бытовая химия']";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathSelector)));
    }
}
