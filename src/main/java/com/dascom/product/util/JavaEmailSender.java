package com.dascom.product.util;

import java.io.File;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import com.sun.mail.util.MailSSLSocketFactory;

public class JavaEmailSender {
	
	/**
	 * 
	 * @param toEmailAddress 对方的邮件
	 * @param emailTitle  邮件标题
	 * @param emailContent 邮件内容
	 * @param attachment  附件
	 * @throws Exception
	 */
	public static void sendEmail(String toEmailAddress,String emailTitle,String emailContent ,File attachment)throws Exception{
	    Properties props = new Properties();
	
	    // 开启debug调试
	    props.setProperty("mail.debug", "true");
	    // 发送服务器需要身份验证
	    props.setProperty("mail.smtp.auth", "true");
	    // 设置邮件服务器主机名
	    props.setProperty("mail.host", "smtp.qq.com");
	    // 发送邮件协议名称
	    props.setProperty("mail.transport.protocol", "smtp");
	
	    /**SSL认证，注意腾讯邮箱是基于SSL加密的，所有需要开启才可以使用**/
	    MailSSLSocketFactory sf = new MailSSLSocketFactory();
	    sf.setTrustAllHosts(true);
	    props.put("mail.smtp.ssl.enable", "true");
	    props.put("mail.smtp.ssl.socketFactory", sf);
	
	    //创建会话
	    Session session = Session.getInstance(props);
	
	    //发送的消息，基于观察者模式进行设计的
	    Message msg = new MimeMessage(session);
	    msg.setSubject(emailTitle);
	    
	    // 向multipart对象中添加邮件的各个部分内容，包括文本内容和附件  
        Multipart multipart = new MimeMultipart(); 
	    
	    //使用StringBuilder，因为StringBuilder加载速度会比String快，而且线程安全性也不错 
	    /*StringBuilder builder = new StringBuilder();
	    builder.append("\n"+emailContent);
	    builder.append("\n时间 " + new Date());
	    msg.setText(builder.toString());*/
	    
	    // 添加邮件正文  
        BodyPart contentPart = new MimeBodyPart();  
        contentPart.setContent(emailContent, "text/html;charset=UTF-8");  
        multipart.addBodyPart(contentPart);  
	    
        // 添加附件的内容  
        if (attachment != null) {  
            BodyPart attachmentBodyPart = new MimeBodyPart();  
            DataSource source = new FileDataSource(attachment);  
            attachmentBodyPart.setDataHandler(new DataHandler(source));  
              
            // 网上流传的解决文件名乱码的方法，其实用MimeUtility.encodeWord就可以很方便的搞定  
            // 这里很重要，通过下面的Base64编码的转换可以保证你的中文附件标题名在发送时不会变成乱码  
            //sun.misc.BASE64Encoder enc = new sun.misc.BASE64Encoder();  
            //messageBodyPart.setFileName("=?GBK?B?" + enc.encode(attachment.getName().getBytes()) + "?=");  
              
            //MimeUtility.encodeWord可以避免文件名乱码  
            attachmentBodyPart.setFileName(MimeUtility.encodeWord(attachment.getName()));  
            multipart.addBodyPart(attachmentBodyPart);  
        }
        
        // 将multipart对象放到message中  
        msg.setContent(multipart);  
	    //发送人
	    msg.setFrom(new InternetAddress("244180437@qq.com","增值应用组"));
	    //保存邮件
	    msg.saveChanges();
	
	    Transport transport = session.getTransport();
	    transport.connect("smtp.qq.com", "244180437@qq.com", "ymitownfrhdlbije");
	    //发送消息
	    transport.sendMessage(msg, new Address[] { new InternetAddress(toEmailAddress) });
	    transport.close();
	}
	
	
	
	
	
	
}
