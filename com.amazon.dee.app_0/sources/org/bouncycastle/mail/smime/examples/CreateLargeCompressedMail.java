package org.bouncycastle.mail.smime.examples;

import java.io.File;
import java.io.FileOutputStream;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import org.bouncycastle.cms.jcajce.ZlibCompressor;
import org.bouncycastle.mail.smime.SMIMECompressedGenerator;
/* loaded from: classes4.dex */
public class CreateLargeCompressedMail {
    public static void main(String[] strArr) throws Exception {
        SMIMECompressedGenerator sMIMECompressedGenerator = new SMIMECompressedGenerator();
        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setDataHandler(new DataHandler(new FileDataSource(new File(strArr[0]))));
        mimeBodyPart.setHeader("Content-Type", "application/octet-stream");
        mimeBodyPart.setHeader("Content-Transfer-Encoding", "binary");
        MimeBodyPart generate = sMIMECompressedGenerator.generate(mimeBodyPart, new ZlibCompressor());
        Session defaultInstance = Session.getDefaultInstance(System.getProperties(), null);
        InternetAddress internetAddress = new InternetAddress("\"Eric H. Echidna\"<eric@bouncycastle.org>");
        InternetAddress internetAddress2 = new InternetAddress("example@bouncycastle.org");
        MimeMessage mimeMessage = new MimeMessage(defaultInstance);
        mimeMessage.setFrom(internetAddress);
        mimeMessage.setRecipient(Message.RecipientType.TO, internetAddress2);
        mimeMessage.setSubject("example compressed message");
        mimeMessage.setContent(generate.getContent(), generate.getContentType());
        mimeMessage.saveChanges();
        mimeMessage.writeTo(new FileOutputStream("compressed.message"));
    }
}
