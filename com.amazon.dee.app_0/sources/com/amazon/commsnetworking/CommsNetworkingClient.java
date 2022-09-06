package com.amazon.commsnetworking;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService;
import com.amazon.commsnetworking.api.Callback;
import com.amazon.commsnetworking.api.INetworkRequest;
import com.amazon.commsnetworking.api.INetworkResponse;
import com.amazon.commsnetworking.api.INetworkingClient;
import com.amazon.commsnetworking.auth.AuthenticationProvider;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import okhttp3.Call;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import rx.Observable;
import rx.functions.Action0;
import rx.functions.Func0;
/* loaded from: classes12.dex */
public class CommsNetworkingClient implements INetworkingClient {
    static int defaultReadTimeout = 15;
    static int defaultWriteTimeout = 15;
    private AuthenticationProvider authProvider;
    private String host;
    private List<Interceptor> interceptors = new ArrayList();
    private AlexaCommsCoreMetricsService metricsService;
    private OkHttpClient okHttpClient;
    private int readTimeout;
    private String source;
    private int writeTimeout;

    /* loaded from: classes12.dex */
    public static class Builder implements INetworkingClient.Builder {
        AuthenticationProvider authProvider;
        String host;
        List<Interceptor> interceptors;
        AlexaCommsCoreMetricsService metricsService;
        OkHttpClient.Builder okHttpBuilder;
        OkHttpClient okHttpClient;
        Integer readTimeout;
        String source;
        Integer writeTimeout;

        @Override // com.amazon.commsnetworking.api.INetworkingClient.Builder
        public INetworkingClient build() throws IllegalArgumentException {
            if (this.source != null) {
                if (this.host != null) {
                    if (this.authProvider != null) {
                        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
                        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
                        this.okHttpBuilder.addNetworkInterceptor(httpLoggingInterceptor);
                        AlexaCommsCoreMetricsService alexaCommsCoreMetricsService = this.metricsService;
                        if (alexaCommsCoreMetricsService != null) {
                            this.okHttpBuilder.addInterceptor(new CommsMetricInterceptor(alexaCommsCoreMetricsService));
                        }
                        for (Interceptor interceptor : this.interceptors) {
                            this.okHttpBuilder.addInterceptor(interceptor);
                        }
                        this.okHttpBuilder.readTimeout(getReadTimeout(), TimeUnit.SECONDS);
                        this.okHttpBuilder.writeTimeout(getWriteTimeout(), TimeUnit.SECONDS);
                        this.okHttpClient = this.okHttpBuilder.build();
                        return new CommsNetworkingClient(this);
                    }
                    throw new IllegalArgumentException("`authProvider` must be specified using `withAuthProvider`");
                }
                throw new IllegalArgumentException("`host` must be specified using `withHost`");
            }
            throw new IllegalArgumentException("`source` must be specified using `withSource`");
        }

        int getReadTimeout() {
            Integer num = this.readTimeout;
            return num == null ? CommsNetworkingClient.defaultReadTimeout : num.intValue();
        }

        int getWriteTimeout() {
            Integer num = this.writeTimeout;
            return num == null ? CommsNetworkingClient.defaultWriteTimeout : num.intValue();
        }

        @Override // com.amazon.commsnetworking.api.INetworkingClient.Builder
        public INetworkingClient.Builder withAuthProvider(@NonNull AuthenticationProvider authenticationProvider) {
            this.authProvider = authenticationProvider;
            return this;
        }

        @Override // com.amazon.commsnetworking.api.INetworkingClient.Builder
        public INetworkingClient.Builder withHost(@NonNull String str) {
            this.host = str;
            return this;
        }

        @Override // com.amazon.commsnetworking.api.INetworkingClient.Builder
        public INetworkingClient.Builder withInterceptor(@NonNull Interceptor interceptor) {
            this.interceptors.add(interceptor);
            return this;
        }

        @Override // com.amazon.commsnetworking.api.INetworkingClient.Builder
        public INetworkingClient.Builder withMetricService(@NonNull AlexaCommsCoreMetricsService alexaCommsCoreMetricsService) {
            this.metricsService = alexaCommsCoreMetricsService;
            return this;
        }

