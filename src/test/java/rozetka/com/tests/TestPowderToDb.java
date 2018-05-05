package rozetka.com.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import rozetka.com.pages.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by val on 04.05.2018.
 */

public class TestPowderToDb extends BasePageInstance {
    public static HomePage homePage;
    public static HouseholdProductPage householdProductPage;
    public static ChemicalPage chemicalPage;
    public static WashePage washePage;
    public static WashingMeanPage washingMeanPage;
    public static PowderPage powderPage;

    @BeforeClass
    public void init() {
        homePage = new HomePage(driver);
        householdProductPage = new HouseholdProductPage(driver);
        chemicalPage = new ChemicalPage(driver);
        washePage = new WashePage(driver);
        washingMeanPage = new WashingMeanPage(driver);
        powderPage = new PowderPage(driver);
    }

    @Test
    public void startTest() {
        int pageNumber = 5;
        int priceFrom = 100;
        int priceTo = 300;
        homePage.openUrl();
        homePage.clickHouseholdProducts();
        householdProductPage.clickChemicals();
        chemicalPage.clickWashes();
        washePage.clickWashingMeans();
        washingMeanPage.clickPowders();
        Map<String, Integer> map = new HashMap<>();
        if (pageNumber >= 1) {
            for (int i = 1; i <= pageNumber; i++) {
                powderPage.clickNextPage(i);
                map.putAll(powderPage.sortMapFromTo(powderPage.getPowders(), priceFrom, priceTo));
            }
        }
        //System.out.println("RESULT");
        //for (Map.Entry<String, Integer> pair: map.entrySet()) {
        //    String key = pair.getKey();
        //    int value = pair.getValue();
        //    System.out.println(key + " = " + value);
        //}
    }
}
