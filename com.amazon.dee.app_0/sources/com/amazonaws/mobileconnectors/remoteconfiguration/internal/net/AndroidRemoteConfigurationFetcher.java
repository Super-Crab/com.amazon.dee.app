package com.amazonaws.mobileconnectors.remoteconfiguration.internal.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.amazonaws.mobileconnectors.remoteconfiguration.Attributes;
import com.amazonaws.mobileconnectors.remoteconfiguration.BuildConfig;
import com.amazonaws.mobileconnectors.remoteconfiguration.exceptions.NetworkException;
import com.amazonaws.mobileconnectors.remoteconfiguration.internal.ConfigurationImpl;
import com.amazonaws.mobileconnectors.remoteconfiguration.internal.model.RemoteConfiguration;
import com.amazonaws.mobileconnectors.remoteconfiguration.internal.model.RemoteConfigurationImpl;
import com.amazonaws.org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes13.dex */
public class AndroidRemoteConfigurationFetcher implements RemoteConfigurationFetcher {
    private static final Pattern ARCUS_CLIENT_VERSION_PATTERN = Pattern.compile("[1-9][0-9]*\\.[0-9]+\\.[0-9]+");
    private static final String CHARSET_NAME_UTF8 = "UTF-8";
    private static final int DEFAULT_CONNECT_TIMEOUT = 15000;
    private static final int DEFAULT_READ_TIMEOUT = 15000;
    public static final String DISABLE_CERT_CHECKING_SYSTEM_PROPERTY = "com.amazonaws.sdk.disableCertChecking";
    private static final String GZIP_ENCODING = "gzip";
    private static final String HEADER_ACCEPT_ENCODING = "Accept-Encoding";
    private static final String HEADER_AMZ_TARGET = "X-Amz-Target";
    private static final String HEADER_CONTENT_ENCODING = "Content-Encoding";
    private static final String HEADER_CONTENT_TYPE = "Content-Type";
    private static final String HEADER_USER_AGENT = "User-Agent";
    private static final int HTTP_OK_STATUS_CODE = 200;
    private static final String QUERY_CONFIGURATION_TARGET = "RemoteConfigurationDistributionService.QueryConfiguration";
    private static final String REQUEST_KEY_APP_CONFIG_ID = "appConfigId";
    private static final String REQUEST_KEY_CLIENT_ATTRIBUTES = "clientAttributes";
    private static final String REQUEST_KEY_LAST_SEEN_ENTITY_TAG = "lastSeenEntityTag";
    private static final String REQUEST_KEY_LOCAL_CONFIG_INSTANCE_ID = "localConfigurationInstanceId";
    private static final String RESPONSE_KEY_CONFIGURATION = "resultVariables";
    private static final String RESPONSE_KEY_ENTITY_TAG = "entityTag";
    private static final String RESPONSE_KEY_UPDATED = "updatedConfigurationAvailable";
    private static final String TAG = "AndroidRemoteConfigurationFetcher";
    private static final String USER_AGENT_PREFIX = "Arcus-Android/";
    private static final String XAMZ_JSON_CONTENT_TYPE = "application/x-amz-json-1.1";
    private final Context mContext;
    private final URL mEndpoint;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes13.dex */
    public static class AllowAllHostnameVerifier implements HostnameVerifier {
        private AllowAllHostnameVerifier() {
        }

        @Override // javax.net.ssl.HostnameVerifier
        public boolean verify(String str, SSLSession sSLSession) {
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes13.dex */
    public static class TrustAllManager implements X509TrustManager {
        private TrustAllManager() {
        }

        @Override // javax.net.ssl.X509TrustManager
        public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        }

        @Override // javax.net.ssl.X509TrustManager
        public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        }

