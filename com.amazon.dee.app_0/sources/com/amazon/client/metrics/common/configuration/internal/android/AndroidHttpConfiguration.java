package com.amazon.client.metrics.common.configuration.internal.android;

import com.amazon.client.metrics.common.configuration.HttpRequestSignerType;
import com.amazon.client.metrics.common.configuration.MetricsConfigurationException;
import com.amazon.client.metrics.common.configuration.internal.IHttpConfiguration;
import com.amazon.client.metrics.thirdparty.configuration.HttpConfiguration;
/* loaded from: classes11.dex */
public class AndroidHttpConfiguration implements IHttpConfiguration {
    private final HttpConfiguration mDelegateHttpConfiguration;

    public AndroidHttpConfiguration(HttpRequestSignerType httpRequestSignerType, String str, String str2) throws MetricsConfigurationException {
        try {
            this.mDelegateHttpConfiguration = new HttpConfiguration(com.amazon.client.metrics.thirdparty.configuration.HttpRequestSignerType.valueOf(httpRequestSignerType.name()), str, str2);
        } catch (com.amazon.client.metrics.thirdparty.configuration.MetricsConfigurationException e) {
            throw new MetricsConfigurationException(e.getMessage(), e);
        }
    }

    @Override // com.amazon.client.metrics.common.configuration.internal.IHttpConfiguration
    public int getConnectTimeout() {
        return this.mDelegateHttpConfiguration.getConnectTimeout();
    }

    public HttpConfiguration getDelegateHttpConfiguration() {
        return this.mDelegateHttpConfiguration;
    }

    @Override // com.amazon.client.metrics.common.configuration.internal.IHttpConfiguration
    public HttpRequestSignerType getHttpRequestSignerType() {
        return HttpRequestSignerType.valueOf(this.mDelegateHttpConfiguration.getHttpRequestSignerType().name());
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
    public void updateHttpConfigurationWithRemoteSettings(com.amazon.client.metrics.common.configuration.HttpConfiguration httpConfiguration) {
    }

    public AndroidHttpConfiguration(HttpRequestSignerType httpRequestSignerType, String str, String str2, int i, int i2, long j) throws MetricsConfigurationException {
        try {
            try {
                this.mDelegateHttpConfiguration = new HttpConfiguration(com.amazon.client.metrics.thirdparty.configuration.HttpRequestSignerType.valueOf(httpRequestSignerType.name()), str, str2, i, i2, j);
            } catch (com.amazon.client.metrics.thirdparty.configuration.MetricsConfigurationException e) {
                e = e;
                throw new MetricsConfigurationException(e.getMessage(), e);
            }
        } catch (com.amazon.client.metrics.thirdparty.configuration.MetricsConfigurationException e2) {
            e = e2;
        }
    }

    public AndroidHttpConfiguration(HttpConfiguration httpConfiguration) {
        if (httpConfiguration != null) {
            this.mDelegateHttpConfiguration = httpConfiguration;
            return;
        }
        throw new NullPointerException("Delegate ThirdParty HttpConfiguration may not be null");
    }
}
