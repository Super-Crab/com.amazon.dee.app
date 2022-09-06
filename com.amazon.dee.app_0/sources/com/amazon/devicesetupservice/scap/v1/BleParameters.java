package com.amazon.devicesetupservice.scap.v1;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
import java.nio.ByteBuffer;
/* loaded from: classes12.dex */
public class BleParameters implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.scap.v1.BleParameters");
    private String characteristicUuid;
    private String deviceId;
    private ByteBuffer payload;
    private String serviceUuid;

    public boolean equals(Object obj) {
        if (!(obj instanceof BleParameters)) {
            return false;
        }
        BleParameters bleParameters = (BleParameters) obj;
        return Helper.equals(this.deviceId, bleParameters.deviceId) && Helper.equals(this.serviceUuid, bleParameters.serviceUuid) && Helper.equals(this.payload, bleParameters.payload) && Helper.equals(this.characteristicUuid, bleParameters.characteristicUuid);
    }

    public String getCharacteristicUuid() {
        return this.characteristicUuid;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public ByteBuffer getPayload() {
        return this.payload;
    }

    public String getServiceUuid() {
        return this.serviceUuid;
    }

    public int hashCode() {
        return Helper.hash(Integer.valueOf(classNameHashCode), this.deviceId, this.serviceUuid, this.payload, this.characteristicUuid);
    }

    public void setCharacteristicUuid(String str) {
        this.characteristicUuid = str;
    }

    public void setDeviceId(String str) {
        this.deviceId = str;
    }

    public void setPayload(ByteBuffer byteBuffer) {
        this.payload = byteBuffer;
    }

    public void setServiceUuid(String str) {
        this.serviceUuid = str;
    }
}
