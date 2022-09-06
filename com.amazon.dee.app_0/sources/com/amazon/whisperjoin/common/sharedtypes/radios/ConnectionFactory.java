package com.amazon.whisperjoin.common.sharedtypes.radios;

import com.amazon.whisperjoin.common.sharedtypes.devices.DiscoveredRadio;
import com.amazon.whisperjoin.common.sharedtypes.devices.WhisperJoinPeripheralDeviceDetails;
import com.amazon.whisperjoin.common.sharedtypes.devices.interfaces.PeripheralDevice;
/* loaded from: classes13.dex */
public interface ConnectionFactory {
    PeripheralDevice create(WhisperJoinPeripheralDeviceDetails whisperJoinPeripheralDeviceDetails, DiscoveredRadio discoveredRadio);
}
