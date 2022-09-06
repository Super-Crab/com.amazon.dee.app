package com.amazon.clouddrive.model;
/* loaded from: classes11.dex */
public abstract class GetFileProgressResponse implements CloudDriveResponse {
    private long mBytesReceived;
    private String mContentLink;
    private String mExpectedMd5;
    private boolean mIsProgressAvailable = false;
    private String mNodeId;
    private String mResumeState;
    private String mStartedTime;

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof GetFileProgressResponse) && compareTo((CloudDriveResponse) ((GetFileProgressResponse) obj)) == 0;
    }

    public long getBytesReceived() {
        return this.mBytesReceived;
    }

    public String getContentLink() {
        return this.mContentLink;
    }

    public String getExpectedMd5() {
        return this.mExpectedMd5;
    }

    public String getNodeId() {
        return this.mNodeId;
    }

    public String getResumeState() {
        return this.mResumeState;
    }

    public String getStartedTime() {
        return this.mStartedTime;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = Long.valueOf(getBytesReceived()).hashCode() + getClass().getSimpleName().hashCode() + (getResumeState() == null ? 0 : getResumeState().hashCode()) + (getNodeId() == null ? 0 : getNodeId().hashCode()) + (getContentLink() == null ? 0 : getContentLink().hashCode()) + (getExpectedMd5() == null ? 0 : getExpectedMd5().hashCode());
        if (getStartedTime() != null) {
            i = getStartedTime().hashCode();
        }
        return hashCode + i + (isProgressAvailable() ? 1 : 0);
    }

    public boolean isProgressAvailable() {
        return this.mIsProgressAvailable;
    }

    public void setBytesReceived(long j) {
        this.mBytesReceived = j;
    }

    public void setContentLink(String str) {
        this.mContentLink = str;
    }

    public void setExpectedMd5(String str) {
        this.mExpectedMd5 = str;
    }

    public void setIsProgressAvailable(boolean z) {
        this.mIsProgressAvailable = z;
    }

    public void setNodeId(String str) {
        this.mNodeId = str;
    }

    public void setResumeState(String str) {
        this.mResumeState = str;
    }

    public void setStartedTime(String str) {
        this.mStartedTime = str;
    }

    @Override // java.lang.Comparable
    public int compareTo(CloudDriveResponse cloudDriveResponse) {
        if (cloudDriveResponse == null) {
            return -1;
        }
        if (cloudDriveResponse == this) {
            return 0;
        }
        if (!(cloudDriveResponse instanceof GetFileProgressResponse)) {
            return 1;
        }
        GetFileProgressResponse getFileProgressResponse = (GetFileProgressResponse) cloudDriveResponse;
        int compare = ObjectComparator.compare(getResumeState(), getFileProgressResponse.getResumeState());
        if (compare != 0) {
            return compare;
        }
        int compare2 = ObjectComparator.compare(getNodeId(), getFileProgressResponse.getNodeId());
        if (compare2 != 0) {
            return compare2;
        }
        int compare3 = ObjectComparator.compare(getContentLink(), getFileProgressResponse.getContentLink());
        if (compare3 != 0) {
            return compare3;
        }
        int compare4 = ObjectComparator.compare(Long.valueOf(getBytesReceived()), Long.valueOf(getFileProgressResponse.getBytesReceived()));
        if (compare4 != 0) {
            return compare4;
        }
        int compare5 = ObjectComparator.compare(getExpectedMd5(), getFileProgressResponse.getExpectedMd5());
        if (compare5 != 0) {
            return compare5;
        }
        int compare6 = ObjectComparator.compare(getStartedTime(), getFileProgressResponse.getStartedTime());
        if (compare6 != 0) {
            return compare6;
        }
        int compare7 = ObjectComparator.compare(Boolean.valueOf(isProgressAvailable()), Boolean.valueOf(getFileProgressResponse.isProgressAvailable()));
        if (compare7 == 0) {
            return 0;
        }
        return compare7;
    }
}
