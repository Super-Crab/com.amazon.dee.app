package com.amazon.clouddrive.model;
/* loaded from: classes11.dex */
public class GetAccountInfoRequest implements CloudDriveRequest {
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof GetAccountInfoRequest) && compareTo((CloudDriveRequest) ((GetAccountInfoRequest) obj)) == 0;
    }

    public int hashCode() {
        return 1;
    }

    @Override // java.lang.Comparable
    public int compareTo(CloudDriveRequest cloudDriveRequest) {
        if (cloudDriveRequest == null) {
            return -1;
        }
        return (cloudDriveRequest != this && !(cloudDriveRequest instanceof GetAccountInfoRequest)) ? 1 : 0;
    }
}
