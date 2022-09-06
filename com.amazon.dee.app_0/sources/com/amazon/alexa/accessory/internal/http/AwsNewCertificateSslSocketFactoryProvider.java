package com.amazon.alexa.accessory.internal.http;

import android.content.Context;
import android.os.Build;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessory.R;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.internal.util.WrapperUtil;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
/* loaded from: classes.dex */
public class AwsNewCertificateSslSocketFactoryProvider implements SslSocketFactoryProvider {
    private static final String CERT_PREFIX = "AmazonCA";
    private static final String TAG = "AwsNewCertificateSslSocketFactoryProvider:";
    private static final String TLS = "TLS";
    static final List<Integer> TRUSTED_AWS_CERTS_RESOURCE_IDS = Collections.unmodifiableList(Arrays.asList(Integer.valueOf(R.raw.amazon_root_ca1_cer), Integer.valueOf(R.raw.amazon_root_ca2_cer), Integer.valueOf(R.raw.amazon_root_ca3_cer), Integer.valueOf(R.raw.amazon_root_ca4_cer)));
    private static final String X_509 = "X.509";
    private WrapperUtil.Supplier<CertificateFactory, CertificateException> certificateFactorySupplier;
    private WrapperUtil.TwoParamedSupplier<Certificate, CertificateFactory, InputStream, CertificateException> certificateGenerator;
    @RequiresApi(api = 24)
    private WrapperUtil.ParamedSupplier<CompositeX509ExtendedTrustManager, CompositeX509TrustManager, Exception> compositeX509ExtendedTrustManagerInstantiator;
    private WrapperUtil.ParamedSupplier<CompositeX509TrustManager, List<X509TrustManager>, Exception> compositeX509TrustManagerInstantiator;
    private final Context context;
    private WrapperUtil.ParamedSupplier<SSLSocketFactory, SSLContext, Exception> getSslSocketFactoryWrapper;
    private WrapperUtil.ParamedSupplier<TrustManager[], TrustManagerFactory, Exception> getTrustManagersWrapper;
    private WrapperUtil.ParamedSupplier<KeyStore, String, KeyStoreException> keyStoreInstanceSupplier;
    private KeyStoreLoadWrapper keyStoreLoadWrapper;
    private KeyStoreSetCertificateEntryWrapper keyStoreSetCertificateEntryWrapper;
    private SslContextInitWrapper sslContextInitWrapper;
    private WrapperUtil.ParamedSupplier<SSLContext, String, NoSuchAlgorithmException> sslContextInstanceSupplier;
    private TrustManagerFactoryInitWrapper trustManagerFactoryInitWrapper;
    private WrapperUtil.ParamedSupplier<TrustManagerFactory, String, NoSuchAlgorithmException> trustManagerFactoryInstanceSupplier;
    private final Object lock = new Object();
    private boolean isInitialized = false;
    private SSLSocketFactory socketFactory = null;

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    /* loaded from: classes.dex */
    public interface KeyStoreLoadWrapper {
        void load(KeyStore keyStore, InputStream inputStream, char[] cArr) throws CertificateException, IOException, NoSuchAlgorithmException;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    /* loaded from: classes.dex */
    public interface KeyStoreSetCertificateEntryWrapper {
        void setCertificateEntry(KeyStore keyStore, String str, Certificate certificate) throws KeyStoreException;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    /* loaded from: classes.dex */
    public interface SslContextInitWrapper {
        void init(SSLContext sSLContext, KeyManager[] keyManagerArr, TrustManager[] trustManagerArr, SecureRandom secureRandom) throws KeyManagementException;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    /* loaded from: classes.dex */
    public interface TrustManagerFactoryInitWrapper {
        void init(TrustManagerFactory trustManagerFactory, KeyStore keyStore) throws KeyStoreException;
    }

