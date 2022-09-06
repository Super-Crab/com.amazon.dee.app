package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.model.CloudDriveRequest;
import java.util.Objects;
/* loaded from: classes11.dex */
public class ListSourcesRequest implements CloudDriveRequest {
    private Boolean includeDevice;
    private String parentDeviceId;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ListSourcesRequest)) {
            return false;
        }
        ListSourcesRequest listSourcesRequest = (ListSourcesRequest) obj;
        return Objects.equals(getIncludeDevice(), listSourcesRequest.getIncludeDevice()) && Objects.equals(getParentDeviceId(), listSourcesRequest.getParentDeviceId());
    }

    public Boolean getIncludeDevice() {
        return this.includeDevice;
    }

    public String getParentDeviceId() {
        return this.parentDeviceId;
    }

    public int hashCode() {
        return Objects.hash(getIncludeDevice(), getParentDeviceId());
    }

    public void setIncludeDevice(Boolean bool) {
        this.includeDevice = bool;
    }

    public void setParentDeviceId(String str) {
        this.parentDeviceId = str;
    }

    public ListSourcesRequest withIncludeDevice(Boolean bool) {
        setIncludeDevice(bool);
        return this;
    }

    public ListSourcesRequest withParentDeviceId(String str) {
        setParentDeviceId(str);
        return this;
    }

    @Override // java.lang.Comparable
    public int compareTo(CloudDriveRequest cloudDriveRequest) {
        if (cloudDriveRequest == null) {
            return -1;
        }
        if (cloudDriveRequest == this) {
            return 0;
        }
        if (!(cloudDriveRequest instanceof ListSourcesRequest)) {
            return 1;
        }
        ListSourcesRequest listSourcesRequest = (ListSourcesRequest) cloudDriveRequest;
        Boolean includeDevice = getIncludeDevice();
        Boolean includeDevice2 = listSourcesRequest.getIncludeDevice();
        if (includeDevice != includeDevice2) {
            if (includeDevice == null) {
                return -1;
            }
            if (includeDevice2 == null) {
                return 1;
            }
            int compareTo = includeDevice.compareTo(includeDevice2);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        String parentDeviceId = getParentDeviceId();
        String parentDeviceId2 = listSourcesRequest.getParentDeviceId();
        if (parentDeviceId != parentDeviceId2) {
            if (parentDeviceId == null) {
                return -1;
            }
            if (parentDeviceId2 == null) {
                return 1;
            }
            int compareTo2 = parentDeviceId.compareTo(parentDeviceId2);
            if (compareTo2 != 0) {
                return compareTo2;
            }
        }
        return 0;
    }
}
