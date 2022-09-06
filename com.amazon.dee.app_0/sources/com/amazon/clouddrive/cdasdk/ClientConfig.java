package com.amazon.clouddrive.cdasdk;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.clouddrive.cdasdk.aps.common.DevicePlatform;
import java.security.InvalidParameterException;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import javax.net.SocketFactory;
/* loaded from: classes11.dex */
public final class ClientConfig {
    @NonNull
    private final Context appContext;
    private String appVersionName;
    @NonNull
    private final String applicationId;
    private DevicePlatform apsDevicePlatform;
    private String clientName;
    private long connectTimeoutMs;
    @NonNull
    private EndpointConfiguration endpointConfiguration;
    private long readTimeoutMs;
    private RequestLoggingConfig requestLoggingConfig;
    private SdkMetrics sdkMetrics;
    private SocketFactory socketFactory;
    private SSLConfiguration sslConfiguration;
    @NonNull
    private final TokenProvider tokenProvider;
    @NonNull
    private final String userAgent;
    private long writeTimeoutMs;

    /* loaded from: classes11.dex */
    public static class Builder {
        @NonNull
        private final Context appContext;
        @Nullable
        private String appVersionName;
        @NonNull
        private final String applicationId;
        @NonNull
        private final String clientName;
        @Nullable
        private DevicePlatform devicePlatform;
        @NonNull
        private final TokenProvider tokenProvider;
        @NonNull
        private final String userAgent;
        @NonNull
        private EndpointConfiguration endpointConfiguration = new DefaultEndpointConfiguration();
        private long connectTimeoutMs = TimeUnit.SECONDS.toMillis(60);
        private long readTimeoutMs = TimeUnit.SECONDS.toMillis(60);
        private long writeTimeoutMs = TimeUnit.SECONDS.toMillis(60);
        @NonNull
        private RequestLoggingConfig requestLoggingConfig = new RequestLoggingConfig();
        @NonNull
        private SocketFactory socketFactory = SocketFactory.getDefault();
        @NonNull
        private SSLConfiguration sslConfiguration = SSLConfiguration.SAFE;
        @NonNull
        private SdkMetrics sdkMetrics = new NoOpSdkMetrics();

        public Builder(@NonNull Context context, @NonNull TokenProvider tokenProvider, @NonNull String str, @NonNull String str2, @NonNull String str3) {
            this.appContext = context;
            this.tokenProvider = tokenProvider;
            this.userAgent = str;
            this.applicationId = str2;
            this.clientName = str3;
        }

        @NonNull
        public ClientConfig build() throws InvalidParameterException {
            ClientConfig clientConfig = new ClientConfig(this.appContext, this.tokenProvider, this.userAgent, this.applicationId, this.endpointConfiguration, this.clientName);
            clientConfig.setRequestLoggingConfig(this.requestLoggingConfig);
            clientConfig.setSocketFactory(this.socketFactory);
            clientConfig.setSslConfiguration(this.sslConfiguration);
            clientConfig.setConnectTimeoutMs(this.connectTimeoutMs);
            clientConfig.setReadTimeoutMs(this.readTimeoutMs);
            clientConfig.setWriteTimeoutMs(this.writeTimeoutMs);
            clientConfig.setSdkMetrics(this.sdkMetrics);
            clientConfig.setApsDevicePlatform(this.devicePlatform);
            clientConfig.setAppVersionName(this.appVersionName);
            return clientConfig;
        }

        public Builder withAPSDevicePlatform(@NonNull DevicePlatform devicePlatform) {
            this.devicePlatform = devicePlatform;
            return this;
        }

        public Builder withAppVersionName(@NonNull String str) {
            this.appVersionName = str;
            return this;
        }

        public Builder withConnectTimeoutMs(long j) {
            this.connectTimeoutMs = j;
            return this;
        }

        public Builder withEndpointConfiguration(@NonNull EndpointConfiguration endpointConfiguration) {
            this.endpointConfiguration = endpointConfiguration;
            return this;
        }

