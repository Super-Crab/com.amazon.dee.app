package org.bouncycastle.x509;

import com.amazon.whispercloak.KeyUtils;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.security.PublicKey;
import java.security.cert.CertPath;
import java.security.cert.CertPathValidatorException;
import java.security.cert.CertificateFactory;
import java.security.cert.PKIXCertPathChecker;
import java.security.cert.PKIXParameters;
import java.security.cert.PolicyNode;
import java.security.cert.TrustAnchor;
import java.security.cert.X509CRL;
import java.security.cert.X509CertSelector;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import javax.security.auth.x500.X500Principal;
import org.apache.logging.log4j.util.Chars;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERIA5String;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.x509.AccessDescription;
import org.bouncycastle.asn1.x509.AuthorityInformationAccess;
import org.bouncycastle.asn1.x509.AuthorityKeyIdentifier;
import org.bouncycastle.asn1.x509.BasicConstraints;
import org.bouncycastle.asn1.x509.CRLDistPoint;
import org.bouncycastle.asn1.x509.DistributionPoint;
import org.bouncycastle.asn1.x509.DistributionPointName;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.asn1.x509.GeneralNames;
import org.bouncycastle.asn1.x509.GeneralSubtree;
import org.bouncycastle.asn1.x509.NameConstraints;
import org.bouncycastle.asn1.x509.qualified.ETSIQCObjectIdentifiers;
import org.bouncycastle.asn1.x509.qualified.MonetaryValue;
import org.bouncycastle.asn1.x509.qualified.QCStatement;
import org.bouncycastle.asn1.x509.qualified.RFC3739QCObjectIdentifiers;
import org.bouncycastle.i18n.ErrorBundle;
import org.bouncycastle.i18n.filter.TrustedInput;
import org.bouncycastle.i18n.filter.UntrustedInput;
import org.bouncycastle.jce.provider.AnnotatedException;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.provider.PKIXNameConstraintValidator;
import org.bouncycastle.jce.provider.PKIXNameConstraintValidatorException;
import org.bouncycastle.util.Integers;
/* loaded from: classes5.dex */
public class PKIXCertPathReviewer extends CertPathValidatorUtilities {
    private static final String RESOURCE_NAME = "org.bouncycastle.x509.CertPathReviewerMessages";
    protected CertPath certPath;
    protected List certs;
    protected List[] errors;
    private boolean initialized;
    protected int n;
    protected List[] notifications;
    protected PKIXParameters pkixParams;
    protected PolicyNode policyTree;
    protected PublicKey subjectPublicKey;
    protected TrustAnchor trustAnchor;
    protected Date validDate;
    private static final String QC_STATEMENT = Extension.qCStatements.getId();
    private static final String CRL_DIST_POINTS = Extension.cRLDistributionPoints.getId();
    private static final String AUTH_INFO_ACCESS = Extension.authorityInfoAccess.getId();

    public PKIXCertPathReviewer() {
    }

    public PKIXCertPathReviewer(CertPath certPath, PKIXParameters pKIXParameters) throws CertPathReviewerException {
        init(certPath, pKIXParameters);
    }

