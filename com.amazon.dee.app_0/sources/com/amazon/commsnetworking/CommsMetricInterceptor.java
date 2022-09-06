package com.amazon.commsnetworking;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
/* loaded from: classes12.dex */
class CommsMetricInterceptor implements Interceptor {
    private final String[] RESPONSE_METRICS = {".success", Constants.REDIRECT_SUFFIX, ".fail", ".fault", ".network", ".unknown"};
    private AlexaCommsCoreMetricsService metricsService;

    /* JADX INFO: Access modifiers changed from: package-private */
    public CommsMetricInterceptor(@NonNull AlexaCommsCoreMetricsService alexaCommsCoreMetricsService) {
        this.metricsService = alexaCommsCoreMetricsService;
    }

    private String getMetricSuffix(int i) {
        int i2 = i / 100;
        return i2 != 2 ? i2 != 3 ? i2 != 4 ? i2 != 5 ? ".unknown" : ".fault" : ".fail" : Constants.REDIRECT_SUFFIX : ".success";
    }

    private void recordNetworkErrorInResponseMetrics(RequestTag requestTag, Map<String, Object> map) {
        String[] strArr;
        for (String str : this.RESPONSE_METRICS) {
            this.metricsService.recordOccurrence(GeneratedOutlineSupport1.outline91(new StringBuilder(), getMetricNameForTag(requestTag), str), requestTag.getSource(), str.equals(".network"), map);
        }
    }

    private String recordResponseMetrics(Response response, RequestTag requestTag, Map<String, Object> map) {
        String[] strArr;
        String metricSuffix = getMetricSuffix(response.code());
        String outline91 = GeneratedOutlineSupport1.outline91(new StringBuilder(), getMetricNameForTag(requestTag), metricSuffix);
        for (String str : this.RESPONSE_METRICS) {
            this.metricsService.recordOccurrence(GeneratedOutlineSupport1.outline91(new StringBuilder(), getMetricNameForTag(requestTag), str), requestTag.getSource(), metricSuffix.equals(str), map);
        }
        return outline91;
    }

    @VisibleForTesting
    String getMetricNameForTag(RequestTag requestTag) {
        if (requestTag.includeCommsNetworkingPrefix()) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107(Constants.COMMS_METRIC_PREFIX);
            outline107.append(requestTag.getMetricName());
            return outline107.toString();
        }
        return requestTag.getMetricName();
    }

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        RequestTag requestTag;
        Request request = chain.request();
        RequestTag requestTag2 = (RequestTag) request.tag(RequestTag.class);
        Map<String, Object> hashMap = new HashMap<>();
        if (requestTag2 != null) {
            hashMap.put("requestId", requestTag2.getRequestId());
            hashMap.put("source", requestTag2.getSource());
            hashMap.put("contentId", requestTag2.getRequestId());
            this.metricsService.recordOccurrence(GeneratedOutlineSupport1.outline91(new StringBuilder(), getMetricNameForTag(requestTag2), ".call"), requestTag2.getSource(), true, hashMap);
        }
        try {
            Response proceed = chain.proceed(request);
            if (requestTag2 != null) {
                int code = proceed.code();
                String metricSuffix = getMetricSuffix(code);
                String recordResponseMetrics = recordResponseMetrics(proceed, requestTag2, hashMap);
                requestTag = requestTag2;
                try {
                    this.metricsService.recordTimer(this.metricsService.createTimer(getMetricNameForTag(requestTag2) + ".latency", requestTag2.getSource(), proceed.receivedResponseAtMillis() - proceed.sentRequestAtMillis(), false, hashMap));
                    if (!".success".equals(metricSuffix) && !Constants.REDIRECT_SUFFIX.equals(metricSuffix)) {
                        hashMap.put("statusCode", Integer.valueOf(code));
                        hashMap.put("errorSource", proceed.message());
                        hashMap.put("contentProvider", Integer.valueOf(code));
                        hashMap.put("contentDetails", proceed.message());
                        this.metricsService.recordOccurrence(recordResponseMetrics + "." + code, requestTag.getSource(), true, hashMap);
                    }
                } catch (Exception e) {
                    e = e;
                    if (requestTag != null) {
                        hashMap.put("errorSource", e);
                        hashMap.put("contentDetails", e.getMessage());
                        recordNetworkErrorInResponseMetrics(requestTag, hashMap);
                    }
                    throw e;
                }
            }
            return proceed;
        } catch (Exception e2) {
            e = e2;
            requestTag = requestTag2;
        }
    }
}
