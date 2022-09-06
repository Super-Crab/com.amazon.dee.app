package com.amazon.metrics;

import amazon.speech.simclient.metrics.dcmadapter.DcmAdapterManager;
import amazon.speech.simclient.metrics.dcmadapter.MetricEventInterface;
import amazon.speech.simclient.metrics.util.HighPriorityMetricsWhiteList;
import amazon.speech.util.DebugUtil;
import amazon.speech.util.Log;
import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import com.amazon.metrics.MetricsUtil;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/* loaded from: classes9.dex */
public class MetricsUtilImpl extends MetricsUtil {
    private static final String METRIC_HIT_TYPE = "deviceAction";
    private static final String REF_MARKER_DATA_POINT = "ref-override";
    private String mDirectedId;
    private final Handler mHandler;
    private String mPageAction;
    private String mPageType;
    private String mProgram;
    private String mSource;
    private static final boolean DEBUG = DebugUtil.getShouldDebug(DebugUtil.Module.CSM);
    private static final String TAG = MetricsUtilImpl.class.getSimpleName();
    private int mProfileId = Integer.MIN_VALUE;
    private boolean mProfileIdChanged = false;
    private final Map<String, String> mGlobalMetricMetadata = new ConcurrentHashMap();
    private final Map<String, TimerEvent> mTimerEventMap = new HashMap();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public class TimerEvent {
        private final MetricEventInterface mMetricEvent;

        TimerEvent(MetricEventInterface metricEventInterface) {
            this.mMetricEvent = metricEventInterface;
        }

        public MetricEventInterface getMetricEvent() {
            return this.mMetricEvent;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MetricsUtilImpl() {
        HandlerThread handlerThread = new HandlerThread("MetricslUtilImplHanderThread", 19);
        handlerThread.start();
        this.mHandler = new Handler(handlerThread.getLooper());
    }

    private void recordEvent(final Context context, final MetricEventInterface metricEventInterface, final boolean z) {
        for (Map.Entry<String, String> entry : this.mGlobalMetricMetadata.entrySet()) {
            metricEventInterface.addString(entry.getKey(), entry.getValue());
        }
        Handler handler = this.mHandler;
        if (handler == null) {
            DcmAdapterManager.getDcmAdapter().recordMetricsEvent(context, metricEventInterface, z);
        } else {
            handler.post(new Runnable() { // from class: com.amazon.metrics.MetricsUtilImpl.1
                @Override // java.lang.Runnable
                public void run() {
                    DcmAdapterManager.getDcmAdapter().recordMetricsEvent(context, metricEventInterface, z);
                }
            });
        }
    }

    private void recordEventWithPriority(Context context, MetricEventInterface metricEventInterface, String str) {
        recordEvent(context, metricEventInterface, HighPriorityMetricsWhiteList.isHighPriorityMetric(str));
    }

    private boolean timerRunning(String str) {
        return this.mTimerEventMap.containsKey(str);
    }

    @Override // com.amazon.metrics.MetricsUtil
    public void addGlobalMetadata(Map<String, String> map) {
        if (map == null) {
            Log.w(TAG, "Global metadata was null.");
        } else {
            this.mGlobalMetricMetadata.putAll(map);
        }
    }

    @Override // com.amazon.metrics.MetricsUtil
    public synchronized boolean addTimer(Context context, String str, String str2, String str3, long j, int i, MetricsUtil.MetadataHelper metadataHelper) {
        MetricEventInterface createMetricEvent = DcmAdapterManager.getDcmAdapter().createMetricEvent(context, str2, str);
        if (createMetricEvent == null) {
            return false;
        }
        if (metadataHelper != null) {
            for (Map.Entry<String, String> entry : metadataHelper.mMetadataMap.entrySet()) {
                createMetricEvent.addString(entry.getKey(), entry.getValue());
            }
        }
        createMetricEvent.addTimer(str3, j, i);
        recordEventWithPriority(context, createMetricEvent, str3);
        return true;
    }

    public boolean recordClickStreamMetrics(Context context, String str, String str2, String str3, String str4, String str5, String str6, boolean z, String str7) {
        return DcmAdapterManager.getDcmAdapter().recordClickStreamMetric(context, str, str2, str3, str4, METRIC_HIT_TYPE, str5, REF_MARKER_DATA_POINT, str6, z, str7);
    }

    @Override // com.amazon.metrics.MetricsUtil
    public synchronized boolean recordCounter(Context context, String str, String str2, String str3, MetricsUtil.MetadataHelper metadataHelper, double d, boolean z) {
        MetricEventInterface createMetricEvent = DcmAdapterManager.getDcmAdapter().createMetricEvent(context, str2, str);
        if (createMetricEvent == null) {
            return false;
        }
        createMetricEvent.incrementCounter(str3, d);
        if (metadataHelper != null) {
            for (Map.Entry<String, String> entry : metadataHelper.mMetadataMap.entrySet()) {
                createMetricEvent.addString(entry.getKey(), entry.getValue());
            }
        }
        recordEvent(context, createMetricEvent, z);
        return true;
    }

    @Override // com.amazon.metrics.MetricsUtil
    public synchronized boolean recordDiscreteValue(Context context, String str, String str2, String str3, String str4, boolean z, MetricsUtil.MetadataHelper metadataHelper) {
        MetricEventInterface createMetricEvent = DcmAdapterManager.getDcmAdapter().createMetricEvent(context, str2, str);
        if (createMetricEvent == null) {
            return false;
        }
        createMetricEvent.addString(str3, str4);
        if (metadataHelper != null) {
            for (Map.Entry<String, String> entry : metadataHelper.mMetadataMap.entrySet()) {
                createMetricEvent.addString(entry.getKey(), entry.getValue());
            }
        }
        recordEvent(context, createMetricEvent, z);
        return true;
    }

    @Override // com.amazon.metrics.MetricsUtil
    public synchronized boolean removeTimer(Context context, String str) {
        if (DEBUG) {
            Log.d(TAG, String.format("removetimer name='%s'.", str));
        }
        TimerEvent timerEvent = this.mTimerEventMap.get(str);
        if (timerEvent == null) {
            Log.w(TAG, String.format("Tried to remove timer but '%s' does not exist.", str));
            return false;
        }
        MetricEventInterface metricEvent = timerEvent.getMetricEvent();
        if (metricEvent != null) {
            metricEvent.removeTimer(str);
            metricEvent.clear();
        }
        this.mTimerEventMap.remove(str);
        return true;
    }

    @Override // com.amazon.metrics.MetricsUtil
    public synchronized boolean startTimer(Context context, String str, String str2, String str3) {
        boolean z = true;
        if (DEBUG) {
            Log.d(TAG, String.format("starttimer name='%s'.", str3));
        }
        if (timerRunning(str3)) {
            Log.w(TAG, String.format("Timer '%s' is already running. Ignoring start request.", str3));
            return false;
        }
        this.mTimerEventMap.put(str3, new TimerEvent(DcmAdapterManager.getDcmAdapter().createMetricEvent(context, str2, str)));
        if (this.mTimerEventMap.get(str3).getMetricEvent() != null) {
            this.mTimerEventMap.get(str3).getMetricEvent().startTimer(str3);
        } else {
            z = false;
        }
        return z;
    }

    @Override // com.amazon.metrics.MetricsUtil
    public synchronized boolean stopTimer(Context context, String str, MetricsUtil.MetadataHelper metadataHelper) {
        if (DEBUG) {
            Log.d(TAG, String.format("stoptimer name='%s'.", str));
        }
        TimerEvent timerEvent = this.mTimerEventMap.get(str);
        if (timerEvent == null) {
            if (str == null) {
                str = "null";
            }
            Log.w(TAG, String.format("Tried to stop a timer name='%s' on an MetricsEvent that doesn't exist.", str));
            return false;
        }
        MetricEventInterface metricEvent = timerEvent.getMetricEvent();
        if (metricEvent != null) {
            if (metadataHelper != null) {
                for (Map.Entry<String, String> entry : metadataHelper.mMetadataMap.entrySet()) {
                    metricEvent.addString(entry.getKey(), entry.getValue());
                }
            }
            metricEvent.stopTimer(str);
            recordEventWithPriority(context, metricEvent, str);
        }
        this.mTimerEventMap.remove(str);
        return true;
    }

    MetricsUtilImpl(Handler handler) {
        this.mHandler = handler;
    }

    @Override // com.amazon.metrics.MetricsUtil
    public synchronized boolean recordCounter(Context context, String str, String str2, String str3, MetricsUtil.MetadataHelper metadataHelper, double d) {
        return recordCounter(context, str, str2, str3, metadataHelper, d, HighPriorityMetricsWhiteList.isHighPriorityMetric(str3));
    }
}
