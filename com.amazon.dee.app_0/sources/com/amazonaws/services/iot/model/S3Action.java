package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class S3Action implements Serializable {
    private String bucketName;
    private String cannedAcl;
    private String key;
    private String roleArn;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof S3Action)) {
            return false;
        }
        S3Action s3Action = (S3Action) obj;
        if ((s3Action.getRoleArn() == null) ^ (getRoleArn() == null)) {
            return false;
        }
        if (s3Action.getRoleArn() != null && !s3Action.getRoleArn().equals(getRoleArn())) {
            return false;
        }
        if ((s3Action.getBucketName() == null) ^ (getBucketName() == null)) {
            return false;
        }
        if (s3Action.getBucketName() != null && !s3Action.getBucketName().equals(getBucketName())) {
            return false;
        }
        if ((s3Action.getKey() == null) ^ (getKey() == null)) {
            return false;
        }
        if (s3Action.getKey() != null && !s3Action.getKey().equals(getKey())) {
            return false;
        }
        if ((s3Action.getCannedAcl() == null) ^ (getCannedAcl() == null)) {
            return false;
        }
        return s3Action.getCannedAcl() == null || s3Action.getCannedAcl().equals(getCannedAcl());
    }

    public String getBucketName() {
        return this.bucketName;
    }

    public String getCannedAcl() {
        return this.cannedAcl;
    }

    public String getKey() {
        return this.key;
    }

    public String getRoleArn() {
        return this.roleArn;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((getRoleArn() == null ? 0 : getRoleArn().hashCode()) + 31) * 31) + (getBucketName() == null ? 0 : getBucketName().hashCode())) * 31) + (getKey() == null ? 0 : getKey().hashCode())) * 31;
        if (getCannedAcl() != null) {
            i = getCannedAcl().hashCode();
        }
        return hashCode + i;
    }

    public void setBucketName(String str) {
        this.bucketName = str;
    }

    public void setCannedAcl(String str) {
        this.cannedAcl = str;
    }

    public void setKey(String str) {
        this.key = str;
    }

    public void setRoleArn(String str) {
        this.roleArn = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getRoleArn() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("roleArn: ");
            outline1072.append(getRoleArn());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getBucketName() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("bucketName: ");
            outline1073.append(getBucketName());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getKey() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("key: ");
            outline1074.append(getKey());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getCannedAcl() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("cannedAcl: ");
            outline1075.append(getCannedAcl());
            outline107.append(outline1075.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public S3Action withBucketName(String str) {
        this.bucketName = str;
        return this;
    }

    public S3Action withCannedAcl(String str) {
        this.cannedAcl = str;
        return this;
    }

    public S3Action withKey(String str) {
        this.key = str;
        return this;
    }

    public S3Action withRoleArn(String str) {
        this.roleArn = str;
        return this;
    }

    public void setCannedAcl(CannedAccessControlList cannedAccessControlList) {
        this.cannedAcl = cannedAccessControlList.toString();
    }

    public S3Action withCannedAcl(CannedAccessControlList cannedAccessControlList) {
        this.cannedAcl = cannedAccessControlList.toString();
        return this;
    }
}
