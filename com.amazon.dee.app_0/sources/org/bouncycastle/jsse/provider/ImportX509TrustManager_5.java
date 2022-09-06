package org.bouncycastle.jsse.provider;

import java.net.Socket;
import java.security.GeneralSecurityException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.X509TrustManager;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.jsse.BCX509ExtendedTrustManager;
/* loaded from: classes4.dex */
class ImportX509TrustManager_5 extends BCX509ExtendedTrustManager implements ImportX509TrustManager {
    final JcaJceHelper helper;
    final X509TrustManager x509TrustManager;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ImportX509TrustManager_5(JcaJceHelper jcaJceHelper, X509TrustManager x509TrustManager) {
        this.helper = jcaJceHelper;
        this.x509TrustManager = x509TrustManager;
    }

    private void checkAdditionalTrust(X509Certificate[] x509CertificateArr, String str, TransportData transportData, boolean z) throws CertificateException {
        checkAlgorithmConstraints(x509CertificateArr, str, transportData, z);
        ProvX509TrustManager.checkExtendedTrust(x509CertificateArr, str, transportData, z);
    }

    private void checkAlgorithmConstraints(X509Certificate[] x509CertificateArr, String str, TransportData transportData, boolean z) throws CertificateException {
        try {
            ProvAlgorithmChecker.checkChain(this.helper, TransportData.getAlgorithmConstraints(transportData, false), getTrustedCerts(), x509CertificateArr, ProvX509TrustManager.getRequiredExtendedKeyUsage(z), ProvX509TrustManager.getRequiredKeyUsage(z, str));
        } catch (GeneralSecurityException e) {
            throw new CertificateException("Certificates do not conform to algorithm constraints", e);
        }
    }

    private static X509Certificate[] checkChain(X509Certificate[] x509CertificateArr) {
        if (x509CertificateArr == null || x509CertificateArr.length < 1) {
            throw new IllegalArgumentException("'chain' must be a chain of at least one certificate");
        }
        return x509CertificateArr;
    }

    private static X509Certificate[] copyChain(X509Certificate[] x509CertificateArr) {
        return (X509Certificate[]) checkChain(x509CertificateArr).clone();
    }

    private Set<X509Certificate> getTrustedCerts() {
        X509Certificate[] acceptedIssuers = getAcceptedIssuers();
        if (acceptedIssuers == null || acceptedIssuers.length < 1) {
            return Collections.emptySet();
        }
        HashSet hashSet = new HashSet();
        for (X509Certificate x509Certificate : acceptedIssuers) {
            if (x509Certificate != null) {
                hashSet.add(x509Certificate);
            }
        }
        return Collections.unmodifiableSet(hashSet);
    }

    @Override // javax.net.ssl.X509TrustManager
    public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        this.x509TrustManager.checkClientTrusted(copyChain(x509CertificateArr), str);
        checkAdditionalTrust(x509CertificateArr, str, null, false);
    }

    @Override // org.bouncycastle.jsse.BCX509ExtendedTrustManager
    public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str, Socket socket) throws CertificateException {
        this.x509TrustManager.checkClientTrusted(copyChain(x509CertificateArr), str);
        checkAdditionalTrust(x509CertificateArr, str, TransportData.from(socket), false);
    }

    @Override // org.bouncycastle.jsse.BCX509ExtendedTrustManager
    public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str, SSLEngine sSLEngine) throws CertificateException {
        this.x509TrustManager.checkClientTrusted(copyChain(x509CertificateArr), str);
        checkAdditionalTrust(x509CertificateArr, str, TransportData.from(sSLEngine), false);
    }

    @Override // javax.net.ssl.X509TrustManager
    public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        this.x509TrustManager.checkServerTrusted(copyChain(x509CertificateArr), str);
        checkAdditionalTrust(x509CertificateArr, str, null, true);
    }

    @Override // org.bouncycastle.jsse.BCX509ExtendedTrustManager
    public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str, Socket socket) throws CertificateException {
        this.x509TrustManager.checkServerTrusted(copyChain(x509CertificateArr), str);
        checkAdditionalTrust(x509CertificateArr, str, TransportData.from(socket), true);
    }

    @Override // org.bouncycastle.jsse.BCX509ExtendedTrustManager
    public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str, SSLEngine sSLEngine) throws CertificateException {
        this.x509TrustManager.checkServerTrusted(copyChain(x509CertificateArr), str);
        checkAdditionalTrust(x509CertificateArr, str, TransportData.from(sSLEngine), true);
    }

    @Override // javax.net.ssl.X509TrustManager
    public X509Certificate[] getAcceptedIssuers() {
        return this.x509TrustManager.getAcceptedIssuers();
    }

    @Override // org.bouncycastle.jsse.provider.ImportX509TrustManager
    public X509TrustManager unwrap() {
        return this.x509TrustManager;
    }
}
