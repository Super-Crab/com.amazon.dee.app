package com.amazon.clouddrive.model;
/* loaded from: classes11.dex */
public class GetOverwriteFileProgressRequest extends GetFileProgressRequest {
    public GetOverwriteFileProgressRequest(String str) {
        super(str);
    }

    @Override // com.amazon.clouddrive.model.GetFileProgressRequest
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof GetOverwriteFileProgressRequest) && compareTo((CloudDriveRequest) ((GetOverwriteFileProgressRequest) obj)) == 0;
    }

    public String getNodeId() {
        return this.mId;
    }

    @Override // com.amazon.clouddrive.model.GetFileProgressRequest
    public /* bridge */ /* synthetic */ int hashCode() {
        return super.hashCode();
    }

    public void setNodeId(String str) {
        this.mId = str;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.model.GetFileProgressRequest, java.lang.Comparable
    public int compareTo(CloudDriveRequest cloudDriveRequest) {
        if (cloudDriveRequest == null) {
            return -1;
        }
        if (cloudDriveRequest == this) {
            return 0;
        }
        if (cloudDriveRequest instanceof GetOverwriteFileProgressRequest) {
            return super.compareTo(cloudDriveRequest);
        }
        return 1;
    }
}
