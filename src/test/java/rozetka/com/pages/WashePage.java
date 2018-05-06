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

public class WashePage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(xpath = "//*[@id=\"category_tree\"]//li[4]/a")
    private WebElement washingMeans;

    public WashePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, 25, 1000);
    }

    public void clickWashingMeans() {
        washingMeans.click();
        String xpathSelector = "//ul[contains(@class, 'breadcrumbs-catalog')]//span[contains(@class, 'breadcrumbs-catalog-title') and text()='Стиральные средства']";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathSelector)));
    }
}
