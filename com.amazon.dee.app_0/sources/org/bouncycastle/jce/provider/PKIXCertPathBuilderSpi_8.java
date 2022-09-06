package org.bouncycastle.jce.provider;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.security.InvalidAlgorithmParameterException;
import java.security.cert.CertPathBuilderException;
import java.security.cert.CertPathBuilderResult;
import java.security.cert.CertPathBuilderSpi;
import java.security.cert.CertPathParameters;
import java.security.cert.PKIXBuilderParameters;
import java.security.cert.PKIXCertPathChecker;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Iterator;
import org.bouncycastle.jcajce.PKIXCertStore;
import org.bouncycastle.jcajce.PKIXExtendedBuilderParameters;
import org.bouncycastle.jcajce.PKIXExtendedParameters;
import org.bouncycastle.jcajce.util.BCJcaJceHelper;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.x509.ExtendedPKIXBuilderParameters;
import org.bouncycastle.x509.ExtendedPKIXParameters;
/* loaded from: classes4.dex */
public class PKIXCertPathBuilderSpi_8 extends CertPathBuilderSpi {
    private Exception certPathException;
    private final JcaJceHelper helper;
    private final boolean isForCRLCheck;

    public PKIXCertPathBuilderSpi_8() {
        this(false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PKIXCertPathBuilderSpi_8(boolean z) {
        this.helper = new BCJcaJceHelper();
        this.isForCRLCheck = z;
    }

    /* JADX WARN: Removed duplicated region for block: B:51:0x00fb  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected java.security.cert.CertPathBuilderResult build(java.security.cert.X509Certificate r6, org.bouncycastle.jcajce.PKIXExtendedBuilderParameters r7, java.util.List r8) {
        /*
            r5 = this;
            boolean r0 = r8.contains(r6)
            r1 = 0
            if (r0 == 0) goto L8
            return r1
        L8:
            java.util.Set r0 = r7.getExcludedCerts()
            boolean r0 = r0.contains(r6)
            if (r0 == 0) goto L13
            return r1
        L13:
            int r0 = r7.getMaxPathLength()
            r2 = -1
            if (r0 == r2) goto L27
            int r0 = r8.size()
            int r0 = r0 + (-1)
            int r2 = r7.getMaxPathLength()
            if (r0 <= r2) goto L27
            return r1
        L27:
            r8.add(r6)
            org.bouncycastle.jcajce.provider.asymmetric.x509.CertificateFactory r0 = new org.bouncycastle.jcajce.provider.asymmetric.x509.CertificateFactory     // Catch: java.lang.Exception -> Lee
            r0.<init>()     // Catch: java.lang.Exception -> Lee
            org.bouncycastle.jce.provider.PKIXCertPathValidatorSpi_8 r2 = new org.bouncycastle.jce.provider.PKIXCertPathValidatorSpi_8     // Catch: java.lang.Exception -> Lee
            boolean r3 = r5.isForCRLCheck     // Catch: java.lang.Exception -> Lee
            r2.<init>(r3)     // Catch: java.lang.Exception -> Lee
            org.bouncycastle.jcajce.PKIXExtendedParameters r3 = r7.getBaseParameters()     // Catch: org.bouncycastle.jce.provider.AnnotatedException -> Lf6
            java.util.Set r3 = r3.getTrustAnchors()     // Catch: org.bouncycastle.jce.provider.AnnotatedException -> Lf6
            org.bouncycastle.jcajce.PKIXExtendedParameters r4 = r7.getBaseParameters()     // Catch: org.bouncycastle.jce.provider.AnnotatedException -> Lf6
            java.lang.String r4 = r4.getSigProvider()     // Catch: org.bouncycastle.jce.provider.AnnotatedException -> Lf6
            boolean r3 = org.bouncycastle.jce.provider.CertPathValidatorUtilities.isIssuerTrustAnchor(r6, r3, r4)     // Catch: org.bouncycastle.jce.provider.AnnotatedException -> Lf6
            if (r3 == 0) goto L7a
            java.security.cert.CertPath r0 = r0.engineGenerateCertPath(r8)     // Catch: java.lang.Exception -> L71
            java.security.cert.CertPathValidatorResult r7 = r2.engineValidate(r0, r7)     // Catch: java.lang.Exception -> L68
            java.security.cert.PKIXCertPathValidatorResult r7 = (java.security.cert.PKIXCertPathValidatorResult) r7     // Catch: java.lang.Exception -> L68
            java.security.cert.PKIXCertPathBuilderResult r2 = new java.security.cert.PKIXCertPathBuilderResult     // Catch: org.bouncycastle.jce.provider.AnnotatedException -> Lf6
            java.security.cert.TrustAnchor r3 = r7.getTrustAnchor()     // Catch: org.bouncycastle.jce.provider.AnnotatedException -> Lf6
            java.security.cert.PolicyNode r4 = r7.getPolicyTree()     // Catch: org.bouncycastle.jce.provider.AnnotatedException -> Lf6
            java.security.PublicKey r7 = r7.getPublicKey()     // Catch: org.bouncycastle.jce.provider.AnnotatedException -> Lf6
            r2.<init>(r0, r3, r4, r7)     // Catch: org.bouncycastle.jce.provider.AnnotatedException -> Lf6
            return r2
        L68:
            r7 = move-exception
            org.bouncycastle.jce.provider.AnnotatedException r0 = new org.bouncycastle.jce.provider.AnnotatedException     // Catch: org.bouncycastle.jce.provider.AnnotatedException -> Lf6
            java.lang.String r2 = "Certification path could not be validated."
            r0.<init>(r2, r7)     // Catch: org.bouncycastle.jce.provider.AnnotatedException -> Lf6
            throw r0     // Catch: org.bouncycastle.jce.provider.AnnotatedException -> Lf6
        L71:
            r7 = move-exception
            org.bouncycastle.jce.provider.AnnotatedException r0 = new org.bouncycastle.jce.provider.AnnotatedException     // Catch: org.bouncycastle.jce.provider.AnnotatedException -> Lf6
            java.lang.String r2 = "Certification path could not be constructed from certificate list."
            r0.<init>(r2, r7)     // Catch: org.bouncycastle.jce.provider.AnnotatedException -> Lf6
            throw r0     // Catch: org.bouncycastle.jce.provider.AnnotatedException -> Lf6
        L7a:
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch: org.bouncycastle.jce.provider.AnnotatedException -> Lf6
            r0.<init>()     // Catch: org.bouncycastle.jce.provider.AnnotatedException -> Lf6
            org.bouncycastle.jcajce.PKIXExtendedParameters r2 = r7.getBaseParameters()     // Catch: org.bouncycastle.jce.provider.AnnotatedException -> Lf6
            java.util.List r2 = r2.getCertificateStores()     // Catch: org.bouncycastle.jce.provider.AnnotatedException -> Lf6
            r0.addAll(r2)     // Catch: org.bouncycastle.jce.provider.AnnotatedException -> Lf6
            org.bouncycastle.asn1.ASN1ObjectIdentifier r2 = org.bouncycastle.asn1.x509.Extension.issuerAlternativeName     // Catch: java.security.cert.CertificateParsingException -> Le5 org.bouncycastle.jce.provider.AnnotatedException -> Lf6
            java.lang.String r2 = r2.getId()     // Catch: java.security.cert.CertificateParsingException -> Le5 org.bouncycastle.jce.provider.AnnotatedException -> Lf6
            byte[] r2 = r6.getExtensionValue(r2)     // Catch: java.security.cert.CertificateParsingException -> Le5 org.bouncycastle.jce.provider.AnnotatedException -> Lf6
            org.bouncycastle.jcajce.PKIXExtendedParameters r3 = r7.getBaseParameters()     // Catch: java.security.cert.CertificateParsingException -> Le5 org.bouncycastle.jce.provider.AnnotatedException -> Lf6
            java.util.Map r3 = r3.getNamedCertificateStoreMap()     // Catch: java.security.cert.CertificateParsingException -> Le5 org.bouncycastle.jce.provider.AnnotatedException -> Lf6
            java.util.List r2 = org.bouncycastle.jce.provider.CertPathValidatorUtilities.getAdditionalStoresFromAltNames(r2, r3)     // Catch: java.security.cert.CertificateParsingException -> Le5 org.bouncycastle.jce.provider.AnnotatedException -> Lf6
            r0.addAll(r2)     // Catch: java.security.cert.CertificateParsingException -> Le5 org.bouncycastle.jce.provider.AnnotatedException -> Lf6
            java.util.HashSet r2 = new java.util.HashSet     // Catch: org.bouncycastle.jce.provider.AnnotatedException -> Lf6
            r2.<init>()     // Catch: org.bouncycastle.jce.provider.AnnotatedException -> Lf6
            org.bouncycastle.jcajce.PKIXExtendedParameters r3 = r7.getBaseParameters()     // Catch: org.bouncycastle.jce.provider.AnnotatedException -> Ldc
            java.util.List r3 = r3.getCertStores()     // Catch: org.bouncycastle.jce.provider.AnnotatedException -> Ldc
            java.util.Collection r0 = org.bouncycastle.jce.provider.CertPathValidatorUtilities.findIssuerCerts(r6, r3, r0)     // Catch: org.bouncycastle.jce.provider.AnnotatedException -> Ldc
            r2.addAll(r0)     // Catch: org.bouncycastle.jce.provider.AnnotatedException -> Ldc
            boolean r0 = r2.isEmpty()     // Catch: org.bouncycastle.jce.provider.AnnotatedException -> Lf6
            if (r0 != 0) goto Ld4
            java.util.Iterator r0 = r2.iterator()     // Catch: org.bouncycastle.jce.provider.AnnotatedException -> Lf6
        Lc1:
            boolean r2 = r0.hasNext()     // Catch: org.bouncycastle.jce.provider.AnnotatedException -> Lf6
            if (r2 == 0) goto Lf9
            if (r1 != 0) goto Lf9
            java.lang.Object r2 = r0.next()     // Catch: org.bouncycastle.jce.provider.AnnotatedException -> Lf6
            java.security.cert.X509Certificate r2 = (java.security.cert.X509Certificate) r2     // Catch: org.bouncycastle.jce.provider.AnnotatedException -> Lf6
            java.security.cert.CertPathBuilderResult r1 = r5.build(r2, r7, r8)     // Catch: org.bouncycastle.jce.provider.AnnotatedException -> Lf6
            goto Lc1
        Ld4:
            org.bouncycastle.jce.provider.AnnotatedException r7 = new org.bouncycastle.jce.provider.AnnotatedException     // Catch: org.bouncycastle.jce.provider.AnnotatedException -> Lf6
            java.lang.String r0 = "No issuer certificate for certificate in certification path found."
            r7.<init>(r0)     // Catch: org.bouncycastle.jce.provider.AnnotatedException -> Lf6
            throw r7     // Catch: org.bouncycastle.jce.provider.AnnotatedException -> Lf6
        Ldc:
            r7 = move-exception
            org.bouncycastle.jce.provider.AnnotatedException r0 = new org.bouncycastle.jce.provider.AnnotatedException     // Catch: org.bouncycastle.jce.provider.AnnotatedException -> Lf6
            java.lang.String r2 = "Cannot find issuer certificate for certificate in certification path."
            r0.<init>(r2, r7)     // Catch: org.bouncycastle.jce.provider.AnnotatedException -> Lf6
            throw r0     // Catch: org.bouncycastle.jce.provider.AnnotatedException -> Lf6
        Le5:
            r7 = move-exception
            org.bouncycastle.jce.provider.AnnotatedException r0 = new org.bouncycastle.jce.provider.AnnotatedException     // Catch: org.bouncycastle.jce.provider.AnnotatedException -> Lf6
            java.lang.String r2 = "No additional X.509 stores can be added from certificate locations."
            r0.<init>(r2, r7)     // Catch: org.bouncycastle.jce.provider.AnnotatedException -> Lf6
            throw r0     // Catch: org.bouncycastle.jce.provider.AnnotatedException -> Lf6
        Lee:
            java.lang.RuntimeException r7 = new java.lang.RuntimeException     // Catch: org.bouncycastle.jce.provider.AnnotatedException -> Lf6
            java.lang.String r0 = "Exception creating support classes."
            r7.<init>(r0)     // Catch: org.bouncycastle.jce.provider.AnnotatedException -> Lf6
            throw r7     // Catch: org.bouncycastle.jce.provider.AnnotatedException -> Lf6
        Lf6:
            r7 = move-exception
            r5.certPathException = r7
        Lf9:
            if (r1 != 0) goto Lfe
            r8.remove(r6)
        Lfe:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.jce.provider.PKIXCertPathBuilderSpi_8.build(java.security.cert.X509Certificate, org.bouncycastle.jcajce.PKIXExtendedBuilderParameters, java.util.List):java.security.cert.CertPathBuilderResult");
    }

    @Override // java.security.cert.CertPathBuilderSpi
    public CertPathBuilderResult engineBuild(CertPathParameters certPathParameters) throws CertPathBuilderException, InvalidAlgorithmParameterException {
        PKIXExtendedBuilderParameters pKIXExtendedBuilderParameters;
        Exception exc;
        PKIXExtendedBuilderParameters.Builder builder;
        if (certPathParameters instanceof PKIXBuilderParameters) {
            PKIXBuilderParameters pKIXBuilderParameters = (PKIXBuilderParameters) certPathParameters;
            PKIXExtendedParameters.Builder builder2 = new PKIXExtendedParameters.Builder(pKIXBuilderParameters);
            if (certPathParameters instanceof ExtendedPKIXParameters) {
                ExtendedPKIXBuilderParameters extendedPKIXBuilderParameters = (ExtendedPKIXBuilderParameters) certPathParameters;
                for (PKIXCertStore pKIXCertStore : extendedPKIXBuilderParameters.getAdditionalStores()) {
                    builder2.addCertificateStore(pKIXCertStore);
                }
                PKIXExtendedBuilderParameters.Builder builder3 = new PKIXExtendedBuilderParameters.Builder(builder2.build());
                builder3.addExcludedCerts(extendedPKIXBuilderParameters.getExcludedCerts());
                builder3.setMaxPathLength(extendedPKIXBuilderParameters.getMaxPathLength());
                builder = builder3;
            } else {
                builder = new PKIXExtendedBuilderParameters.Builder(pKIXBuilderParameters);
            }
            pKIXExtendedBuilderParameters = builder.build();
        } else if (!(certPathParameters instanceof PKIXExtendedBuilderParameters)) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Parameters must be an instance of ");
            outline107.append(PKIXBuilderParameters.class.getName());
            outline107.append(" or ");
            outline107.append(PKIXExtendedBuilderParameters.class.getName());
            outline107.append(".");
            throw new InvalidAlgorithmParameterException(outline107.toString());
        } else {
            pKIXExtendedBuilderParameters = (PKIXExtendedBuilderParameters) certPathParameters;
        }
        ArrayList arrayList = new ArrayList();
        CertPathBuilderResult certPathBuilderResult = null;
        Iterator it2 = CertPathValidatorUtilities.findTargets(pKIXExtendedBuilderParameters).iterator();
        while (it2.hasNext() && certPathBuilderResult == null) {
            certPathBuilderResult = build((X509Certificate) it2.next(), pKIXExtendedBuilderParameters, arrayList);
        }
        if (certPathBuilderResult == null && (exc = this.certPathException) != null) {
            if (!(exc instanceof AnnotatedException)) {
                throw new CertPathBuilderException("Possible certificate chain could not be validated.", exc);
            }
            throw new CertPathBuilderException(exc.getMessage(), this.certPathException.getCause());
        } else if (certPathBuilderResult != null || this.certPathException != null) {
            return certPathBuilderResult;
        } else {
            throw new CertPathBuilderException("Unable to find certificate chain.");
        }
    }

    @Override // java.security.cert.CertPathBuilderSpi
    public PKIXCertPathChecker engineGetRevocationChecker() {
        return new ProvRevocationChecker(this.helper);
    }
}
