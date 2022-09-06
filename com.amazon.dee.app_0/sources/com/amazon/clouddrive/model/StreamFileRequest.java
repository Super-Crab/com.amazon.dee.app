package com.amazon.clouddrive.model;

import java.io.InputStream;
/* loaded from: classes11.dex */
public class StreamFileRequest implements CloudDriveRequest {
    private long mContentLength;
    private InputStream mInputStream;
    private String mNodeID;
    private int mBlockSize = 32768;
    private boolean mUseChunkedStreaming = false;
    private int mChunkSize = 0;

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof StreamFileRequest) && compareTo((CloudDriveRequest) ((StreamFileRequest) obj)) == 0;
    }

    public int getBlockSize() {
        return this.mBlockSize;
    }

    public int getChunkSize() {
        return this.mChunkSize;
    }

    public long getContentLength() {
        return this.mContentLength;
    }

    public InputStream getInputStream() {
        return this.mInputStream;
    }

    public String getNodeId() {
        return this.mNodeID;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = getClass().getSimpleName().hashCode() + (getNodeId() == null ? 0 : getNodeId().hashCode());
        if (getInputStream() != null) {
            i = getInputStream().hashCode();
        }
        return getChunkSize() + ((int) (getContentLength() + getBlockSize() + hashCode + i)) + (useChunkedStreaming() ? 1 : 0);
    }

    public void setBlockSize(int i) {
        if (i >= 1) {
            this.mBlockSize = i;
            return;
        }
        throw new IllegalArgumentException("The block size must be at the very least 1.");
    }

    public void setChunkSize(int i) {
        this.mChunkSize = i;
    }

    public void setChunkedStreaming(boolean z) {
        this.mUseChunkedStreaming = z;
    }

    public void setContentLength(long j) {
        this.mContentLength = j;
    }

    public void setInputStream(InputStream inputStream) {
        this.mInputStream = inputStream;
    }

    public void setNodeId(String str) {
        this.mNodeID = str;
    }

    public boolean useChunkedStreaming() {
        return this.mUseChunkedStreaming;
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
        if (!(cloudDriveRequest instanceof StreamFileRequest)) {
            return 1;
        }
        StreamFileRequest streamFileRequest = (StreamFileRequest) cloudDriveRequest;
        int compare = ObjectComparator.compare(getNodeId(), streamFileRequest.getNodeId());
        if (compare != 0) {
            return compare;
        }
        int compare2 = ObjectComparator.compare(getInputStream(), streamFileRequest.getInputStream());
        if (compare2 != 0) {
            return compare2;
        }
        int compare3 = ObjectComparator.compare(Integer.valueOf(getBlockSize()), Integer.valueOf(streamFileRequest.getBlockSize()));
        if (compare3 != 0) {
            return compare3;
        }
        int compare4 = ObjectComparator.compare(Long.valueOf(getContentLength()), Long.valueOf(streamFileRequest.getContentLength()));
        if (compare4 != 0) {
            return compare4;
        }
        int compare5 = ObjectComparator.compare(Boolean.valueOf(useChunkedStreaming()), Boolean.valueOf(streamFileRequest.useChunkedStreaming()));
        if (compare5 != 0) {
            return compare5;
        }
        int compare6 = ObjectComparator.compare(Integer.valueOf(getChunkSize()), Integer.valueOf(streamFileRequest.getChunkSize()));
        if (compare6 == 0) {
            return 0;
        }
        return compare6;
    }
}
