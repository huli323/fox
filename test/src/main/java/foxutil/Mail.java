package foxutil;

import com.sun.mail.util.MailSSLSocketFactory;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.Properties;

public class Mail {
    private static final String smtpHost = "smtp.qq.com";

    private static final String port = "465";

    private static final String user = "1037813603@qq.com";

    //    private static final String password = "feyamtgxubrbbbig";
    private static final String password = "ckjnedtgmgqvbbbj";

//    private static final String smtpHost = "smtp.163.com";
//    private static final String user = "13339881337@163.com";
//    private static final String password = "huli94323";

    public static void main(String[] args) {
        try {
            send(new String[]{"13339881337@163.com", "huli94323@gmail.com", "1037813603@qq.com"}, "subject", "hello world", "C:\\Users\\Administrator\\Desktop\\something.txt");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param sendTo  收件人
     * @param subject 主题
     * @param content 内容
     * @param files   附件
     * @throws MessagingException
     */
    public static void send(String[] sendTo, String subject, String content, String... files) throws MessagingException {
        // 设置邮件服务器环境
        Properties properties = new Properties();
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.host", smtpHost);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.enable", true);

        // 设置ssl加密
        MailSSLSocketFactory mailSSLSocketFactory = null;
        try {
            mailSSLSocketFactory = new MailSSLSocketFactory();
            mailSSLSocketFactory.setTrustAllHosts(true);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        properties.put("mail.smtp.ssl.socketFactory", mailSSLSocketFactory);

        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });
        session.setDebug(true);

        // 邮件内容
        Multipart multipart = new MimeMultipart();

        // 设置邮件正文
        BodyPart conPart = new MimeBodyPart();
        conPart.setContent(content, "text/plain");
        multipart.addBodyPart(conPart);

        // 设置附件
        for (int i = 0; i < files.length; i++) {
            File file = new File(files[i]);
            BodyPart filePart = new MimeBodyPart();
            filePart.setDataHandler(new DataHandler(new FileDataSource(file)));
            filePart.setFileName(file.getName());

            multipart.addBodyPart(filePart);
        }

        InternetAddress from = new InternetAddress(user);
        InternetAddress[] to = new InternetAddress[sendTo.length];
        for (int i = 0; i < sendTo.length; i++) {
            to[i] = new InternetAddress(sendTo[i]);
        }

        // 创建邮件对象
        Message message = new MimeMessage(session);
        message.setSentDate(new Date());
        message.setSubject(subject);
        message.setContent(multipart);
        message.setFrom(from);
        message.setRecipients(Message.RecipientType.TO, to);
        message.saveChanges();

        // 发送
        Transport transport = session.getTransport("smtp");
//        transport.connect(smtpHost, user, password);
        transport.send(message);
        transport.close();

        System.out.println("发送成功");
    }

}

