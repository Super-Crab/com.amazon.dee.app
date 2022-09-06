package com.amazon.dee.app.services.metrics;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.ArrayMap;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.MultiFilterSubscriber;
import com.amazon.alexa.eventbus.api.Subscriber;
import com.amazon.alexa.eventbus.message.EventTypeMessageFilter;
import com.amazon.alexa.lifecycle.api.LifecycleEvent;
import com.amazon.alexa.lifecycle.api.LifecycleManager;
import com.amazon.alexa.mobilytics.event.operational.MobilyticsMetricsTimer;
import com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver;
import com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserverRegistrar;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.amazon.alexa.routing.api.Route;
import com.amazon.alexa.ttcf.api.TTCFRoute;
import com.amazon.dee.app.services.logging.Log;
import com.amazon.dee.app.services.metrics.AlexaMetricsConstants;
import com.amazon.dee.app.services.metrics.LatencyService;
import com.amazon.deecomms.services.conversation.CommsEventBusConstants;
import com.amazon.latencyinfra.LatencyInfra;
import com.amazon.latencyinfra.LatencyMarker;
import com.amazon.latencyinfra.LatencyNamespace;
import com.amazon.latencyinfra.TimelineInputArgument;
import com.amazon.latencyinfra.TimelineLatencyAction;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.metrics.MetricsService;
import com.dee.app.metrics.MetricsTimer;
import com.google.common.base.Optional;
import java.util.Collections;
import java.util.Locale;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
/* loaded from: classes12.dex */
public final class LatencyService {
    private static final String COMPONENT_PLACEHOLDER = "COMPONENT_PLACEHOLDER";
    private static final String TIMER_PLACEHOLDER = "TIMER_PLACEHOLDER";
    private static Recording earlyColdRecording;
    private static Handler handler;
    private static boolean hasRecordedCoreLatency;
    private static LatencyInfra latencyInfra;
    private static MetricsService metricsService;
    private static Completion overrideCompletion;
    private static String savedEndpoint;
    private static long savedTime;
    private static long startTime;
    private static StartType startType;
    @GuardedBy("timeoutLock")
    private static Timer timeout;
    private static MetricsTimer timer;
    private static Object timeoutLock = new Object();
    private static final String TAG = Log.tag(LatencyService.class);
    private static boolean suspended = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.amazon.dee.app.services.metrics.LatencyService$1  reason: invalid class name */
    /* loaded from: classes12.dex */
    public static class AnonymousClass1 extends TimerTask {
        final /* synthetic */ String val$startTypeAbbreviation;

        AnonymousClass1(String str) {
            this.val$startTypeAbbreviation = str;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ void lambda$run$0(String str) {
            if (LatencyService.savedTime > 0) {
                LatencyService.complete(Component.WEB.value(), LatencyService.savedEndpoint);
                return;
            }
            if (LatencyService.metricsService != null) {
                LatencyService.metricsService.recordEvent(String.format("native.%s.timeout.count", str), "Application", null);
            }
            String unused = LatencyService.TAG;
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            Handler handler = LatencyService.getHandler();
            final String str = this.val$startTypeAbbreviation;
            handler.post(new Runnable() { // from class: com.amazon.dee.app.services.metrics.-$$Lambda$LatencyService$1$TlBMgW7KOVCyWvP3y_-7zroMym4
                @Override // java.lang.Runnable
                public final void run() {
                    LatencyService.AnonymousClass1.lambda$run$0(str);
                }
            });
        }
    }

    /* loaded from: classes12.dex */
    private static class ColdTrailingRoute implements TTCFTrailingRoute {
        private static final String METRIC_NAME = "user.cold_start.time";
        private final long startTime;

        ColdTrailingRoute(long j) {
            this.startTime = j;
        }

        @Override // com.amazon.dee.app.services.metrics.TTCFTrailingRoute
        @NonNull
        public String getMetricName() {
            return METRIC_NAME;
        }

        @Override // com.amazon.dee.app.services.metrics.TTCFTrailingRoute
        public long getStartTime() {
            return this.startTime;
        }
    }

