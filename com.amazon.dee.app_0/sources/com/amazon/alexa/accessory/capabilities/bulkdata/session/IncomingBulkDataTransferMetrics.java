package com.amazon.alexa.accessory.capabilities.bulkdata.session;

import com.amazon.alexa.accessory.capabilities.bulkdata.BulkDataMetricsReporter;
import javax.annotation.Nullable;
/* loaded from: classes.dex */
public class IncomingBulkDataTransferMetrics {
    private final String deviceType;
    private long startTime = 0;

    public IncomingBulkDataTransferMetrics(@Nullable String str) {
        this.deviceType = str;
    }

    private void recordCompletionMetrics(boolean z) {
        String str = this.deviceType;
        if (str == null) {
            return;
        }
        BulkDataMetricsReporter.recordIncomingTransfer(z, str);
        if (this.startTime == 0) {
            return;
        }
        BulkDataMetricsReporter.recordIncomingTransferTime(System.currentTimeMillis() - this.startTime, this.deviceType);
    }

    public void onError() {
        recordCompletionMetrics(false);
    }

    public void onStart() {
        this.startTime = System.currentTimeMillis();
    }

    public void onSuccess() {
        recordCompletionMetrics(true);
    }
}
