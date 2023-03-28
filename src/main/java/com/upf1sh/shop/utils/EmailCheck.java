package com.upf1sh.shop.utils;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

public class EmailCheck {


    public void emailsend(String email_add, String code) throws EmailException {
        HtmlEmail email = new HtmlEmail();
        email.setHostName("smtp.qq.com");
        email.setAuthentication("2350736728@qq.com", "eoxzpmjvsqscdibg");
        email.setCharset("utf-8");
        email.addTo(email_add);
        email.setSSLOnConnect(false);
        email.setFrom("2350736728@qq.com");
        email.setSubject("欢迎注册！");
        email.setMsg("欢迎注册Big Data System!您的验证码是" + '\n' + code);
        email.setSmtpPort(587);
        email.send();
    }


}



