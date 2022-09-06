package com.amazon.alexa.handsfree.voiceappreporter.services;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.core.app.JobIntentService;
import androidx.core.app.SafeDequeueJobIntentService;
import com.amazon.alexa.handsfree.devices.AMPDInformationProvider;
import com.amazon.alexa.handsfree.metrics.dependencies.AlexaMobileMetricsComponent;
import com.amazon.alexa.handsfree.protocols.Initializer;
import com.amazon.alexa.handsfree.protocols.dependencies.AhfComponentsProvider;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilderProvider;
import com.amazon.alexa.handsfree.protocols.utils.CrashReportRecorder;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.handsfree.voiceappreporter.VoiceAppEvent;
import com.amazon.alexa.handsfree.voiceappreporter.database.VoiceAppEventReporterDatabaseHelper;
import com.amazon.alexa.handsfree.voiceappreporter.schedulers.VoiceAppEventReporterServiceScheduler;
import com.android.tools.r8.GeneratedOutlineSupport1;
import javax.inject.Inject;
/* loaded from: classes8.dex */
public class VoiceAppEventReporterService extends SafeDequeueJobIntentService {
    private static final int JOB_ID = 30009;
    private static final String TAG = VoiceAppEventReporterService.class.getSimpleName();
    @Inject
    AMPDInformationProvider mAMPDInformationProvider;
    @Inject
    CrashReportRecorder mCrashReportRecorder;
    @Inject
    Initializer mInitializer;
    @Inject
    MetricsBuilderProvider mMetricsBuilderProvider;
    @Inject
    VoiceAppEventReporterDatabaseHelper mVoiceAppEventReporterDatabaseHelper;
    @Inject
    VoiceAppEventReporterServiceScheduler mVoiceAppEventReporterServiceScheduler;

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    /* loaded from: classes8.dex */
    public enum MetricType {
        VOICE_APP_EVENT("VoiceAppEvent"),
        CURSOR_ERROR("CursorError"),
        UNABLE_DESERIALIZE_EXCEPTION("UnableDeserializeException");
        
        private final String mValue;

        MetricType(@NonNull String str) {
            this.mValue = str;
        }

