/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.si.pet.sappe.util;

/**
 *
 * @author Gleyson
 */
import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;

public class SendMail {

    public static void sendMail(String mailServer, String from, String to, String subject, String Mensagem)
            throws AddressException, MessagingException {

        Properties mailProps = new Properties();
        //definição do mailserver

        mailProps.put("mail.smtp.host", mailServer);
        mailProps.put("mail.smtp.auth", "true");
        Session mailSession = Session.getDefaultInstance(mailProps, new Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("petufc.quixada", "ufcquixada");
            }
        });

        String texto = Mensagem;
        texto = texto.replaceAll("\n", "\r\n");
        mailSession.setDebug(true);
        mailProps.put("mail.debug", "true");
        mailProps.put("mail.smtp.debug", "true");
        mailProps.put("mail.mime.charset", "ISO-8859-1");
        mailProps.put("mail.smtp.port", "465");
        mailProps.put("mail.smtp.starttls.enable", "true");
        mailProps.put("mail.smtp.socketFactory.port", "465");
        mailProps.put("mail.smtp.socketFactory.fallback", "false");
        mailProps.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        Message message = new MimeMessage(mailSession);
        message.setFrom(new InternetAddress(from));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
        message.setSentDate(new Date());
        message.setSubject(subject);
        message.setText(Mensagem);
        message.setContent(Mensagem.toString(), "text/html");
        Transport.send(message);
        System.out.println("Mensagem enviada com sucesso");
    }

    public static void sendMail(String to, String subject, String Mensagem)
            throws AddressException, MessagingException {
        SendMail.sendMail("smtp.gmail.com", "petufc.quixada@gmail.com", to, subject, Mensagem);
    }

    public static void main(String args[]) throws AddressException, MessagingException {
        sendMail("fgleysondasilva@gmail.com", "Meu primeiro teste para enviar email", "Meu primeiro teste para enviar email e o trabalho foi ralizado com sucesso.");
    }
}
