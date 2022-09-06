package org.bouncycastle.jsse.provider;

import java.security.KeyManagementException;
import java.security.SecureRandom;
import javax.net.ssl.KeyManager;
import javax.net.ssl.TrustManager;
import org.bouncycastle.tls.crypto.impl.jcajce.JcaTlsCryptoProvider;
/* loaded from: classes4.dex */
class DefaultSSLContextSpi extends ProvSSLContextSpi {

    /* loaded from: classes4.dex */
    private static class LazyInstance {
        private static final Exception initException;
        private static final DefaultSSLContextSpi instance;

        static {
            Exception e = LazyManagers.initException;
            DefaultSSLContextSpi defaultSSLContextSpi = null;
            if (e == null) {
                try {
                    defaultSSLContextSpi = new DefaultSSLContextSpi(false, new JcaTlsCryptoProvider());
                } catch (Exception e2) {
                    e = e2;
                }
            }
            initException = e;
            instance = defaultSSLContextSpi;
        }

        private LazyInstance() {
        }
    }

    /* loaded from: classes4.dex */
    private static class LazyManagers {
        private static final Exception initException;
        private static final KeyManager[] keyManagers;
        private static final TrustManager[] trustManagers;

        static {
            TrustManager[] trustManagerArr;
            KeyManager[] keyManagerArr;
            Exception exc = null;
            try {
                keyManagerArr = ProvSSLContextSpi.getDefaultKeyManagers();
                trustManagerArr = ProvSSLContextSpi.getDefaultTrustManagers();
            } catch (Exception e) {
                trustManagerArr = null;
                exc = e;
                keyManagerArr = null;
            }
            initException = exc;
            keyManagers = keyManagerArr;
            trustManagers = trustManagerArr;
        }

        private LazyManagers() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DefaultSSLContextSpi(boolean z, JcaTlsCryptoProvider jcaTlsCryptoProvider) throws KeyManagementException {
        super(z, jcaTlsCryptoProvider, null);
        if (LazyManagers.initException == null) {
            super.engineInit(LazyManagers.keyManagers, LazyManagers.trustManagers, null);
            return;
        }
        throw new KeyManagementException("Default key/trust managers unavailable", LazyManagers.initException);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ProvSSLContextSpi getDefaultInstance() throws Exception {
        if (LazyInstance.initException == null) {
            return LazyInstance.instance;
        }
        throw LazyInstance.initException;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.bouncycastle.jsse.provider.ProvSSLContextSpi, javax.net.ssl.SSLContextSpi
    public void engineInit(KeyManager[] keyManagerArr, TrustManager[] trustManagerArr, SecureRandom secureRandom) throws KeyManagementException {
        throw new KeyManagementException("Default SSLContext is initialized automatically");
    }
}
