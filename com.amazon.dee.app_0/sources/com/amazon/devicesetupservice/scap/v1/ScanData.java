package com.amazon.devicesetupservice.scap.v1;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
import java.nio.ByteBuffer;
/* loaded from: classes12.dex */
public class ScanData implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.scap.v1.ScanData");
    private ByteBuffer advertisementData;
    private boolean isConnectable;
    private int rssi;

    public boolean equals(Object obj) {
        if (!(obj instanceof ScanData)) {
            return false;
        }
        ScanData scanData = (ScanData) obj;
        return Helper.equals(Boolean.valueOf(this.isConnectable), Boolean.valueOf(scanData.isConnectable)) && Helper.equals(this.advertisementData, scanData.advertisementData) && Helper.equals(Integer.valueOf(this.rssi), Integer.valueOf(scanData.rssi));
    }

    public ByteBuffer getAdvertisementData() {
        return this.advertisementData;
    }

    public int getRssi() {
        return this.rssi;
    }

    public int hashCode() {
        return Helper.hash(Integer.valueOf(classNameHashCode), Boolean.valueOf(this.isConnectable), this.advertisementData, Integer.valueOf(this.rssi));
    }

    public boolean isIsConnectable() {
        return this.isConnectable;
    }

    public void setAdvertisementData(ByteBuffer byteBuffer) {
        this.advertisementData = byteBuffer;
    }

    public void setIsConnectable(boolean z) {
        this.isConnectable = z;
    }

    public void setRssi(int i) {
        this.rssi = i;
    }
}
