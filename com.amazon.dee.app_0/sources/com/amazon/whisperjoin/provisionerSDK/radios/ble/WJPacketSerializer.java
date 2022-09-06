package com.amazon.whisperjoin.provisionerSDK.radios.ble;

import com.amazon.whisperjoin.common.sharedtypes.ble.WhisperJoinBlePacket;
import java.util.List;
/* loaded from: classes13.dex */
public interface WJPacketSerializer {

    /* loaded from: classes13.dex */
    public static class DefaultWJPacketSerializer implements WJPacketSerializer {
        @Override // com.amazon.whisperjoin.provisionerSDK.radios.ble.WJPacketSerializer
        public List<WhisperJoinBlePacket> serialize(byte[] bArr) {
            return WhisperJoinBlePacket.createPackets(bArr);
        }
    }

    List<WhisperJoinBlePacket> serialize(byte[] bArr);
}
