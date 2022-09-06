package com.amazon.client.metrics.common.configuration;

import com.amazon.client.metrics.common.configuration.internal.IHttpConfiguration;
import com.amazon.client.metrics.common.configuration.internal.NullHttpConfiguration;
import com.amazon.client.metrics.common.configuration.internal.android.AndroidHttpConfiguration;
import com.amazon.client.metrics.common.internal.util.DevicePlatformIdentifierUtil;
/* loaded from: classes11.dex */
public class HttpConfiguration implements IHttpConfiguration {
    protected static final String DEFAULT_HTTP_URL_ENDPOINT_DEVO = "https://dp-mont.integ.amazon.com:443";
    protected static final String DEFAULT_HTTP_URL_ENDPOINT_PROD = "https://device-metrics-us.amazon.com:443";
    protected static final String DEFAULT_STATIC_CREDENTIAL_HTTP_URL_ENDPOINT = "https://device-metrics-us-2.amazon.com:443";
    private final IHttpConfiguration mDelegateHttpConfiguration;

    public HttpConfiguration(HttpRequestSignerType httpRequestSignerType, String str, String str2) throws MetricsConfigurationException {
        if (DevicePlatformIdentifierUtil.getInstance().isDevicePlatformAndroid()) {
            this.mDelegateHttpConfiguration = new AndroidHttpConfiguration(httpRequestSignerType, str, str2);
        } else {
            this.mDelegateHttpConfiguration = new NullHttpConfiguration();
        }
    }

    @Override // com.amazon.client.metrics.common.configuration.internal.IHttpConfiguration
    public int getConnectTimeout() {
        return this.mDelegateHttpConfiguration.getConnectTimeout();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public IHttpConfiguration getDelegateHttpConfiguration() {
        return this.mDelegateHttpConfiguration;
    }

    @Override // com.amazon.client.metrics.common.configuration.internal.IHttpConfiguration
    public HttpRequestSignerType getHttpRequestSignerType() {
        return this.mDelegateHttpConfiguration.getHttpRequestSignerType();
    }

    @Override // com.amazon.client.metrics.common.configuration.internal.IHttpConfiguration
    public int getReadTimeout() {
        return this.mDelegateHttpConfiguration.getReadTimeout();
    }

    @Override // com.amazon.client.metrics.common.configuration.internal.IHttpConfiguration
    public String getStaticCredentialUrlEndpoint() {
        return this.mDelegateHttpConfiguration.getStaticCredentialUrlEndpoint();
    }

    @Override // com.amazon.client.metrics.common.configuration.internal.IHttpConfiguration
    public String getUrlEndpoint() {
        return this.mDelegateHttpConfiguration.getUrlEndpoint();
    }

    @Override // com.amazon.client.metrics.common.configuration.internal.IHttpConfiguration
    public long getWakeLockTimeout() {
        return this.mDelegateHttpConfiguration.getWakeLockTimeout();
    }

    @Override // com.amazon.client.metrics.common.configuration.internal.IHttpConfiguration
    public void updateHttpConfigurationWithRemoteSettings(HttpConfiguration httpConfiguration) {
    }

    public HttpConfiguration(HttpRequestSignerType httpRequestSignerType, String str, String str2, int i, int i2, long j) throws MetricsConfigurationException {
        if (DevicePlatformIdentifierUtil.getInstance().isDevicePlatformAndroid()) {
            this.mDelegateHttpConfiguration = new AndroidHttpConfiguration(httpRequestSignerType, str, str2, i, i2, j);
        } else {
            this.mDelegateHttpConfiguration = new NullHttpConfiguration();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public HttpConfiguration(IHttpConfiguration iHttpConfiguration) {
        if (iHttpConfiguration != null) {
            this.mDelegateHttpConfiguration = iHttpConfiguration;
            return;
        }
        throw new NullPointerException("Delegate HttpConfiguration may not be null");
    }
}