    /* loaded from: classes12.dex */
    public enum Completion {
        OOBE,
        HOME,
        HOMEFEED,
        CONVERSATION,
        UNKNOWN;

        public String value() {
            return name().toLowerCase();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public static class FirstRenderObserver implements MainActivityLifecycleObserver {
        private final EventBus eventBus;
        private Subscriber.SubscriberUuid firstRenderListener = null;

        FirstRenderObserver(EventBus eventBus) {
            this.eventBus = eventBus;
        }

        @Override // com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver
        public void onActivityCreated() {
        }

        @Override // com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver
        public void onActivityDestroy() {
        }

        @Override // com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver
        public void onActivityPause() {
        }

        @Override // com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver
        public void onActivityResume() {
        }

        @Override // com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver
        public void onActivityStart() {
            MultiFilterSubscriber subscriber = this.eventBus.getSubscriber();
            this.firstRenderListener = subscriber.getSubscriberUuid();
            subscriber.subscribeFilter(new EventTypeMessageFilter("oobe::loading:complete"), $$Lambda$LatencyService$FirstRenderObserver$yNWhzjfe9GBYwMdqjnE3BdL0y0Q.INSTANCE);
        }

        @Override // com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver
        public void onActivityStop() {
            Subscriber.SubscriberUuid subscriberUuid = this.firstRenderListener;
            if (subscriberUuid != null) {
                this.eventBus.unsubscribe(subscriberUuid);
                this.firstRenderListener = null;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public static class Recording {
        @NonNull
        public final String component;
        public final long startTime;

        Recording(long j, @NonNull String str) {
            this.startTime = j;
            this.component = str;
        }
    }

    /* loaded from: classes12.dex */
    public enum StartType {
        COLD("cs"),
        WARM("ws");
        
        private String alternate;

        StartType(String str) {
            this.alternate = str;
        }

        public String abbr() {
            return this.alternate;
        }

        public String value() {
            return name().toLowerCase();
        }
    }

    private LatencyService() {
    }

    public static void activate() {
        if (timer == null || !suspended) {
            return;
        }
        suspended = false;
        resume();
    }

    private static void addTimelineEvent(String str, String str2, String str3) {
        if (getLatencyInfra() == null) {
            return;
        }
        ArrayMap arrayMap = new ArrayMap();
        arrayMap.put(str3, Long.valueOf(System.currentTimeMillis()));
        latencyInfra.recordTimeline(TimelineLatencyAction.RECORD_CACHED_EVENTS_IN_TIMELINE, new TimelineInputArgument.Builder().withNamespace(str2).withTimelineName(str).withEvents(arrayMap).withLogOption(true).build());
    }

    private static void cancelTimeout(boolean z) {
        synchronized (timeoutLock) {
            if (timeout != null) {
                timeout.cancel();
                if (z) {
                    timeout = null;
                }
            }
        }
    }

    public static void catalystLoaded() {
        addTimelineEvent("RN_LOAD_TO_RENDER", LatencyNamespace.CORE_COLDSTART, "CATALYST_LOADED");
        addTimelineEvent(AlexaMetricsConstants.MetricEvents.ACTIVITY_COLD_START, LatencyNamespace.CORE_COLDSTART, "CATALYST_LOADED");
    }

    private static void cleanup() {
        cancelTimeout(true);
        savedTime = 0L;
        savedEndpoint = null;
        overrideCompletion = null;
        startType = null;
        timer = null;
    }

    private static MetricsTimer cloneTimerAs(String str, long j) {
        return cloneTimerAs(str, "Application", j);
    }

    public static void complete(@NonNull Component component, @NonNull Completion completion) {
        complete(component.value(), completion.value());
    }

    private static void endTimeline(String str, String str2) {
        endTimeline(str, str2, 0L, Collections.EMPTY_MAP);
    }

    @VisibleForTesting
    static void firstRender() {
        endTimeline(AlexaMetricsConstants.MetricEvents.ACTIVITY_COLD_START, LatencyNamespace.CORE_COLDSTART);
        endTimeline("RN_LOAD_TO_RENDER", LatencyNamespace.CORE_COLDSTART);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String formatLatency(MetricsTimer metricsTimer) {
        return formatLatency(metricsTimer.getEventName(), metricsTimer.getElapsedTime());
    }

    private static TimelineInputArgument.Builder generateArgs(String str, String str2) {
        return new TimelineInputArgument.Builder().withNamespace(str2).withTimelineName(str).withLogOption(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Handler getHandler() {
        Handler handler2;
        synchronized (timeoutLock) {
            if (handler == null) {
                handler = new Handler(Looper.getMainLooper());
            }
            handler2 = handler;
        }
        return handler2;
    }

    private static LatencyInfra getLatencyInfra() {
        Optional optional = ComponentRegistry.getInstance().get(LatencyInfra.class);
        if (optional.isPresent()) {
            latencyInfra = (LatencyInfra) optional.get();
        } else {
            Log.e(TAG, "LatencyInfra invoked before it has been instantiated");
        }
        return latencyInfra;
    }

    public static void invalidate() {
        Object[] objArr = new Object[1];
        StartType startType2 = startType;
        objArr[0] = startType2 == null ? "No start type being measured; nothing was " : startType2.value();
        cleanup();
    }

    @VisibleForTesting
    static boolean isActive() {
        return timer != null;
    }

    public static boolean isStartActive() {
        return startType != null;
    }

    public static boolean isStartType(@NonNull StartType startType2) {
        return startType2.equals(startType);
    }

    public static void onCreateFinished() {
        addTimelineEvent(AlexaMetricsConstants.MetricEvents.ACTIVITY_COLD_START, LatencyNamespace.CORE_COLDSTART, "ON_CREATE_FINISHED");
    }

    public static void onCreateStarted() {
        startTimeline(AlexaMetricsConstants.MetricEvents.ACTIVITY_COLD_START, LatencyNamespace.CORE_COLDSTART);
        startListening();
    }

    public static void override(Completion completion) {
        overrideCompletion = completion;
    }

    public static void pause() {
        MetricsTimer metricsTimer = timer;
        if (metricsTimer == null || suspended) {
            return;
        }
        metricsTimer.pauseTimer();
        cancelTimeout(false);
    }

    public static void reactNativeLoaded(TimelineInputArgument.Builder builder) {
        if (getLatencyInfra() == null) {
            return;
        }
        getLatencyInfra().recordTimeline(TimelineLatencyAction.END_TIMELINE, builder.build());
        addTimelineEvent("RN_LOAD_TO_RENDER", LatencyNamespace.CORE_COLDSTART, "REACT_BRIDGE_READY");
        addTimelineEvent(AlexaMetricsConstants.MetricEvents.ACTIVITY_COLD_START, LatencyNamespace.CORE_COLDSTART, "REACT_BRIDGE_FINISH");
    }

    private static void recordComplete(String str, String str2, long j) {
        metricsService.recordEvent(cloneTimerAs(String.format("native.%s.%s.%s.time", startType.abbr(), str, str2), j));
    }

    private static void recordGenericComplete(String str, long j) {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("native.");
        outline107.append(startType.value());
        outline107.append("_start.time ");
        outline107.append(j - startTime);
        outline107.toString();
        metricsService.recordEvent(cloneTimerAs(String.format("native.%s_start.time", startType.value()), str, j));
        if (!TextUtils.isEmpty(str)) {
            latencyInfra.mark(LatencyMarker.GENERIC_CSL_COMPLETION, str);
        }
        if (hasRecordedCoreLatency || startType != StartType.COLD) {
            return;
        }
        earlyColdRecording = new Recording(startTime, str);
    }

    public static void recordStartupTimeline(long j, long j2, long j3) {
        ArrayMap arrayMap = new ArrayMap();
        arrayMap.put("COMPONENTS_INJECTED", Long.valueOf(j2));
        arrayMap.put("REACT_NATIVE_LIB_INIT", Long.valueOf(j3));
        endTimeline("APPLICATION_INITIALIZATION", "APP_STARTUP", j, arrayMap);
    }

    public static void recordUserPerceivedColdStartLatency() {
        UserPerceivedLatencyService.recordColdStartTimer(metricsService);
    }

    public static void resume() {
        MetricsTimer metricsTimer = timer;
        if (metricsTimer == null || suspended) {
            return;
        }
        metricsTimer.resumeTimer();
        synchronized (timeoutLock) {
            if (timeout != null) {
                startTimeout();
            }
        }
    }

    @Nullable
    public static synchronized TTCFRoute routeDidStart(@NonNull Route route) {
        long elapsedTime;
        ColdTrailingRoute coldTrailingRoute;
        MetricsTimer metricsTimer;
        String str;
        synchronized (LatencyService.class) {
            if (!hasRecordedCoreLatency && (startType == StartType.COLD || earlyColdRecording != null)) {
                long currentTimeMillis = System.currentTimeMillis();
                if (earlyColdRecording != null) {
                    str = "core.cold_start.missed.time";
                    elapsedTime = currentTimeMillis - earlyColdRecording.startTime;
                    metricsTimer = new DefaultMetricsTimer(str, earlyColdRecording.component, null, elapsedTime, false);
                    coldTrailingRoute = new ColdTrailingRoute(earlyColdRecording.startTime);
                    earlyColdRecording = null;
                } else if (timer == null) {
                    return null;
                } else {
                    MetricsTimer cloneTimerAs = cloneTimerAs("core.cold_start.time", "upon.routing." + route.getName(), currentTimeMillis);
                    elapsedTime = cloneTimerAs.getElapsedTime();
                    coldTrailingRoute = new ColdTrailingRoute(startTime);
                    metricsTimer = cloneTimerAs;
                    str = "core.cold_start.time";
                }
                String str2 = "[PERF PROFILE] " + str + " " + elapsedTime;
                metricsService.recordEvent(metricsTimer);
                hasRecordedCoreLatency = true;
                return coldTrailingRoute;
            }
            return null;
        }
    }

    public static void saveCompletion(String str) {
        if (timer == null || startType == null || savedTime != 0) {
            return;
        }
        savedTime = System.currentTimeMillis();
        savedEndpoint = str;
    }

    public static void setMetricsService(MetricsService metricsService2) {
        metricsService = metricsService2;
        if (startType != null) {
            startPlaceholderTimer();
        }
    }

    public static void start(StartType startType2) {
        if (startType != null) {
            return;
        }
        startType = startType2;
        startTime = System.currentTimeMillis();
        savedTime = 0L;
        earlyColdRecording = null;
        if (startType2 == StartType.COLD) {
            hasRecordedCoreLatency = false;
        }
        if (metricsService != null) {
            startPlaceholderTimer();
        }
        if (StartType.COLD.equals(startType)) {
            startTimeout();
        }
        new Object[1][0] = startType.value();
    }

    private static void startListening() {
        ComponentRegistry componentRegistry = ComponentRegistry.getInstance();
        EventBus eventBus = (EventBus) componentRegistry.get(EventBus.class).get();
        ((MainActivityLifecycleObserverRegistrar) componentRegistry.get(MainActivityLifecycleObserverRegistrar.class).get()).addObserver(new FirstRenderObserver(eventBus));
        MultiFilterSubscriber subscriber = eventBus.getSubscriber();
        subscriber.subscribeFilter(new EventTypeMessageFilter(CommsEventBusConstants.COMMS_CONVERSATION_COMPLETE), $$Lambda$LatencyService$fDujmb8Nz5ZQfuZS5c04i3v0Yso.INSTANCE);
        subscriber.subscribeFilter(new EventTypeMessageFilter(CommsEventBusConstants.COMMS_OOBE_COMPLETE), $$Lambda$LatencyService$_Ymp7z_911ooKRxpQqIPhAtuzI.INSTANCE);
    }

    @NonNull
    public static TimelineInputArgument.Builder startLoadingReactNative() {
        TimelineInputArgument.Builder generateArgs = generateArgs(InfraConstants.SP_RN_BRIDGE_LOAD, LatencyNamespace.CORE_COLDSTART);
        TimelineInputArgument.Builder generateArgs2 = generateArgs("RN_LOAD_TO_RENDER", LatencyNamespace.CORE_COLDSTART);
        getLatencyInfra().recordTimeline(TimelineLatencyAction.START_RECORD_TIMELINE, generateArgs.build());
        getLatencyInfra().recordTimeline(TimelineLatencyAction.START_RECORD_TIMELINE, generateArgs2.build());
        addTimelineEvent(AlexaMetricsConstants.MetricEvents.ACTIVITY_COLD_START, LatencyNamespace.CORE_COLDSTART, "REACT_BRIDGE_START");
        return generateArgs;
    }

    private static void startPlaceholderTimer() {
        timer = new DefaultMetricsTimer(TIMER_PLACEHOLDER, COMPONENT_PLACEHOLDER, null, System.currentTimeMillis() - startTime, true);
    }

    private static void startTimeline(String str, String str2) {
        if (getLatencyInfra() == null) {
            return;
        }
        latencyInfra.recordTimeline(TimelineLatencyAction.START_RECORD_TIMELINE, new TimelineInputArgument.Builder().withNamespace(str2).withTimelineName(str).withLogOption(true).build());
    }

    private static void startTimeout() {
        synchronized (timeoutLock) {
            cancelTimeout(true);
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(startType.abbr());
            Timer timer2 = new Timer();
            timer2.schedule(anonymousClass1, TimeUnit.MILLISECONDS.convert(120L, TimeUnit.SECONDS));
            timeout = timer2;
        }
    }

    public static void startWhenReady() {
        startTimeline(AlexaMetricsConstants.MetricEvents.WHEN_READY, LatencyNamespace.CORE_COLDSTART);
        addTimelineEvent(AlexaMetricsConstants.MetricEvents.ACTIVITY_COLD_START, LatencyNamespace.CORE_COLDSTART, "WHEN_READY_START");
    }

    public static void stateMachineComplete() {
        endTimeline(AlexaMetricsConstants.MetricEvents.STARTUP_STATE_MACHINE, LatencyNamespace.CORE_COLDSTART);
    }

    public static void statemachineStarted() {
        startTimeline(AlexaMetricsConstants.MetricEvents.STARTUP_STATE_MACHINE, LatencyNamespace.CORE_COLDSTART);
    }

    public static void suspend() {
        pause();
        suspended = true;
    }

    public static void viewModelStarted() {
        addTimelineEvent(AlexaMetricsConstants.MetricEvents.ACTIVITY_COLD_START, LatencyNamespace.CORE_COLDSTART, "VIEW_MODEL_STARTED");
    }

    public static void voiceInitialized() {
        addTimelineEvent(AlexaMetricsConstants.MetricEvents.ACTIVITY_COLD_START, LatencyNamespace.CORE_COLDSTART, "VOICE_INITIALIZED");
    }

    public static void whenReadyComplete() {
        endTimeline(AlexaMetricsConstants.MetricEvents.WHEN_READY, LatencyNamespace.CORE_COLDSTART);
        addTimelineEvent(AlexaMetricsConstants.MetricEvents.ACTIVITY_COLD_START, LatencyNamespace.CORE_COLDSTART, "WHEN_READY_END");
    }

    /* loaded from: classes12.dex */
    public enum Component {
        FTUE,
        COMMS,
        HOME,
        NATIVE,
        WEB,
        SIGN_IN,
        ELEMENT("lmnt");
        
        private String value;

        Component() {
            this.value = name().toLowerCase();
        }

        public String value() {
            return this.value;
        }

        Component(String str) {
            this.value = str;
        }
    }

    private static MetricsTimer cloneTimerAs(String str, String str2, long j) {
        DefaultMetricsTimer defaultMetricsTimer = new DefaultMetricsTimer(str, str2, null, timer.getElapsedTime(), true);
        defaultMetricsTimer.finishTimer(Long.valueOf(j));
        formatLatency(defaultMetricsTimer);
        return defaultMetricsTimer;
    }

    public static synchronized void complete(@NonNull String str, @NonNull String str2) {
        synchronized (LatencyService.class) {
            if (timer != null && startType != null) {
                long currentTimeMillis = System.currentTimeMillis();
                if (str.equals(Component.HOME.value())) {
                    recordComplete(str, str2, currentTimeMillis);
                    if (savedTime > 0) {
                        recordComplete(Component.WEB.value(), savedEndpoint, savedTime);
                    }
                } else if (savedTime > 0) {
                    if (overrideCompletion != null && str2.equals(overrideCompletion.value())) {
                        recordGenericComplete(str, currentTimeMillis);
                        recordComplete(str, str2, currentTimeMillis);
                        recordComplete(Component.WEB.value(), savedEndpoint, savedTime);
                    } else {
                        recordGenericComplete(str, savedTime);
                        recordComplete(Component.WEB.value(), savedEndpoint, savedTime);
                    }
                } else {
                    recordGenericComplete(str, currentTimeMillis);
                    recordComplete(str, str2, currentTimeMillis);
                }
                firstRender();
                if (StartType.COLD.equals(startType)) {
                    ((LifecycleManager) ComponentRegistry.getInstance().get(LifecycleManager.class).get()).notify(LifecycleEvent.APPLICATION_READY);
                }
                cleanup();
            }
        }
    }

    private static void endTimeline(String str, String str2, long j, Map<String, Long> map) {
        if (getLatencyInfra() == null) {
            return;
        }
        TimelineInputArgument.Builder withLogOption = new TimelineInputArgument.Builder().withNamespace(str2).withTimelineName(str).withEvents(map).withEndTimestamp(Long.valueOf(System.currentTimeMillis())).withLogOption(true);
        if (j != 0) {
            withLogOption = withLogOption.withStartTimestamp(Long.valueOf(j));
        }
        latencyInfra.recordTimeline(TimelineLatencyAction.RECORD_TIMELINE_WITH_CACHED_START_AND_END_TIMESTAMP, withLogOption.build());
    }

    static String formatLatency(MobilyticsMetricsTimer mobilyticsMetricsTimer) {
        return formatLatency(mobilyticsMetricsTimer.getEventName(), mobilyticsMetricsTimer.getElapsedTime());
    }

    private static String formatLatency(String str, long j) {
        StringBuilder sb = new StringBuilder();
        if (j != 0) {
            float convert = (float) TimeUnit.HOURS.convert(j, TimeUnit.MILLISECONDS);
            float convert2 = (float) (TimeUnit.MINUTES.convert(j, TimeUnit.MILLISECONDS) % 60);
            float convert3 = (((float) (j % 1000)) / 1000.0f) + ((float) (TimeUnit.SECONDS.convert(j, TimeUnit.MILLISECONDS) % 60));
            Locale locale = Locale.getDefault();
            int i = (convert > 1.0d ? 1 : (convert == 1.0d ? 0 : -1));
            String str2 = "s";
            if (i >= 0) {
                Object[] objArr = new Object[2];
                objArr[0] = Float.valueOf(convert);
                objArr[1] = i != 0 ? str2 : "";
                sb.append(String.format(locale, " %.0f hour%s", objArr));
            }
            int i2 = (convert2 > 1.0d ? 1 : (convert2 == 1.0d ? 0 : -1));
            if (i2 >= 0) {
                Object[] objArr2 = new Object[2];
                objArr2[0] = Float.valueOf(convert2);
                if (i2 == 0) {
                    str2 = "";
                }
                objArr2[1] = str2;
                sb.append(String.format(locale, " %.0f minute%s", objArr2));
            }
            if (convert3 != 0.0f) {
                sb.append(String.format(locale, " %.3f seconds", Float.valueOf(convert3)));
            }
        }
        if (sb.length() == 0) {
            sb.append("none");
        }
        return String.format("%s latency%s", str, sb);
    }
}
