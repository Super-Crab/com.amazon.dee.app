package com.amazon.commsnetworking.api;

import androidx.annotation.NonNull;
import com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService;
import com.amazon.commsnetworking.NetworkException;
import com.amazon.commsnetworking.auth.AuthenticationProvider;
import java.util.List;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import rx.Observable;
/* loaded from: classes12.dex */
public interface INetworkingClient {

    /* loaded from: classes12.dex */
    public interface Builder {
        INetworkingClient build();

        Builder withAuthProvider(@NonNull AuthenticationProvider authenticationProvider);

        Builder withHost(@NonNull String str);

        Builder withInterceptor(@NonNull Interceptor interceptor);

        Builder withMetricService(@NonNull AlexaCommsCoreMetricsService alexaCommsCoreMetricsService);

        Builder withReadTimeout(int i);

        Builder withSource(@NonNull String str);

        Builder withWriteTimeout(int i);
    }

    INetworkRequest delete(@NonNull String str, @NonNull String str2);

    INetworkResponse execute(@NonNull INetworkRequest iNetworkRequest) throws NetworkException;

    Observable<INetworkResponse> executeAsObservable(@NonNull INetworkRequest iNetworkRequest);

    void executeWithCallback(@NonNull INetworkRequest iNetworkRequest, @NonNull Callback callback);

    INetworkRequest get(@NonNull String str, @NonNull String str2);

    @NonNull
    AuthenticationProvider getAuthProvider();

    @NonNull
    String getHost();

    List<Interceptor> getInterceptors();

    OkHttpClient getOkHttpClient();

    int getReadTimeout();

    @NonNull
    String getSource();

    int getWriteTimeout();

    Builder newBuilder();

    INetworkRequest patch(@NonNull String str, @NonNull String str2);

    INetworkRequest post(@NonNull String str, @NonNull String str2);

    INetworkRequest put(@NonNull String str, @NonNull String str2);
}
