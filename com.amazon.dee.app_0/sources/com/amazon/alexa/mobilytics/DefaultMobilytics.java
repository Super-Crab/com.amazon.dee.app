package com.amazon.alexa.mobilytics;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.mobilytics.event.LogLevel;
import com.amazon.alexa.mobilytics.event.MobilyticsEvent;
import com.amazon.alexa.mobilytics.event.operational.DefaultMobilyticsErrorEvent;
import com.amazon.alexa.mobilytics.event.operational.DefaultMobilyticsMetricsCounter;
import com.amazon.alexa.mobilytics.event.operational.DefaultMobilyticsMetricsTimer;
import com.amazon.alexa.mobilytics.event.operational.DefaultMobilyticsOperationalEvent;
import com.amazon.alexa.mobilytics.event.operational.MobilyticsMetricsCounter;
import com.amazon.alexa.mobilytics.event.operational.MobilyticsMetricsTimer;
import com.amazon.alexa.mobilytics.event.operational.MobilyticsOperationalEvent;
import com.amazon.alexa.mobilytics.event.operational.OperationalEventType;
import com.amazon.alexa.mobilytics.event.userinteraction.DefaultMobilyticsUserInteractionEvent;
import com.amazon.alexa.mobilytics.event.userinteraction.MobilyticsUserInteractionEvent;
import com.amazon.alexa.mobilytics.executor.Executor;
import com.amazon.alexa.mobilytics.session.MobilyticsSession;
import com.amazon.alexa.mobilytics.session.SessionManager;
import com.amazon.alexa.mobilytics.timeline.TimelineExceptionLogger;
import com.amazon.alexa.mobilytics.timeline.TimelineMessage;
import com.amazon.alexa.mobilytics.util.Log;
import com.amazon.alexa.mobilytics.util.Utils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Preconditions;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Singleton;
@Singleton
/* loaded from: classes9.dex */
public class DefaultMobilytics implements Mobilytics {
    private static final String EVENT_VALUE = "eventValue";
    private static final String STACK_TRACE = "stackTrace";
    private static final String TAG = Log.tag(DefaultMobilytics.class);
    private final MobilyticsConfiguration config;
    private final Executor executor;
    private final SessionManager sessionManager;

    @Inject
    public DefaultMobilytics(@NonNull MobilyticsConfiguration mobilyticsConfiguration, @NonNull Executor executor, @NonNull SessionManager sessionManager) {
        this.executor = (Executor) Preconditions.checkNotNull(executor);
        this.config = (MobilyticsConfiguration) Preconditions.checkNotNull(mobilyticsConfiguration);
        this.sessionManager = (SessionManager) Preconditions.checkNotNull(sessionManager);
    }

    private void recordMobilyticsEvent(MobilyticsEvent mobilyticsEvent) {
        if (mobilyticsEvent != null) {
            this.executor.executeRecordEvent(mobilyticsEvent);
        }
    }

    @Override // com.amazon.alexa.mobilytics.Mobilytics
    public void abortTimeline(@NonNull String str, @NonNull String str2, @NonNull String str3) {
        if (TextUtils.isEmpty(str)) {
            TimelineExceptionLogger.logInvalidInput(TimelineExceptionLogger.PARAM_TIMELINE_NAME);
        } else if (TextUtils.isEmpty(str2)) {
            TimelineExceptionLogger.logInvalidInput(TimelineExceptionLogger.PARAM_TIMELINE_NAMESPACE);
        } else if (TextUtils.isEmpty(str3)) {
            TimelineExceptionLogger.logInvalidInput(TimelineExceptionLogger.PARAM_TIMELINE_ABORT_REASON);
        } else {
            this.executor.executeTimelineEvent(new TimelineMessage(5, str, str2, str3));
        }
    }

    @Override // com.amazon.alexa.mobilytics.Mobilytics
    @NonNull
    public MobilyticsMetricsCounter createCounter(@NonNull String str, @NonNull String str2, @Nullable String str3) {
        return new DefaultMobilyticsMetricsCounter(str, str2, str3);
    }

    @Override // com.amazon.alexa.mobilytics.Mobilytics
    @NonNull
    public MobilyticsOperationalEvent createOperationalEvent(@NonNull String str, String str2, @NonNull String str3, @Nullable String str4) {
        if (OperationalEventType.COUNTER.equals(str2)) {
            return new DefaultMobilyticsMetricsCounter(str, str3, str4);
        }
        if (OperationalEventType.TIMER.equals(str2)) {
            return new DefaultMobilyticsMetricsTimer(str, str3, str4);
        }
        return new DefaultMobilyticsOperationalEvent(str, str2, str3, str4);
    }

