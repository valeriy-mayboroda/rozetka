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
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#catalog_goods_block .g-i-tile-i-box-desc .g-i-tile-i-image")));
    }
}
