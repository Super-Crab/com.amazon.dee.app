package com.amazon.whisperjoin.provisioning.metrics.client;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Build;
import com.amazon.client.metrics.common.MetricsFactory;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.amazon.whisperjoin.provisioning.Radios;
import com.amazon.whisperjoin.provisioning.metrics.client.helpers.CredentialLockerMetrics;
import com.amazon.whisperjoin.provisioning.metrics.client.helpers.CustomMetrics;
import com.amazon.whisperjoin.provisioning.utils.SystemTime;
import java.util.UUID;
import org.apache.commons.lang.exception.ExceptionUtils;
/* loaded from: classes13.dex */
public abstract class SetupAttemptMetrics {
    static final String ABORTED_STEP_METRIC = "AbortedStep";
    static final String APP_CLIENT_NAME_METRIC = "AppClient";
    static final String APP_CLIENT_VERSION_METRIC = "AppVersion";
    static final String BLE_ENABLED_ON_START_METRIC = "BleEnabledOnAttemptStart";
    static final String CENTRAL_DEVICE_MANUFACTURER_METRIC = "CentralDeviceManufacturer";
    static final String CENTRAL_DEVICE_PLATFORM_ANDROID = "Android";
    static final String CENTRAL_DEVICE_PLATFORM_METRIC = "CentralDevicePlatform";
    static final String CENTRAL_DEVICE_PRODUCT_METRIC = "CentralDeviceProduct";
    static final String CENTRAL_DEVICE_SUPPORTS_BLE_METRIC = "CentralDeviceSupportsBle";
    static final String CENTRAL_DEVICE_VERSION_METRIC = "CentralDeviceVersion";
    static final String FAILURE_ROOT_CAUSE_METRIC = "FailureCause";
    static final String FAILURE_STACKTRACE_METRIC = "FailureStackTrace";
    static final String FAILURE_STEP_METRIC = "FailureStep";
    static final String SETUP_ID = "SetupId";
    static final String SUCCESS_METRIC = "Success";
    static final String TOTAL_PROVISIONING_ATTEMPT_TIME_METRIC = "TotalProvisioningTime";
    static final String WIFI_ENABLED_ON_START_METRIC = "WifiEnabledOnAttemptStart";
    public CustomMetrics customMetrics;
    public final CredentialLockerMetrics lockerMetrics;
    protected Radios mActiveRadio;
    protected MetricHelper mMetricHelper;
    private final long mStartTime;

    /* loaded from: classes13.dex */
    public static abstract class Builder<T extends SetupAttemptMetrics> {
        protected Radios mActiveRadio;
        protected String mAppVersion;
        protected Context mContext;
        protected String mDirectedId;
        protected MetricsFactory mMetricsFactory;
        protected String mSourceName;

        public abstract T build();

        /* JADX INFO: Access modifiers changed from: protected */
        /* renamed from: setActiveRadio */
        public Builder mo6644setActiveRadio(Radios radios) {
            this.mActiveRadio = radios;
            return this;
        }

        public Builder setAppVersion(String str) {
            this.mAppVersion = str;
            return this;
        }

        public Builder setContext(Context context) {
            this.mContext = context;
            return this;
        }

        public Builder setDirectedId(String str) {
            this.mDirectedId = str;
            return this;
        }

        public Builder setMetricsFactory(MetricsFactory metricsFactory) {
            this.mMetricsFactory = metricsFactory;
            return this;
        }

        public Builder setSourceName(String str) {
            this.mSourceName = str;
            return this;
        }

