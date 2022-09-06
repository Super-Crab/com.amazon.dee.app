package com.amazon.clouddrive.model;
/* loaded from: classes11.dex */
public class GetOverwriteFileProgressResponse extends GetFileProgressResponse {
    @Override // com.amazon.clouddrive.model.GetFileProgressResponse
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof GetOverwriteFileProgressResponse) && compareTo((CloudDriveResponse) ((GetOverwriteFileProgressResponse) obj)) == 0;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.model.GetFileProgressResponse, java.lang.Comparable
    public int compareTo(CloudDriveResponse cloudDriveResponse) {
        if (cloudDriveResponse == null) {
            return -1;
        }
        if (cloudDriveResponse == this) {
            return 0;
        }
        if (cloudDriveResponse instanceof GetOverwriteFileProgressResponse) {
            return super.compareTo(cloudDriveResponse);
        }
        return 1;
    }
}
