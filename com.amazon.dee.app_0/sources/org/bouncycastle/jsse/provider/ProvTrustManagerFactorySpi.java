package org.bouncycastle.jsse.provider;

import com.amazonaws.org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchProviderException;
import java.security.cert.CertPathParameters;
import java.security.cert.Certificate;
import java.security.cert.PKIXParameters;
import java.security.cert.TrustAnchor;
import java.security.cert.X509Certificate;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.CertPathTrustManagerParameters;
import javax.net.ssl.ManagerFactoryParameters;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactorySpi;
import org.bouncycastle.jcajce.util.JcaJceHelper;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes4.dex */
public class ProvTrustManagerFactorySpi extends TrustManagerFactorySpi {
    private static final Logger LOG = Logger.getLogger(ProvTrustManagerFactorySpi.class.getName());
    private static final boolean provKeyStoreTypeCompat = PropertyUtils.getBooleanSecurityProperty("keystore.type.compat", false);
    protected final JcaJceHelper helper;
    protected ProvX509TrustManager x509TrustManager;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ProvTrustManagerFactorySpi(JcaJceHelper jcaJceHelper) {
        this.helper = jcaJceHelper;
    }

    private static void collectTrustAnchor(Set<TrustAnchor> set, Certificate certificate) {
        if (certificate instanceof X509Certificate) {
            set.add(new TrustAnchor((X509Certificate) certificate, null));
        }
    }

