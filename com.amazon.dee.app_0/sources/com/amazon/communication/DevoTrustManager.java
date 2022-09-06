package com.amazon.communication;

import android.os.Build;
import com.amazon.dp.logger.DPFormattedMessage;
import com.amazon.dp.logger.DPLogger;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
/* loaded from: classes12.dex */
public class DevoTrustManager implements X509TrustManager {
    private static final DPLogger log = new DPLogger("TComm.DevoTrustManager");
    protected X509TrustManager mTrustManager;

    public DevoTrustManager() throws Exception {
        this.mTrustManager = null;
        int i = 0;
        if (Build.TYPE.equals("eng")) {
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(keyStore);
            TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
            if (trustManagers != null) {
                int length = trustManagers.length;
                while (true) {
                    if (i >= length) {
                        break;
                    }
                    TrustManager trustManager = trustManagers[i];
                    if (trustManager instanceof X509TrustManager) {
                        this.mTrustManager = (X509TrustManager) trustManager;
                        break;
                    }
                    i++;
                }
            }
            if (this.mTrustManager == null) {
                throw new IllegalStateException("Could not find default TrustManager");
            }
            return;
        }
        throw new IllegalStateException(DPFormattedMessage.toDPFormat("constructor", "DevoTrustManager can only be used with eng builds", "build type", Build.TYPE));
    }

    @Override // javax.net.ssl.X509TrustManager
    public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        try {
            this.mTrustManager.checkClientTrusted(x509CertificateArr, str);
        } catch (CertificateException unused) {
            log.warn("checkClientTrusted", "passing through non-trusted certificate", "first certificate in chain", x509CertificateArr[0]);
        }
    }

    @Override // javax.net.ssl.X509TrustManager
    public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        try {
            this.mTrustManager.checkServerTrusted(x509CertificateArr, str);
        } catch (CertificateException unused) {
            log.warn("checkServerTrusted", "passing through non-trusted certificate", "first certificate in chain", x509CertificateArr[0]);
        }
    }

    @Override // javax.net.ssl.X509TrustManager
    public X509Certificate[] getAcceptedIssuers() {
        return this.mTrustManager.getAcceptedIssuers();
    }
}
