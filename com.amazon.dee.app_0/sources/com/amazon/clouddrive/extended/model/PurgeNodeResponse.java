package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.model.CloudDriveResponse;
/* loaded from: classes11.dex */
public class PurgeNodeResponse implements CloudDriveResponse {
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof PurgeNodeResponse) && compareTo((CloudDriveResponse) ((PurgeNodeResponse) obj)) == 0;
    }

    public int hashCode() {
        return 31 + super.hashCode();
    }

    @Override // java.lang.Comparable
    public int compareTo(CloudDriveResponse cloudDriveResponse) {
        if (cloudDriveResponse == null) {
            return -1;
        }
        return (cloudDriveResponse != this && !(cloudDriveResponse instanceof PurgeNodeResponse)) ? 1 : 0;
    }
}
