package com.amazon.clouddrive.model;
/* loaded from: classes11.dex */
public class GetApplicationAccessRulesRequest implements CloudDriveRequest {
    @Override // java.lang.Comparable
    public int compareTo(CloudDriveRequest cloudDriveRequest) {
        if (cloudDriveRequest == this) {
            return 0;
        }
        if (cloudDriveRequest == null) {
            return -1;
        }
        return !(cloudDriveRequest instanceof GetApplicationAccessRulesRequest) ? 1 : 0;
    }
}
