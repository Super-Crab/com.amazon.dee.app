package com.amazon.whisperjoin.deviceprovisioningservice.metrics;

import android.os.SystemClock;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.amazon.whisperjoin.metrics.MetricsRecorder;
import com.amazon.whisperjoin.metrics.MetricsRecorderProvider;
import com.amazon.whisperjoin.metrics.WhisperJoinMetricSourceName;
/* loaded from: classes13.dex */
public class FFSProvisioningServiceMetricsRecorder {
    private MetricsRecorder mCurrentRecorder;
    private final MetricsRecorderProvider mMetricsRecorderProvider;
    private long mSetupWorkflowStartTime = -1;
    private long mWorkflowStartTime = -1;

    /* loaded from: classes13.dex */
    private static class FFSBattery {
        private static final String PERCENT_DROP_PER_HOUR_SERVICE_LIFETIME = "PercentDropPerHourServiceLifetime";
        private static final String PERCENT_DROP_PER_HOUR_USING_BATTERY = "PercentDropPerHourUsingBattery";
        private static final String PERCENT_DROP_PER_HOUR_USING_BATTERY_DOZE_ACTIVE = "PercentDropPerHourUsingBatteryDozeActive";
        private static final String PERCENT_DROP_PER_HOUR_USING_BATTERY_DOZE_INACTIVE = "PercentDropPerHourUsingBatteryDozeInactive";
        private static final String PREFIX = "FFSBattery";

        private FFSBattery() {
        }
    }

    /* loaded from: classes13.dex */
    private static class FFSConstraintMetric {
        private static final String API_REQUIRES_LOCATION_PERMISSION_FOR_SCAN = "FFSConstraintApiRequiresLocationPermissionForScan";
        private static final String BLUETOOTH_ENABLED_AT_START_COUNT = "FFSConstraintBluetoothEnabledAtStartCount";
        private static final String PERMISSION_APP_LOCATION_ENABLED_COUNT = "FFSConstraintAppLocationEnabledCount";
        private static final String PERMISSION_SYSTEM_LOCATION_ENABLED_COUNT = "FFSConstraintSystemLocationEnabledCount";
        private static final String POWER_ABOVE_THRESHOLD_AT_START_COUNT = "FFSConstraintPowerAboveThresholdAtStartCount";
        private static final String POWER_ABOVE_TRESHOLD_WHILE_RUNNING_COUNT = "FFSConstraintPowerAboveThresholdWhileRunningCount";
        private static final String POWER_BELOW_TRESHOLD_WHILE_RUNNING_COUNT = "FFSConstraintPowerBelowThresholdWhileRunningCount";
        private static final String PREFIX = "FFSConstraint";
        private static final String WHITE_LISTED_COUNT = "FFSConstraintWhiteListedCount";

        private FFSConstraintMetric() {
        }
    }

    /* loaded from: classes13.dex */
    private static class FFSServiceMetric {
        private static final String DOZE_ENTER_COUNT = "FFSServiceDozeEnterCount";
        private static final String DOZE_EXIT_COUNT = "FFSServiceDozeExitCount";
        private static final String PREFIX = "FFSService";
        private static final String START_COUNT = "FFSServiceStartCount";
        private static final String UPTIME_POWER_BATTERY_DOZE_ACTIVE_MILLIS = "FFSServiceUptimePowerBatteryDozeActiveMillis";
        private static final String UPTIME_POWER_BATTERY_DOZE_INACTIVE_MILLIS = "FFSServiceUptimePowerBatteryDozeInactiveMillis";
        private static final String UPTIME_POWER_BATTERY_MILLIS = "FFSServiceUptimePowerBatteryMillis";
        private static final String UPTIME_POWER_CHARGER_MILLIS = "FFSServiceUptimePowerChargerMillis";
        private static final String UPTIME_TOTAL_MILLIS = "FFSServiceUptimeTotalMillis";

        private FFSServiceMetric() {
        }
    }

