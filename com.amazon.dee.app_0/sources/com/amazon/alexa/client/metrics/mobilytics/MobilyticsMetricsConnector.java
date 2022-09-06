package com.amazon.alexa.client.metrics.mobilytics;

import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.api.AlexaMetricsName;
import com.amazon.alexa.client.core.configuration.ClientConfiguration;
import com.amazon.alexa.client.crashreporting.CrashReporter;
import com.amazon.alexa.client.metrics.core.AlexaMetricsConstants;
import com.amazon.alexa.client.metrics.core.AlexaMetricsEvent;
import com.amazon.alexa.client.metrics.core.AlexaMetricsUtils;
import com.amazon.alexa.client.metrics.core.AsyncMetricsConnector;
import com.amazon.alexa.client.metrics.core.DefaultAlexaMetricsEvent;
import com.amazon.alexa.client.metrics.core.DefaultMetricsCounter;
import com.amazon.alexa.client.metrics.core.DefaultMetricsTimer;
import com.amazon.alexa.client.metrics.core.MetricsTimer;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.mobilytics.OwnerIdentifier;
import com.amazon.alexa.mobilytics.event.MobilyticsEventFactory;
import com.amazon.alexa.mobilytics.event.metadata.A4ALaunchMetadata;
import com.amazon.alexa.mobilytics.event.metadata.A4ASdkMetadata;
import com.amazon.alexa.mobilytics.event.metadata.AMPDMetadata;
import com.amazon.alexa.mobilytics.event.operational.MobilyticsOperationalEvent;
import com.amazon.alexa.mobilytics.event.operational.OperationalEventType;
import com.amazon.alexa.mobilytics.session.MobilyticsSession;
import com.amazon.alexa.preload.attribution.PreloadAttributionManager;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.android.tools.r8.GeneratedOutlineSupport1;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import javax.inject.Inject;
import javax.inject.Singleton;
import rx.functions.Action1;
@Singleton
/* loaded from: classes6.dex */
public class MobilyticsMetricsConnector extends AsyncMetricsConnector {
    @VisibleForTesting
    static final String CONTENT_ID_KEY = "contentId";
    @VisibleForTesting
    static final String CONTENT_TYPE_KEY = "contentType";
    @VisibleForTesting
    static final String DATA_VALUE_KEY = "Data";
    @VisibleForTesting
    static final String ERROR_VALUE_KEY = "Error";
    public static final String OPERATIONAL_EVENT_TYPE_KEY = "operationalEventType";
    private static final String SHIM_SUBCOMPONENT_VALUE = "subcomponent_shim";
    private static final String SUBCOMPONENT = "subComponent";
    private final String androidId;
    private final CrashReporter crashReporter;
    private final Lazy<Mobilytics> mobilytics;
    private final Lazy<MobilyticsEventFactory> mobilyticsEventFactory;
    private final MobilyticsUserProviderImpl mobilyticsUserProvider;
    @VisibleForTesting
    final ConcurrentMap<String, AlexaMetricsEvent> ongoingEvents;
    private final Lazy<PreloadAttributionManager> preloadAttributionManagerLazy;
    private static final String TAG = MobilyticsMetricsConnector.class.getSimpleName();
    private static final String AMPD_EVENT_NAME = AlexaMetricsName.DeviceLockScreenState.DEVICE_LOCK_SCREEN_STATE_CHECK.toString();

