package com.amazon.client.metrics.common.configuration.internal;

import com.amazon.client.metrics.common.configuration.HttpConfiguration;
import com.amazon.client.metrics.common.configuration.HttpRequestSignerType;
/* loaded from: classes11.dex */
public class NullHttpConfiguration implements IHttpConfiguration {
    @Override // com.amazon.client.metrics.common.configuration.internal.IHttpConfiguration
    public int getConnectTimeout() {
        return 0;
    }

    @Override // com.amazon.client.metrics.common.configuration.internal.IHttpConfiguration
    public HttpRequestSignerType getHttpRequestSignerType() {
        return null;
    }

    @Override // com.amazon.client.metrics.common.configuration.internal.IHttpConfiguration
    public int getReadTimeout() {
        return 0;
    }

    @Override // com.amazon.client.metrics.common.configuration.internal.IHttpConfiguration
    public String getStaticCredentialUrlEndpoint() {
        return null;
    }

    @Override // com.amazon.client.metrics.common.configuration.internal.IHttpConfiguration
    public String getUrlEndpoint() {
        return null;
    }

    @Override // com.amazon.client.metrics.common.configuration.internal.IHttpConfiguration
    public long getWakeLockTimeout() {
        return 0L;
    }

    @Override // com.amazon.client.metrics.common.configuration.internal.IHttpConfiguration
    public void updateHttpConfigurationWithRemoteSettings(HttpConfiguration httpConfiguration) {
    }
}
