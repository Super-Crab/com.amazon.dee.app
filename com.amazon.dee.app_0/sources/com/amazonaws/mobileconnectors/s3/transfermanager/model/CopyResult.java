package com.amazonaws.mobileconnectors.s3.transfermanager.model;
@Deprecated
/* loaded from: classes13.dex */
public class CopyResult {
    private String destinationBucketName;
    private String destinationKey;
    private String eTag;
    private String sourceBucketName;
    private String sourceKey;
    private String versionId;

    public String getDestinationBucketName() {
        return this.destinationBucketName;
    }

    public String getDestinationKey() {
        return this.destinationKey;
    }

    public String getETag() {
        return this.eTag;
    }

    public String getSourceBucketName() {
        return this.sourceBucketName;
    }

    public String getSourceKey() {
        return this.sourceKey;
    }

    public String getVersionId() {
        return this.versionId;
    }

    public void setDestinationBucketName(String str) {
        this.destinationBucketName = str;
    }

    public void setDestinationKey(String str) {
        this.destinationKey = str;
    }

    public void setETag(String str) {
        this.eTag = str;
    }

    public void setSourceBucketName(String str) {
        this.sourceBucketName = str;
    }

    public void setSourceKey(String str) {
        this.sourceKey = str;
    }

    public void setVersionId(String str) {
        this.versionId = str;
    }
}
