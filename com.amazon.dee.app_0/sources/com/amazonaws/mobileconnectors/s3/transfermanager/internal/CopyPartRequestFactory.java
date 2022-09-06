package com.amazonaws.mobileconnectors.s3.transfermanager.internal;

import com.amazonaws.services.s3.model.CopyObjectRequest;
import com.amazonaws.services.s3.model.CopyPartRequest;
/* loaded from: classes13.dex */
public class CopyPartRequestFactory {
    private final CopyObjectRequest copyObjectRequest;
    private final long optimalPartSize;
    private long remainingBytes;
    private final String uploadId;
    private int partNumber = 1;
    private long offset = 0;

    public CopyPartRequestFactory(CopyObjectRequest copyObjectRequest, String str, long j, long j2) {
        this.copyObjectRequest = copyObjectRequest;
        this.uploadId = str;
        this.optimalPartSize = j;
        this.remainingBytes = j2;
    }

    private void setOtherMetadataInRequest(CopyPartRequest copyPartRequest) {
        if (this.copyObjectRequest.getMatchingETagConstraints() != null) {
            copyPartRequest.setMatchingETagConstraints(this.copyObjectRequest.getMatchingETagConstraints());
        }
        if (this.copyObjectRequest.getModifiedSinceConstraint() != null) {
            copyPartRequest.setModifiedSinceConstraint(this.copyObjectRequest.getModifiedSinceConstraint());
        }
        if (this.copyObjectRequest.getNonmatchingETagConstraints() != null) {
            copyPartRequest.setNonmatchingETagConstraints(this.copyObjectRequest.getNonmatchingETagConstraints());
        }
        if (this.copyObjectRequest.getSourceVersionId() != null) {
            copyPartRequest.setSourceVersionId(this.copyObjectRequest.getSourceVersionId());
        }
        if (this.copyObjectRequest.getUnmodifiedSinceConstraint() != null) {
            copyPartRequest.setUnmodifiedSinceConstraint(this.copyObjectRequest.getUnmodifiedSinceConstraint());
        }
    }

    public synchronized CopyPartRequest getNextCopyPartRequest() {
        CopyPartRequest withDestinationSSECustomerKey;
        long min = Math.min(this.optimalPartSize, this.remainingBytes);
        CopyPartRequest withUploadId = new CopyPartRequest().withSourceBucketName(this.copyObjectRequest.getSourceBucketName()).withSourceKey(this.copyObjectRequest.getSourceKey()).withUploadId(this.uploadId);
        int i = this.partNumber;
        this.partNumber = i + 1;
        withDestinationSSECustomerKey = withUploadId.withPartNumber(i).withDestinationBucketName(this.copyObjectRequest.getDestinationBucketName()).withDestinationKey(this.copyObjectRequest.getDestinationKey()).withSourceVersionId(this.copyObjectRequest.getSourceVersionId()).withFirstByte(new Long(this.offset)).withLastByte(new Long((this.offset + min) - 1)).withSourceSSECustomerKey(this.copyObjectRequest.getSourceSSECustomerKey()).withDestinationSSECustomerKey(this.copyObjectRequest.getDestinationSSECustomerKey());
        setOtherMetadataInRequest(withDestinationSSECustomerKey);
        this.offset += min;
        this.remainingBytes -= min;
        return withDestinationSSECustomerKey;
    }

    public synchronized boolean hasMoreRequests() {
        return this.remainingBytes > 0;
    }
}