    @Inject
    public MobilyticsMetricsConnector(Lazy<PreloadAttributionManager> lazy, Lazy<Mobilytics> lazy2, Lazy<MobilyticsEventFactory> lazy3, CrashReporter crashReporter, String str, Lazy<ClientConfiguration> lazy4, MobilyticsUserProviderImpl mobilyticsUserProviderImpl) {
        super(lazy4);
        this.preloadAttributionManagerLazy = lazy;
        this.mobilytics = lazy2;
        this.mobilyticsEventFactory = lazy3;
        this.crashReporter = crashReporter;
        this.ongoingEvents = new ConcurrentHashMap();
        this.androidId = str;
        this.mobilyticsUserProvider = mobilyticsUserProviderImpl;
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
            hashMap.put("contentId", this.androidId);
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

    private A4ALaunchMetadata getA4ALaunchMetadata(@NonNull Map<String, Object> map) {
        A4ALaunchMetadata a4ALaunchMetadata = new A4ALaunchMetadata();
        for (String str : map.keySet()) {
            char c = 65535;
            switch (str.hashCode()) {
                case -2103264366:
                    if (str.equals(AlexaMetricsConstants.Launcher.OUTCOME)) {
                        c = 0;
                        break;
                    }
                    break;
                case -1110606279:
                    if (str.equals(AlexaMetricsConstants.Launcher.TOKEN)) {
                        c = 3;
                        break;
                    }
                    break;
                case -81775279:
                    if (str.equals(AlexaMetricsConstants.Launcher.TARGET)) {
                        c = 1;
                        break;
                    }
                    break;
                case 84110063:
                    if (str.equals(AlexaMetricsConstants.Launcher.REASONS)) {
                        c = 2;
                        break;
                    }
                    break;
            }
            if (c == 0) {
                a4ALaunchMetadata = a4ALaunchMetadata.withOutcome(map.get(str).toString());
            } else if (c == 1) {
                a4ALaunchMetadata = a4ALaunchMetadata.withTarget(map.get(str).toString());
            } else if (c == 2) {
                a4ALaunchMetadata = a4ALaunchMetadata.withReasons(map.get(str).toString());
            } else if (c == 3) {
                a4ALaunchMetadata = a4ALaunchMetadata.withToken(map.get(str).toString());
            }
        }
        return a4ALaunchMetadata;
    }

    private A4ASdkMetadata getA4ASdkMetadata(@NonNull Map<String, Object> map) {
        A4ASdkMetadata a4ASdkMetadata = new A4ASdkMetadata();
        for (String str : map.keySet()) {
            char c = 65535;
            int hashCode = str.hashCode();
            if (hashCode != -233843581) {
                if (hashCode == 1773908970 && str.equals(AlexaMetricsConstants.EventConstants.USER_SPEECH_INVOCATION_TYPE)) {
                    c = 1;
                }
            } else if (str.equals("dialogId")) {
                c = 0;
            }
            if (c == 0) {
                a4ASdkMetadata = a4ASdkMetadata.withDialogRequestId(map.get(str).toString());
            } else if (c == 1) {
                a4ASdkMetadata = InvocationTypeSplitter.addInvocationType(a4ASdkMetadata, map.get(str).toString());
            }
        }
        return a4ASdkMetadata;
    }

    private AMPDMetadata getAMPDMetadata(@NonNull Map<String, Object> map) {
        AMPDMetadata aMPDMetadata = new AMPDMetadata();
        for (String str : map.keySet()) {
            char c = 65535;
            int hashCode = str.hashCode();
            if (hashCode != -2093132688) {
                if (hashCode == -1277018948 && str.equals(AlexaMetricsConstants.AMPD.LAST_UNLOCK_TIMESTAMP)) {
                    c = 1;
                }
            } else if (str.equals(AlexaMetricsConstants.AMPD.DEVICE_UNLOCK_STATE)) {
                c = 0;
            }
            if (c == 0) {
                aMPDMetadata = aMPDMetadata.withDeviceLockState(map.get(str).toString());
            } else if (c == 1) {
                aMPDMetadata = aMPDMetadata.withLastUnlockTimestamp(map.get(str).toString());
            }
        }
        return aMPDMetadata;
    }

    private double getUpdatedTime(Map<String, Object> map, String str) {
        Double asDouble = AlexaMetricsUtils.getAsDouble(map, str);
        return asDouble == null ? FrostVideoEffectController.VIDEO_STRENGTH_CLEAR : asDouble.doubleValue() / 1000.0d;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$onBeginSession$1(Throwable th) {
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Failed to sync mobilytics session id: ");
        outline107.append(th.getMessage());
        Log.i(str, outline107.toString());
    }

    private void recordCounterMetric(DefaultMetricsCounter defaultMetricsCounter) {
        Map<String, Object> createDataMap = createDataMap(defaultMetricsCounter.getCustomEntries());
        createDataMap.put("CounterValue", Double.valueOf(defaultMetricsCounter.getCount()));
        recordMobilyticsOperational(OperationalEventType.COUNTER, defaultMetricsCounter.getEventName(), defaultMetricsCounter.getComponentName(), createDataMap);
    }

    private void recordMobilyticsOperational(String str, String str2, String str3, @NonNull Map<String, Object> map) {
        boolean z = true;
        boolean z2 = str2 != null && str2.startsWith(AlexaMetricsConstants.Launcher.EVENT_NAME_PREFIX);
        if (str2 == null || !str2.equals(AMPD_EVENT_NAME)) {
            z = false;
        }
        if (z2) {
            map.put("ownerIdentifier", OwnerIdentifier.ALEXA_FOR_APPS);
        }
        if (!map.containsKey("ownerIdentifier")) {
            map.put("ownerIdentifier", OwnerIdentifier.ALEXA_APP_VOICE_CORE_ANDROID);
        }
        Map<String, Object> populateOperationalCustomEntries = populateOperationalCustomEntries(str2, str, str3, new HashMap(map));
        MobilyticsOperationalEvent operationalEvent = this.mobilyticsEventFactory.mo358get().getOperationalEvent(str2, str3, populateOperationalCustomEntries);
        if (!map.isEmpty()) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(getA4ASdkMetadata(populateOperationalCustomEntries));
            if (z2) {
                arrayList.add(getA4ALaunchMetadata(populateOperationalCustomEntries));
            }
            if (z) {
                arrayList.add(getAMPDMetadata(populateOperationalCustomEntries));
            }
            operationalEvent.setEventMetadata(arrayList);
        }
        this.mobilytics.mo358get().recordOperationalEvent(operationalEvent);
    }

    private void recordOtherMetric(DefaultAlexaMetricsEvent defaultAlexaMetricsEvent) {
        String asString;
        String eventName = defaultAlexaMetricsEvent.getEventName();
        String componentName = defaultAlexaMetricsEvent.getComponentName();
        Map<String, Object> customEntries = defaultAlexaMetricsEvent.getCustomEntries();
        Map<String, Object> createDataMap = createDataMap(customEntries);
        if (customEntries != null && (asString = AlexaMetricsUtils.getAsString(customEntries, "EventType")) != null && "Error".equals(asString) && AlexaMetricsUtils.getAsString(customEntries, "EventValue") != null) {
            recordMobilyticsOperational("error", eventName, componentName, createDataMap);
        } else {
            recordMobilyticsOperational(OperationalEventType.DIAGNOSTIC, defaultAlexaMetricsEvent.getEventName(), defaultAlexaMetricsEvent.getComponentName(), createDataMap);
        }
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
    }

    public /* synthetic */ void lambda$onBeginSession$0$MobilyticsMetricsConnector(String str) {
        this.crashReporter.putMetadata("mobilyticsSessionId", str);
    }

    @Override // com.amazon.alexa.client.metrics.core.AsyncMetricsConnector
    protected void onBeginSession() {
        MobilyticsSession session = this.mobilytics.mo358get().getSession();
        String id = session.id();
        if (!TextUtils.isEmpty(id)) {
            this.crashReporter.putMetadata("mobilyticsSessionId", id);
        }
        session.onIdChanged().subscribe(new Action1() { // from class: com.amazon.alexa.client.metrics.mobilytics.-$$Lambda$MobilyticsMetricsConnector$EqWp5fjEJaegqSZpZs3LUZNKaJQ
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                MobilyticsMetricsConnector.this.lambda$onBeginSession$0$MobilyticsMetricsConnector((String) obj);
            }
        }, $$Lambda$MobilyticsMetricsConnector$Sdkjgmg0PcBjV3FgAGeqloHFaw.INSTANCE);
    }

