package com.proj.comp2171project;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Email{
    public static void sendMail(String recipient, String sub, String msg) throws MessagingException {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

//        String username = "swenProjectEmailer@gmail.com";
//        String password = "SwenProjectEmailer@123";
          String username = "comp2171emailer@gmail.com";
          String password = "comp2171emailer123";

        Session session = Session.getInstance(properties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        Message message = prepareMessage(session,username,recipient,sub,msg);
        Transport.send(message);
        System.out.println("Sent out");
    }

    private static Message prepareMessage(Session session, String username,String recipient, String sub, String msg) throws MessagingException {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipient(Message.RecipientType.TO,new InternetAddress(recipient));
        message.setContent(msg,"text/html");
        message.setSubject(sub);
        //message.setText(msg);
        return message;
    }


}





