package com.amazon.devicesetup.provisioning.ble;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.collect.ImmutableMap;
import java.util.Map;
/* loaded from: classes12.dex */
public class StartProvisioningResponse {
    public static final byte DENIED_ACTIVELY_PROVISIONING_ALREADY = 1;
    public static final byte DENIED_INTERNAL_ERROR = 2;
    public static final byte GRANTED = 0;
    private static final Map<Byte, String> STATUS_MAP = ImmutableMap.builder().mo7828put((byte) 0, "Granted.").mo7828put((byte) 1, "Denied, active provisioning already started.").mo7828put((byte) 2, "Denied, internal error.").mo7826build();
    private final int mRequestID;
    private final byte mStatus;

    public StartProvisioningResponse(int i, byte b) {
        if (STATUS_MAP.containsKey(Byte.valueOf(b))) {
            this.mRequestID = i;
            this.mStatus = b;
            return;
        }
        throw new IllegalArgumentException("status must align with one of the pre-defined values.");
    }

    public int getRequestID() {
        return this.mRequestID;
    }

    public byte getStatus() {
        return this.mStatus;
    }

    public String getStatusMessage() {
        return STATUS_MAP.get(Byte.valueOf(this.mStatus));
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("StartProvisioningResponse [id=");
        outline107.append(this.mRequestID);
        outline107.append(", status=");
        return GeneratedOutlineSupport1.outline91(outline107, STATUS_MAP.get(Byte.valueOf(this.mStatus)), "]");
    }
}
