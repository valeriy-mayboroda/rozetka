package rozetka.com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by val on 04.05.2018.
 */

public class PhonePage {
    private WebDriver driver;

    @FindBy(linkText = "Смартфоны")
    private WebElement smartPhones;

    public PhonePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void clickSmartPhones() {smartPhones.click();}
}
