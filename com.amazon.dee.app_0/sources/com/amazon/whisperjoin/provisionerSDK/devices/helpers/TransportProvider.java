package com.amazon.whisperjoin.provisionerSDK.devices.helpers;

import com.amazon.whisperbridge.Transport;
import java.util.Map;
/* loaded from: classes13.dex */
public interface TransportProvider {

    /* loaded from: classes13.dex */
    public static class DefaultTransportProvider implements TransportProvider {
        private final Map<String, Transport> mRadioTransports;

        public DefaultTransportProvider(Map<String, Transport> map) {
            if (map != null) {
                this.mRadioTransports = map;
                return;
            }
            throw new IllegalArgumentException("Radio Transports can not be null");
        }

        @Override // com.amazon.whisperjoin.provisionerSDK.devices.helpers.TransportProvider
        public Transport getTransportForRadio(String str) {
            return this.mRadioTransports.get(str);
        }
    }

    Transport getTransportForRadio(String str);
}
