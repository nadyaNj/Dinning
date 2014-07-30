package am.ucom.dinning.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

/**
 * Class description here.
 *
 * @author Hayk Hayryan
 * @version 1.0 8/24/11 6:04 PM
 */
public class MailSender {

    public static final Logger LOG = LoggerFactory.getLogger(MailSender.class);

    public MailSender() {
    }

    public static void sendMail(String mailToAddress, String subject, String body) {
        String testEmailTo = CommonProperties.getTestEmailTo().trim();
        if (!testEmailTo.equals("")) {
            StringBuilder text = new StringBuilder();
            text.append("------------------------------------------------------------------\n");
            text.append("The original e-mail was address to: ").append(mailToAddress).append("\n");
            text.append("------------------------------------------------------------------\n");

            text.append(body);

            subject = "TEST MODE: " + subject;
            body = text.toString();
            mailToAddress = testEmailTo;
        }

        Session session = getSMTPSession();

        try {
            Message msg = new MimeMessage(session);

            msg.setFrom(new InternetAddress(CommonProperties.getMailFromAddress()));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailToAddress));
            msg.setSubject(subject);
            msg.setSentDate(new Date());

            msg.setText(body);

            Transport.send(msg);
        } catch (MessagingException ex) {
            LOG.error("Mail sending error: " + ex.getMessage(), ex);
        }
    }

    private static Session getSMTPSession() {
        final Properties props = CommonProperties.getSettingsProperties();

        return Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(CommonProperties.getMailFromAddress(), CommonProperties.getMailPassword());
            }
        });
    }
}

