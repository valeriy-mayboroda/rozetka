package rozetka.com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by val on 04.05.2018.
 */

public class SmartPhonePage {
    private WebDriver driver;

    //@FindBy(xpath = "//*[@id=\"catalog_goods_block\"]/div/div/a")
    @FindBy(className = "g-i-more-link-text")
    private WebElement elsePage;

    @FindBy(xpath = "//*[@id=\"page2\"]/a")
    private WebElement secondPage;

    @FindBy(id = "page3")
    private WebElement thirdPage;

    @FindAll({@FindBy(xpath = "//*[@id=\"catalog_goods_block\"]/div/div/div/div/div/div/div/a")})
    private List<WebElement> elements;

    public SmartPhonePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void clickSecondPage() {secondPage.click();}
    public void clickThirdPage() {thirdPage.click();}
    //public void clickElsePage() {
        //elsePage.click();
    //}

    public List<String> getNames() {
        List<String> list = new ArrayList<>();
        for(WebElement webElement: elements) {
            list.add(webElement.getText());
        }
        return list;
    }

    public void save (List<String> list) {
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
    }
}