        @Override // com.amazon.commsnetworking.api.INetworkingClient.Builder
        public INetworkingClient.Builder withReadTimeout(int i) {
            this.readTimeout = Integer.valueOf(i);
            return this;
        }

        @Override // com.amazon.commsnetworking.api.INetworkingClient.Builder
        public INetworkingClient.Builder withSource(@NonNull String str) {
            this.source = str;
            return this;
        }

        @Override // com.amazon.commsnetworking.api.INetworkingClient.Builder
        public INetworkingClient.Builder withWriteTimeout(int i) {
            this.writeTimeout = Integer.valueOf(i);
            return this;
        }

        public Builder() {
            this.interceptors = new ArrayList();
            this.okHttpBuilder = new OkHttpClient.Builder();
        }

        private Builder(CommsNetworkingClient commsNetworkingClient) {
            this.interceptors = new ArrayList();
            this.okHttpBuilder = new OkHttpClient.Builder();
            this.source = commsNetworkingClient.getSource();
            this.host = commsNetworkingClient.getHost();
            this.authProvider = commsNetworkingClient.getAuthProvider();
            this.readTimeout = Integer.valueOf(commsNetworkingClient.getReadTimeout());
            this.writeTimeout = Integer.valueOf(commsNetworkingClient.getWriteTimeout());
            this.metricsService = commsNetworkingClient.getMetricService();
            this.interceptors.addAll(commsNetworkingClient.interceptors);
        }
    }

    CommsNetworkingClient(@NonNull Builder builder) {
        this.source = builder.source;
        this.host = builder.host;
        this.authProvider = builder.authProvider;
        this.readTimeout = builder.getReadTimeout();
        this.writeTimeout = builder.getWriteTimeout();
        this.metricsService = builder.metricsService;
        this.interceptors.addAll(builder.interceptors);
        this.okHttpClient = builder.okHttpClient;
    }

    @VisibleForTesting
    Call createCall(@NonNull Request request) {
        return this.okHttpClient.newCall(request);
    }

    @Override // com.amazon.commsnetworking.api.INetworkingClient
    public INetworkRequest delete(@NonNull String str, @NonNull String str2) {
        return CommsNetworkRequest.delete(this.source, this.host, str, str2);
    }

    @Override // com.amazon.commsnetworking.api.INetworkingClient
    public INetworkResponse execute(@NonNull INetworkRequest iNetworkRequest) throws NetworkException {
        this.authProvider.authenticateRequest(iNetworkRequest);
        try {
            CommsNetworkResponse commsNetworkResponse = new CommsNetworkResponse(iNetworkRequest, createCall(iNetworkRequest).execute());
            if (commsNetworkResponse.isSuccessful()) {
                return commsNetworkResponse;
            }
            if (iNetworkRequest.canRetry(commsNetworkResponse)) {
                iNetworkRequest.prepareForRetry(commsNetworkResponse);
                return execute(iNetworkRequest);
            }
            throw new NetworkException(iNetworkRequest, commsNetworkResponse);
        } catch (IOException e) {
            throw new NetworkException(iNetworkRequest, e);
        }
    }

    @Override // com.amazon.commsnetworking.api.INetworkingClient
    public Observable<INetworkResponse> executeAsObservable(@NonNull final INetworkRequest iNetworkRequest) {
        Observable defer = Observable.defer(new Func0() { // from class: com.amazon.commsnetworking.-$$Lambda$CommsNetworkingClient$zb67MDEStV-CvgMpdu09HCb-csY
            @Override // rx.functions.Func0, java.util.concurrent.Callable
            /* renamed from: call */
            public final Object mo13098call() {
                return CommsNetworkingClient.this.lambda$executeAsObservable$0$CommsNetworkingClient(iNetworkRequest);
            }
        });
        iNetworkRequest.getClass();
        return defer.doOnUnsubscribe(new Action0() { // from class: com.amazon.commsnetworking.-$$Lambda$hJm_BCLctM-faHh2NSGWmbpw_VU
            @Override // rx.functions.Action0
            public final void call() {
                INetworkRequest.this.cancel();
            }
        });
    }

