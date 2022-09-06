package com.amazonaws.mobileconnectors.iot;

import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
/* loaded from: classes13.dex */
final class AWSIotSslUtility {
    private AWSIotSslUtility() {
    }

    public static SSLSocketFactory getSocketFactoryWithKeyStore(KeyStore keyStore) throws NoSuchAlgorithmException, UnrecoverableKeyException, KeyStoreException, KeyManagementException {
        SSLContext sSLContext = SSLContext.getInstance("TLSv1.2");
        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        keyManagerFactory.init(keyStore, AWSIotKeystoreHelper.AWS_IOT_INTERNAL_KEYSTORE_PASSWORD.toCharArray());
        sSLContext.init(keyManagerFactory.getKeyManagers(), null, new SecureRandom());
        return new AWSIotTLSSocketFactory(sSLContext.getSocketFactory());
    }
}
