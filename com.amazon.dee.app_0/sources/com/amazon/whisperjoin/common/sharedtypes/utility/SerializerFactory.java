package com.amazon.whisperjoin.common.sharedtypes.utility;

import com.amazon.whisperjoin.common.sharedtypes.devices.DiscoveredRadio;
import com.amazon.whisperjoin.common.sharedtypes.devices.WhisperJoinPeripheralDeviceDetails;
/* loaded from: classes13.dex */
public interface SerializerFactory {
    Serializer create(WhisperJoinPeripheralDeviceDetails whisperJoinPeripheralDeviceDetails, DiscoveredRadio discoveredRadio);
}
