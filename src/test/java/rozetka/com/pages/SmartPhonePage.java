package rozetka.com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by val on 04.05.2018.
 */

public class SmartPhonePage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindAll({@FindBy(xpath = "//*[@id=\"catalog_goods_block\"]/div/div/div/div/div/div/div/a")})
    private List<WebElement> elements;

    public SmartPhonePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, 15, 1000);
    }

    public void clickNextPage(int pageNumber) {
        By pageBy = By.id("page" + pageNumber);
        driver.findElement(pageBy).click();
        wait.until(ExpectedConditions.
                visibilityOfElementLocated(By.cssSelector("#page" + pageNumber + ".active")));
    }

    public List<String> getNames() {
        List<String> list = new ArrayList<>();
        for(WebElement webElement: elements) {
            list.add(webElement.getText());
        }
        return list;
    }

    public File saveToFile(List<String> list) {
        File file = new File("./src/test/file.txt");
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(file, "UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        for (String line : list){
            writer.println(line);
        }
        writer.flush();
        writer.close();
        return file;
    }
}
