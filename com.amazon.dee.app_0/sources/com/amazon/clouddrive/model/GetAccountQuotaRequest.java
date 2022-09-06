package com.amazon.clouddrive.model;
/* loaded from: classes11.dex */
public class GetAccountQuotaRequest implements CloudDriveRequest {
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof GetAccountQuotaRequest) && compareTo((CloudDriveRequest) ((GetAccountQuotaRequest) obj)) == 0;
    }

    public int hashCode() {
        return 1;
    }

    @Override // java.lang.Comparable
    public int compareTo(CloudDriveRequest cloudDriveRequest) {
        if (cloudDriveRequest == null) {
            return -1;
        }
        return (cloudDriveRequest != this && !(cloudDriveRequest instanceof GetAccountQuotaRequest)) ? 1 : 0;
    }
}
