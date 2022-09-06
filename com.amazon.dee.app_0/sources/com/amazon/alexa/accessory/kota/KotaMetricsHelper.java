package com.amazon.alexa.accessory.kota;

import com.amazon.alexa.accessory.metrics.AccessoryMetricsServiceHolder;
import com.amazon.alexa.accessory.metrics.MetricsConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes.dex */
public final class KotaMetricsHelper {
    private KotaMetricsHelper() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void recordFirmwareDownloadSuccess(boolean z, String str) {
        GeneratedOutlineSupport1.outline171(MetricsConstants.Kota.FIRMWARE_DOWNLOAD_SUCCESS, str, z, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void recordJobActivatedSuccess(boolean z, String str) {
        GeneratedOutlineSupport1.outline171(MetricsConstants.Kota.JOB_ACTIVATED_SUCCESS, str, z, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void recordJobScheduledSuccess(boolean z, String str) {
        GeneratedOutlineSupport1.outline171(MetricsConstants.Kota.DOWNLOAD_JOB_SCHEDULED_SUCCESS, str, z, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void recordPreparedCheckForUpdateRequest(boolean z, String str) {
        GeneratedOutlineSupport1.outline171(MetricsConstants.Kota.PREPARE_DFU_CHECK_UPDATE, str, z, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void recordUpdateCheckError(Throwable th, String str) {
        AccessoryMetricsServiceHolder.getInstance().get().recordCriticalEvent(MetricsConstants.Kota.UPDATE_CHECK_ERROR, MetricsConstants.Kota.UPDATE_CHECK_ERROR, str, th);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void recordUpdateCheckSuccess(boolean z, String str) {
        GeneratedOutlineSupport1.outline171(MetricsConstants.Kota.UPDATE_CHECK_SUCCESS, str, z, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void recordUserRetrievedForUpdateCheck(boolean z, String str) {
        GeneratedOutlineSupport1.outline171(MetricsConstants.Kota.USER_RETRIEVED_FOR_DFU_CHECK_UPDATE, str, z, null);
    }
}
