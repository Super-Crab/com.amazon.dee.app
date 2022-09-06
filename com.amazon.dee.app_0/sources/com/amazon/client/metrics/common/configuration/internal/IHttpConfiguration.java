package com.amazon.client.metrics.common.configuration.internal;

import com.amazon.client.metrics.common.configuration.HttpConfiguration;
import com.amazon.client.metrics.common.configuration.HttpRequestSignerType;
/* loaded from: classes11.dex */
public interface IHttpConfiguration {
    int getConnectTimeout();

    HttpRequestSignerType getHttpRequestSignerType();

    int getReadTimeout();

    String getStaticCredentialUrlEndpoint();

    String getUrlEndpoint();

    long getWakeLockTimeout();

    void updateHttpConfigurationWithRemoteSettings(HttpConfiguration httpConfiguration);
}
