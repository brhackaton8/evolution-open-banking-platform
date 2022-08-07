package com.openbanking.platform.mail;


import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import java.util.Properties;

@Service
public class JavaMailSender {

    public void sendmail(String mailTo, String mailSubject, String mailContent) throws MessagingException, MessagingException, IOException, AddressException, IOException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.office365.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("s.huseynov@ufaz.az", "Se7513244");
            }
        });
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("s.huseynov@ufaz.az", "SMART Bankçılıq"));
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailTo));
        msg.setSubject(mailSubject);
        msg.setSentDate(new Date());


        MimeBodyPart messageBodyPart = new MimeBodyPart();

        messageBodyPart.setContent(mailContent, "text/html");


        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
//       MimeBodyPart attachPart = new MimeBodyPart();

//        attachPart.attachFile("src/main/resources/templates/Shamistan.jpeg");
//        multipart.addBodyPart(attachPart);
        msg.setContent(multipart);
        Transport.send(msg);
    }

}
