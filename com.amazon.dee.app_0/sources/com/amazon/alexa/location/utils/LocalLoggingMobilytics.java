package com.amazon.alexa.location.utils;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.mobilytics.event.MobilyticsEvent;
import com.amazon.alexa.mobilytics.event.operational.MobilyticsMetricsCounter;
import com.amazon.alexa.mobilytics.event.operational.MobilyticsMetricsTimer;
import com.amazon.alexa.mobilytics.event.operational.MobilyticsOperationalEvent;
import com.amazon.alexa.mobilytics.event.userinteraction.MobilyticsUserInteractionEvent;
import com.amazon.alexa.mobilytics.session.MobilyticsSession;
import com.amazon.alexa.protocols.service.api.LazyComponent;
import java.util.Locale;
import java.util.Map;
/* loaded from: classes9.dex */
public class LocalLoggingMobilytics implements Mobilytics {
    private static final String TAG = "LocalMobilytics";
    private static LocalLoggingMobilytics theInstance;
    @NonNull
    private final Context context;
    @NonNull
    private final Mobilytics mobilytics;

    public LocalLoggingMobilytics(@NonNull Mobilytics mobilytics, @NonNull Context context) {
        this.mobilytics = mobilytics;
        this.context = context;
    }

    public static LazyComponent<Mobilytics> getFactory(final LazyComponent<Mobilytics> lazyComponent, @NonNull final Context context) {
        return new LazyComponent() { // from class: com.amazon.alexa.location.utils.-$$Lambda$LocalLoggingMobilytics$gRD6J8ibg6bbBwCAWVP3tMkyWsw
            @Override // com.amazon.alexa.protocols.service.api.LazyComponent, javax.inject.Provider
            /* renamed from: get */
            public final Object mo10268get() {
                return LocalLoggingMobilytics.lambda$getFactory$0(LazyComponent.this, context);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Mobilytics lambda$getFactory$0(LazyComponent lazyComponent, Context context) {
        if (theInstance == null) {
            theInstance = new LocalLoggingMobilytics((Mobilytics) lazyComponent.mo10268get(), context);
        }
        return theInstance;
    }

    @Override // com.amazon.alexa.mobilytics.Mobilytics
    public void abortTimeline(@NonNull String str, @NonNull String str2, @NonNull String str3) {
        WriteToFile.appendLogForDebugBuild(this.context, String.format(Locale.US, "abortTimeline(%s, %s, %s)", str, str2, str3));
        this.mobilytics.abortTimeline(str, str2, str3);
    }

    @Override // com.amazon.alexa.mobilytics.Mobilytics
    @NonNull
    public MobilyticsMetricsCounter createCounter(@NonNull String str, @NonNull String str2, @Nullable String str3) {
        return this.mobilytics.createCounter(str, str2, str3);
    }

    @Override // com.amazon.alexa.mobilytics.Mobilytics
    @NonNull
    public MobilyticsOperationalEvent createOperationalEvent(@NonNull String str, String str2, @NonNull String str3, @Nullable String str4) {
        return this.mobilytics.createOperationalEvent(str, str2, str3, str4);
    }

    @Override // com.amazon.alexa.mobilytics.Mobilytics
    @NonNull
    public MobilyticsMetricsTimer createTimer(@NonNull String str, @NonNull String str2, @Nullable String str3, long j, boolean z) {
        return this.mobilytics.createTimer(str, str2, str3, j, z);
    }

    @Override // com.amazon.alexa.mobilytics.Mobilytics
    @NonNull
    public MobilyticsUserInteractionEvent createUserInteractionEvent(@NonNull String str, String str2, @NonNull String str3, @NonNull String str4) {
        return this.mobilytics.createUserInteractionEvent(str, str2, str3, str4);
    }

    @Override // com.amazon.alexa.mobilytics.Mobilytics
    public MobilyticsSession getSession() {
        return this.mobilytics.getSession();
    }

    @Override // com.amazon.alexa.mobilytics.Mobilytics
    public void pauseTimeline(@NonNull String str, @NonNull String str2) {
        WriteToFile.appendLogForDebugBuild(this.context, String.format(Locale.US, "pauseTimeline(%s, %s, %s)", str, str2));
        this.mobilytics.pauseTimeline(str, str2);
    }

    @Override // com.amazon.alexa.mobilytics.Mobilytics
    public void recordCounter(MobilyticsMetricsCounter mobilyticsMetricsCounter) {
        WriteToFile.appendLogForDebugBuild(this.context, String.format(Locale.US, "recordCounter(name: %s, count: %d, component: %s, sub: %s, owner: %s)", mobilyticsMetricsCounter.getEventName(), Long.valueOf(mobilyticsMetricsCounter.getCount()), mobilyticsMetricsCounter.getComponent(), mobilyticsMetricsCounter.getSubComponent(), mobilyticsMetricsCounter.getOwnerIdentifier()));
        this.mobilytics.recordCounter(mobilyticsMetricsCounter);
    }

    @Override // com.amazon.alexa.mobilytics.Mobilytics
    public void recordCriticalEvent(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull String str4, @Nullable Throwable th) {
        WriteToFile.appendLogForDebugBuild(this.context, String.format(Locale.US, "recordCriticalEvent(%s, %s, %s, %s, %s)", str, str2, str3, str4, th.getMessage()));
        this.mobilytics.recordCriticalEvent(str, str2, str3, str4, th);
    }

    @Override // com.amazon.alexa.mobilytics.Mobilytics
    public void recordDataEvent(@NonNull String str, @NonNull String str2, @NonNull String str3, @Nullable String str4) {
        WriteToFile.appendLogForDebugBuild(this.context, String.format(Locale.US, "recordDataEvent(%s, %s, %s, %s)", str, str2, str3, str4));
        this.mobilytics.recordDataEvent(str, str2, str3, str4);
    }

    @Override // com.amazon.alexa.mobilytics.Mobilytics
    public void recordDiagnosticInfo(@NonNull String str, @NonNull Map<String, String> map, @NonNull String str2, @Nullable String str3) {
        WriteToFile.appendLogForDebugBuild(this.context, String.format(Locale.US, "recordDiagnosticEvent(%s, <map>, %s, %s)", str, str2, str3));
        this.mobilytics.recordDiagnosticInfo(str, map, str2, str3);
    }

    @Override // com.amazon.alexa.mobilytics.Mobilytics
    @Deprecated
    public void recordErrorEvent(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull String str4) {
        WriteToFile.appendLogForDebugBuild(this.context, String.format(Locale.US, "recordErrorEvent(%s, %s, %s, %s)", str, str2, str3, str4));
        this.mobilytics.recordErrorEvent(str, str2, str3, str4);
    }

    @Override // com.amazon.alexa.mobilytics.Mobilytics
    public void recordEvent(@NonNull MobilyticsEvent mobilyticsEvent, @NonNull String str, @NonNull String str2) {
        WriteToFile.appendLogForDebugBuild(this.context, String.format(Locale.US, "recordEvent(%s, %s, %s)", mobilyticsEvent.getEventName(), str, str2));
        this.mobilytics.recordEvent(mobilyticsEvent, str, str2);
    }

    @Override // com.amazon.alexa.mobilytics.Mobilytics
    public void recordInfoEvent(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull String str4, @Nullable Throwable th) {
        WriteToFile.appendLogForDebugBuild(this.context, String.format(Locale.US, "recordInfoEvent(%s, %s, %s, %s, %s)", str, str2, str3, str4, th.getMessage()));
        this.mobilytics.recordInfoEvent(str, str2, str3, str4, th);
    }

    @Override // com.amazon.alexa.mobilytics.Mobilytics
    public void recordOccurrence(@NonNull String str, boolean z, @NonNull String str2, @Nullable String str3) {
        WriteToFile.appendLogForDebugBuild(this.context, String.format(Locale.US, "recordOccurrence(%s, %b, %s, %s)", str, Boolean.valueOf(z), str2, str3));
        this.mobilytics.recordOccurrence(str, z, str2, str3);
    }

    @Override // com.amazon.alexa.mobilytics.Mobilytics
    public void recordOperationalEvent(MobilyticsOperationalEvent mobilyticsOperationalEvent) {
        WriteToFile.appendLogForDebugBuild(this.context, String.format(Locale.US, "recordOperationalEvent(name: %s, type: %s, component: %s, sub: %s, owner: %s)", mobilyticsOperationalEvent.getEventName(), mobilyticsOperationalEvent.getOperationalEventType(), mobilyticsOperationalEvent.getComponent(), mobilyticsOperationalEvent.getSubComponent(), mobilyticsOperationalEvent.getOwnerIdentifier()));
        this.mobilytics.recordOperationalEvent(mobilyticsOperationalEvent);
    }

    @Override // com.amazon.alexa.mobilytics.Mobilytics
    public void recordPercentOccurrence(@NonNull String str, boolean z, @NonNull String str2, @Nullable String str3) {
        WriteToFile.appendLogForDebugBuild(this.context, String.format(Locale.US, "recordPercentOccurrence(%s, %b, %s, %s)", str, Boolean.valueOf(z), str2, str3));
        this.mobilytics.recordPercentOccurrence(str, z, str2, str3);
    }

    @Override // com.amazon.alexa.mobilytics.Mobilytics
    public void recordTimer(MobilyticsMetricsTimer mobilyticsMetricsTimer) {
        WriteToFile.appendLogForDebugBuild(this.context, String.format(Locale.US, "recordTimer(name: %s, time: %d, component: %s, sub: %s, owner: %s)", mobilyticsMetricsTimer.getEventName(), Long.valueOf(mobilyticsMetricsTimer.getElapsedTime()), mobilyticsMetricsTimer.getComponent(), mobilyticsMetricsTimer.getSubComponent(), mobilyticsMetricsTimer.getOwnerIdentifier()));
        this.mobilytics.recordTimer(mobilyticsMetricsTimer);
    }

    @Override // com.amazon.alexa.mobilytics.Mobilytics
    public void recordUserInteractionEvent(MobilyticsUserInteractionEvent mobilyticsUserInteractionEvent) {
        WriteToFile.appendLogForDebugBuild(this.context, String.format(Locale.US, "recordUserInteractionEvent(%s)", mobilyticsUserInteractionEvent.getInteractionType()));
        this.mobilytics.recordUserInteractionEvent(mobilyticsUserInteractionEvent);
    }

    @Override // com.amazon.alexa.mobilytics.Mobilytics
    public void recordWarningEvent(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull String str4, @Nullable Throwable th) {
        WriteToFile.appendLogForDebugBuild(this.context, String.format(Locale.US, "recordWarningEvent(%s, %s, %s, %s, %s)", str, str2, str3, str4, th.getMessage()));
        this.mobilytics.recordWarningEvent(str, str2, str3, str4, th);
    }

    @Override // com.amazon.alexa.mobilytics.Mobilytics
    public void resumeTimeline(@NonNull String str, @NonNull String str2) {
        WriteToFile.appendLogForDebugBuild(this.context, String.format(Locale.US, "resumeTimeline(%s, %s, %s)", str, str2));
        this.mobilytics.resumeTimeline(str, str2);
    }

    @Override // com.amazon.alexa.mobilytics.Mobilytics
    public void startTimeline(@NonNull String str, @NonNull String str2) {
        WriteToFile.appendLogForDebugBuild(this.context, String.format(Locale.US, "startTimeline(%s, %s, %s)", str, str2));
        this.mobilytics.startTimeline(str, str2);
    }

    @Override // com.amazon.alexa.mobilytics.Mobilytics
    public void stopTimeline(@NonNull String str, @NonNull String str2) {
        WriteToFile.appendLogForDebugBuild(this.context, String.format(Locale.US, "stopTimeline(%s, %s, %s)", str, str2));
        this.mobilytics.stopTimeline(str, str2);
    }

    @Override // com.amazon.alexa.mobilytics.Mobilytics
    @NonNull
    public MobilyticsMetricsCounter createCounter(@NonNull String str, @NonNull String str2, @Nullable String str3, @NonNull String str4) {
        return this.mobilytics.createCounter(str, str2, str3, str4);
    }

    @Override // com.amazon.alexa.mobilytics.Mobilytics
    @NonNull
    public MobilyticsOperationalEvent createOperationalEvent(@NonNull String str, String str2, @NonNull String str3, @Nullable String str4, @NonNull String str5) {
        return this.mobilytics.createOperationalEvent(str, str2, str3, str4, str5);
    }

    @Override // com.amazon.alexa.mobilytics.Mobilytics
    @NonNull
    public MobilyticsMetricsTimer createTimer(@NonNull String str, @NonNull String str2, @Nullable String str3, long j, boolean z, @NonNull String str4) {
        return this.mobilytics.createTimer(str, str2, str3, j, z, str4);
    }

    @Override // com.amazon.alexa.mobilytics.Mobilytics
    @NonNull
    public MobilyticsUserInteractionEvent createUserInteractionEvent(@NonNull String str, String str2, @NonNull String str3, @NonNull String str4, @NonNull String str5) {
        return this.mobilytics.createUserInteractionEvent(str, str2, str3, str4, str5);
    }

    @Override // com.amazon.alexa.mobilytics.Mobilytics
    @NonNull
    public MobilyticsMetricsTimer createTimer(@NonNull String str, @NonNull String str2, @Nullable String str3) {
        return this.mobilytics.createTimer(str, str2, str3);
    }

    @Override // com.amazon.alexa.mobilytics.Mobilytics
    @NonNull
    public MobilyticsMetricsTimer createTimer(@NonNull String str, @NonNull String str2, @Nullable String str3, @NonNull String str4) {
        return this.mobilytics.createTimer(str, str2, str3, str4);
    }

    @Override // com.amazon.alexa.mobilytics.Mobilytics
    public void recordDataEvent(@NonNull String str, @NonNull String str2, @NonNull String str3, @Nullable String str4, @NonNull String str5) {
        WriteToFile.appendLogForDebugBuild(this.context, String.format(Locale.US, "recordDataEvent(%s, %s, %s, %s, %s)", str, str2, str3, str4, str5));
        this.mobilytics.recordDataEvent(str, str2, str3, str4, str5);
    }

    @Override // com.amazon.alexa.mobilytics.Mobilytics
    public void recordDiagnosticInfo(@NonNull String str, @NonNull Map<String, String> map, @NonNull String str2, @Nullable String str3, @NonNull String str4) {
        WriteToFile.appendLogForDebugBuild(this.context, String.format(Locale.US, "recordDiagnosticEvent(%s, <map>, %s, %s, %s)", str, str2, str3, str4));
        this.mobilytics.recordDiagnosticInfo(str, map, str2, str3, str4);
    }

    @Override // com.amazon.alexa.mobilytics.Mobilytics
    public void recordOccurrence(@NonNull String str, boolean z, @NonNull String str2, @Nullable String str3, @NonNull String str4) {
        WriteToFile.appendLogForDebugBuild(this.context, String.format(Locale.US, "recordOccurrence(%s, %b, %s, %s, %s)", str, Boolean.valueOf(z), str2, str3, str4));
        this.mobilytics.recordOccurrence(str, z, str2, str3, str4);
    }

    @Override // com.amazon.alexa.mobilytics.Mobilytics
    public void recordPercentOccurrence(@NonNull String str, boolean z, @NonNull String str2, @Nullable String str3, @NonNull String str4) {
        WriteToFile.appendLogForDebugBuild(this.context, String.format(Locale.US, "recordPercentOccurrence(%s, %b, %s, %s, %s)", str, Boolean.valueOf(z), str2, str3, str4));
        this.mobilytics.recordPercentOccurrence(str, z, str2, str3, str4);
    }

    @Override // com.amazon.alexa.mobilytics.Mobilytics
    public void recordCriticalEvent(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull String str4, @Nullable Throwable th, @NonNull String str5) {
        WriteToFile.appendLogForDebugBuild(this.context, String.format(Locale.US, "recordCriticalEvent(%s, %s, %s, %s, %s, %s)", str, str2, str3, str4, th.getMessage(), str5));
        this.mobilytics.recordCriticalEvent(str, str2, str3, str4, th, str5);
    }

    @Override // com.amazon.alexa.mobilytics.Mobilytics
    public void recordInfoEvent(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull String str4, @Nullable Throwable th, @NonNull String str5) {
        WriteToFile.appendLogForDebugBuild(this.context, String.format(Locale.US, "recordInfoEvent(%s, %s, %s, %s, %s, %s)", str, str2, str3, str4, th.getMessage(), str5));
        this.mobilytics.recordInfoEvent(str, str2, str3, str4, th, str5);
    }

    @Override // com.amazon.alexa.mobilytics.Mobilytics
    public void recordWarningEvent(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull String str4, @Nullable Throwable th, @NonNull String str5) {
        WriteToFile.appendLogForDebugBuild(this.context, String.format(Locale.US, "recordWarningEvent(%s, %s, %s, %s, %s, %s)", str, str2, str3, str4, th.getMessage(), str5));
        this.mobilytics.recordWarningEvent(str, str2, str3, str4, th, str5);
    }

    @Override // com.amazon.alexa.mobilytics.Mobilytics
    public void recordOperationalEvent(@NonNull String str, @NonNull String str2, @Nullable String str3) {
        WriteToFile.appendLogForDebugBuild(this.context, String.format(Locale.US, "recordOperationalEvent(%s, %s, %s)", str, str2, str3));
        this.mobilytics.recordOperationalEvent(str, str2, str3);
    }

    @Override // com.amazon.alexa.mobilytics.Mobilytics
    public void recordCriticalEvent(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull String str4, boolean z) {
        WriteToFile.appendLogForDebugBuild(this.context, String.format(Locale.US, "recordCriticalEvent(%s, %s, %s, %s, %b)", str, str2, str3, str4, Boolean.valueOf(z)));
        this.mobilytics.recordCriticalEvent(str, str2, str3, str4, z);
    }

    @Override // com.amazon.alexa.mobilytics.Mobilytics
    public void recordInfoEvent(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull String str4, boolean z) {
        WriteToFile.appendLogForDebugBuild(this.context, String.format(Locale.US, "recordInfoEvent(%s, %s, %s, %s, %b)", str, str2, str3, str4, Boolean.valueOf(z)));
        this.mobilytics.recordInfoEvent(str, str2, str3, str4, z);
    }

    @Override // com.amazon.alexa.mobilytics.Mobilytics
    public void recordOperationalEvent(@NonNull String str, @NonNull String str2, @Nullable String str3, @NonNull String str4) {
        WriteToFile.appendLogForDebugBuild(this.context, String.format(Locale.US, "recordOperationalEvent(%s, %s, %s, %s)", str, str2, str3, str4));
        this.mobilytics.recordOperationalEvent(str, str2, str3, str4);
    }

    @Override // com.amazon.alexa.mobilytics.Mobilytics
    public void recordWarningEvent(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull String str4, boolean z) {
        WriteToFile.appendLogForDebugBuild(this.context, String.format(Locale.US, "recordWarningEvent(%s, %s, %s, %s, %b)", str, str2, str3, str4, Boolean.valueOf(z)));
        this.mobilytics.recordWarningEvent(str, str2, str3, str4, z);
    }

    @Override // com.amazon.alexa.mobilytics.Mobilytics
    public void recordCriticalEvent(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull String str4, boolean z, @NonNull String str5) {
        WriteToFile.appendLogForDebugBuild(this.context, String.format(Locale.US, "recordCriticalEvent(%s, %s, %s, %s, %b, %s)", str, str2, str3, str4, Boolean.valueOf(z), str5));
        this.mobilytics.recordCriticalEvent(str, str2, str3, str4, z, str5);
    }

    @Override // com.amazon.alexa.mobilytics.Mobilytics
    public void recordInfoEvent(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull String str4, boolean z, @NonNull String str5) {
        WriteToFile.appendLogForDebugBuild(this.context, String.format(Locale.US, "recordInfoEvent(%s, %s, %s, %s, %b, %s)", str, str2, str3, str4, Boolean.valueOf(z), str5));
        this.mobilytics.recordInfoEvent(str, str2, str3, str4, z, str5);
    }

    @Override // com.amazon.alexa.mobilytics.Mobilytics
    public void recordWarningEvent(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull String str4, boolean z, @NonNull String str5) {
        WriteToFile.appendLogForDebugBuild(this.context, String.format(Locale.US, "recordWarningEvent(%s, %s, %s, %s, %b, %s)", str, str2, str3, str4, Boolean.valueOf(z), str5));
        this.mobilytics.recordWarningEvent(str, str2, str3, str4, z, str5);
    }
}
