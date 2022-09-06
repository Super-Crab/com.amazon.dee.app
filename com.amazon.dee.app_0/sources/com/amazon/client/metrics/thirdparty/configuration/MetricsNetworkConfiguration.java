package com.amazon.client.metrics.thirdparty.configuration;

import java.util.Set;
import org.json.JSONObject;
/* loaded from: classes11.dex */
public class MetricsNetworkConfiguration implements NetworkConfiguration {
    final Set<NetworkType> mNetworkTypes;
    TransportType mTransportType;

    public MetricsNetworkConfiguration(TransportType transportType, Set<NetworkType> set) {
        if (transportType != null) {
            if (set != null && !set.isEmpty()) {
                this.mTransportType = transportType;
                this.mNetworkTypes = set;
                return;
            }
            throw new IllegalArgumentException("networkTypes is null or empty in configuration");
        }
        throw new IllegalArgumentException("tranportType is null in configuration");
    }

    @Override // com.amazon.client.metrics.thirdparty.configuration.NetworkConfiguration
    public Set<NetworkType> getNetworkTypes() {
        return this.mNetworkTypes;
    }

    @Override // com.amazon.client.metrics.thirdparty.configuration.NetworkConfiguration
    public TransportType getTransportType() {
        return this.mTransportType;
    }

    @Override // com.amazon.client.metrics.thirdparty.configuration.NetworkConfiguration
    public void updateNetworkConfigurationWithRemoteSettings(JSONObject jSONObject) {
        throw new UnsupportedOperationException("Updating Network Configuration is not supported for Metrics 3p Library");
    }
}
