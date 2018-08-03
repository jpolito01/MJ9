package common.controller;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.mail.internet.MimeMultipart;

public class EmailController {
	public Void emailSending(String[] email, String htmlText, String subject) {
		//      Object to = email;
		      final String from = "redsaidbluesaid01@gmail.com";
		      final String password="AZNest1!";
		      String host = "smtp.googlemail.com";
		      Properties properties = System.getProperties();
		      properties.setProperty("mail.transport.protocol", "smtp");
		      properties.setProperty("mail.host", host);
		      properties.put("mail.smtp.auth", "true");
		      properties.put("mail.smtp.starttls.enable", true);
		      properties.put("mail.smtp.port", "25");
		      properties.put("mail.smtp.socketFactory.port", "465");
		      properties.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		      Session session = Session.getDefaultInstance(properties,new javax.mail.Authenticator() 
	    {
	         protected PasswordAuthentication getPasswordAuthentication()
	         { 
	        	 return new PasswordAuthentication(from,password);  
	         }
	    });
		      try {
		         MimeMessage message = new MimeMessage(session);
		         message.setFrom(new InternetAddress(from));
		         Address[] ia = new InternetAddress[email.length];
		               int i = 0;
		               for (String address : email) {
		                   ia[i] = new InternetAddress(address);
		                   i++;
		               }
		               
		               message.addRecipients(RecipientType.TO, ia);
		    //     message.addRecipient(Message.RecipientType.TO, new InternetAddress((String) to));
		         message.setSubject(subject);
		            Multipart multipart = new MimeMultipart();
			        MimeBodyPart messageBodyPart = new MimeBodyPart();
		            messageBodyPart.setContent(htmlText, "text/html");
		            multipart.addBodyPart(messageBodyPart); 
		            message.setContent(multipart);
		            Transport.send(message);
		         System.out.println("Sent message successfully....");
		      }catch (MessagingException mex) {
		         mex.printStackTrace();
		      }
			return null;
		}
	
	public Void sendingForgotPasswordMail(String[] email, String htmlText) {
		//      Object to = email;
	      final String from = "redsaidbluesaid01@gmail.com";
	      final String password="AZNest1!";
		      String host = "smtp.googlemail.com";
		      Properties properties = System.getProperties();
		      properties.setProperty("mail.transport.protocol", "smtp");
		      properties.setProperty("mail.host", host);
		      properties.put("mail.smtp.auth", "true");
		      properties.put("mail.smtp.starttls.enable", true);
		      properties.put("mail.smtp.port", "25");
		      properties.put("mail.smtp.socketFactory.port", "465");
		      properties.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		      Session session = Session.getDefaultInstance(properties,new javax.mail.Authenticator() 
	    {
	         protected PasswordAuthentication getPasswordAuthentication()
	         { 
	        	 return new PasswordAuthentication(from,password);  
	         }
	    });
		      try {
		         MimeMessage message = new MimeMessage(session);
		         message.setFrom(new InternetAddress(from));
		         Address[] ia = new InternetAddress[email.length];
		               int i = 0;
		               for (String address : email) {
		                   ia[i] = new InternetAddress(address);
		                   i++;
		               }
		               
		               message.addRecipients(RecipientType.TO, ia);
		    //     message.addRecipient(Message.RecipientType.TO, new InternetAddress((String) to));
		         message.setSubject("Red Said Blue Said - Forgot password");
		            Multipart multipart = new MimeMultipart();
			        MimeBodyPart messageBodyPart = new MimeBodyPart();
		            messageBodyPart.setContent(htmlText, "text/html");
		            multipart.addBodyPart(messageBodyPart); 
		            message.setContent(multipart);
		            Transport.send(message);
		         System.out.println("Sent message successfully....");
		      }catch (MessagingException mex) {
		         mex.printStackTrace();
		      }
			return null;
		}

	}

