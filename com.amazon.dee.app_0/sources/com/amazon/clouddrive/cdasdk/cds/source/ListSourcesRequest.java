package com.amazon.clouddrive.cdasdk.cds.source;

import com.amazon.clouddrive.cdasdk.cds.common.CloudDriveRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes11.dex */
public class ListSourcesRequest extends CloudDriveRequest {
    @JsonProperty("includeDevice")
    private Boolean includeDevice;
    @JsonProperty("parentDeviceId")
    private String parentDeviceId;

    @Override // com.amazon.clouddrive.cdasdk.cds.common.CloudDriveRequest
    protected boolean canEqual(Object obj) {
        return obj instanceof ListSourcesRequest;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.common.CloudDriveRequest
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ListSourcesRequest)) {
            return false;
        }
        ListSourcesRequest listSourcesRequest = (ListSourcesRequest) obj;
        if (!listSourcesRequest.canEqual(this) || !super.equals(obj)) {
            return false;
        }
        Boolean includeDevice = getIncludeDevice();
        Boolean includeDevice2 = listSourcesRequest.getIncludeDevice();
        if (includeDevice != null ? !includeDevice.equals(includeDevice2) : includeDevice2 != null) {
            return false;
        }
        String parentDeviceId = getParentDeviceId();
        String parentDeviceId2 = listSourcesRequest.getParentDeviceId();
        return parentDeviceId != null ? parentDeviceId.equals(parentDeviceId2) : parentDeviceId2 == null;
    }

    public Boolean getIncludeDevice() {
        return this.includeDevice;
    }

    public String getParentDeviceId() {
        return this.parentDeviceId;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.common.CloudDriveRequest
    public int hashCode() {
        int hashCode = super.hashCode();
        Boolean includeDevice = getIncludeDevice();
        int i = 43;
        int hashCode2 = (hashCode * 59) + (includeDevice == null ? 43 : includeDevice.hashCode());
        String parentDeviceId = getParentDeviceId();
        int i2 = hashCode2 * 59;
        if (parentDeviceId != null) {
            i = parentDeviceId.hashCode();
        }
        return i2 + i;
    }

    @JsonProperty("includeDevice")
    public void setIncludeDevice(Boolean bool) {
        this.includeDevice = bool;
    }

    @JsonProperty("parentDeviceId")
    public void setParentDeviceId(String str) {
        this.parentDeviceId = str;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.common.CloudDriveRequest
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ListSourcesRequest(includeDevice=");
        outline107.append(getIncludeDevice());
        outline107.append(", parentDeviceId=");
        outline107.append(getParentDeviceId());
        outline107.append(")");
        return outline107.toString();
    }
}
