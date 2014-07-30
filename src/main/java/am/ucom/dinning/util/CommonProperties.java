package am.ucom.dinning.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;

/**
 * Class description here.
 *
 * @author Hayk Hayryan
 * @version 1.0 8/24/11 6:17 PM
 */
public class CommonProperties {

    public static final String SMTP_HOST = "mail.smtp.host";
    public static final String MAIL_FROM = "mail.from";
    public static final String MAIL_PASS = "mail.password";
    public static final String TEST_EMAIL_TO = "mail.test.email.to";
    public static final String MAIL_DOMAINS = "mail.domain";


    public static final Logger LOG = LoggerFactory.getLogger(CommonProperties.class);

    private CommonProperties() {
    }

    public static Properties getSettingsProperties() {
        Properties props = new Properties();
        try {
            props.load(CommonProperties.class.getResourceAsStream("/smtp_settings.properties"));
        } catch (IOException e) {
            LOG.error("Property file reading error: " + e.getMessage(), e);
        }

        return props;
    }

    public static String getSmtpHost() {
        return getSettingsProperties().getProperty(SMTP_HOST);
    }

    public static String getMailFromAddress() {
        return getSettingsProperties().getProperty(MAIL_FROM);
    }

    public static String getMailPassword() {
        return getSettingsProperties().getProperty(MAIL_PASS);
    }

    public static String getTestEmailTo() {
        return getSettingsProperties().getProperty(TEST_EMAIL_TO);
    }

    public static String[] getDomainsList() {
        return getSettingsProperties().getProperty(MAIL_DOMAINS).split(",");
    }
}