    private String IPtoString(byte[] bArr) {
        try {
            return InetAddress.getByAddress(bArr).getHostAddress();
        } catch (Exception unused) {
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i != bArr.length; i++) {
                stringBuffer.append(Integer.toHexString(bArr[i] & 255));
                stringBuffer.append(Chars.SPACE);
            }
            return stringBuffer.toString();
        }
    }

    private void checkCriticalExtensions() {
        List<PKIXCertPathChecker> certPathCheckers = this.pkixParams.getCertPathCheckers();
        for (PKIXCertPathChecker pKIXCertPathChecker : certPathCheckers) {
            try {
                try {
                    pKIXCertPathChecker.init(false);
                } catch (CertPathValidatorException e) {
                    throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.certPathCheckerError", new Object[]{e.getMessage(), e, e.getClass().getName()}), e);
                }
            } catch (CertPathReviewerException e2) {
                addError(e2.getErrorMessage(), e2.getIndex());
                return;
            }
        }
        for (int size = this.certs.size() - 1; size >= 0; size--) {
            X509Certificate x509Certificate = (X509Certificate) this.certs.get(size);
            Set<String> criticalExtensionOIDs = x509Certificate.getCriticalExtensionOIDs();
            if (criticalExtensionOIDs != null && !criticalExtensionOIDs.isEmpty()) {
                criticalExtensionOIDs.remove(CertPathValidatorUtilities.KEY_USAGE);
                criticalExtensionOIDs.remove(CertPathValidatorUtilities.CERTIFICATE_POLICIES);
                criticalExtensionOIDs.remove(CertPathValidatorUtilities.POLICY_MAPPINGS);
                criticalExtensionOIDs.remove(CertPathValidatorUtilities.INHIBIT_ANY_POLICY);
                criticalExtensionOIDs.remove(CertPathValidatorUtilities.ISSUING_DISTRIBUTION_POINT);
                criticalExtensionOIDs.remove(CertPathValidatorUtilities.DELTA_CRL_INDICATOR);
                criticalExtensionOIDs.remove(CertPathValidatorUtilities.POLICY_CONSTRAINTS);
                criticalExtensionOIDs.remove(CertPathValidatorUtilities.BASIC_CONSTRAINTS);
                criticalExtensionOIDs.remove(CertPathValidatorUtilities.SUBJECT_ALTERNATIVE_NAME);
                criticalExtensionOIDs.remove(CertPathValidatorUtilities.NAME_CONSTRAINTS);
                if (criticalExtensionOIDs.contains(QC_STATEMENT) && processQcStatements(x509Certificate, size)) {
                    criticalExtensionOIDs.remove(QC_STATEMENT);
                }
                for (PKIXCertPathChecker pKIXCertPathChecker2 : certPathCheckers) {
                    try {
                        pKIXCertPathChecker2.check(x509Certificate, criticalExtensionOIDs);
                    } catch (CertPathValidatorException e3) {
                        throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.criticalExtensionError", new Object[]{e3.getMessage(), e3, e3.getClass().getName()}), e3.getCause(), this.certPath, size);
                    }
                }
                if (!criticalExtensionOIDs.isEmpty()) {
                    Iterator<String> it2 = criticalExtensionOIDs.iterator();
                    while (it2.hasNext()) {
                        addError(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.unknownCriticalExt", new Object[]{new ASN1ObjectIdentifier(it2.next())}), size);
                    }
                }
            }
        }
    }

    private void checkNameConstraints() {
        PKIXNameConstraintValidator pKIXNameConstraintValidator = new PKIXNameConstraintValidator();
        try {
            for (int size = this.certs.size() - 1; size > 0; size--) {
                X509Certificate x509Certificate = (X509Certificate) this.certs.get(size);
                if (!CertPathValidatorUtilities.isSelfIssued(x509Certificate)) {
                    X500Principal subjectPrincipal = CertPathValidatorUtilities.getSubjectPrincipal(x509Certificate);
                    try {
                        ASN1Sequence aSN1Sequence = (ASN1Sequence) new ASN1InputStream(new ByteArrayInputStream(subjectPrincipal.getEncoded())).readObject();
                        try {
                            pKIXNameConstraintValidator.checkPermittedDN(aSN1Sequence);
                            try {
                                pKIXNameConstraintValidator.checkExcludedDN(aSN1Sequence);
                                try {
                                    ASN1Sequence aSN1Sequence2 = (ASN1Sequence) CertPathValidatorUtilities.getExtensionValue(x509Certificate, CertPathValidatorUtilities.SUBJECT_ALTERNATIVE_NAME);
                                    if (aSN1Sequence2 != null) {
                                        for (int i = 0; i < aSN1Sequence2.size(); i++) {
                                            GeneralName generalName = GeneralName.getInstance(aSN1Sequence2.getObjectAt(i));
                                            try {
                                                pKIXNameConstraintValidator.checkPermitted(generalName);
                                                pKIXNameConstraintValidator.checkExcluded(generalName);
                                            } catch (PKIXNameConstraintValidatorException e) {
                                                throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.notPermittedEmail", new Object[]{new UntrustedInput(generalName)}), e, this.certPath, size);
                                            }
                                        }
                                    }
                                } catch (AnnotatedException e2) {
                                    throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.subjAltNameExtError"), e2, this.certPath, size);
                                }
                            } catch (PKIXNameConstraintValidatorException e3) {
                                throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.excludedDN", new Object[]{new UntrustedInput(subjectPrincipal.getName())}), e3, this.certPath, size);
                            }
                        } catch (PKIXNameConstraintValidatorException e4) {
                            throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.notPermittedDN", new Object[]{new UntrustedInput(subjectPrincipal.getName())}), e4, this.certPath, size);
                        }
                    } catch (IOException e5) {
                        throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.ncSubjectNameError", new Object[]{new UntrustedInput(subjectPrincipal)}), e5, this.certPath, size);
                    }
                }
                try {
                    ASN1Sequence aSN1Sequence3 = (ASN1Sequence) CertPathValidatorUtilities.getExtensionValue(x509Certificate, CertPathValidatorUtilities.NAME_CONSTRAINTS);
                    if (aSN1Sequence3 != null) {
                        NameConstraints nameConstraints = NameConstraints.getInstance(aSN1Sequence3);
                        GeneralSubtree[] permittedSubtrees = nameConstraints.getPermittedSubtrees();
                        if (permittedSubtrees != null) {
                            pKIXNameConstraintValidator.intersectPermittedSubtree(permittedSubtrees);
                        }
                        GeneralSubtree[] excludedSubtrees = nameConstraints.getExcludedSubtrees();
                        if (excludedSubtrees != null) {
                            for (int i2 = 0; i2 != excludedSubtrees.length; i2++) {
                                pKIXNameConstraintValidator.addExcludedSubtree(excludedSubtrees[i2]);
                            }
                        }
                    }
                } catch (AnnotatedException e6) {
                    throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.ncExtError"), e6, this.certPath, size);
                }
            }
        } catch (CertPathReviewerException e7) {
            addError(e7.getErrorMessage(), e7.getIndex());
        }
    }

    private void checkPathLength() {
        BasicConstraints basicConstraints;
        BigInteger pathLenConstraint;
        int intValue;
        int i = this.n;
        int i2 = i;
        int i3 = 0;
        for (int size = this.certs.size() - 1; size > 0; size--) {
            X509Certificate x509Certificate = (X509Certificate) this.certs.get(size);
            if (!CertPathValidatorUtilities.isSelfIssued(x509Certificate)) {
                if (i2 <= 0) {
                    addError(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.pathLengthExtended"));
                }
                i2--;
                i3++;
            }
            try {
                basicConstraints = BasicConstraints.getInstance(CertPathValidatorUtilities.getExtensionValue(x509Certificate, CertPathValidatorUtilities.BASIC_CONSTRAINTS));
            } catch (AnnotatedException unused) {
                addError(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.processLengthConstError"), size);
                basicConstraints = null;
            }
            if (basicConstraints != null && (pathLenConstraint = basicConstraints.getPathLenConstraint()) != null && (intValue = pathLenConstraint.intValue()) < i2) {
                i2 = intValue;
            }
        }
        addNotification(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.totalPathLength", new Object[]{Integers.valueOf(i3)}));
    }

    /* JADX WARN: Code restructure failed: missing block: B:54:0x011d, code lost:
        if (r7 >= r34.n) goto L69;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x0123, code lost:
        if (org.bouncycastle.x509.CertPathValidatorUtilities.isSelfIssued(r4) == false) goto L69;
     */
    /* JADX WARN: Removed duplicated region for block: B:109:0x0236 A[Catch: CertPathReviewerException -> 0x05e8, TryCatch #2 {CertPathReviewerException -> 0x05e8, blocks: (B:18:0x006a, B:22:0x007c, B:23:0x0087, B:27:0x0097, B:28:0x00a2, B:30:0x00a8, B:32:0x00c9, B:33:0x00d1, B:35:0x00d7, B:37:0x00dc, B:38:0x00e8, B:42:0x00f4, B:45:0x00fb, B:46:0x0104, B:48:0x010a, B:50:0x0114, B:53:0x011b, B:55:0x011f, B:95:0x0206, B:97:0x020a, B:98:0x020f, B:100:0x0215, B:102:0x0221, B:105:0x0228, B:106:0x022b, B:107:0x0230, B:109:0x0236, B:110:0x023f, B:112:0x0245, B:120:0x0267, B:121:0x0273, B:122:0x0274, B:124:0x0278, B:126:0x0280, B:127:0x0284, B:129:0x028a, B:132:0x02aa, B:134:0x02b4, B:135:0x02b7, B:136:0x02c3, B:137:0x02c4, B:138:0x02d0, B:140:0x02d3, B:141:0x02e0, B:143:0x02e6, B:145:0x030a, B:147:0x0322, B:146:0x0319, B:148:0x0327, B:149:0x032d, B:151:0x0333, B:153:0x033b, B:164:0x0365, B:157:0x0343, B:158:0x034f, B:160:0x0351, B:161:0x0360, B:168:0x0373, B:179:0x0390, B:181:0x039a, B:182:0x039e, B:184:0x03a4, B:189:0x03b4, B:192:0x03c1, B:195:0x03ce, B:197:0x03d8, B:210:0x0419, B:202:0x03e3, B:203:0x03f1, B:204:0x03f2, B:205:0x0400, B:207:0x0402, B:208:0x0410, B:60:0x012e, B:61:0x0132, B:63:0x0138, B:65:0x014e, B:67:0x0158, B:68:0x015d, B:70:0x0163, B:71:0x0171, B:73:0x0177, B:75:0x0183, B:79:0x0190, B:80:0x0196, B:82:0x019c, B:87:0x01b5, B:76:0x0186, B:78:0x018a, B:91:0x01ee, B:93:0x01f9, B:94:0x0205, B:212:0x0425, B:213:0x0432, B:214:0x0433, B:218:0x0442, B:220:0x044c, B:221:0x0451, B:223:0x0457, B:226:0x0465, B:233:0x047a, B:313:0x05ce, B:314:0x05da, B:236:0x0486, B:237:0x0492, B:238:0x0493, B:240:0x0499, B:242:0x04a1, B:244:0x04a7, B:245:0x04ad, B:247:0x04b0, B:248:0x04b3, B:250:0x04b9, B:252:0x04c9, B:253:0x04cd, B:255:0x04d3, B:256:0x04db, B:257:0x04de, B:258:0x04e1, B:259:0x04e5, B:261:0x04eb, B:262:0x04f9, B:264:0x04ff, B:265:0x0504, B:267:0x050a, B:269:0x0516, B:270:0x051a, B:271:0x051d, B:272:0x0522, B:273:0x052e, B:275:0x0533, B:276:0x0539, B:278:0x053c, B:279:0x053f, B:281:0x0545, B:283:0x0555, B:284:0x0559, B:286:0x055f, B:288:0x056f, B:289:0x0573, B:290:0x0576, B:291:0x0579, B:292:0x057f, B:294:0x0585, B:296:0x0597, B:299:0x05a0, B:301:0x05a6, B:302:0x05aa, B:304:0x05b0, B:306:0x05bc, B:307:0x05c0, B:308:0x05c3, B:315:0x05db, B:316:0x05e7), top: B:322:0x006a, inners: #0, #3, #4, #5, #7, #8, #9, #10 }] */
    /* JADX WARN: Removed duplicated region for block: B:97:0x020a A[Catch: CertPathReviewerException -> 0x05e8, TryCatch #2 {CertPathReviewerException -> 0x05e8, blocks: (B:18:0x006a, B:22:0x007c, B:23:0x0087, B:27:0x0097, B:28:0x00a2, B:30:0x00a8, B:32:0x00c9, B:33:0x00d1, B:35:0x00d7, B:37:0x00dc, B:38:0x00e8, B:42:0x00f4, B:45:0x00fb, B:46:0x0104, B:48:0x010a, B:50:0x0114, B:53:0x011b, B:55:0x011f, B:95:0x0206, B:97:0x020a, B:98:0x020f, B:100:0x0215, B:102:0x0221, B:105:0x0228, B:106:0x022b, B:107:0x0230, B:109:0x0236, B:110:0x023f, B:112:0x0245, B:120:0x0267, B:121:0x0273, B:122:0x0274, B:124:0x0278, B:126:0x0280, B:127:0x0284, B:129:0x028a, B:132:0x02aa, B:134:0x02b4, B:135:0x02b7, B:136:0x02c3, B:137:0x02c4, B:138:0x02d0, B:140:0x02d3, B:141:0x02e0, B:143:0x02e6, B:145:0x030a, B:147:0x0322, B:146:0x0319, B:148:0x0327, B:149:0x032d, B:151:0x0333, B:153:0x033b, B:164:0x0365, B:157:0x0343, B:158:0x034f, B:160:0x0351, B:161:0x0360, B:168:0x0373, B:179:0x0390, B:181:0x039a, B:182:0x039e, B:184:0x03a4, B:189:0x03b4, B:192:0x03c1, B:195:0x03ce, B:197:0x03d8, B:210:0x0419, B:202:0x03e3, B:203:0x03f1, B:204:0x03f2, B:205:0x0400, B:207:0x0402, B:208:0x0410, B:60:0x012e, B:61:0x0132, B:63:0x0138, B:65:0x014e, B:67:0x0158, B:68:0x015d, B:70:0x0163, B:71:0x0171, B:73:0x0177, B:75:0x0183, B:79:0x0190, B:80:0x0196, B:82:0x019c, B:87:0x01b5, B:76:0x0186, B:78:0x018a, B:91:0x01ee, B:93:0x01f9, B:94:0x0205, B:212:0x0425, B:213:0x0432, B:214:0x0433, B:218:0x0442, B:220:0x044c, B:221:0x0451, B:223:0x0457, B:226:0x0465, B:233:0x047a, B:313:0x05ce, B:314:0x05da, B:236:0x0486, B:237:0x0492, B:238:0x0493, B:240:0x0499, B:242:0x04a1, B:244:0x04a7, B:245:0x04ad, B:247:0x04b0, B:248:0x04b3, B:250:0x04b9, B:252:0x04c9, B:253:0x04cd, B:255:0x04d3, B:256:0x04db, B:257:0x04de, B:258:0x04e1, B:259:0x04e5, B:261:0x04eb, B:262:0x04f9, B:264:0x04ff, B:265:0x0504, B:267:0x050a, B:269:0x0516, B:270:0x051a, B:271:0x051d, B:272:0x0522, B:273:0x052e, B:275:0x0533, B:276:0x0539, B:278:0x053c, B:279:0x053f, B:281:0x0545, B:283:0x0555, B:284:0x0559, B:286:0x055f, B:288:0x056f, B:289:0x0573, B:290:0x0576, B:291:0x0579, B:292:0x057f, B:294:0x0585, B:296:0x0597, B:299:0x05a0, B:301:0x05a6, B:302:0x05aa, B:304:0x05b0, B:306:0x05bc, B:307:0x05c0, B:308:0x05c3, B:315:0x05db, B:316:0x05e7), top: B:322:0x006a, inners: #0, #3, #4, #5, #7, #8, #9, #10 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void checkPolicy() {
        /*
            Method dump skipped, instructions count: 1525
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.x509.PKIXCertPathReviewer.checkPolicy():void");
    }

    /* JADX WARN: Can't wrap try/catch for region: R(15:(2:90|91)|(4:(16:93|94|95|(12:97|98|(2:101|99)|102|103|(2:106|104)|107|108|109|110|111|112)|119|98|(1:99)|102|103|(1:104)|107|108|109|110|111|112)|110|111|112)|122|94|95|(0)|119|98|(1:99)|102|103|(1:104)|107|108|109) */
    /* JADX WARN: Can't wrap try/catch for region: R(15:30|(3:137|138|139)(2:32|(3:130|131|132)(4:34|(2:38|(2:40|41))|129|41))|(2:42|43)|44|(19:90|91|(16:93|94|95|(12:97|98|(2:101|99)|102|103|(2:106|104)|107|108|109|110|111|112)|119|98|(1:99)|102|103|(1:104)|107|108|109|110|111|112)|122|94|95|(0)|119|98|(1:99)|102|103|(1:104)|107|108|109|110|111|112)(1:46)|(1:89)(1:50)|51|(7:53|(2:55|(1:57))(1:87)|58|59|(2:61|(1:63))(1:84)|64|(1:70))(1:88)|71|72|73|74|75|77|78) */
    /* JADX WARN: Can't wrap try/catch for region: R(16:90|91|(4:(16:93|94|95|(12:97|98|(2:101|99)|102|103|(2:106|104)|107|108|109|110|111|112)|119|98|(1:99)|102|103|(1:104)|107|108|109|110|111|112)|110|111|112)|122|94|95|(0)|119|98|(1:99)|102|103|(1:104)|107|108|109) */
    /* JADX WARN: Code restructure failed: missing block: B:113:0x0338, code lost:
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:114:0x0339, code lost:
        r15 = r3;
        r12 = r4;
        r18 = r6;
        r17 = r7;
        r19 = r9;
        r13 = r16;
        r11 = 5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:150:0x03fb, code lost:
        r6 = r18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:151:0x03fd, code lost:
        addError(new org.bouncycastle.i18n.ErrorBundle(org.bouncycastle.x509.PKIXCertPathReviewer.RESOURCE_NAME, "CertPathReviewer.pubKeyError"), r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:97:0x02b8, code lost:
        addError(new org.bouncycastle.i18n.ErrorBundle(org.bouncycastle.x509.PKIXCertPathReviewer.RESOURCE_NAME, "CertPathReviewer.crlAuthInfoAccError"), r7);
     */
    /* JADX WARN: Removed duplicated region for block: B:102:0x02d5 A[LOOP:1: B:100:0x02cf->B:102:0x02d5, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:106:0x02ff A[LOOP:2: B:104:0x02f9->B:106:0x02ff, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:116:0x034e  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x0358  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x0388  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x03e5  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x0291 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00fc  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0143  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0146  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x016b  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x017a  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x02b3 A[Catch: AnnotatedException -> 0x02b8, TRY_LEAVE, TryCatch #13 {AnnotatedException -> 0x02b8, blocks: (B:93:0x02ab, B:95:0x02b3), top: B:176:0x02ab }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void checkSignatures() {
        /*
            Method dump skipped, instructions count: 1053
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.x509.PKIXCertPathReviewer.checkSignatures():void");
    }

    private X509CRL getCRL(String str) throws CertPathReviewerException {
        try {
            URL url = new URL(str);
            if (!url.getProtocol().equals("http") && !url.getProtocol().equals("https")) {
                return null;
            }
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setDoInput(true);
            httpURLConnection.connect();
            if (httpURLConnection.getResponseCode() != 200) {
                throw new Exception(httpURLConnection.getResponseMessage());
            }
            return (X509CRL) CertificateFactory.getInstance(KeyUtils.X509_CERITIFATE_FACTORY, BouncyCastleProvider.PROVIDER_NAME).generateCRL(httpURLConnection.getInputStream());
        } catch (Exception e) {
            throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.loadCrlDistPointError", new Object[]{new UntrustedInput(str), e.getMessage(), e, e.getClass().getName()}));
        }
    }

    private boolean processQcStatements(X509Certificate x509Certificate, int i) {
        ErrorBundle errorBundle;
        try {
            ASN1Sequence aSN1Sequence = (ASN1Sequence) CertPathValidatorUtilities.getExtensionValue(x509Certificate, QC_STATEMENT);
            boolean z = false;
            for (int i2 = 0; i2 < aSN1Sequence.size(); i2++) {
                QCStatement qCStatement = QCStatement.getInstance(aSN1Sequence.getObjectAt(i2));
                if (ETSIQCObjectIdentifiers.id_etsi_qcs_QcCompliance.equals((ASN1Primitive) qCStatement.getStatementId())) {
                    errorBundle = new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.QcEuCompliance");
                } else {
                    if (!RFC3739QCObjectIdentifiers.id_qcs_pkixQCSyntax_v1.equals((ASN1Primitive) qCStatement.getStatementId())) {
                        if (ETSIQCObjectIdentifiers.id_etsi_qcs_QcSSCD.equals((ASN1Primitive) qCStatement.getStatementId())) {
                            errorBundle = new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.QcSSCD");
                        } else if (ETSIQCObjectIdentifiers.id_etsi_qcs_LimiteValue.equals((ASN1Primitive) qCStatement.getStatementId())) {
                            MonetaryValue monetaryValue = MonetaryValue.getInstance(qCStatement.getStatementInfo());
                            monetaryValue.getCurrency();
                            double doubleValue = monetaryValue.getAmount().doubleValue() * Math.pow(10.0d, monetaryValue.getExponent().doubleValue());
                            addNotification(monetaryValue.getCurrency().isAlphabetic() ? new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.QcLimitValueAlpha", new Object[]{monetaryValue.getCurrency().getAlphabetic(), new TrustedInput(new Double(doubleValue)), monetaryValue}) : new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.QcLimitValueNum", new Object[]{Integers.valueOf(monetaryValue.getCurrency().getNumeric()), new TrustedInput(new Double(doubleValue)), monetaryValue}), i);
                        } else {
                            addNotification(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.QcUnknownStatement", new Object[]{qCStatement.getStatementId(), new UntrustedInput(qCStatement)}), i);
                            z = true;
                        }
                    }
                }
                addNotification(errorBundle, i);
            }
            return true ^ z;
        } catch (AnnotatedException unused) {
            addError(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.QcStatementExtError"), i);
            return false;
        }
    }

    protected void addError(ErrorBundle errorBundle) {
        this.errors[0].add(errorBundle);
    }

    protected void addError(ErrorBundle errorBundle, int i) {
        if (i < -1 || i >= this.n) {
            throw new IndexOutOfBoundsException();
        }
        this.errors[i + 1].add(errorBundle);
    }

    protected void addNotification(ErrorBundle errorBundle) {
        this.notifications[0].add(errorBundle);
    }

    protected void addNotification(ErrorBundle errorBundle, int i) {
        if (i < -1 || i >= this.n) {
            throw new IndexOutOfBoundsException();
        }
        this.notifications[i + 1].add(errorBundle);
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x02e9  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x02b6  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x02cc  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void checkCRLs(java.security.cert.PKIXParameters r21, java.security.cert.X509Certificate r22, java.util.Date r23, java.security.cert.X509Certificate r24, java.security.PublicKey r25, java.util.Vector r26, int r27) throws org.bouncycastle.x509.CertPathReviewerException {
        /*
            Method dump skipped, instructions count: 1195
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.x509.PKIXCertPathReviewer.checkCRLs(java.security.cert.PKIXParameters, java.security.cert.X509Certificate, java.util.Date, java.security.cert.X509Certificate, java.security.PublicKey, java.util.Vector, int):void");
    }

    protected void checkRevocation(PKIXParameters pKIXParameters, X509Certificate x509Certificate, Date date, X509Certificate x509Certificate2, PublicKey publicKey, Vector vector, Vector vector2, int i) throws CertPathReviewerException {
        checkCRLs(pKIXParameters, x509Certificate, date, x509Certificate2, publicKey, vector, i);
    }

    protected void doChecks() {
        if (this.initialized) {
            if (this.notifications != null) {
                return;
            }
            int i = this.n;
            this.notifications = new List[i + 1];
            this.errors = new List[i + 1];
            int i2 = 0;
            while (true) {
                List[] listArr = this.notifications;
                if (i2 >= listArr.length) {
                    checkSignatures();
                    checkNameConstraints();
                    checkPathLength();
                    checkPolicy();
                    checkCriticalExtensions();
                    return;
                }
                listArr[i2] = new ArrayList();
                this.errors[i2] = new ArrayList();
                i2++;
            }
        } else {
            throw new IllegalStateException("Object not initialized. Call init() first.");
        }
    }

    protected Vector getCRLDistUrls(CRLDistPoint cRLDistPoint) {
        Vector vector = new Vector();
        if (cRLDistPoint != null) {
            for (DistributionPoint distributionPoint : cRLDistPoint.getDistributionPoints()) {
                DistributionPointName distributionPoint2 = distributionPoint.getDistributionPoint();
                if (distributionPoint2.getType() == 0) {
                    GeneralName[] names = GeneralNames.getInstance(distributionPoint2.getName()).getNames();
                    for (int i = 0; i < names.length; i++) {
                        if (names[i].getTagNo() == 6) {
                            vector.add(((DERIA5String) names[i].getName()).getString());
                        }
                    }
                }
            }
        }
        return vector;
    }

    public CertPath getCertPath() {
        return this.certPath;
    }

    public int getCertPathSize() {
        return this.n;
    }

    public List getErrors(int i) {
        doChecks();
        return this.errors[i + 1];
    }

    public List[] getErrors() {
        doChecks();
        return this.errors;
    }

    public List getNotifications(int i) {
        doChecks();
        return this.notifications[i + 1];
    }

    public List[] getNotifications() {
        doChecks();
        return this.notifications;
    }

    protected Vector getOCSPUrls(AuthorityInformationAccess authorityInformationAccess) {
        Vector vector = new Vector();
        if (authorityInformationAccess != null) {
            AccessDescription[] accessDescriptions = authorityInformationAccess.getAccessDescriptions();
            for (int i = 0; i < accessDescriptions.length; i++) {
                if (accessDescriptions[i].getAccessMethod().equals((ASN1Primitive) AccessDescription.id_ad_ocsp)) {
                    GeneralName accessLocation = accessDescriptions[i].getAccessLocation();
                    if (accessLocation.getTagNo() == 6) {
                        vector.add(((DERIA5String) accessLocation.getName()).getString());
                    }
                }
            }
        }
        return vector;
    }

    public PolicyNode getPolicyTree() {
        doChecks();
        return this.policyTree;
    }

    public PublicKey getSubjectPublicKey() {
        doChecks();
        return this.subjectPublicKey;
    }

    public TrustAnchor getTrustAnchor() {
        doChecks();
        return this.trustAnchor;
    }

    protected Collection getTrustAnchors(X509Certificate x509Certificate, Set set) throws CertPathReviewerException {
        ArrayList arrayList = new ArrayList();
        Iterator it2 = set.iterator();
        X509CertSelector x509CertSelector = new X509CertSelector();
        try {
            x509CertSelector.setSubject(CertPathValidatorUtilities.getEncodedIssuerPrincipal(x509Certificate).getEncoded());
            byte[] extensionValue = x509Certificate.getExtensionValue(Extension.authorityKeyIdentifier.getId());
            if (extensionValue != null) {
                AuthorityKeyIdentifier authorityKeyIdentifier = AuthorityKeyIdentifier.getInstance(ASN1Primitive.fromByteArray(((ASN1OctetString) ASN1Primitive.fromByteArray(extensionValue)).getOctets()));
                x509CertSelector.setSerialNumber(authorityKeyIdentifier.getAuthorityCertSerialNumber());
                byte[] keyIdentifier = authorityKeyIdentifier.getKeyIdentifier();
                if (keyIdentifier != null) {
                    x509CertSelector.setSubjectKeyIdentifier(new DEROctetString(keyIdentifier).getEncoded());
                }
            }
            while (it2.hasNext()) {
                TrustAnchor trustAnchor = (TrustAnchor) it2.next();
                if (trustAnchor.getTrustedCert() != null) {
                    if (x509CertSelector.match(trustAnchor.getTrustedCert())) {
                        arrayList.add(trustAnchor);
                    }
                } else if (trustAnchor.getCAName() != null && trustAnchor.getCAPublicKey() != null && CertPathValidatorUtilities.getEncodedIssuerPrincipal(x509Certificate).equals(new X500Principal(trustAnchor.getCAName()))) {
                    arrayList.add(trustAnchor);
                }
            }
            return arrayList;
        } catch (IOException unused) {
            throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.trustAnchorIssuerError"));
        }
    }

    public void init(CertPath certPath, PKIXParameters pKIXParameters) throws CertPathReviewerException {
        if (!this.initialized) {
            this.initialized = true;
            if (certPath == null) {
                throw new NullPointerException("certPath was null");
            }
            this.certPath = certPath;
            this.certs = certPath.getCertificates();
            this.n = this.certs.size();
            if (this.certs.isEmpty()) {
                throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.emptyCertPath"));
            }
            this.pkixParams = (PKIXParameters) pKIXParameters.clone();
            this.validDate = CertPathValidatorUtilities.getValidDate(this.pkixParams);
            this.notifications = null;
            this.errors = null;
            this.trustAnchor = null;
            this.subjectPublicKey = null;
            this.policyTree = null;
            return;
        }
        throw new IllegalStateException("object is already initialized!");
    }

    public boolean isValidCertPath() {
        doChecks();
        int i = 0;
        while (true) {
            List[] listArr = this.errors;
            if (i < listArr.length) {
                if (!listArr[i].isEmpty()) {
                    return false;
                }
                i++;
            } else {
                return true;
            }
        }
    }
}
