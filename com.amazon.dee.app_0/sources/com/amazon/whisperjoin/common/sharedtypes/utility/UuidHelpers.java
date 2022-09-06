package com.amazon.whisperjoin.common.sharedtypes.utility;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.UUID;
/* loaded from: classes13.dex */
public class UuidHelpers {
    public static UUID decodeIntoUuid(byte[] bArr) {
        ByteBuffer order = ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN);
        return new UUID(order.getLong(), order.getLong());
    }

    public static byte[] encodeIntoByteArray(UUID uuid) {
        ByteBuffer order = ByteBuffer.allocate(16).order(ByteOrder.LITTLE_ENDIAN);
        order.putLong(uuid.getLeastSignificantBits());
        order.putLong(uuid.getMostSignificantBits());
        return order.array();
    }
}
