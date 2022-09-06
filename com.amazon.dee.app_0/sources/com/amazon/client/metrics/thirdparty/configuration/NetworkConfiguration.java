package com.amazon.client.metrics.thirdparty.configuration;

import java.util.Set;
import org.json.JSONObject;
/* loaded from: classes11.dex */
public interface NetworkConfiguration {
    Set<NetworkType> getNetworkTypes();

    TransportType getTransportType();

    void updateNetworkConfigurationWithRemoteSettings(JSONObject jSONObject);
}
