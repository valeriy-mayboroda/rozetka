package rozetka.com.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import rozetka.com.pages.HomePage;
import rozetka.com.pages.PhonePage;
import rozetka.com.pages.PhoneTvElectonicPage;
import rozetka.com.pages.SmartPhonePage;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by val on 03.05.2018.
 */

public class TestSmartPhoneToFile extends BasePageInstance {
    public static HomePage homePage;
    public static PhoneTvElectonicPage phoneTvElectonicPage;
    public static PhonePage phonePage;
    public static SmartPhonePage smartPhonePage;

    @BeforeClass
    public void init() {
        homePage = new HomePage(driver);
        phoneTvElectonicPage = new PhoneTvElectonicPage(driver);
        phonePage = new PhonePage(driver);
        smartPhonePage = new SmartPhonePage(driver);
    }

    @Test
    public void startTest() {
        int pageNumber = 3;
        homePage.openUrl();
        homePage.clickPhoneTvElectronics();
        phoneTvElectonicPage.clickPhones();
        phonePage.clickSmartPhones();
        List<String> list = new ArrayList<>();
        for (int i = 1; i <= pageNumber; i++) {
            list.addAll(smartPhonePage.getNames());
            if (i != pageNumber && pageNumber > 1) {
                smartPhonePage.clickNextPage(i + 1);
            }
        }
        smartPhonePage.save(list);
    }
}
