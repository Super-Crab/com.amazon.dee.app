package com.amazon.alexa.accessory.engagement;

import androidx.annotation.NonNull;
import com.amazon.alexa.accessory.Accessory;
import com.amazon.alexa.accessory.AccessorySession;
import com.amazon.alexa.accessory.AccessorySessionListener;
import com.amazon.alexa.accessory.SessionSupplier;
import com.amazon.alexa.accessory.capabilities.metrics.AccessoryMetric;
import com.amazon.alexa.accessory.capabilities.metrics.MetricsObserver;
import com.amazon.alexa.accessory.internal.monitor.NetworkStatusMonitor;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.protocol.Metrics;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes.dex */
public final class DeviceEngagementMetricsTriggers extends AccessorySessionListener implements NetworkStatusMonitor.Observer, MetricsObserver {
    private final DemReporter demReporter;
    private final NetworkStatusMonitor networkStatusMonitor;
    private final SessionSupplier sessionSupplier;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DeviceEngagementMetricsTriggers(@NonNull NetworkStatusMonitor networkStatusMonitor, @NonNull SessionSupplier sessionSupplier, @NonNull DemReporter demReporter) {
        Preconditions.notNull(networkStatusMonitor, "networkStatusMonitor");
        Preconditions.notNull(sessionSupplier, "sessionSupplier");
        Preconditions.notNull(demReporter, "demReporter");
        this.networkStatusMonitor = networkStatusMonitor;
        this.sessionSupplier = sessionSupplier;
        this.demReporter = demReporter;
    }

    @Override // com.amazon.alexa.accessory.AccessorySessionListener
    public void onAccessorySessionConnected(Accessory accessory) {
        AccessorySession session = this.sessionSupplier.getSession(accessory);
        this.demReporter.report(DemType.APP_CONNECTION, session, null);
        if (this.networkStatusMonitor.isConnected()) {
            this.demReporter.report(DemType.INTERNET_CONNECTED, session, null);
        }
    }

    @Override // com.amazon.alexa.accessory.capabilities.metrics.MetricsObserver
    public void onMetricsAvailable(AccessorySession accessorySession, List<AccessoryMetric> list, List<Metrics.MetricsEvent> list2) {
        if (!this.networkStatusMonitor.isConnected()) {
            return;
        }
        boolean z = false;
        String str = null;
        Iterator<AccessoryMetric> it2 = list.iterator();
        while (true) {
            if (!it2.hasNext()) {
                break;
            }
            AccessoryMetric next = it2.next();
            if (next.getDestination() == AccessoryMetric.Destination.USER_PRESENT) {
                z = true;
                str = String.valueOf(next.getTimestamp());
                break;
            }
        }
        if (!z) {
            return;
        }
        this.demReporter.report(DemType.USER_PRESENT, accessorySession, str);
    }

    @Override // com.amazon.alexa.accessory.internal.monitor.NetworkStatusMonitor.Observer
    public void onNetworkStatusChanged(boolean z) {
        if (!z) {
            return;
        }
        for (AccessorySession accessorySession : this.sessionSupplier.getActiveSessions()) {
            this.demReporter.report(DemType.INTERNET_CONNECTED, accessorySession, null);
        }
    }
}
