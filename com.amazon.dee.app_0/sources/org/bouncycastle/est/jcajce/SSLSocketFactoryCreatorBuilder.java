package org.bouncycastle.est.jcajce;

import com.amazonaws.org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.Security;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
/* loaded from: classes4.dex */
class SSLSocketFactoryCreatorBuilder {
    protected KeyManager[] keyManagers;
    protected SecureRandom secureRandom;
    protected Provider tlsProvider;
    protected String tlsVersion = SSLSocketFactoryFactory.DEFAULT_PROTOCOL;
    protected X509TrustManager[] trustManagers;

    public SSLSocketFactoryCreatorBuilder(X509TrustManager x509TrustManager) {
        if (x509TrustManager != null) {
            this.trustManagers = new X509TrustManager[]{x509TrustManager};
            return;
        }
        throw new NullPointerException("Trust managers can not be null");
    }

    public SSLSocketFactoryCreatorBuilder(X509TrustManager[] x509TrustManagerArr) {
        if (x509TrustManagerArr != null) {
            this.trustManagers = x509TrustManagerArr;
            return;
        }
        throw new NullPointerException("Trust managers can not be null");
    }

    public SSLSocketFactoryCreator build() {
        return new SSLSocketFactoryCreator() { // from class: org.bouncycastle.est.jcajce.SSLSocketFactoryCreatorBuilder.1
            @Override // org.bouncycastle.est.jcajce.SSLSocketFactoryCreator
            public SSLSocketFactory createFactory() throws NoSuchAlgorithmException, NoSuchProviderException, KeyManagementException {
                SSLSocketFactoryCreatorBuilder sSLSocketFactoryCreatorBuilder = SSLSocketFactoryCreatorBuilder.this;
                Provider provider = sSLSocketFactoryCreatorBuilder.tlsProvider;
                String str = sSLSocketFactoryCreatorBuilder.tlsVersion;
                SSLContext sSLContext = provider != null ? SSLContext.getInstance(str, provider) : SSLContext.getInstance(str);
                SSLSocketFactoryCreatorBuilder sSLSocketFactoryCreatorBuilder2 = SSLSocketFactoryCreatorBuilder.this;
                sSLContext.init(sSLSocketFactoryCreatorBuilder2.keyManagers, sSLSocketFactoryCreatorBuilder2.trustManagers, sSLSocketFactoryCreatorBuilder2.secureRandom);
                return sSLContext.getSocketFactory();
            }

            @Override // org.bouncycastle.est.jcajce.SSLSocketFactoryCreator
            public boolean isTrusted() {
                int i = 0;
                while (true) {
                    X509TrustManager[] x509TrustManagerArr = SSLSocketFactoryCreatorBuilder.this.trustManagers;
                    if (i != x509TrustManagerArr.length) {
                        if (x509TrustManagerArr[i].getAcceptedIssuers().length > 0) {
                            return true;
                        }
                        i++;
                    } else {
                        return false;
                    }
                }
            }
        };
    }

    public SSLSocketFactoryCreatorBuilder withKeyManager(KeyManager keyManager) {
        if (keyManager == null) {
            this.keyManagers = null;
        } else {
            this.keyManagers = new KeyManager[]{keyManager};
        }
        return this;
    }

    public SSLSocketFactoryCreatorBuilder withKeyManagers(KeyManager[] keyManagerArr) {
        this.keyManagers = keyManagerArr;
        return this;
    }

    public SSLSocketFactoryCreatorBuilder withProvider(String str) throws NoSuchProviderException {
        this.tlsProvider = Security.getProvider(str);
        if (this.tlsProvider != null) {
            return this;
        }
        throw new NoSuchProviderException(GeneratedOutlineSupport1.outline72("JSSE provider not found: ", str));
    }

    public SSLSocketFactoryCreatorBuilder withProvider(Provider provider) {
        this.tlsProvider = provider;
        return this;
    }

    public SSLSocketFactoryCreatorBuilder withSecureRandom(SecureRandom secureRandom) {
        this.secureRandom = secureRandom;
        return this;
    }

    public SSLSocketFactoryCreatorBuilder withTLSVersion(String str) {
        this.tlsVersion = str;
        return this;
    }
}
