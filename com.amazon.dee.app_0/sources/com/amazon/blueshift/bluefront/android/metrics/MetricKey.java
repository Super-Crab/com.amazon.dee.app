package com.amazon.blueshift.bluefront.android.metrics;

import com.amazon.tarazed.core.metrics.TarazedMetricsHelper;
import com.amazonaws.cognito.clientcontext.datacollection.DataRecordKey;
/* loaded from: classes11.dex */
public enum MetricKey {
    TOTAL_REQUEST_TIME("TotalRequestTime"),
    CONNECTION_TIME("ConnectionTime"),
    ENDPOINT_TO_RESPONSE_TIME("EndpointToResponseTime"),
    TIME_TO_START_PROCESSING_AUDIO("StartListeningToStartProcessingAudioTime"),
    REQUEST_CANCELLED("RequestCancelled"),
    CONNECTION_FAILED("ConnectionFailed"),
    AUTOMATIC_ENDPOINTING_USED("AutomaticEndpointingUsed"),
    REQUEST_FAILED("RequestFailed"),
    REQUEST_RETURNED_WITH_ERROR("RequestReturnedWithError"),
    CLIENT_NAME("ClientName"),
    REQUEST_ID("RequestId"),
    NETWORK_TYPE(DataRecordKey.NETWORK_TYPE),
    APP_VERSION("AppVersion"),
    CARRIER(DataRecordKey.NETWORK_OPERATOR),
    LIBRARY_VERSION(TarazedMetricsHelper.LIB_VERSION_KEY),
    LOCALE("Locale");
    
    private final String mMetricName;

    MetricKey(String str) {
        this.mMetricName = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.mMetricName;
    }
}
