package org.bouncycastle.mail.smime.examples;

import java.io.PrintStream;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cms.SignerInformation;
import org.bouncycastle.cms.jcajce.JcaSimpleSignerInfoVerifierBuilder;
import org.bouncycastle.mail.smime.SMIMESigned;
import org.bouncycastle.util.Store;
/* loaded from: classes4.dex */
public class ReadSignedMail {
    private static final String BC = "BC";

    /* JADX WARN: Code restructure failed: missing block: B:23:0x00b4, code lost:
        if ((r0 instanceof java.lang.String) != false) goto L20;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void main(java.lang.String[] r8) throws java.lang.Exception {
        /*
            java.util.Properties r8 = java.lang.System.getProperties()
            r0 = 0
            javax.mail.Session r8 = javax.mail.Session.getDefaultInstance(r8, r0)
            javax.mail.internet.MimeMessage r0 = new javax.mail.internet.MimeMessage
            java.io.FileInputStream r1 = new java.io.FileInputStream
            java.lang.String r2 = "signed.message"
            r1.<init>(r2)
            r0.<init>(r8, r1)
            java.lang.String r8 = "multipart/signed"
            boolean r8 = r0.isMimeType(r8)
            java.lang.String r1 = "Status:"
            java.lang.String r2 = "Content:"
            if (r8 == 0) goto L87
            org.bouncycastle.mail.smime.SMIMESigned r8 = new org.bouncycastle.mail.smime.SMIMESigned
            java.lang.Object r0 = r0.getContent()
            javax.mail.internet.MimeMultipart r0 = (javax.mail.internet.MimeMultipart) r0
            r8.<init>(r0)
            javax.mail.internet.MimeBodyPart r0 = r8.getContent()
            java.io.PrintStream r3 = java.lang.System.out
            r3.println(r2)
            java.lang.Object r0 = r0.getContent()
            boolean r2 = r0 instanceof java.lang.String
            if (r2 == 0) goto L3f
            goto Lb6
        L3f:
            boolean r2 = r0 instanceof javax.mail.Multipart
            if (r2 == 0) goto Lbd
            javax.mail.Multipart r0 = (javax.mail.Multipart) r0
            int r2 = r0.getCount()
            r3 = 0
        L4a:
            if (r3 >= r2) goto Lbd
            javax.mail.BodyPart r4 = r0.getBodyPart(r3)
            java.lang.Object r4 = r4.getContent()
            java.io.PrintStream r5 = java.lang.System.out
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r7 = "Part "
            r6.append(r7)
            r6.append(r3)
            java.lang.String r6 = r6.toString()
            r5.println(r6)
            java.io.PrintStream r5 = java.lang.System.out
            java.lang.String r6 = "---------------------------"
            r5.println(r6)
            boolean r5 = r4 instanceof java.lang.String
            if (r5 == 0) goto L7d
            java.io.PrintStream r5 = java.lang.System.out
            java.lang.String r4 = (java.lang.String) r4
            r5.println(r4)
            goto L84
        L7d:
            java.io.PrintStream r4 = java.lang.System.out
            java.lang.String r5 = "can't print..."
            r4.println(r5)
        L84:
            int r3 = r3 + 1
            goto L4a
        L87:
            java.lang.String r8 = "application/pkcs7-mime"
            boolean r8 = r0.isMimeType(r8)
            if (r8 != 0) goto La0
            java.lang.String r8 = "application/x-pkcs7-mime"
            boolean r8 = r0.isMimeType(r8)
            if (r8 == 0) goto L98
            goto La0
        L98:
            java.io.PrintStream r8 = java.lang.System.err
            java.lang.String r0 = "Not a signed message!"
            r8.println(r0)
            goto Lc5
        La0:
            org.bouncycastle.mail.smime.SMIMESigned r8 = new org.bouncycastle.mail.smime.SMIMESigned
            r8.<init>(r0)
            javax.mail.internet.MimeBodyPart r0 = r8.getContent()
            java.io.PrintStream r3 = java.lang.System.out
            r3.println(r2)
            java.lang.Object r0 = r0.getContent()
            boolean r2 = r0 instanceof java.lang.String
            if (r2 == 0) goto Lbd
        Lb6:
            java.io.PrintStream r2 = java.lang.System.out
            java.lang.String r0 = (java.lang.String) r0
            r2.println(r0)
        Lbd:
            java.io.PrintStream r0 = java.lang.System.out
            r0.println(r1)
            verify(r8)
        Lc5:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.mail.smime.examples.ReadSignedMail.main(java.lang.String[]):void");
    }

    private static void verify(SMIMESigned sMIMESigned) throws Exception {
        PrintStream printStream;
        String str;
        Store<X509CertificateHolder> certificates = sMIMESigned.getCertificates();
        for (SignerInformation signerInformation : sMIMESigned.getSignerInfos().getSigners()) {
            if (signerInformation.verify(new JcaSimpleSignerInfoVerifierBuilder().setProvider("BC").build(new JcaX509CertificateConverter().setProvider("BC").getCertificate(certificates.getMatches(signerInformation.getSID()).iterator().next())))) {
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