    public AwsNewCertificateSslSocketFactoryProvider(Context context) {
        Preconditions.notNull(context, "context");
        this.context = context;
        this.certificateFactorySupplier = $$Lambda$AwsNewCertificateSslSocketFactoryProvider$e1bEG5Srq42Kl_o8j6r3XPIhG9w.INSTANCE;
        this.certificateGenerator = $$Lambda$AwsNewCertificateSslSocketFactoryProvider$RkyRB_xquSzmd7KRT4kEk2yIQ.INSTANCE;
        this.keyStoreInstanceSupplier = $$Lambda$AwsNewCertificateSslSocketFactoryProvider$qEPaabJk_cqibq1XdvM4gM8Ts.INSTANCE;
        this.keyStoreLoadWrapper = $$Lambda$AwsNewCertificateSslSocketFactoryProvider$II0tUcumKKc3ymROWGxLSQiFYQ.INSTANCE;
        this.keyStoreSetCertificateEntryWrapper = $$Lambda$AwsNewCertificateSslSocketFactoryProvider$eRTCny2FseCrUZGOlVtdSw8kfJM.INSTANCE;
        this.trustManagerFactoryInstanceSupplier = $$Lambda$AwsNewCertificateSslSocketFactoryProvider$0sWwsaBG2QzwbCzrIKer8H740eE.INSTANCE;
        this.trustManagerFactoryInitWrapper = $$Lambda$AwsNewCertificateSslSocketFactoryProvider$seGHkBmJ9PeGSxx24e49OSqOiU4.INSTANCE;
        this.getTrustManagersWrapper = $$Lambda$AwsNewCertificateSslSocketFactoryProvider$1xcscCOdAJ98W76O86TKtgEolXE.INSTANCE;
        this.compositeX509TrustManagerInstantiator = $$Lambda$AwsNewCertificateSslSocketFactoryProvider$YvON3ICgzh2_aStuQvX5yc4cMIE.INSTANCE;
        int i = Build.VERSION.SDK_INT;
        this.compositeX509ExtendedTrustManagerInstantiator = $$Lambda$AwsNewCertificateSslSocketFactoryProvider$xXh0LJs6RSVEhpBebtvLMnKaqZY.INSTANCE;
        this.sslContextInstanceSupplier = $$Lambda$AwsNewCertificateSslSocketFactoryProvider$WQVJ9VPGqdicfB2QpBzC6RYqM.INSTANCE;
        this.sslContextInitWrapper = $$Lambda$AwsNewCertificateSslSocketFactoryProvider$cAStAxwHMGXWmqWH0sFo9C_YN1Y.INSTANCE;
        this.getSslSocketFactoryWrapper = $$Lambda$AwsNewCertificateSslSocketFactoryProvider$EF_5WOvbJj1LISuzGxVZp8OPgzo.INSTANCE;
    }

    private SSLSocketFactory createSslSocketFactory() {
        try {
            Logger.d("%s Initialize", TAG);
            List<Certificate> readCertificatesFromResources = readCertificatesFromResources();
            if (readCertificatesFromResources.isEmpty()) {
                Logger.e("%s certificates are empty, return null", TAG);
                return null;
            }
            Logger.d("%s Read certificates", TAG);
            KeyStore keyStore = getKeyStore();
            if (!setCertificateEntries(readCertificatesFromResources, keyStore)) {
                Logger.e("%s failed to set certificate entries, return null", TAG);
                return null;
            }
            return this.getSslSocketFactoryWrapper.get(getSslContext(getTrustManagers(keyStore)));
        } catch (Exception unused) {
            return null;
        }
    }

    private KeyStore getKeyStore() throws Exception {
        String defaultType = KeyStore.getDefaultType();
        try {
            KeyStore keyStore = this.keyStoreInstanceSupplier.get(defaultType);
            Logger.d("%s Got KeyStore instance for type %s", TAG, defaultType);
            try {
                this.keyStoreLoadWrapper.load(keyStore, null, null);
                Logger.d("%s KeyStore loaded", TAG);
                return keyStore;
            } catch (IOException | NoSuchAlgorithmException | CertificateException e) {
                Logger.e("%s Error loading keystore", e, TAG);
                throw e;
            }
        } catch (KeyStoreException e2) {
            Logger.e("%s Error getting KeyStore instance for type: %s", e2, TAG, defaultType);
            throw e2;
        }
    }

    private SSLContext getSslContext(TrustManager[] trustManagerArr) throws Exception {
        try {
            SSLContext sSLContext = this.sslContextInstanceSupplier.get("TLS");
            Logger.d("%s Got SSLContext instance", TAG);
            this.sslContextInitWrapper.init(sSLContext, null, trustManagerArr, null);
            Logger.d("%s SSLContext init done", TAG);
            return sSLContext;
        } catch (KeyManagementException e) {
            Logger.e("%s Error init SSLContext", e, TAG);
            throw e;
        } catch (NoSuchAlgorithmException e2) {
            Logger.e("%s Error getting SSLContext instance", e2, TAG);
            throw e2;
        }
    }

