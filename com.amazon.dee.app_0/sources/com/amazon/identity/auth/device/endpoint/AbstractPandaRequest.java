package com.amazon.identity.auth.device.endpoint;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.amazon.identity.auth.device.AuthError;
import com.amazon.identity.auth.device.authorization.EndpointDomainBuilder;
import com.amazon.identity.auth.device.authorization.Service;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.amazon.identity.auth.device.endpoint.PandaResponse;
import com.amazon.identity.auth.device.utils.DefaultLibraryInfo;
import com.amazon.identity.auth.device.utils.MAPUtils;
import com.amazon.identity.auth.map.device.utils.MAPLog;
import com.amazonaws.org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.net.HttpHeaders;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.SingleClientConnManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
/* loaded from: classes12.dex */
public abstract class AbstractPandaRequest<T extends PandaResponse> {
    protected static final String ANDROID_OS_NAME = "Android";
    protected static final String API_PREFIX_DEVO = "api.integ";
    protected static final String API_PREFIX_PRE_PROD = "api.pre-prod";
    protected static final String API_PREFIX_PROD = "api";
    protected static final String APP_NAME = "app_name";
    protected static final String APP_VERSION = "app_version";
    protected static final String DEFAULT_TOP_LEVEL_DOMAIN = ".amazon.com";
    private static final String DEFAULT_USER_AGENT;
    protected static final String DI_HW_NAME = "di.hw.name";
    protected static final String DI_HW_VERSION = "di.hw.version";
    protected static final String DI_OS_NAME = "di.os.name";
    protected static final String DI_OS_VERSION = "di.os.version";
    protected static final String DI_SDK_VERSION = "di.sdk.version";
    private static final String LOG_TAG = "com.amazon.identity.auth.device.endpoint.AbstractPandaRequest";
    private static final int MAX_NUM_POST_PARAMS = 10;
    protected static final int NUM_RETRY_ATTEMPTS = 2;
    private AppInfo appInfo;
    private String appName;
    private String appVersion;
    private Context context;
    private HttpClient httpClient;
    protected HttpRequestBase httpRequest;
    private int socketTimeout = -1;
    private final List<Header> headers = new ArrayList();
    protected final List<NameValuePair> postParameters = new ArrayList(10);
    private String libVersion = "3.0.2";

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public class UnsafeSslHttpClient extends DefaultHttpClient {
        private static final String BKS = "BKS";
        private static final String HTTP = "http";
        private static final String HTTPS = "https";

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes12.dex */
        public class MySSLSocketFactory extends SSLSocketFactory {
            SSLContext sslContext;

            public MySSLSocketFactory(KeyStore keyStore) throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException {
                super(keyStore);
                this.sslContext = SSLContext.getInstance(SSLSocketFactoryFactory.DEFAULT_PROTOCOL);
                this.sslContext.init(null, new TrustManager[]{new X509TrustManager() { // from class: com.amazon.identity.auth.device.endpoint.AbstractPandaRequest.UnsafeSslHttpClient.MySSLSocketFactory.1
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
                }}, null);
            }

            @Override // org.apache.http.conn.ssl.SSLSocketFactory, org.apache.http.conn.scheme.LayeredSocketFactory
            public Socket createSocket(Socket socket, String str, int i, boolean z) throws IOException, UnknownHostException {
                return this.sslContext.getSocketFactory().createSocket(socket, str, i, z);
            }

            @Override // org.apache.http.conn.ssl.SSLSocketFactory, org.apache.http.conn.scheme.SocketFactory
            public Socket createSocket() throws IOException {
                return this.sslContext.getSocketFactory().createSocket();
            }
        }

        public UnsafeSslHttpClient() {
        }

        private SSLSocketFactory newSslSocketFactory() {
            try {
                MySSLSocketFactory mySSLSocketFactory = new MySSLSocketFactory(KeyStore.getInstance(BKS));
                mySSLSocketFactory.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
                return mySSLSocketFactory;
            } catch (Exception e) {
                throw new AssertionError(e);
            }
        }

        protected ClientConnectionManager createClientConnectionManager() {
            SchemeRegistry schemeRegistry = new SchemeRegistry();
            schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
            schemeRegistry.register(new Scheme("https", newSslSocketFactory(), 443));
            return new SingleClientConnManager(getParams(), schemeRegistry);
        }
    }

    static {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("LWAAndroidSDK/3.0.2/Android/");
        outline107.append(Build.VERSION.RELEASE);
        outline107.append("/");
        outline107.append(Build.MODEL);
        DEFAULT_USER_AGENT = outline107.toString();
    }

