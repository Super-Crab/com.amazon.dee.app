package com.amazon.devicesetupservice.smarthome;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
/* loaded from: classes12.dex */
public class BLEMeshDeviceCredential implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.smarthome.BLEMeshDeviceCredential");
    private String oobData;
    private int oobType;
    private String uuid;

    public boolean equals(Object obj) {
        if (!(obj instanceof BLEMeshDeviceCredential)) {
            return false;
        }
        BLEMeshDeviceCredential bLEMeshDeviceCredential = (BLEMeshDeviceCredential) obj;
        return Helper.equals(this.oobData, bLEMeshDeviceCredential.oobData) && Helper.equals(Integer.valueOf(this.oobType), Integer.valueOf(bLEMeshDeviceCredential.oobType)) && Helper.equals(this.uuid, bLEMeshDeviceCredential.uuid);
    }

    public String getOobData() {
        return this.oobData;
    }

    public int getOobType() {
        return this.oobType;
    }

    public String getUuid() {
        return this.uuid;
    }

    public int hashCode() {
        return Helper.hash(Integer.valueOf(classNameHashCode), this.oobData, Integer.valueOf(this.oobType), this.uuid);
    }

    public void setOobData(String str) {
        this.oobData = str;
    }

    public void setOobType(int i) {
        this.oobType = i;
    }

    public void setUuid(String str) {
        this.uuid = str;
    }
}
