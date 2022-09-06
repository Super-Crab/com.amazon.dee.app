package com.amazon.alexa.accessory.capabilities.bulkdata;

import com.amazon.alexa.accessory.metrics.AccessoryMetricsServiceHolder;
import com.amazon.alexa.accessory.metrics.MetricsConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes.dex */
public final class BulkDataMetricsReporter {
    private BulkDataMetricsReporter() {
    }

    public static void recordIncomingManifest(boolean z, String str) {
        GeneratedOutlineSupport1.outline171(MetricsConstants.BulkData.INCOMING_MANIFEST_SUCCESS, str, z, null);
    }

    public static void recordIncomingManifestTime(long j, String str) {
        AccessoryMetricsServiceHolder.getInstance().get().recordCounter(MetricsConstants.BulkData.INCOMING_MANIFEST_TIME, str, new Long(j).doubleValue(), null);
    }

    public static void recordIncomingTransfer(boolean z, String str) {
        GeneratedOutlineSupport1.outline171(MetricsConstants.BulkData.INCOMING_TRANSFER_SUCCESS, str, z, null);
    }

    public static void recordIncomingTransferTime(long j, String str) {
        AccessoryMetricsServiceHolder.getInstance().get().recordCounter(MetricsConstants.BulkData.INCOMING_TRANSFER_TIME, str, new Long(j).doubleValue(), null);
    }
}
