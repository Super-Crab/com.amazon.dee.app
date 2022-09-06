package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class CreateOTAUpdateResult implements Serializable {
    private String awsIotJobArn;
    private String awsIotJobId;
    private String otaUpdateArn;
    private String otaUpdateId;
    private String otaUpdateStatus;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof CreateOTAUpdateResult)) {
            return false;
        }
        CreateOTAUpdateResult createOTAUpdateResult = (CreateOTAUpdateResult) obj;
        if ((createOTAUpdateResult.getOtaUpdateId() == null) ^ (getOtaUpdateId() == null)) {
            return false;
        }
        if (createOTAUpdateResult.getOtaUpdateId() != null && !createOTAUpdateResult.getOtaUpdateId().equals(getOtaUpdateId())) {
            return false;
        }
        if ((createOTAUpdateResult.getAwsIotJobId() == null) ^ (getAwsIotJobId() == null)) {
            return false;
        }
        if (createOTAUpdateResult.getAwsIotJobId() != null && !createOTAUpdateResult.getAwsIotJobId().equals(getAwsIotJobId())) {
            return false;
        }
        if ((createOTAUpdateResult.getOtaUpdateArn() == null) ^ (getOtaUpdateArn() == null)) {
            return false;
        }
        if (createOTAUpdateResult.getOtaUpdateArn() != null && !createOTAUpdateResult.getOtaUpdateArn().equals(getOtaUpdateArn())) {
            return false;
        }
        if ((createOTAUpdateResult.getAwsIotJobArn() == null) ^ (getAwsIotJobArn() == null)) {
            return false;
        }
        if (createOTAUpdateResult.getAwsIotJobArn() != null && !createOTAUpdateResult.getAwsIotJobArn().equals(getAwsIotJobArn())) {
            return false;
        }
        if ((createOTAUpdateResult.getOtaUpdateStatus() == null) ^ (getOtaUpdateStatus() == null)) {
            return false;
        }
        return createOTAUpdateResult.getOtaUpdateStatus() == null || createOTAUpdateResult.getOtaUpdateStatus().equals(getOtaUpdateStatus());
    }

    public String getAwsIotJobArn() {
        return this.awsIotJobArn;
    }

    public String getAwsIotJobId() {
        return this.awsIotJobId;
    }

    public String getOtaUpdateArn() {
        return this.otaUpdateArn;
    }

    public String getOtaUpdateId() {
        return this.otaUpdateId;
    }

    public String getOtaUpdateStatus() {
        return this.otaUpdateStatus;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((((getOtaUpdateId() == null ? 0 : getOtaUpdateId().hashCode()) + 31) * 31) + (getAwsIotJobId() == null ? 0 : getAwsIotJobId().hashCode())) * 31) + (getOtaUpdateArn() == null ? 0 : getOtaUpdateArn().hashCode())) * 31) + (getAwsIotJobArn() == null ? 0 : getAwsIotJobArn().hashCode())) * 31;
        if (getOtaUpdateStatus() != null) {
            i = getOtaUpdateStatus().hashCode();
        }
        return hashCode + i;
    }

    public void setAwsIotJobArn(String str) {
        this.awsIotJobArn = str;
    }

    public void setAwsIotJobId(String str) {
        this.awsIotJobId = str;
    }

    public void setOtaUpdateArn(String str) {
        this.otaUpdateArn = str;
    }

    public void setOtaUpdateId(String str) {
        this.otaUpdateId = str;
    }

    public void setOtaUpdateStatus(String str) {
        this.otaUpdateStatus = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getOtaUpdateId() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("otaUpdateId: ");
            outline1072.append(getOtaUpdateId());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getAwsIotJobId() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("awsIotJobId: ");
            outline1073.append(getAwsIotJobId());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getOtaUpdateArn() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("otaUpdateArn: ");
            outline1074.append(getOtaUpdateArn());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getAwsIotJobArn() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("awsIotJobArn: ");
            outline1075.append(getAwsIotJobArn());
            outline1075.append(",");
            outline107.append(outline1075.toString());
        }
        if (getOtaUpdateStatus() != null) {
            StringBuilder outline1076 = GeneratedOutlineSupport1.outline107("otaUpdateStatus: ");
            outline1076.append(getOtaUpdateStatus());
            outline107.append(outline1076.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public CreateOTAUpdateResult withAwsIotJobArn(String str) {
        this.awsIotJobArn = str;
        return this;
    }

    public CreateOTAUpdateResult withAwsIotJobId(String str) {
        this.awsIotJobId = str;
        return this;
    }

    public CreateOTAUpdateResult withOtaUpdateArn(String str) {
        this.otaUpdateArn = str;
        return this;
    }

    public CreateOTAUpdateResult withOtaUpdateId(String str) {
        this.otaUpdateId = str;
        return this;
    }

    public CreateOTAUpdateResult withOtaUpdateStatus(String str) {
        this.otaUpdateStatus = str;
        return this;
    }

    public void setOtaUpdateStatus(OTAUpdateStatus oTAUpdateStatus) {
        this.otaUpdateStatus = oTAUpdateStatus.toString();
    }

    public CreateOTAUpdateResult withOtaUpdateStatus(OTAUpdateStatus oTAUpdateStatus) {
        this.otaUpdateStatus = oTAUpdateStatus.toString();
        return this;
    }
}
