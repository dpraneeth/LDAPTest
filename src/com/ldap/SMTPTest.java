package com.ldap;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SMTPTest {
	
	 private final Properties mailProperties = new Properties();
	    private final String imapHost;
	    private final int imapPort;
	 
	    public SMTPTest(String smtpHost, int smtpPort,
	                        String imapHost, int imapPort,
	                        String user, String password) {
	        this.imapHost = imapHost;
	        this.imapPort = imapPort;
	        mailProperties.setProperty("mail.smtp.host", smtpHost);
	        mailProperties.setProperty("mail.smtp.port", Integer.toString(smtpPort));
	        mailProperties.setProperty("mail.user", user);
	        mailProperties.setProperty("mail.password", password);
	        mailProperties.setProperty("mail.store.protocol", "smtp");
	    }
	 
	    public void sendMail(String to, String from, String subject,
	                         String content) throws MessagingException {
	        Session session = Session.getDefaultInstance(mailProperties);
	 
	        MimeMessage message = new MimeMessage(session);
	        message.setFrom(new InternetAddress(from));
	        message.setRecipients(Message.RecipientType.TO, to);
	        message.setSubject(subject);
	        message.setText(content);
	 
	        Transport.send(message);
	    }
	 
	    public Message[] receiveMail(String user, String password)
	            throws MessagingException {
	        Session session = Session.getDefaultInstance(mailProperties);
	 
	        Store store = session.getStore("imap");
	        store.connect(imapHost, imapPort, user, password);
	        Folder inbox = store.getFolder("INBOX");
	 
	        inbox.open(Folder.READ_ONLY);
	 
	        return inbox.getMessages();
	    }
	    
       
	    public static void main(String a[]){
	    	
	    	 String to = "praneeth.desur@revolutionit.com.au";
	         String from = "praneeth.desur@revolutionit.com.au";
	         String subject = "Test";
	         String content = "This content should be received after the call of sendMail.";
	         
	        String smtpHost = "smtp.office365.com";
	 	    int smtpPort = 587;
	 	    String imapHost = "outlook.office365.com";
	 	    int imapPort = 993;
	 	    
	 	    String user = "DoNotReply@revolutionit.com.au";
	 	    String password = "Voho0783";
	 	    
	 	   SMTPTest smtpTest = new SMTPTest(smtpHost, smtpPort, imapHost, imapPort, user, password);
	  
		 	try {
				smtpTest.sendMail(to, from, subject, content);
			} catch (MessagingException e) {
				e.printStackTrace();
			}
	    }
	    

}
