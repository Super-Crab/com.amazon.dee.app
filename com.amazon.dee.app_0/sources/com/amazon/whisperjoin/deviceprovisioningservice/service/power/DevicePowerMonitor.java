package com.amazon.whisperjoin.deviceprovisioningservice.service.power;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import com.amazon.whisperjoin.common.sharedtypes.utility.WJLog;
import com.amazon.whisperjoin.deviceprovisioningservice.service.ProvisionerClientData;
import com.amazon.whisperjoin.metrics.MetricsRecorder;
import com.amazon.whisperjoin.metrics.MetricsRecorderProvider;
import com.amazon.whisperjoin.metrics.WhisperJoinMetricSourceName;
import java.lang.ref.WeakReference;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
/* loaded from: classes13.dex */
public class DevicePowerMonitor {
    private static final String ACTIVE_WORKFLOW_BATTERY_PERCENTAGE_DELTA_METRIC_PREFIX = "ActiveWorkflow_BatteryPercentageDeltaWithIntervalMinutes_";
    private static final String INACTIVE_WORKFLOW_BATTERY_PERCENTAGE_DELTA_METRIC_PREFIX = "InactiveWorkflow_BatteryPercentageDeltaWithIntervalMinutes_";
    private static final String MONITORING_DEVICE_MODEL = "MonitoringDeviceModel";
    private static final String MONITORING_OS_MAJOR_VERSION = "MonitoringOSMajorVersion";
    private static final int REFRESH_TIME_MINUTES = 30;
    private static final String TAG = "DevicePowerMonitor";
    public final long DEFAULT_TIME_BETWEEN_REFRESH_MS;
    private final Context mContext;
    private final DevicePowerStatusProvider mDevicePowerStatusProvider;
    private DevicePowerUpdateListener mDevicePowerUpdateListener;
    private final Handler mHandler;
    private final IntentFilter mIntentFilter;
    private boolean mIsMonitoring;
    private DevicePowerStatus mLastKnownPowerStatus;
    private final MetricsRecorderProvider mMetricsRecorderProvider;
    private PowerSourceChangeBroadcastReceiver mPowerSourceChangeBroadcastReceiver;
    private final ProvisionerClientData mProvisionerClientData;
    private RefreshPowerStatusRunnable mRefreshPowerStatusRunnable;
    private long mTimeBetweenRefreshMs;
    private boolean mWorkflowActivationState;

    /* loaded from: classes13.dex */
    public interface DevicePowerUpdateListener {
        void onUpdate(DevicePowerStatus devicePowerStatus);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes13.dex */
    public static class PowerSourceChangeBroadcastReceiver extends BroadcastReceiver {
        private final WeakReference<DevicePowerMonitor> mDevicePowerMonitor;