    public AbstractPandaRequest(Context context, AppInfo appInfo) {
        this.context = context;
        this.appInfo = appInfo;
        this.appName = MAPUtils.getAppName(context);
        this.appVersion = MAPUtils.getVersion(context);
    }

    private void addAppInfoParameters() throws AuthError {
        this.postParameters.add(new BasicNameValuePair(APP_NAME, this.appName));
        String str = this.appVersion;
        if (str != null) {
            this.postParameters.add(new BasicNameValuePair(APP_VERSION, str));
        }
    }

    private void addDefaultHeaders() {
        this.headers.add(new BasicHeader("Accept-Encoding", "gzip, deflate"));
        this.headers.add(new BasicHeader("Accept-Language", "en-us,en;q=0.5"));
        this.headers.add(new BasicHeader("Accept", "application/xml,application/xhtml+xml,text/html,application/json;q=0.9,text/plain;q=0.8,image/png,*/*;q=0.5"));
        this.headers.add(new BasicHeader(HttpHeaders.ACCEPT_CHARSET, "utf-8, iso-8859-1, utf-16, *;q=0.7"));
    }

    private void addDeviceParams() throws AuthError {
        if (!TextUtils.isEmpty(Build.MANUFACTURER) && !Build.MANUFACTURER.equals("unknown")) {
            this.postParameters.add(new BasicNameValuePair(DI_HW_NAME, Build.MANUFACTURER));
        }
        if (!TextUtils.isEmpty(Build.MODEL) && !Build.MODEL.equals("unknown")) {
            this.postParameters.add(new BasicNameValuePair(DI_HW_VERSION, Build.MODEL));
        }
        this.postParameters.add(new BasicNameValuePair(DI_OS_NAME, "Android"));
        if (!TextUtils.isEmpty(Build.VERSION.RELEASE) && !Build.VERSION.RELEASE.equals("unknown")) {
            this.postParameters.add(new BasicNameValuePair(DI_OS_VERSION, Build.VERSION.RELEASE));
        }
        this.postParameters.add(new BasicNameValuePair(DI_SDK_VERSION, this.libVersion));
    }

    private void addExtraHeaders() {
        List<Header> extraHeaders = getExtraHeaders();
        if (extraHeaders != null) {
            this.headers.addAll(extraHeaders);
        }
    }

    private void addExtraParameters() {
        List<BasicNameValuePair> extraParameters = getExtraParameters();
        if (extraParameters != null) {
            this.postParameters.addAll(extraParameters);
        }
    }

    private static int getCode(HttpResponse httpResponse) {
        return httpResponse.getStatusLine().getStatusCode();
    }

    private static boolean hasReceived500(HttpResponse httpResponse) {
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        return statusCode >= 500 && statusCode < 600;
    }

    private void initializeHeaders() throws AuthError {
        addDefaultHeaders();
        addExtraHeaders();
    }

    private void initializeHttp() throws AuthError {
        if (this.httpClient == null) {
            if (DefaultLibraryInfo.isDevo()) {
                this.httpClient = new UnsafeSslHttpClient();
            } else {
                this.httpClient = new DefaultHttpClient();
            }
            this.httpRequest = initializeRequest(getRequestUrl());
        }
    }

    private void initializePostParams() throws AuthError {
        addExtraParameters();
        addAppInfoParameters();
        addDeviceParams();
    }

    private void initializeUserAgent() {
        this.httpClient.getParams().setParameter("http.useragent", DEFAULT_USER_AGENT);
    }

    private void logRequestInfo() {
        MAPLog.pii(LOG_TAG, "Logging Request info.", "UserAgent = " + ((String) this.httpClient.getParams().getParameter("http.useragent")));
        Header[] allHeaders = this.httpRequest.getAllHeaders();
        if (allHeaders != null) {
            String str = LOG_TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Number of Headers : ");
            outline107.append(allHeaders.length);
            MAPLog.i(str, outline107.toString());
            for (Header header : allHeaders) {
                String str2 = LOG_TAG;
                StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Header used for request: name=");
                outline1072.append(header.getName());
                String sb = outline1072.toString();
                StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("val=");
                outline1073.append(header.getValue());
                MAPLog.pii(str2, sb, outline1073.toString());
            }
        } else {
            MAPLog.i(LOG_TAG, "No Headers");
        }
        logRequest();
    }

    protected void consumeEntity() throws IOException {
        this.httpRequest.getEntity().consumeContent();
    }

