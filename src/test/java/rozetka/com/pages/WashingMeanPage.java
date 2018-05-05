package rozetka.com.pages;

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

    @FindBy(name = "vid-sredstva-71899_301219")
    private WebElement powders;

    public WashingMeanPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void clickPowders() {powders.click();}
}
