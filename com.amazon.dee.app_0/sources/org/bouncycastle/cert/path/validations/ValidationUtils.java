package org.bouncycastle.cert.path.validations;

import org.bouncycastle.cert.X509CertificateHolder;
/* loaded from: classes4.dex */
class ValidationUtils {
    ValidationUtils() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isSelfIssued(X509CertificateHolder x509CertificateHolder) {
        return x509CertificateHolder.getSubject().equals(x509CertificateHolder.getIssuer());
    }
}
