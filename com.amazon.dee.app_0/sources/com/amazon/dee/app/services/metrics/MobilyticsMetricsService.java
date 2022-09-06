package com.amazon.dee.app.services.metrics;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.crashreporting.api.CrashMetadata;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.mobilytics.event.MobilyticsEventFactory;
import com.amazon.alexa.mobilytics.event.operational.OperationalEventType;
import com.amazon.alexa.mobilytics.event.userinteraction.InteractionType;
import com.amazon.alexa.mobilytics.session.MobilyticsSession;
import com.amazon.alexa.preload.attribution.PreloadAttributionManager;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.alexa.protocols.storage.PersistentStorage;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.amazon.dee.app.services.logging.Log;
import com.amazon.dee.app.services.metrics.AlexaMetricsConstants;
import com.amazon.dee.app.util.MapUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.metrics.AlexaMetricsEvent;
import com.dee.app.metrics.MetricsCounter;
import com.dee.app.metrics.MetricsService;
import com.dee.app.metrics.MetricsTimer;
import dagger.Lazy;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import rx.functions.Action1;
/* loaded from: classes12.dex */
public class MobilyticsMetricsService implements MetricsService {
    @VisibleForTesting
    static final String CONTENT_ID_KEY = "contentId";
    @VisibleForTesting
    static final String CONTENT_TYPE_KEY = "contentType";
    @VisibleForTesting
    static final String DATA_VALUE_KEY = "Data";
    @VisibleForTesting
    static final String ERROR_VALUE_KEY = "Error";
    private static final String SHIM_SUBCOMPONENT_VALUE = "subcomponent_shim";
    private static final Set<String> SINGLE_SESSION_EVENTS;
    private static final String TAG = Log.tag(MobilyticsMetricsService.class);
    private final CrashMetadata crashMetadata;
    private final EnvironmentService environmentService;
    private final Lazy<Mobilytics> mobilytics;
    private final Lazy<MobilyticsEventFactory> mobilyticsEventFactory;
    @VisibleForTesting
    final PersistentStorage persistentStorage;
    private final Lazy<PreloadAttributionManager> preloadAttributionManagerLazy;
    @VisibleForTesting
    final Set<String> sessionEventsRecorded = Collections.synchronizedSet(new HashSet());
    @VisibleForTesting
    final ConcurrentMap<String, AlexaMetricsEvent> ongoingEvents = new ConcurrentHashMap();

