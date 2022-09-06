package com.amazon.dee.app.services.network;

import android.annotation.SuppressLint;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.network.api.HttpClient;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.alexa.protocols.service.api.ComponentGetter;
import com.amazon.dee.app.BuildConfig;
import com.amazon.dee.app.services.coral.GZIPRequestInterceptor;
import com.amazon.dee.app.services.security.CertificateReaderService;
import com.amazon.dee.app.services.useragent.UserAgent;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;
import javax.inject.Provider;
import javax.inject.Singleton;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import okhttp3.OkHttpClient;
@Singleton
/* loaded from: classes12.dex */
public class DefaultHttpClient implements HttpClient {
    private static final int CONNECT_TIMEOUT = 60000;
    private static final int SOCKET_TIMEOUT = 60000;
    private OkHttpClient bearerAuthOkHttpClient;
    private final Provider<CertificateReaderService> certificateReaderService;
    private final Provider<EnvironmentService> environmentService;
    private final Provider<IdentityService> identityService;
    @VisibleForTesting
    Boolean isDebugSigned;
    private final Provider<Mobilytics> mobilyticsProvider;
    private OkHttpClient okHttpClient;

    public DefaultHttpClient(@NonNull ComponentGetter componentGetter, @NonNull Context context) {
        this.identityService = componentGetter.get(IdentityService.class);
        this.environmentService = componentGetter.get(EnvironmentService.class);
        this.certificateReaderService = componentGetter.get(CertificateReaderService.class);
        this.mobilyticsProvider = componentGetter.get(Mobilytics.class);
    }

    private OkHttpClient getBearerAuthOkHttpClient() {
        if (this.bearerAuthOkHttpClient == null) {
            this.bearerAuthOkHttpClient = getRootOkHttpClient().newBuilder().addInterceptor(new BearerAuthorizationInterceptor(this.identityService, this.environmentService, this.mobilyticsProvider, BuildConfig.IS_PROD_ENVIRONMENT)).build();
        }
        return this.bearerAuthOkHttpClient;
    }

    private OkHttpClient getRootOkHttpClient() {
        if (this.okHttpClient == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.addInterceptor(new GZIPRequestInterceptor());
            builder.addInterceptor(new UserAgentInterceptor(UserAgent.get()));
            builder.addInterceptor(new ApplicationMetadataInterceptor());
            if (BuildConfig.IS_LOCAL_ENVIRONMENT || BuildConfig.IS_GAMMA_ENVIRONMENT || isDebugSigned().booleanValue()) {
                try {
                    X509TrustManager x509TrustManager = new X509TrustManager() { // from class: com.amazon.dee.app.services.network.DefaultHttpClient.1
                        @Override // javax.net.ssl.X509TrustManager
                        @SuppressLint({"TrustAllX509TrustManager"})
                        public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
                        }

                        @Override // javax.net.ssl.X509TrustManager
                        @SuppressLint({"TrustAllX509TrustManager"})
                        public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
                        }

                        @Override // javax.net.ssl.X509TrustManager
                        public X509Certificate[] getAcceptedIssuers() {
                            return new X509Certificate[0];
                        }
                    };
                    SSLContext sSLContext = SSLContext.getInstance("SSL");
                    sSLContext.init(null, new TrustManager[]{x509TrustManager}, new SecureRandom());
                    builder.sslSocketFactory(sSLContext.getSocketFactory(), x509TrustManager);
                } catch (KeyManagementException | NoSuchAlgorithmException e) {
                    throw new RuntimeException("Invalid environment", e);
                }
            }
            builder.connectTimeout(60000L, TimeUnit.MILLISECONDS);
            builder.readTimeout(60000L, TimeUnit.MILLISECONDS);
            builder.writeTimeout(60000L, TimeUnit.MILLISECONDS);
            this.okHttpClient = builder.build();
        }
        return this.okHttpClient;
    }

    private Boolean isDebugSigned() {
        if (this.isDebugSigned == null) {
            this.isDebugSigned = Boolean.valueOf(this.certificateReaderService.mo10268get().isDebugSigned());
        }
        return this.isDebugSigned;
    }

    @Override // com.amazon.alexa.network.api.HttpClient
    public synchronized OkHttpClient httpClient() {
        return getRootOkHttpClient();
    }

    @Override // com.amazon.alexa.network.api.HttpClient
    public synchronized OkHttpClient okHttpClientWithBearerAuth() {
        return getBearerAuthOkHttpClient();
    }
}
