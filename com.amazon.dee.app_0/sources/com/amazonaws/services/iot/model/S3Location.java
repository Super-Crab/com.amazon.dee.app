package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class S3Location implements Serializable {
    private String bucket;
    private String key;
    private String version;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof S3Location)) {
            return false;
        }
        S3Location s3Location = (S3Location) obj;
        if ((s3Location.getBucket() == null) ^ (getBucket() == null)) {
            return false;
        }
        if (s3Location.getBucket() != null && !s3Location.getBucket().equals(getBucket())) {
            return false;
        }
        if ((s3Location.getKey() == null) ^ (getKey() == null)) {
            return false;
        }
        if (s3Location.getKey() != null && !s3Location.getKey().equals(getKey())) {
            return false;
        }
        if ((s3Location.getVersion() == null) ^ (getVersion() == null)) {
            return false;
        }
        return s3Location.getVersion() == null || s3Location.getVersion().equals(getVersion());
    }

    public String getBucket() {
        return this.bucket;
    }

    public String getKey() {
        return this.key;
    }

    public String getVersion() {
        return this.version;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((getBucket() == null ? 0 : getBucket().hashCode()) + 31) * 31) + (getKey() == null ? 0 : getKey().hashCode())) * 31;
        if (getVersion() != null) {
            i = getVersion().hashCode();
        }
        return hashCode + i;
    }

    public void setBucket(String str) {
        this.bucket = str;
    }

    public void setKey(String str) {
        this.key = str;
    }

    public void setVersion(String str) {
        this.version = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getBucket() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("bucket: ");
            outline1072.append(getBucket());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getKey() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("key: ");
            outline1073.append(getKey());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getVersion() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("version: ");
            outline1074.append(getVersion());
            outline107.append(outline1074.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public S3Location withBucket(String str) {
        this.bucket = str;
        return this;
    }

    public S3Location withKey(String str) {
        this.key = str;
        return this;
    }

    public S3Location withVersion(String str) {
        this.version = str;
        return this;
    }
}
