package com.amazon.clouddrive.model;
/* loaded from: classes11.dex */
abstract class GetFileProgressRequest implements CloudDriveRequest {
    protected String mId;

    /* JADX INFO: Access modifiers changed from: package-private */
    public GetFileProgressRequest(String str) {
        this.mId = str;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof GetFileProgressRequest) && compareTo((CloudDriveRequest) ((GetFileProgressRequest) obj)) == 0;
    }

    public int hashCode() {
        int hashCode = getClass().getSimpleName().hashCode();
        String str = this.mId;
        return hashCode + (str == null ? 0 : str.hashCode());
    }

    @Override // java.lang.Comparable
    public int compareTo(CloudDriveRequest cloudDriveRequest) {
        if (cloudDriveRequest == null) {
            return -1;
        }
        if (cloudDriveRequest == this) {
            return 0;
        }
        if (!(cloudDriveRequest instanceof GetFileProgressRequest)) {
            return 1;
        }
        int compare = ObjectComparator.compare(this.mId, ((GetFileProgressRequest) cloudDriveRequest).mId);
        if (compare == 0) {
            return 0;
        }
        return compare;
    }
}
