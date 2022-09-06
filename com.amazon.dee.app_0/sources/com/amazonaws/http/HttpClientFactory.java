package com.amazonaws.http;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.http.impl.client.HttpRequestNoRetryHandler;
import com.amazonaws.http.impl.client.SdkHttpClient;
import com.amazonaws.logging.Log;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.NTCredentials;
import org.apache.http.conn.params.ConnRouteParams;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultRedirectHandler;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HttpContext;
/* loaded from: classes13.dex */
class HttpClientFactory {
    private static final int HTTPS_PORT = 443;
    private static final int HTTP_PORT = 80;

    /* loaded from: classes13.dex */
    private static final class LocationHeaderNotRequiredRedirectHandler extends DefaultRedirectHandler {
        private LocationHeaderNotRequiredRedirectHandler() {
        }

        public boolean isRedirectRequested(org.apache.http.HttpResponse httpResponse, HttpContext httpContext) {
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            if (httpResponse.getFirstHeader("location") == null && statusCode == 301) {
                return false;
            }
            return super.isRedirectRequested(httpResponse, httpContext);
        }
    }

    public org.apache.http.client.HttpClient createHttpClient(ClientConfiguration clientConfiguration) {
        BasicHttpParams basicHttpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(basicHttpParams, clientConfiguration.getConnectionTimeout());
        HttpConnectionParams.setSoTimeout(basicHttpParams, clientConfiguration.getSocketTimeout());
        HttpConnectionParams.setStaleCheckingEnabled(basicHttpParams, true);
        HttpConnectionParams.setTcpNoDelay(basicHttpParams, true);
        int i = clientConfiguration.getSocketBufferSizeHints()[0];
        int i2 = clientConfiguration.getSocketBufferSizeHints()[1];
        if (i > 0 || i2 > 0) {
            HttpConnectionParams.setSocketBufferSize(basicHttpParams, Math.max(i, i2));
        }
        ThreadSafeClientConnManager createThreadSafeClientConnManager = ConnectionManagerFactory.createThreadSafeClientConnManager(clientConfiguration, basicHttpParams);
        SdkHttpClient sdkHttpClient = new SdkHttpClient(createThreadSafeClientConnManager, basicHttpParams);
        sdkHttpClient.setHttpRequestRetryHandler(HttpRequestNoRetryHandler.Singleton);
        sdkHttpClient.setRedirectHandler(new LocationHeaderNotRequiredRedirectHandler());
        if (clientConfiguration.getLocalAddress() != null) {
            ConnRouteParams.setLocalAddress(basicHttpParams, clientConfiguration.getLocalAddress());
        }
        Scheme scheme = new Scheme("http", PlainSocketFactory.getSocketFactory(), 80);
        SSLSocketFactory socketFactory = SSLSocketFactory.getSocketFactory();
        socketFactory.setHostnameVerifier(SSLSocketFactory.STRICT_HOSTNAME_VERIFIER);
        Scheme scheme2 = new Scheme("https", socketFactory, (int) HTTPS_PORT);
        SchemeRegistry schemeRegistry = createThreadSafeClientConnManager.getSchemeRegistry();
        schemeRegistry.register(scheme);
        schemeRegistry.register(scheme2);
        String proxyHost = clientConfiguration.getProxyHost();
        int proxyPort = clientConfiguration.getProxyPort();
        if (proxyHost != null && proxyPort > 0) {
            Log log = AmazonHttpClient.log;
            log.info("Configuring Proxy. Proxy Host: " + proxyHost + " Proxy Port: " + proxyPort);
            sdkHttpClient.getParams().setParameter("http.route.default-proxy", new HttpHost(proxyHost, proxyPort));
            String proxyUsername = clientConfiguration.getProxyUsername();
            String proxyPassword = clientConfiguration.getProxyPassword();
            String proxyDomain = clientConfiguration.getProxyDomain();
            String proxyWorkstation = clientConfiguration.getProxyWorkstation();
            if (proxyUsername != null && proxyPassword != null) {
                sdkHttpClient.getCredentialsProvider().setCredentials(new AuthScope(proxyHost, proxyPort), new NTCredentials(proxyUsername, proxyPassword, proxyWorkstation, proxyDomain));
            }
        }
        return sdkHttpClient;
    }
}
