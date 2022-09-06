package com.amazon.commscore.metrics;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.mobilytics.event.MobilyticsEvent;
import com.amazon.alexa.mobilytics.event.metadata.CommsMetadata;
import com.amazon.alexa.mobilytics.event.operational.MobilyticsMetricsCounter;
import com.amazon.alexa.mobilytics.event.operational.MobilyticsMetricsTimer;
import com.amazon.alexa.mobilytics.event.operational.MobilyticsOperationalEvent;
import com.amazon.alexa.mobilytics.event.operational.OperationalEventType;
import com.amazon.alexa.mobilytics.event.userinteraction.InteractionType;
import com.amazon.alexa.mobilytics.event.userinteraction.MobilyticsUserInteractionEvent;
import com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService;
import com.amazon.commscore.api.metrics.CounterMetric;
import com.amazon.commscore.api.metrics.InteractionMetric;
import com.amazon.commscore.api.metrics.InteractionMetricType;
import com.amazon.commscore.api.metrics.MobilyticsCustomEntries;
import com.amazon.commscore.api.metrics.OperationalMetric;
import com.amazon.commscore.api.metrics.OperationalMetricType;
import com.amazon.commscore.api.metrics.TimerMetric;
import dagger.Lazy;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
/* loaded from: classes12.dex */
public class DefaultAlexaCommsCoreMetricsService implements AlexaCommsCoreMetricsService {
    private static final String COMMS_APP_COMPONENT = "Comms";
    private static final String COMMS_CHANNEL_NAME = "comms";
    private static final String COMMS_OWNER_IDENTIFIER = "41c6c2ed-e8be-474b-96c3-4c6c1681b2f2";
    private final Lazy<Mobilytics> mobilyticsLazy;
    @VisibleForTesting
    final ConcurrentMap<String, TimerMetric> ongoingTimers = new ConcurrentHashMap();

    /* renamed from: com.amazon.commscore.metrics.DefaultAlexaCommsCoreMetricsService$1  reason: invalid class name */
    /* loaded from: classes12.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$commscore$api$metrics$InteractionMetricType = new int[InteractionMetricType.values().length];
        static final /* synthetic */ int[] $SwitchMap$com$amazon$commscore$api$metrics$OperationalMetricType;

