package com.amazon.commsnetworking;

import androidx.annotation.NonNull;
import com.amazon.commsnetworking.metrics.MetricMetadata;
/* loaded from: classes12.dex */
public class RequestTag {
    private MetricMetadata metricMetadata;
    private String requestId;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RequestTag(@NonNull String str, @NonNull MetricMetadata metricMetadata) {
        this.requestId = str;
        this.metricMetadata = metricMetadata;
    }

    public String getMetricName() {
        return this.metricMetadata.getMetricName();
    }

    public String getRequestId() {
        return this.requestId;
    }

    public String getSource() {
        return this.metricMetadata.getSource();
    }

    public boolean includeCommsNetworkingPrefix() {
        return this.metricMetadata.includeCommsNetworkingPrefix();
    }

    public void setRequestId(@NonNull String str) {
        this.requestId = str;
    }
}
