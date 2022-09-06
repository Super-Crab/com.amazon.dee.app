package com.amazon.alexa.accessory.metrics;

import androidx.annotation.NonNull;
/* loaded from: classes.dex */
public final class AccessoryMetricsServiceHolder {
    private AccessoryMetricsService accessoryMetricsService;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class LazyHolder {
        static final AccessoryMetricsServiceHolder INSTANCE = new AccessoryMetricsServiceHolder();

        private LazyHolder() {
        }
    }

    public static AccessoryMetricsServiceHolder getInstance() {
        return LazyHolder.INSTANCE;
    }

    @NonNull
    public AccessoryMetricsService get() {
        AccessoryMetricsService accessoryMetricsService = this.accessoryMetricsService;
        return accessoryMetricsService == null ? new NoOpMetricsService() : accessoryMetricsService;
    }

    public void set(@NonNull AccessoryMetricsService accessoryMetricsService) {
        this.accessoryMetricsService = accessoryMetricsService;
    }

    private AccessoryMetricsServiceHolder() {
    }
}
