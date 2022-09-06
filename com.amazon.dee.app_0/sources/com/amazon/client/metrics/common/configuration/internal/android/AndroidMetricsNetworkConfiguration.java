package com.amazon.client.metrics.common.configuration.internal.android;

import com.amazon.client.metrics.common.configuration.MetricsConfigurationConverter;
import com.amazon.client.metrics.common.configuration.NetworkConfiguration;
import com.amazon.client.metrics.common.configuration.NetworkType;
import com.amazon.client.metrics.common.configuration.TransportType;
import com.amazon.client.metrics.thirdparty.configuration.MetricsNetworkConfiguration;
import java.util.Set;
import org.json.JSONObject;
/* loaded from: classes11.dex */
public class AndroidMetricsNetworkConfiguration implements NetworkConfiguration {
    private final com.amazon.client.metrics.thirdparty.configuration.NetworkConfiguration mDelegateThirdPartyNetworkConfiguration;

    public AndroidMetricsNetworkConfiguration(TransportType transportType, Set<NetworkType> set) {
        this.mDelegateThirdPartyNetworkConfiguration = new MetricsNetworkConfiguration(com.amazon.client.metrics.thirdparty.configuration.TransportType.valueOf(transportType.name()), MetricsConfigurationConverter.convertNetworkTypes_fromCommonToThirdParty(set));
    }

    public com.amazon.client.metrics.thirdparty.configuration.NetworkConfiguration getDelegateNetworkConfiguration() {
        return this.mDelegateThirdPartyNetworkConfiguration;
    }

    @Override // com.amazon.client.metrics.common.configuration.NetworkConfiguration
    public Set<NetworkType> getNetworkTypes() {
        return MetricsConfigurationConverter.convertNetworkTypes_fromThirdPartyToCommon(this.mDelegateThirdPartyNetworkConfiguration.getNetworkTypes());
    }

    @Override // com.amazon.client.metrics.common.configuration.NetworkConfiguration
    public TransportType getTransportType() {
        return TransportType.valueOf(this.mDelegateThirdPartyNetworkConfiguration.getTransportType().name());
    }

    @Override // com.amazon.client.metrics.common.configuration.NetworkConfiguration
    public void updateNetworkConfigurationWithRemoteSettings(JSONObject jSONObject) {
    }

    public AndroidMetricsNetworkConfiguration(com.amazon.client.metrics.thirdparty.configuration.NetworkConfiguration networkConfiguration) {
        if (networkConfiguration != null) {
            this.mDelegateThirdPartyNetworkConfiguration = networkConfiguration;
            return;
        }
        throw new NullPointerException("Delegate ThirdParty NetworkConfiguration may not be null");
    }
}
