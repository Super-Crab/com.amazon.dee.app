package com.amazon.matter.discovery;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
/* loaded from: classes9.dex */
public final class BleHelper {
    private static final String TAG = DiscoveryServiceBleImpl.class.getSimpleName();

    private BleHelper() {
    }

    public static boolean isServiceDataMatchSetupPayload(byte[] bArr, short s, short s2, short s3) {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("serviceData:");
        outline107.append(Arrays.toString(bArr));
        outline107.toString();
        ByteBuffer allocate = ByteBuffer.allocate(2);
        allocate.order(ByteOrder.LITTLE_ENDIAN);
        allocate.put(bArr[1]);
        allocate.put(bArr[2]);
        int i = allocate.getShort(0) & 3840;
        if ((s & i) != i) {
            return false;
        }
        if (s2 != 0) {
            ByteBuffer allocate2 = ByteBuffer.allocate(2);
            allocate2.order(ByteOrder.LITTLE_ENDIAN);
            allocate2.put(bArr[3]);
            allocate2.put(bArr[4]);
            if (allocate2.getShort(0) != s2) {
                return false;
            }
        }
        if (s3 != 0) {
            ByteBuffer allocate3 = ByteBuffer.allocate(2);
            allocate3.order(ByteOrder.LITTLE_ENDIAN);
            allocate3.put(bArr[5]);
            allocate3.put(bArr[6]);
            if (allocate3.getShort(0) != s3) {
                return false;
            }
        }
        return true;
    }
}
