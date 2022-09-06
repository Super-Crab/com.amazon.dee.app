package org.bouncycastle.mail.smime;

import com.amazon.alexa.mobilytics.configuration.DefaultRecordChecker;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.AccessController;
import java.security.PrivilegedAction;
import javax.activation.CommandMap;
import javax.activation.MailcapCommandMap;
import javax.mail.MessagingException;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimePart;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.CMSSignedData;
import org.bouncycastle.cms.CMSTypedData;
/* loaded from: classes4.dex */
public class SMIMESigned extends CMSSignedData {
    MimeBodyPart content;
    Object message;

    static {
        final MailcapCommandMap defaultCommandMap = CommandMap.getDefaultCommandMap();
        defaultCommandMap.addMailcap("application/pkcs7-signature;; x-java-content-handler=org.bouncycastle.mail.smime.handlers.pkcs7_signature");
        defaultCommandMap.addMailcap("application/pkcs7-mime;; x-java-content-handler=org.bouncycastle.mail.smime.handlers.pkcs7_mime");
        defaultCommandMap.addMailcap("application/x-pkcs7-signature;; x-java-content-handler=org.bouncycastle.mail.smime.handlers.x_pkcs7_signature");
        defaultCommandMap.addMailcap("application/x-pkcs7-mime;; x-java-content-handler=org.bouncycastle.mail.smime.handlers.x_pkcs7_mime");
        defaultCommandMap.addMailcap("multipart/signed;; x-java-content-handler=org.bouncycastle.mail.smime.handlers.multipart_signed");
        AccessController.doPrivileged(new PrivilegedAction() { // from class: org.bouncycastle.mail.smime.SMIMESigned.1
            @Override // java.security.PrivilegedAction
            public Object run() {
                CommandMap.setDefaultCommandMap(defaultCommandMap);
                return null;
            }
        });
    }

    public SMIMESigned(Part part) throws MessagingException, CMSException, SMIMEException {
        super(getInputStream(part));
        this.message = part;
        CMSTypedData signedContent = getSignedContent();
        if (signedContent != null) {
            this.content = SMIMEUtil.toMimeBodyPart((byte[]) signedContent.getContent());
        }
    }

    public SMIMESigned(MimeMultipart mimeMultipart) throws MessagingException, CMSException {
        super(new CMSProcessableBodyPartInbound(mimeMultipart.getBodyPart(0)), getInputStream(mimeMultipart.getBodyPart(1)));
        this.message = mimeMultipart;
        this.content = (MimeBodyPart) mimeMultipart.getBodyPart(0);
    }

    public SMIMESigned(MimeMultipart mimeMultipart, String str) throws MessagingException, CMSException {
        super(new CMSProcessableBodyPartInbound(mimeMultipart.getBodyPart(0), str), getInputStream(mimeMultipart.getBodyPart(1)));
        this.message = mimeMultipart;
        this.content = (MimeBodyPart) mimeMultipart.getBodyPart(0);
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

    public MimeBodyPart getContent() {
        return this.content;
    }

    public MimeMessage getContentAsMimeMessage(Session session) throws MessagingException, IOException {
        byte[] byteArray;
        Object content = getSignedContent().getContent();
        if (content instanceof byte[]) {
            byteArray = (byte[]) content;
        } else if (!(content instanceof MimePart)) {
            throw new MessagingException(GeneratedOutlineSupport1.outline75("Could not transfrom content of type ", content != null ? content.getClass().getName() : DefaultRecordChecker.Regex.EMPTY, " into MimeMessage."));
        } else {
            MimePart mimePart = (MimePart) content;
            ByteArrayOutputStream byteArrayOutputStream = mimePart.getSize() > 0 ? new ByteArrayOutputStream(mimePart.getSize()) : new ByteArrayOutputStream();
            mimePart.writeTo(byteArrayOutputStream);
            byteArray = byteArrayOutputStream.toByteArray();
        }
        if (byteArray != null) {
            return new MimeMessage(session, new ByteArrayInputStream(byteArray));
        }
        return null;
    }

    public Object getContentWithSignature() {
        return this.message;
    }
}
