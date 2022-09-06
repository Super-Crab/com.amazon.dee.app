package com.amazon.client.metrics.thirdparty.configuration;

import java.util.concurrent.TimeUnit;
/* loaded from: classes11.dex */
public class HttpConfiguration {
    public static final int DEFAULT_CONNECT_TIMEOUT_MILLIS = 10000;
    protected static final String DEFAULT_HTTP_URL_ENDPOINT_DEVO = "https://dp-mont.integ.amazon.com:443";
    protected static final String DEFAULT_HTTP_URL_ENDPOINT_PROD = "https://device-metrics-us.amazon.com:443";
    public static final int DEFAULT_READ_TIMEOUT_MILLIS = 10000;
    protected static final String DEFAULT_STATIC_CREDENTIAL_HTTP_URL_ENDPOINT = "https://device-metrics-us-2.amazon.com:443";
    public static final long DEFAULT_WAKE_LOCK_TIMEOUT_MILLIS = TimeUnit.MILLISECONDS.convert(5, TimeUnit.MINUTES);
    private final int mConnectTimeout;
    private final HttpRequestSignerType mHttpRequestSignerType;
    private final int mReadTimeout;
    private final String mStaticCredentialUrlEndpoint;
    private final String mUrlEndpoint;
    private final long mWakeLockTimeout;

    public HttpConfiguration(HttpRequestSignerType httpRequestSignerType, String str, String str2) throws MetricsConfigurationException {
        this(httpRequestSignerType, str, str2, 10000, 10000, DEFAULT_WAKE_LOCK_TIMEOUT_MILLIS);
    }

    public int getConnectTimeout() {
        return this.mConnectTimeout;
    }

    public HttpRequestSignerType getHttpRequestSignerType() {
        return this.mHttpRequestSignerType;
    }

    public int getReadTimeout() {
        return this.mReadTimeout;
    }

    public String getStaticCredentialUrlEndpoint() {
        return this.mStaticCredentialUrlEndpoint;
    }

    public String getUrlEndpoint() {
        return this.mUrlEndpoint;
    }

    public long getWakeLockTimeout() {
        return this.mWakeLockTimeout;
    }

    public void updateHttpConfigurationWithRemoteSettings(HttpConfiguration httpConfiguration) {
        throw new UnsupportedOperationException("Updating HTTP configuration is not supported on Metrics 3P library");
    }

    public HttpConfiguration(HttpRequestSignerType httpRequestSignerType, String str, String str2, int i, int i2, long j) throws MetricsConfigurationException {
        if (httpRequestSignerType != null) {
            if (str == null) {
                throw new MetricsConfigurationException("UrlEndpoint is null in configuration");
            }
            if (str2 == null) {
                throw new MetricsConfigurationException("UrlEndpoint is null in configuration");
            }
            if (i >= 0 && i2 >= 0 && j >= 0) {
                this.mHttpRequestSignerType = httpRequestSignerType;
                this.mUrlEndpoint = str;
                this.mStaticCredentialUrlEndpoint = str2;
                this.mConnectTimeout = i;
                this.mReadTimeout = i2;
                this.mWakeLockTimeout = j;
                return;
            }
            throw new MetricsConfigurationException("Negative timeout in configuration");
        }
        throw new MetricsConfigurationException("HttpRequestSignerType is null in configuration");
    }
}