    /* loaded from: classes13.dex */
    private static class FFSWorkflowMetric {
        private static final String BACKOFF_COUNT = "FFSWorkflowBackOffCount";
        private static final String BACKOFF_COUNT_CAUSE_PREFIX = "FFSWorkflowBackOffCount_";
        private static final String BACKOFF_DURATION_MILLIS = "FFSWorkflowBackOffDurationMillis";
        private static final String ERROR_COUNT = "FFSWorkflowErrorCount";
        private static final String PREFIX = "FFSWorkflow";
        private static final String SETUP_ATTEMPT_COUNT = "FFSWorkflowSetupAttemptCount";
        private static final String SETUP_FAILURE_COUNT = "FFSWorkflowSetupFailureCount";
        private static final String SETUP_FAILURE_DURATION_MILLIS = "FFSWorkflowSetupFailureDurationMillis";
        private static final String SETUP_SUCCESS_COUNT = "FFSWorkflowSetupSuccessCount";
        private static final String SETUP_SUCCESS_DURATION_MILLIS = "FFSWorkflowSetupSuccessDurationMillis";
        private static final String START_COUNT = "FFSWorkflowStartCount";
        private static final String UPTIME_TOTAL_MILLIS = "FFSWorkflowUptimeTotalMillis";

        private FFSWorkflowMetric() {
        }
    }

    public FFSProvisioningServiceMetricsRecorder(MetricsRecorderProvider metricsRecorderProvider) {
        this.mMetricsRecorderProvider = metricsRecorderProvider;
    }

    public void onApiDoesntRequireLocationPermissionForScan() {
        this.mCurrentRecorder.recordCounter("FFSConstraintApiRequiresLocationPermissionForScan", FrostVideoEffectController.VIDEO_STRENGTH_CLEAR);
    }

    public void onApiRequireLocationPermissionForScan(boolean z, boolean z2) {
        double d = 1.0d;
        this.mCurrentRecorder.recordCounter("FFSConstraintApiRequiresLocationPermissionForScan", 1.0d);
        this.mCurrentRecorder.recordCounter("FFSConstraintAppLocationEnabledCount", z ? 1.0d : 0.0d);
        MetricsRecorder metricsRecorder = this.mCurrentRecorder;
        if (!z2) {
            d = 0.0d;
        }
        metricsRecorder.recordCounter("FFSConstraintSystemLocationEnabledCount", d);
    }

    public void onServiceStart() {
        this.mCurrentRecorder = this.mMetricsRecorderProvider.getMetricsRecorder(WhisperJoinMetricSourceName.FFS_PROVISIONING_SERVICE);
        this.mCurrentRecorder.incrementCounter("FFSServiceStartCount");
        this.mCurrentRecorder.startTimer("FFSServiceUptimeTotalMillis");
    }

    public void onServiceStop() {
        this.mCurrentRecorder.stopTimer("FFSServiceUptimeTotalMillis");
        this.mCurrentRecorder.close();
    }

    public void onWorkflowBackoff(String str) {
        this.mCurrentRecorder.incrementCounter("FFSWorkflowBackOffCount");
        MetricsRecorder metricsRecorder = this.mCurrentRecorder;
        metricsRecorder.incrementCounter("FFSWorkflowBackOffCount_" + str);
        if (this.mWorkflowStartTime != -1) {
            this.mCurrentRecorder.addTimer("FFSWorkflowBackOffDurationMillis", SystemClock.elapsedRealtime() - this.mWorkflowStartTime);
        }
    }

    public void onWorkflowError() {
        this.mCurrentRecorder.incrementCounter("FFSWorkflowErrorCount");
    }

    public void onWorkflowSetupAttemptStart() {
        this.mCurrentRecorder.incrementCounter("FFSWorkflowSetupAttemptCount");
        this.mSetupWorkflowStartTime = SystemClock.elapsedRealtime();
    }

    public void onWorkflowSetupFailure() {
        this.mCurrentRecorder.incrementCounter("FFSWorkflowSetupFailureCount");
        if (this.mSetupWorkflowStartTime != -1) {
            this.mCurrentRecorder.addTimer("FFSWorkflowSetupFailureDurationMillis", SystemClock.elapsedRealtime() - this.mSetupWorkflowStartTime);
        }
    }

    public void onWorkflowSetupSuccess() {
        this.mCurrentRecorder.incrementCounter("FFSWorkflowSetupSuccessCount");
        if (this.mSetupWorkflowStartTime != -1) {
            this.mCurrentRecorder.addTimer("FFSWorkflowSetupSuccessDurationMillis", SystemClock.elapsedRealtime() - this.mSetupWorkflowStartTime);
        }
    }

    public void onWorkflowStart() {
        this.mCurrentRecorder.incrementCounter("FFSWorkflowStartCount");
        this.mCurrentRecorder.startTimer("FFSWorkflowUptimeTotalMillis");
        this.mWorkflowStartTime = SystemClock.elapsedRealtime();
    }

    public void onWorkflowStop() {
        this.mCurrentRecorder.stopTimer("FFSWorkflowUptimeTotalMillis");
    }
}
