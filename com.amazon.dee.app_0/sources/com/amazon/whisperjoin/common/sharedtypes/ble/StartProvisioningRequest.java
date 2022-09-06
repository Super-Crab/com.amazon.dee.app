package com.amazon.whisperjoin.common.sharedtypes.ble;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.security.SecureRandom;
/* loaded from: classes13.dex */
public class StartProvisioningRequest {
    private static final String TAG = "StartProvisioningRequest";
    private int mRequestID;

    public StartProvisioningRequest(int i) {
        this.mRequestID = i;
    }

    public static StartProvisioningRequest createRequest() {
        return new StartProvisioningRequest(new SecureRandom().nextInt());
    }

    public int getRequestID() {
        return this.mRequestID;
    }

    public String toString() {
        return GeneratedOutlineSupport1.outline86(GeneratedOutlineSupport1.outline107("StartProvisioningRequest [id="), this.mRequestID, "]");
    }
}