    /* renamed from: com.amazon.dee.app.services.metrics.MobilyticsMetricsService$1  reason: invalid class name */
    /* loaded from: classes12.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$dee$app$services$metrics$CustomEventType = new int[CustomEventType.values().length];

        static {
            try {
                $SwitchMap$com$amazon$dee$app$services$metrics$CustomEventType[CustomEventType.OCCURRENCE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$dee$app$services$metrics$CustomEventType[CustomEventType.ERROR.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$dee$app$services$metrics$CustomEventType[CustomEventType.DIAGNOSTIC.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$dee$app$services$metrics$CustomEventType[CustomEventType.TIMER.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$dee$app$services$metrics$CustomEventType[CustomEventType.OPERATIONAL.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$dee$app$services$metrics$CustomEventType[CustomEventType.INTERACTION.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    static {
        HashSet hashSet = new HashSet();
        Collections.addAll(hashSet, AlexaMetricsConstants.MetricEvents.APP_SESSION_MEMORY_WARNING, AlexaMetricsConstants.MetricEvents.APP_SESSION_LANDSCAPE_ENABLED, AlexaMetricsConstants.MetricEvents.APP_SESSION_LANDSCAPE_USE_10, AlexaMetricsConstants.MetricEvents.APP_SESSION_LANDSCAPE_USE_60, AlexaMetricsConstants.MetricEvents.OOBE_CORS_ERROR, "APP_CRASH", "APP_CRASH_COUNT");
        SINGLE_SESSION_EVENTS = Collections.unmodifiableSet(hashSet);
    }

    public MobilyticsMetricsService(EnvironmentService environmentService, PersistentStorage.Factory factory, CrashMetadata crashMetadata, Lazy<PreloadAttributionManager> lazy, Lazy<Mobilytics> lazy2, Lazy<MobilyticsEventFactory> lazy3) {
        this.environmentService = environmentService;
        this.persistentStorage = factory.create(AlexaMetricsConstants.EventConstants.METRICS_SESSION_STORAGE);
        this.crashMetadata = crashMetadata;
        this.preloadAttributionManagerLazy = lazy;
        this.mobilytics = lazy2;
        this.mobilyticsEventFactory = lazy3;
    }

    private Map<String, Object> addContentInformation(@NonNull Map<String, Object> map) {
        HashMap hashMap = new HashMap(map);
        if (!hashMap.containsKey("contentType")) {
            String attributionTag = this.preloadAttributionManagerLazy.mo358get().getAttributionTag();
            if (!TextUtils.isEmpty(attributionTag)) {
                hashMap.put("contentType", attributionTag);
            }
        }
        if (!hashMap.containsKey("contentId")) {
            hashMap.put("contentId", this.environmentService.getDeviceInformation().getAndroidId());
        }
        return hashMap;
    }

    private Map<String, Object> copyMapObjects(String str, String str2, String str3, String str4, Map<String, Object> map) {
        Map<String, Object> createDataMap = createDataMap(map);
        if (createDataMap.containsKey(str)) {
            createDataMap.put(str2, createDataMap.get(str));
        } else {
            GeneratedOutlineSupport1.outline158("Recording metric with no event value: ", str3);
        }
        return createDataMap;
    }

    private Map<String, Object> createDataMap(@Nullable Map<String, Object> map) {
        HashMap hashMap = new HashMap();
        if (map != null) {
            hashMap.putAll(map);
        }
        return hashMap;
    }

    private double getUpdatedTime(Map<String, Object> map, String str) {
        Double asDouble = MapUtils.getAsDouble(map, str);
        return asDouble == null ? FrostVideoEffectController.VIDEO_STRENGTH_CLEAR : asDouble.doubleValue() / 1000.0d;
    }

    private boolean isEventValid(AlexaMetricsEvent alexaMetricsEvent) {
        return alexaMetricsEvent != null && !alexaMetricsEvent.isInvalidated();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$beginSession$1(Throwable th) {
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Failed to sync mobilytics session id: ");
        outline107.append(th.getMessage());
        Log.i(str, outline107.toString());
    }

    private void recordCustomOccurrence(String str, String str2, @NonNull Map<String, Object> map) {
        if (map.containsKey("CounterValue")) {
            recordMobilyticsOperational(OperationalEventType.COUNTER, str, str2, map);
        } else {
            new Object[1][0] = str;
        }
    }

    private void recordCustomTimer(String str, String str2, @NonNull Map<String, Object> map) {
        long j;
        boolean z;
        Object obj = map.get("TimerValue");
        if (obj instanceof Long) {
            j = ((Long) obj).longValue();
            z = false;
        } else {
            j = 0;
            z = true;
        }
        recordTimerMetric(new DefaultMetricsTimer(str, str2, map, j, z));
    }

    private void recordMobilyticsInteraction(String str, String str2, String str3, Map<String, Object> map) {
        if (singleSessionEventAlreadyReported(str2)) {
            return;
        }
        Map<String, Object> createDataMap = createDataMap(map);
        if (!createDataMap.containsKey("interactionType")) {
            if (!str.equalsIgnoreCase("Engagement")) {
                if (str.equalsIgnoreCase("Impression")) {
                    str = InteractionType.PAGE_VIEW;
                } else if (str.equalsIgnoreCase("MobilyticsInteraction")) {
                    GeneratedOutlineSupport1.outline158("Recording generic MobilyticsInteraction event as click: ", str2);
                }
                createDataMap.put("interactionType", str);
            }
            str = "click";
            createDataMap.put("interactionType", str);
        }
        Map<String, Object> addContentInformation = addContentInformation(createDataMap);
        addContentInformation.put("subComponent", SHIM_SUBCOMPONENT_VALUE);
        this.mobilytics.mo358get().recordUserInteractionEvent(this.mobilyticsEventFactory.mo358get().getUserInteractionEvent(str2, str3, addContentInformation));
    }

    private void recordMobilyticsOperational(String str, String str2, String str3, @NonNull Map<String, Object> map) {
        if (singleSessionEventAlreadyReported(str2)) {
            return;
        }
        this.mobilytics.mo358get().recordOperationalEvent(this.mobilyticsEventFactory.mo358get().getOperationalEvent(str2, str3, populateOperationalCustomEntries(str2, str, str3, new HashMap(map))));
    }

    private void recordTimerMetric(MetricsTimer metricsTimer) {
        metricsTimer.finishTimer();
        Map<String, Object> createDataMap = createDataMap(metricsTimer.getCustomEntries());
        createDataMap.put("TimerValue", Long.valueOf(metricsTimer.getElapsedTime()));
        Object obj = createDataMap.get("EventTimestamp");
        if (obj instanceof Long) {
            createDataMap.put("RecordTimerEnd", obj);
        }
        recordMobilyticsOperational(OperationalEventType.TIMER, metricsTimer.getEventName(), metricsTimer.getComponentName(), createDataMap);
        Log.i(TAG, LatencyService.formatLatency(metricsTimer));
    }

    private boolean singleSessionEventAlreadyReported(String str) {
        return SINGLE_SESSION_EVENTS.contains(str) && !this.sessionEventsRecorded.add(str);
    }

    @Override // com.dee.app.metrics.MetricsService
    public void beginSession() {
        this.sessionEventsRecorded.clear();
        boolean z = this.persistentStorage.getBoolean("FirstLaunch", true);
        String string = this.persistentStorage.getString(AlexaMetricsConstants.EventConstants.VERSION_NAME, "2.2.0.0");
        String versionName = this.environmentService.getVersionName();
        if (z) {
            this.crashMetadata.put(AlexaMetricsConstants.EventConstants.FIRST_INSTALL, true);
            recordEvent(AlexaMetricsConstants.MetricEvents.APP_INSTALL, "Application", null);
            this.persistentStorage.edit().set("FirstLaunch", false).set(AlexaMetricsConstants.EventConstants.VERSION_NAME, versionName).commit();
        } else if (!versionName.equals(string)) {
            this.crashMetadata.put(AlexaMetricsConstants.EventConstants.APP_UPDATE, true);
            recordEvent(AlexaMetricsConstants.MetricEvents.APP_UPDATE, "Application", null);
            this.persistentStorage.edit().set(AlexaMetricsConstants.EventConstants.VERSION_NAME, versionName).commit();
        }
        recordEvent(AlexaMetricsConstants.MetricEvents.APP_SESSION, "Application", null);
        MobilyticsSession session = this.mobilytics.mo358get().getSession();
        String id = session.id();
        if (!TextUtils.isEmpty(id)) {
            this.crashMetadata.put("mobilyticsSessionId", id);
        }
        session.onIdChanged().subscribe(new Action1() { // from class: com.amazon.dee.app.services.metrics.-$$Lambda$MobilyticsMetricsService$tByNtzLuq-F0XAIyslc28qGLDZY
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                MobilyticsMetricsService.this.lambda$beginSession$0$MobilyticsMetricsService((String) obj);
            }
        }, $$Lambda$MobilyticsMetricsService$E5uwryJuE9ucu2VJrW2VThGAlo.INSTANCE);
    }

    @Override // com.dee.app.metrics.MetricsService
    public void cancelTimer(String str) {
        if (this.ongoingEvents.get(str) instanceof MetricsTimer) {
            this.ongoingEvents.remove(str);
        } else {
            new Object[1][0] = str;
        }
    }

    @Override // com.dee.app.metrics.MetricsService
    @Deprecated
    public AlexaMetricsEvent createEvent(String str, String str2, Map<String, Object> map) {
        return new DefaultAlexaMetricsEvent(str, str2, map);
    }

    @Override // com.dee.app.metrics.MetricsService
    @Deprecated
    public void endSession() {
    }

    @Override // com.dee.app.metrics.MetricsService
    public void incrementCounter(String str) {
        AlexaMetricsEvent alexaMetricsEvent = this.ongoingEvents.get(str);
        if (alexaMetricsEvent instanceof MetricsCounter) {
            ((MetricsCounter) alexaMetricsEvent).incrementCounter();
        } else {
            new Object[1][0] = str;
        }
    }

    @Override // com.dee.app.metrics.MetricsService
    public void incrementCounterByValue(String str, int i) {
        AlexaMetricsEvent alexaMetricsEvent = this.ongoingEvents.get(str);
        if (alexaMetricsEvent instanceof MetricsCounter) {
            ((MetricsCounter) alexaMetricsEvent).incrementCounterByValue(i);
        } else {
            new Object[1][0] = str;
        }
    }

    @Override // com.dee.app.metrics.MetricsService
    public boolean invalidateEvent(String str) {
        AlexaMetricsEvent alexaMetricsEvent = this.ongoingEvents.get(str);
        if (alexaMetricsEvent == null) {
            new Object[1][0] = str;
            return false;
        }
        alexaMetricsEvent.invalidateEvent();
        new Object[1][0] = str;
        return true;
    }

    @Override // com.dee.app.metrics.MetricsService
    public boolean isOngoingEvent(String str) {
        return this.ongoingEvents.containsKey(str);
    }

    public /* synthetic */ void lambda$beginSession$0$MobilyticsMetricsService(String str) {
        this.crashMetadata.put("mobilyticsSessionId", str);
    }

