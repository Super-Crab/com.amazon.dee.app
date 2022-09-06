package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class PresignedUrlConfig implements Serializable {
    private Long expiresInSec;
    private String roleArn;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof PresignedUrlConfig)) {
            return false;
        }
        PresignedUrlConfig presignedUrlConfig = (PresignedUrlConfig) obj;
        if ((presignedUrlConfig.getRoleArn() == null) ^ (getRoleArn() == null)) {
            return false;
        }
        if (presignedUrlConfig.getRoleArn() != null && !presignedUrlConfig.getRoleArn().equals(getRoleArn())) {
            return false;
        }
        if ((presignedUrlConfig.getExpiresInSec() == null) ^ (getExpiresInSec() == null)) {
            return false;
        }
        return presignedUrlConfig.getExpiresInSec() == null || presignedUrlConfig.getExpiresInSec().equals(getExpiresInSec());
    }

    public Long getExpiresInSec() {
        return this.expiresInSec;
    }

    public String getRoleArn() {
        return this.roleArn;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getRoleArn() == null ? 0 : getRoleArn().hashCode()) + 31) * 31;
        if (getExpiresInSec() != null) {
            i = getExpiresInSec().hashCode();
        }
        return hashCode + i;
    }

    public void setExpiresInSec(Long l) {
        this.expiresInSec = l;
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
        if (getExpiresInSec() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("expiresInSec: ");
            outline1073.append(getExpiresInSec());
            outline107.append(outline1073.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public PresignedUrlConfig withExpiresInSec(Long l) {
        this.expiresInSec = l;
        return this;
    }

    public PresignedUrlConfig withRoleArn(String str) {
        this.roleArn = str;
        return this;
    }
}