    HttpResponse executeRequest() throws ClientProtocolException, IOException {
        if (this.socketTimeout != -1) {
            HttpParams params = this.httpRequest.getParams();
            HttpConnectionParams.setSoTimeout(params, this.socketTimeout);
            this.httpRequest.setParams(params);
        }
        logRequestInfo();
        return this.httpClient.execute(this.httpRequest);
    }

    /* renamed from: generateResponse */
    protected abstract T mo4066generateResponse(HttpResponse httpResponse);

    protected abstract String getEndPoint();

    protected abstract List<Header> getExtraHeaders();

    protected abstract List<BasicNameValuePair> getExtraParameters();

    List<Header> getHeaders() {
        return this.headers;
    }

    List<NameValuePair> getPostParameters() {
        for (NameValuePair nameValuePair : this.postParameters) {
            if (nameValuePair != null) {
                String str = LOG_TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("name=");
                outline107.append(nameValuePair.getName());
                outline107.append(" val=");
                outline107.append(nameValuePair.getValue());
                MAPLog.pii(str, "Parameter Added to request", outline107.toString());
            } else {
                MAPLog.e(LOG_TAG, "Parameter Added to request was NULL");
            }
        }
        return this.postParameters;
    }

    String getRequestUrl() throws AuthError {
        String endPoint = getEndPoint();
        try {
            String domain = new EndpointDomainBuilder(this.context, this.appInfo).forService(Service.PANDA).forSandbox(isSandboxEnabled()).getDomain();
            return new URL(domain + endPoint).toString();
        } catch (MalformedURLException e) {
            throw new AuthError("MalformedURLException", e, AuthError.ERROR_TYPE.ERROR_BAD_PARAM);
        }
    }

    protected HttpRequestBase initializeRequest(String str) {
        return new HttpPost(str);
    }

    protected boolean isSandboxEnabled() {
        return false;
    }

    protected abstract void logRequest();

    protected void setEntity() throws UnsupportedEncodingException, AuthError {
        this.httpRequest.setEntity(new UrlEncodedFormEntity(getPostParameters()));
    }

    public final T submit() throws AuthError {
        initializeHttp();
        initializeUserAgent();
        initializePostParams();
        initializeHeaders();
        try {
            setEntity();
            for (Header header : this.headers) {
                this.httpRequest.addHeader(header);
            }
            HttpResponse httpResponse = null;
            try {
                try {
                    String str = LOG_TAG;
                    MAPLog.i(str, "Request url: " + this.httpRequest.getURI());
                    int i = 0;
                    while (i <= 2) {
                        httpResponse = executeRequest();
                        if (!hasReceived500(httpResponse)) {
                            break;
                        }
                        if (i != 2) {
                            httpResponse.getEntity().consumeContent();
                        }
                        String str2 = LOG_TAG;
                        StringBuilder sb = new StringBuilder();
                        sb.append("Received ");
                        sb.append(getCode(httpResponse));
                        sb.append(" error on request attempt ");
                        i++;
                        sb.append(i);
                        sb.append(" of ");
                        sb.append(3);
                        MAPLog.w(str2, sb.toString());
                    }
                    return mo4066generateResponse(httpResponse);
                } catch (ClientProtocolException e) {
                    String str3 = LOG_TAG;
                    MAPLog.e(str3, "Received communication error when executing token request:" + e.toString());
                    throw new AuthError("Received communication error when executing token request", e, AuthError.ERROR_TYPE.ERROR_COM);
                } catch (IOException e2) {
                    String str4 = LOG_TAG;
                    MAPLog.e(str4, "Received IO error when executing token request:" + e2.toString());
                    throw new AuthError("Received communication error when executing token request", e2, AuthError.ERROR_TYPE.ERROR_IO);
                } catch (IllegalStateException e3) {
                    String str5 = LOG_TAG;
                    MAPLog.e(str5, "Received IllegalStateException error when executing token request:" + e3.toString());
                    throw new AuthError("Received communication error when executing token request", e3, AuthError.ERROR_TYPE.ERROR_COM);
                }
            } finally {
                HttpClient httpClient = this.httpClient;
                if (httpClient != null) {
                    httpClient.getConnectionManager().closeIdleConnections(5L, TimeUnit.SECONDS);
                }
                if (this.httpRequest != null) {
                    try {
                        consumeEntity();
                    } catch (IOException e4) {
                        String str6 = LOG_TAG;
                        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("IOException consuming httppost entity content ");
                        outline107.append(e4.toString());
                        MAPLog.e(str6, outline107.toString());
                    }
                }
            }
        } catch (UnsupportedEncodingException e5) {
            throw new AuthError(e5.getMessage(), e5, AuthError.ERROR_TYPE.ERROR_BAD_PARAM);
        }
    }
}
