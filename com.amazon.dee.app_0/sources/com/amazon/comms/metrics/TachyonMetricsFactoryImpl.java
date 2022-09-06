package com.amazon.comms.metrics;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import com.amazon.alexa.comms.mediaInsights.Trace;
import com.amazon.alexa.comms.mediaInsights.TraceRecorder;
import com.amazon.alexa.comms.mediaInsights.TraceRecorderFactory;
import com.amazon.client.metrics.common.AndroidMetricsFactoryImpl;
import com.amazon.client.metrics.common.Channel;
import com.amazon.client.metrics.common.ClickStreamMetricsEvent;
import com.amazon.client.metrics.common.DataPoint;
import com.amazon.client.metrics.common.MetricEvent;
import com.amazon.client.metrics.common.MetricEventType;
import com.amazon.client.metrics.common.MetricsFactory;
import com.amazon.client.metrics.common.Priority;
import com.amazon.client.metrics.common.clickstream.GenericClickStreamMetricEvent;
import com.amazon.comms.log.CommsLogger;
import com.amazon.comms.util.LooperExecutor;
import com.amazon.comms.util.SystemProperty;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.metrics.MetricsConstants;
import com.google.common.annotations.VisibleForTesting;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
/* loaded from: classes11.dex */
public class TachyonMetricsFactoryImpl implements TachyonMetricsFactory {
    private static final String PAYLOAD_ENCODING_BASE64_JSON = "application/json";
    private static final int TRACE_RECORDER_BUFFER_MAX_COUNT = 32;
    private static LooperExecutor executor;
    private static Runnable shutdownTracePublisherServiceRunnable;
    private static TachyonMetricsFactoryImpl tachyonMetricsFactoryImpl;
    private static String uniqueIdentifier;
    private Context appContext;
    private final MetricsFactory dcmFactory;
    private long looperThreadId;
    private TraceRecorder traceRecorder;
    private TachyonMetricsFactoryImplHelper wrapper;
    private static CommsLogger log = CommsLogger.getLogger(TachyonMetricsFactoryImpl.class);
    private static MetricsDestination metricsDestination = MetricsDestination.DCM;
    private static String deviceType = "";
    private static String version = "";
    private static boolean isDebugBuild = CommsLogger.isDebugBuild();

    private TachyonMetricsFactoryImpl(MetricsFactory metricsFactory, long j) {
        this.dcmFactory = metricsFactory;
        this.looperThreadId = j;
    }

    private void buildAndRecordTrace(String str, Map<String, String> map, Map<String, String> map2, Map<String, String> map3, String str2, String str3) {
        checkOnLooperThread();
        if (this.traceRecorder == null && ((metricsDestination.equals(MetricsDestination.INSIGHTS) || metricsDestination.equals(MetricsDestination.ALL)) && !initTracePublisherService())) {
            throw new RuntimeException("Failed to create TraceRecorder.");
        }
        TraceRecorder traceRecorder = this.traceRecorder;
        if (traceRecorder == null) {
            return;
        }
        Trace.builder(traceRecorder).withType(str).withSourceInfo(map).withAnnotations(map2).withPrincipals(map3).withPayloadEncoding(str2).withPayloadData(this.wrapper.getEncodedString(str3)).build().record();
    }

    private void checkOnLooperThread() {
        if (!isDebugBuild || this.looperThreadId == Thread.currentThread().getId()) {
            return;
        }
        throw new RuntimeException("Not running on looper thread.");
    }