    @Override // com.amazon.commsnetworking.api.INetworkingClient
    public void executeWithCallback(@NonNull final INetworkRequest iNetworkRequest, @NonNull final Callback callback) {
        this.authProvider.authenticateRequest(iNetworkRequest);
        createCall(iNetworkRequest).enqueue(new okhttp3.Callback() { // from class: com.amazon.commsnetworking.CommsNetworkingClient.1
            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException iOException) {
                Callback callback2 = callback;
                INetworkRequest iNetworkRequest2 = iNetworkRequest;
                callback2.onFailure(iNetworkRequest2, new NetworkException(iNetworkRequest2, iOException));
            }

            @Override // okhttp3.Callback
            public void onResponse(Call call, Response response) throws IOException {
                CommsNetworkResponse commsNetworkResponse = new CommsNetworkResponse(iNetworkRequest, response);
                if (commsNetworkResponse.isSuccessful()) {
                    callback.onResult(iNetworkRequest, commsNetworkResponse);
                } else if (iNetworkRequest.canRetry(commsNetworkResponse)) {
                    iNetworkRequest.prepareForRetry(commsNetworkResponse);
                    CommsNetworkingClient.this.executeWithCallback(iNetworkRequest, callback);
                } else {
                    Callback callback2 = callback;
                    INetworkRequest iNetworkRequest2 = iNetworkRequest;
                    callback2.onFailure(iNetworkRequest2, new NetworkException(iNetworkRequest2, commsNetworkResponse));
                }
            }
        });
    }

    @Override // com.amazon.commsnetworking.api.INetworkingClient
    public INetworkRequest get(@NonNull String str, @NonNull String str2) {
        return CommsNetworkRequest.get(this.source, this.host, str, str2);
    }

    @Override // com.amazon.commsnetworking.api.INetworkingClient
    @NonNull
    public AuthenticationProvider getAuthProvider() {
        return this.authProvider;
    }

    @Override // com.amazon.commsnetworking.api.INetworkingClient
    @NonNull
    public String getHost() {
        return this.host;
    }

    @Override // com.amazon.commsnetworking.api.INetworkingClient
    public List<Interceptor> getInterceptors() {
        return this.interceptors;
    }

    @Nullable
    @VisibleForTesting
    AlexaCommsCoreMetricsService getMetricService() {
        return this.metricsService;
    }

    @Override // com.amazon.commsnetworking.api.INetworkingClient
    public OkHttpClient getOkHttpClient() {
        return this.okHttpClient;
    }

    @Override // com.amazon.commsnetworking.api.INetworkingClient
    public int getReadTimeout() {
        return this.readTimeout;
    }

    @Override // com.amazon.commsnetworking.api.INetworkingClient
    @NonNull
    public String getSource() {
        return this.source;
    }

    @Override // com.amazon.commsnetworking.api.INetworkingClient
    public int getWriteTimeout() {
        return this.writeTimeout;
    }

    public /* synthetic */ Observable lambda$executeAsObservable$0$CommsNetworkingClient(INetworkRequest iNetworkRequest) {
        try {
            return Observable.just(execute(iNetworkRequest));
        } catch (NetworkException e) {
            return Observable.error(e);
        }
    }

    @Override // com.amazon.commsnetworking.api.INetworkingClient
    public INetworkingClient.Builder newBuilder() {
        return new Builder();
    }

    @Override // com.amazon.commsnetworking.api.INetworkingClient
    public INetworkRequest patch(@NonNull String str, @NonNull String str2) {
        return CommsNetworkRequest.patch(this.source, this.host, str, str2);
    }

    @Override // com.amazon.commsnetworking.api.INetworkingClient
    public INetworkRequest post(@NonNull String str, @NonNull String str2) {
        return CommsNetworkRequest.post(this.source, this.host, str, str2);
    }

    @Override // com.amazon.commsnetworking.api.INetworkingClient
    public INetworkRequest put(@NonNull String str, @NonNull String str2) {
        return CommsNetworkRequest.put(this.source, this.host, str, str2);
    }

    private Call createCall(@NonNull INetworkRequest iNetworkRequest) {
        Call createCall = createCall(iNetworkRequest.buildRequest());
        iNetworkRequest.setCall(createCall);
        return createCall;
    }
}
