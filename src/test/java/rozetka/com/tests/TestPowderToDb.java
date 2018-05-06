package rozetka.com.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import rozetka.com.db.DBConnection;
import rozetka.com.models.Powder;
import rozetka.com.pages.*;
import java.sql.Connection;
import java.sql.SQLException;
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
    public void startTest() throws SQLException {
        int pageNumber = 5;
        int priceFrom = 100;
        int priceTo = 300;
        String dbName = "rozetka.powders";
        String URL = "jdbc:mysql://localhost:3306/rozetka?useSSL=false";
        String USERNAME = "root";
        String PASSWORD = "root";
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
        DBConnection db = new DBConnection();
        Connection connection = db.getConnection(URL, USERNAME, PASSWORD);
        for (Powder powder : list) {
            powder.savePowderToDb(connection, dbName);
        }
        connection.close();
    }
}
