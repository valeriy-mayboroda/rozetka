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

public class WashingMeanPage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(name = "vid-sredstva-71899_301219")
    private WebElement powders;

    public WashingMeanPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, 25, 1000);
    }

    public void clickPowders() {
        powders.click();
        String sideFilterXpath = "//a[contains(@name, 'vid-sredstva-71899_301219')]//span[contains(@class,'filter-parametrs-i-l-i-checkbox-active')]";
        String upperFilterXpath = "//div[contains(@class, 'filter-active')]//a[contains(@class, 'filter-active-i-link') and text()='Порошок']";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(sideFilterXpath)));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(upperFilterXpath)));
    }
}