        public void validateConfiguration() {
            if (this.mMetricsFactory != null) {
                if (this.mContext != null) {
                    if (this.mSourceName != null) {
                        if (this.mAppVersion != null) {
                            if (this.mActiveRadio != null) {
                                if (this.mDirectedId == null) {
                                    throw new IllegalArgumentException("Directed Id can not be null");
                                }
                                return;
                            }
                            throw new IllegalArgumentException("Active Radio can not be null");
                        }
                        throw new IllegalArgumentException("App version can not be null");
                    }
                    throw new IllegalArgumentException("Program name can not be null");
                }
                throw new IllegalArgumentException("Context can not be null");
            }
            throw new IllegalArgumentException("Metrics factory can not be null");
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public SetupAttemptMetrics(Context context, MetricsFactory metricsFactory, String str, String str2, String str3, Radios radios) {
        this(context.getApplicationContext(), new MetricHelper(metricsFactory, str, str3), str2, radios);
    }

    private void emitSuccess(boolean z) {
        double d = 1.0d;
        this.mMetricHelper.recordCounter("Success", z ? 1.0d : 0.0d, new Object[0]);
        MetricHelper metricHelper = this.mMetricHelper;
        if (!z) {
            d = 0.0d;
        }
        metricHelper.recordCounter("Success", d, this.mActiveRadio);
    }

    private void initBleMetrics(Context context) {
        boolean z;
        double d = 1.0d;
        if (context.getPackageManager().hasSystemFeature("android.hardware.bluetooth_le")) {
            z = true;
            BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
            if (defaultAdapter != null) {
                this.mMetricHelper.recordCounter(BLE_ENABLED_ON_START_METRIC, defaultAdapter.isEnabled() ? 1.0d : 0.0d, new Object[0]);
            }
        } else {
            z = false;
        }
        MetricHelper metricHelper = this.mMetricHelper;
        if (!z) {
            d = 0.0d;
        }
        metricHelper.recordCounter(CENTRAL_DEVICE_SUPPORTS_BLE_METRIC, d, new Object[0]);
    }

    private void initWifiMetrics(Context context) {
        this.mMetricHelper.recordCounter(WIFI_ENABLED_ON_START_METRIC, ((ConnectivityManager) context.getSystemService("connectivity")).getNetworkInfo(1).isConnected() ? 1.0d : FrostVideoEffectController.VIDEO_STRENGTH_CLEAR, new Object[0]);
    }

    private void onTerminated() {
        long now = SystemTime.now();
        this.mMetricHelper.recordTimer(TOTAL_PROVISIONING_ATTEMPT_TIME_METRIC, now - this.mStartTime, new Object[0]);
        this.mMetricHelper.recordTimer(TOTAL_PROVISIONING_ATTEMPT_TIME_METRIC, now - this.mStartTime, this.mActiveRadio);
        this.mMetricHelper.close();
    }

    public void onTerminationAborted(String str) {
        this.mMetricHelper.recordString(ABORTED_STEP_METRIC, str, new Object[0]);
        this.mMetricHelper.recordCounter(ABORTED_STEP_METRIC, 1.0d, this.mActiveRadio, str);
        onTerminated();
    }

    public void onTerminationFailure(String str, Throwable th) {
        emitSuccess(false);
        this.mMetricHelper.recordString(FAILURE_STEP_METRIC, str, new Object[0]);
        this.mMetricHelper.recordCounter(FAILURE_STEP_METRIC, 1.0d, this.mActiveRadio, str);
        if (th != null) {
            if (th.getCause() != null) {
                this.mMetricHelper.recordString(FAILURE_ROOT_CAUSE_METRIC, th.getCause().getClass(), new Object[0]);
            } else {
                this.mMetricHelper.recordString(FAILURE_ROOT_CAUSE_METRIC, th.getClass(), new Object[0]);
            }
            this.mMetricHelper.recordString(FAILURE_STACKTRACE_METRIC, ExceptionUtils.getStackTrace(th), new Object[0]);
        }
        onTerminated();
    }

    public void onTerminationSuccess() {
        emitSuccess(true);
        onTerminated();
    }

    protected SetupAttemptMetrics(Context context, MetricHelper metricHelper, String str, Radios radios) {
        this.mStartTime = SystemTime.now();
        this.mMetricHelper = metricHelper;
        this.mActiveRadio = radios;
        this.mMetricHelper.recordString(SETUP_ID, UUID.randomUUID(), new Object[0]);
        this.mMetricHelper.recordString("AppVersion", str, new Object[0]);
        this.mMetricHelper.recordString(CENTRAL_DEVICE_PLATFORM_METRIC, "Android", new Object[0]);
        this.mMetricHelper.recordString(CENTRAL_DEVICE_MANUFACTURER_METRIC, Build.MANUFACTURER, new Object[0]);
        this.mMetricHelper.recordString(CENTRAL_DEVICE_PRODUCT_METRIC, Build.PRODUCT, new Object[0]);
        this.mMetricHelper.recordString(CENTRAL_DEVICE_VERSION_METRIC, Build.VERSION.RELEASE, new Object[0]);
        this.mMetricHelper.recordString(APP_CLIENT_NAME_METRIC, context, new Object[0]);
        this.customMetrics = new CustomMetrics(this.mMetricHelper);
        this.lockerMetrics = new CredentialLockerMetrics(this.mMetricHelper);
        initBleMetrics(context);
        initWifiMetrics(context);
    }
}
