package com.amazon.clouddrive.cdasdk.dps.devices;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
/* loaded from: classes11.dex */
public class ListDevicesResponse {
    @JsonProperty("devices")
    private List<DeviceResponse> devices;

    protected boolean canEqual(Object obj) {
        return obj instanceof ListDevicesResponse;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ListDevicesResponse)) {
            return false;
        }
        ListDevicesResponse listDevicesResponse = (ListDevicesResponse) obj;
        if (!listDevicesResponse.canEqual(this)) {
            return false;
        }
        List<DeviceResponse> devices = getDevices();
        List<DeviceResponse> devices2 = listDevicesResponse.getDevices();
        return devices != null ? devices.equals(devices2) : devices2 == null;
    }

    public List<DeviceResponse> getDevices() {
        return this.devices;
    }

    public int hashCode() {
        List<DeviceResponse> devices = getDevices();
        return 59 + (devices == null ? 43 : devices.hashCode());
    }

    @JsonProperty("devices")
    public void setDevices(List<DeviceResponse> list) {
        this.devices = list;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ListDevicesResponse(devices=");
        outline107.append(getDevices());
        outline107.append(")");
        return outline107.toString();
    }
}
