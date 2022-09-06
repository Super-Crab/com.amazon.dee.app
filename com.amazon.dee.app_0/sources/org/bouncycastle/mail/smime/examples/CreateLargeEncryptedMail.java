package org.bouncycastle.mail.smime.examples;

import java.io.File;
import java.io.FileOutputStream;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import org.bouncycastle.cms.CMSAlgorithm;
import org.bouncycastle.cms.jcajce.JceCMSContentEncryptorBuilder;
import org.bouncycastle.cms.jcajce.JceKeyTransRecipientInfoGenerator;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.mail.smime.SMIMEEnvelopedGenerator;
/* loaded from: classes4.dex */
public class CreateLargeEncryptedMail {
    public static void main(String[] strArr) throws Exception {
        if (strArr.length != 3) {
            System.err.println("usage: CreateLargeEncryptedMail pkcs12Keystore password inputFile");
            System.exit(0);
        }
        KeyStore keyStore = KeyStore.getInstance("PKCS12", BouncyCastleProvider.PROVIDER_NAME);
        Certificate[] certificateChain = keyStore.getCertificateChain(ExampleUtils.findKeyAlias(keyStore, strArr[0], strArr[1].toCharArray()));
        SMIMEEnvelopedGenerator sMIMEEnvelopedGenerator = new SMIMEEnvelopedGenerator();
        sMIMEEnvelopedGenerator.addRecipientInfoGenerator(new JceKeyTransRecipientInfoGenerator((X509Certificate) certificateChain[0]).setProvider(BouncyCastleProvider.PROVIDER_NAME));
        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setDataHandler(new DataHandler(new FileDataSource(new File(strArr[2]))));
        mimeBodyPart.setHeader("Content-Type", "application/octet-stream");
        mimeBodyPart.setHeader("Content-Transfer-Encoding", "binary");
        MimeBodyPart generate = sMIMEEnvelopedGenerator.generate(mimeBodyPart, new JceCMSContentEncryptorBuilder(CMSAlgorithm.RC2_CBC).setProvider(BouncyCastleProvider.PROVIDER_NAME).build());
        Session defaultInstance = Session.getDefaultInstance(System.getProperties(), null);
        InternetAddress internetAddress = new InternetAddress("\"Eric H. Echidna\"<eric@bouncycastle.org>");
        InternetAddress internetAddress2 = new InternetAddress("example@bouncycastle.org");
        MimeMessage mimeMessage = new MimeMessage(defaultInstance);
        mimeMessage.setFrom(internetAddress);
        mimeMessage.setRecipient(Message.RecipientType.TO, internetAddress2);
        mimeMessage.setSubject("example encrypted message");
        mimeMessage.setContent(generate.getContent(), generate.getContentType());
        mimeMessage.saveChanges();
        mimeMessage.writeTo(new FileOutputStream("encrypted.message"));
    }
}
