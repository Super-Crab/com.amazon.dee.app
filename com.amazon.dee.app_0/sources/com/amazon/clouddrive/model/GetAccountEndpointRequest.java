package com.amazon.clouddrive.model;
/* loaded from: classes11.dex */
public class GetAccountEndpointRequest implements CloudDriveRequest {
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof GetAccountEndpointRequest) && compareTo((CloudDriveRequest) ((GetAccountEndpointRequest) obj)) == 0;
    }

    public int hashCode() {
        return 1;
    }

    @Override // java.lang.Comparable
    public int compareTo(CloudDriveRequest cloudDriveRequest) {
        if (cloudDriveRequest == null) {
            return -1;
        }
        return (cloudDriveRequest != this && !(cloudDriveRequest instanceof GetAccountEndpointRequest)) ? 1 : 0;
    }
}