        public Builder withReadTimeoutMs(long j) {
            this.readTimeoutMs = j;
            return this;
        }

        public Builder withRequestLoggingConfig(@NonNull RequestLoggingConfig requestLoggingConfig) {
            this.requestLoggingConfig = requestLoggingConfig;
            return this;
        }

        public Builder withSSLConfiguration(@NonNull SSLConfiguration sSLConfiguration) {
            this.sslConfiguration = sSLConfiguration;
            return this;
        }

        public Builder withSdkMetrics(@NonNull SdkMetrics sdkMetrics) {
            this.sdkMetrics = sdkMetrics;
            return this;
        }

        public Builder withSocketFactory(@NonNull SocketFactory socketFactory) {
            this.socketFactory = socketFactory;
            return this;
        }

        public Builder withWriteTimeoutMs(long j) {
            this.writeTimeoutMs = j;
            return this;
        }
    }

    /* loaded from: classes11.dex */
    public enum SSLConfiguration {
        SAFE
    }

    public Context getAppContext() {
        return this.appContext;
    }

    public String getAppVersionName() {
        return this.appVersionName;
    }

    public String getApplicationId() {
        return this.applicationId;
    }

    public DevicePlatform getApsDevicePlatform() {
        return this.apsDevicePlatform;
    }

    public String getClientName() {
        return this.clientName;
    }

    public long getConnectTimeoutMs() {
        return this.connectTimeoutMs;
    }

    public EndpointConfiguration getEndpointConfiguration() {
        return this.endpointConfiguration;
    }

    public long getReadTimeoutMs() {
        return this.readTimeoutMs;
    }

    public RequestLoggingConfig getRequestLoggingConfig() {
        return this.requestLoggingConfig;
    }

    public SdkMetrics getSdkMetrics() {
        return this.sdkMetrics;
    }

    public SocketFactory getSocketFactory() {
        return this.socketFactory;
    }

    public SSLConfiguration getSslConfiguration() {
        return this.sslConfiguration;
    }

    public TokenProvider getTokenProvider() {
        return this.tokenProvider;
    }

    public String getUserAgent() {
        return this.userAgent;
    }

    public long getWriteTimeoutMs() {
        return this.writeTimeoutMs;
    }

    public void setAppVersionName(String str) {
        this.appVersionName = str;
    }

    public void setApsDevicePlatform(DevicePlatform devicePlatform) {
        this.apsDevicePlatform = devicePlatform;
    }

    public void setClientName(String str) {
        this.clientName = str;
    }

    public void setConnectTimeoutMs(long j) {
        this.connectTimeoutMs = j;
    }

    public void setEndpointConfiguration(EndpointConfiguration endpointConfiguration) {
        this.endpointConfiguration = endpointConfiguration;
    }

    public void setReadTimeoutMs(long j) {
        this.readTimeoutMs = j;
    }

    public void setRequestLoggingConfig(RequestLoggingConfig requestLoggingConfig) {
        this.requestLoggingConfig = requestLoggingConfig;
    }

    public void setSdkMetrics(SdkMetrics sdkMetrics) {
        this.sdkMetrics = sdkMetrics;
    }

    public void setSocketFactory(SocketFactory socketFactory) {
        this.socketFactory = socketFactory;
    }

    public void setSslConfiguration(SSLConfiguration sSLConfiguration) {
        this.sslConfiguration = sSLConfiguration;
    }

    public void setWriteTimeoutMs(long j) {
        this.writeTimeoutMs = j;
    }

    private ClientConfig(@NonNull Context context, @NonNull TokenProvider tokenProvider, @NonNull String str, @NonNull String str2, @NonNull EndpointConfiguration endpointConfiguration, @NonNull String str3) {
        this.appContext = context;
        this.tokenProvider = tokenProvider;
        this.userAgent = str;
        this.applicationId = str2;
        this.endpointConfiguration = endpointConfiguration;
        this.clientName = str3;
    }
}
