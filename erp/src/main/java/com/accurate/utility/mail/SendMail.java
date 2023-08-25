package com.accurate.utility.mail;

import java.util.Properties;

import javax.mail.Session;

public class SendMail {
	
public static void main(String[] args) {
		
	    System.out.println("SimpleEmail Start");
		
	    String smtpHostServer = "smtp.freesmtpservers.com";
	    String emailID = "Nadim784@gmail.com";
	    
	    Properties props = System.getProperties();

	    props.put("mail.smtp.host", smtpHostServer);
	    props.put("mail.smtp.port", 25);

	    Session session = Session.getInstance(props, null);
	    
	    EmaiUtil.sendEmail(session, emailID,"SimpleEmail Testing Subject", "SimpleEmail Testing Body");
	}

}