    @Override // com.dee.app.metrics.MetricsService
    @Deprecated
    public void pauseSession() {
    }

    @Override // com.dee.app.metrics.MetricsService
    public void pauseTimer(String str) {
        AlexaMetricsEvent alexaMetricsEvent = this.ongoingEvents.get(str);
        if (alexaMetricsEvent instanceof MetricsTimer) {
            ((MetricsTimer) alexaMetricsEvent).pauseTimer();
        } else {
            new Object[1][0] = str;
        }
    }

    @VisibleForTesting
    Map<String, Object> populateOperationalCustomEntries(String str, String str2, String str3, @NonNull Map<String, Object> map) {
        Map<String, Object> createDataMap = createDataMap(map);
        if (!createDataMap.containsKey("operationalEventType")) {
            createDataMap.put("operationalEventType", str2);
        }
        Map<String, Object> addContentInformation = addContentInformation(createDataMap);
        addContentInformation.put("subComponent", SHIM_SUBCOMPONENT_VALUE);
        if (str2.equalsIgnoreCase(OperationalEventType.TIMER)) {
            if (!addContentInformation.containsKey("TimerValue")) {
                String str4 = "Recording timer metric with no elapsed time: " + str;
                addContentInformation.put("TimerValue", 0);
            }
            addContentInformation.put("TimerValue", Double.valueOf(getUpdatedTime(addContentInformation, "TimerValue")));
        } else if (str2.equalsIgnoreCase(OperationalEventType.COUNTER)) {
            if (addContentInformation.get("CounterValue") == null) {
                String str5 = "Recording counter metric with no value: " + str;
                addContentInformation.put("CounterValue", 0);
            }
        } else if (str2.equalsIgnoreCase("error")) {
            if (!addContentInformation.containsKey("Error")) {
                addContentInformation = copyMapObjects("EventValue", "Error", str, str3, addContentInformation);
            }
        } else if (str2.equalsIgnoreCase("data") && !addContentInformation.containsKey("Data")) {
            addContentInformation = copyMapObjects("EventValue", "Data", str, str3, addContentInformation);
        }
        if (addContentInformation.containsKey("EventTimestamp")) {
            addContentInformation.put("EventTimestamp", Double.valueOf(getUpdatedTime(addContentInformation, "EventTimestamp")));
        }
        return addContentInformation;
    }

