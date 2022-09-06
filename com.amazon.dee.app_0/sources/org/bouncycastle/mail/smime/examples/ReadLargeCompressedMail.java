package org.bouncycastle.mail.smime.examples;

import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import org.bouncycastle.cms.jcajce.ZlibExpanderProvider;
import org.bouncycastle.mail.smime.SMIMECompressedParser;
import org.bouncycastle.mail.smime.SMIMEUtil;
import org.bouncycastle.mail.smime.util.SharedFileInputStream;
/* loaded from: classes4.dex */
public class ReadLargeCompressedMail {
    public static void main(String[] strArr) throws Exception {
        ExampleUtils.dumpContent(SMIMEUtil.toMimeBodyPart(new SMIMECompressedParser(new MimeMessage(Session.getDefaultInstance(System.getProperties(), null), new SharedFileInputStream("compressed.message"))).getContent(new ZlibExpanderProvider())), strArr[0]);
    }
}
