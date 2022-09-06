package com.amazon.devicesetupservice.scap.v1;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
import java.nio.ByteBuffer;
/* loaded from: classes12.dex */
public class BlacklistDevice implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.scap.v1.BlacklistDevice");
    private ByteBuffer advertisementData;
    private String deviceId;

    public boolean equals(Object obj) {
        if (!(obj instanceof BlacklistDevice)) {
            return false;
        }
        BlacklistDevice blacklistDevice = (BlacklistDevice) obj;
        return Helper.equals(this.deviceId, blacklistDevice.deviceId) && Helper.equals(this.advertisementData, blacklistDevice.advertisementData);
    }

    public ByteBuffer getAdvertisementData() {
        return this.advertisementData;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public int hashCode() {
        return Helper.hash(Integer.valueOf(classNameHashCode), this.deviceId, this.advertisementData);
    }

    public void setAdvertisementData(ByteBuffer byteBuffer) {
        this.advertisementData = byteBuffer;
    }

    public void setDeviceId(String str) {
        this.deviceId = str;
    }
}