    @Override // com.dee.app.metrics.MetricsService
    public void recordCounter(MetricsCounter metricsCounter) {
        this.ongoingEvents.remove(metricsCounter.getEventName());
        if (isEventValid(metricsCounter)) {
            Map<String, Object> createDataMap = createDataMap(metricsCounter.getCustomEntries());
            createDataMap.put("CounterValue", Double.valueOf(metricsCounter.getCount()));
            recordMobilyticsOperational(OperationalEventType.COUNTER, metricsCounter.getEventName(), metricsCounter.getComponentName(), createDataMap);
        }
    }

    @Override // com.dee.app.metrics.MetricsService
    public void recordCustom(String str, String str2, String str3, Map<String, Object> map) {
        if (str == null) {
            new Object[1][0] = str2;
            return;
        }
        Map<String, Object> createDataMap = createDataMap(map);
        if (map != null && map.containsKey("TimerValue")) {
            recordCustomTimer(str2, str3, createDataMap);
        } else if (map != null && map.containsKey("CounterValue")) {
            recordMobilyticsOperational(OperationalEventType.COUNTER, str2, str3, createDataMap);
        } else {
            int ordinal = CustomEventType.getEventType(str).ordinal();
            if (ordinal == 0) {
                recordCustomOccurrence(str2, str3, createDataMap);
            } else if (ordinal == 1) {
                recordMobilyticsOperational("error", str2, str3, map);
            } else if (ordinal == 2) {
                recordMobilyticsOperational(OperationalEventType.DIAGNOSTIC, str2, str3, createDataMap);
            } else if (ordinal == 3) {
                recordCustomTimer(str2, str3, createDataMap);
            } else if (ordinal == 4) {
                if (str.equals(MetricType.COUNT.toString())) {
                    str = OperationalEventType.COUNTER;
                }
                recordMobilyticsOperational(str, str2, str3, createDataMap);
            } else if (ordinal != 5) {
                GeneratedOutlineSupport1.outline158("Recording generic operational event %s for unsupported event type: ", str);
                new Object[1][0] = str2;
                recordEvent(str2, str3, createDataMap);
            } else {
                recordMobilyticsInteraction(str, str2, str3, createDataMap);
            }
        }
    }