    @Override // com.amazon.alexa.mobilytics.Mobilytics
    @NonNull
    public MobilyticsMetricsTimer createTimer(@NonNull String str, @NonNull String str2, @Nullable String str3) {
        return new DefaultMobilyticsMetricsTimer(str, str2, str3);
    }

    @Override // com.amazon.alexa.mobilytics.Mobilytics
    @NonNull
    public MobilyticsUserInteractionEvent createUserInteractionEvent(@NonNull String str, String str2, @NonNull String str3, @NonNull String str4) {
        return new DefaultMobilyticsUserInteractionEvent(str, str2, str3, str4);
    }

    @Override // com.amazon.alexa.mobilytics.Mobilytics
    @NonNull
    public MobilyticsSession getSession() {
        return this.sessionManager.session();
    }

    @Override // com.amazon.alexa.mobilytics.Mobilytics
    public void pauseTimeline(@NonNull String str, @NonNull String str2) {
        if (TextUtils.isEmpty(str)) {
            TimelineExceptionLogger.logInvalidInput(TimelineExceptionLogger.PARAM_TIMELINE_NAME);
        } else if (TextUtils.isEmpty(str2)) {
            TimelineExceptionLogger.logInvalidInput(TimelineExceptionLogger.PARAM_TIMELINE_NAMESPACE);
        } else {
            this.executor.executeTimelineEvent(new TimelineMessage(2, str, str2));
        }
    }

    @Override // com.amazon.alexa.mobilytics.Mobilytics
    public void recordCounter(@NonNull MobilyticsMetricsCounter mobilyticsMetricsCounter) {
        recordMobilyticsEvent(mobilyticsMetricsCounter);
    }

    @Override // com.amazon.alexa.mobilytics.Mobilytics
    public void recordCriticalEvent(@NonNull String str, @NonNull String str2, @NonNull String str3, @Nullable String str4, @Nullable Throwable th) {
        recordErrorEvent(str, str2, str3, str4, LogLevel.CRITICAL, false, th, null);
    }

    @Override // com.amazon.alexa.mobilytics.Mobilytics
    public void recordDataEvent(@NonNull String str, @NonNull String str2, @NonNull String str3, @Nullable String str4) {
        recordOperationalEvent(new DefaultMobilyticsOperationalEvent(str, "data", str3, str4).withDebugInfo(GeneratedOutlineSupport1.outline133(EVENT_VALUE, str2)));
    }

    @Override // com.amazon.alexa.mobilytics.Mobilytics
    public void recordDiagnosticInfo(@NonNull String str, @NonNull Map<String, String> map, @NonNull String str2, @Nullable String str3) {
        recordOperationalEvent(new DefaultMobilyticsOperationalEvent(str, OperationalEventType.DIAGNOSTIC, str2, str3).withDebugInfo(map));
    }

    @Override // com.amazon.alexa.mobilytics.Mobilytics
    @Deprecated
    public void recordErrorEvent(@NonNull String str, @NonNull String str2, @NonNull String str3, @Nullable String str4) {
        recordOperationalEvent(new DefaultMobilyticsOperationalEvent(str, "error", str3, str4).withDebugInfo(GeneratedOutlineSupport1.outline133(EVENT_VALUE, str2)));
    }

    @Override // com.amazon.alexa.mobilytics.Mobilytics
    public void recordEvent(@NonNull MobilyticsEvent mobilyticsEvent, @NonNull String str, @NonNull String str2) {
        if (TextUtils.isEmpty(str)) {
            TimelineExceptionLogger.logInvalidInput(TimelineExceptionLogger.PARAM_TIMELINE_NAME);
        } else if (TextUtils.isEmpty(str2)) {
            TimelineExceptionLogger.logInvalidInput(TimelineExceptionLogger.PARAM_TIMELINE_NAMESPACE);
        } else if (mobilyticsEvent == null) {
            TimelineExceptionLogger.logInvalidInput("Event");
        } else {
            this.executor.executeTimelineEvent(new TimelineMessage(6, str, str2, mobilyticsEvent));
        }
    }

    @Override // com.amazon.alexa.mobilytics.Mobilytics
    public void recordInfoEvent(@NonNull String str, @NonNull String str2, @NonNull String str3, @Nullable String str4, @Nullable Throwable th) {
        recordErrorEvent(str, str2, str3, str4, LogLevel.INFO, false, th, null);
    }

