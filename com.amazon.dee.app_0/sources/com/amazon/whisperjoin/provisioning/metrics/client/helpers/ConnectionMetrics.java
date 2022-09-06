package com.amazon.whisperjoin.provisioning.metrics.client.helpers;

import com.amazon.whisperjoin.provisioning.Radios;
import com.amazon.whisperjoin.provisioning.metrics.client.MetricHelper;
/* loaded from: classes13.dex */
public class ConnectionMetrics {
    private static final String ACTIVE_RADIO_METRIC = "ActiveRadio";
    private static final String CONNECTION_DROPPED_METRIC = "ConnectionDropped";
    private static final String CONNECTION_FAILED_METRIC = "ConnectionFailed";
    private static final String CONNECT_METRIC = "Connected";
    private static final String NO_DEVICES_DISCOVERED_METRIC = "NoDevicesDiscovered";
    private final Radios mActiveRadio;
    private final MetricHelper mMetricHelper;

    public ConnectionMetrics(MetricHelper metricHelper, Radios radios) {
        this.mMetricHelper = metricHelper;
        this.mActiveRadio = radios;
        this.mMetricHelper.recordString(ACTIVE_RADIO_METRIC, this.mActiveRadio, new Object[0]);
    }

    public void onConnectionAttemptFailed() {
        this.mMetricHelper.recordCounter(CONNECTION_FAILED_METRIC, 1.0d, new Object[0]);
        this.mMetricHelper.recordCounter(CONNECTION_FAILED_METRIC, 1.0d, this.mActiveRadio);
    }

    public void onConnectionDropped() {
        this.mMetricHelper.recordCounter("ConnectionDropped", 1.0d, new Object[0]);
        this.mMetricHelper.recordCounter("ConnectionDropped", 1.0d, this.mActiveRadio);
    }

    public void onConnectionEstablished() {
        this.mMetricHelper.recordCounter("Connected", 1.0d, new Object[0]);
        this.mMetricHelper.recordCounter("Connected", 1.0d, this.mActiveRadio);
    }

    public void onDiscoveryFailed() {
        this.mMetricHelper.recordCounter(NO_DEVICES_DISCOVERED_METRIC, 1.0d, new Object[0]);
        this.mMetricHelper.recordCounter(NO_DEVICES_DISCOVERED_METRIC, 1.0d, this.mActiveRadio);
    }
}