    private TrustManager[] getTrustManagers(KeyStore keyStore) throws Exception {
        String defaultAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
        try {
            ArrayList arrayList = new ArrayList();
            TrustManagerFactory trustManagerFactory = this.trustManagerFactoryInstanceSupplier.get(defaultAlgorithm);
            this.trustManagerFactoryInitWrapper.init(trustManagerFactory, null);
            Logger.d("%s default TrustManagerFactory init done", TAG);
            TrustManager[] trustManagerArr = this.getTrustManagersWrapper.get(trustManagerFactory);
            Logger.d("%s default TrustManagerFactory returned TrustManagers size: %d", TAG, Integer.valueOf(trustManagerArr.length));
            for (TrustManager trustManager : trustManagerArr) {
                if (trustManager instanceof X509TrustManager) {
                    arrayList.add((X509TrustManager) trustManager);
                }
            }
            TrustManagerFactory trustManagerFactory2 = this.trustManagerFactoryInstanceSupplier.get(defaultAlgorithm);
            this.trustManagerFactoryInitWrapper.init(trustManagerFactory2, keyStore);
            Logger.d("%s custom TrustManagerFactory init done", TAG);
            TrustManager[] trustManagerArr2 = this.getTrustManagersWrapper.get(trustManagerFactory2);
            Logger.d("%s custom TrustManagerFactory returned TrustManagers size: %d", TAG, Integer.valueOf(trustManagerArr2.length));
            for (TrustManager trustManager2 : trustManagerArr2) {
                if (trustManager2 instanceof X509TrustManager) {
                    arrayList.add((X509TrustManager) trustManager2);
                }
            }
            Logger.d("%s x509TrustManager size: %d", TAG, Integer.valueOf(arrayList.size()));
            int i = Build.VERSION.SDK_INT;
            return new TrustManager[]{this.compositeX509ExtendedTrustManagerInstantiator.get(this.compositeX509TrustManagerInstantiator.get(arrayList))};
        } catch (KeyStoreException e) {
            Logger.e("%s Error init trust manager factory with keystore", e, TAG);
            throw e;
        } catch (NoSuchAlgorithmException e2) {
            Logger.e("%s Error getting trust manager instance for algorithm: %s", e2, TAG, defaultAlgorithm);
            throw e2;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ CompositeX509TrustManager lambda$new$8(List list) throws Exception {
        return new CompositeX509TrustManager(list);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ CompositeX509ExtendedTrustManager lambda$new$9(CompositeX509TrustManager compositeX509TrustManager) throws Exception {
        return new CompositeX509ExtendedTrustManager(compositeX509TrustManager);
    }

    private Certificate readCertificate(CertificateFactory certificateFactory, int i) {
        try {
            InputStream openRawResource = this.context.getResources().openRawResource(i);
            Certificate certificate = this.certificateGenerator.get(certificateFactory, openRawResource);
            if (openRawResource != null) {
                openRawResource.close();
            }
            return certificate;
        } catch (IOException e) {
            Logger.e("%s Error reading cert Id: %d", e, TAG, Integer.valueOf(i));
            return null;
        } catch (CertificateException e2) {
            Logger.e("%s Error generating certificate for cert Id: %d", e2, TAG, Integer.valueOf(i));
            return null;
        }
    }

    private List<Certificate> readCertificatesFromResources() throws CertificateException {
        try {
            ArrayList arrayList = new ArrayList();
            CertificateFactory certificateFactory = this.certificateFactorySupplier.get();
            for (Integer num : TRUSTED_AWS_CERTS_RESOURCE_IDS) {
                int intValue = num.intValue();
                Certificate readCertificate = readCertificate(certificateFactory, intValue);
                if (readCertificate != null) {
                    arrayList.add(readCertificate);
                    Logger.d("%s certificate added for resId %d", TAG, Integer.valueOf(intValue));
                } else {
                    Logger.e("%s certificate is null for resId %d", TAG, Integer.valueOf(intValue));
                }
            }
            return arrayList;
        } catch (CertificateException e) {
            Logger.e("%s Error reading certificates", e, TAG);
            throw e;
        }
    }

    private boolean setCertificateEntries(List<Certificate> list, KeyStore keyStore) {
        int i;
        KeyStoreException e;
        KeyStoreSetCertificateEntryWrapper keyStoreSetCertificateEntryWrapper;
        StringBuilder sb;
        boolean z = false;
        int i2 = 1;
        for (Certificate certificate : list) {
            try {
                keyStoreSetCertificateEntryWrapper = this.keyStoreSetCertificateEntryWrapper;
                sb = new StringBuilder();
                sb.append(CERT_PREFIX);
                i = i2 + 1;
            } catch (KeyStoreException e2) {
                i = i2;
                e = e2;
            }
            try {
                sb.append(i2);
                keyStoreSetCertificateEntryWrapper.setCertificateEntry(keyStore, sb.toString(), certificate);
                Logger.d("%s certificate entry set", TAG);
                z = true;
            } catch (KeyStoreException e3) {
                e = e3;
                Logger.e("%s Error setting certificate entry", e, TAG);
                i2 = i;
            }
            i2 = i;
        }
        return z;
    }

    @Override // com.amazon.alexa.accessory.internal.http.SslSocketFactoryProvider
    @Nullable
    public SSLSocketFactory provide() {
        synchronized (this.lock) {
            if (this.isInitialized) {
                Logger.d("%s already initialized", TAG);
                return this.socketFactory;
            }
            this.socketFactory = createSslSocketFactory();
            this.isInitialized = true;
            return this.socketFactory;
        }
    }

    @VisibleForTesting
    void setCertificateFactorySupplierForTest(WrapperUtil.Supplier<CertificateFactory, CertificateException> supplier) {
        this.certificateFactorySupplier = supplier;
    }

    @VisibleForTesting
    void setCertificateGeneratorForTest(WrapperUtil.TwoParamedSupplier<Certificate, CertificateFactory, InputStream, CertificateException> twoParamedSupplier) {
        this.certificateGenerator = twoParamedSupplier;
    }

    @RequiresApi(api = 24)
    @VisibleForTesting
    void setCompositeX509ExtendedTrustManagerInstantiatorForTest(WrapperUtil.ParamedSupplier<CompositeX509ExtendedTrustManager, CompositeX509TrustManager, Exception> paramedSupplier) {
        this.compositeX509ExtendedTrustManagerInstantiator = paramedSupplier;
    }

    @VisibleForTesting
    void setCompositeX509TrustManagerInstantiatorForTest(WrapperUtil.ParamedSupplier<CompositeX509TrustManager, List<X509TrustManager>, Exception> paramedSupplier) {
        this.compositeX509TrustManagerInstantiator = paramedSupplier;
    }

    @VisibleForTesting
    void setGetSslSocketFactoryWrapperForTest(WrapperUtil.ParamedSupplier<SSLSocketFactory, SSLContext, Exception> paramedSupplier) {
        this.getSslSocketFactoryWrapper = paramedSupplier;
    }

    @VisibleForTesting
    void setGetTrustManagersWrapperForTest(WrapperUtil.ParamedSupplier<TrustManager[], TrustManagerFactory, Exception> paramedSupplier) {
        this.getTrustManagersWrapper = paramedSupplier;
    }

    @VisibleForTesting
    void setKeyStoreInstanceSupplierForTest(WrapperUtil.ParamedSupplier<KeyStore, String, KeyStoreException> paramedSupplier) {
        this.keyStoreInstanceSupplier = paramedSupplier;
    }

    @VisibleForTesting
    void setKeyStoreLoadWrapperForTest(KeyStoreLoadWrapper keyStoreLoadWrapper) {
        this.keyStoreLoadWrapper = keyStoreLoadWrapper;
    }

    @VisibleForTesting
    void setKeyStoreSetCertificateEntryWrapperForTest(KeyStoreSetCertificateEntryWrapper keyStoreSetCertificateEntryWrapper) {
        this.keyStoreSetCertificateEntryWrapper = keyStoreSetCertificateEntryWrapper;
    }

    @VisibleForTesting
    void setSslContextInitWrapperForTest(SslContextInitWrapper sslContextInitWrapper) {
        this.sslContextInitWrapper = sslContextInitWrapper;
    }

    @VisibleForTesting
    void setSslContextInstanceSupplierForTest(WrapperUtil.ParamedSupplier<SSLContext, String, NoSuchAlgorithmException> paramedSupplier) {
        this.sslContextInstanceSupplier = paramedSupplier;
    }

    @VisibleForTesting
    void setTrustManagerFactoryInitWrapperForTest(TrustManagerFactoryInitWrapper trustManagerFactoryInitWrapper) {
        this.trustManagerFactoryInitWrapper = trustManagerFactoryInitWrapper;
    }

    @VisibleForTesting
    void setTrustManagerFactoryInstanceSupplierForTest(WrapperUtil.ParamedSupplier<TrustManagerFactory, String, NoSuchAlgorithmException> paramedSupplier) {
        this.trustManagerFactoryInstanceSupplier = paramedSupplier;
    }
}
