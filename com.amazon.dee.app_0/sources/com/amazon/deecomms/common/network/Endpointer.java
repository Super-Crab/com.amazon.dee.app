package com.amazon.deecomms.common.network;

import com.amazon.deecomms.remoteConfig.ArcusConfig;
import com.amazon.deecomms.remoteConfig.RemoteConfigKeys;
/* loaded from: classes12.dex */
public class Endpointer {
    private final ArcusConfig arcusConfig;

    public Endpointer(ArcusConfig arcusConfig) {
        this.arcusConfig = arcusConfig;
    }

    public String getACMSEndpoint() {
        return this.arcusConfig.getConfigString(RemoteConfigKeys.ACMS_ENDPOINT_KEY);
    }

    public String getDUIEndpoint() {
        return this.arcusConfig.getConfigString(RemoteConfigKeys.DUI_ENDPOINT_KEY);
    }

    public String getSipProxyEndpoint() {
        return this.arcusConfig.getConfigString(RemoteConfigKeys.SIP_ENDPOINT_KEY);
    }
}
