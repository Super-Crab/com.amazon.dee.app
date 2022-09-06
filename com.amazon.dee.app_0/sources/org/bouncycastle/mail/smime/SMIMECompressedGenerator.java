package org.bouncycastle.mail.smime;

import java.io.IOException;
import java.io.OutputStream;
import java.security.AccessController;
import java.security.PrivilegedAction;
import javax.activation.CommandMap;
import javax.activation.MailcapCommandMap;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import org.bouncycastle.cms.CMSCompressedDataStreamGenerator;
import org.bouncycastle.operator.OutputCompressor;
/* loaded from: classes4.dex */
public class SMIMECompressedGenerator extends SMIMEGenerator {
    private static final String COMPRESSED_CONTENT_TYPE = "application/pkcs7-mime; name=\"smime.p7z\"; smime-type=compressed-data";
    public static final String ZLIB = "1.2.840.113549.1.9.16.3.8";

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public class ContentCompressor implements SMIMEStreamingProcessor {
        private final OutputCompressor compressor;
        private final MimeBodyPart content;

        ContentCompressor(MimeBodyPart mimeBodyPart, OutputCompressor outputCompressor) {
            this.content = mimeBodyPart;
            this.compressor = outputCompressor;
        }

        @Override // org.bouncycastle.mail.smime.SMIMEStreamingProcessor
        public void write(OutputStream outputStream) throws IOException {
            OutputStream open = new CMSCompressedDataStreamGenerator().open(outputStream, this.compressor);
            try {
                this.content.writeTo(open);
                open.close();
            } catch (MessagingException e) {
                throw new IOException(e.toString());
            }
        }
    }

    static {
        MailcapCommandMap defaultCommandMap = CommandMap.getDefaultCommandMap();
        if (defaultCommandMap instanceof MailcapCommandMap) {
            final MailcapCommandMap mailcapCommandMap = defaultCommandMap;
            mailcapCommandMap.addMailcap("application/pkcs7-mime;; x-java-content-handler=org.bouncycastle.mail.smime.handlers.pkcs7_mime");
            mailcapCommandMap.addMailcap("application/x-pkcs7-mime;; x-java-content-handler=org.bouncycastle.mail.smime.handlers.x_pkcs7_mime");
            AccessController.doPrivileged(new PrivilegedAction() { // from class: org.bouncycastle.mail.smime.SMIMECompressedGenerator.1
                @Override // java.security.PrivilegedAction
                public Object run() {
                    CommandMap.setDefaultCommandMap(mailcapCommandMap);
                    return null;
                }
            });
        }
    }

    private MimeBodyPart make(MimeBodyPart mimeBodyPart, OutputCompressor outputCompressor) throws SMIMEException {
        try {
            MimeBodyPart mimeBodyPart2 = new MimeBodyPart();
            mimeBodyPart2.setContent(new ContentCompressor(mimeBodyPart, outputCompressor), COMPRESSED_CONTENT_TYPE);
            mimeBodyPart2.addHeader("Content-Type", COMPRESSED_CONTENT_TYPE);
            mimeBodyPart2.addHeader("Content-Disposition", "attachment; filename=\"smime.p7z\"");
            mimeBodyPart2.addHeader("Content-Description", "S/MIME Compressed Message");
            mimeBodyPart2.addHeader("Content-Transfer-Encoding", this.encoding);
            return mimeBodyPart2;
        } catch (MessagingException e) {
            throw new SMIMEException("exception putting multi-part together.", e);
        }
    }

    public MimeBodyPart generate(MimeBodyPart mimeBodyPart, OutputCompressor outputCompressor) throws SMIMEException {
        return make(makeContentBodyPart(mimeBodyPart), outputCompressor);
    }

    public MimeBodyPart generate(MimeMessage mimeMessage, OutputCompressor outputCompressor) throws SMIMEException {
        try {
            mimeMessage.saveChanges();
            return make(makeContentBodyPart(mimeMessage), outputCompressor);
        } catch (MessagingException e) {
            throw new SMIMEException("unable to save message", e);
        }
    }
}
