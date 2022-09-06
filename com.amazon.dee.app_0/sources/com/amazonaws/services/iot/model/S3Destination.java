package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class S3Destination implements Serializable {
    private String bucket;
    private String prefix;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof S3Destination)) {
            return false;
        }
        S3Destination s3Destination = (S3Destination) obj;
        if ((s3Destination.getBucket() == null) ^ (getBucket() == null)) {
            return false;
        }
        if (s3Destination.getBucket() != null && !s3Destination.getBucket().equals(getBucket())) {
            return false;
        }
        if ((s3Destination.getPrefix() == null) ^ (getPrefix() == null)) {
            return false;
        }
        return s3Destination.getPrefix() == null || s3Destination.getPrefix().equals(getPrefix());
    }

    public String getBucket() {
        return this.bucket;
    }

    public String getPrefix() {
        return this.prefix;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getBucket() == null ? 0 : getBucket().hashCode()) + 31) * 31;
        if (getPrefix() != null) {
            i = getPrefix().hashCode();
        }
        return hashCode + i;
    }

    public void setBucket(String str) {
        this.bucket = str;
    }

    public void setPrefix(String str) {
        this.prefix = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getBucket() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("bucket: ");
            outline1072.append(getBucket());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getPrefix() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("prefix: ");
            outline1073.append(getPrefix());
            outline107.append(outline1073.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public S3Destination withBucket(String str) {
        this.bucket = str;
        return this;
    }

    public S3Destination withPrefix(String str) {
        this.prefix = str;
        return this;
    }
}