    @Override // com.amazon.alexa.mobilytics.Mobilytics
    public void recordOccurrence(@NonNull String str, boolean z, @NonNull String str2, @Nullable String str3) {
        DefaultMobilyticsMetricsCounter defaultMobilyticsMetricsCounter = new DefaultMobilyticsMetricsCounter(str, str2, str3);
        if (z) {
            defaultMobilyticsMetricsCounter.incrementCounter();
        }
        recordCounter(defaultMobilyticsMetricsCounter);
    }

    @Override // com.amazon.alexa.mobilytics.Mobilytics
    public void recordOperationalEvent(@NonNull MobilyticsOperationalEvent mobilyticsOperationalEvent) {
        recordMobilyticsEvent(mobilyticsOperationalEvent);
    }

    @Override // com.amazon.alexa.mobilytics.Mobilytics
    public void recordPercentOccurrence(@NonNull String str, boolean z, @NonNull String str2, @Nullable String str3) {
        DefaultMobilyticsMetricsCounter defaultMobilyticsMetricsCounter = new DefaultMobilyticsMetricsCounter(str, str2, str3);
        if (z) {
            defaultMobilyticsMetricsCounter.incrementCounterByValue(100L);
        }
        recordCounter(defaultMobilyticsMetricsCounter);
    }

    @Override // com.amazon.alexa.mobilytics.Mobilytics
    public void recordTimer(@NonNull MobilyticsMetricsTimer mobilyticsMetricsTimer) {
        mobilyticsMetricsTimer.finishTimer();
        recordMobilyticsEvent(mobilyticsMetricsTimer);
    }

    @Override // com.amazon.alexa.mobilytics.Mobilytics
    public void recordUserInteractionEvent(@NonNull MobilyticsUserInteractionEvent mobilyticsUserInteractionEvent) {
        recordMobilyticsEvent(mobilyticsUserInteractionEvent);
    }

    @Override // com.amazon.alexa.mobilytics.Mobilytics
    public void recordWarningEvent(@NonNull String str, @NonNull String str2, @NonNull String str3, @Nullable String str4, @Nullable Throwable th) {
        recordErrorEvent(str, str2, str3, str4, LogLevel.WARN, false, th, null);
    }

    @Override // com.amazon.alexa.mobilytics.Mobilytics
    public void resumeTimeline(@NonNull String str, @NonNull String str2) {
        if (TextUtils.isEmpty(str)) {
            TimelineExceptionLogger.logInvalidInput(TimelineExceptionLogger.PARAM_TIMELINE_NAME);
        } else if (TextUtils.isEmpty(str2)) {
            TimelineExceptionLogger.logInvalidInput(TimelineExceptionLogger.PARAM_TIMELINE_NAMESPACE);
        } else {
            this.executor.executeTimelineEvent(new TimelineMessage(3, str, str2));
        }
    }

    @Override // com.amazon.alexa.mobilytics.Mobilytics
    public void startTimeline(@NonNull String str, @NonNull String str2) {
        if (TextUtils.isEmpty(str)) {
            TimelineExceptionLogger.logInvalidInput(TimelineExceptionLogger.PARAM_TIMELINE_NAME);
        } else if (TextUtils.isEmpty(str2)) {
            TimelineExceptionLogger.logInvalidInput(TimelineExceptionLogger.PARAM_TIMELINE_NAMESPACE);
        } else {
            this.executor.executeTimelineEvent(new TimelineMessage(1, str, str2));
        }
    }

    @Override // com.amazon.alexa.mobilytics.Mobilytics
    public void stopTimeline(@NonNull String str, @NonNull String str2) {
        if (TextUtils.isEmpty(str)) {
            TimelineExceptionLogger.logInvalidInput(TimelineExceptionLogger.PARAM_TIMELINE_NAME);
        } else if (TextUtils.isEmpty(str2)) {
            TimelineExceptionLogger.logInvalidInput(TimelineExceptionLogger.PARAM_TIMELINE_NAMESPACE);
        } else {
            this.executor.executeTimelineEvent(new TimelineMessage(4, str, str2));
        }
    }

    @Override // com.amazon.alexa.mobilytics.Mobilytics
    @NonNull
    public MobilyticsMetricsCounter createCounter(@NonNull String str, @NonNull String str2, @Nullable String str3, @NonNull String str4) {
        return new DefaultMobilyticsMetricsCounter(str, str2, str3, str4);
    }

