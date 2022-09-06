package com.amazon.clouddrive.model;
/* loaded from: classes11.dex */
public class GetAccountUsageRequest implements CloudDriveRequest {
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof GetAccountUsageRequest) && compareTo((CloudDriveRequest) ((GetAccountUsageRequest) obj)) == 0;
    }

    public int hashCode() {
        return 1;
    }

    @Override // java.lang.Comparable
    public int compareTo(CloudDriveRequest cloudDriveRequest) {
        if (cloudDriveRequest == null) {
            return -1;
        }
        return (cloudDriveRequest != this && !(cloudDriveRequest instanceof GetAccountUsageRequest)) ? 1 : 0;
    }
}
