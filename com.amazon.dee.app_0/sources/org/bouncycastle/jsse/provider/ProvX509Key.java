package org.bouncycastle.jsse.provider;

import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import javax.net.ssl.X509KeyManager;
import org.bouncycastle.jsse.BCX509Key;
/* loaded from: classes4.dex */
class ProvX509Key implements BCX509Key {
    private final X509Certificate[] certificateChain;
    private final PrivateKey privateKey;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ProvX509Key(PrivateKey privateKey, X509Certificate[] x509CertificateArr) {
        this.privateKey = privateKey;
        this.certificateChain = x509CertificateArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ProvX509Key from(X509KeyManager x509KeyManager, String str) {
        PrivateKey privateKey;
        X509Certificate[] certificateChain;
        if (x509KeyManager != null) {
            if (str == null || (privateKey = x509KeyManager.getPrivateKey(str)) == null || (certificateChain = x509KeyManager.getCertificateChain(str)) == null || certificateChain.length < 1) {
                return null;
            }
            X509Certificate[] x509CertificateArr = (X509Certificate[]) certificateChain.clone();
            if (!JsseUtils.containsNull(x509CertificateArr)) {
                return new ProvX509Key(privateKey, x509CertificateArr);
            }
            return null;
        }
        throw new NullPointerException("'x509KeyManager' cannot be null");
    }

    @Override // org.bouncycastle.jsse.BCX509Key
    public X509Certificate[] getCertificateChain() {
        return (X509Certificate[]) this.certificateChain.clone();
    }

    @Override // org.bouncycastle.jsse.BCX509Key
    public PrivateKey getPrivateKey() {
        return this.privateKey;
    }
}