    public static synchronized TachyonMetricsFactory getInstance(final Context context) {
        TachyonMetricsFactoryImpl tachyonMetricsFactoryImpl2;
        synchronized (TachyonMetricsFactoryImpl.class) {
            if (context != null) {
                if (tachyonMetricsFactoryImpl == null) {
                    metricsDestination = MetricsDestination.getDefault();
                    MetricsFactory androidMetricsFactoryImpl = AndroidMetricsFactoryImpl.getInstance(context);
                    executor = new LooperExecutor("recordingExec");
                    executor.requestStart();
                    tachyonMetricsFactoryImpl = new TachyonMetricsFactoryImpl(androidMetricsFactoryImpl, executor.getId());
                    TachyonMetricsFactoryImpl tachyonMetricsFactoryImpl3 = tachyonMetricsFactoryImpl;
                    shutdownTracePublisherServiceRunnable = new Runnable() { // from class: com.amazon.comms.metrics.TachyonMetricsFactoryImpl.1
                        @Override // java.lang.Runnable
                        public void run() {
                            TachyonMetricsFactoryImpl tachyonMetricsFactoryImpl4 = TachyonMetricsFactoryImpl.this;
                            if (tachyonMetricsFactoryImpl4 != null) {
                                tachyonMetricsFactoryImpl4.shutdownTracePublisherService();
                            }
                        }
                    };
                    executor.execute(new Runnable() { // from class: com.amazon.comms.metrics.TachyonMetricsFactoryImpl.2
                        @Override // java.lang.Runnable
                        public void run() {
                            TachyonMetricsFactoryImpl tachyonMetricsFactoryImpl4 = TachyonMetricsFactoryImpl.this;
                            if (tachyonMetricsFactoryImpl4 != null) {
                                tachyonMetricsFactoryImpl4.wrapper = new TachyonMetricsFactoryImplHelper();
                                TachyonMetricsFactoryImpl.this.appContext = context;
                                TachyonMetricsFactoryImpl.this.setPackageInfoAndDeviceType(context);
                                return;
                            }
                            throw new RuntimeException("Metrics factory is null.");
                        }
                    });
                }
                tachyonMetricsFactoryImpl2 = tachyonMetricsFactoryImpl;
            } else {
                throw new RuntimeException("Context is null.");
            }
        }
        return tachyonMetricsFactoryImpl2;
    }

    public static synchronized MetricsDestination getMetricsDestination() {
        MetricsDestination metricsDestination2;
        synchronized (TachyonMetricsFactoryImpl.class) {
            metricsDestination2 = metricsDestination;
        }
        return metricsDestination2;
    }

