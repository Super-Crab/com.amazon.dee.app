package com.amazon.whisperjoin.deviceprovisioningservice.metrics;

import com.amazon.whisperjoin.metrics.MetricsRecorder;
import com.amazon.whisperjoin.metrics.MetricsRecorderProvider;
import com.amazon.whisperjoin.metrics.WhisperJoinMetricSourceName;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes13.dex */
public class AutoDiscoveryMetricsRecorder {
    private final MetricsRecorder mCurrentRecorder;
    private AtomicInteger mControlledSetupReportSize = new AtomicInteger();
    private AtomicInteger mDevicesIneligibleForAutomatedSetupReportSize = new AtomicInteger();
    private AtomicInteger mRecentlySetupDevicesReportSize = new AtomicInteger();

    /* loaded from: classes13.dex */
    private static class AutoDiscoveryMetric {
        private static final String CLIENT_SETUP_STATUS_REQUEST_COUNT = "FFSAutoDiscoveryClientSetupStatusRequestCount";
        private static final String CONTROLLED_SETUP_DEVICE_COUNT = "FFSAutoDiscoveryControlledSetupDeviceCount";
        private static final String FAILURE_REPORT_COUNT = "FFSAutoDiscoveryFailureReportCount";
        private static final String INELIGIBLE_FOR_AUTOMATED_SETUP_DEVICE_COUNT = "FFSAutoDiscoveryIneligibleForAutomatedSetupDeviceCount";
        private static final String PREFIX = "FFSAutoDiscovery";
        private static final String RECENTLY_SETUP_DEVICE_COUNT = "FFSAutoDiscoveryRecentlySetupDeviceCount";
        private static final String SCAN_TOTAL_MILLIS = "FFSAutoDiscoveryUptimeTotalMillis";
        private static final String START_COUNT = "FFSAutoDiscoveryStartCount";

        private AutoDiscoveryMetric() {
        }
    }

    public AutoDiscoveryMetricsRecorder(MetricsRecorderProvider metricsRecorderProvider) {
        this.mCurrentRecorder = metricsRecorderProvider.getMetricsRecorder(WhisperJoinMetricSourceName.FFS_PROVISIONING_SERVICE);
    }

    private void ratchetValueUp(AtomicInteger atomicInteger, int i) {
        boolean z;
        do {
            int i2 = atomicInteger.get();
            z = i2 >= i;
            if (!z) {
                z = atomicInteger.compareAndSet(i2, i);
                continue;
            }
        } while (!z);
    }

    public void onAutoDiscoveryStart() {
        this.mCurrentRecorder.incrementCounter("FFSAutoDiscoveryStartCount");
        this.mCurrentRecorder.startTimer("FFSAutoDiscoveryUptimeTotalMillis");
    }

    public void onAutoDiscoveryStop() {
        this.mCurrentRecorder.stopTimer("FFSAutoDiscoveryUptimeTotalMillis");
    }

    public void onControlledSetupReport(int i) {
        ratchetValueUp(this.mControlledSetupReportSize, i);
    }

    public void onDestroy() {
        this.mCurrentRecorder.recordCounter("FFSAutoDiscoveryControlledSetupDeviceCount", this.mControlledSetupReportSize.get());
        this.mCurrentRecorder.recordCounter("FFSAutoDiscoveryIneligibleForAutomatedSetupDeviceCount", this.mDevicesIneligibleForAutomatedSetupReportSize.get());
        this.mCurrentRecorder.recordCounter("FFSAutoDiscoveryRecentlySetupDeviceCount", this.mRecentlySetupDevicesReportSize.get());
        this.mCurrentRecorder.close();
    }

    public void onDevicesIneligibleForAutomatedSetupReport(int i) {
        ratchetValueUp(this.mDevicesIneligibleForAutomatedSetupReportSize, i);
    }

    public void onFailureReport() {
        this.mCurrentRecorder.incrementCounter("FFSAutoDiscoveryFailureReportCount");
    }

    public void onGetCustomerProvisioneesSetupStatus() {
        this.mCurrentRecorder.incrementCounter("FFSAutoDiscoveryClientSetupStatusRequestCount");
    }

    public void onRecentlySetupDevicesReport(int i) {
        ratchetValueUp(this.mRecentlySetupDevicesReportSize, i);
    }
}
