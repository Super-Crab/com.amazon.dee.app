package com.amazon.alexa.accessory.metrics;

import android.annotation.SuppressLint;
import com.amazon.alexa.accessory.capabilities.firmware.AccessoryDfuMetricRecorder;
import com.amazon.alexa.accessory.capabilities.firmware.DfuCandidateInformation;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.metrics.FirmwareTaskMetricsReporter;
import com.amazon.alexa.accessory.metrics.MetricsConstants;
import com.amazon.alexa.accessory.metrics.Stopwatch;
import com.android.tools.r8.GeneratedOutlineSupport1;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
/* loaded from: classes.dex */
public class FirmwareTaskMetricsReporter implements MetricsReporter, AccessoryDfuMetricRecorder {
    private volatile int componentCacheInvalidationCount;
    private final DfuCandidateInformation dfuCandidateInformation;
    private volatile int getCachedComponentErrorCount;
    private volatile int getCachedComponentErrorCountDAVS;
    private final Object lock;
    private final MetricsReporter metricsReporter;
    private volatile boolean released;
    private final Stopwatch suspendedTimeStopwatch;
    private volatile int suspensionCount;
    private final int totalComponentCount;
    private final Stopwatch totalTimeStopwatch;
    private volatile int transmitComponentErrorCount;
    private final Set<String> transmittedComponents;
    private volatile int verifyArtifactSignatureErrorCount;

    /* loaded from: classes.dex */
    public static final class PackageMetricsData {
        private final int currentVersion;
        private final String name;
        private final String reference;
        private final int versionTo;

        public PackageMetricsData(String str, String str2, int i, int i2) {
            Preconditions.notNull(str, "reference");
            Preconditions.notNull(str2, "name");
            this.reference = str;
            this.name = str2;
            this.versionTo = i;
            this.currentVersion = i2;
        }

        int getCurrentVersion() {
            return this.currentVersion;
        }

        String getName() {
            return this.name;
        }

        String getReference() {
            return this.reference;
        }

        int getVersionTo() {
            return this.versionTo;
        }
    }

    public FirmwareTaskMetricsReporter(MetricsReporter metricsReporter, DfuCandidateInformation dfuCandidateInformation, int i, Stopwatch.CurrentTimeSupplier currentTimeSupplier) {
        Preconditions.notNull(metricsReporter, "metricsReporter");
        Preconditions.notNull(dfuCandidateInformation, "dfuCandidateInformation");
        Preconditions.notNull(currentTimeSupplier, "currentTimeSupplier");
        this.metricsReporter = metricsReporter;
        this.dfuCandidateInformation = dfuCandidateInformation;
        this.totalComponentCount = i;
        this.transmittedComponents = Collections.synchronizedSet(new HashSet());
        this.totalTimeStopwatch = new Stopwatch(currentTimeSupplier);
        this.suspendedTimeStopwatch = new Stopwatch(currentTimeSupplier);
        this.lock = new Object();
    }

    private void addReporterData(CustomMetricsEvent customMetricsEvent) {
        synchronized (this.lock) {
            customMetricsEvent.putTimer(MetricsConstants.Firmware.TOTAL_ELAPSED_TIME_MILLIS, this.totalTimeStopwatch.getElapsedTimeMillis());
            customMetricsEvent.putTimer(MetricsConstants.Firmware.TOTAL_SUSPENDED_TIME_MILLIS, this.suspendedTimeStopwatch.getElapsedTimeMillis());
            customMetricsEvent.putCounter(MetricsConstants.Firmware.TOTAL_COMPONENTS_COUNT, this.totalComponentCount);
            customMetricsEvent.putCounter(MetricsConstants.Firmware.SUSPENSION_COUNT, this.suspensionCount);
            customMetricsEvent.putCounter(MetricsConstants.Firmware.TRANSMITTED_COMPONENTS_COUNT, this.transmittedComponents.size());
            customMetricsEvent.putCounter(MetricsConstants.Firmware.COMPONENT_CACHE_INVALIDATED_COUNT, this.componentCacheInvalidationCount);
            customMetricsEvent.putCounter(MetricsConstants.Firmware.GET_CACHED_COMPONENT_ERROR_COUNT, this.getCachedComponentErrorCount);
            customMetricsEvent.putCounter(MetricsConstants.Firmware.TRANSMIT_COMPONENT_ERROR_COUNT, this.transmitComponentErrorCount);
        }
    }

