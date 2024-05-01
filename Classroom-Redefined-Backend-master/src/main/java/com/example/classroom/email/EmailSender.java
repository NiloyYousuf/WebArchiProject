package com.example.classroom.email;


import com.example.classroom.classroom.Classroom;
import com.example.classroom.classroom.ClassroomRepository;
import com.example.classroom.student.Student;
import com.example.classroom.submission.Submission;
import org.springframework.beans.factory.annotation.Autowired;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.*;
import java.awt.*;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;


public class EmailSender implements Runnable {


    @Override
    public void run(){

        try{
            sendMail();
        }
        catch (HeadlessException e){
            System.out.println("Head");
        }
    }
    private List<Student> txtstudEmail;

    private  List<String> txtEmail;


    private String txtmsg;


    private  String txtsub;

    private Classroom c;


    public EmailSender(List<String> txtEmail, String txtmsg, String txtsub ) {
        this.txtEmail = txtEmail;
        this.txtmsg = txtmsg;
        this.txtsub = txtsub;

    }

    public void sendMail() {
            System.out.println(txtEmail);


            //Setting Properties
            Properties props = System.getProperties();
            propertySetter(props);

            final String username = "projectclassroomredefined@gmail.com";//
            final String password = "icevbblyfgbygveq";
            if(txtEmail == null) return;
            instaSendMail(username, password, txtEmail, txtsub, txtmsg, props);

    }

    public void propertySetter(Properties props){
        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
        props.setProperty("mail.smtp.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.auth", "true");
        props.put("mail.debug", "true");
        props.put("mail.store.protocol", "pop3");
        props.put("mail.transport.protocol", "smtp");
        //props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
    }
    public void instaSendMail(String usernam, String pas, List<String> toMail, String sub, String message, Properties props){
        try{
            Session session = Session.getInstance(props,
                    new Authenticator(){
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(usernam, pas);
                        }});


            session.setDebug(false);
            Message msg = new MimeMessage(session);

            ;
            Address[] adrs = new Address[toMail.size()];
            System.out.println(toMail.size());
            for(int i = 0 ; i < toMail.size() ; i++){
                adrs[i] = new InternetAddress(toMail.get(i) );
            }
            //adrs[0] = new InternetAddress("samazfar52@gmail.com" );

            msg.setFrom(new InternetAddress(usernam));
            msg.setRecipients(Message.RecipientType.BCC,
                    adrs);
            msg.setSubject(sub);
            msg.setText(message);


            msg.setSentDate(new Date());
            Transport.send(msg);

        }catch (MessagingException e){ System.out.println("Error, cause: " + e);}
    }



    private static String getExceptionMessage(Exception e) {
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        return sw.toString();
    }
}
