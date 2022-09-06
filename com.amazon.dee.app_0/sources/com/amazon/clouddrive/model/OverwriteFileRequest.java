package com.amazon.clouddrive.model;

import java.io.InputStream;
/* loaded from: classes11.dex */
public class OverwriteFileRequest extends StreamFileRequest {
    private String mMD5;

    public OverwriteFileRequest(String str, InputStream inputStream, long j) {
        setNodeId(str);
        setInputStream(inputStream);
        setContentLength(j);
    }

    public int compareTo(StreamFileRequest streamFileRequest) {
        if (streamFileRequest == null) {
            return -1;
        }
        if (streamFileRequest == this) {
            return 0;
        }
        if (!(streamFileRequest instanceof OverwriteFileRequest)) {
            return 1;
        }
        OverwriteFileRequest overwriteFileRequest = (OverwriteFileRequest) streamFileRequest;
        int compare = ObjectComparator.compare(getNodeId(), overwriteFileRequest.getNodeId());
        if (compare != 0) {
            return compare;
        }
        int compare2 = ObjectComparator.compare(getMD5(), overwriteFileRequest.getMD5());
        return compare2 != 0 ? compare2 : super.compareTo((CloudDriveRequest) streamFileRequest);
    }

    @Override // com.amazon.clouddrive.model.StreamFileRequest
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof OverwriteFileRequest) && compareTo((StreamFileRequest) ((OverwriteFileRequest) obj)) == 0;
    }

    public String getMD5() {
        return this.mMD5;
    }

    @Override // com.amazon.clouddrive.model.StreamFileRequest
    public int hashCode() {
        return (getMD5() == null ? 0 : getMD5().hashCode()) ^ super.hashCode();
    }

    public void setMD5(String str) {
        this.mMD5 = str;
    }

    public OverwriteFileRequest withBlockSize(int i) {
        setBlockSize(i);
        return this;
    }

    public OverwriteFileRequest withChunkSize(int i) {
        setChunkSize(i);
        return this;
    }

    public OverwriteFileRequest withChunkedStreaming(boolean z) {
        setChunkedStreaming(z);
        return this;
    }

    public OverwriteFileRequest withMD5(String str) {
        setMD5(str);
        return this;
    }
}
