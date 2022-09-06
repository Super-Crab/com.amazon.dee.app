package org.apache.thrift.orig.transport;

import com.amazonaws.org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.FileInputStream;
import java.net.InetAddress;
import java.security.KeyStore;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;
/* loaded from: classes4.dex */
public class TSSLTransportFactory {
    private static TSocket createClient(SSLSocketFactory sSLSocketFactory, String str, int i, int i2) throws TTransportException {
        try {
            SSLSocket sSLSocket = (SSLSocket) sSLSocketFactory.createSocket(str, i);
            sSLSocket.setSoTimeout(i2);
            return new TSocket(sSLSocket);
        } catch (Exception e) {
            throw new TTransportException("Could not connect to " + str + " on port " + i, e);
        }
    }

    private static SSLContext createSSLContext(TSSLTransportParameters tSSLTransportParameters) throws TTransportException {
        TrustManagerFactory trustManagerFactory;
        KeyManagerFactory keyManagerFactory;
        try {
            SSLContext sSLContext = SSLContext.getInstance(tSSLTransportParameters.protocol);
            if (tSSLTransportParameters.isTrustStoreSet) {
                trustManagerFactory = TrustManagerFactory.getInstance(tSSLTransportParameters.trustManagerType);
                KeyStore keyStore = KeyStore.getInstance(tSSLTransportParameters.trustStoreType);
                keyStore.load(new FileInputStream(tSSLTransportParameters.trustStore), tSSLTransportParameters.trustPass.toCharArray());
                trustManagerFactory.init(keyStore);
            } else {
                trustManagerFactory = null;
            }
            if (tSSLTransportParameters.isKeyStoreSet) {
                keyManagerFactory = KeyManagerFactory.getInstance(tSSLTransportParameters.keyManagerType);
                KeyStore keyStore2 = KeyStore.getInstance(tSSLTransportParameters.keyStoreType);
                keyStore2.load(new FileInputStream(tSSLTransportParameters.keyStore), tSSLTransportParameters.keyPass.toCharArray());
                keyManagerFactory.init(keyStore2, tSSLTransportParameters.keyPass.toCharArray());
            } else {
                keyManagerFactory = null;
            }
            if (tSSLTransportParameters.isKeyStoreSet && tSSLTransportParameters.isTrustStoreSet) {
                sSLContext.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), null);
            } else if (tSSLTransportParameters.isKeyStoreSet) {
                sSLContext.init(keyManagerFactory.getKeyManagers(), null, null);
            } else {
                sSLContext.init(null, trustManagerFactory.getTrustManagers(), null);
            }
            return sSLContext;
        } catch (Exception e) {
            throw new TTransportException("Error creating the transport", e);
        }
    }

    private static TServerSocket createServer(SSLServerSocketFactory sSLServerSocketFactory, int i, int i2, boolean z, InetAddress inetAddress, TSSLTransportParameters tSSLTransportParameters) throws TTransportException {
        String[] strArr;
        try {
            SSLServerSocket sSLServerSocket = (SSLServerSocket) sSLServerSocketFactory.createServerSocket(i, 100, inetAddress);
            sSLServerSocket.setSoTimeout(i2);
            sSLServerSocket.setNeedClientAuth(z);
            if (tSSLTransportParameters != null && (strArr = tSSLTransportParameters.cipherSuites) != null) {
                sSLServerSocket.setEnabledCipherSuites(strArr);
            }
            return new TServerSocket(sSLServerSocket, i2);
        } catch (Exception e) {
            throw new TTransportException(GeneratedOutlineSupport1.outline49("Could not bind to port ", i), e);
        }
    }

    public static TSocket getClientSocket(String str, int i, int i2) throws TTransportException {
        return createClient((SSLSocketFactory) SSLSocketFactory.getDefault(), str, i, i2);
    }

    public static TServerSocket getServerSocket(int i) throws TTransportException {
        return getServerSocket(i, 0);
    }

    public static TServerSocket getServerSocket(int i, int i2) throws TTransportException {
        return getServerSocket(i, i2, false, (InetAddress) null);
    }

    public static TSocket getClientSocket(String str, int i) throws TTransportException {
        return getClientSocket(str, i, 0);
    }

    public static TServerSocket getServerSocket(int i, int i2, boolean z, InetAddress inetAddress) throws TTransportException {
        return createServer((SSLServerSocketFactory) SSLServerSocketFactory.getDefault(), i, i2, z, inetAddress, null);
    }

    public static TSocket getClientSocket(String str, int i, int i2, TSSLTransportParameters tSSLTransportParameters) throws TTransportException {
        if (tSSLTransportParameters != null && (tSSLTransportParameters.isKeyStoreSet || tSSLTransportParameters.isTrustStoreSet)) {
            return createClient(createSSLContext(tSSLTransportParameters).getSocketFactory(), str, i, i2);
        }
        throw new TTransportException("Either one of the KeyStore or TrustStore must be set for SSLTransportParameters");
    }

    /* loaded from: classes4.dex */
    public static class TSSLTransportParameters {
        protected String[] cipherSuites;
        protected boolean clientAuth;
        protected boolean isKeyStoreSet;
        protected boolean isTrustStoreSet;
        protected String keyManagerType;
        protected String keyPass;
        protected String keyStore;
        protected String keyStoreType;
        protected String protocol;
        protected String trustManagerType;
        protected String trustPass;
        protected String trustStore;
        protected String trustStoreType;

        public TSSLTransportParameters() {
            this.protocol = SSLSocketFactoryFactory.DEFAULT_PROTOCOL;
            this.keyManagerType = KeyManagerFactory.getDefaultAlgorithm();
            this.keyStoreType = "JKS";
            this.trustManagerType = TrustManagerFactory.getDefaultAlgorithm();
            this.trustStoreType = "JKS";
            this.clientAuth = false;
            this.isKeyStoreSet = false;
            this.isTrustStoreSet = false;
        }

        public void requireClientAuth(boolean z) {
            this.clientAuth = z;
        }

        public void setKeyStore(String str, String str2, String str3, String str4) {
            this.keyStore = str;
            this.keyPass = str2;
            if (str3 != null) {
                this.keyManagerType = str3;
            }
            if (str4 != null) {
                this.keyStoreType = str4;
            }
            this.isKeyStoreSet = true;
        }

        public void setTrustStore(String str, String str2, String str3, String str4) {
            this.trustStore = str;
            this.trustPass = str2;
            if (str3 != null) {
                this.trustManagerType = str3;
            }
            if (str4 != null) {
                this.trustStoreType = str4;
            }
            this.isTrustStoreSet = true;
        }

        public void setKeyStore(String str, String str2) {
            setKeyStore(str, str2, null, null);
        }

        public void setTrustStore(String str, String str2) {
            setTrustStore(str, str2, null, null);
        }

        public TSSLTransportParameters(String str, String[] strArr) {
            this(str, strArr, false);
        }

        public TSSLTransportParameters(String str, String[] strArr, boolean z) {
            this.protocol = SSLSocketFactoryFactory.DEFAULT_PROTOCOL;
            this.keyManagerType = KeyManagerFactory.getDefaultAlgorithm();
            this.keyStoreType = "JKS";
            this.trustManagerType = TrustManagerFactory.getDefaultAlgorithm();
            this.trustStoreType = "JKS";
            this.clientAuth = false;
            this.isKeyStoreSet = false;
            this.isTrustStoreSet = false;
            if (str != null) {
                this.protocol = str;
            }
            this.cipherSuites = strArr;
            this.clientAuth = z;
        }
    }

    public static TServerSocket getServerSocket(int i, int i2, InetAddress inetAddress, TSSLTransportParameters tSSLTransportParameters) throws TTransportException {
        if (tSSLTransportParameters != null && (tSSLTransportParameters.isKeyStoreSet || tSSLTransportParameters.isTrustStoreSet)) {
            return createServer(createSSLContext(tSSLTransportParameters).getServerSocketFactory(), i, i2, tSSLTransportParameters.clientAuth, inetAddress, tSSLTransportParameters);
        }
        throw new TTransportException("Either one of the KeyStore or TrustStore must be set for SSLTransportParameters");
    }
}
