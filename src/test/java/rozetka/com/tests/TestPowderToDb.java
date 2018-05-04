package rozetka.com.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import rozetka.com.pages.*;

/**
 * Created by val on 04.05.2018.
 */

public class TestPowderToDb extends BasePageInstance {
    public static HomePage homePage;
    public static HouseholdProductPage householdProductPage;
    public static ChemicalPage chemicalPage;
    public static WashePage washePage;
    public static WashingMeanPage washingMeanPage;


    @BeforeClass
    public void init() {
        homePage = new HomePage(driver);
        householdProductPage = new HouseholdProductPage(driver);
        chemicalPage = new ChemicalPage(driver);
        washePage = new WashePage(driver);
        washingMeanPage = new WashingMeanPage(driver);
    }

    @Test
    public void startTest() {
        homePage.openUrl();
        homePage.clickHouseholdProducts();
        householdProductPage.clickChemicals();
        chemicalPage.clickWashes();
        washePage.clickWashingMeans();
        washingMeanPage.clickPowders();
    }
}
