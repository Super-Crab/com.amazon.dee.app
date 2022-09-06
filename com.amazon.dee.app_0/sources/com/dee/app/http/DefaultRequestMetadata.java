package com.dee.app.http;

import androidx.annotation.VisibleForTesting;
import com.dee.app.http.CoralService;
import com.dee.app.metrics.MetricDescriptor;
import com.dee.app.metrics.MetricsTimer;
@VisibleForTesting
/* loaded from: classes2.dex */
public class DefaultRequestMetadata implements CoralService.Request.Metadata {
    private String domain;
    private MetricDescriptor metricDescriptor;
    private String metricsSource;
    private MetricsTimer metricsTimer;
    private String operationName;

    @Override // com.dee.app.http.CoralService.Request.Metadata
    public String getDomain() {
        return this.domain;
    }

    @Override // com.dee.app.http.CoralService.Request.Metadata
    public MetricDescriptor getMetricDescriptor() {
        return this.metricDescriptor;
    }

    @Override // com.dee.app.http.CoralService.Request.Metadata
    public String getMetricsSource() {
        return this.metricsSource;
    }

    @Override // com.dee.app.http.CoralService.Request.Metadata
    public MetricsTimer getMetricsTimer() {
        return this.metricsTimer;
    }

    @Override // com.dee.app.http.CoralService.Request.Metadata
    public String getOperationName() {
        return this.operationName;
    }

    @Override // com.dee.app.http.CoralService.Request.Metadata
    /* renamed from: setDomain  reason: collision with other method in class */
    public DefaultRequestMetadata mo6800setDomain(String str) {
        this.domain = str;
        return this;
    }

    @Override // com.dee.app.http.CoralService.Request.Metadata
    /* renamed from: setMetricDescriptor  reason: collision with other method in class */
    public DefaultRequestMetadata mo6801setMetricDescriptor(MetricDescriptor metricDescriptor) {
        this.metricDescriptor = metricDescriptor;
        return this;
    }

    @Override // com.dee.app.http.CoralService.Request.Metadata
    /* renamed from: setMetricsSource  reason: collision with other method in class */
    public DefaultRequestMetadata mo6802setMetricsSource(String str) {
        this.metricsSource = str;
        return this;
    }

    @Override // com.dee.app.http.CoralService.Request.Metadata
    /* renamed from: setMetricsTimer  reason: collision with other method in class */
    public DefaultRequestMetadata mo6803setMetricsTimer(MetricsTimer metricsTimer) {
        this.metricsTimer = metricsTimer;
        return this;
    }

    @Override // com.dee.app.http.CoralService.Request.Metadata
    /* renamed from: setOperationName  reason: collision with other method in class */
    public DefaultRequestMetadata mo6804setOperationName(String str) {
        this.operationName = str;
        return this;
    }
}