    @Override // com.dee.app.metrics.MetricsService
    public void recordData(String str, String str2, String str3, Map<String, Object> map) {
        Map<String, Object> createDataMap = createDataMap(map);
        createDataMap.put("Data", str3);
        recordMobilyticsOperational("data", str, str2, createDataMap);
    }

    @Override // com.dee.app.metrics.MetricsService
    public void recordEngagement(String str, String str2, Map<String, Object> map) {
        recordMobilyticsInteraction("click", str, str2, map);
    }

    @Override // com.dee.app.metrics.MetricsService
    public void recordError(String str, String str2, String str3, Map<String, Object> map) {
        Map<String, Object> createDataMap = createDataMap(map);
        if (!createDataMap.containsKey("Error")) {
            createDataMap.put("Error", str2);
        }
        recordMobilyticsOperational("error", GeneratedOutlineSupport1.outline72("Error.", str), str3, createDataMap);
    }

    @Override // com.dee.app.metrics.MetricsService
    public void recordEvent(String str, @NonNull String str2, Map<String, Object> map) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        Map<String, Object> createDataMap = createDataMap(map);
        if (map != null && map.containsKey("TimerValue") && MapUtils.getAsDouble(map, "TimerValue") != null) {
            recordCustomTimer(str, str2, createDataMap);
        } else if (map != null && map.containsKey("CounterValue") && MapUtils.getAsDouble(map, "CounterValue") != null) {
            recordMobilyticsOperational(OperationalEventType.COUNTER, str, str2, createDataMap);
        } else {
            recordMobilyticsOperational(OperationalEventType.DIAGNOSTIC, str, str2, createDataMap);
        }
    }

    @Override // com.dee.app.metrics.MetricsService
    public void recordImpression(String str, String str2, Map<String, Object> map) {
        recordMobilyticsInteraction(InteractionType.PAGE_VIEW, str, str2, map);
    }

    @Override // com.dee.app.metrics.MetricsService
    @Deprecated
    public void recordMonetization(String str, String str2, Map<String, Object> map) {
        recordEvent(str, str2, map);
    }

    @Override // com.dee.app.metrics.MetricsService
    public void recordOccurrence(String str, String str2, boolean z, @Nullable Map<String, Object> map) {
        Map<String, Object> createDataMap = createDataMap(map);
        createDataMap.put("CounterValue", Double.valueOf(z ? 1.0d : FrostVideoEffectController.VIDEO_STRENGTH_CLEAR));
        recordMobilyticsOperational(OperationalEventType.COUNTER, str, str2, createDataMap);
    }

    @Override // com.dee.app.metrics.MetricsService
    @Deprecated
    public void recordPercentOccurrence(String str, String str2, boolean z, @Nullable Map<String, Object> map) {
        Map<String, Object> createDataMap = createDataMap(map);
        createDataMap.put("CounterValue", Long.valueOf(z ? 100L : 0L));
        recordMobilyticsOperational(OperationalEventType.COUNTER, str, str2, createDataMap);
    }

    @Override // com.dee.app.metrics.MetricsService
    public void recordTimer(String str, Map<String, Object> map) {
        AlexaMetricsEvent remove = this.ongoingEvents.remove(str);
        if (!(remove instanceof MetricsTimer) || !isEventValid(remove)) {
            new Object[1][0] = str;
            return;
        }
        MetricsTimer metricsTimer = (MetricsTimer) remove;
        if (map != null) {
            metricsTimer.getCustomEntries().putAll(map);
        }
        recordTimerMetric(metricsTimer);
    }

    @Override // com.dee.app.metrics.MetricsService
    public void recordTimerAs(String str, String str2) {
        AlexaMetricsEvent remove = this.ongoingEvents.remove(str);
        if (!(remove instanceof MetricsTimer)) {
            new Object[1][0] = str;
            return;
        }
        MetricsTimer metricsTimer = (MetricsTimer) remove;
        recordTimerMetric(new DefaultMetricsTimer(str2, metricsTimer.getComponentName(), new HashMap(metricsTimer.getCustomEntries()), metricsTimer.getElapsedTime(), true));
        Object[] objArr = {str, str2};
    }

    @Override // com.dee.app.metrics.MetricsService
    @Deprecated
    public void resumeSession() {
    }

    @Override // com.dee.app.metrics.MetricsService
    public void resumeTimer(String str) {
        AlexaMetricsEvent alexaMetricsEvent = this.ongoingEvents.get(str);
        if (alexaMetricsEvent instanceof MetricsTimer) {
            ((MetricsTimer) alexaMetricsEvent).resumeTimer();
        } else {
            new Object[1][0] = str;
        }
    }

    @Override // com.dee.app.metrics.MetricsService
    public MetricsCounter startCounter(String str, String str2, Map<String, Object> map) {
        DefaultMetricsCounter defaultMetricsCounter = new DefaultMetricsCounter(str, str2, map);
        this.ongoingEvents.put(str, defaultMetricsCounter);
        return defaultMetricsCounter;
    }

    @Override // com.dee.app.metrics.MetricsService
    public MetricsTimer startTimer(String str, String str2, Map<String, Object> map) {
        DefaultMetricsTimer defaultMetricsTimer = new DefaultMetricsTimer(str, str2, map);
        this.ongoingEvents.put(str, defaultMetricsTimer);
        return defaultMetricsTimer;
    }

    @Override // com.dee.app.metrics.MetricsService
    public void recordTimer(MetricsTimer metricsTimer) {
        this.ongoingEvents.remove(metricsTimer.getEventName(), metricsTimer);
        recordTimerMetric(metricsTimer);
    }

    @Override // com.dee.app.metrics.MetricsService
    public void recordTimer(String str) {
        recordTimer(str, null);
    }

    @Override // com.dee.app.metrics.MetricsService
    public void recordEvent(AlexaMetricsEvent alexaMetricsEvent) {
        if (alexaMetricsEvent.isInvalidated()) {
            return;
        }
        if (alexaMetricsEvent instanceof MetricsTimer) {
            recordTimer((MetricsTimer) alexaMetricsEvent);
        } else if (alexaMetricsEvent instanceof MetricsCounter) {
            recordCounter((MetricsCounter) alexaMetricsEvent);
        } else {
            recordEvent(alexaMetricsEvent.getEventName(), alexaMetricsEvent.getComponentName(), alexaMetricsEvent.getCustomEntries());
        }
    }

    @Override // com.dee.app.metrics.MetricsService
    public void recordEvent(String str) {
        AlexaMetricsEvent alexaMetricsEvent = this.ongoingEvents.get(str);
        if (alexaMetricsEvent instanceof MetricsTimer) {
            recordTimer(str);
        } else if (alexaMetricsEvent instanceof MetricsCounter) {
            recordCounter((MetricsCounter) alexaMetricsEvent);
        } else {
            this.ongoingEvents.remove(str);
            if (alexaMetricsEvent != null) {
                if (!isEventValid(alexaMetricsEvent)) {
                    return;
                }
                recordEvent(alexaMetricsEvent.getEventName(), alexaMetricsEvent.getComponentName(), alexaMetricsEvent.getCustomEntries());
                return;
            }
            new Object[1][0] = str;
            recordEvent(str, "UNKNOWN", null);
        }
    }
}
