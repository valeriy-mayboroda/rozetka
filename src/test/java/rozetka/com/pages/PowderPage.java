package rozetka.com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        if (pageNumber != 1) {
            driver.findElement(pageBy).click();
        }
        wait.until(ExpectedConditions.
                visibilityOfElementLocated(By.cssSelector("#page" + pageNumber + ".active")));
    }

    public Map<String, Integer> getPowders() {
        Map map = new HashMap<String, Integer>();
        for(WebElement webElement: elements) {
            wait.until(ExpectedConditions.visibilityOf(webElement.findElement(By.className("g-price-uah"))));
            wait.until(ExpectedConditions.visibilityOf(webElement.findElement(By.xpath("./div[3]/a"))));
            int value = Integer.parseInt(webElement.findElement(By.className("g-price-uah")).getText().
                    replaceAll("\\D", ""));
            String key = webElement.findElement(By.xpath("./div[3]/a")).getText();
            map.put(key, value);
        }
        return map;
    }

    public Map<String, Integer> sortMapFromTo(Map<String, Integer> input, int from, int to) {
        Map result = new HashMap<String, Integer>();
        for (Map.Entry<String, Integer> pair: input.entrySet()) {
            String key = pair.getKey();
            int value = pair.getValue();
            if (value >= from && value <= to) {
                result.put(key, value);
            }
        }
        return result;
    }
}
