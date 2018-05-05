package rozetka.com.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import rozetka.com.models.Powder;
import rozetka.com.pages.*;
import java.util.ArrayList;
import java.util.List;

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
        List<Powder> list = new ArrayList<>();
        for (int i = 1; i <= pageNumber; i++) {
            list.addAll(powderPage.getFilteredPowders(priceFrom, priceTo));
            if (i != pageNumber && pageNumber > 1) {
                powderPage.clickNextPage(i + 1);
            }
        }
        System.out.println("RESULT");
        for (Powder powder : list) {
            System.out.println(powder);
        }
    }
}
