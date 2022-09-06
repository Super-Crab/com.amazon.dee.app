package com.amazon.clouddrive.cdasdk.dps.devices;

import com.amazon.clouddrive.cdasdk.dps.common.DPSRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes11.dex */
public class ListDevicesRequest extends DPSRequest {
    @JsonProperty("deviceTypeId")
    private String deviceTypeId;
    @JsonProperty("dsn")
    private String dsn;

    @Override // com.amazon.clouddrive.cdasdk.dps.common.DPSRequest
    protected boolean canEqual(Object obj) {
        return obj instanceof ListDevicesRequest;
    }

    @Override // com.amazon.clouddrive.cdasdk.dps.common.DPSRequest
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ListDevicesRequest)) {
            return false;
        }
        ListDevicesRequest listDevicesRequest = (ListDevicesRequest) obj;
        if (!listDevicesRequest.canEqual(this) || !super.equals(obj)) {
            return false;
        }
        String deviceTypeId = getDeviceTypeId();
        String deviceTypeId2 = listDevicesRequest.getDeviceTypeId();
        if (deviceTypeId != null ? !deviceTypeId.equals(deviceTypeId2) : deviceTypeId2 != null) {
            return false;
        }
        String dsn = getDsn();
        String dsn2 = listDevicesRequest.getDsn();
        return dsn != null ? dsn.equals(dsn2) : dsn2 == null;
    }

    public String getDeviceTypeId() {
        return this.deviceTypeId;
    }

    public String getDsn() {
        return this.dsn;
    }

    @Override // com.amazon.clouddrive.cdasdk.dps.common.DPSRequest
    public int hashCode() {
        int hashCode = super.hashCode();
        String deviceTypeId = getDeviceTypeId();
        int i = 43;
        int hashCode2 = (hashCode * 59) + (deviceTypeId == null ? 43 : deviceTypeId.hashCode());
        String dsn = getDsn();
        int i2 = hashCode2 * 59;
        if (dsn != null) {
            i = dsn.hashCode();
        }
        return i2 + i;
    }

    @JsonProperty("deviceTypeId")
    public void setDeviceTypeId(String str) {
        this.deviceTypeId = str;
    }

    @JsonProperty("dsn")
    public void setDsn(String str) {
        this.dsn = str;
    }

    @Override // com.amazon.clouddrive.cdasdk.dps.common.DPSRequest
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ListDevicesRequest(deviceTypeId=");
        outline107.append(getDeviceTypeId());
        outline107.append(", dsn=");
        outline107.append(getDsn());
        outline107.append(")");
        return outline107.toString();
    }
}
