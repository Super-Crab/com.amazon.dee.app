package com.dee.app.http;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.dee.app.metrics.MetricDescriptor;
import com.dee.app.metrics.MetricsTimer;
import okhttp3.Response;
import rx.Observable;
/* loaded from: classes2.dex */
public interface CoralService {

    /* loaded from: classes2.dex */
    public interface Call<T> {
        <E> Call<E> as(@NonNull Class<E> cls);

        Call<Response> asRaw();

        void cancel();

        void enqueue(Callback<T> callback);

        T execute() throws CoralServiceException;

        boolean isCanceled();

        Observable<T> toObservable();
    }

    /* loaded from: classes2.dex */
    public interface Callback<T> {
        void onFailure(Call<T> call, CoralServiceException coralServiceException);

        void onResult(Call<T> call, T t);
    }

    /* loaded from: classes2.dex */
    public interface Request {

        /* loaded from: classes2.dex */
        public interface Metadata {
            String getDomain();

            MetricDescriptor getMetricDescriptor();

            String getMetricsSource();

            MetricsTimer getMetricsTimer();

            String getOperationName();

            /* renamed from: setDomain */
            Metadata mo6800setDomain(String str);

            /* renamed from: setMetricDescriptor */
            Metadata mo6801setMetricDescriptor(MetricDescriptor metricDescriptor);

            /* renamed from: setMetricsSource */
            Metadata mo6802setMetricsSource(String str);

            /* renamed from: setMetricsTimer */
            Metadata mo6803setMetricsTimer(MetricsTimer metricsTimer);

            /* renamed from: setOperationName */
            Metadata mo6804setOperationName(String str);
        }

        <T> Call<T> delete();

        <T> Call<T> delete(@Nullable Object obj);

        <T> Call<T> get();

        @NonNull
        Metadata getMetadata();

        Timeout getTimeout();

        <T> Call<T> patch(@Nullable Object obj);

        <T> Call<T> post(@Nullable Object obj);

        <T> Call<T> put(@Nullable Object obj);

        Request withHeader(@NonNull String str, String str2);
    }

    /* loaded from: classes2.dex */
    public interface Timeout {
        Integer getConnectionTimeout();

        Integer getReadTimeout();
    }

    Request request(@NonNull String str);

    Request request(@NonNull String str, Timeout timeout);
}
