package com.ldap;

import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {

    private static final Logger LOGGER = Logger.getAnonymousLogger();

    private static final String SERVIDOR_SMTP = "mel-cla-01.revit.local";//"smtp.office365.com";
    private static final int PORTA_SERVIDOR_SMTP = 25;
    private static final String CONTA_PADRAO = "DoNotReply@revolutionit.com.au";
    private static final String SENHA_CONTA_PADRAO = "Voho0783";

    private final String from = "DoNotReply@revolutionit.com.au";
    private final String to = "Praneeth.Desur@revolutionit.com.au";

    private final String subject = "Test Email - CA PPM";
    private final String messageContent = "Test Email Content";

    public void sendEmail() {
        final Session session = Session.getInstance(this.getEmailProperties(), new Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(CONTA_PADRAO, SENHA_CONTA_PADRAO);
            }

        });

        try {
            final Message message = new MimeMessage(session);
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setFrom(new InternetAddress(from));
            message.setSubject(subject);
            message.setText(messageContent);
            message.setSentDate(new Date());
            Transport.send(message);
        } catch (final MessagingException ex) {
            LOGGER.log(Level.WARNING, "Error message: " + ex.getMessage(), ex);
        }
    }

    public Properties getEmailProperties() {
        final Properties config = new Properties();
        config.put("mail.smtp.auth", "false");
        config.put("mail.smtp.starttls.enable", "false"); 
        config.put("mail.smtp.host", SERVIDOR_SMTP);
        config.put("mail.smtp.port", PORTA_SERVIDOR_SMTP);
        return config;
    }

    public static void main(final String[] args) {
        new SendEmail().sendEmail();
    }

}