package com.amazon.clouddrive.model;

import java.io.OutputStream;
/* loaded from: classes11.dex */
public class DownloadFileRequest implements CloudDriveRequest {
    private String mId;
    private OutputStream mOutputStream;
    private String mTransform;
    private int mBlockSize = 32768;
    private int mResolution = -1;

    public DownloadFileRequest(String str, OutputStream outputStream) {
        this.mId = str;
        this.mOutputStream = outputStream;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof DownloadFileRequest) && compareTo((CloudDriveRequest) ((DownloadFileRequest) obj)) == 0;
    }

    public int getBlockSize() {
        return this.mBlockSize;
    }

    public String getId() {
        return this.mId;
    }

    public OutputStream getOutputStream() {
        return this.mOutputStream;
    }

    public int getResolution() {
        return this.mResolution;
    }

    public String getTransform() {
        return this.mTransform;
    }

    public boolean hasResolution() {
        return getResolution() > 0;
    }

    public boolean hasTransform() {
        return getTransform() != null;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = this.mOutputStream.hashCode() + (getId() == null ? 0 : getId().hashCode()) + 1;
        if (getTransform() != null) {
            i = getTransform().hashCode();
        }
        return getResolution() + hashCode + i;
    }

    public void setBlockSize(int i) {
        if (i >= 1) {
            this.mBlockSize = i;
            return;
        }
        throw new IllegalArgumentException("The block size must be at the very least 1.");
    }

    public void setId(String str) {
        this.mId = str;
    }

    public void setResolution(int i) {
        this.mResolution = i;
    }

    public void setTransform(String str) {
        this.mTransform = str;
    }

    public DownloadFileRequest withBlockSize(int i) {
        setBlockSize(i);
        return this;
    }

    public DownloadFileRequest withResolution(int i) {
        setResolution(i);
        return this;
    }

    public DownloadFileRequest withTransform(String str) {
        setTransform(str);
        return this;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.lang.Comparable
    public int compareTo(CloudDriveRequest cloudDriveRequest) {
        if (cloudDriveRequest == null) {
            return -1;
        }
        if (cloudDriveRequest == this) {
            return 0;
        }
        if (!(cloudDriveRequest instanceof DownloadFileRequest)) {
            return 1;
        }
        DownloadFileRequest downloadFileRequest = (DownloadFileRequest) cloudDriveRequest;
        int compare = ObjectComparator.compare(getId(), downloadFileRequest.getId());
        if (compare != 0) {
            return compare;
        }
        int compare2 = ObjectComparator.compare(getOutputStream(), downloadFileRequest.getOutputStream());
        if (compare2 != 0) {
            return compare2;
        }
        int compare3 = ObjectComparator.compare(Integer.valueOf(getBlockSize()), Integer.valueOf(downloadFileRequest.getBlockSize()));
        if (compare3 != 0) {
            return compare3;
        }
        int compare4 = ObjectComparator.compare(getTransform(), downloadFileRequest.getTransform());
        if (compare4 != 0) {
            return compare4;
        }
        int compare5 = ObjectComparator.compare(Integer.valueOf(getResolution()), Integer.valueOf(downloadFileRequest.getResolution()));
        if (compare5 == 0) {
            return 0;
        }
        return compare5;
    }
}
