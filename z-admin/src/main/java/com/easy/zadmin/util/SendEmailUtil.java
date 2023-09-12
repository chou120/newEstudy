package com.easy.zadmin.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import javax.mail.internet.MimeMessage;

/**
 * @Author sanye
 * @Date 2023/8/27 22:15
 * @Version 1.0
 */
@Component
public class SendEmailUtil {
    @Autowired
    JavaMailSender mailSender;//注入QQ发送邮件的bean

    public boolean qqEmail(String qq, String title,String new_pass) {

        String  context="尊敬的用户您好：\n" +
                "            您的登录密码已重置为"+new_pass+"，拿到新密码请前往平台重新设置新密码，谢谢\n" +
                "登录地址：http:180.184.182.74/user/exUserLogin";

        try {
            MimeMessage mimeMessage = this.mailSender.createMimeMessage();
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
            message.setFrom("1208160221@qq.com");//设置发件qq邮箱
            //qq+="@qq.com";                    //补全地址
            message.setTo(qq);                //设置收件人
            message.setSubject(title);    //设置标题
            message.setText(context,true);      //第二个参数true表示使用HTML语言来编写邮件
//            FileSystemResource file = new FileSystemResource(
//            File file = new File("图片路径");
//            helper.addAttachment("图片.jpg", file);//添加带附件的邮件
//            helper.addInline("picture",file);//添加带静态资源的邮件
            this.mailSender.send(mimeMessage);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }


}
