package rozetka.com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import rozetka.com.models.Powder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by val on 05.05.2018.
 */

public class PowderPage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindAll({@FindBy(xpath = "//*[@id=\"catalog_goods_block\"]/div/div/div/div/div[1]/div")})
    private List<WebElement> elements;

    public PowderPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, 25, 1000);
    }

    public void clickNextPage(int pageNumber) {
        By pageBy = By.id("page" + pageNumber);
        driver.findElement(pageBy).click();
        wait.until(ExpectedConditions.
                visibilityOfElementLocated(By.cssSelector("#page" + pageNumber + ".active")));
    }

    public List<Powder> getPowders() {
        List<Powder> list = new ArrayList<>();
        for(WebElement webElement: elements) {
            wait.until(ExpectedConditions.visibilityOf(webElement.findElement(By.className("g-price-uah"))));
            wait.until(ExpectedConditions.visibilityOf(webElement.findElement(By.xpath("./div[3]/a"))));
            int price = Integer.parseInt(webElement.findElement(By.className("g-price-uah")).getText().
                    replaceAll("\\D", ""));
            String name = webElement.findElement(By.xpath("./div[3]/a")).getText();
            list.add(new Powder(name, price));
        }
        return list;
    }

    public List<Powder> getFilteredPowders(int from, int to) {
        return sortPowderFromTo(getPowders(), from, to);
    }

    public List<Powder> sortPowderFromTo(List<Powder> input, int from, int to) {
        List<Powder> result = new ArrayList<>();
        for (Powder powder : input) {
            int price = powder.getPrice();
            if (price >= from && price <= to) {
                result.add(powder);
            }
        }
        return result;
    }
}