    @Override // com.amazon.alexa.mobilytics.Mobilytics
    @NonNull
    public MobilyticsMetricsTimer createTimer(@NonNull String str, @NonNull String str2, @Nullable String str3, @NonNull String str4) {
        return new DefaultMobilyticsMetricsTimer(str, str2, str3, str4);
    }

    @Override // com.amazon.alexa.mobilytics.Mobilytics
    @NonNull
    public MobilyticsUserInteractionEvent createUserInteractionEvent(@NonNull String str, String str2, @NonNull String str3, @NonNull String str4, @NonNull String str5) {
        return new DefaultMobilyticsUserInteractionEvent(str, str2, str3, str4, str5);
    }

    @Override // com.amazon.alexa.mobilytics.Mobilytics
    public void recordCriticalEvent(@NonNull String str, @NonNull String str2, @NonNull String str3, @Nullable String str4, @Nullable Throwable th, @NonNull String str5) {
        recordErrorEvent(str, str2, str3, str4, LogLevel.CRITICAL, false, th, str5);
    }

    @Override // com.amazon.alexa.mobilytics.Mobilytics
    public void recordInfoEvent(@NonNull String str, @NonNull String str2, @NonNull String str3, @Nullable String str4, @Nullable Throwable th, @NonNull String str5) {
        recordErrorEvent(str, str2, str3, str4, LogLevel.INFO, false, th, str5);
    }

    @Override // com.amazon.alexa.mobilytics.Mobilytics
    public void recordOperationalEvent(@NonNull String str, @NonNull String str2, @Nullable String str3) {
        recordOperationalEvent(new DefaultMobilyticsOperationalEvent(str, str2, str3));
    }

    @Override // com.amazon.alexa.mobilytics.Mobilytics
    public void recordWarningEvent(@NonNull String str, @NonNull String str2, @NonNull String str3, @Nullable String str4, @Nullable Throwable th, @NonNull String str5) {
        recordErrorEvent(str, str2, str3, str4, LogLevel.WARN, false, th, str5);
    }

    @Override // com.amazon.alexa.mobilytics.Mobilytics
    @NonNull
    public MobilyticsMetricsTimer createTimer(@NonNull String str, @NonNull String str2, @Nullable String str3, long j, boolean z) {
        return new DefaultMobilyticsMetricsTimer(str, str2, str3, j, z);
    }

    @Override // com.amazon.alexa.mobilytics.Mobilytics
    public void recordCriticalEvent(@NonNull String str, @NonNull String str2, @NonNull String str3, @Nullable String str4, @NonNull boolean z) {
        recordErrorEvent(str, str2, str3, str4, LogLevel.CRITICAL, z, null, null);
    }

    @Override // com.amazon.alexa.mobilytics.Mobilytics
    public void recordInfoEvent(@NonNull String str, @NonNull String str2, @NonNull String str3, @Nullable String str4, @NonNull boolean z) {
        recordErrorEvent(str, str2, str3, str4, LogLevel.INFO, z, null, null);
    }

    @Override // com.amazon.alexa.mobilytics.Mobilytics
    public void recordOperationalEvent(@NonNull String str, @NonNull String str2, @Nullable String str3, @NonNull String str4) {
        recordOperationalEvent(new DefaultMobilyticsOperationalEvent(str, str2, str3, str4));
    }

    @Override // com.amazon.alexa.mobilytics.Mobilytics
    public void recordWarningEvent(@NonNull String str, @NonNull String str2, @NonNull String str3, @Nullable String str4, @NonNull boolean z) {
        recordErrorEvent(str, str2, str3, str4, LogLevel.WARN, z, null, null);
    }

    @Override // com.amazon.alexa.mobilytics.Mobilytics
    @NonNull
    public MobilyticsMetricsTimer createTimer(@NonNull String str, @NonNull String str2, @Nullable String str3, long j, boolean z, @NonNull String str4) {
        return new DefaultMobilyticsMetricsTimer(str, str2, str3, j, z, str4);
    }

    @Override // com.amazon.alexa.mobilytics.Mobilytics
    public void recordCriticalEvent(@NonNull String str, @NonNull String str2, @NonNull String str3, @Nullable String str4, @NonNull boolean z, @NonNull String str5) {
        recordErrorEvent(str, str2, str3, str4, LogLevel.CRITICAL, z, null, str5);
    }