        @NonNull
        public String getValue() {
            return this.mValue;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    /* loaded from: classes8.dex */
    public static class VoiceAppNonFatalException extends Exception {
        private VoiceAppNonFatalException(String str, Throwable th) {
            super(str, th);
        }
    }

    public static void enqueueWork(@NonNull Context context, @NonNull Intent intent) {
        JobIntentService.enqueueWork(context, VoiceAppEventReporterService.class, (int) JOB_ID, intent);
    }

    private void reportAppCrash(@NonNull VoiceAppEvent voiceAppEvent) {
        Throwable throwable = voiceAppEvent.getThrowable();
        if (throwable != null) {
            this.mCrashReportRecorder.reportNonFatalCrash(this, throwable.getStackTrace()[0].getMethodName(), getVoiceAppNonFatalException(this.mAMPDInformationProvider.getVoiceAppPackageName(), throwable));
            Log.d(TAG, "Crash has been reported");
        } else {
            Log.d(TAG, "There's no crash being reported. Adding deserialize error");
            this.mMetricsBuilderProvider.newBuilder().withNonFatalErrorEventMetric(TAG, MetricType.UNABLE_DESERIALIZE_EXCEPTION.getValue()).emit(this);
        }
        this.mMetricsBuilderProvider.newBuilder().withPercentileMetricFailure(TAG, MetricType.VOICE_APP_EVENT.getValue()).emit(this);
    }

    private void reportAppStart() {
        this.mMetricsBuilderProvider.newBuilder().withPercentileMetricSuccess(TAG, MetricType.VOICE_APP_EVENT.getValue()).emit(this);
    }

    private void reportCursorError() {
        Log.d(TAG, "There was an error accessing cursor. Adding cursor error");
        this.mMetricsBuilderProvider.newBuilder().withNonFatalErrorEventMetric(TAG, MetricType.CURSOR_ERROR.getValue()).emit(this);
    }

    @VisibleForTesting
    VoiceAppNonFatalException getVoiceAppNonFatalException(String str, Throwable th) {
        return new VoiceAppNonFatalException(GeneratedOutlineSupport1.outline72("Voice app: ", str), th);
    }

    @Override // androidx.core.app.SafeDequeueJobIntentService, androidx.core.app.JobIntentService, android.app.Service
    public void onCreate() {
        super.onCreate();
        ((AlexaMobileMetricsComponent) AhfComponentsProvider.getComponent(this, AlexaMobileMetricsComponent.class)).inject(this);
        this.mInitializer.initialize(this);
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0033, code lost:
        if (r2 == 1) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0036, code lost:
        com.amazon.alexa.handsfree.protocols.utils.Log.d(com.amazon.alexa.handsfree.voiceappreporter.services.VoiceAppEventReporterService.TAG, "Reporting a crash");
        reportAppCrash(r0);
     */
    @Override // androidx.core.app.JobIntentService
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void onHandleWork(@androidx.annotation.NonNull android.content.Intent r7) {
        /*
            r6 = this;
        L0:
            r7 = 0
            com.amazon.alexa.handsfree.voiceappreporter.database.VoiceAppEventReporterDatabaseHelper r0 = r6.mVoiceAppEventReporterDatabaseHelper     // Catch: java.lang.Exception -> L58
            com.amazon.alexa.handsfree.voiceappreporter.VoiceAppEvent r0 = r0.popVoiceEvent()     // Catch: java.lang.Exception -> L58
            if (r0 == 0) goto L4c
            java.lang.String r1 = r0.getEventType()     // Catch: java.lang.Exception -> L58
            r2 = -1
            int r3 = r1.hashCode()     // Catch: java.lang.Exception -> L58
            r4 = -200567657(0xfffffffff40b9497, float:-4.4234804E31)
            r5 = 1
            if (r3 == r4) goto L28
            r4 = -185731758(0xfffffffff4edf552, float:-1.5082398E32)
            if (r3 == r4) goto L1e
            goto L31
        L1e:
            java.lang.String r3 = "VOICE_APK_START"
            boolean r1 = r1.equals(r3)     // Catch: java.lang.Exception -> L58
            if (r1 == 0) goto L31
            r2 = r7
            goto L31
        L28:
            java.lang.String r3 = "VOICE_APK_CRASH"
            boolean r1 = r1.equals(r3)     // Catch: java.lang.Exception -> L58
            if (r1 == 0) goto L31
            r2 = r5
        L31:
            if (r2 == 0) goto L41
            if (r2 == r5) goto L36
            goto L0
        L36:
            java.lang.String r1 = com.amazon.alexa.handsfree.voiceappreporter.services.VoiceAppEventReporterService.TAG     // Catch: java.lang.Exception -> L58
            java.lang.String r2 = "Reporting a crash"
            com.amazon.alexa.handsfree.protocols.utils.Log.d(r1, r2)     // Catch: java.lang.Exception -> L58
            r6.reportAppCrash(r0)     // Catch: java.lang.Exception -> L58
            goto L0
        L41:
            java.lang.String r0 = com.amazon.alexa.handsfree.voiceappreporter.services.VoiceAppEventReporterService.TAG     // Catch: java.lang.Exception -> L58
            java.lang.String r1 = "Reporting an app start event"
            com.amazon.alexa.handsfree.protocols.utils.Log.d(r0, r1)     // Catch: java.lang.Exception -> L58
            r6.reportAppStart()     // Catch: java.lang.Exception -> L58
            goto L0
        L4c:
            com.amazon.alexa.handsfree.voiceappreporter.database.VoiceAppEventReporterDatabaseHelper r0 = r6.mVoiceAppEventReporterDatabaseHelper     // Catch: java.lang.Exception -> L58
            boolean r0 = r0.getHasCursorErrorOccurred()     // Catch: java.lang.Exception -> L58
            if (r0 == 0) goto L7e
            r6.reportCursorError()     // Catch: java.lang.Exception -> L58
            goto L7e
        L58:
            r0 = move-exception
            java.lang.String r1 = com.amazon.alexa.handsfree.voiceappreporter.services.VoiceAppEventReporterService.TAG
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Exception reporting the voice event: "
            r2.append(r3)
            r2.append(r0)
            java.lang.String r2 = r2.toString()
            com.amazon.alexa.handsfree.protocols.utils.Log.e(r1, r2)
            com.amazon.alexa.handsfree.protocols.utils.CrashReportRecorder r1 = r6.mCrashReportRecorder
            java.lang.StackTraceElement[] r2 = r0.getStackTrace()
            r7 = r2[r7]
            java.lang.String r7 = r7.getMethodName()
            r1.reportNonFatalCrash(r6, r7, r0)
        L7e:
            com.amazon.alexa.handsfree.voiceappreporter.schedulers.VoiceAppEventReporterServiceScheduler r7 = r6.mVoiceAppEventReporterServiceScheduler
            r7.scheduleNextCheck()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.handsfree.voiceappreporter.services.VoiceAppEventReporterService.onHandleWork(android.content.Intent):void");
    }
}
