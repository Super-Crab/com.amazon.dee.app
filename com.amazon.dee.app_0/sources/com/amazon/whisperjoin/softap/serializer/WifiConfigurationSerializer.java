package com.amazon.whisperjoin.softap.serializer;

import com.amazon.whisperjoin.wifi.WifiConfiguration;
/* loaded from: classes13.dex */
public interface WifiConfigurationSerializer extends ByteSerializer<WifiConfiguration> {
    byte[] serialize(WifiConfiguration wifiConfiguration);
}
