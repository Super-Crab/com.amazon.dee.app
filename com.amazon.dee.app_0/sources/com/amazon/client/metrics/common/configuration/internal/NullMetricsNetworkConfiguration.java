package com.amazon.client.metrics.common.configuration.internal;

import com.amazon.client.metrics.common.configuration.NetworkConfiguration;
import com.amazon.client.metrics.common.configuration.NetworkType;
import com.amazon.client.metrics.common.configuration.TransportType;
import java.util.Set;
import org.json.JSONObject;
/* loaded from: classes11.dex */
public class NullMetricsNetworkConfiguration implements NetworkConfiguration {
    @Override // com.amazon.client.metrics.common.configuration.NetworkConfiguration
    public Set<NetworkType> getNetworkTypes() {
        return null;
    }

    @Override // com.amazon.client.metrics.common.configuration.NetworkConfiguration
    public TransportType getTransportType() {
        return null;
    }

    @Override // com.amazon.client.metrics.common.configuration.NetworkConfiguration
    public void updateNetworkConfigurationWithRemoteSettings(JSONObject jSONObject) {
    }
}
