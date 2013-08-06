/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.si.pet.sappe.util;

/**
 *
 * @author Gleyson
 */
import java.net.MalformedURLException;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.SimpleEmail;
import javax.naming.Context;
import javax.naming.InitialContext;

public class SendMail {

    public static void enviarEmail(String mailServer, String from, String to, String subject, String Mensagem)
            throws AddressException, MessagingException, MalformedURLException {

        Properties mailProps = new Properties();
        //definição do mailserver
        mailProps.put("mail.smtp.host", mailServer);
        mailProps.put("mail.smtp.auth", "true");
        Session mailSession = Session.getDefaultInstance(mailProps, new Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("sistema.sappe", "castelo.sappe");
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

    public static void enviarEmail(String to, String subject, String Mensagem)
            throws AddressException, MessagingException, MalformedURLException {
        SendMail.enviarEmail("smtp.gmail.com", "sistema.sappe@gmail.com", to, subject, Mensagem);
    //   SendMail.sendEmailSappe(Mensagem, "apps@quixada.ufc.br", to, subject);
    }

    public static void sendMail(String mailServer, String from, String to, String subject, String Mensagem)
            throws AddressException, MessagingException, EmailException, MalformedURLException {
        enviaEmailFormatoHtml(to, subject, Mensagem);
    }

    public static void emailSimples(String to, String subject, String Mensagem) throws EmailException {
        SimpleEmail email = new SimpleEmail();
        email.setHostName("smtp.gmail.com"); // o servidor SMTP para envio do e-mail
        email.addTo(to, "Usuário"); //destinatário
        email.setFrom("sistema.sappe@gmail.com", "pet"); // remetente
        email.setSubject(subject); // assunto do e-mail
        email.setMsg(Mensagem); //conteudo do e-mail
        email.setAuthentication("sistema.sappe@gmail.com", "castelo.sappe");
        email.setSmtpPort(465);
        email.setSSL(true);
        email.setTLS(true);
        email.send();
    }//fim do método

    public static void enviaEmailFormatoHtml(String to, String subject, String Mensagem) throws EmailException, MalformedURLException {

        HtmlEmail email = new HtmlEmail();

// configura a mensagem para o formato HTML

        email.setHtmlMsg("<html><body> " + Mensagem + " </body></html>");

// configure uma mensagem alternativa caso o servidor não suporte HTML

        email.setTextMsg("Seu servidor de e-mail não suporta mensagem HTML");

        email.setHostName("smtp.gmail.com"); // o servidor SMTP para envio do e-mail

        email.addTo(to, to); //destinatário

        email.setFrom("sistema.sappe@gmail.com", "sappe"); // remetente</div>

        email.setSubject(subject); // assunto do e-mail

//email.setMsg(Mensagem); //conteudo do e-mail

        email.setAuthentication("sistema.sappe@gmail.com", "castelo.sappe");

        email.setSmtpPort(465);

        email.setSSL(true);

        email.setTLS(true);

// envia email

        email.send();

    }

    public static void sendMail(String to, String subject, String Mensagem)
            throws AddressException, MessagingException, EmailException, MalformedURLException {
        SendMail.sendMail("smtp.gmail.com", "sistema.sappe@gmail.com", to, subject, Mensagem);
       //SendMail.sendEmailSappe(Mensagem, "apps@quixada.ufc.br", to, subject);
    }

    public static void main(String args[]) throws AddressException, MessagingException, MalformedURLException {
        enviarEmail("mardsonferreira25@gmail.com", "Meu primeiro teste para enviar email", "Meu primeiro teste para enviar email e o trabalho foi ralizado com sucesso.");
    }



    public static void sendEmailSappe(String messageBody, String from, String to, String subject){

        System.out.println(to);


        try{
        Context initCtx = new InitialContext();

        Session s = (javax.mail.Session)initCtx.lookup("java:comp/env/"+"mail/Session");

 
        MimeMessage message = new MimeMessage( s);
        message.setFrom(new InternetAddress(from));
        message.setRecipients(Message.RecipientType.TO, to);
//        message.setSubject(MimeUtility.encodeText(SendMail.SUBJECT), "us-ascii");
        message.setSubject(MimeUtility.encodeText(subject), "UTF-8");
//        message.setSubject(SendMail.SUBJECT);
        String messageBodyContent = "<html><body>";
        messageBodyContent+="<html><body> " + messageBody + "</body></html>";

        message.setContent(messageBodyContent, "text/html; charset=\"UTF-8\"");
       //Objeto encarregado de enviar os dados para o email
        Transport tr;
        try {
            tr = s.getTransport("smtp"); //define smtp para transporte
            Properties props = s.getProperties();
            String host =props.getProperty("mail.smtp.host");
            int port = Integer.parseInt(props.getProperty("mail.smtp.port"));
            String user = props.getProperty("mail.smtp.user");
            String password = s.getProperties().getProperty("mail.smtp.password");
            tr.connect(host, port, user, password);
            message.saveChanges(); // don't forget this
            //envio da mensagem
            tr.sendMessage(message, message.getAllRecipients());
            tr.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println(">> Erro: Envio Mensagem");
            e.printStackTrace();
        }
        }catch(Exception ex){
            System.out.println(ex.toString());

        }
    }

}
