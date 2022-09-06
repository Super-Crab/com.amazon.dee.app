package org.bouncycastle.mail.smime.examples;

import java.io.PrintStream;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cms.SignerInformation;
import org.bouncycastle.cms.jcajce.JcaSimpleSignerInfoVerifierBuilder;
import org.bouncycastle.mail.smime.SMIMESignedParser;
import org.bouncycastle.mail.smime.util.SharedFileInputStream;
import org.bouncycastle.operator.jcajce.JcaDigestCalculatorProviderBuilder;
import org.bouncycastle.util.Store;
/* loaded from: classes4.dex */
public class ReadLargeSignedMail {
    private static final String BC = "BC";

    public static void main(String[] strArr) throws Exception {
        SMIMESignedParser sMIMESignedParser;
        MimeMessage mimeMessage = new MimeMessage(Session.getDefaultInstance(System.getProperties(), null), new SharedFileInputStream("signed.message"));
        if (mimeMessage.isMimeType("multipart/signed")) {
            sMIMESignedParser = new SMIMESignedParser(new JcaDigestCalculatorProviderBuilder().build(), (MimeMultipart) mimeMessage.getContent());
        } else if (!mimeMessage.isMimeType("application/pkcs7-mime")) {
            System.err.println("Not a signed message!");
            return;
        } else {
            sMIMESignedParser = new SMIMESignedParser(new JcaDigestCalculatorProviderBuilder().build(), mimeMessage);
        }
        System.out.println("Status:");
        verify(sMIMESignedParser);
    }

    private static void verify(SMIMESignedParser sMIMESignedParser) throws Exception {
        PrintStream printStream;
        String str;
        Store certificates = sMIMESignedParser.getCertificates();
        for (SignerInformation signerInformation : sMIMESignedParser.getSignerInfos().getSigners()) {
            if (signerInformation.verify(new JcaSimpleSignerInfoVerifierBuilder().setProvider("BC").build(new JcaX509CertificateConverter().setProvider("BC").getCertificate((X509CertificateHolder) certificates.getMatches(signerInformation.getSID()).iterator().next())))) {
                printStream = System.out;
                str = "signature verified";
            } else {
                printStream = System.out;
                str = "signature failed!";
            }
            printStream.println(str);
        }
    }
}
