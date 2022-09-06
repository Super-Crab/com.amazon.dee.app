package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.model.CloudDriveRequest;
/* loaded from: classes11.dex */
public class ImportContactsRequest implements CloudDriveRequest {
    @Override // java.lang.Comparable
    public int compareTo(CloudDriveRequest cloudDriveRequest) {
        if (cloudDriveRequest == null) {
            return -1;
        }
        return (cloudDriveRequest != this && !(cloudDriveRequest instanceof ImportContactsRequest)) ? 1 : 0;
    }
}
