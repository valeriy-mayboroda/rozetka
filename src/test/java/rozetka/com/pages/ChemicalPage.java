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
public class ChemicalPage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(linkText = "Для стирки")
    private WebElement washes;

    public ChemicalPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, 25, 1000);
    }

    public void clickWashes() {
        washes.click();
        String xpathSelector = "//ul[contains(@class, 'cat-tree-l')]//span[contains(@class, 'cat-tree-l-i-link') and contains(text(), 'Средства для стирки')]";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathSelector)));
    }
}
