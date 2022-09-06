package com.amazon.clouddrive.cdasdk.dps.devices;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes11.dex */
public class DeviceResponse {
    @JsonProperty("deviceAccount")
    private String deviceAccount;
    @JsonProperty("name")
    private String name;
    @JsonProperty("type")
    private String type;

    protected boolean canEqual(Object obj) {
        return obj instanceof DeviceResponse;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DeviceResponse)) {
            return false;
        }
        DeviceResponse deviceResponse = (DeviceResponse) obj;
        if (!deviceResponse.canEqual(this)) {
            return false;
        }
        String name = getName();
        String name2 = deviceResponse.getName();
        if (name != null ? !name.equals(name2) : name2 != null) {
            return false;
        }
        String type = getType();
        String type2 = deviceResponse.getType();
        if (type != null ? !type.equals(type2) : type2 != null) {
            return false;
        }
        String deviceAccount = getDeviceAccount();
        String deviceAccount2 = deviceResponse.getDeviceAccount();
        return deviceAccount != null ? deviceAccount.equals(deviceAccount2) : deviceAccount2 == null;
    }

    public String getDeviceAccount() {
        return this.deviceAccount;
    }

    public String getName() {
        return this.name;
    }

    public String getType() {
        return this.type;
    }

    public int hashCode() {
        String name = getName();
        int i = 43;
        int hashCode = name == null ? 43 : name.hashCode();
        String type = getType();
        int hashCode2 = ((hashCode + 59) * 59) + (type == null ? 43 : type.hashCode());
        String deviceAccount = getDeviceAccount();
        int i2 = hashCode2 * 59;
        if (deviceAccount != null) {
            i = deviceAccount.hashCode();
        }
        return i2 + i;
    }

    @JsonProperty("deviceAccount")
    public void setDeviceAccount(String str) {
        this.deviceAccount = str;
    }

    @JsonProperty("name")
    public void setName(String str) {
        this.name = str;
    }

    @JsonProperty("type")
    public void setType(String str) {
        this.type = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("DeviceResponse(name=");
        outline107.append(getName());
        outline107.append(", type=");
        outline107.append(getType());
        outline107.append(", deviceAccount=");
        outline107.append(getDeviceAccount());
        outline107.append(")");
        return outline107.toString();
    }
}
