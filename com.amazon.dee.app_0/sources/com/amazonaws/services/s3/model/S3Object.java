package com.amazonaws.services.s3.model;

import com.amazonaws.services.s3.internal.S3RequesterChargedResult;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class S3Object implements Closeable, Serializable, S3RequesterChargedResult {
    private boolean isRequesterCharged;
    private transient S3ObjectInputStream objectContent;
    private String redirectLocation;
    private Integer taggingCount;
    private String key = null;
    private String bucketName = null;
    private ObjectMetadata metadata = new ObjectMetadata();

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (getObjectContent() != null) {
            getObjectContent().close();
        }
    }

    public String getBucketName() {
        return this.bucketName;
    }

    public String getKey() {
        return this.key;
    }

    public S3ObjectInputStream getObjectContent() {
        return this.objectContent;
    }

    public ObjectMetadata getObjectMetadata() {
        return this.metadata;
    }

    public String getRedirectLocation() {
        return this.redirectLocation;
    }

    public Integer getTaggingCount() {
        return this.taggingCount;
    }

    @Override // com.amazonaws.services.s3.internal.S3RequesterChargedResult
    public boolean isRequesterCharged() {
        return this.isRequesterCharged;
    }

    public void setBucketName(String str) {
        this.bucketName = str;
    }

    public void setKey(String str) {
        this.key = str;
    }

    public void setObjectContent(S3ObjectInputStream s3ObjectInputStream) {
        this.objectContent = s3ObjectInputStream;
    }

    public void setObjectMetadata(ObjectMetadata objectMetadata) {
        this.metadata = objectMetadata;
    }

    public void setRedirectLocation(String str) {
        this.redirectLocation = str;
    }

    @Override // com.amazonaws.services.s3.internal.S3RequesterChargedResult
    public void setRequesterCharged(boolean z) {
        this.isRequesterCharged = z;
    }

    public void setTaggingCount(Integer num) {
        this.taggingCount = num;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("S3Object [key=");
        outline107.append(getKey());
        outline107.append(",bucket=");
        String str = this.bucketName;
        if (str == null) {
            str = "<Unknown>";
        }
        return GeneratedOutlineSupport1.outline91(outline107, str, "]");
    }

    public void setObjectContent(InputStream inputStream) {
        S3ObjectInputStream s3ObjectInputStream = this.objectContent;
        setObjectContent(new S3ObjectInputStream(inputStream, s3ObjectInputStream != null ? s3ObjectInputStream.getHttpRequest() : null));
    }
}
