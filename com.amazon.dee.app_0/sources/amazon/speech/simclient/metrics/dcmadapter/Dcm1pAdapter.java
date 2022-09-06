package amazon.speech.simclient.metrics.dcmadapter;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.amazon.client.metrics.AndroidMetricsFactoryImpl;
import com.amazon.client.metrics.Channel;
import com.amazon.client.metrics.ClickStreamMetricsEvent;
import com.amazon.client.metrics.DataPoint;
import com.amazon.client.metrics.DataPointType;
import com.amazon.client.metrics.MetricEvent;
import com.amazon.client.metrics.MetricsException;
import com.amazon.client.metrics.MetricsFactory;
import com.amazon.client.metrics.Priority;
import com.amazon.client.metrics.clickstream.UsageInfo;
/* loaded from: classes.dex */
public class Dcm1pAdapter implements DcmAdapter {
    private static final String TAG = "Dcm1pAdapter";

    private MetricEvent createMetricsEvent(Context context, String str, String str2) {
        if (getMetricsFactory(context) == null) {
            return null;
        }
        return getMetricsFactory(context).createMetricEvent(str, str2);
    }

    @Override // amazon.speech.simclient.metrics.dcmadapter.DcmAdapter
    public MetricEventInterface createMetricEvent(Context context, String str, String str2) {
        MetricEvent createMetricsEvent = createMetricsEvent(context, str, str2);
        if (createMetricsEvent == null) {
            return null;
        }
        return new Dcm1pMetricEventWrapper(createMetricsEvent);
    }

    protected MetricsFactory getMetricsFactory(Context context) {
        MetricsFactory metricsFactory;
        try {
            metricsFactory = (MetricsFactory) context.getSystemService(com.amazon.client.metrics.thirdparty.MetricsFactory.SYSTEM_SERVICE_KEY);
        } catch (Exception unused) {
            metricsFactory = null;
        }
        if (metricsFactory == null) {
            try {
                return AndroidMetricsFactoryImpl.getInstance(context);
            } catch (Exception e) {
                Log.wtf(TAG, "Failed to get MetricsFactory instance", e);
                return metricsFactory;
            }
        }
        return metricsFactory;
    }

    @Override // amazon.speech.simclient.metrics.dcmadapter.DcmAdapter
    public boolean recordClickStreamMetric(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, boolean z, String str9) {
        try {
            MetricsFactory androidMetricsFactoryImpl = AndroidMetricsFactoryImpl.getInstance(context);
            String.format("Click stream metric: source: %s, program: %s, pageAction: %s, pageType: %s, subPageType: %s, shouldReportNonAnonymousMetric: %s", str2, str, str3, str4, str6, Boolean.valueOf(z));
            ClickStreamMetricsEvent createClickStreamMetricEvent = androidMetricsFactoryImpl.createClickStreamMetricEvent(str, str2);
            createClickStreamMetricEvent.addCounter(str4, 1.0d);
            UsageInfo usageInfo = new UsageInfo(str4, str5, str2, str);
            if (!TextUtils.isEmpty(str3)) {
                usageInfo.setPageAction(str3);
            }
            if (!TextUtils.isEmpty(str6)) {
                usageInfo.setSubPageType(str6);
            }
            createClickStreamMetricEvent.setUsageInfo(usageInfo);
            if (!TextUtils.isEmpty(str8) && !TextUtils.isEmpty(str7)) {
                createClickStreamMetricEvent.addDataPoint(new DataPoint(str7, str8, 1, DataPointType.CK));
            }
            if (!z) {
                androidMetricsFactoryImpl.record(createClickStreamMetricEvent);
            } else if (str9 != null) {
                createClickStreamMetricEvent.setNonAnonymousCustomerId(str9);
                androidMetricsFactoryImpl.record(createClickStreamMetricEvent, Priority.NORMAL, Channel.NON_ANONYMOUS);
                String str10 = TAG;
                Log.i(str10, "Reported Clickstream Non-Anonymous metric! | pageAction: " + str3);
            }
            return true;
        } catch (Exception e) {
            Log.wtf(TAG, "Can not send click stream metrics due to DCM error", e);
            return false;
        } catch (MetricsException e2) {
            Log.e(TAG, "Can not send click stream", e2);
            return false;
        }
    }

    @Override // amazon.speech.simclient.metrics.dcmadapter.DcmAdapter
    public void recordMetricsEvent(Context context, MetricEventInterface metricEventInterface, boolean z) {
        MetricsFactory metricsFactory = getMetricsFactory(context);
        if (metricsFactory == null) {
            return;
        }
        try {
            MetricEvent metricEvent = (MetricEvent) metricEventInterface.getDcmMetricEvent();
            try {
                if (z) {
                    metricsFactory.record(metricEvent, Priority.HIGH, Channel.ANONYMOUS);
                } else {
                    metricsFactory.record(metricEvent);
                }
            } catch (Exception e) {
                Log.wtf(TAG, "Error record MetricEvent: ", e);
            }
        } catch (ClassCastException unused) {
            Log.e(TAG, "Can not cast to DCM MetricsEvent.");
        }
    }
}
