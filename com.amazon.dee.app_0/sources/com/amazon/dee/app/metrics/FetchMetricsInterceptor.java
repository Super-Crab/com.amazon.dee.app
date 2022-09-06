package com.amazon.dee.app.metrics;

import android.annotation.SuppressLint;
import androidx.annotation.NonNull;
import com.amazon.dee.app.services.metrics.DefaultMetricsTimer;
import com.dee.app.http.CoralService;
import com.dee.app.http.CoralServiceException;
import com.dee.app.http.HttpCoralService;
import com.dee.app.metrics.MetricDescriptor;
import com.dee.app.metrics.MetricName;
import com.dee.app.metrics.MetricsServiceV2;
import com.dee.app.metrics.MetricsTimer;
import okhttp3.Response;
/* loaded from: classes12.dex */
public class FetchMetricsInterceptor implements HttpCoralService.ResponseInterceptor, HttpCoralService.RequestInterceptor {
    private static final String TAG = "FetchMetricsInterceptor";
    private MetricsServiceV2 metricsService;

    public FetchMetricsInterceptor(@NonNull MetricsServiceV2 metricsServiceV2) {
        this.metricsService = metricsServiceV2;
    }

    private boolean isServerError(@NonNull Response response) {
        return response.code() >= 400;
    }

    @Override // com.dee.app.http.HttpCoralService.RequestInterceptor
    public void intercept(@NonNull HttpCoralService.HttpRequest httpRequest) {
        CoralService.Request.Metadata metadata = httpRequest.getMetadata();
        MetricDescriptor createMetricDescriptor = httpRequest.createMetricDescriptor(metadata.getMetricsSource());
        metadata.mo6803setMetricsTimer(new DefaultMetricsTimer(createMetricDescriptor.getName().toString(), createMetricDescriptor.getComponentName().toString(), createMetricDescriptor.getCustomEntries(), 0L, true));
        metadata.mo6801setMetricDescriptor(createMetricDescriptor);
    }

    @Override // com.dee.app.http.HttpCoralService.ResponseInterceptor
    @SuppressLint({"DefaultLocale"})
    public <T> T intercept(@NonNull HttpCoralService.ResponseInterceptor.Chain<T> chain) throws CoralServiceException {
        Response response = chain.response();
        CoralService.Request.Metadata metadata = chain.request().getMetadata();
        MetricDescriptor metricDescriptor = metadata.getMetricDescriptor();
        MetricsTimer metricsTimer = metadata.getMetricsTimer();
        metricsTimer.finishTimer();
        this.metricsService.recordTime(metricDescriptor, metricsTimer.getElapsedTime());
        if (isServerError(response)) {
            MetricName name = metricDescriptor.getName();
            MetricName metricName = new MetricName(name);
            metricName.setMetricId(String.format("%s_%d", name.getMetricId(), Integer.valueOf(response.code())));
            MetricDescriptor metricDescriptor2 = new MetricDescriptor(metricDescriptor);
            metricDescriptor2.setName(metricName);
            this.metricsService.recordCount(metricDescriptor2, 1.0d);
        }
        this.metricsService.recordSuccess(metricDescriptor);
        return chain.proceed(response);
    }
}
