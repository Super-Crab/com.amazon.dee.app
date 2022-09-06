package com.amazon.deecomms.common.network.okhttp;

import android.os.Build;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.auth.AuthTokenHelper;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.network.IHttpClient;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.remoteConfig.ArcusConfig;
import com.amazon.deecomms.remoteConfig.RemoteConfigKeys;
import com.android.tools.r8.GeneratedOutlineSupport;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import okhttp3.Authenticator;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
/* loaded from: classes12.dex */
public class OkHttpClientWrapper implements IHttpClient {
    public static final String ANNOUNCEMENT_CLIENT = "announcement";
    private static final OkHttpClient ANNOUNCEMENT_MEDIA_HTTP_CLIENT;
    public static final String DEFAULT_CLIENT = "default";
    private static final OkHttpClient DEFAULT_HTTP_CLIENT;
    private static final int DEFAULT_HTTP_CLIENT_READ_TIMEOUT_VALUE = 120;
    public static final String MESSAGING_CLIENT = "messaging";
    private static final OkHttpClient MESSAGING_MEDIA_HTTP_CLIENT;
    protected IHttpClient.AuthHeaderProvider authHeaderProvider;
    protected IHttpClient.JSONConverter jsonConverter;
    protected OkHttpClient okHttpClient;
    private String operationMetricNameRoot;
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, OkHttpClientWrapper.class);
    private static Authenticator authenticationFailureHandler = new Authenticator() { // from class: com.amazon.deecomms.common.network.okhttp.OkHttpClientWrapper.1
        private static final String AUTH_TOKEN_HEADER = "Authorization";
        private static final int MAX_REQUESTS_TO_MAKE = 3;

        private int responseCount(Response response) {
            int i = 1;
            while (response != null) {
                response = response.priorResponse();
                i++;
            }
            return i;
        }

        @Override // okhttp3.Authenticator
        public Request authenticate(Route route, Response response) throws IOException {
            if (responseCount(response) > 3) {
                OkHttpClientWrapper.LOG.e("Reached maxlimit of making request to MediaStorageService");
                return null;
            }
            String commsId = CommsDaggerWrapper.getComponent().getCommsIdentityManager().getCommsId("OkHttpClientWrapper.authenticate", false);
            if (TextUtils.isEmpty(commsId)) {
                OkHttpClientWrapper.LOG.e("CommsId is null and we cannot refresh tokens");
                return null;
            }
            AuthTokenHelper.AuthToken fetchAuthToken = AuthTokenHelper.fetchAuthToken(commsId, AuthTokenHelper.TokenType.MEDIA_SERVICE, true);
            if (fetchAuthToken == null || TextUtils.isEmpty(fetchAuthToken.getValue())) {
                OkHttpClientWrapper.LOG.e("Cannot refresh authToken successfully");
                return null;
            }
            CommsLogger commsLogger = OkHttpClientWrapper.LOG;
            commsLogger.i("Refreshed authToken and constructing new request: " + fetchAuthToken);
            return response.request().newBuilder().header("Authorization", fetchAuthToken.getValue()).build();
        }
    };

    static {
        ArcusConfig arcusConfig = CommsDaggerWrapper.getComponent().getArcusConfig();
        int intValue = arcusConfig.getConfigInteger(RemoteConfigKeys.MESSAGE_MEDIA_STORAGE_TIMEOUT).intValue();
        int intValue2 = arcusConfig.getConfigInteger(RemoteConfigKeys.ANNOUNCEMENT_MEDIA_STORAGE_TIMEOUT).intValue();
        DEFAULT_HTTP_CLIENT = enableTls12(new OkHttpClient.Builder().readTimeout(120L, TimeUnit.SECONDS)).build();
        long j = intValue;
        MESSAGING_MEDIA_HTTP_CLIENT = new OkHttpClient.Builder().readTimeout(j, TimeUnit.SECONDS).writeTimeout(j, TimeUnit.SECONDS).authenticator(authenticationFailureHandler).build();
        long j2 = intValue2;
        ANNOUNCEMENT_MEDIA_HTTP_CLIENT = new OkHttpClient.Builder().readTimeout(j2, TimeUnit.SECONDS).writeTimeout(j2, TimeUnit.SECONDS).authenticator(authenticationFailureHandler).build();
    }

    public OkHttpClientWrapper(IHttpClient.JSONConverter jSONConverter, IHttpClient.AuthHeaderProvider authHeaderProvider) {
        this(jSONConverter, authHeaderProvider, "default");
    }

    private static OkHttpClient.Builder enableTls12(OkHttpClient.Builder builder) {
        int i = Build.VERSION.SDK_INT;
        return builder;
    }

    private static X509TrustManager getTrustManager() throws NoSuchAlgorithmException, KeyStoreException {
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        trustManagerFactory.init((KeyStore) null);
        TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
        if (trustManagers.length == 1 && (trustManagers[0] instanceof X509TrustManager)) {
            return (X509TrustManager) trustManagers[0];
        }
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("Unexpected default trust managers:");
        outline1.append(Arrays.toString(trustManagers));
        throw new IllegalStateException(outline1.toString());
    }

    public OkHttpClient getClient() {
        return this.okHttpClient;
    }

    @Override // com.amazon.deecomms.common.network.IHttpClient
    public IHttpClient.Request request(@NonNull String str) {
        OkHttpRequest okHttpRequest = new OkHttpRequest(str, this.jsonConverter, this.authHeaderProvider, this.okHttpClient);
        okHttpRequest.setOperationMetricNameRoot(this.operationMetricNameRoot);
        return okHttpRequest;
    }

    @Override // com.amazon.deecomms.common.network.IHttpClient
    public void setOperationMetricNameRoot(String str) {
        this.operationMetricNameRoot = str;
    }

    public OkHttpClientWrapper(IHttpClient.JSONConverter jSONConverter, IHttpClient.AuthHeaderProvider authHeaderProvider, String str) {
        this.jsonConverter = jSONConverter;
        this.authHeaderProvider = authHeaderProvider;
        if (ANNOUNCEMENT_CLIENT.equals(str)) {
            this.okHttpClient = ANNOUNCEMENT_MEDIA_HTTP_CLIENT;
        } else if (MESSAGING_CLIENT.equals(str)) {
            this.okHttpClient = MESSAGING_MEDIA_HTTP_CLIENT;
        } else {
            this.okHttpClient = DEFAULT_HTTP_CLIENT;
        }
    }
}
