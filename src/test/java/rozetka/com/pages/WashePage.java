package rozetka.com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by val on 04.05.2018.
 */

public class WashePage {
    private WebDriver driver;

    @FindBy(xpath = "//*[@id=\"category_tree\"]//li[4]/a")
    private WebElement washingMeans;

    public WashePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void clickWashingMeans() {washingMeans.click();}
}