    private static KeyStore createTrustStore(String str) throws NoSuchProviderException, KeyStoreException {
        String trustStoreType = getTrustStoreType(str);
        String systemProperty = PropertyUtils.getSystemProperty("javax.net.ssl.trustStoreProvider");
        return (systemProperty == null || systemProperty.length() < 1) ? KeyStore.getInstance(trustStoreType) : KeyStore.getInstance(trustStoreType, systemProperty);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x002e, code lost:
        if (new java.io.File(r2).exists() != false) goto L11;
     */
    /* JADX WARN: Removed duplicated region for block: B:29:0x008f  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0094  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0097 A[Catch: all -> 0x00c9, TRY_ENTER, TryCatch #0 {all -> 0x00c9, blocks: (B:32:0x0097, B:34:0x00c0, B:33:0x009f), top: B:42:0x0095 }] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x009f A[Catch: all -> 0x00c9, TryCatch #0 {all -> 0x00c9, blocks: (B:32:0x0097, B:34:0x00c0, B:33:0x009f), top: B:42:0x0095 }] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00c5 A[DONT_GENERATE] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.security.KeyStore getDefaultTrustStore() throws java.lang.Exception {
        /*
            java.lang.String r0 = java.security.KeyStore.getDefaultType()
            boolean r1 = org.bouncycastle.jsse.provider.ProvTrustManagerFactorySpi.provKeyStoreTypeCompat
            if (r1 == 0) goto L12
            java.lang.String r1 = "pkcs12"
            boolean r1 = r1.equalsIgnoreCase(r0)
            if (r1 == 0) goto L12
            r1 = 1
            goto L13
        L12:
            r1 = 0
        L13:
            java.lang.String r2 = "javax.net.ssl.trustStore"
            java.lang.String r2 = org.bouncycastle.jsse.provider.PropertyUtils.getSystemProperty(r2)
            java.lang.String r3 = "NONE"
            boolean r3 = r3.equals(r2)
            r4 = 0
            if (r3 == 0) goto L23
            goto L82
        L23:
            if (r2 == 0) goto L31
            java.io.File r1 = new java.io.File
            r1.<init>(r2)
            boolean r1 = r1.exists()
            if (r1 == 0) goto L82
            goto L83
        L31:
            java.lang.String r2 = "java.home"
            java.lang.String r2 = org.bouncycastle.jsse.provider.PropertyUtils.getSystemProperty(r2)
            if (r2 == 0) goto L82
            java.lang.StringBuilder r3 = com.android.tools.r8.GeneratedOutlineSupport1.outline107(r2)
            java.lang.String r5 = java.io.File.separator
            java.lang.String r6 = "/"
            java.lang.String r7 = "/lib/security/jssecacerts"
            java.lang.String r5 = r7.replace(r6, r5)
            r3.append(r5)
            java.lang.String r3 = r3.toString()
            java.io.File r5 = new java.io.File
            r5.<init>(r3)
            boolean r5 = r5.exists()
            java.lang.String r7 = "jks"
            if (r5 == 0) goto L60
            if (r1 == 0) goto L5e
            r0 = r7
        L5e:
            r2 = r3
            goto L83
        L60:
            java.lang.StringBuilder r2 = com.android.tools.r8.GeneratedOutlineSupport1.outline107(r2)
            java.lang.String r3 = java.io.File.separator
            java.lang.String r5 = "/lib/security/cacerts"
            java.lang.String r3 = r5.replace(r6, r3)
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            java.io.File r3 = new java.io.File
            r3.<init>(r2)
            boolean r3 = r3.exists()
            if (r3 == 0) goto L82
            if (r1 == 0) goto L83
            r0 = r7
            goto L83
        L82:
            r2 = r4
        L83:
            java.security.KeyStore r0 = createTrustStore(r0)
            java.lang.String r1 = "javax.net.ssl.trustStorePassword"
            java.lang.String r1 = org.bouncycastle.jsse.provider.PropertyUtils.getSystemProperty(r1)
            if (r1 == 0) goto L94
            char[] r1 = r1.toCharArray()
            goto L95
        L94:
            r1 = r4
        L95:
            if (r2 != 0) goto L9f
            java.util.logging.Logger r2 = org.bouncycastle.jsse.provider.ProvTrustManagerFactorySpi.LOG     // Catch: java.lang.Throwable -> Lc9
            java.lang.String r3 = "Initializing empty trust store"
            r2.info(r3)     // Catch: java.lang.Throwable -> Lc9
            goto Lc0
        L9f:
            java.util.logging.Logger r3 = org.bouncycastle.jsse.provider.ProvTrustManagerFactorySpi.LOG     // Catch: java.lang.Throwable -> Lc9
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lc9
            r5.<init>()     // Catch: java.lang.Throwable -> Lc9
            java.lang.String r6 = "Initializing with trust store at path: "
            r5.append(r6)     // Catch: java.lang.Throwable -> Lc9
            r5.append(r2)     // Catch: java.lang.Throwable -> Lc9
            java.lang.String r5 = r5.toString()     // Catch: java.lang.Throwable -> Lc9
            r3.info(r5)     // Catch: java.lang.Throwable -> Lc9
            java.io.BufferedInputStream r3 = new java.io.BufferedInputStream     // Catch: java.lang.Throwable -> Lc9
            java.io.FileInputStream r5 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> Lc9
            r5.<init>(r2)     // Catch: java.lang.Throwable -> Lc9
            r3.<init>(r5)     // Catch: java.lang.Throwable -> Lc9
            r4 = r3
        Lc0:
            r0.load(r4, r1)     // Catch: java.lang.Throwable -> Lc9
            if (r4 == 0) goto Lc8
            r4.close()
        Lc8:
            return r0
        Lc9:
            r0 = move-exception
            if (r4 == 0) goto Lcf
            r4.close()
        Lcf:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.jsse.provider.ProvTrustManagerFactorySpi.getDefaultTrustStore():java.security.KeyStore");
    }

    private static Set<TrustAnchor> getTrustAnchors(KeyStore keyStore) throws KeyStoreException {
        Certificate certificate;
        Certificate[] certificateChain;
        if (keyStore == null) {
            return Collections.emptySet();
        }
        HashSet hashSet = new HashSet();
        Enumeration<String> aliases = keyStore.aliases();
        while (aliases.hasMoreElements()) {
            String nextElement = aliases.nextElement();
            if (keyStore.isCertificateEntry(nextElement)) {
                certificate = keyStore.getCertificate(nextElement);
            } else if (keyStore.isKeyEntry(nextElement) && (certificateChain = keyStore.getCertificateChain(nextElement)) != null && certificateChain.length > 0) {
                certificate = certificateChain[0];
            }
            collectTrustAnchor(hashSet, certificate);
        }
        return hashSet;
    }

    private static String getTrustStoreType(String str) {
        String systemProperty = PropertyUtils.getSystemProperty(SSLSocketFactoryFactory.SYSTRUSTSTORETYPE);
        return systemProperty == null ? str : systemProperty;
    }

    @Override // javax.net.ssl.TrustManagerFactorySpi
    protected TrustManager[] engineGetTrustManagers() {
        ProvX509TrustManager provX509TrustManager = this.x509TrustManager;
        if (provX509TrustManager != null) {
            return new TrustManager[]{provX509TrustManager.getExportX509TrustManager()};
        }
        throw new IllegalStateException("TrustManagerFactory not initialized");
    }

    @Override // javax.net.ssl.TrustManagerFactorySpi
    protected void engineInit(KeyStore keyStore) throws KeyStoreException {
        if (keyStore == null) {
            try {
                keyStore = getDefaultTrustStore();
            } catch (Error e) {
                LOG.log(Level.WARNING, "Skipped default trust store", (Throwable) e);
                throw e;
            } catch (SecurityException e2) {
                LOG.log(Level.WARNING, "Skipped default trust store", (Throwable) e2);
            } catch (RuntimeException e3) {
                LOG.log(Level.WARNING, "Skipped default trust store", (Throwable) e3);
                throw e3;
            } catch (Exception e4) {
                LOG.log(Level.WARNING, "Skipped default trust store", (Throwable) e4);
                throw new KeyStoreException("Failed to load default��trust store", e4);
            }
        }
        try {
            this.x509TrustManager = new ProvX509TrustManager(this.helper, getTrustAnchors(keyStore));
        } catch (InvalidAlgorithmParameterException e5) {
            throw new KeyStoreException("Failed to create trust manager", e5);
        }
    }

    @Override // javax.net.ssl.TrustManagerFactorySpi
    protected void engineInit(ManagerFactoryParameters managerFactoryParameters) throws InvalidAlgorithmParameterException {
        if (managerFactoryParameters instanceof CertPathTrustManagerParameters) {
            CertPathParameters parameters = ((CertPathTrustManagerParameters) managerFactoryParameters).getParameters();
            if (!(parameters instanceof PKIXParameters)) {
                throw new InvalidAlgorithmParameterException("parameters must inherit from PKIXParameters");
            }
            this.x509TrustManager = new ProvX509TrustManager(this.helper, (PKIXParameters) parameters);
        } else if (managerFactoryParameters == null) {
            throw new InvalidAlgorithmParameterException("spec cannot be null");
        } else {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("unknown spec: ");
            outline107.append(managerFactoryParameters.getClass().getName());
            throw new InvalidAlgorithmParameterException(outline107.toString());
        }
    }
}
