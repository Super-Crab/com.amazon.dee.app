package org.bouncycastle.mail.smime;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import javax.mail.MessagingException;
import javax.mail.Part;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.Recipient;
import org.bouncycastle.cms.RecipientId;
import org.bouncycastle.cms.RecipientInfoGenerator;
import org.bouncycastle.cms.RecipientInformation;
import org.bouncycastle.cms.SignerId;
import org.bouncycastle.cms.SignerInfoGenerator;
import org.bouncycastle.cms.SignerInformation;
import org.bouncycastle.cms.SignerInformationVerifier;
import org.bouncycastle.operator.DigestCalculatorProvider;
import org.bouncycastle.operator.OutputEncryptor;
import org.bouncycastle.util.CollectionStore;
/* loaded from: classes4.dex */
public class SMIMEToolkit {
    private final DigestCalculatorProvider digestCalculatorProvider;

    public SMIMEToolkit(DigestCalculatorProvider digestCalculatorProvider) {
        this.digestCalculatorProvider = digestCalculatorProvider;
    }

    private boolean isAtLeastOneValidSigner(SMIMESignedParser sMIMESignedParser, SignerInformationVerifier signerInformationVerifier) throws CMSException {
        if (signerInformationVerifier.hasAssociatedCertificate()) {
            X509CertificateHolder associatedCertificate = signerInformationVerifier.getAssociatedCertificate();
            SignerInformation signerInformation = sMIMESignedParser.getSignerInfos().get(new SignerId(associatedCertificate.getIssuer(), associatedCertificate.getSerialNumber()));
            if (signerInformation != null) {
                return signerInformation.verify(signerInformationVerifier);
            }
        }
        for (SignerInformation signerInformation2 : sMIMESignedParser.getSignerInfos().getSigners()) {
            if (signerInformation2.verify(signerInformationVerifier)) {
                return true;
            }
        }
        return false;
    }

