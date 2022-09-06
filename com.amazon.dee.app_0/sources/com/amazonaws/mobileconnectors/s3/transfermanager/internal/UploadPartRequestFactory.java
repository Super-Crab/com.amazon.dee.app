package com.amazonaws.mobileconnectors.s3.transfermanager.internal;

import com.amazonaws.services.s3.internal.InputSubstream;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.SSECustomerKey;
import com.amazonaws.services.s3.model.UploadPartRequest;
import java.io.File;
/* loaded from: classes13.dex */
public class UploadPartRequestFactory {
    private final String bucketName;
    private final File file;
    private final String key;
    private final long optimalPartSize;
    private final PutObjectRequest putObjectRequest;
    private long remainingBytes;
    private SSECustomerKey sseCustomerKey;
    private final String uploadId;
    private int partNumber = 1;
    private long offset = 0;

    public UploadPartRequestFactory(PutObjectRequest putObjectRequest, String str, long j) {
        this.putObjectRequest = putObjectRequest;
        this.uploadId = str;
        this.optimalPartSize = j;
        this.bucketName = putObjectRequest.getBucketName();
        this.key = putObjectRequest.getKey();
        this.file = TransferManagerUtils.getRequestFile(putObjectRequest);
        this.remainingBytes = TransferManagerUtils.getContentLength(putObjectRequest);
        this.sseCustomerKey = putObjectRequest.getSSECustomerKey();
    }

    public synchronized UploadPartRequest getNextUploadPartRequest() {
        UploadPartRequest withPartSize;
        long min = Math.min(this.optimalPartSize, this.remainingBytes);
        boolean z = this.remainingBytes - min <= 0;
        if (this.putObjectRequest.getInputStream() != null) {
            UploadPartRequest withInputStream = new UploadPartRequest().withBucketName(this.bucketName).withKey(this.key).withUploadId(this.uploadId).withInputStream(new InputSubstream(this.putObjectRequest.getInputStream(), 0L, min, z));
            int i = this.partNumber;
            this.partNumber = i + 1;
            withPartSize = withInputStream.withPartNumber(i).withPartSize(min);
        } else {
            UploadPartRequest withFileOffset = new UploadPartRequest().withBucketName(this.bucketName).withKey(this.key).withUploadId(this.uploadId).withFile(this.file).withFileOffset(this.offset);
            int i2 = this.partNumber;
            this.partNumber = i2 + 1;
            withPartSize = withFileOffset.withPartNumber(i2).withPartSize(min);
        }
        if (this.sseCustomerKey != null) {
            withPartSize.setSSECustomerKey(this.sseCustomerKey);
        }
        this.offset += min;
        this.remainingBytes -= min;
        withPartSize.setLastPart(z);
        withPartSize.setGeneralProgressListener(this.putObjectRequest.getGeneralProgressListener());
        return withPartSize;
    }

    public synchronized boolean hasMoreRequests() {
        return this.remainingBytes > 0;
    }
}
