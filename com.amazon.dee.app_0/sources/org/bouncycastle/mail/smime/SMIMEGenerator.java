package org.bouncycastle.mail.smime;

import com.amazonaws.services.s3.internal.crypto.JceEncryptionConstants;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.KeyGenerator;
import javax.mail.Header;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import org.bouncycastle.cms.CMSEnvelopedGenerator;
import org.bouncycastle.util.Strings;
/* loaded from: classes4.dex */
public class SMIMEGenerator {
    private static Map BASE_CIPHER_NAMES = new HashMap();
    protected boolean useBase64 = true;
    protected String encoding = "base64";

    static {
        BASE_CIPHER_NAMES.put(CMSEnvelopedGenerator.DES_EDE3_CBC, "DESEDE");
        BASE_CIPHER_NAMES.put(CMSEnvelopedGenerator.AES128_CBC, JceEncryptionConstants.SYMMETRIC_KEY_ALGORITHM);
        BASE_CIPHER_NAMES.put(CMSEnvelopedGenerator.AES192_CBC, JceEncryptionConstants.SYMMETRIC_KEY_ALGORITHM);
        BASE_CIPHER_NAMES.put(CMSEnvelopedGenerator.AES256_CBC, JceEncryptionConstants.SYMMETRIC_KEY_ALGORITHM);
    }

    private KeyGenerator createKeyGenerator(String str, Provider provider) throws NoSuchAlgorithmException {
        return provider != null ? KeyGenerator.getInstance(str, provider) : KeyGenerator.getInstance(str);
    }

    private void extractHeaders(MimeBodyPart mimeBodyPart, MimeMessage mimeMessage) throws MessagingException {
        Enumeration allHeaders = mimeMessage.getAllHeaders();
        while (allHeaders.hasMoreElements()) {
            Header header = (Header) allHeaders.nextElement();
            mimeBodyPart.addHeader(header.getName(), header.getValue());
        }
    }

    protected KeyGenerator createSymmetricKeyGenerator(String str, Provider provider) throws NoSuchAlgorithmException {
        try {
            return createKeyGenerator(str, provider);
        } catch (NoSuchAlgorithmException e) {
            try {
                String str2 = (String) BASE_CIPHER_NAMES.get(str);
                if (str2 != null) {
                    return createKeyGenerator(str2, provider);
                }
            } catch (NoSuchAlgorithmException unused) {
            }
            if (provider == null) {
                throw e;
            }
            return createSymmetricKeyGenerator(str, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public MimeBodyPart makeContentBodyPart(MimeBodyPart mimeBodyPart) throws SMIMEException {
        try {
            MimeMessage mimeMessage = new MimeMessage(null) { // from class: org.bouncycastle.mail.smime.SMIMEGenerator.1
                @Override // javax.mail.internet.MimeMessage
                protected void updateMessageID() throws MessagingException {
                }
            };
            Enumeration allHeaders = mimeBodyPart.getAllHeaders();
            mimeMessage.setDataHandler(mimeBodyPart.getDataHandler());
            while (allHeaders.hasMoreElements()) {
                Header header = (Header) allHeaders.nextElement();
                mimeMessage.setHeader(header.getName(), header.getValue());
            }
            mimeMessage.saveChanges();
            Enumeration allHeaders2 = mimeMessage.getAllHeaders();
            while (allHeaders2.hasMoreElements()) {
                Header header2 = (Header) allHeaders2.nextElement();
                if (Strings.toLowerCase(header2.getName()).startsWith("content-")) {
                    mimeBodyPart.setHeader(header2.getName(), header2.getValue());
                }
            }
            return mimeBodyPart;
        } catch (MessagingException e) {
            throw new SMIMEException("exception saving message state.", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public MimeBodyPart makeContentBodyPart(MimeMessage mimeMessage) throws SMIMEException {
        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        try {
            try {
                mimeMessage.removeHeader("Message-Id");
                mimeMessage.removeHeader("Mime-Version");
                try {
                    if (mimeMessage.getContent() instanceof Multipart) {
                        mimeBodyPart.setContent(mimeMessage.getRawInputStream(), mimeMessage.getContentType());
                        extractHeaders(mimeBodyPart, mimeMessage);
                        return mimeBodyPart;
                    }
                } catch (MessagingException unused) {
                }
                mimeBodyPart.setContent(mimeMessage.getContent(), mimeMessage.getContentType());
                mimeBodyPart.setDataHandler(mimeMessage.getDataHandler());
                extractHeaders(mimeBodyPart, mimeMessage);
                return mimeBodyPart;
            } catch (MessagingException e) {
                throw new SMIMEException("exception saving message state.", e);
            }
        } catch (IOException e2) {
            throw new SMIMEException("exception getting message content.", e2);
        }
    }

    public void setContentTransferEncoding(String str) {
        this.encoding = str;
        this.useBase64 = Strings.toLowerCase(str).equals("base64");
    }
}
