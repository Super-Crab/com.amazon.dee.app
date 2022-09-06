package org.bouncycastle.mail.smime.examples;

import java.io.FileInputStream;
import javax.mail.Session;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import org.bouncycastle.cms.jcajce.ZlibExpanderProvider;
import org.bouncycastle.mail.smime.SMIMECompressed;
import org.bouncycastle.mail.smime.SMIMEUtil;
/* loaded from: classes4.dex */
public class ReadCompressedMail {
    public static void main(String[] strArr) throws Exception {
        MimeBodyPart mimeBodyPart = SMIMEUtil.toMimeBodyPart(new SMIMECompressed(new MimeMessage(Session.getDefaultInstance(System.getProperties(), null), new FileInputStream("compressed.message"))).getContent(new ZlibExpanderProvider()));
        System.out.println("Message Contents");
        System.out.println("----------------");
        System.out.println(mimeBodyPart.getContent());
    }
}
