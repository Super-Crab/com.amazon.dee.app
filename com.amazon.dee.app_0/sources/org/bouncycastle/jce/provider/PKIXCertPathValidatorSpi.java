package org.bouncycastle.jce.provider;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.security.InvalidAlgorithmParameterException;
import java.security.PublicKey;
import java.security.cert.CertPath;
import java.security.cert.CertPathParameters;
import java.security.cert.CertPathValidatorException;
import java.security.cert.CertPathValidatorResult;
import java.security.cert.CertPathValidatorSpi;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.PKIXCertPathChecker;
import java.security.cert.PKIXCertPathValidatorResult;
import java.security.cert.PKIXParameters;
import java.security.cert.TrustAnchor;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.TBSCertificate;
import org.bouncycastle.jcajce.PKIXExtendedBuilderParameters;
import org.bouncycastle.jcajce.PKIXExtendedParameters;
import org.bouncycastle.jcajce.interfaces.BCX509Certificate;
import org.bouncycastle.jcajce.util.BCJcaJceHelper;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.jce.exception.ExtCertPathValidatorException;
import org.bouncycastle.x509.ExtendedPKIXParameters;
/* loaded from: classes4.dex */
public class PKIXCertPathValidatorSpi extends CertPathValidatorSpi {
    private final JcaJceHelper helper;
    private final boolean isForCRLCheck;

    public PKIXCertPathValidatorSpi() {
        this(false);
    }

    public PKIXCertPathValidatorSpi(boolean z) {
        this.helper = new BCJcaJceHelper();
        this.isForCRLCheck = z;
    }

