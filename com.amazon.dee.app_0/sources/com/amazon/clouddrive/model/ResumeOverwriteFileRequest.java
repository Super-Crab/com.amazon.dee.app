package com.amazon.clouddrive.model;

import java.io.InputStream;
/* loaded from: classes11.dex */
public class ResumeOverwriteFileRequest extends ResumeFileRequest {
    public ResumeOverwriteFileRequest(String str, InputStream inputStream, long j, long j2) {
        setNodeId(str);
        setInputStream(inputStream);
        setResumeFromPosition(j);
        setContentLength(j2);
    }

    @Override // com.amazon.clouddrive.model.ResumeFileRequest, com.amazon.clouddrive.model.StreamFileRequest
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof ResumeOverwriteFileRequest) && compareTo((CloudDriveRequest) ((ResumeOverwriteFileRequest) obj)) == 0;
    }

    public ResumeOverwriteFileRequest withBlockSize(int i) {
        setBlockSize(i);
        return this;
    }

    public ResumeOverwriteFileRequest withChunkSize(int i) {
        setChunkSize(i);
        return this;
    }

    public ResumeOverwriteFileRequest withChunkedStreaming(boolean z) {
        setChunkedStreaming(z);
        return this;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.model.ResumeFileRequest, com.amazon.clouddrive.model.StreamFileRequest, java.lang.Comparable
    public int compareTo(CloudDriveRequest cloudDriveRequest) {
        if (cloudDriveRequest == null) {
            return -1;
        }
        if (cloudDriveRequest == this) {
            return 0;
        }
        if (cloudDriveRequest instanceof ResumeOverwriteFileRequest) {
            return super.compareTo(cloudDriveRequest);
        }
        return 1;
    }
}
