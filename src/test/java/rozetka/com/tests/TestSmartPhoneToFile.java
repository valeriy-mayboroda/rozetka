package rozetka.com.tests;

import org.simplejavamail.email.Email;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.Mailer;
import org.simplejavamail.mailer.MailerBuilder;
import org.simplejavamail.mailer.config.TransportStrategy;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import rozetka.com.pages.HomePage;
import rozetka.com.pages.PhonePage;
import rozetka.com.pages.PhoneTvElectonicPage;
import rozetka.com.pages.SmartPhonePage;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import java.io.File;
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
        File file = smartPhonePage.saveToFile(list);
        DataSource source = new FileDataSource(file);
        Email email = EmailBuilder.startingBlank()
                .to("valeriy", "valeromayb@gmail.com")
                .from("noreply@testtask.com")
                .withSubject("Test task result file")
                .withPlainText("Please view attached file as a result for first subtask of Autmation QA Test Task")
                .withAttachment("result_file", source)
                .buildEmail();

        Mailer mailer = MailerBuilder
                .withSMTPServer("smtp.gmail.com", 587, "valeromayb@gmail.com", "*****")//Sender
                .withTransportStrategy(TransportStrategy.SMTP_TLS)
                .withSessionTimeout(10 * 1000)
                .withDebugLogging(true)
                .buildMailer();

        mailer.sendMail(email);
    }
}
