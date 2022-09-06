package com.amazon.alexa.accessorykit.finishsetup.metrics;

import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.metrics.AccessoryMetricsService;
/* loaded from: classes6.dex */
public final class DefaultFinishSetupMetricsRecorder implements FinishSetupMetricsRecorder {
    private final AccessoryMetricsService accessoryMetricsService;

    public DefaultFinishSetupMetricsRecorder(AccessoryMetricsService accessoryMetricsService) {
        Preconditions.notNull(accessoryMetricsService, "accessoryMetricsService");
        this.accessoryMetricsService = accessoryMetricsService;
    }

    @Override // com.amazon.alexa.accessorykit.finishsetup.metrics.FinishSetupMetricsRecorder
    public void recordKnownDeviceSetupCompleted(String str) {
        this.accessoryMetricsService.recordOccurrence("FinishAlexaSetupKnownDeviceSetupCompleted", str, true, null);
    }

    @Override // com.amazon.alexa.accessorykit.finishsetup.metrics.FinishSetupMetricsRecorder
    public void recordShowNotification(String str) {
        this.accessoryMetricsService.recordOccurrence("FinishAlexaSetupShowNotification", str, true, null);
    }
}