        public PowerSourceChangeBroadcastReceiver(DevicePowerMonitor devicePowerMonitor) {
            this.mDevicePowerMonitor = new WeakReference<>(devicePowerMonitor);
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (this.mDevicePowerMonitor.get() == null) {
                return;
            }
            if (!this.mDevicePowerMonitor.get().mIntentFilter.matchAction(intent.getAction())) {
                WJLog.d(DevicePowerMonitor.TAG, "Intent doesn't match desired power intent filter");
                return;
            }
            synchronized (this.mDevicePowerMonitor.get()) {
                this.mDevicePowerMonitor.get().mLastKnownPowerStatus = this.mDevicePowerMonitor.get().mDevicePowerStatusProvider.getDevicePowerStatus();
                this.mDevicePowerMonitor.get().sendUpdateToListenerIfExists(this.mDevicePowerMonitor.get().mLastKnownPowerStatus);
                this.mDevicePowerMonitor.get().resetRefreshPowerStatusRunnable();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes13.dex */
    public static class RefreshPowerStatusRunnable implements Runnable {
        private final WeakReference<DevicePowerMonitor> mDevicePowerMonitor;

        public RefreshPowerStatusRunnable(DevicePowerMonitor devicePowerMonitor) {
            this.mDevicePowerMonitor = new WeakReference<>(devicePowerMonitor);
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.mDevicePowerMonitor.get() == null) {
                return;
            }
            synchronized (this.mDevicePowerMonitor.get()) {
                DevicePowerStatus devicePowerStatus = this.mDevicePowerMonitor.get().mDevicePowerStatusProvider.getDevicePowerStatus();
                this.mDevicePowerMonitor.get().sendUpdateToListenerIfExists(devicePowerStatus);
                this.mDevicePowerMonitor.get().recordBatteryMetricsSinceLastRefresh(this.mDevicePowerMonitor.get().mLastKnownPowerStatus, devicePowerStatus);
                this.mDevicePowerMonitor.get().mLastKnownPowerStatus = devicePowerStatus;
                String str = DevicePowerMonitor.TAG;
                WJLog.d(str, "Power Status Update: " + this.mDevicePowerMonitor.get().mLastKnownPowerStatus);
                String str2 = DevicePowerMonitor.TAG;
                WJLog.d(str2, "Scheduling refresh after " + this.mDevicePowerMonitor.get().mTimeBetweenRefreshMs + "ms");
                if (this.mDevicePowerMonitor.get().mRefreshPowerStatusRunnable != null) {
                    this.mDevicePowerMonitor.get().mHandler.postDelayed(this.mDevicePowerMonitor.get().mRefreshPowerStatusRunnable, this.mDevicePowerMonitor.get().mTimeBetweenRefreshMs);
                }
            }
        }
    }

    public DevicePowerMonitor(Context context, DevicePowerStatusProvider devicePowerStatusProvider, MetricsRecorderProvider metricsRecorderProvider, ProvisionerClientData provisionerClientData) {
        this(context, devicePowerStatusProvider, new Handler(context.getMainLooper()), metricsRecorderProvider, provisionerClientData);
    }

    private static IntentFilter getPowerSourceChangeIntentFilter() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.ACTION_POWER_CONNECTED");
        intentFilter.addAction("android.intent.action.ACTION_POWER_DISCONNECTED");
        return intentFilter;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void recordBatteryMetricsSinceLastRefresh(DevicePowerStatus devicePowerStatus, DevicePowerStatus devicePowerStatus2) {
        if (!devicePowerStatus.isCharging() && !devicePowerStatus2.isCharging()) {
            MetricsRecorder metricsRecorder = this.mMetricsRecorderProvider.getMetricsRecorder(WhisperJoinMetricSourceName.DEVICE_POWER_MONITOR);
            double batteryPercentage = devicePowerStatus2.getBatteryPercentage() - devicePowerStatus.getBatteryPercentage();
            StringBuilder sb = new StringBuilder();
            sb.append(this.mWorkflowActivationState ? ACTIVE_WORKFLOW_BATTERY_PERCENTAGE_DELTA_METRIC_PREFIX : INACTIVE_WORKFLOW_BATTERY_PERCENTAGE_DELTA_METRIC_PREFIX);
            sb.append(30);
            metricsRecorder.recordCounter(sb.toString(), batteryPercentage);
            metricsRecorder.recordString(MONITORING_DEVICE_MODEL, this.mProvisionerClientData.getDeviceModel());
            metricsRecorder.recordString(MONITORING_OS_MAJOR_VERSION, this.mProvisionerClientData.getDeviceFirmwareVersion());
            WJLog.d(TAG, String.format(Locale.ENGLISH, "Recording Battery Consumption Metrics. Percentage Delta: %f workflowActive: %b", Double.valueOf(batteryPercentage), Boolean.valueOf(this.mWorkflowActivationState)));
            metricsRecorder.close();
            return;
        }
        WJLog.d(TAG, "Not recording battery metrics since charging");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resetRefreshPowerStatusRunnable() {
        WJLog.d(TAG, "reset DevicePowerStatus refresh runnable");
        this.mHandler.removeCallbacks(this.mRefreshPowerStatusRunnable);
        this.mHandler.postDelayed(this.mRefreshPowerStatusRunnable, this.mTimeBetweenRefreshMs);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendUpdateToListenerIfExists(DevicePowerStatus devicePowerStatus) {
        DevicePowerUpdateListener devicePowerUpdateListener = this.mDevicePowerUpdateListener;
        if (devicePowerUpdateListener != null) {
            devicePowerUpdateListener.onUpdate(devicePowerStatus);
        }
    }

    public DevicePowerStatus getCurrentDevicePowerStatus() {
        return this.mDevicePowerStatusProvider.getDevicePowerStatus();
    }

    public synchronized void onWorkflowActivationStateChange(boolean z) {
        String str = TAG;
        WJLog.d(str, "onWorkflowActivationStateChange: " + z);
        if (!this.mIsMonitoring) {
            WJLog.d(TAG, "Not currently monitoring. Skipping state change.");
            return;
        }
        this.mWorkflowActivationState = z;
        this.mLastKnownPowerStatus = this.mDevicePowerStatusProvider.getDevicePowerStatus();
        sendUpdateToListenerIfExists(this.mLastKnownPowerStatus);
        resetRefreshPowerStatusRunnable();
    }

    public synchronized DevicePowerStatus startMonitoring(DevicePowerUpdateListener devicePowerUpdateListener) {
        return startMonitoring(devicePowerUpdateListener, this.DEFAULT_TIME_BETWEEN_REFRESH_MS);
    }

    public synchronized void stopMonitoring() {
        WJLog.d(TAG, "stopMonitoring");
        this.mHandler.removeCallbacks(this.mRefreshPowerStatusRunnable);
        try {
            this.mContext.unregisterReceiver(this.mPowerSourceChangeBroadcastReceiver);
        } catch (IllegalArgumentException unused) {
            WJLog.d(TAG, "mPowerSourceChangeBroadcastReceiver is not registered");
        }
        this.mRefreshPowerStatusRunnable = null;
        this.mPowerSourceChangeBroadcastReceiver = null;
        this.mDevicePowerUpdateListener = null;
        this.mTimeBetweenRefreshMs = -1L;
        this.mIsMonitoring = false;
    }

    DevicePowerMonitor(Context context, DevicePowerStatusProvider devicePowerStatusProvider, Handler handler, MetricsRecorderProvider metricsRecorderProvider, ProvisionerClientData provisionerClientData) {
        this.DEFAULT_TIME_BETWEEN_REFRESH_MS = TimeUnit.MINUTES.toMillis(30L);
        this.mWorkflowActivationState = false;
        this.mTimeBetweenRefreshMs = -1L;
        this.mIsMonitoring = false;
        this.mContext = context;
        this.mHandler = handler;
        this.mDevicePowerStatusProvider = devicePowerStatusProvider;
        this.mMetricsRecorderProvider = metricsRecorderProvider;
        this.mProvisionerClientData = provisionerClientData;
        this.mIntentFilter = getPowerSourceChangeIntentFilter();
    }

    public synchronized DevicePowerStatus startMonitoring(DevicePowerUpdateListener devicePowerUpdateListener, long j) {
        DevicePowerStatus devicePowerStatus;
        WJLog.d(TAG, "startMonitoring");
        if (devicePowerUpdateListener == null) {
            throw new IllegalArgumentException("devicePowerUpdateListener can not be null");
        }
        if (j > 0) {
            if (this.mDevicePowerUpdateListener != null) {
                WJLog.d(TAG, "Stopping monitoring before restarting");
                stopMonitoring();
            }
            this.mIsMonitoring = true;
            this.mDevicePowerUpdateListener = devicePowerUpdateListener;
            this.mTimeBetweenRefreshMs = j;
            this.mPowerSourceChangeBroadcastReceiver = new PowerSourceChangeBroadcastReceiver(this);
            this.mRefreshPowerStatusRunnable = new RefreshPowerStatusRunnable(this);
            this.mHandler.postDelayed(this.mRefreshPowerStatusRunnable, j);
            this.mContext.registerReceiver(this.mPowerSourceChangeBroadcastReceiver, getPowerSourceChangeIntentFilter());
            devicePowerStatus = this.mDevicePowerStatusProvider.getDevicePowerStatus();
            this.mLastKnownPowerStatus = devicePowerStatus;
            String str = TAG;
            WJLog.d(str, "Returning initial power status: " + devicePowerStatus);
        } else {
            throw new IllegalArgumentException("timeBetweenRefreshMs should be greater than 0");
        }
        return devicePowerStatus;
    }
}