    @Override // com.amazon.alexa.client.metrics.core.AsyncMetricsConnector
    @Deprecated
    protected void onEndSession() {
    }

    @Override // com.amazon.alexa.client.metrics.core.AsyncMetricsConnector
    @Deprecated
    protected void onPauseSession() {
    }

    @Override // com.amazon.alexa.client.metrics.core.AsyncMetricsConnector
    protected void onRecordEvent(DefaultAlexaMetricsEvent defaultAlexaMetricsEvent) {
        recordEvent(defaultAlexaMetricsEvent);
    }

    @Override // com.amazon.alexa.client.metrics.core.AsyncMetricsConnector
    @Deprecated
    protected void onResumeSession() {
    }

    @Override // com.amazon.alexa.client.metrics.core.AsyncMetricsConnector
    protected void onShutdown() {
        this.mobilyticsUserProvider.shutdown();
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
            if (!addContentInformation.containsKey("CounterValue")) {
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

    @VisibleForTesting
    void recordEvent(DefaultAlexaMetricsEvent defaultAlexaMetricsEvent) {
        if (TextUtils.isEmpty(defaultAlexaMetricsEvent.getEventName())) {
            return;
        }
        if (defaultAlexaMetricsEvent instanceof DefaultMetricsCounter) {
            recordCounterMetric((DefaultMetricsCounter) defaultAlexaMetricsEvent);
        } else if (defaultAlexaMetricsEvent instanceof DefaultMetricsTimer) {
            recordTimerMetric((DefaultMetricsTimer) defaultAlexaMetricsEvent);
        } else {
            recordOtherMetric(defaultAlexaMetricsEvent);
        }
    }
}
