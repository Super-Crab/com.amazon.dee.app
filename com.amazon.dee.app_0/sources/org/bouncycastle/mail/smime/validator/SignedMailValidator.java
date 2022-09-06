package org.bouncycastle.mail.smime.validator;

import com.amazon.whispercloak.KeyUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.cert.CertPath;
import java.security.cert.CertStore;
import java.security.cert.CertStoreException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateFactory;
import java.security.cert.PKIXParameters;
import java.security.cert.TrustAnchor;
import java.security.cert.X509CertSelector;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.mail.Address;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1String;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERIA5String;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.cms.Attribute;
import org.bouncycastle.asn1.cms.AttributeTable;
import org.bouncycastle.asn1.cms.CMSAttributes;
import org.bouncycastle.asn1.cms.Time;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.x500.AttributeTypeAndValue;
import org.bouncycastle.asn1.x500.RDN;
import org.bouncycastle.asn1.x509.AuthorityKeyIdentifier;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.TBSCertificate;
import org.bouncycastle.cert.jcajce.JcaCertStoreBuilder;
import org.bouncycastle.cms.SignerInformation;
import org.bouncycastle.cms.SignerInformationStore;
import org.bouncycastle.cms.jcajce.JcaX509CertSelectorConverter;
import org.bouncycastle.i18n.ErrorBundle;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.mail.smime.SMIMESigned;
import org.bouncycastle.x509.PKIXCertPathReviewer;
/* loaded from: classes4.dex */
public class SignedMailValidator {
    private static final String RESOURCE_NAME = "org.bouncycastle.mail.smime.validator.SignedMailValidatorMessages";
    private static final long THIRTY_YEARS_IN_MILLI_SEC = 946728000000L;
    private static final int shortKeyLength = 512;
    private Class certPathReviewerClass;
    private CertStore certs;
    private String[] fromAddresses;
    private Map results;
    private SignerInformationStore signers;
    private static final Class DEFAULT_CERT_PATH_REVIEWER = PKIXCertPathReviewer.class;
    private static final String EXT_KEY_USAGE = Extension.extendedKeyUsage.getId();
    private static final String SUBJECT_ALTERNATIVE_NAME = Extension.subjectAlternativeName.getId();
    private static final JcaX509CertSelectorConverter selectorConverter = new JcaX509CertSelectorConverter();

    /* loaded from: classes4.dex */
    public class ValidationResult {
        private List errors;
        private List notifications;
        private PKIXCertPathReviewer review;
        private boolean signVerified;
        private List userProvidedCerts;

        ValidationResult(PKIXCertPathReviewer pKIXCertPathReviewer, boolean z, List list, List list2, List list3) {
            this.review = pKIXCertPathReviewer;
            this.errors = list;
            this.notifications = list2;
            this.signVerified = z;
            this.userProvidedCerts = list3;
        }

        public CertPath getCertPath() {
            PKIXCertPathReviewer pKIXCertPathReviewer = this.review;
            if (pKIXCertPathReviewer != null) {
                return pKIXCertPathReviewer.getCertPath();
            }
            return null;
        }

        public PKIXCertPathReviewer getCertPathReview() {
            return this.review;
        }

        public List getErrors() {
            return this.errors;
        }

        public List getNotifications() {
            return this.notifications;
        }

        public List getUserProvidedCerts() {
            return this.userProvidedCerts;
        }

        public boolean isValidSignature() {
            PKIXCertPathReviewer pKIXCertPathReviewer = this.review;
            return pKIXCertPathReviewer != null && this.signVerified && pKIXCertPathReviewer.isValidCertPath() && this.errors.isEmpty();
        }

        public boolean isVerifiedSignature() {
            return this.signVerified;
        }
    }

    public SignedMailValidator(MimeMessage mimeMessage, PKIXParameters pKIXParameters) throws SignedMailValidatorException {
        this(mimeMessage, pKIXParameters, DEFAULT_CERT_PATH_REVIEWER);
    }