    @Override // com.amazon.alexa.mobilytics.Mobilytics
    public void recordDiagnosticInfo(@NonNull String str, @NonNull Map<String, String> map, @NonNull String str2, @Nullable String str3, @NonNull String str4) {
        recordOperationalEvent(new DefaultMobilyticsOperationalEvent(str, OperationalEventType.DIAGNOSTIC, str2, str3, str4).withDebugInfo(map));
    }

    @Override // com.amazon.alexa.mobilytics.Mobilytics
    public void recordInfoEvent(@NonNull String str, @NonNull String str2, @NonNull String str3, @Nullable String str4, @NonNull boolean z, @NonNull String str5) {
        recordErrorEvent(str, str2, str3, str4, LogLevel.INFO, z, null, str5);
    }

    @Override // com.amazon.alexa.mobilytics.Mobilytics
    public void recordOccurrence(@NonNull String str, boolean z, @NonNull String str2, @Nullable String str3, @NonNull String str4) {
        DefaultMobilyticsMetricsCounter defaultMobilyticsMetricsCounter = new DefaultMobilyticsMetricsCounter(str, str2, str3, str4);
        if (z) {
            defaultMobilyticsMetricsCounter.incrementCounter();
        }
        recordCounter(defaultMobilyticsMetricsCounter);
    }

    @Override // com.amazon.alexa.mobilytics.Mobilytics
    public void recordPercentOccurrence(@NonNull String str, boolean z, @NonNull String str2, @Nullable String str3, @NonNull String str4) {
        DefaultMobilyticsMetricsCounter defaultMobilyticsMetricsCounter = new DefaultMobilyticsMetricsCounter(str, str2, str3, str4);
        if (z) {
            defaultMobilyticsMetricsCounter.incrementCounterByValue(100L);
        }
        recordCounter(defaultMobilyticsMetricsCounter);
    }

    @Override // com.amazon.alexa.mobilytics.Mobilytics
    public void recordWarningEvent(@NonNull String str, @NonNull String str2, @NonNull String str3, @Nullable String str4, @NonNull boolean z, @NonNull String str5) {
        recordErrorEvent(str, str2, str3, str4, LogLevel.WARN, z, null, str5);
    }

    private void recordErrorEvent(@NonNull String str, @NonNull String str2, @NonNull String str3, @Nullable String str4, String str5, @NonNull boolean z, @Nullable Throwable th, @Nullable String str6) {
        if (str5.equals(LogLevel.CRITICAL) || this.config.isDebug()) {
            HashMap hashMap = new HashMap();
            String str7 = null;
            if (th != null) {
                hashMap.put(STACK_TRACE, Utils.getStackTrace(th.getStackTrace()));
                str7 = th.getMessage();
            } else if (z) {
                hashMap.put(STACK_TRACE, Utils.getStackTrace(new Throwable().getStackTrace()));
            }
            String str8 = str7;
            DefaultMobilyticsErrorEvent defaultMobilyticsErrorEvent = new DefaultMobilyticsErrorEvent(str, str5, str2, str3, str4, str6);
            if (th != null) {
                defaultMobilyticsErrorEvent.withShortMessage(str8);
            }
            defaultMobilyticsErrorEvent.withDebugInfo(hashMap);
            recordOperationalEvent(defaultMobilyticsErrorEvent);
        }
    }

    @Override // com.amazon.alexa.mobilytics.Mobilytics
    public void recordDataEvent(@NonNull String str, @NonNull String str2, @NonNull String str3, @Nullable String str4, @NonNull String str5) {
        recordOperationalEvent(new DefaultMobilyticsOperationalEvent(str, "data", str3, str4, str5).withDebugInfo(GeneratedOutlineSupport1.outline133(EVENT_VALUE, str2)));
    }

    @Override // com.amazon.alexa.mobilytics.Mobilytics
    @NonNull
    public MobilyticsOperationalEvent createOperationalEvent(@NonNull String str, String str2, @NonNull String str3, @Nullable String str4, @NonNull String str5) {
        if (OperationalEventType.COUNTER.equals(str2)) {
            return new DefaultMobilyticsMetricsCounter(str, str3, str4, str5);
        }
        if (OperationalEventType.TIMER.equals(str2)) {
            return new DefaultMobilyticsMetricsTimer(str, str3, str4, str5);
        }
        return new DefaultMobilyticsOperationalEvent(str, str2, str3, str4, str5);
    }
}