    @SuppressLint({"CheckResult"})
    private Single<CustomMetricsEvent> createMetric(final String str) {
        return this.dfuCandidateInformation.getPackageMetricsData().map(new Function() { // from class: com.amazon.alexa.accessory.metrics.-$$Lambda$FirmwareTaskMetricsReporter$BLZ4uQM8GCnNlL27rkavH1Yo8t4
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return FirmwareTaskMetricsReporter.lambda$createMetric$2(str, (FirmwareTaskMetricsReporter.PackageMetricsData) obj);
            }
        }).onErrorReturnItem(new CustomMetricsEvent(str)).map(new Function() { // from class: com.amazon.alexa.accessory.metrics.-$$Lambda$FirmwareTaskMetricsReporter$2msm0GgUxhM-F4mO5vvvvzV1bOk
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return FirmwareTaskMetricsReporter.this.lambda$createMetric$3$FirmwareTaskMetricsReporter((CustomMetricsEvent) obj);
            }
        });
    }

    private Map<String, Object> getCustomDfuMetaData() {
        HashMap hashMap = new HashMap();
        try {
            hashMap.put("firmware_accessory", String.valueOf(this.dfuCandidateInformation.getCurrentVersion()));
            try {
                hashMap.put("firmware_accessory_0", String.valueOf(this.dfuCandidateInformation.getVersion()));
            } catch (Exception e) {
                Logger.e("Exception in put FIRMWARE_VERSION_0", e);
            }
        } catch (Exception e2) {
            Logger.e("Exception in set Meta data", e2);
        }
        return hashMap;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ CustomMetricsEvent lambda$createMetric$2(String str, PackageMetricsData packageMetricsData) throws Throwable {
        CustomMetricsEvent customMetricsEvent = new CustomMetricsEvent(str);
        customMetricsEvent.putString(MetricsConstants.Firmware.TARGET_NAME, packageMetricsData.getName());
        customMetricsEvent.putString(MetricsConstants.Firmware.TARGET_VERSION, String.valueOf(packageMetricsData.getVersionTo()));
        customMetricsEvent.putString(MetricsConstants.Firmware.TARGET_REFERENCE, packageMetricsData.getReference());
        return customMetricsEvent;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ CustomMetricsEvent lambda$reportMetricForFirmwareTask$0(String str, CustomMetricsEvent customMetricsEvent) throws Throwable {
        if (str != null) {
            customMetricsEvent.putString(MetricsConstants.Firmware.CAUSE_OF_FAILURE, str);
        }
        return customMetricsEvent;
    }

    @SuppressLint({"CheckResult"})
    private void reportFailureMetric(String str) {
        reportMetricForFirmwareTask(MetricsConstants.Firmware.FIRMWARE_UPDATE_FAILED_EVENT, str);
    }

    @SuppressLint({"CheckResult"})
    private void reportMetricForFirmwareTask(final String str, final String str2) {
        synchronized (this.lock) {
            if (this.released) {
                return;
            }
            this.released = true;
            this.totalTimeStopwatch.stop();
            this.suspendedTimeStopwatch.stop();
            Single<R> map = createMetric(str).map(new Function() { // from class: com.amazon.alexa.accessory.metrics.-$$Lambda$FirmwareTaskMetricsReporter$6RY13hrrxDc69g3eRit7-oVHQKc
                @Override // io.reactivex.rxjava3.functions.Function
                /* renamed from: apply */
                public final Object mo10358apply(Object obj) {
                    CustomMetricsEvent customMetricsEvent = (CustomMetricsEvent) obj;
                    FirmwareTaskMetricsReporter.lambda$reportMetricForFirmwareTask$0(str2, customMetricsEvent);
                    return customMetricsEvent;
                }
            });
            final MetricsReporter metricsReporter = this.metricsReporter;
            metricsReporter.getClass();
            map.subscribe(new Consumer() { // from class: com.amazon.alexa.accessory.metrics.-$$Lambda$ihmPOjvoDD_PkckydzNzovoV960
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    MetricsReporter.this.recordEvent((CustomMetricsEvent) obj);
                }
            }, new Consumer() { // from class: com.amazon.alexa.accessory.metrics.-$$Lambda$FirmwareTaskMetricsReporter$uy14LAjUISNaW94dL77zt90iAow
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    Throwable th = (Throwable) obj;
                    Logger.e("Critical: FirmwareTaskMetricsReporter failed to generate metrics event for" + str);
                }
            });
        }
    }

    public void applyFirmwareErrorResponse() {
        reportFailureMetric(MetricsConstants.Firmware.CAUSE_APPLY_FIRMWARE_ERROR);
    }

    public void componentTransmissionRanOutOfRetries() {
        reportFailureMetric(MetricsConstants.Firmware.CAUSE_RAN_OUT_OF_TRANSMISSION_RETRIES);
    }

    public void componentTransmissionRanOutOfRetriesForDavs() {
        reportFailureMetric(MetricsConstants.Firmware.CAUSE_RAN_OUT_OF_DAVS_TRANSMISSION_RETRIES);
    }

    public /* synthetic */ CustomMetricsEvent lambda$createMetric$3$FirmwareTaskMetricsReporter(CustomMetricsEvent customMetricsEvent) throws Throwable {
        addReporterData(customMetricsEvent);
        return customMetricsEvent;
    }

    public void onComponentCacheInvalidated() {
        synchronized (this.lock) {
            this.componentCacheInvalidationCount++;
        }
    }

    public void onComponentTransmissionCompleted(String str, int i) {
        synchronized (this.lock) {
            if (i != 0) {
                this.transmittedComponents.add(str);
            }
        }
    }

    public void onComponentTransmissionError() {
        synchronized (this.lock) {
            this.transmitComponentErrorCount++;
        }
    }

    public void onGetCachedComponentError() {
        synchronized (this.lock) {
            this.getCachedComponentErrorCount++;
        }
    }

    public void onGetCachedComponentErrorForDAVS() {
        synchronized (this.lock) {
            this.getCachedComponentErrorCountDAVS++;
        }
    }

    public void onTaskResumed() {
        this.suspendedTimeStopwatch.stop();
        this.totalTimeStopwatch.startOrResume();
    }

    public void onTaskSuspended() {
        synchronized (this.lock) {
            this.suspensionCount++;
        }
        this.suspendedTimeStopwatch.startOrResume();
    }

    public void onVerifyArtifactSignatureError() {
        synchronized (this.lock) {
            this.verifyArtifactSignatureErrorCount++;
        }
    }

    @Override // com.amazon.alexa.accessory.capabilities.firmware.AccessoryDfuMetricRecorder
    public void recordApplyFirmwareErrorCode(String str) {
        AccessoryMetricsServiceHolder.getInstance().get().recordCounter(GeneratedOutlineSupport1.outline72("AccessoryDfuOnApplyFirmwareErrorCode:", str), this.dfuCandidateInformation.getDeviceType(), 1.0d, getCustomDfuMetaData());
    }

    @Override // com.amazon.alexa.accessory.capabilities.firmware.AccessoryDfuMetricRecorder
    public void recordDfuSuccessMetric(boolean z) {
        AccessoryMetricsServiceHolder.getInstance().get().recordOccurrence(MetricsConstants.Dfu.SUCCESS, this.dfuCandidateInformation.getDeviceType(), z, getCustomDfuMetaData());
    }

    @Override // com.amazon.alexa.accessory.metrics.MetricsReporter
    public void recordEvent(MetricsEvent metricsEvent) {
        Preconditions.notNull(metricsEvent, "event");
        this.metricsReporter.recordEvent(metricsEvent);
    }

    public void recordFirmwareTaskError(int i, int i2) {
        AccessoryMetricsServiceHolder.getInstance().get().recordOccurrence((i <= 0 || i2 <= 0) ? (i <= 0 || i2 != 0) ? (i != 0 || i2 <= 0) ? MetricsConstants.Firmware.FIRMWARE_TASK_ERROR_NO_KOTA_NO_DAVS : MetricsConstants.Firmware.FIRMWARE_TASK_ERROR_NO_KOTA_HAS_DAVS : MetricsConstants.Firmware.FIRMWARE_TASK_ERROR_HAS_KOTA_NO_DAVS : MetricsConstants.Firmware.FIRMWARE_TASK_ERROR_HAS_KOTA_HAS_DAVS, this.dfuCandidateInformation.getDeviceType(), true, getCustomDfuMetaData());
    }

    public void recordFirmwareTaskStart(int i, int i2) {
        AccessoryMetricsServiceHolder.getInstance().get().recordOccurrence((i <= 0 || i2 <= 0) ? (i <= 0 || i2 != 0) ? (i != 0 || i2 <= 0) ? MetricsConstants.Firmware.FIRMWARE_TASK_START_NO_KOTA_NO_DAVS : MetricsConstants.Firmware.FIRMWARE_TASK_START_NO_KOTA_HAS_DAVS : MetricsConstants.Firmware.FIRMWARE_TASK_START_HAS_KOTA_NO_DAVS : MetricsConstants.Firmware.FIRMWARE_TASK_START_HAS_KOTA_HAS_DAVS, this.dfuCandidateInformation.getDeviceType(), true, getCustomDfuMetaData());
    }

    public void recordFirmwareTaskSuccess(int i, int i2) {
        AccessoryMetricsServiceHolder.getInstance().get().recordOccurrence((i <= 0 || i2 <= 0) ? (i <= 0 || i2 != 0) ? (i != 0 || i2 <= 0) ? MetricsConstants.Firmware.FIRMWARE_TASK_SUCCESS_NO_KOTA_NO_DAVS : MetricsConstants.Firmware.FIRMWARE_TASK_SUCCESS_NO_KOTA_HAS_DAVS : MetricsConstants.Firmware.FIRMWARE_TASK_SUCCESS_HAS_KOTA_NO_DAVS : MetricsConstants.Firmware.FIRMWARE_TASK_SUCCESS_HAS_KOTA_HAS_DAVS, this.dfuCandidateInformation.getDeviceType(), true, getCustomDfuMetaData());
    }

    @Override // com.amazon.alexa.accessory.capabilities.firmware.AccessoryDfuMetricRecorder
    public void recordMetric(String str) {
        AccessoryMetricsServiceHolder.getInstance().get().recordCounter(str, this.dfuCandidateInformation.getDeviceType(), 1.0d, getCustomDfuMetaData());
    }

    @SuppressLint({"CheckResult"})
    public void taskCompleted() {
        reportMetricForFirmwareTask(MetricsConstants.Firmware.FIRMWARE_UPDATE_APPLIED_EVENT, null);
    }

    public void taskDisposed() {
        reportFailureMetric(MetricsConstants.Firmware.CAUSE_DISPOSED);
    }
}
