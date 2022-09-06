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
import java.security.cert.PKIXRevocationChecker;
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
import org.bouncycastle.jcajce.PKIXCertRevocationChecker;
import org.bouncycastle.jcajce.PKIXExtendedBuilderParameters;
import org.bouncycastle.jcajce.PKIXExtendedParameters;
import org.bouncycastle.jcajce.interfaces.BCX509Certificate;
import org.bouncycastle.jcajce.util.BCJcaJceHelper;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.jce.exception.ExtCertPathValidatorException;
import org.bouncycastle.x509.ExtendedPKIXParameters;
/* loaded from: classes4.dex */
public class PKIXCertPathValidatorSpi_8 extends CertPathValidatorSpi {
    private final JcaJceHelper helper;
    private final boolean isForCRLCheck;

    public PKIXCertPathValidatorSpi_8() {
        this(false);
    }

    public PKIXCertPathValidatorSpi_8(boolean z) {
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

    @Override // java.security.cert.CertPathValidatorSpi
    public PKIXCertPathChecker engineGetRevocationChecker() {
        return new ProvRevocationChecker(this.helper);
    }

    /* JADX WARN: Type inference failed for: r22v5, types: [boolean, java.security.cert.TrustAnchor] */
    /* JADX WARN: Type inference failed for: r23v2, types: [int] */
    @Override // java.security.cert.CertPathValidatorSpi
    public CertPathValidatorResult engineValidate(CertPath certPath, CertPathParameters certPathParameters) throws CertPathValidatorException, InvalidAlgorithmParameterException {
        PKIXExtendedParameters pKIXExtendedParameters;
        List<? extends Certificate> list;
        X500Name ca;
        PublicKey cAPublicKey;
        HashSet hashSet;
        ArrayList arrayList;
        ArrayList[] arrayListArr;
        HashSet hashSet2;
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
                ArrayList arrayList2 = new ArrayList();
                PKIXCertRevocationChecker pKIXCertRevocationChecker = null;
                for (PKIXCertPathChecker pKIXCertPathChecker : build.getCertPathCheckers()) {
                    pKIXCertPathChecker.init(false);
                    if (!(pKIXCertPathChecker instanceof PKIXRevocationChecker)) {
                        arrayList2.add(pKIXCertPathChecker);
                    } else if (pKIXCertRevocationChecker != null) {
                        throw new CertPathValidatorException("only one PKIXRevocationChecker allowed");
                    } else {
                        pKIXCertRevocationChecker = pKIXCertPathChecker instanceof PKIXCertRevocationChecker ? (PKIXCertRevocationChecker) pKIXCertPathChecker : new WrappedRevocationChecker(pKIXCertPathChecker);
                    }
                }
                ProvRevocationChecker provRevocationChecker = (!build.isRevocationEnabled() || pKIXCertRevocationChecker != null) ? pKIXCertRevocationChecker : new ProvRevocationChecker(this.helper);
                int i = size + 1;
                ArrayList[] arrayListArr2 = new ArrayList[i];
                for (int i2 = 0; i2 < arrayListArr2.length; i2++) {
                    arrayListArr2[i2] = new ArrayList();
                }
                HashSet hashSet3 = new HashSet();
                hashSet3.add(RFC3280CertPathUtilities.ANY_POLICY);
                PKIXPolicyNode pKIXPolicyNode = new PKIXPolicyNode(new ArrayList(), 0, hashSet3, null, new HashSet(), RFC3280CertPathUtilities.ANY_POLICY, false);
                arrayListArr2[0].add(pKIXPolicyNode);
                PKIXNameConstraintValidator pKIXNameConstraintValidator = new PKIXNameConstraintValidator();
                HashSet hashSet4 = new HashSet();
                int i3 = build.isExplicitPolicyRequired() ? 0 : i;
                int i4 = build.isAnyPolicyInhibited() ? 0 : i;
                if (build.isPolicyMappingInhibited()) {
                    i = 0;
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
                        int size2 = certificates.size() - 1;
                        int i5 = i4;
                        PKIXPolicyNode pKIXPolicyNode2 = pKIXPolicyNode;
                        int i6 = i3;
                        X509Certificate x509Certificate = null;
                        int i7 = i;
                        int i8 = size;
                        while (size2 >= 0) {
                            int i9 = size - size2;
                            Set set = initialPolicies;
                            X509Certificate x509Certificate2 = (X509Certificate) certificates.get(size2);
                            boolean z = size2 == certificates.size() + (-1);
                            try {
                                checkCertificate(x509Certificate2);
                                List<? extends Certificate> list2 = certificates;
                                ?? r23 = arrayList2;
                                int i10 = i5;
                                PKIXExtendedParameters pKIXExtendedParameters2 = build;
                                int i11 = i6;
                                int i12 = size2;
                                ?? r22 = findTrustAnchor;
                                PKIXNameConstraintValidator pKIXNameConstraintValidator2 = pKIXNameConstraintValidator;
                                ArrayList[] arrayListArr3 = arrayListArr2;
                                RFC3280CertPathUtilities.processCertA(certPath, build, provRevocationChecker, size2, cAPublicKey, r22, ca, trustedCert);
                                RFC3280CertPathUtilities.processCertBC(certPath, i12, pKIXNameConstraintValidator2, this.isForCRLCheck);
                                PKIXPolicyNode pKIXPolicyNode3 = pKIXPolicyNode2;
                                PKIXPolicyNode processCertE = RFC3280CertPathUtilities.processCertE(certPath, i12, RFC3280CertPathUtilities.processCertD(certPath, i12, hashSet4, pKIXPolicyNode3, arrayListArr3, i10, this.isForCRLCheck));
                                RFC3280CertPathUtilities.processCertF(certPath, i12, processCertE, i11);
                                if (pKIXPolicyNode3 != size) {
                                    if (x509Certificate2 == null || x509Certificate2.getVersion() != 1) {
                                        RFC3280CertPathUtilities.prepareNextCertA(certPath, i12);
                                        arrayListArr = arrayListArr3;
                                        PKIXPolicyNode prepareCertB = RFC3280CertPathUtilities.prepareCertB(certPath, i12, arrayListArr, processCertE, i12);
                                        RFC3280CertPathUtilities.prepareNextCertG(certPath, i12, pKIXNameConstraintValidator2);
                                        int prepareNextCertH1 = RFC3280CertPathUtilities.prepareNextCertH1(certPath, i12, i11);
                                        int prepareNextCertH2 = RFC3280CertPathUtilities.prepareNextCertH2(certPath, i12, i12);
                                        int prepareNextCertH3 = RFC3280CertPathUtilities.prepareNextCertH3(certPath, i12, i10);
                                        int prepareNextCertI1 = RFC3280CertPathUtilities.prepareNextCertI1(certPath, i12, prepareNextCertH1);
                                        i7 = RFC3280CertPathUtilities.prepareNextCertI2(certPath, i12, prepareNextCertH2);
                                        int prepareNextCertJ = RFC3280CertPathUtilities.prepareNextCertJ(certPath, i12, prepareNextCertH3);
                                        RFC3280CertPathUtilities.prepareNextCertK(certPath, i12);
                                        int prepareNextCertM = RFC3280CertPathUtilities.prepareNextCertM(certPath, i12, RFC3280CertPathUtilities.prepareNextCertL(certPath, i12, r23));
                                        RFC3280CertPathUtilities.prepareNextCertN(certPath, i12);
                                        Set<String> criticalExtensionOIDs = x509Certificate2.getCriticalExtensionOIDs();
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
                                        arrayList = r23;
                                        RFC3280CertPathUtilities.prepareNextCertO(certPath, i12, hashSet2, arrayList);
                                        ca = PrincipalUtils.getSubjectPrincipal(x509Certificate2);
                                        try {
                                            cAPublicKey = CertPathValidatorUtilities.getNextWorkingKey(certPath.getCertificates(), i12, this.helper);
                                            AlgorithmIdentifier algorithmIdentifier2 = CertPathValidatorUtilities.getAlgorithmIdentifier(cAPublicKey);
                                            algorithmIdentifier2.getAlgorithm();
                                            algorithmIdentifier2.getParameters();
                                            pKIXPolicyNode2 = prepareCertB;
                                            i8 = prepareNextCertM;
                                            trustedCert = x509Certificate2;
                                            i6 = prepareNextCertI1;
                                            i5 = prepareNextCertJ;
                                            int i13 = i12 - 1;
                                            arrayListArr2 = arrayListArr;
                                            arrayList2 = arrayList;
                                            pKIXNameConstraintValidator = pKIXNameConstraintValidator2;
                                            findTrustAnchor = r22;
                                            certificates = list2;
                                            build = pKIXExtendedParameters2;
                                            size2 = i13;
                                            x509Certificate = x509Certificate2;
                                            initialPolicies = set;
                                        } catch (CertPathValidatorException e2) {
                                            throw new CertPathValidatorException("Next working key could not be retrieved.", e2, certPath, i12);
                                        }
                                    } else if (pKIXPolicyNode3 != 1 || !x509Certificate2.equals(r22.getTrustedCert())) {
                                        throw new CertPathValidatorException("Version 1 certificates can't be used as CA ones.", null, certPath, i12);
                                    }
                                }
                                arrayList = r23;
                                i7 = i12;
                                arrayListArr = arrayListArr3;
                                pKIXPolicyNode2 = processCertE;
                                i5 = i10;
                                i8 = r23;
                                i6 = i11;
                                int i132 = i12 - 1;
                                arrayListArr2 = arrayListArr;
                                arrayList2 = arrayList;
                                pKIXNameConstraintValidator = pKIXNameConstraintValidator2;
                                findTrustAnchor = r22;
                                certificates = list2;
                                build = pKIXExtendedParameters2;
                                size2 = i132;
                                x509Certificate = x509Certificate2;
                                initialPolicies = set;
                            } catch (AnnotatedException e3) {
                                throw new CertPathValidatorException(e3.getMessage(), e3.getUnderlyingException(), certPath, size2);
                            }
                        }
                        PKIXExtendedParameters pKIXExtendedParameters3 = build;
                        ArrayList[] arrayListArr4 = arrayListArr2;
                        Set set2 = initialPolicies;
                        TrustAnchor trustAnchor = findTrustAnchor;
                        ArrayList arrayList3 = arrayList2;
                        int i14 = size2;
                        int i15 = i14 + 1;
                        int wrapupCertB = RFC3280CertPathUtilities.wrapupCertB(certPath, i15, RFC3280CertPathUtilities.wrapupCertA(i6, x509Certificate));
                        Set<String> criticalExtensionOIDs2 = x509Certificate.getCriticalExtensionOIDs();
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
                        RFC3280CertPathUtilities.wrapupCertF(certPath, i15, arrayList3, hashSet);
                        X509Certificate x509Certificate3 = x509Certificate;
                        PKIXPolicyNode wrapupCertG = RFC3280CertPathUtilities.wrapupCertG(certPath, pKIXExtendedParameters3, set2, i15, arrayListArr4, pKIXPolicyNode2, hashSet4);
                        if (wrapupCertB <= 0 && wrapupCertG == null) {
                            throw new CertPathValidatorException("Path processing failed on policy.", null, certPath, i14);
                        }
                        return new PKIXCertPathValidatorResult(trustAnchor, wrapupCertG, x509Certificate3.getPublicKey());
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
