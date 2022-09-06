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
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.cms.CMSEnvelopedDataStreamGenerator;
import org.bouncycastle.cms.CMSEnvelopedGenerator;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.RecipientInfoGenerator;
import org.bouncycastle.operator.OutputEncryptor;
/* loaded from: classes4.dex */
public class SMIMEEnvelopedGenerator extends SMIMEGenerator {
    public static final String CAST5_CBC = "1.2.840.113533.7.66.10";
    private static final String ENCRYPTED_CONTENT_TYPE = "application/pkcs7-mime; name=\"smime.p7m\"; smime-type=enveloped-data";
    public static final String IDEA_CBC = "1.3.6.1.4.1.188.7.1.1.2";
    private EnvelopedGenerator fact = new EnvelopedGenerator();
    public static final String DES_EDE3_CBC = CMSEnvelopedGenerator.DES_EDE3_CBC;
    public static final String RC2_CBC = CMSEnvelopedGenerator.RC2_CBC;
    public static final String AES128_CBC = CMSEnvelopedGenerator.AES128_CBC;
    public static final String AES192_CBC = CMSEnvelopedGenerator.AES192_CBC;
    public static final String AES256_CBC = CMSEnvelopedGenerator.AES256_CBC;
    public static final String CAMELLIA128_CBC = CMSEnvelopedGenerator.CAMELLIA128_CBC;
    public static final String CAMELLIA192_CBC = CMSEnvelopedGenerator.CAMELLIA192_CBC;
    public static final String CAMELLIA256_CBC = CMSEnvelopedGenerator.CAMELLIA256_CBC;
    public static final String SEED_CBC = CMSEnvelopedGenerator.SEED_CBC;
    public static final String DES_EDE3_WRAP = CMSEnvelopedGenerator.DES_EDE3_WRAP;
    public static final String AES128_WRAP = CMSEnvelopedGenerator.AES128_WRAP;
    public static final String AES256_WRAP = CMSEnvelopedGenerator.AES256_WRAP;
    public static final String CAMELLIA128_WRAP = CMSEnvelopedGenerator.CAMELLIA128_WRAP;
    public static final String CAMELLIA192_WRAP = CMSEnvelopedGenerator.CAMELLIA192_WRAP;
    public static final String CAMELLIA256_WRAP = CMSEnvelopedGenerator.CAMELLIA256_WRAP;
    public static final String SEED_WRAP = CMSEnvelopedGenerator.SEED_WRAP;
    public static final String ECDH_SHA1KDF = CMSEnvelopedGenerator.ECDH_SHA1KDF;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public class ContentEncryptor implements SMIMEStreamingProcessor {
        private final MimeBodyPart _content;
        private OutputEncryptor _encryptor;
        private boolean _firstTime = true;

        ContentEncryptor(MimeBodyPart mimeBodyPart, OutputEncryptor outputEncryptor) {
            this._content = mimeBodyPart;
            this._encryptor = outputEncryptor;
        }

        @Override // org.bouncycastle.mail.smime.SMIMEStreamingProcessor
        public void write(OutputStream outputStream) throws IOException {
            OutputStream regenerate;
            try {
                if (this._firstTime) {
                    regenerate = SMIMEEnvelopedGenerator.this.fact.open(outputStream, this._encryptor);
                    this._firstTime = false;
                } else {
                    regenerate = SMIMEEnvelopedGenerator.this.fact.regenerate(outputStream, this._encryptor);
                }
                MailcapCommandMap defaultCommandMap = CommandMap.getDefaultCommandMap();
                if (defaultCommandMap instanceof MailcapCommandMap) {
                    this._content.getDataHandler().setCommandMap(MailcapUtil.addCommands(defaultCommandMap));
                }
                this._content.writeTo(regenerate);
                regenerate.close();
            } catch (MessagingException e) {
                throw new WrappingIOException(e.toString(), e);
            } catch (CMSException e2) {
                throw new WrappingIOException(e2.toString(), e2);
            }
        }
    }

    /* loaded from: classes4.dex */
    private class EnvelopedGenerator extends CMSEnvelopedDataStreamGenerator {
        private ASN1ObjectIdentifier dataType;
        private ASN1EncodableVector recipientInfos;

        private EnvelopedGenerator() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // org.bouncycastle.cms.CMSEnvelopedDataStreamGenerator
        public OutputStream open(ASN1ObjectIdentifier aSN1ObjectIdentifier, OutputStream outputStream, ASN1EncodableVector aSN1EncodableVector, OutputEncryptor outputEncryptor) throws IOException {
            this.dataType = aSN1ObjectIdentifier;
            this.recipientInfos = aSN1EncodableVector;
            return super.open(aSN1ObjectIdentifier, outputStream, aSN1EncodableVector, outputEncryptor);
        }

        OutputStream regenerate(OutputStream outputStream, OutputEncryptor outputEncryptor) throws IOException {
            return super.open(this.dataType, outputStream, this.recipientInfos, outputEncryptor);
        }
    }

    /* loaded from: classes4.dex */
    private static class WrappingIOException extends IOException {
        private Throwable cause;

        WrappingIOException(String str, Throwable th) {
            super(str);
            this.cause = th;
        }

        @Override // java.lang.Throwable
        public Throwable getCause() {
            return this.cause;
        }
    }

    static {
        AccessController.doPrivileged(new PrivilegedAction() { // from class: org.bouncycastle.mail.smime.SMIMEEnvelopedGenerator.1
            @Override // java.security.PrivilegedAction
            public Object run() {
                MailcapCommandMap defaultCommandMap = CommandMap.getDefaultCommandMap();
                if (defaultCommandMap instanceof MailcapCommandMap) {
                    CommandMap.setDefaultCommandMap(MailcapUtil.addCommands(defaultCommandMap));
                    return null;
                }
                return null;
            }
        });
    }

    private MimeBodyPart make(MimeBodyPart mimeBodyPart, OutputEncryptor outputEncryptor) throws SMIMEException {
        try {
            MimeBodyPart mimeBodyPart2 = new MimeBodyPart();
            mimeBodyPart2.setContent(new ContentEncryptor(mimeBodyPart, outputEncryptor), ENCRYPTED_CONTENT_TYPE);
            mimeBodyPart2.addHeader("Content-Type", ENCRYPTED_CONTENT_TYPE);
            mimeBodyPart2.addHeader("Content-Disposition", "attachment; filename=\"smime.p7m\"");
            mimeBodyPart2.addHeader("Content-Description", "S/MIME Encrypted Message");
            mimeBodyPart2.addHeader("Content-Transfer-Encoding", this.encoding);
            return mimeBodyPart2;
        } catch (MessagingException e) {
            throw new SMIMEException("exception putting multi-part together.", e);
        }
    }

    public void addRecipientInfoGenerator(RecipientInfoGenerator recipientInfoGenerator) throws IllegalArgumentException {
        this.fact.addRecipientInfoGenerator(recipientInfoGenerator);
    }

    public MimeBodyPart generate(MimeBodyPart mimeBodyPart, OutputEncryptor outputEncryptor) throws SMIMEException {
        return make(makeContentBodyPart(mimeBodyPart), outputEncryptor);
    }

    public MimeBodyPart generate(MimeMessage mimeMessage, OutputEncryptor outputEncryptor) throws SMIMEException {
        try {
            mimeMessage.saveChanges();
            return make(makeContentBodyPart(mimeMessage), outputEncryptor);
        } catch (MessagingException e) {
            throw new SMIMEException("unable to save message", e);
        }
    }

    public void setBerEncodeRecipients(boolean z) {
        this.fact.setBEREncodeRecipients(z);
    }
}
