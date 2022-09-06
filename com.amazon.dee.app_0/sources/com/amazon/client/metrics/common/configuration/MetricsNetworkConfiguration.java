package com.amazon.client.metrics.common.configuration;

import com.amazon.client.metrics.common.configuration.internal.NullMetricsNetworkConfiguration;
import com.amazon.client.metrics.common.configuration.internal.android.AndroidMetricsNetworkConfiguration;
import com.amazon.client.metrics.common.internal.util.DevicePlatformIdentifierUtil;
import java.util.Set;
import org.json.JSONObject;
/* loaded from: classes11.dex */
public class MetricsNetworkConfiguration implements NetworkConfiguration {
    private final NetworkConfiguration mDelegateNetworkConfiguration;

    public MetricsNetworkConfiguration(TransportType transportType, Set<NetworkType> set) {
        if (DevicePlatformIdentifierUtil.getInstance().isDevicePlatformAndroid()) {
            this.mDelegateNetworkConfiguration = new AndroidMetricsNetworkConfiguration(transportType, set);
        } else {
            this.mDelegateNetworkConfiguration = new NullMetricsNetworkConfiguration();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public NetworkConfiguration getDelegateNetworkConfiguration() {
        return this.mDelegateNetworkConfiguration;
    }

    @Override // com.amazon.client.metrics.common.configuration.NetworkConfiguration
    public Set<NetworkType> getNetworkTypes() {
        return this.mDelegateNetworkConfiguration.getNetworkTypes();
    }

    @Override // com.amazon.client.metrics.common.configuration.NetworkConfiguration
    public TransportType getTransportType() {
        return this.mDelegateNetworkConfiguration.getTransportType();
    }

    @Override // com.amazon.client.metrics.common.configuration.NetworkConfiguration
    public void updateNetworkConfigurationWithRemoteSettings(JSONObject jSONObject) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MetricsNetworkConfiguration(NetworkConfiguration networkConfiguration) {
        if (networkConfiguration != null) {
            this.mDelegateNetworkConfiguration = networkConfiguration;
            return;
        }
        throw new NullPointerException("Delegate Network Configuration may not be null");
    }
}
