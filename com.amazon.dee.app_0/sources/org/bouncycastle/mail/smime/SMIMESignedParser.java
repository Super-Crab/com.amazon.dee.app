package org.bouncycastle.mail.smime;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.AccessController;
import java.security.PrivilegedAction;
import javax.activation.CommandMap;
import javax.activation.MailcapCommandMap;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.CMSSignedDataParser;
import org.bouncycastle.cms.CMSTypedStream;
import org.bouncycastle.operator.DigestCalculatorProvider;
/* loaded from: classes4.dex */
public class SMIMESignedParser extends CMSSignedDataParser {
    MimeBodyPart content;
    Object message;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public static class TemporaryFileInputStream extends BufferedInputStream {
        private final File _file;

        TemporaryFileInputStream(File file) throws FileNotFoundException {
            super(new FileInputStream(file));
            this._file = file;
        }

        @Override // java.io.BufferedInputStream, java.io.FilterInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            super.close();
            this._file.delete();
        }
    }

    static {
        MailcapCommandMap defaultCommandMap = CommandMap.getDefaultCommandMap();
        if (defaultCommandMap instanceof MailcapCommandMap) {
            final MailcapCommandMap mailcapCommandMap = defaultCommandMap;
            mailcapCommandMap.addMailcap("application/pkcs7-signature;; x-java-content-handler=org.bouncycastle.mail.smime.handlers.pkcs7_signature");
            mailcapCommandMap.addMailcap("application/pkcs7-mime;; x-java-content-handler=org.bouncycastle.mail.smime.handlers.pkcs7_mime");
            mailcapCommandMap.addMailcap("application/x-pkcs7-signature;; x-java-content-handler=org.bouncycastle.mail.smime.handlers.x_pkcs7_signature");
            mailcapCommandMap.addMailcap("application/x-pkcs7-mime;; x-java-content-handler=org.bouncycastle.mail.smime.handlers.x_pkcs7_mime");
            mailcapCommandMap.addMailcap("multipart/signed;; x-java-content-handler=org.bouncycastle.mail.smime.handlers.multipart_signed");
            AccessController.doPrivileged(new PrivilegedAction() { // from class: org.bouncycastle.mail.smime.SMIMESignedParser.1
                @Override // java.security.PrivilegedAction
                public Object run() {
                    CommandMap.setDefaultCommandMap(mailcapCommandMap);
                    return null;
                }
            });
        }
    }

    public SMIMESignedParser(DigestCalculatorProvider digestCalculatorProvider, Part part) throws MessagingException, CMSException, SMIMEException {
        super(digestCalculatorProvider, getInputStream(part));
        this.message = part;
        CMSTypedStream signedContent = getSignedContent();
        if (signedContent != null) {
            this.content = SMIMEUtil.toWriteOnceBodyPart(signedContent);
        }
    }

    public SMIMESignedParser(DigestCalculatorProvider digestCalculatorProvider, Part part, File file) throws MessagingException, CMSException, SMIMEException {
        super(digestCalculatorProvider, getInputStream(part));
        this.message = part;
        CMSTypedStream signedContent = getSignedContent();
        if (signedContent != null) {
            this.content = SMIMEUtil.toMimeBodyPart(signedContent, file);
        }
    }

    public SMIMESignedParser(DigestCalculatorProvider digestCalculatorProvider, MimeMultipart mimeMultipart) throws MessagingException, CMSException {
        this(digestCalculatorProvider, mimeMultipart, getTmpFile());
    }

    public SMIMESignedParser(DigestCalculatorProvider digestCalculatorProvider, MimeMultipart mimeMultipart, File file) throws MessagingException, CMSException {
        this(digestCalculatorProvider, mimeMultipart, "7bit", file);
    }

    public SMIMESignedParser(DigestCalculatorProvider digestCalculatorProvider, MimeMultipart mimeMultipart, String str) throws MessagingException, CMSException {
        this(digestCalculatorProvider, mimeMultipart, str, getTmpFile());
    }

    public SMIMESignedParser(DigestCalculatorProvider digestCalculatorProvider, MimeMultipart mimeMultipart, String str, File file) throws MessagingException, CMSException {
        super(digestCalculatorProvider, getSignedInputStream(mimeMultipart.getBodyPart(0), str, file), getInputStream(mimeMultipart.getBodyPart(1)));
        this.message = mimeMultipart;
        this.content = (MimeBodyPart) mimeMultipart.getBodyPart(0);
        drainContent();
    }

    private void drainContent() throws CMSException {
        try {
            getSignedContent().drain();
        } catch (IOException e) {
            throw new CMSException(GeneratedOutlineSupport1.outline65("unable to read content for verification: ", e), e);
        }
    }

    private static InputStream getInputStream(Part part) throws MessagingException {
        try {
            if (part.isMimeType("multipart/signed")) {
                throw new MessagingException("attempt to create signed data object from multipart content - use MimeMultipart constructor.");
            }
            return part.getInputStream();
        } catch (IOException e) {
            throw new MessagingException(GeneratedOutlineSupport1.outline65("can't extract input stream: ", e));
        }
    }

    private static CMSTypedStream getSignedInputStream(BodyPart bodyPart, String str, File file) throws MessagingException {
        try {
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
            SMIMEUtil.outputBodyPart(bufferedOutputStream, true, bodyPart, str);
            bufferedOutputStream.close();
            return new CMSTypedStream(new TemporaryFileInputStream(file));
        } catch (IOException e) {
            throw new MessagingException(GeneratedOutlineSupport1.outline65("can't extract input stream: ", e));
        }
    }

    private static File getTmpFile() throws MessagingException {
        try {
            return File.createTempFile("bcMail", ".mime");
        } catch (IOException e) {
            throw new MessagingException(GeneratedOutlineSupport1.outline65("can't extract input stream: ", e));
        }
    }

    public MimeBodyPart getContent() {
        return this.content;
    }

    public MimeMessage getContentAsMimeMessage(Session session) throws MessagingException, IOException {
        Object obj = this.message;
        return obj instanceof MimeMultipart ? new MimeMessage(session, ((MimeMultipart) obj).getBodyPart(0).getInputStream()) : new MimeMessage(session, getSignedContent().getContentStream());
    }

    public Object getContentWithSignature() {
        return this.message;
    }
}