    public MimeBodyPart decrypt(MimeBodyPart mimeBodyPart, RecipientId recipientId, Recipient recipient) throws SMIMEException, MessagingException {
        try {
            RecipientInformation recipientInformation = new SMIMEEnvelopedParser(mimeBodyPart).getRecipientInfos().get(recipientId);
            if (recipientInformation != null) {
                return SMIMEUtil.toMimeBodyPart(recipientInformation.getContent(recipient));
            }
            return null;
        } catch (IOException e) {
            throw new SMIMEException(GeneratedOutlineSupport1.outline37(e, GeneratedOutlineSupport1.outline107("Parsing failure: ")), e);
        } catch (CMSException e2) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("CMS processing failure: ");
            outline107.append(e2.getMessage());
            throw new SMIMEException(outline107.toString(), e2);
        }
    }

    public MimeBodyPart decrypt(MimeMessage mimeMessage, RecipientId recipientId, Recipient recipient) throws SMIMEException, MessagingException {
        try {
            RecipientInformation recipientInformation = new SMIMEEnvelopedParser(mimeMessage).getRecipientInfos().get(recipientId);
            if (recipientInformation != null) {
                return SMIMEUtil.toMimeBodyPart(recipientInformation.getContent(recipient));
            }
            return null;
        } catch (IOException e) {
            throw new SMIMEException(GeneratedOutlineSupport1.outline37(e, GeneratedOutlineSupport1.outline107("Parsing failure: ")), e);
        } catch (CMSException e2) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("CMS processing failure: ");
            outline107.append(e2.getMessage());
            throw new SMIMEException(outline107.toString(), e2);
        }
    }

    public MimeBodyPart encrypt(MimeBodyPart mimeBodyPart, OutputEncryptor outputEncryptor, RecipientInfoGenerator recipientInfoGenerator) throws SMIMEException {
        SMIMEEnvelopedGenerator sMIMEEnvelopedGenerator = new SMIMEEnvelopedGenerator();
        sMIMEEnvelopedGenerator.addRecipientInfoGenerator(recipientInfoGenerator);
        return sMIMEEnvelopedGenerator.generate(mimeBodyPart, outputEncryptor);
    }

    public MimeBodyPart encrypt(MimeMessage mimeMessage, OutputEncryptor outputEncryptor, RecipientInfoGenerator recipientInfoGenerator) throws SMIMEException {
        SMIMEEnvelopedGenerator sMIMEEnvelopedGenerator = new SMIMEEnvelopedGenerator();
        sMIMEEnvelopedGenerator.addRecipientInfoGenerator(recipientInfoGenerator);
        return sMIMEEnvelopedGenerator.generate(mimeMessage, outputEncryptor);
    }

    public MimeBodyPart encrypt(MimeMultipart mimeMultipart, OutputEncryptor outputEncryptor, RecipientInfoGenerator recipientInfoGenerator) throws SMIMEException, MessagingException {
        SMIMEEnvelopedGenerator sMIMEEnvelopedGenerator = new SMIMEEnvelopedGenerator();
        sMIMEEnvelopedGenerator.addRecipientInfoGenerator(recipientInfoGenerator);
        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(mimeMultipart);
        return sMIMEEnvelopedGenerator.generate(mimeBodyPart, outputEncryptor);
    }

    public X509CertificateHolder extractCertificate(Part part, SignerInformation signerInformation) throws SMIMEException, MessagingException {
        try {
            Iterator it2 = ((!(part instanceof MimeMessage) || !part.isMimeType("multipart/signed")) ? new SMIMESignedParser(this.digestCalculatorProvider, part) : new SMIMESignedParser(this.digestCalculatorProvider, (MimeMultipart) part.getContent())).getCertificates().getMatches(signerInformation.getSID()).iterator();
            if (!it2.hasNext()) {
                return null;
            }
            return (X509CertificateHolder) it2.next();
        } catch (IOException e) {
            throw new SMIMEException(GeneratedOutlineSupport1.outline37(e, GeneratedOutlineSupport1.outline107("Parsing failure: ")), e);
        } catch (CMSException e2) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("CMS processing failure: ");
            outline107.append(e2.getMessage());
            throw new SMIMEException(outline107.toString(), e2);
        }
    }

    public X509CertificateHolder extractCertificate(MimeMultipart mimeMultipart, SignerInformation signerInformation) throws SMIMEException, MessagingException {
        try {
            Iterator it2 = new SMIMESignedParser(this.digestCalculatorProvider, mimeMultipart).getCertificates().getMatches(signerInformation.getSID()).iterator();
            if (!it2.hasNext()) {
                return null;
            }
            return (X509CertificateHolder) it2.next();
        } catch (CMSException e) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("CMS processing failure: ");
            outline107.append(e.getMessage());
            throw new SMIMEException(outline107.toString(), e);
        }
    }

    public boolean isEncrypted(Part part) throws MessagingException {
        return part.getHeader("Content-Type")[0].equals("application/pkcs7-mime; name=\"smime.p7m\"; smime-type=enveloped-data");
    }

    public boolean isSigned(Part part) throws MessagingException {
        return part.getHeader("Content-Type")[0].startsWith("multipart/signed") || part.getHeader("Content-Type")[0].equals("application/pkcs7-mime; name=smime.p7m; smime-type=signed-data");
    }

    public boolean isSigned(MimeMultipart mimeMultipart) throws MessagingException {
        return mimeMultipart.getBodyPart(1).getHeader("Content-Type")[0].equals("application/pkcs7-signature; name=smime.p7s; smime-type=signed-data");
    }

    public boolean isValidSignature(Part part, SignerInformationVerifier signerInformationVerifier) throws SMIMEException, MessagingException {
        try {
            return isAtLeastOneValidSigner(part.isMimeType("multipart/signed") ? new SMIMESignedParser(this.digestCalculatorProvider, (MimeMultipart) part.getContent()) : new SMIMESignedParser(this.digestCalculatorProvider, part), signerInformationVerifier);
        } catch (IOException e) {
            throw new SMIMEException(GeneratedOutlineSupport1.outline37(e, GeneratedOutlineSupport1.outline107("Parsing failure: ")), e);
        } catch (CMSException e2) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("CMS processing failure: ");
            outline107.append(e2.getMessage());
            throw new SMIMEException(outline107.toString(), e2);
        }
    }

    public boolean isValidSignature(MimeMultipart mimeMultipart, SignerInformationVerifier signerInformationVerifier) throws SMIMEException, MessagingException {
        try {
            return isAtLeastOneValidSigner(new SMIMESignedParser(this.digestCalculatorProvider, mimeMultipart), signerInformationVerifier);
        } catch (CMSException e) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("CMS processing failure: ");
            outline107.append(e.getMessage());
            throw new SMIMEException(outline107.toString(), e);
        }
    }

    public MimeMultipart sign(MimeBodyPart mimeBodyPart, SignerInfoGenerator signerInfoGenerator) throws SMIMEException {
        SMIMESignedGenerator sMIMESignedGenerator = new SMIMESignedGenerator();
        if (signerInfoGenerator.hasAssociatedCertificate()) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(signerInfoGenerator.getAssociatedCertificate());
            sMIMESignedGenerator.addCertificates(new CollectionStore(arrayList));
        }
        sMIMESignedGenerator.addSignerInfoGenerator(signerInfoGenerator);
        return sMIMESignedGenerator.generate(mimeBodyPart);
    }

    public MimeBodyPart signEncapsulated(MimeBodyPart mimeBodyPart, SignerInfoGenerator signerInfoGenerator) throws SMIMEException {
        SMIMESignedGenerator sMIMESignedGenerator = new SMIMESignedGenerator();
        if (signerInfoGenerator.hasAssociatedCertificate()) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(signerInfoGenerator.getAssociatedCertificate());
            sMIMESignedGenerator.addCertificates(new CollectionStore(arrayList));
        }
        sMIMESignedGenerator.addSignerInfoGenerator(signerInfoGenerator);
        return sMIMESignedGenerator.generateEncapsulated(mimeBodyPart);
    }
}