        static {
            try {
                $SwitchMap$com$amazon$commscore$api$metrics$InteractionMetricType[InteractionMetricType.VIEW.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$commscore$api$metrics$InteractionMetricType[InteractionMetricType.SLIDER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$commscore$api$metrics$InteractionMetricType[InteractionMetricType.DEEP_LINK_CLICK.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$commscore$api$metrics$InteractionMetricType[InteractionMetricType.LONG_PRESS.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$commscore$api$metrics$InteractionMetricType[InteractionMetricType.PAGE_VIEW.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$commscore$api$metrics$InteractionMetricType[InteractionMetricType.COMMS_MESSAGE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$commscore$api$metrics$InteractionMetricType[InteractionMetricType.COMMS_CALL.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            $SwitchMap$com$amazon$commscore$api$metrics$OperationalMetricType = new int[OperationalMetricType.values().length];
            try {
                $SwitchMap$com$amazon$commscore$api$metrics$OperationalMetricType[OperationalMetricType.DATA.ordinal()] = 1;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$amazon$commscore$api$metrics$OperationalMetricType[OperationalMetricType.COUNTER.ordinal()] = 2;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$amazon$commscore$api$metrics$OperationalMetricType[OperationalMetricType.TIMER.ordinal()] = 3;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$amazon$commscore$api$metrics$OperationalMetricType[OperationalMetricType.ERROR.ordinal()] = 4;
            } catch (NoSuchFieldError unused11) {
            }
        }
    }

    public DefaultAlexaCommsCoreMetricsService(Lazy<Mobilytics> lazy) {
        this.mobilyticsLazy = lazy;
    }

    MobilyticsUserInteractionEvent convertToMobilyticsInteractionEvent(InteractionMetric interactionMetric) {
        return this.mobilyticsLazy.mo358get().createUserInteractionEvent(interactionMetric.getName(), getMobilyticsInteractionEventType(interactionMetric.getType()), interactionMetric.getComponent(), interactionMetric.getSubComponent(), interactionMetric.getOwnerIdentifier());
    }

    MobilyticsOperationalEvent convertToMobilyticsOperationalEvent(OperationalMetric operationalMetric) {
        return this.mobilyticsLazy.mo358get().createOperationalEvent(operationalMetric.getName(), getMobilyticsOperationalEventType(operationalMetric.getType()), operationalMetric.getComponent(), operationalMetric.getSubComponent(), operationalMetric.getOwnerIdentifier());
    }

    @VisibleForTesting
    CommsMetadata createCommsMetadata(Map<String, Object> map) {
        CommsMetadata commsMetadata = new CommsMetadata();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            String obj = entry.getValue().toString();
            char c = 65535;
            switch (key.hashCode()) {
                case -1992012396:
                    if (key.equals("duration")) {
                        c = 6;
                        break;
                    }
                    break;
                case -962590849:
                    if (key.equals("direction")) {
                        c = 3;
                        break;
                    }
                    break;
                case -873093151:
                    if (key.equals("messageType")) {
                        c = '\b';
                        break;
                    }
                    break;
                case -172613960:
                    if (key.equals("callType")) {
                        c = 1;
                        break;
                    }
                    break;
                case 3530753:
                    if (key.equals("size")) {
                        c = 5;
                        break;
                    }
                    break;
                case 247507199:
                    if (key.equals("statusCode")) {
                        c = 0;
                        break;
                    }
                    break;
                case 486622315:
                    if (key.equals(MobilyticsCustomEntries.CommsMetadata.TARGET_TYPE)) {
                        c = '\t';
                        break;
                    }
                    break;
                case 693933066:
                    if (key.equals("requestId")) {
                        c = 4;
                        break;
                    }
                    break;
                case 1052964649:
                    if (key.equals("transport")) {
                        c = 2;
                        break;
                    }
                    break;
                case 2140463422:
                    if (key.equals("mediaType")) {
                        c = 7;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    commsMetadata.withStatusCode(Long.valueOf(obj));
                    break;
                case 1:
                    commsMetadata.withCallType(obj);
                    break;
                case 2:
                    commsMetadata.withTransport(obj);
                    break;
                case 3:
                    commsMetadata.withDirection(obj);
                    break;
                case 4:
                    commsMetadata.withRequestId(obj);
                    break;
                case 5:
                    commsMetadata.withSize(Long.valueOf(obj));
                    break;
                case 6:
                    commsMetadata.withDuration(Long.valueOf(obj));
                    break;
                case 7:
                    commsMetadata.withMediaType(obj);
                    break;
                case '\b':
                    commsMetadata.withMessageType(obj);
                    break;
                case '\t':
                    commsMetadata.withTargetType(obj);
                    break;
            }
        }
        return commsMetadata;
    }

    @Override // com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService
    @NonNull
    public CounterMetric createCounter(@NonNull String str, @Nullable String str2, @Nullable Map<String, Object> map) {
        return createCounter(str, str2, "41c6c2ed-e8be-474b-96c3-4c6c1681b2f2", map);
    }

    @Override // com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService
    @NonNull
    public InteractionMetric createInteractionMetric(@NonNull String str, @NonNull InteractionMetricType interactionMetricType, @NonNull String str2, @Nullable Map<String, Object> map) {
        return createInteractionMetric(str, interactionMetricType, str2, "41c6c2ed-e8be-474b-96c3-4c6c1681b2f2", map);
    }

    @Override // com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService
    @NonNull
    public OperationalMetric createOperationalMetric(@NonNull String str, @NonNull OperationalMetricType operationalMetricType, @Nullable String str2, @Nullable Map<String, Object> map) {
        return createOperationalMetric(str, operationalMetricType, str2, "41c6c2ed-e8be-474b-96c3-4c6c1681b2f2", map);
    }

    @Override // com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService
    @NonNull
    public TimerMetric createTimer(@NonNull String str, @Nullable String str2, @Nullable Map<String, Object> map) {
        return createTimer(str, str2, "41c6c2ed-e8be-474b-96c3-4c6c1681b2f2", 0L, true, map);
    }

    @VisibleForTesting
    String getMobilyticsInteractionEventType(InteractionMetricType interactionMetricType) {
        switch (interactionMetricType.ordinal()) {
            case 1:
                return InteractionType.LONG_PRESS;
            case 2:
                return InteractionType.SLIDER;
            case 3:
                return "view";
            case 4:
                return InteractionType.PAGE_VIEW;
            case 5:
                return InteractionType.DEEP_LINK_CLICK;
            case 6:
                return InteractionType.COMMS_CALL;
            case 7:
                return InteractionType.COMMS_MESSAGE;
            default:
                return "click";
        }
    }

    @VisibleForTesting
    String getMobilyticsOperationalEventType(OperationalMetricType operationalMetricType) {
        int ordinal = operationalMetricType.ordinal();
        return ordinal != 0 ? ordinal != 1 ? ordinal != 2 ? ordinal != 4 ? OperationalEventType.DIAGNOSTIC : "error" : OperationalEventType.TIMER : OperationalEventType.COUNTER : "data";
    }

    @VisibleForTesting
    MobilyticsEvent populateAttributes(MobilyticsEvent mobilyticsEvent, Map<String, Object> map) {
        mobilyticsEvent.setChannelName("comms");
        if (map != null) {
            populateGenericEventAttributes(mobilyticsEvent, map);
            mobilyticsEvent.setEventMetadata(new HashSet(Collections.singletonList(createCommsMetadata(map))));
        }
        return mobilyticsEvent;
    }

    @VisibleForTesting
    MobilyticsEvent populateGenericEventAttributes(MobilyticsEvent mobilyticsEvent, Map<String, Object> map) {
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            String obj = entry.getValue().toString();
            char c = 65535;
            switch (key.hashCode()) {
                case -896505829:
                    if (key.equals("source")) {
                        c = 7;
                        break;
                    }
                    break;
                case -407108748:
                    if (key.equals("contentId")) {
                        c = 2;
                        break;
                    }
                    break;
                case -389131437:
                    if (key.equals("contentType")) {
                        c = 1;
                        break;
                    }
                    break;
                case 310417372:
                    if (key.equals(MobilyticsCustomEntries.APP_COMPONENT)) {
                        c = 0;
                        break;
                    }
                    break;
                case 706885151:
                    if (key.equals(MobilyticsCustomEntries.CONTENT_VERSION)) {
                        c = 4;
                        break;
                    }
                    break;
                case 1046094100:
                    if (key.equals("sourceContext")) {
                        c = 6;
                        break;
                    }
                    break;
                case 1436161418:
                    if (key.equals("contentProvider")) {
                        c = 3;
                        break;
                    }
                    break;
                case 1706976804:
                    if (key.equals(MobilyticsCustomEntries.INPUT_TYPE)) {
                        c = 5;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    mobilyticsEvent.setComponent(obj);
                    break;
                case 1:
                    mobilyticsEvent.setContentType(obj);
                    break;
                case 2:
                    mobilyticsEvent.setContentId(obj);
                    break;
                case 3:
                    mobilyticsEvent.setContentProvider(obj);
                    break;
                case 4:
                    mobilyticsEvent.setContentVersion(obj);
                    break;
                case 5:
                    mobilyticsEvent.setInputType(obj);
                    break;
                case 6:
                case 7:
                    mobilyticsEvent.setSourceContext(obj);
                    break;
            }
        }
        return mobilyticsEvent;
    }

    @Override // com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService
    public void recordCounter(@NonNull CounterMetric counterMetric) {
        MobilyticsMetricsCounter createCounter = this.mobilyticsLazy.mo358get().createCounter(counterMetric.getName(), counterMetric.getComponent(), counterMetric.getSubComponent(), counterMetric.getOwnerIdentifier());
        createCounter.incrementCounterByValue(counterMetric.getCount());
        populateAttributes(createCounter, counterMetric.getCustomEntries());
        this.mobilyticsLazy.mo358get().recordCounter(createCounter);
    }

    @Override // com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService
    public void recordCriticalEvent(@NonNull String str, @NonNull String str2, @NonNull String str3, @Nullable Throwable th) {
        recordCriticalEvent(str, str2, str3, "41c6c2ed-e8be-474b-96c3-4c6c1681b2f2", th);
    }

    @Override // com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService
    public void recordInfoEvent(@NonNull String str, @NonNull String str2, @NonNull String str3, @Nullable Throwable th) {
        recordInfoEvent(str, str2, str3, "41c6c2ed-e8be-474b-96c3-4c6c1681b2f2", th);
    }

    @Override // com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService
    public void recordInteractionMetric(@NonNull InteractionMetric interactionMetric) {
        MobilyticsUserInteractionEvent convertToMobilyticsInteractionEvent = convertToMobilyticsInteractionEvent(interactionMetric);
        populateAttributes(convertToMobilyticsInteractionEvent, interactionMetric.getCustomEntries());
        this.mobilyticsLazy.mo358get().recordUserInteractionEvent(convertToMobilyticsInteractionEvent);
    }

    @Override // com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService
    public void recordOccurrence(@NonNull String str, @Nullable String str2, boolean z, @Nullable Map<String, Object> map) {
        recordOccurrence(str, str2, "41c6c2ed-e8be-474b-96c3-4c6c1681b2f2", z, map);
    }

    @Override // com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService
    public void recordOperationalMetric(@NonNull OperationalMetric operationalMetric) {
        MobilyticsOperationalEvent convertToMobilyticsOperationalEvent = convertToMobilyticsOperationalEvent(operationalMetric);
        populateAttributes(convertToMobilyticsOperationalEvent, operationalMetric.getCustomEntries());
        this.mobilyticsLazy.mo358get().recordOperationalEvent(convertToMobilyticsOperationalEvent);
    }

    @Override // com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService
    public void recordTimer(@NonNull String str) {
        if (this.ongoingTimers.containsKey(str)) {
            recordTimer(this.ongoingTimers.get(str));
        }
    }

    @Override // com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService
    public void recordWarningEvent(@NonNull String str, @NonNull String str2, @NonNull String str3, @Nullable Throwable th) {
        recordWarningEvent(str, str2, str3, "41c6c2ed-e8be-474b-96c3-4c6c1681b2f2", th);
    }

    @Override // com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService
    @NonNull
    public CounterMetric createCounter(@NonNull String str, @Nullable String str2, @NonNull String str3, @Nullable Map<String, Object> map) {
        DefaultCounterMetric defaultCounterMetric = new DefaultCounterMetric(str, "Comms", str2, str3);
        defaultCounterMetric.setCustomEntries(map);
        return defaultCounterMetric;
    }

    @Override // com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService
    @NonNull
    public InteractionMetric createInteractionMetric(@NonNull String str, @NonNull InteractionMetricType interactionMetricType, @NonNull String str2, @NonNull String str3, @Nullable Map<String, Object> map) {
        return new InteractionMetric(str, "Comms", str2, str3).setCustomEntries(map).ofType(interactionMetricType);
    }

    @Override // com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService
    @NonNull
    public OperationalMetric createOperationalMetric(@NonNull String str, @NonNull OperationalMetricType operationalMetricType, @Nullable String str2, @NonNull String str3, @Nullable Map<String, Object> map) {
        if (OperationalMetricType.TIMER == operationalMetricType) {
            return createTimer(str, str2, str3, map);
        }
        if (OperationalMetricType.COUNTER == operationalMetricType) {
            return createCounter(str, str2, str3, map);
        }
        return new OperationalMetric(str, "Comms", str2, str3).setCustomEntries(map).ofType(operationalMetricType);
    }

    @Override // com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService
    @NonNull
    public TimerMetric createTimer(@NonNull String str, @Nullable String str2, @NonNull String str3, @Nullable Map<String, Object> map) {
        return createTimer(str, str2, str3, 0L, true, map);
    }

    @Override // com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService
    public void recordCriticalEvent(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull String str4, @Nullable Throwable th) {
        this.mobilyticsLazy.mo358get().recordCriticalEvent(str, str2, "Comms", str3, th, str4);
    }

    @Override // com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService
    public void recordInfoEvent(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull String str4, @Nullable Throwable th) {
        this.mobilyticsLazy.mo358get().recordInfoEvent(str, str2, "Comms", str3, th, str4);
    }

    @Override // com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService
    public void recordOccurrence(@NonNull String str, @Nullable String str2, @NonNull String str3, boolean z, @Nullable Map<String, Object> map) {
        CounterMetric createCounter = createCounter(str, str2, str3, map);
        createCounter.incrementCounterBy(z ? 1L : 0L);
        recordCounter(createCounter);
    }

    @Override // com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService
    public void recordWarningEvent(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull String str4, @Nullable Throwable th) {
        this.mobilyticsLazy.mo358get().recordWarningEvent(str, str2, "Comms", str3, th, str4);
    }

    @Override // com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService
    @NonNull
    public TimerMetric createTimer(@NonNull String str, @Nullable String str2, long j, boolean z, @Nullable Map<String, Object> map) {
        return createTimer(str, str2, "41c6c2ed-e8be-474b-96c3-4c6c1681b2f2", j, z, map);
    }

    @Override // com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService
    @NonNull
    public TimerMetric createTimer(@NonNull String str, @Nullable String str2, @NonNull String str3, long j, boolean z, @Nullable Map<String, Object> map) {
        DefaultTimerMetric defaultTimerMetric = new DefaultTimerMetric(str, "Comms", str2, j, z, str3);
        defaultTimerMetric.setCustomEntries(map);
        this.ongoingTimers.put(str, defaultTimerMetric);
        return defaultTimerMetric;
    }

    @Override // com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService
    public void recordTimer(@NonNull TimerMetric timerMetric) {
        timerMetric.finishTimer();
        this.ongoingTimers.remove(timerMetric.getName());
        MobilyticsMetricsTimer createTimer = this.mobilyticsLazy.mo358get().createTimer(timerMetric.getName(), timerMetric.getComponent(), timerMetric.getSubComponent(), timerMetric.getElapsedTime(), false, timerMetric.getOwnerIdentifier());
        populateAttributes(createTimer, timerMetric.getCustomEntries());
        this.mobilyticsLazy.mo358get().recordTimer(createTimer);
    }
}