    private synchronized boolean initTracePublisherService() {
        checkOnLooperThread();
        log.i("Initializing TracePublisherService.");
        try {
            this.traceRecorder = TraceRecorderFactory.createTraceRecorder(this.appContext, 32, uniqueIdentifier);
            log.i("TraceRecorder initialized.");
        } catch (NullPointerException e) {
            CommsLogger commsLogger = log;
            commsLogger.e("NullPointerException creating TraceRecorder. " + e.toString());
            this.traceRecorder = null;
        } catch (Throwable th) {
            CommsLogger commsLogger2 = log;
            commsLogger2.e("Exception creating TraceRecorder. " + th.toString());
            this.traceRecorder = null;
        }
        return this.traceRecorder != null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void recordInternal(MetricEvent metricEvent, Priority priority) {
        checkOnLooperThread();
        CommsLogger commsLogger = log;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Event source: ");
        outline107.append(metricEvent.getSource());
        outline107.append(", callID: ");
        outline107.append(Arrays.asList(getTracePrincipals(metricEvent)));
        outline107.append(", payload: ");
        outline107.append(convertEventToTrace(metricEvent));
        outline107.append(", priority: ");
        outline107.append(priority);
        commsLogger.d(outline107.toString());
        this.dcmFactory.record(metricEvent, priority, Channel.ANONYMOUS);
    }

    private void recordTraceEvent(final MetricEvent metricEvent) {
        LooperExecutor looperExecutor = executor;
        if (looperExecutor != null) {
            looperExecutor.execute(new Runnable() { // from class: com.amazon.comms.metrics.TachyonMetricsFactoryImpl.5
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        TachyonMetricsFactoryImpl.this.recordTraceEventInternal(metricEvent);
                    } catch (Throwable th) {
                        TachyonMetricsFactoryImpl.log.e(th.toString());
                    }
                }
            });
            resetPublisherServiceAutoShutdown();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void recordTraceEventInternal(MetricEvent metricEvent) {
        checkOnLooperThread();
        String source = metricEvent.getSource();
        if (source.length() > 64) {
            log.w(String.format(Locale.US, "Metric type name %s has a length larger than a max length %s. Will truncate it.", source, Integer.toString(64)));
        }
        String substring = source.substring(0, Math.min(source.length(), 64));
        try {
            Map<String, String> traceSourceInfo = getTraceSourceInfo(metricEvent);
            Map<String, String> traceAnnotations = getTraceAnnotations();
            Map<String, String> tracePrincipals = getTracePrincipals(metricEvent);
            if (tracePrincipals == null) {
                log.d(String.format(Locale.US, "This trace with type %s does not have a CallId. Not publish it to Insights backend.", metricEvent.getSource()));
                return;
            }
            String convertEventToTrace = convertEventToTrace(metricEvent);
            CommsLogger commsLogger = log;
            commsLogger.d("TracePublish, source:" + metricEvent.getSource() + " callId:" + Arrays.asList(tracePrincipals) + "payload:" + convertEventToTrace);
            buildAndRecordTrace(substring, traceSourceInfo, traceAnnotations, tracePrincipals, "application/json", this.wrapper.getEncodedString(convertEventToTrace));
        } catch (Throwable th) {
            log.e(String.format(Locale.US, "Error in building and recording the trace with source %s: %s", metricEvent.getSource(), th.toString()));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void recordTraceInternal(String str, String str2, String str3, String str4, String str5) {
        checkOnLooperThread();
        try {
            buildAndRecordTrace(str3, GeneratedOutlineSupport1.outline133("source", str), getTraceAnnotations(), GeneratedOutlineSupport1.outline133("callId", str2), str4, this.wrapper.getEncodedString(str5));
        } catch (Throwable th) {
            log.e(String.format(Locale.US, "Error in building and recording the trace: Source: %s. CallId: %s. Error: %s", str, str2, th.toString()));
        }
    }

    private void resetPublisherServiceAutoShutdown() {
        executor.cancel(shutdownTracePublisherServiceRunnable);
        CommsLogger commsLogger = log;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Posting delayed shutdown for ");
        outline107.append(Integer.toString(15000));
        outline107.append(" milliseconds.");
        commsLogger.d(outline107.toString());
        executor.postDelayedRunnable(shutdownTracePublisherServiceRunnable, 15000L);
    }

    public static synchronized void setMetricsDestination(MetricsDestination metricsDestination2) {
        synchronized (TachyonMetricsFactoryImpl.class) {
            metricsDestination = metricsDestination2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void setPackageInfoAndDeviceType(Context context) {
        checkOnLooperThread();
        if (!deviceType.isEmpty()) {
            return;
        }
        try {
            version = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        deviceType = SystemProperty.getSystemProperty("ro.product.config.type", context.getPackageName());
        if (deviceType == null) {
            deviceType = Build.MANUFACTURER + "_" + Build.MODEL;
        }
    }

    public static synchronized void setUniqueIdentifier(String str) {
        synchronized (TachyonMetricsFactoryImpl.class) {
            uniqueIdentifier = str;
        }
    }

    public static synchronized void shutdown() {
        synchronized (TachyonMetricsFactoryImpl.class) {
            log.d("Shutting down TachyonMetricsFactoryImpl.");
            if (executor != null) {
                executor.cancel(shutdownTracePublisherServiceRunnable);
                log.d("Shutdown remaining instances of TracePublisherService.");
                executor.post(new Runnable() { // from class: com.amazon.comms.metrics.TachyonMetricsFactoryImpl.3
                    @Override // java.lang.Runnable
                    public void run() {
                        TachyonMetricsFactoryImpl tachyonMetricsFactoryImpl2 = TachyonMetricsFactoryImpl.this;
                        if (tachyonMetricsFactoryImpl2 != null) {
                            tachyonMetricsFactoryImpl2.shutdownTracePublisherService();
                            TachyonMetricsFactoryImpl.this.wrapper = null;
                            TachyonMetricsFactoryImpl.this.appContext = null;
                        }
                    }
                });
                executor.requestStop();
                executor = null;
                tachyonMetricsFactoryImpl = null;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void shutdownTracePublisherService() {
        checkOnLooperThread();
        log.i("Shutting down TracePublisherService.");
        try {
            if (this.traceRecorder != null) {
                log.i("Shutting down traceRecorder.");
                this.traceRecorder.shutdown();
            }
        } catch (Throwable th) {
            CommsLogger commsLogger = log;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Exception shutting down TraceRecorder. ");
            outline107.append(th.toString());
            commsLogger.e(outline107.toString());
        }
        this.traceRecorder = null;
    }

    @VisibleForTesting
    String convertEventToTrace(MetricEvent metricEvent) {
        checkOnLooperThread();
        JsonArray jsonArray = new JsonArray();
        Gson gson = new Gson();
        for (DataPoint dataPoint : metricEvent.getAsDataPoints()) {
            jsonArray.add(gson.toJson(dataPoint.toString()));
        }
        return jsonArray.toString();
    }

    @Override // com.amazon.client.metrics.common.MetricsFactory
    public ClickStreamMetricsEvent createClickStreamMetricEvent(String str, String str2) {
        return this.dcmFactory.createClickStreamMetricEvent(str, str2);
    }

    @Override // com.amazon.client.metrics.common.MetricsFactory
    /* renamed from: createClickStreamWeblabTrigger */
    public GenericClickStreamMetricEvent mo2878createClickStreamWeblabTrigger(String str, String str2, String str3, String str4) {
        return this.dcmFactory.mo2878createClickStreamWeblabTrigger(str, str2, str3, str4);
    }

    @Override // com.amazon.client.metrics.common.MetricsFactory
    public MetricEvent createConcurrentMetricEvent(String str, String str2) {
        return this.dcmFactory.createConcurrentMetricEvent(str, str2);
    }

    @Override // com.amazon.client.metrics.common.MetricsFactory
    public MetricEvent createMetricEvent(String str, String str2) {
        return this.dcmFactory.createConcurrentMetricEvent(str, str2);
    }

    @Override // com.amazon.client.metrics.common.MetricsFactory
    public String getSessionID() {
        return this.dcmFactory.getSessionID();
    }

    @VisibleForTesting
    Map<String, String> getTraceAnnotations() {
        checkOnLooperThread();
        HashMap hashMap = new HashMap();
        hashMap.put("version", deviceType + "_" + version);
        hashMap.put("isDebug", String.valueOf(CommsLogger.isUserDebugBuild()));
        return hashMap;
    }

    @VisibleForTesting
    Map<String, String> getTracePrincipals(MetricEvent metricEvent) {
        checkOnLooperThread();
        HashMap hashMap = new HashMap();
        for (DataPoint dataPoint : metricEvent.getAsDataPoints()) {
            if (dataPoint.getName().equalsIgnoreCase("CallId") && !dataPoint.getValue().equals("NO_CALL_ID")) {
                hashMap.put("callId", dataPoint.getValue());
                return hashMap;
            }
        }
        return null;
    }

    @VisibleForTesting
    Map<String, String> getTraceSourceInfo(MetricEvent metricEvent) {
        checkOnLooperThread();
        HashMap hashMap = new HashMap();
        hashMap.put("source", metricEvent.getSource());
        hashMap.put(MetricsConstants.NativeFetch.METHOD, metricEvent.getSource());
        return hashMap;
    }

    @Override // com.amazon.comms.metrics.TachyonMetricsFactory
    public void record(MetricEvent metricEvent, MetricsDestination metricsDestination2) {
        record(metricEvent, metricsDestination2, Priority.NORMAL);
    }

    @Override // com.amazon.comms.metrics.TachyonMetricsFactory
    public void recordTrace(final String str, final String str2, final String str3, final String str4, final String str5) {
        LooperExecutor looperExecutor = executor;
        if (looperExecutor != null) {
            looperExecutor.execute(new Runnable() { // from class: com.amazon.comms.metrics.TachyonMetricsFactoryImpl.6
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        TachyonMetricsFactoryImpl.this.recordTraceInternal(str, str2, str3, str4, str5);
                    } catch (Throwable th) {
                        TachyonMetricsFactoryImpl.log.e(th.toString());
                    }
                }
            });
            resetPublisherServiceAutoShutdown();
        }
    }

    @Override // com.amazon.client.metrics.common.MetricsFactory
    public ClickStreamMetricsEvent createClickStreamMetricEvent(String str, String str2, MetricEventType metricEventType) {
        return this.dcmFactory.createClickStreamMetricEvent(str, str2, metricEventType);
    }

    @Override // com.amazon.client.metrics.common.MetricsFactory
    public MetricEvent createConcurrentMetricEvent(String str, String str2, MetricEventType metricEventType) {
        return this.dcmFactory.createConcurrentMetricEvent(str, str2, metricEventType);
    }

    @Override // com.amazon.client.metrics.common.MetricsFactory
    public MetricEvent createMetricEvent(String str, String str2, MetricEventType metricEventType) {
        return this.dcmFactory.createMetricEvent(str, str2, metricEventType);
    }

    @Override // com.amazon.comms.metrics.TachyonMetricsFactory
    public void record(MetricEvent metricEvent, MetricsDestination metricsDestination2, Priority priority) {
        if (metricsDestination2.equals(MetricsDestination.DCM)) {
            record(metricEvent, priority);
        } else if (metricsDestination2.equals(MetricsDestination.INSIGHTS)) {
            recordTraceEvent(metricEvent);
        } else {
            recordTraceEvent(metricEvent);
            record(metricEvent, priority);
        }
    }

    @Override // com.amazon.client.metrics.common.MetricsFactory
    public ClickStreamMetricsEvent createClickStreamMetricEvent(String str, String str2, MetricEventType metricEventType, boolean z) {
        return this.dcmFactory.createClickStreamMetricEvent(str, str2, metricEventType, z);
    }

    @Override // com.amazon.client.metrics.common.MetricsFactory
    public MetricEvent createConcurrentMetricEvent(String str, String str2, MetricEventType metricEventType, boolean z) {
        return this.dcmFactory.createConcurrentMetricEvent(str, str2, metricEventType, z);
    }

    @Override // com.amazon.client.metrics.common.MetricsFactory
    public MetricEvent createMetricEvent(String str, String str2, MetricEventType metricEventType, boolean z) {
        return this.dcmFactory.createMetricEvent(str, str2, metricEventType, z);
    }

    @VisibleForTesting
    TachyonMetricsFactoryImpl(MetricsFactory metricsFactory, TachyonMetricsFactoryImplHelper tachyonMetricsFactoryImplHelper) {
        this.dcmFactory = metricsFactory;
        this.wrapper = tachyonMetricsFactoryImplHelper;
    }

    @Override // com.amazon.client.metrics.common.MetricsFactory
    public void record(MetricEvent metricEvent) {
        record(metricEvent, Priority.NORMAL);
    }

    @Override // com.amazon.client.metrics.common.MetricsFactory
    public void record(final MetricEvent metricEvent, final Priority priority) {
        LooperExecutor looperExecutor = executor;
        if (looperExecutor != null) {
            looperExecutor.execute(new Runnable() { // from class: com.amazon.comms.metrics.TachyonMetricsFactoryImpl.4
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        TachyonMetricsFactoryImpl.this.recordInternal(metricEvent, priority);
                    } catch (Throwable th) {
                        TachyonMetricsFactoryImpl.log.e(th.toString());
                    }
                }
            });
        }
    }

    @Override // com.amazon.client.metrics.common.MetricsFactory
    public void record(MetricEvent metricEvent, Priority priority, Channel channel) {
        this.dcmFactory.record(metricEvent, priority, channel);
    }
}
