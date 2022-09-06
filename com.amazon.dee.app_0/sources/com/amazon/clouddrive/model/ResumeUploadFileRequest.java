package com.amazon.clouddrive.model;

import java.io.InputStream;
/* loaded from: classes11.dex */
public class ResumeUploadFileRequest extends ResumeFileRequest {
    public ResumeUploadFileRequest(String str, InputStream inputStream, long j, long j2) {
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
        return (obj instanceof ResumeUploadFileRequest) && compareTo((CloudDriveRequest) ((ResumeUploadFileRequest) obj)) == 0;
    }

    public ResumeUploadFileRequest withBlockSize(int i) {
        setBlockSize(i);
        return this;
    }

    public ResumeUploadFileRequest withChunkSize(int i) {
        setChunkSize(i);
        return this;
    }

    public ResumeUploadFileRequest withChunkedStreaming(boolean z) {
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
        if (cloudDriveRequest instanceof ResumeUploadFileRequest) {
            return super.compareTo(cloudDriveRequest);
        }
        return 1;
    }
}
