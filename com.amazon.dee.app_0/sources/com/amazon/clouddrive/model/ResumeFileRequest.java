package com.amazon.clouddrive.model;
/* loaded from: classes11.dex */
public abstract class ResumeFileRequest extends StreamFileRequest {
    private long mResumeFromPosition = 0;

    @Override // com.amazon.clouddrive.model.StreamFileRequest
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof ResumeFileRequest) && compareTo((CloudDriveRequest) ((ResumeFileRequest) obj)) == 0;
    }

    public long getResumeFromPosition() {
        return this.mResumeFromPosition;
    }

    @Override // com.amazon.clouddrive.model.StreamFileRequest
    public int hashCode() {
        return ((int) (getResumeFromPosition() ^ (getResumeFromPosition() >>> 32))) ^ super.hashCode();
    }

    public void setResumeFromPosition(long j) {
        this.mResumeFromPosition = j;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.model.StreamFileRequest, java.lang.Comparable
    public int compareTo(CloudDriveRequest cloudDriveRequest) {
        if (cloudDriveRequest == null) {
            return -1;
        }
        if (cloudDriveRequest == this) {
            return 0;
        }
        if (!(cloudDriveRequest instanceof ResumeFileRequest)) {
            return 1;
        }
        int compare = ObjectComparator.compare(Long.valueOf(getResumeFromPosition()), Long.valueOf(((ResumeFileRequest) cloudDriveRequest).getResumeFromPosition()));
        return compare != 0 ? compare : super.compareTo(cloudDriveRequest);
    }
}
