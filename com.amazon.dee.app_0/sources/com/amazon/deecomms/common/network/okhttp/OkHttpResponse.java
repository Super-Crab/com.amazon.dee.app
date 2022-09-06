package com.amazon.deecomms.common.network.okhttp;

import androidx.annotation.NonNull;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.calling.util.HttpStatusCodeFamily;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.MetricsStatsProvider;
import com.amazon.deecomms.common.network.IHttpClient;
import com.google.common.io.CharStreams;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.List;
import java.util.Map;
import okhttp3.Response;
/* loaded from: classes12.dex */
public class OkHttpResponse implements IHttpClient.Response, MetricsStatsProvider {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, OkHttpResponse.class);
    private final HttpStatusCodeFamily family;
    private final IHttpClient.Call mCall;
    private final IHttpClient.JSONConverter mJsonConverter;
    private final Response mResponse;

    public OkHttpResponse(@NonNull IHttpClient.Call call, Response response, IHttpClient.JSONConverter jSONConverter) {
        this.mResponse = response;
        this.mJsonConverter = jSONConverter;
        this.mCall = call;
        this.family = HttpStatusCodeFamily.familyFromStatusCode(Integer.valueOf(this.mResponse.code()));
    }

    @Override // com.amazon.deecomms.common.network.IHttpClient.Response, java.lang.AutoCloseable
    public void close() {
        this.mResponse.close();
    }

    @Override // com.amazon.deecomms.common.network.IHttpClient.Response
    public int code() {
        return this.mResponse.code();
    }

    @Override // com.amazon.deecomms.common.network.IHttpClient.Response
    public <T> T convert(Class<T> cls) {
        try {
            return (T) this.mJsonConverter.fromJson(this.mResponse.body().charStream(), cls);
        } finally {
            this.mResponse.close();
        }
    }

    @Override // com.amazon.deecomms.common.network.IHttpClient.Response
    public String getBody() {
        try {
            try {
                return CharStreams.toString(this.mResponse.body().charStream());
            } catch (IOException e) {
                LOG.e("Error while getting response body", e);
                this.mResponse.close();
                return null;
            }
        } finally {
            this.mResponse.close();
        }
    }

    @Override // com.amazon.deecomms.common.network.IHttpClient.Response
    public InputStream getByteStream() {
        return this.mResponse.body().byteStream();
    }

    @Override // com.amazon.deecomms.common.network.IHttpClient.Response
    public byte[] getBytes() {
        try {
            return this.mResponse.body().bytes();
        } catch (IOException unused) {
            LOG.e("Exception occurred while getting the bytes");
            return null;
        }
    }

    @Override // com.amazon.deecomms.common.network.IHttpClient.Response
    @NonNull
    public IHttpClient.Call getCall() {
        return this.mCall;
    }

    @Override // com.amazon.deecomms.common.network.IHttpClient.Response, com.amazon.deecomms.common.metrics.MetricsStatsProvider
    public Long getCallDuration() {
        return Long.valueOf(this.mResponse.receivedResponseAtMillis() - this.mResponse.sentRequestAtMillis());
    }

    @Override // com.amazon.deecomms.common.network.IHttpClient.Response
    public Reader getCharStream() {
        return this.mResponse.body().charStream();
    }

    @Override // com.amazon.deecomms.common.metrics.MetricsStatsProvider
    public int getFailureCount() {
        return this.family.getFailureCount();
    }

    @Override // com.amazon.deecomms.common.metrics.MetricsStatsProvider
    public int getFaultCount() {
        return this.family.getFaultCount();
    }

    @Override // com.amazon.deecomms.common.network.IHttpClient.Response
    public Map<String, List<String>> getHeaders() {
        return this.mResponse.headers().toMultimap();
    }

    @Override // com.amazon.deecomms.common.metrics.MetricsStatsProvider
    public Integer getHttpResponseCode() {
        return Integer.valueOf(code());
    }

    @Override // com.amazon.deecomms.common.metrics.MetricsStatsProvider
    public String getRequestId() {
        return this.mCall.getRequestId();
    }

    @Override // com.amazon.deecomms.common.metrics.MetricsStatsProvider
    public int getSuccessCount() {
        return this.family.getSuccessCount();
    }

    @Override // com.amazon.deecomms.common.metrics.MetricsStatsProvider
    public int getUnknownCount() {
        return this.family.getUnknownCount();
    }

    @Override // com.amazon.deecomms.common.network.IHttpClient.Response
    public boolean isSuccessful() {
        return this.mResponse.isSuccessful();
    }
}
