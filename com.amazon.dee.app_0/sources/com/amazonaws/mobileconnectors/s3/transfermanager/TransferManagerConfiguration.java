package com.amazonaws.mobileconnectors.s3.transfermanager;

import com.google.android.exoplayer2.upstream.cache.CacheDataSink;
@Deprecated
/* loaded from: classes13.dex */
public class TransferManagerConfiguration {
    private static final long DEFAULT_MINIMUM_COPY_PART_SIZE = 104857600;
    private static final int DEFAULT_MINIMUM_UPLOAD_PART_SIZE = 5242880;
    private static final long DEFAULT_MULTIPART_COPY_THRESHOLD = 5368709120L;
    private static final long DEFAULT_MULTIPART_UPLOAD_THRESHOLD = 16777216;
    private long minimumUploadPartSize = CacheDataSink.DEFAULT_FRAGMENT_SIZE;
    private long multipartUploadThreshold = DEFAULT_MULTIPART_UPLOAD_THRESHOLD;
    private long multipartCopyThreshold = DEFAULT_MULTIPART_COPY_THRESHOLD;
    private long multipartCopyPartSize = 104857600;

    public long getMinimumUploadPartSize() {
        return this.minimumUploadPartSize;
    }

    public long getMultipartCopyPartSize() {
        return this.multipartCopyPartSize;
    }

    public long getMultipartCopyThreshold() {
        return this.multipartCopyThreshold;
    }

    public long getMultipartUploadThreshold() {
        return this.multipartUploadThreshold;
    }

    public void setMinimumUploadPartSize(long j) {
        this.minimumUploadPartSize = j;
    }

    public void setMultipartCopyPartSize(long j) {
        this.multipartCopyPartSize = j;
    }

    public void setMultipartCopyThreshold(long j) {
        this.multipartCopyThreshold = j;
    }

    public void setMultipartUploadThreshold(long j) {
        this.multipartUploadThreshold = j;
    }
}