    static void checkCertificate(X509Certificate x509Certificate) throws AnnotatedException {
        if (x509Certificate instanceof BCX509Certificate) {
            RuntimeException runtimeException = null;
            try {
                if (((BCX509Certificate) x509Certificate).getTBSCertificateNative() != null) {
                    return;
                }
            } catch (RuntimeException e) {
                runtimeException = e;
            }
            throw new AnnotatedException("unable to process TBSCertificate", runtimeException);
        }
        try {
            TBSCertificate.getInstance(x509Certificate.getTBSCertificate());
        } catch (IllegalArgumentException e2) {
            throw new AnnotatedException(e2.getMessage());
        } catch (CertificateEncodingException e3) {
            throw new AnnotatedException("unable to process TBSCertificate", e3);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v25 */
    /* JADX WARN: Type inference failed for: r0v27 */
    /* JADX WARN: Type inference failed for: r0v30 */
    /* JADX WARN: Type inference failed for: r0v32, types: [int] */
    /* JADX WARN: Type inference failed for: r0v36, types: [boolean] */
    /* JADX WARN: Type inference failed for: r0v37 */
    /* JADX WARN: Type inference failed for: r13v3, types: [java.security.cert.X509Certificate, org.bouncycastle.jce.provider.PKIXNameConstraintValidator] */
    /* JADX WARN: Type inference failed for: r22v5, types: [boolean, java.security.cert.TrustAnchor] */
    @Override // java.security.cert.CertPathValidatorSpi
    public CertPathValidatorResult engineValidate(CertPath certPath, CertPathParameters certPathParameters) throws CertPathValidatorException, InvalidAlgorithmParameterException {
        PKIXExtendedParameters pKIXExtendedParameters;
        List<? extends Certificate> list;
        X500Name ca;
        PublicKey cAPublicKey;
        HashSet hashSet;
        int i;
        List list2;
        int i2;
        int i3;
        X509Certificate x509Certificate;
        int i4;
        HashSet hashSet2;
        ?? equals;
        if (certPathParameters instanceof PKIXParameters) {
            PKIXExtendedParameters.Builder builder = new PKIXExtendedParameters.Builder((PKIXParameters) certPathParameters);
            if (certPathParameters instanceof ExtendedPKIXParameters) {
                ExtendedPKIXParameters extendedPKIXParameters = (ExtendedPKIXParameters) certPathParameters;
                builder.setUseDeltasEnabled(extendedPKIXParameters.isUseDeltasEnabled());
                builder.setValidityModel(extendedPKIXParameters.getValidityModel());
            }
            pKIXExtendedParameters = builder.build();
        } else if (certPathParameters instanceof PKIXExtendedBuilderParameters) {
            pKIXExtendedParameters = ((PKIXExtendedBuilderParameters) certPathParameters).getBaseParameters();
        } else if (!(certPathParameters instanceof PKIXExtendedParameters)) {
            throw new InvalidAlgorithmParameterException(GeneratedOutlineSupport1.outline40(PKIXParameters.class, GeneratedOutlineSupport1.outline107("Parameters must be a "), " instance."));
        } else {
            pKIXExtendedParameters = (PKIXExtendedParameters) certPathParameters;
        }
        if (pKIXExtendedParameters.getTrustAnchors() != null) {
            List<? extends Certificate> certificates = certPath.getCertificates();
            int size = certificates.size();
            if (certificates.isEmpty()) {
                throw new CertPathValidatorException("Certification path is empty.", null, certPath, -1);
            }
            Set initialPolicies = pKIXExtendedParameters.getInitialPolicies();
            try {
                TrustAnchor findTrustAnchor = CertPathValidatorUtilities.findTrustAnchor((X509Certificate) certificates.get(certificates.size() - 1), pKIXExtendedParameters.getTrustAnchors(), pKIXExtendedParameters.getSigProvider());
                if (findTrustAnchor == null) {
                    list = certificates;
                    try {
                        throw new CertPathValidatorException("Trust anchor for certification path not found.", null, certPath, -1);
                    } catch (AnnotatedException e) {
                        e = e;
                        throw new CertPathValidatorException(e.getMessage(), e.getUnderlyingException(), certPath, list.size() - 1);
                    }
                }
                checkCertificate(findTrustAnchor.getTrustedCert());
                PKIXExtendedParameters build = new PKIXExtendedParameters.Builder(pKIXExtendedParameters).setTrustAnchor(findTrustAnchor).build();
                int i5 = size + 1;
                ArrayList[] arrayListArr = new ArrayList[i5];
                for (int i6 = 0; i6 < arrayListArr.length; i6++) {
                    arrayListArr[i6] = new ArrayList();
                }
                HashSet hashSet3 = new HashSet();
                hashSet3.add(RFC3280CertPathUtilities.ANY_POLICY);
                PKIXPolicyNode pKIXPolicyNode = new PKIXPolicyNode(new ArrayList(), 0, hashSet3, null, new HashSet(), RFC3280CertPathUtilities.ANY_POLICY, false);
                arrayListArr[0].add(pKIXPolicyNode);
                PKIXNameConstraintValidator pKIXNameConstraintValidator = new PKIXNameConstraintValidator();
                HashSet hashSet4 = new HashSet();
                int i7 = build.isExplicitPolicyRequired() ? 0 : i5;
                int i8 = build.isAnyPolicyInhibited() ? 0 : i5;
                if (build.isPolicyMappingInhibited()) {
                    i5 = 0;
                }
                X509Certificate trustedCert = findTrustAnchor.getTrustedCert();
                try {
                    if (trustedCert != null) {
                        ca = PrincipalUtils.getSubjectPrincipal(trustedCert);
                        cAPublicKey = trustedCert.getPublicKey();
                    } else {
                        ca = PrincipalUtils.getCA(findTrustAnchor);
                        cAPublicKey = findTrustAnchor.getCAPublicKey();
                    }
                    try {
                        AlgorithmIdentifier algorithmIdentifier = CertPathValidatorUtilities.getAlgorithmIdentifier(cAPublicKey);
                        algorithmIdentifier.getAlgorithm();
                        algorithmIdentifier.getParameters();
                        if (build.getTargetConstraints() != null && !build.getTargetConstraints().match((Certificate) ((X509Certificate) certificates.get(0)))) {
                            throw new ExtCertPathValidatorException("Target certificate in certification path does not match targetConstraints.", null, certPath, 0);
                        }
                        List<PKIXCertPathChecker> certPathCheckers = build.getCertPathCheckers();
                        for (PKIXCertPathChecker pKIXCertPathChecker : certPathCheckers) {
                            pKIXCertPathChecker.init(false);
                            i5 = i5;
                        }
                        int i9 = i5;
                        ProvCrlRevocationChecker provCrlRevocationChecker = build.isRevocationEnabled() ? new ProvCrlRevocationChecker(this.helper) : null;
                        int i10 = size;
                        X509Certificate x509Certificate2 = trustedCert;
                        PKIXPolicyNode pKIXPolicyNode2 = pKIXPolicyNode;
                        int i11 = i7;
                        X509Certificate x509Certificate3 = null;
                        int i12 = i8;
                        int size2 = certificates.size() - 1;
                        int i13 = i9;
                        while (size2 >= 0) {
                            int i14 = size - size2;
                            Set set = initialPolicies;
                            X509Certificate x509Certificate4 = (X509Certificate) certificates.get(size2);
                            boolean z = size2 == certificates.size() + (-1);
                            try {
                                checkCertificate(x509Certificate4);
                                List<? extends Certificate> list3 = certificates;
                                int i15 = i12;
                                PKIXExtendedParameters pKIXExtendedParameters2 = build;
                                PKIXExtendedParameters pKIXExtendedParameters3 = build;
                                ?? r0 = i10;
                                PublicKey publicKey = cAPublicKey;
                                int i16 = i11;
                                ?? r22 = findTrustAnchor;
                                int i17 = size2;
                                ?? r13 = pKIXNameConstraintValidator;
                                RFC3280CertPathUtilities.processCertA(certPath, pKIXExtendedParameters2, provCrlRevocationChecker, size2, publicKey, r22, ca, x509Certificate2);
                                RFC3280CertPathUtilities.processCertBC(certPath, i17, r13, this.isForCRLCheck);
                                PKIXPolicyNode processCertE = RFC3280CertPathUtilities.processCertE(certPath, i17, RFC3280CertPathUtilities.processCertD(certPath, i17, hashSet4, pKIXPolicyNode2, arrayListArr, i15, this.isForCRLCheck));
                                RFC3280CertPathUtilities.processCertF(certPath, i17, processCertE, i16);
                                if (publicKey == size) {
                                    i = i15;
                                    list2 = r0;
                                    i2 = r0;
                                    i3 = i15;
                                    x509Certificate = r13;
                                } else if (r13 == 0 || r13.getVersion() != 1) {
                                    x509Certificate = r13;
                                    RFC3280CertPathUtilities.prepareNextCertA(certPath, i17);
                                    PKIXPolicyNode prepareCertB = RFC3280CertPathUtilities.prepareCertB(certPath, i17, arrayListArr, processCertE, i15);
                                    RFC3280CertPathUtilities.prepareNextCertG(certPath, i17, r13);
                                    int prepareNextCertH1 = RFC3280CertPathUtilities.prepareNextCertH1(certPath, i17, i16);
                                    int prepareNextCertH2 = RFC3280CertPathUtilities.prepareNextCertH2(certPath, i17, i15);
                                    int prepareNextCertH3 = RFC3280CertPathUtilities.prepareNextCertH3(certPath, i17, i15);
                                    i4 = RFC3280CertPathUtilities.prepareNextCertI1(certPath, i17, prepareNextCertH1);
                                    i3 = RFC3280CertPathUtilities.prepareNextCertI2(certPath, i17, prepareNextCertH2);
                                    int prepareNextCertJ = RFC3280CertPathUtilities.prepareNextCertJ(certPath, i17, prepareNextCertH3);
                                    RFC3280CertPathUtilities.prepareNextCertK(certPath, i17);
                                    int prepareNextCertM = RFC3280CertPathUtilities.prepareNextCertM(certPath, i17, RFC3280CertPathUtilities.prepareNextCertL(certPath, i17, i3));
                                    RFC3280CertPathUtilities.prepareNextCertN(certPath, i17);
                                    Set<String> criticalExtensionOIDs = x509Certificate.getCriticalExtensionOIDs();
                                    if (criticalExtensionOIDs != null) {
                                        hashSet2 = new HashSet(criticalExtensionOIDs);
                                        hashSet2.remove(RFC3280CertPathUtilities.KEY_USAGE);
                                        hashSet2.remove(RFC3280CertPathUtilities.CERTIFICATE_POLICIES);
                                        hashSet2.remove(RFC3280CertPathUtilities.POLICY_MAPPINGS);
                                        hashSet2.remove(RFC3280CertPathUtilities.INHIBIT_ANY_POLICY);
                                        hashSet2.remove(RFC3280CertPathUtilities.ISSUING_DISTRIBUTION_POINT);
                                        hashSet2.remove(RFC3280CertPathUtilities.DELTA_CRL_INDICATOR);
                                        hashSet2.remove(RFC3280CertPathUtilities.POLICY_CONSTRAINTS);
                                        hashSet2.remove(RFC3280CertPathUtilities.BASIC_CONSTRAINTS);
                                        hashSet2.remove(RFC3280CertPathUtilities.SUBJECT_ALTERNATIVE_NAME);
                                        hashSet2.remove(RFC3280CertPathUtilities.NAME_CONSTRAINTS);
                                    } else {
                                        hashSet2 = new HashSet();
                                    }
                                    list2 = i3;
                                    RFC3280CertPathUtilities.prepareNextCertO(certPath, i17, hashSet2, list2);
                                    ca = PrincipalUtils.getSubjectPrincipal(x509Certificate);
                                    try {
                                        cAPublicKey = CertPathValidatorUtilities.getNextWorkingKey(certPath.getCertificates(), i17, this.helper);
                                        AlgorithmIdentifier algorithmIdentifier2 = CertPathValidatorUtilities.getAlgorithmIdentifier(cAPublicKey);
                                        algorithmIdentifier2.getAlgorithm();
                                        algorithmIdentifier2.getParameters();
                                        pKIXPolicyNode2 = prepareCertB;
                                        x509Certificate2 = x509Certificate;
                                        i = prepareNextCertJ;
                                        i10 = prepareNextCertM;
                                        i13 = i3;
                                        int i18 = i17 - 1;
                                        x509Certificate3 = x509Certificate;
                                        i12 = i;
                                        pKIXNameConstraintValidator = r13;
                                        findTrustAnchor = r22;
                                        certificates = list3;
                                        initialPolicies = set;
                                        size2 = i18;
                                        build = pKIXExtendedParameters3;
                                        List list4 = list2;
                                        i11 = i4;
                                        certPathCheckers = list4;
                                    } catch (CertPathValidatorException e2) {
                                        throw new CertPathValidatorException("Next working key could not be retrieved.", e2, certPath, i17);
                                    }
                                } else if (publicKey != 1 || (equals = (x509Certificate = r13).equals(r22.getTrustedCert())) == 0) {
                                    throw new CertPathValidatorException("Version 1 certificates can't be used as CA ones.", null, certPath, i17);
                                } else {
                                    i = i15;
                                    list2 = equals;
                                    i2 = equals;
                                    i3 = i15;
                                }
                                pKIXPolicyNode2 = processCertE;
                                i10 = i2;
                                i4 = i16;
                                i13 = i3;
                                int i182 = i17 - 1;
                                x509Certificate3 = x509Certificate;
                                i12 = i;
                                pKIXNameConstraintValidator = r13;
                                findTrustAnchor = r22;
                                certificates = list3;
                                initialPolicies = set;
                                size2 = i182;
                                build = pKIXExtendedParameters3;
                                List list42 = list2;
                                i11 = i4;
                                certPathCheckers = list42;
                            } catch (AnnotatedException e3) {
                                throw new CertPathValidatorException(e3.getMessage(), e3.getUnderlyingException(), certPath, size2);
                            }
                        }
                        PKIXExtendedParameters pKIXExtendedParameters4 = build;
                        int i19 = i11;
                        Set set2 = initialPolicies;
                        TrustAnchor trustAnchor = findTrustAnchor;
                        List list5 = certPathCheckers;
                        int i20 = size2;
                        int i21 = i20 + 1;
                        int wrapupCertB = RFC3280CertPathUtilities.wrapupCertB(certPath, i21, RFC3280CertPathUtilities.wrapupCertA(i19, x509Certificate3));
                        Set<String> criticalExtensionOIDs2 = x509Certificate3.getCriticalExtensionOIDs();
                        if (criticalExtensionOIDs2 != null) {
                            hashSet = new HashSet(criticalExtensionOIDs2);
                            hashSet.remove(RFC3280CertPathUtilities.KEY_USAGE);
                            hashSet.remove(RFC3280CertPathUtilities.CERTIFICATE_POLICIES);
                            hashSet.remove(RFC3280CertPathUtilities.POLICY_MAPPINGS);
                            hashSet.remove(RFC3280CertPathUtilities.INHIBIT_ANY_POLICY);
                            hashSet.remove(RFC3280CertPathUtilities.ISSUING_DISTRIBUTION_POINT);
                            hashSet.remove(RFC3280CertPathUtilities.DELTA_CRL_INDICATOR);
                            hashSet.remove(RFC3280CertPathUtilities.POLICY_CONSTRAINTS);
                            hashSet.remove(RFC3280CertPathUtilities.BASIC_CONSTRAINTS);
                            hashSet.remove(RFC3280CertPathUtilities.SUBJECT_ALTERNATIVE_NAME);
                            hashSet.remove(RFC3280CertPathUtilities.NAME_CONSTRAINTS);
                            hashSet.remove(RFC3280CertPathUtilities.CRL_DISTRIBUTION_POINTS);
                            hashSet.remove(Extension.extendedKeyUsage.getId());
                        } else {
                            hashSet = new HashSet();
                        }
                        RFC3280CertPathUtilities.wrapupCertF(certPath, i21, list5, hashSet);
                        X509Certificate x509Certificate5 = x509Certificate3;
                        PKIXPolicyNode wrapupCertG = RFC3280CertPathUtilities.wrapupCertG(certPath, pKIXExtendedParameters4, set2, i21, arrayListArr, pKIXPolicyNode2, hashSet4);
                        if (wrapupCertB <= 0 && wrapupCertG == null) {
                            throw new CertPathValidatorException("Path processing failed on policy.", null, certPath, i20);
                        }
                        return new PKIXCertPathValidatorResult(trustAnchor, wrapupCertG, x509Certificate5.getPublicKey());
                    } catch (CertPathValidatorException e4) {
                        throw new ExtCertPathValidatorException("Algorithm identifier of public key of trust anchor could not be read.", e4, certPath, -1);
                    }
                } catch (RuntimeException e5) {
                    throw new ExtCertPathValidatorException("Subject of trust anchor could not be (re)encoded.", e5, certPath, -1);
                }
            } catch (AnnotatedException e6) {
                e = e6;
                list = certificates;
            }
        } else {
            throw new InvalidAlgorithmParameterException("trustAnchors is null, this is not allowed for certification path validation.");
        }
    }
}