    public SignedMailValidator(MimeMessage mimeMessage, PKIXParameters pKIXParameters, Class cls) throws SignedMailValidatorException {
        SMIMESigned sMIMESigned;
        this.certPathReviewerClass = cls;
        if (DEFAULT_CERT_PATH_REVIEWER.isAssignableFrom(cls)) {
            try {
                if (mimeMessage.isMimeType("multipart/signed")) {
                    sMIMESigned = new SMIMESigned((MimeMultipart) mimeMessage.getContent());
                } else {
                    if (!mimeMessage.isMimeType("application/pkcs7-mime") && !mimeMessage.isMimeType("application/x-pkcs7-mime")) {
                        throw new SignedMailValidatorException(new ErrorBundle(RESOURCE_NAME, "SignedMailValidator.noSignedMessage"));
                    }
                    sMIMESigned = new SMIMESigned(mimeMessage);
                }
                this.certs = new JcaCertStoreBuilder().addCertificates(sMIMESigned.getCertificates()).addCRLs(sMIMESigned.getCRLs()).setProvider(BouncyCastleProvider.PROVIDER_NAME).build();
                this.signers = sMIMESigned.getSignerInfos();
                Address[] from = mimeMessage.getFrom();
                InternetAddress internetAddress = null;
                try {
                    if (mimeMessage.getHeader("Sender") != null) {
                        internetAddress = new InternetAddress(mimeMessage.getHeader("Sender")[0]);
                    }
                } catch (MessagingException unused) {
                }
                int length = from != null ? from.length : 0;
                this.fromAddresses = new String[(internetAddress != null ? 1 : 0) + length];
                for (int i = 0; i < length; i++) {
                    this.fromAddresses[i] = ((InternetAddress) from[i]).getAddress();
                }
                if (internetAddress != null) {
                    this.fromAddresses[length] = internetAddress.getAddress();
                }
                this.results = new HashMap();
                validateSignatures(pKIXParameters);
                return;
            } catch (Exception e) {
                if (!(e instanceof SignedMailValidatorException)) {
                    throw new SignedMailValidatorException(new ErrorBundle(RESOURCE_NAME, "SignedMailValidator.exceptionReadingMessage", new Object[]{e.getMessage(), e, e.getClass().getName()}), e);
                }
                throw ((SignedMailValidatorException) e);
            }
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline38(DEFAULT_CERT_PATH_REVIEWER, GeneratedOutlineSupport1.outline107("certPathReviewerClass is not a subclass of ")));
    }

