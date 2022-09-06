package com.amazon.devicesetupservice.smarthome;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
import java.util.List;
/* loaded from: classes12.dex */
public class SmartHomeDeviceCredentials implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.smarthome.SmartHomeDeviceCredentials");
    private List<BLEDeviceCredential> ble;
    private List<BLEMeshDeviceCredential> bleMesh;

    /* renamed from: chip  reason: collision with root package name */
    private List<ChipDeviceCredential> f8chip;
    private List<ZigbeeDeviceCredential> zigbee;

    public boolean equals(Object obj) {
        if (!(obj instanceof SmartHomeDeviceCredentials)) {
            return false;
        }
        SmartHomeDeviceCredentials smartHomeDeviceCredentials = (SmartHomeDeviceCredentials) obj;
        return Helper.equals(this.zigbee, smartHomeDeviceCredentials.zigbee) && Helper.equals(this.bleMesh, smartHomeDeviceCredentials.bleMesh) && Helper.equals(this.ble, smartHomeDeviceCredentials.ble) && Helper.equals(this.f8chip, smartHomeDeviceCredentials.f8chip);
    }

    public List<BLEDeviceCredential> getBle() {
        return this.ble;
    }

    public List<BLEMeshDeviceCredential> getBleMesh() {
        return this.bleMesh;
    }

    public List<ChipDeviceCredential> getChip() {
        return this.f8chip;
    }

    public List<ZigbeeDeviceCredential> getZigbee() {
        return this.zigbee;
    }

    public int hashCode() {
        return Helper.hash(Integer.valueOf(classNameHashCode), this.zigbee, this.bleMesh, this.ble, this.f8chip);
    }

    public void setBle(List<BLEDeviceCredential> list) {
        this.ble = list;
    }

    public void setBleMesh(List<BLEMeshDeviceCredential> list) {
        this.bleMesh = list;
    }

    public void setChip(List<ChipDeviceCredential> list) {
        this.f8chip = list;
    }

    public void setZigbee(List<ZigbeeDeviceCredential> list) {
        this.zigbee = list;
    }
}
