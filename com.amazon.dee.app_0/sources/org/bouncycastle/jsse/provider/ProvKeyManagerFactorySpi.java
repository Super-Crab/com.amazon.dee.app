package org.bouncycastle.jsse.provider;

import com.amazonaws.org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;
import java.util.logging.Logger;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactorySpi;
import javax.net.ssl.KeyStoreBuilderParameters;
import javax.net.ssl.ManagerFactoryParameters;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.jsse.BCX509ExtendedKeyManager;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes4.dex */
public class ProvKeyManagerFactorySpi extends KeyManagerFactorySpi {
    private static final Logger LOG = Logger.getLogger(ProvKeyManagerFactorySpi.class.getName());
    protected final JcaJceHelper helper;
    protected BCX509ExtendedKeyManager x509KeyManager;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ProvKeyManagerFactorySpi(JcaJceHelper jcaJceHelper) {
        this.helper = jcaJceHelper;
    }

    private static KeyStore createKeyStore(String str) throws NoSuchProviderException, KeyStoreException {
        String keyStoreType = getKeyStoreType(str);
        String systemProperty = PropertyUtils.getSystemProperty("javax.net.ssl.keyStoreProvider");
        return (systemProperty == null || systemProperty.length() < 1) ? KeyStore.getInstance(keyStoreType) : KeyStore.getInstance(keyStoreType, systemProperty);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static KeyStoreConfig getDefaultKeyStore() throws Exception {
        String defaultType = KeyStore.getDefaultType();
        String systemProperty = PropertyUtils.getSystemProperty(SSLSocketFactoryFactory.SYSKEYSTORE);
        BufferedInputStream bufferedInputStream = null;
        if ("NONE".equals(systemProperty) || systemProperty == null || !new File(systemProperty).exists()) {
            systemProperty = null;
        }
        KeyStore createKeyStore = createKeyStore(defaultType);
        String systemProperty2 = PropertyUtils.getSystemProperty(SSLSocketFactoryFactory.SYSKEYSTOREPWD);
        char[] charArray = systemProperty2 != null ? systemProperty2.toCharArray() : null;
        try {
            if (systemProperty == null) {
                LOG.info("Initializing empty key store");
            } else {
                LOG.info("Initializing with key store at path: " + systemProperty);
                bufferedInputStream = new BufferedInputStream(new FileInputStream(systemProperty));
            }
            createKeyStore.load(bufferedInputStream, charArray);
            return new KeyStoreConfig(createKeyStore, charArray);
        } finally {
            if (bufferedInputStream != null) {
                bufferedInputStream.close();
            }
        }
    }

    private static String getKeyStoreType(String str) {
        String systemProperty = PropertyUtils.getSystemProperty(SSLSocketFactoryFactory.SYSKEYSTORETYPE);
        return systemProperty == null ? str : systemProperty;
    }

    @Override // javax.net.ssl.KeyManagerFactorySpi
    protected KeyManager[] engineGetKeyManagers() {
        BCX509ExtendedKeyManager bCX509ExtendedKeyManager = this.x509KeyManager;
        if (bCX509ExtendedKeyManager != null) {
            return new KeyManager[]{bCX509ExtendedKeyManager};
        }
        throw new IllegalStateException("KeyManagerFactory not initialized");
    }

    @Override // javax.net.ssl.KeyManagerFactorySpi
    protected void engineInit(KeyStore keyStore, char[] cArr) throws KeyStoreException, NoSuchAlgorithmException, UnrecoverableKeyException {
        this.x509KeyManager = new ProvX509KeyManagerSimple(this.helper, keyStore, cArr);
    }

    @Override // javax.net.ssl.KeyManagerFactorySpi
    protected void engineInit(ManagerFactoryParameters managerFactoryParameters) throws InvalidAlgorithmParameterException {
        if (managerFactoryParameters instanceof KeyStoreBuilderParameters) {
            this.x509KeyManager = new ProvX509KeyManager(this.helper, ((KeyStoreBuilderParameters) managerFactoryParameters).getParameters());
            return;
        }
        throw new InvalidAlgorithmParameterException("Parameters must be instance of KeyStoreBuilderParameters");
    }
}