    static String addressesToString(Object[] objArr) {
        if (objArr == null) {
            return "null";
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(JsonReaderKt.BEGIN_LIST);
        for (int i = 0; i != objArr.length; i++) {
            if (i > 0) {
                stringBuffer.append(", ");
            }
            stringBuffer.append(String.valueOf(objArr[i]));
        }
        stringBuffer.append(JsonReaderKt.END_LIST);
        return stringBuffer.toString();
    }

    public static CertPath createCertPath(X509Certificate x509Certificate, Set set, List list) throws GeneralSecurityException {
        return (CertPath) createCertPath(x509Certificate, set, list, null)[0];
    }

    public static Object[] createCertPath(X509Certificate x509Certificate, Set set, List list, List list2) throws GeneralSecurityException {
        boolean z;
        boolean z2;
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        ArrayList arrayList = new ArrayList();
        linkedHashSet.add(x509Certificate);
        arrayList.add(new Boolean(true));
        X509Certificate x509Certificate2 = null;
        boolean z3 = false;
        while (x509Certificate != null && !z3) {
            Iterator it2 = set.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    break;
                }
                TrustAnchor trustAnchor = (TrustAnchor) it2.next();
                X509Certificate trustedCert = trustAnchor.getTrustedCert();
                if (trustedCert != null) {
                    if (trustedCert.getSubjectX500Principal().equals(x509Certificate.getIssuerX500Principal())) {
                        try {
                            x509Certificate.verify(trustedCert.getPublicKey(), BouncyCastleProvider.PROVIDER_NAME);
                            z3 = true;
                            x509Certificate2 = trustedCert;
                            break;
                        } catch (Exception unused) {
                            continue;
                        }
                    } else {
                        continue;
                    }
                } else if (trustAnchor.getCAName().equals(x509Certificate.getIssuerX500Principal().getName())) {
                    x509Certificate.verify(trustAnchor.getCAPublicKey(), BouncyCastleProvider.PROVIDER_NAME);
                    z3 = true;
                    break;
                }
            }
            if (!z3) {
                X509CertSelector x509CertSelector = new X509CertSelector();
                try {
                    x509CertSelector.setSubject(x509Certificate.getIssuerX500Principal().getEncoded());
                    byte[] extensionValue = x509Certificate.getExtensionValue(Extension.authorityKeyIdentifier.getId());
                    if (extensionValue != null) {
                        try {
                            AuthorityKeyIdentifier authorityKeyIdentifier = AuthorityKeyIdentifier.getInstance(getObject(extensionValue));
                            if (authorityKeyIdentifier.getKeyIdentifier() != null) {
                                x509CertSelector.setSubjectKeyIdentifier(new DEROctetString(authorityKeyIdentifier.getKeyIdentifier()).getEncoded("DER"));
                            }
                        } catch (IOException unused2) {
                        }
                    }
                    x509Certificate = findNextCert(list, x509CertSelector, linkedHashSet);
                    if (x509Certificate != null || list2 == null) {
                        z2 = false;
                    } else {
                        x509Certificate = findNextCert(list2, x509CertSelector, linkedHashSet);
                        z2 = true;
                    }
                    if (x509Certificate != null) {
                        linkedHashSet.add(x509Certificate);
                        arrayList.add(new Boolean(z2));
                    }
                } catch (IOException e) {
                    throw new IllegalStateException(e.toString());
                }
            }
        }
        if (z3) {
            if (x509Certificate2 == null || !x509Certificate2.getSubjectX500Principal().equals(x509Certificate2.getIssuerX500Principal())) {
                X509CertSelector x509CertSelector2 = new X509CertSelector();
                try {
                    x509CertSelector2.setSubject(x509Certificate.getIssuerX500Principal().getEncoded());
                    x509CertSelector2.setIssuer(x509Certificate.getIssuerX500Principal().getEncoded());
                    X509Certificate findNextCert = findNextCert(list, x509CertSelector2, linkedHashSet);
                    if (findNextCert != null || list2 == null) {
                        z = false;
                    } else {
                        findNextCert = findNextCert(list2, x509CertSelector2, linkedHashSet);
                        z = true;
                    }
                    if (findNextCert != null) {
                        try {
                            x509Certificate.verify(findNextCert.getPublicKey(), BouncyCastleProvider.PROVIDER_NAME);
                            linkedHashSet.add(findNextCert);
                            arrayList.add(new Boolean(z));
                        } catch (GeneralSecurityException unused3) {
                        }
                    }
                } catch (IOException e2) {
                    throw new IllegalStateException(e2.toString());
                }
            } else {
                linkedHashSet.add(x509Certificate2);
                arrayList.add(new Boolean(false));
            }
        }
        return new Object[]{CertificateFactory.getInstance(KeyUtils.X509_CERITIFATE_FACTORY, BouncyCastleProvider.PROVIDER_NAME).generateCertPath(new ArrayList(linkedHashSet)), arrayList};
    }

    private static List findCerts(List list, X509CertSelector x509CertSelector) throws CertStoreException {
        ArrayList arrayList = new ArrayList();
        Iterator it2 = list.iterator();
        while (it2.hasNext()) {
            arrayList.addAll(((CertStore) it2.next()).getCertificates(x509CertSelector));
        }
        return arrayList;
    }

    private static X509Certificate findNextCert(List list, X509CertSelector x509CertSelector, Set set) throws CertStoreException {
        boolean z;
        Iterator it2 = findCerts(list, x509CertSelector).iterator();
        X509Certificate x509Certificate = null;
        while (true) {
            if (!it2.hasNext()) {
                z = false;
                break;
            }
            x509Certificate = (X509Certificate) it2.next();
            if (!set.contains(x509Certificate)) {
                z = true;
                break;
            }
        }
        if (z) {
            return x509Certificate;
        }
        return null;
    }

    public static Set getEmailAddresses(X509Certificate x509Certificate) throws IOException, CertificateEncodingException {
        HashSet hashSet = new HashSet();
        for (RDN rdn : getTBSCert(x509Certificate).getSubject().getRDNs(PKCSObjectIdentifiers.pkcs_9_at_emailAddress)) {
            AttributeTypeAndValue[] typesAndValues = rdn.getTypesAndValues();
            for (int i = 0; i != typesAndValues.length; i++) {
                if (typesAndValues[i].getType().equals((ASN1Primitive) PKCSObjectIdentifiers.pkcs_9_at_emailAddress)) {
                    hashSet.add(((ASN1String) typesAndValues[i].getValue()).getString().toLowerCase());
                }
            }
        }
        byte[] extensionValue = x509Certificate.getExtensionValue(SUBJECT_ALTERNATIVE_NAME);
        if (extensionValue != null) {
            ASN1Sequence aSN1Sequence = ASN1Sequence.getInstance(getObject(extensionValue));
            for (int i2 = 0; i2 < aSN1Sequence.size(); i2++) {
                ASN1TaggedObject aSN1TaggedObject = (ASN1TaggedObject) aSN1Sequence.getObjectAt(i2);
                if (aSN1TaggedObject.getTagNo() == 1) {
                    hashSet.add(DERIA5String.getInstance(aSN1TaggedObject, false).getString().toLowerCase());
                }
            }
        }
        return hashSet;
    }

    private static ASN1Primitive getObject(byte[] bArr) throws IOException {
        return ASN1Primitive.fromByteArray(ASN1OctetString.getInstance(new ASN1InputStream(bArr).readObject()).getOctets());
    }

    public static Date getSignatureTime(SignerInformation signerInformation) {
        Attribute attribute;
        AttributeTable signedAttributes = signerInformation.getSignedAttributes();
        if (signedAttributes == null || (attribute = signedAttributes.get(CMSAttributes.signingTime)) == null) {
            return null;
        }
        return Time.getInstance(attribute.getAttrValues().getObjectAt(0).toASN1Primitive()).getDate();
    }

    private static TBSCertificate getTBSCert(X509Certificate x509Certificate) throws CertificateEncodingException {
        return TBSCertificate.getInstance(x509Certificate.getTBSCertificate());
    }

    /* JADX WARN: Can't wrap try/catch for region: R(17:1|(1:3)(2:55|(1:57)(14:58|5|(1:9)|10|(1:12)|13|(1:19)|20|21|22|(2:24|(1:28))|30|31|(2:33|34)(3:36|(2:37|(2:39|(2:42|43)(1:41))(2:48|49))|(2:45|46)(1:47))))|4|5|(2:7|9)|10|(0)|13|(3:15|17|19)|20|21|22|(0)|30|31|(0)(0)|(1:(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00c1, code lost:
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00c2, code lost:
        r11.add(new org.bouncycastle.i18n.ErrorBundle(org.bouncycastle.mail.smime.validator.SignedMailValidator.RESOURCE_NAME, "SignedMailValidator.extKeyUsageError", new java.lang.Object[]{r0.getMessage(), r0, r0.getClass().getName()}));
     */
    /* JADX WARN: Removed duplicated region for block: B:17:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x009e A[Catch: Exception -> 0x00c1, TryCatch #1 {Exception -> 0x00c1, blocks: (B:26:0x0096, B:28:0x009e, B:30:0x00ae, B:32:0x00b6), top: B:54:0x0096 }] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00ea A[Catch: Exception -> 0x0132, TryCatch #0 {Exception -> 0x0132, blocks: (B:36:0x00e0, B:38:0x00ea, B:40:0x00f6, B:42:0x00fb, B:48:0x0111, B:45:0x010b), top: B:53:0x00e0 }] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00f5  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void checkSignerCert(java.security.cert.X509Certificate r10, java.util.List r11, java.util.List r12) {
        /*
            Method dump skipped, instructions count: 338
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.mail.smime.validator.SignedMailValidator.checkSignerCert(java.security.cert.X509Certificate, java.util.List, java.util.List):void");
    }

    public CertStore getCertsAndCRLs() {
        return this.certs;
    }

    public SignerInformationStore getSignerInformationStore() {
        return this.signers;
    }

    public ValidationResult getValidationResult(SignerInformation signerInformation) throws SignedMailValidatorException {
        if (!this.signers.getSigners(signerInformation.getSID()).isEmpty()) {
            return (ValidationResult) this.results.get(signerInformation);
        }
        throw new SignedMailValidatorException(new ErrorBundle(RESOURCE_NAME, "SignedMailValidator.wrongSigner"));
    }

    /*  JADX ERROR: JadxOverflowException in pass: LoopRegionVisitor
        jadx.core.utils.exceptions.JadxOverflowException: LoopRegionVisitor.assignOnlyInLoop endless recursion
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:56)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:30)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:18)
        */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00eb  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0172 A[Catch: CertPathReviewerException -> 0x01fe, GeneralSecurityException -> 0x0223, TryCatch #10 {GeneralSecurityException -> 0x0223, CertPathReviewerException -> 0x01fe, blocks: (B:38:0x0142, B:39:0x0160, B:40:0x0169, B:42:0x0172, B:43:0x017c), top: B:75:0x0142 }] */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0101 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void validateSignatures(java.security.cert.PKIXParameters r24) {
        /*
            Method dump skipped, instructions count: 649
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.mail.smime.validator.SignedMailValidator.validateSignatures(java.security.cert.PKIXParameters):void");
    }
}
