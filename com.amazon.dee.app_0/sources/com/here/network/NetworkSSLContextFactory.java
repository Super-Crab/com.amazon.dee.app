package com.here.network;

import android.util.Log;
import com.amazon.whispercloak.KeyUtils;
import com.amazonaws.org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.util.ArrayList;
import java.util.List;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
/* loaded from: classes3.dex */
class NetworkSSLContextFactory {
    private static final String LOGTAG = "NetworkSSLContextFactory";
    private CertificateFactory m_certificateFactory;
    private String m_certificatesPath;
    private SSLContext m_sslContext;

    /* loaded from: classes3.dex */
    private static class LazyHolder {
        private static final NetworkSSLContextFactory INSTANCE = new NetworkSSLContextFactory();

        private LazyHolder() {
        }
    }

    private NetworkSSLContextFactory() {
        this.m_sslContext = null;
        this.m_certificatesPath = null;
        this.m_certificateFactory = null;
        try {
            this.m_certificateFactory = CertificateFactory.getInstance(KeyUtils.X509_CERITIFATE_FACTORY);
        } catch (Exception e) {
            GeneratedOutlineSupport1.outline156("X509 CertificateFactory failed to create", e, LOGTAG);
        }
    }

    private void generateSSLContext() {
        if (this.m_certificateFactory == null) {
            Log.w(LOGTAG, "generateSSLContext failed since certificateFactory is null");
            return;
        }
        try {
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null, null);
            List<File> listFiles = getListFiles(new File(this.m_certificatesPath));
            for (int i = 0; i < listFiles.size(); i++) {
                File file = listFiles.get(i);
                Certificate loadCertificate = loadCertificate(file);
                if (loadCertificate != null) {
                    keyStore.setCertificateEntry(file.getName(), loadCertificate);
                } else {
                    Log.e(LOGTAG, "invalid certificate file " + file.getName());
                }
            }
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(keyStore);
            SSLContext sSLContext = SSLContext.getInstance(SSLSocketFactoryFactory.DEFAULT_PROTOCOL);
            this.m_sslContext = sSLContext;
            sSLContext.init(null, trustManagerFactory.getTrustManagers(), null);
        } catch (Exception e) {
            GeneratedOutlineSupport1.outline156("generateSSLContext: Failed to generate ssl context: ", e, LOGTAG);
        }
    }

    public static NetworkSSLContextFactory getInstance() {
        return LazyHolder.INSTANCE;
    }

    private List<File> getListFiles(File file) {
        File[] listFiles;
        ArrayList arrayList = new ArrayList();
        for (File file2 : file.listFiles()) {
            if (file2.isDirectory()) {
                arrayList.addAll(getListFiles(file2));
            } else if (file2.length() > 1) {
                arrayList.add(file2);
            }
        }
        return arrayList;
    }

    private Certificate loadCertificate(File file) {
        StringBuilder sb;
        if (file.exists()) {
            try {
                BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
                Certificate generateCertificate = this.m_certificateFactory.generateCertificate(bufferedInputStream);
                bufferedInputStream.close();
                return generateCertificate;
            } catch (Exception e) {
                sb = new StringBuilder();
                sb.append("Load certificate failed ");
                sb.append(e);
            }
        } else {
            sb = GeneratedOutlineSupport1.outline107("certificate file ");
            sb.append(file.getName());
            sb.append("does not exist");
        }
        Log.e(LOGTAG, sb.toString());
        return null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x0023, code lost:
        if (r2.m_certificatesPath.compareToIgnoreCase(r3) != 0) goto L16;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public javax.net.ssl.SSLContext getSSLContext(java.lang.String r3) {
        /*
            r2 = this;
            r0 = 0
            if (r3 != 0) goto Lb
            java.lang.String r3 = "NetworkSSLContextFactory"
            java.lang.String r1 = "getSSLContext certificatesPath is null"
        L7:
            android.util.Log.w(r3, r1)
            return r0
        Lb:
            boolean r1 = r3.isEmpty()
            if (r1 == 0) goto L16
            java.lang.String r3 = "NetworkSSLContextFactory"
            java.lang.String r1 = "getSSLContext certificatesPath is empty"
            goto L7
        L16:
            monitor-enter(r2)
            r0 = 0
            java.lang.String r1 = r2.m_certificatesPath     // Catch: java.lang.Throwable -> L31
            if (r1 != 0) goto L1d
            goto L25
        L1d:
            java.lang.String r1 = r2.m_certificatesPath     // Catch: java.lang.Throwable -> L31
            int r1 = r1.compareToIgnoreCase(r3)     // Catch: java.lang.Throwable -> L31
            if (r1 == 0) goto L26
        L25:
            r0 = 1
        L26:
            if (r0 == 0) goto L2d
            r2.m_certificatesPath = r3     // Catch: java.lang.Throwable -> L31
            r2.generateSSLContext()     // Catch: java.lang.Throwable -> L31
        L2d:
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L31
            javax.net.ssl.SSLContext r3 = r2.m_sslContext
            return r3
        L31:
            r3 = move-exception
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L31
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.here.network.NetworkSSLContextFactory.getSSLContext(java.lang.String):javax.net.ssl.SSLContext");
    }
}
