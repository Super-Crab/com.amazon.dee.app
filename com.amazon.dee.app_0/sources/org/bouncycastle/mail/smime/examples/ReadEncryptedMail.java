package org.bouncycastle.mail.smime.examples;

import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.Enumeration;
import javax.mail.Session;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import org.bouncycastle.cms.jcajce.JceKeyTransEnvelopedRecipient;
import org.bouncycastle.cms.jcajce.JceKeyTransRecipientId;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.mail.smime.SMIMEEnveloped;
import org.bouncycastle.mail.smime.SMIMEUtil;
/* loaded from: classes4.dex */
public class ReadEncryptedMail {
    public static void main(String[] strArr) throws Exception {
        if (strArr.length != 2) {
            System.err.println("usage: ReadEncryptedMail pkcs12Keystore password");
            System.exit(0);
        }
        KeyStore keyStore = KeyStore.getInstance("PKCS12", BouncyCastleProvider.PROVIDER_NAME);
        keyStore.load(new FileInputStream(strArr[0]), strArr[1].toCharArray());
        Enumeration<String> aliases = keyStore.aliases();
        String str = null;
        while (aliases.hasMoreElements()) {
            String nextElement = aliases.nextElement();
            if (keyStore.isKeyEntry(nextElement)) {
                str = nextElement;
            }
        }
        if (str == null) {
            System.err.println("can't find a private key!");
            System.exit(0);
        }
        MimeBodyPart mimeBodyPart = SMIMEUtil.toMimeBodyPart(new SMIMEEnveloped(new MimeMessage(Session.getDefaultInstance(System.getProperties(), null), new FileInputStream("encrypted.message"))).getRecipientInfos().get(new JceKeyTransRecipientId((X509Certificate) keyStore.getCertificate(str))).getContent(new JceKeyTransEnvelopedRecipient((PrivateKey) keyStore.getKey(str, null)).setProvider(BouncyCastleProvider.PROVIDER_NAME)));
        System.out.println("Message Contents");
        System.out.println("----------------");
        System.out.println(mimeBodyPart.getContent());
    }
}
