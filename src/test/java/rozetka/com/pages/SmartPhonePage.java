package rozetka.com.pages;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import rozetka.com.models.SmartPhone;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by val on 04.05.2018.
 */

public class SmartPhonePage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindAll({@FindBy(xpath = "//i[contains(@class, 'popularity')]/ancestor::div[./div[@class='g-i-tile-i-box-desc']][1]")}) //3 вверх (родитель див) div[2]/div[4](class=g-i-tile-i-title clearfix)/a - name
    private List<WebElement> topSmarts;

    @FindAll({@FindBy(xpath = "//div[@data-view_type='catalog_with_hover']")})
    private List<WebElement> domSmarts;

    @FindBy(xpath = "./div[@class='g-price-uah']")
    private WebElement priceElement;

    @FindBy(xpath = "./div/div/div/div/div/a")
    private WebElement nameElement;

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

    public List<SmartPhone> findSmartsOnPage(int from, int to) {
        List<SmartPhone> list = new ArrayList<>();
        for(WebElement webElement: domSmarts) {
            String name = webElement.findElement(By.xpath("./div/div/div/div/div/a")).getText();
            int price = Integer.parseInt(webElement.findElement(By.className("g-price-uah")).getText().replaceAll("\\D", ""));
            if (price >= from && price <= to) {
                list.add(new SmartPhone(name, price));
            }
        }
        return list;
    }

    public List<SmartPhone> findTopSmartsOnPage() {
        List<SmartPhone> list = new ArrayList<>();
        for(WebElement webElement: topSmarts) {
            String name = webElement.findElement(By.className("g-i-tile-i-box-desc")).findElement(By.xpath("./div[4]/a")).getText();
            int price = Integer.parseInt(webElement.findElement(By.className("g-price-uah")).getText().replaceAll("\\D", ""));
            list.add(new SmartPhone(name, price));
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

    public File saveToExcelFile(List<SmartPhone> listPriceSmarts, String priceSmartsName, List<SmartPhone> listTopSmarts, String topSmartsName) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheetPrice = workbook.createSheet(priceSmartsName);
        HSSFSheet sheetTop = workbook.createSheet(topSmartsName);

        for (int i = 0; i < listPriceSmarts.size(); i ++) {
            Row row = sheetPrice.createRow(i);
            Cell name = row.createCell(0, CellType.STRING);
            Cell price = row.createCell(1, CellType.NUMERIC);
            name.setCellValue(listPriceSmarts.get(i).getName());
            price.setCellValue(listPriceSmarts.get(i).getPrice());
        }
        for (int i = 0; i < listTopSmarts.size(); i ++) {
            Row row = sheetTop.createRow(i);
            Cell name = row.createCell(0, CellType.STRING);
            Cell price = row.createCell(1, CellType.NUMERIC);
            name.setCellValue(listTopSmarts.get(i).getName());
            price.setCellValue(listTopSmarts.get(i).getPrice());
        }
        File file = new File("./src/test/file.xls");
        FileOutputStream fos = new FileOutputStream(file);
        workbook.write(fos);
        workbook.close();
        return file;
    }
}
