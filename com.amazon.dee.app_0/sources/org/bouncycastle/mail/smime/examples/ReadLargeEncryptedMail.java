package org.bouncycastle.mail.smime.examples;

import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import org.bouncycastle.cms.jcajce.JceKeyTransEnvelopedRecipient;
import org.bouncycastle.cms.jcajce.JceKeyTransRecipientId;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.mail.smime.SMIMEEnvelopedParser;
import org.bouncycastle.mail.smime.SMIMEUtil;
import org.bouncycastle.mail.smime.util.SharedFileInputStream;
/* loaded from: classes4.dex */
public class ReadLargeEncryptedMail {
    public static void main(String[] strArr) throws Exception {
        if (strArr.length != 3) {
            System.err.println("usage: ReadLargeEncryptedMail pkcs12Keystore password outputFile");
            System.exit(0);
        }
        KeyStore keyStore = KeyStore.getInstance("PKCS12", BouncyCastleProvider.PROVIDER_NAME);
        String findKeyAlias = ExampleUtils.findKeyAlias(keyStore, strArr[0], strArr[1].toCharArray());
        ExampleUtils.dumpContent(SMIMEUtil.toMimeBodyPart(new SMIMEEnvelopedParser(new MimeMessage(Session.getDefaultInstance(System.getProperties(), null), new SharedFileInputStream("encrypted.message"))).getRecipientInfos().get(new JceKeyTransRecipientId((X509Certificate) keyStore.getCertificate(findKeyAlias))).getContentStream(new JceKeyTransEnvelopedRecipient((PrivateKey) keyStore.getKey(findKeyAlias, null)).setProvider(BouncyCastleProvider.PROVIDER_NAME))), strArr[2]);
    }
}
