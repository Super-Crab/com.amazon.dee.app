package com.amazon.alexa.accessory.internal;

import com.amazon.alexa.accessory.AccessorySession;
import com.amazon.alexa.accessory.capabilities.metrics.AccessoryMetric;
import com.amazon.alexa.accessory.capabilities.metrics.MetricsObserver;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.protocol.Metrics;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.List;
/* loaded from: classes.dex */
public final class UnavailableMetricsObserver implements MetricsObserver {
    @Override // com.amazon.alexa.accessory.capabilities.metrics.MetricsObserver
    public void onMetricsAvailable(AccessorySession accessorySession, List<AccessoryMetric> list, List<Metrics.MetricsEvent> list2) {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Received ");
        outline107.append(list.size());
        outline107.append(list2.size());
        outline107.append(" metrics from the accessory. Dropping, this is a noop implementation.");
        Logger.d(outline107.toString());
    }
}
