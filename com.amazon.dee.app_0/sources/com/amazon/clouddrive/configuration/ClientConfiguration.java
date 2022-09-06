package com.amazon.clouddrive.configuration;

import com.amazon.clouddrive.metrics.MetricListener;
import com.amazon.clouddrive.utils.AssertUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.net.ssl.HostnameVerifier;
import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import org.apache.http.conn.ssl.SSLSocketFactory;
/* loaded from: classes11.dex */
public class ClientConfiguration {
    private static final int DEFAULT_CONNECTION_TIMEOUT_MS = 60000;
    private static final String DEFAULT_MASTER_ENDPOINT = "https://drive.amazonaws.com/drive/v1/";
    private static final int DEFAULT_MAX_ERROR_RETRY = 5;
    private static final int DEFAULT_MAX_READ_TIMEOUT_MS = 60000;
    private final String mApplicationId;
    private final List<ConnectionSpec> mConnectionSpecs;
    private final int mConnectionTimeOutMillis;
    private final HostnameVerifier mHostNameVerifier;
    private final OkHttpClient mHttpClient;
    private final String mMasterEndpoint;
    private final int mMaxErrorRetry;
    private final MetricListener mMetricListener;
    private final int mReadTimeOutMillis;
    private final String mUserAgent;
    private static final List<ConnectionSpec> DEFAULT_CONNECTION_SPEC = Collections.singletonList(ConnectionSpec.MODERN_TLS);
    private static final HostnameVerifier DEFAULT_HOSTNAME_VERIFIER = SSLSocketFactory.STRICT_HOSTNAME_VERIFIER;

    /* loaded from: classes11.dex */
    public static class Builder {
        private String mApplicationId;
        private OkHttpClient mHttpClient;
        private MetricListener mMetricListener;
        private String mUserAgent;
        private int mReadTimeOutMillis = 60000;
        private int mConnectionTimeOutMillis = 60000;
        private int mMaxErrorRetry = 5;
        private String mMasterEndpoint = ClientConfiguration.DEFAULT_MASTER_ENDPOINT;
        private List<ConnectionSpec> mConnectionSpecs = ClientConfiguration.DEFAULT_CONNECTION_SPEC;
        private HostnameVerifier mHostNameVerifier = ClientConfiguration.DEFAULT_HOSTNAME_VERIFIER;

        public ClientConfiguration build() {
            return new ClientConfiguration(this);
        }

        public Builder setApplicationId(String str) {
            this.mApplicationId = str;
            return this;
        }

        public Builder setConnectionSpecs(List<ConnectionSpec> list) {
            this.mConnectionSpecs = Collections.unmodifiableList(new ArrayList(list));
            return this;
        }

        public Builder setConnectionTimeOutMillis(int i) {
            this.mConnectionTimeOutMillis = i;
            return this;
        }

        public Builder setHostNameVerifier(HostnameVerifier hostnameVerifier) {
            this.mHostNameVerifier = hostnameVerifier;
            return this;
        }

        public Builder setHttpClient(OkHttpClient okHttpClient) {
            this.mHttpClient = okHttpClient;
            return this;
        }

        public Builder setMasterEndpoint(String str) {
            this.mMasterEndpoint = str;
            return this;
        }

        public Builder setMaxErrorRetry(int i) {
            this.mMaxErrorRetry = i;
            return this;
        }

        public Builder setMetricListener(MetricListener metricListener) {
            this.mMetricListener = metricListener;
            return this;
        }

        public Builder setReadTimeOutMillis(int i) {
            this.mReadTimeOutMillis = i;
            return this;
        }

        public Builder setUserAgent(String str) {
            this.mUserAgent = str;
            return this;
        }
    }

    protected ClientConfiguration(Builder builder) {
        AssertUtils.assertNotNull(builder.mUserAgent, "UserAgent was null.");
        AssertUtils.assertNotNull(builder.mHttpClient, "OkHttpClient was null.");
        AssertUtils.assertNotNull(builder.mMasterEndpoint, "MasterEndpoint was null.");
        AssertUtils.assertNotNullOrEmpty(builder.mConnectionSpecs, "ConnectionSpecs was null or empty.");
        AssertUtils.assertNotNull(builder.mHostNameVerifier, "HostNameVerifier was null.");
        if (builder.mMaxErrorRetry >= 0) {
            this.mUserAgent = builder.mUserAgent.length() > 982 ? builder.mUserAgent.substring(0, 982) : builder.mUserAgent;
            this.mHttpClient = builder.mHttpClient;
            this.mMasterEndpoint = builder.mMasterEndpoint;
            this.mConnectionSpecs = builder.mConnectionSpecs;
            this.mHostNameVerifier = builder.mHostNameVerifier;
            this.mApplicationId = builder.mApplicationId;
            this.mMetricListener = builder.mMetricListener;
            this.mConnectionTimeOutMillis = builder.mConnectionTimeOutMillis;
            this.mReadTimeOutMillis = builder.mReadTimeOutMillis;
            this.mMaxErrorRetry = builder.mMaxErrorRetry;
            return;
        }
        throw new IllegalArgumentException("The maxErrorRetry must be greater than or equal to zero.");
    }

    public String getApplicationId() {
        return this.mApplicationId;
    }

    public List<ConnectionSpec> getConnectionSpecs() {
        return this.mConnectionSpecs;
    }

    public int getConnectionTimeOutMillis() {
        return this.mConnectionTimeOutMillis;
    }

    public HostnameVerifier getHostNameVerifier() {
        return this.mHostNameVerifier;
    }

    public OkHttpClient getHttpClient() {
        return this.mHttpClient;
    }

    public String getMasterEndpoint() {
        return this.mMasterEndpoint;
    }

    public int getMaxErrorRetry() {
        return this.mMaxErrorRetry;
    }

    public MetricListener getMetricListener() {
        return this.mMetricListener;
    }

    public int getReadTimeOutMillis() {
        return this.mReadTimeOutMillis;
    }

    public String getUserAgent() {
        return this.mUserAgent;
    }

    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.setUserAgent(this.mUserAgent);
        builder.setHttpClient(this.mHttpClient);
        builder.setMasterEndpoint(this.mMasterEndpoint);
        builder.setConnectionSpecs(this.mConnectionSpecs);
        builder.setHostNameVerifier(this.mHostNameVerifier);
        builder.setApplicationId(this.mApplicationId);
        builder.setMetricListener(this.mMetricListener);
        builder.setConnectionTimeOutMillis(this.mConnectionTimeOutMillis);
        builder.setReadTimeOutMillis(this.mReadTimeOutMillis);
        builder.setMaxErrorRetry(this.mMaxErrorRetry);
        return builder;
    }
}