        @Override // javax.net.ssl.X509TrustManager
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }
    }

    public AndroidRemoteConfigurationFetcher(Context context, URL url) {
        if (context != null) {
            if (url != null) {
                this.mContext = context;
                this.mEndpoint = url;
                return;
            }
            throw new NullPointerException("The endpoint may not be null.");
        }
        throw new NullPointerException("The context may not be null.");
    }

    private RemoteConfiguration buildRemoteConfiguration(String str, JSONObject jSONObject) {
        ConfigurationImpl configurationImpl;
        try {
            boolean z = jSONObject.getBoolean(RESPONSE_KEY_UPDATED);
            String string = jSONObject.getString(RESPONSE_KEY_ENTITY_TAG);
            try {
                configurationImpl = new ConfigurationImpl(jSONObject.getString(RESPONSE_KEY_CONFIGURATION), new Date());
            } catch (JSONException unused) {
                configurationImpl = null;
            }
            return new RemoteConfigurationImpl(configurationImpl, str, 2, string, z);
        } catch (JSONException e) {
            throw new NetworkException("Expected elements missing from the response", e);
        }
    }

    private byte[] buildRequestBody(String str, Attributes attributes, String str2, String str3) {
        Map<String, Object> allAttributes;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(REQUEST_KEY_APP_CONFIG_ID, str);
            jSONObject.put(REQUEST_KEY_LAST_SEEN_ENTITY_TAG, str2);
            jSONObject.put(REQUEST_KEY_LOCAL_CONFIG_INSTANCE_ID, str3);
            if (attributes != null && (allAttributes = attributes.getAllAttributes()) != null) {
                jSONObject.put(REQUEST_KEY_CLIENT_ATTRIBUTES, new JSONObject((Map) allAttributes).toString());
            }
            return jSONObject.toString().getBytes(Charset.forName("UTF-8"));
        } catch (JSONException e) {
            throw new NetworkException("Error building request", e);
        }
    }

    private void configureConnection(HttpURLConnection httpURLConnection) {
        httpURLConnection.setConnectTimeout(15000);
        httpURLConnection.setReadTimeout(15000);
        httpURLConnection.setUseCaches(false);
        if (httpURLConnection instanceof HttpsURLConnection) {
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) httpURLConnection;
            if (System.getProperty("com.amazonaws.sdk.disableCertChecking") == null) {
                return;
            }
            disableCertificateValidation(httpsURLConnection);
        }
    }

    private void disableCertificateValidation(HttpsURLConnection httpsURLConnection) {
        TrustManager[] trustManagerArr = {new TrustAllManager()};
        try {
            SSLContext sSLContext = SSLContext.getInstance(SSLSocketFactoryFactory.DEFAULT_PROTOCOL);
            sSLContext.init(null, trustManagerArr, null);
            httpsURLConnection.setSSLSocketFactory(sSLContext.getSocketFactory());
            httpsURLConnection.setHostnameVerifier(new AllowAllHostnameVerifier());
        } catch (GeneralSecurityException e) {
            throw new RuntimeException(e);
        }
    }

    private String getArcusUserAgent() {
        Matcher matcher = ARCUS_CLIENT_VERSION_PATTERN.matcher(BuildConfig.VERSION_NAME);
        return GeneratedOutlineSupport1.outline72(USER_AGENT_PREFIX, matcher.find() ? matcher.group() : "1.2.x");
    }

    private boolean isNetworkAvailable() {
        NetworkInfo activeNetworkInfo;
        ConnectivityManager connectivityManager = (ConnectivityManager) this.mContext.getSystemService("connectivity");
        return (connectivityManager == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null || !activeNetworkInfo.isConnected()) ? false : true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:56:0x009b  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x00aa A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v16, types: [java.io.InputStreamReader] */
    /* JADX WARN: Type inference failed for: r2v5 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private org.json.JSONObject readResponse(java.net.HttpURLConnection r7) {
        /*
            Method dump skipped, instructions count: 202
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazonaws.mobileconnectors.remoteconfiguration.internal.net.AndroidRemoteConfigurationFetcher.readResponse(java.net.HttpURLConnection):org.json.JSONObject");
    }

    private void setHeaders(HttpURLConnection httpURLConnection) {
        httpURLConnection.setRequestProperty("Content-Type", XAMZ_JSON_CONTENT_TYPE);
        httpURLConnection.setRequestProperty(HEADER_AMZ_TARGET, QUERY_CONFIGURATION_TARGET);
        httpURLConnection.setRequestProperty("Accept-Encoding", GZIP_ENCODING);
        httpURLConnection.setRequestProperty("User-Agent", getArcusUserAgent());
    }

    private void writeRequest(HttpURLConnection httpURLConnection, byte[] bArr) {
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setFixedLengthStreamingMode(bArr.length);
        OutputStream outputStream = null;
        try {
            try {
                outputStream = httpURLConnection.getOutputStream();
                outputStream.write(bArr);
                outputStream.flush();
                try {
                    outputStream.close();
                } catch (IOException e) {
                    throw new NetworkException("Error closing the connection's output", e);
                }
            } catch (Throwable th) {
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (IOException e2) {
                        throw new NetworkException("Error closing the connection's output", e2);
                    }
                }
                throw th;
            }
        } catch (IOException e3) {
            throw new NetworkException("Error writing the request", e3);
        }
    }

    @Override // com.amazonaws.mobileconnectors.remoteconfiguration.internal.net.RemoteConfigurationFetcher
    public RemoteConfiguration fetch(String str, Attributes attributes, String str2, String str3) {
        HttpURLConnection httpURLConnection;
        if (str != null) {
            if (attributes != null) {
                if (isNetworkAvailable()) {
                    HttpURLConnection httpURLConnection2 = null;
                    try {
                        try {
                            httpURLConnection = (HttpURLConnection) this.mEndpoint.openConnection();
                        } catch (Throwable th) {
                            th = th;
                        }
                    } catch (IOException e) {
                        e = e;
                    }
                    try {
                        configureConnection(httpURLConnection);
                        setHeaders(httpURLConnection);
                        writeRequest(httpURLConnection, buildRequestBody(str, attributes, str2, str3));
                        RemoteConfiguration buildRemoteConfiguration = buildRemoteConfiguration(str, readResponse(httpURLConnection));
                        httpURLConnection.disconnect();
                        return buildRemoteConfiguration;
                    } catch (IOException e2) {
                        e = e2;
                        throw new NetworkException("Unable to open connection", e);
                    } catch (Throwable th2) {
                        th = th2;
                        httpURLConnection2 = httpURLConnection;
                        if (httpURLConnection2 != null) {
                            httpURLConnection2.disconnect();
                        }
                        throw th;
                    }
                }
                throw new NetworkException("There is no network connectivity.");
            }
            throw new NullPointerException("The attributes may not be null");
        }
        throw new NullPointerException("The App Configuration ID may not be null");
    }
}
