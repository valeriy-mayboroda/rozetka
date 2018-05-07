package rozetka.com.tests;

import org.simplejavamail.email.Email;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.Mailer;
import org.simplejavamail.mailer.MailerBuilder;
import org.simplejavamail.mailer.config.TransportStrategy;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import rozetka.com.models.SmartPhone;
import rozetka.com.pages.HomePage;
import rozetka.com.pages.PhonePage;
import rozetka.com.pages.PhoneTvElectonicPage;
import rozetka.com.pages.SmartPhonePage;

import javax.activation.DataSource;
import javax.activation.FileDataSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by val on 04.05.2018.
 */

public class TestSmartPhoneToExcelFile extends BasePageInstance{
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
    public void startTest() throws IOException {
        int pageNumber = 5;
        int from = 3000;
        int to = 6000;
        Properties props = new Properties();
        FileInputStream fis = new FileInputStream("D:\\Документы\\Java\\rozetka\\src\\main\\resources\\config.properties");
        props.load(fis);
        String login = props.getProperty("em.login");
        String password = props.getProperty("em.password");
        fis.close();
        homePage.openUrl();
        homePage.clickPhoneTvElectronics();
        phoneTvElectonicPage.clickPhones();
        phonePage.clickSmartPhones();
        List<SmartPhone> priceSortList = new ArrayList<>();
        List<SmartPhone> topSortList = new ArrayList<>();
        for (int i = 1; i <= pageNumber; i++) {
            priceSortList.addAll(smartPhonePage.findSmartsOnPage(from, to));
            topSortList.addAll(smartPhonePage.findTopSmartsOnPage());
            if (i != pageNumber && pageNumber > 1) {
                smartPhonePage.clickNextPage(i + 1);
            }
        }
        File file = smartPhonePage.saveToExcelFile(priceSortList, "PriceSorting", topSortList, "TopSorting");
        DataSource source = new FileDataSource(file);
        Email email = EmailBuilder.startingBlank()
                .to("valeriy", "valeromayb@gmail.com")
                .from("noreply@testtask.com")
                .withSubject("Test task result file")
                .withPlainText("Please view attached file as a result for first subtask of Autmation QA Test Task")
                .withAttachment("result_file", source)
                .buildEmail();
        Mailer mailer = MailerBuilder
                .withSMTPServer("smtp.gmail.com", 587, login, password)//Sender
                .withTransportStrategy(TransportStrategy.SMTP_TLS)
                .withSessionTimeout(10 * 1000)
                .withDebugLogging(true)
                .buildMailer();
        mailer.sendMail(email);
    }
}
