package com.amazon.whisperjoin.common.sharedtypes.configuration;

import com.amazon.whisperjoin.common.sharedtypes.radios.ConnectionFactory;
import com.amazon.whisperjoin.common.sharedtypes.radios.DiscoveryService;
/* loaded from: classes13.dex */
public class RadioConfiguration {
    private final ConnectionFactory mConnectionFactory;
    private final DiscoveryService mDiscoveryService;
    private final String mSupportedRadio;

    public RadioConfiguration(DiscoveryService discoveryService, String str, ConnectionFactory connectionFactory) {
        if (discoveryService != null) {
            if (str == null || str.isEmpty()) {
                throw new IllegalArgumentException("supportedRadio can not be null or empty");
            }
            if (connectionFactory != null) {
                this.mDiscoveryService = discoveryService;
                this.mSupportedRadio = str;
                this.mConnectionFactory = connectionFactory;
                return;
            }
            throw new IllegalArgumentException("connectionFactory can not be null");
        }
        throw new IllegalArgumentException("discoveryService can not be null");
    }

    public ConnectionFactory getConnectionFactory() {
        return this.mConnectionFactory;
    }

    public DiscoveryService getDiscoveryService() {
        return this.mDiscoveryService;
    }

    public String getRadioType() {
        return this.mSupportedRadio;
    }
}
