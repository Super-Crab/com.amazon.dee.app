package com.amazon.alexa.biloba.metrics;

import android.text.TextUtils;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.biloba.metrics.MetricsConstants;
import com.amazon.alexa.biloba.utils.LogUtils;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.mobilytics.event.operational.MobilyticsMetricsTimer;
import com.amazon.alexa.mobilytics.event.userinteraction.InteractionType;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.android.tools.r8.GeneratedOutlineSupport1;
import dagger.Lazy;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes6.dex */
public class BilobaMetricsService {
    private static final String TAG = "BilobaMetricsService";
    private final Lazy<EnvironmentService> environmentService;
    private final Mobilytics mobilytics;
    private String roleSuffix;
    private String callerContext = "AlexaBiloba";
    @VisibleForTesting
    final Map<String, MobilyticsMetricsTimer> timersTTCF = new HashMap();

    public BilobaMetricsService(Mobilytics mobilytics, Lazy<EnvironmentService> lazy) {
        this.mobilytics = mobilytics;
        this.environmentService = lazy;
    }

    private void emitTTCFLogs(MobilyticsMetricsTimer mobilyticsMetricsTimer) {
        if (mobilyticsMetricsTimer != null) {
            String str = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("TTCF did record biloba-init ");
            outline107.append(mobilyticsMetricsTimer.getEventName());
            outline107.append(" ");
            outline107.append(mobilyticsMetricsTimer.getElapsedTime());
            outline107.append("ms");
            LogUtils.i(str, outline107.toString());
        }
    }

    private void recordInteractionEvents(String str, String str2, String str3) {
        recordUserInteraction(GeneratedOutlineSupport1.outline72(str, str2), str3);
        if (TextUtils.isEmpty(this.roleSuffix)) {
            LogUtils.w(TAG, String.format("No user role for user interaction %1$s %2$s", str3, str));
            return;
        }
        StringBuilder outline113 = GeneratedOutlineSupport1.outline113(str, str2);
        outline113.append(this.roleSuffix);
        recordUserInteraction(outline113.toString(), str3);
    }

    private void recordUserInteraction(String str, String str2) {
        LogUtils.i(TAG, String.format("Recording user interaction %1$s %2$s", str2, str));
        this.mobilytics.recordUserInteractionEvent(this.mobilytics.createUserInteractionEvent(str, str2, "AlexaBiloba", this.callerContext));
        recordCounter(str);
    }

    public void finishTimer(MobilyticsMetricsTimer mobilyticsMetricsTimer) {
        if (mobilyticsMetricsTimer != null) {
            LogUtils.i(TAG, String.format("Finish timer %1$s", mobilyticsMetricsTimer.getEventName()));
            mobilyticsMetricsTimer.finishTimer();
            if (this.environmentService.mo358get().isDebugBuild()) {
                mobilyticsMetricsTimer.setEventName(mobilyticsMetricsTimer.getEventName() + MetricsConstants.DEBUG);
                LogUtils.d(TAG, String.format("Debug build, recording timer as %1$s", mobilyticsMetricsTimer.getEventName()));
            }
            LogUtils.i(TAG, String.format("Record timer %1$s", mobilyticsMetricsTimer.getEventName()));
            this.mobilytics.recordOperationalEvent(mobilyticsMetricsTimer);
        }
    }

    public void initializeMetricsContext(String str) {
        if (TextUtils.isEmpty(str)) {
            this.callerContext = "AlexaBiloba";
        } else {
            this.callerContext = str;
        }
    }

    public void recordCounter(String str) {
        String str2 = TAG;
        LogUtils.i(str2, "Recording Counter event " + str);
        this.mobilytics.recordCounter(this.mobilytics.createCounter(str, "AlexaBiloba", this.callerContext));
        this.mobilytics.recordCounter(this.mobilytics.createCounter(str, String.format("%s_%s", "AlexaBiloba", this.callerContext), this.callerContext));
    }

    public void recordOccurrence(String str, boolean z) {
        LogUtils.i(TAG, String.format("Recording occurrence %1$s %2$b", str, Boolean.valueOf(z)));
        this.mobilytics.recordOccurrence(str, z, "AlexaBiloba", this.callerContext);
    }

    public void recordUserClick(String str, String str2) {
        recordInteractionEvents(str, str2, "click");
    }

    public void recordUserCommsCall(String str, String str2) {
        recordInteractionEvents(str, str2, InteractionType.COMMS_CALL);
    }

    public void recordUserCommsMessage(String str, String str2) {
        recordInteractionEvents(str, str2, InteractionType.COMMS_MESSAGE);
    }

    public void recordUserView(String str, String str2) {
        recordInteractionEvents(str, str2, "view");
    }

    public void resetTTCFTimers() {
        this.timersTTCF.clear();
    }

    public void startRecordingTTCF(long j) {
        this.timersTTCF.put(MetricsConstants.TTCFMetrics.BILOBA_DASHBOARD, startTimer(MetricsConstants.TTCFMetrics.BILOBA_DASHBOARD));
        this.timersTTCF.put(MetricsConstants.TTCFMetrics.BILOBA_GETTING_STARTED, startTimer(MetricsConstants.TTCFMetrics.BILOBA_GETTING_STARTED));
        this.timersTTCF.put(MetricsConstants.TTCFMetrics.TODAY_ACTIVITY_RENDER, startTimer(MetricsConstants.TTCFMetrics.TODAY_ACTIVITY_RENDER));
        this.timersTTCF.put(MetricsConstants.TTCFMetrics.DASHBOARD_CARDS_RENDER, startTimer(MetricsConstants.TTCFMetrics.DASHBOARD_CARDS_RENDER));
        this.timersTTCF.put(MetricsConstants.TTCFMetrics.BILOBA_LONE_CR, startTimer(MetricsConstants.TTCFMetrics.BILOBA_LONE_CR));
        for (MobilyticsMetricsTimer mobilyticsMetricsTimer : this.timersTTCF.values()) {
            mobilyticsMetricsTimer.setEventTimestamp(j);
        }
    }

    public MobilyticsMetricsTimer startTimer(String str) {
        LogUtils.i(TAG, String.format("Start timer %1$s", str));
        return this.mobilytics.createTimer(str, "AlexaBiloba", this.callerContext);
    }

    public void stopRecordingTTCF(String str) {
        if (this.timersTTCF.containsKey(str)) {
            MobilyticsMetricsTimer mobilyticsMetricsTimer = this.timersTTCF.get(str);
            finishTimer(mobilyticsMetricsTimer);
            emitTTCFLogs(mobilyticsMetricsTimer);
            this.timersTTCF.remove(str);
            if (!str.equals(MetricsConstants.TTCFMetrics.BILOBA_GETTING_STARTED) && !str.equals(MetricsConstants.TTCFMetrics.BILOBA_LONE_CR)) {
                if (!str.equals(MetricsConstants.TTCFMetrics.BILOBA_DASHBOARD)) {
                    return;
                }
                this.timersTTCF.remove(MetricsConstants.TTCFMetrics.BILOBA_GETTING_STARTED);
                this.timersTTCF.remove(MetricsConstants.TTCFMetrics.BILOBA_LONE_CR);
                return;
            }
            resetTTCFTimers();
        }
    }

    public void updateRoleSuffix(@MetricsConstants.UserRoleSuffix String str) {
        this.roleSuffix = str;
        LogUtils.d(TAG, String.format("Updated roleSuffix to %s", str));
    }
}
