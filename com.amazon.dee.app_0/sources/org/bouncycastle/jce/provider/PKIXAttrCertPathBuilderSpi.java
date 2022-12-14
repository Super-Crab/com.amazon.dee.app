package org.bouncycastle.jce.provider;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.Principal;
import java.security.cert.CertPathBuilderException;
import java.security.cert.CertPathBuilderResult;
import java.security.cert.CertPathBuilderSpi;
import java.security.cert.CertPathParameters;
import java.security.cert.Certificate;
import java.security.cert.PKIXBuilderParameters;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import javax.security.auth.x500.X500Principal;
import org.bouncycastle.jcajce.PKIXCertStoreSelector;
import org.bouncycastle.jcajce.PKIXExtendedBuilderParameters;
import org.bouncycastle.jce.exception.ExtCertPathBuilderException;
import org.bouncycastle.util.Store;
import org.bouncycastle.util.StoreException;
import org.bouncycastle.x509.ExtendedPKIXBuilderParameters;
import org.bouncycastle.x509.ExtendedPKIXParameters;
import org.bouncycastle.x509.X509AttributeCertStoreSelector;
import org.bouncycastle.x509.X509AttributeCertificate;
import org.bouncycastle.x509.X509CertStoreSelector;
/* loaded from: classes4.dex */
public class PKIXAttrCertPathBuilderSpi extends CertPathBuilderSpi {
    private Exception certPathException;

    /* JADX WARN: Removed duplicated region for block: B:52:0x010b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.security.cert.CertPathBuilderResult build(org.bouncycastle.x509.X509AttributeCertificate r6, java.security.cert.X509Certificate r7, org.bouncycastle.jcajce.PKIXExtendedBuilderParameters r8, java.util.List r9) {
        /*
            Method dump skipped, instructions count: 279
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.jce.provider.PKIXAttrCertPathBuilderSpi.build(org.bouncycastle.x509.X509AttributeCertificate, java.security.cert.X509Certificate, org.bouncycastle.jcajce.PKIXExtendedBuilderParameters, java.util.List):java.security.cert.CertPathBuilderResult");
    }

    protected static Collection findCertificates(X509AttributeCertStoreSelector x509AttributeCertStoreSelector, List list) throws AnnotatedException {
        HashSet hashSet = new HashSet();
        for (Object obj : list) {
            if (obj instanceof Store) {
                try {
                    hashSet.addAll(((Store) obj).getMatches(x509AttributeCertStoreSelector));
                } catch (StoreException e) {
                    throw new AnnotatedException("Problem while picking certificates from X.509 store.", e);
                }
            }
        }
        return hashSet;
    }

    @Override // java.security.cert.CertPathBuilderSpi
    public CertPathBuilderResult engineBuild(CertPathParameters certPathParameters) throws CertPathBuilderException, InvalidAlgorithmParameterException {
        PKIXExtendedBuilderParameters pKIXExtendedBuilderParameters;
        Exception exc;
        boolean z = certPathParameters instanceof PKIXBuilderParameters;
        if (!z && !(certPathParameters instanceof ExtendedPKIXBuilderParameters) && !(certPathParameters instanceof PKIXExtendedBuilderParameters)) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Parameters must be an instance of ");
            outline107.append(PKIXBuilderParameters.class.getName());
            outline107.append(" or ");
            outline107.append(PKIXExtendedBuilderParameters.class.getName());
            outline107.append(".");
            throw new InvalidAlgorithmParameterException(outline107.toString());
        }
        List arrayList = new ArrayList();
        if (z) {
            PKIXExtendedBuilderParameters.Builder builder = new PKIXExtendedBuilderParameters.Builder((PKIXBuilderParameters) certPathParameters);
            if (certPathParameters instanceof ExtendedPKIXParameters) {
                ExtendedPKIXBuilderParameters extendedPKIXBuilderParameters = (ExtendedPKIXBuilderParameters) certPathParameters;
                builder.addExcludedCerts(extendedPKIXBuilderParameters.getExcludedCerts());
                builder.setMaxPathLength(extendedPKIXBuilderParameters.getMaxPathLength());
                arrayList = extendedPKIXBuilderParameters.getStores();
            }
            pKIXExtendedBuilderParameters = builder.build();
        } else {
            pKIXExtendedBuilderParameters = (PKIXExtendedBuilderParameters) certPathParameters;
        }
        ArrayList arrayList2 = new ArrayList();
        PKIXCertStoreSelector targetConstraints = pKIXExtendedBuilderParameters.getBaseParameters().getTargetConstraints();
        if (!(targetConstraints instanceof X509AttributeCertStoreSelector)) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("TargetConstraints must be an instance of ");
            outline1072.append(X509AttributeCertStoreSelector.class.getName());
            outline1072.append(" for ");
            outline1072.append(PKIXAttrCertPathBuilderSpi.class.getName());
            outline1072.append(" class.");
            throw new CertPathBuilderException(outline1072.toString());
        }
        try {
            Collection findCertificates = findCertificates((X509AttributeCertStoreSelector) targetConstraints, arrayList);
            if (findCertificates.isEmpty()) {
                throw new CertPathBuilderException("No attribute certificate found matching targetConstraints.");
            }
            CertPathBuilderResult certPathBuilderResult = null;
            Iterator it2 = findCertificates.iterator();
            while (it2.hasNext() && certPathBuilderResult == null) {
                X509AttributeCertificate x509AttributeCertificate = (X509AttributeCertificate) it2.next();
                X509CertStoreSelector x509CertStoreSelector = new X509CertStoreSelector();
                Principal[] principals = x509AttributeCertificate.getIssuer().getPrincipals();
                HashSet hashSet = new HashSet();
                for (int i = 0; i < principals.length; i++) {
                    try {
                        if (principals[i] instanceof X500Principal) {
                            x509CertStoreSelector.setSubject(((X500Principal) principals[i]).getEncoded());
                        }
                        PKIXCertStoreSelector<? extends Certificate> build = new PKIXCertStoreSelector.Builder(x509CertStoreSelector).build();
                        hashSet.addAll(CertPathValidatorUtilities.findCertificates(build, pKIXExtendedBuilderParameters.getBaseParameters().getCertStores()));
                        hashSet.addAll(CertPathValidatorUtilities.findCertificates(build, pKIXExtendedBuilderParameters.getBaseParameters().getCertificateStores()));
                    } catch (IOException e) {
                        throw new ExtCertPathBuilderException("cannot encode X500Principal.", e);
                    } catch (AnnotatedException e2) {
                        throw new ExtCertPathBuilderException("Public key certificate for attribute certificate cannot be searched.", e2);
                    }
                }
                if (hashSet.isEmpty()) {
                    throw new CertPathBuilderException("Public key certificate for attribute certificate cannot be found.");
                }
                Iterator it3 = hashSet.iterator();
                while (it3.hasNext() && certPathBuilderResult == null) {
                    certPathBuilderResult = build(x509AttributeCertificate, (X509Certificate) it3.next(), pKIXExtendedBuilderParameters, arrayList2);
                }
            }
            if (certPathBuilderResult == null && (exc = this.certPathException) != null) {
                throw new ExtCertPathBuilderException("Possible certificate chain could not be validated.", exc);
            }
            if (certPathBuilderResult != null || this.certPathException != null) {
                return certPathBuilderResult;
            }
            throw new CertPathBuilderException("Unable to find certificate chain.");
        } catch (AnnotatedException e3) {
            throw new ExtCertPathBuilderException("Error finding target attribute certificate.", e3);
        }
    }
}
