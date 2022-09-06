package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.Date;
/* loaded from: classes13.dex */
public class OTAUpdateSummary implements Serializable {
    private Date creationDate;
    private String otaUpdateArn;
    private String otaUpdateId;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof OTAUpdateSummary)) {
            return false;
        }
        OTAUpdateSummary oTAUpdateSummary = (OTAUpdateSummary) obj;
        if ((oTAUpdateSummary.getOtaUpdateId() == null) ^ (getOtaUpdateId() == null)) {
            return false;
        }
        if (oTAUpdateSummary.getOtaUpdateId() != null && !oTAUpdateSummary.getOtaUpdateId().equals(getOtaUpdateId())) {
            return false;
        }
        if ((oTAUpdateSummary.getOtaUpdateArn() == null) ^ (getOtaUpdateArn() == null)) {
            return false;
        }
        if (oTAUpdateSummary.getOtaUpdateArn() != null && !oTAUpdateSummary.getOtaUpdateArn().equals(getOtaUpdateArn())) {
            return false;
        }
        if ((oTAUpdateSummary.getCreationDate() == null) ^ (getCreationDate() == null)) {
            return false;
        }
        return oTAUpdateSummary.getCreationDate() == null || oTAUpdateSummary.getCreationDate().equals(getCreationDate());
    }

    public Date getCreationDate() {
        return this.creationDate;
    }

    public String getOtaUpdateArn() {
        return this.otaUpdateArn;
    }

    public String getOtaUpdateId() {
        return this.otaUpdateId;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((getOtaUpdateId() == null ? 0 : getOtaUpdateId().hashCode()) + 31) * 31) + (getOtaUpdateArn() == null ? 0 : getOtaUpdateArn().hashCode())) * 31;
        if (getCreationDate() != null) {
            i = getCreationDate().hashCode();
        }
        return hashCode + i;
    }

    public void setCreationDate(Date date) {
        this.creationDate = date;
    }

    public void setOtaUpdateArn(String str) {
        this.otaUpdateArn = str;
    }

    public void setOtaUpdateId(String str) {
        this.otaUpdateId = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getOtaUpdateId() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("otaUpdateId: ");
            outline1072.append(getOtaUpdateId());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getOtaUpdateArn() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("otaUpdateArn: ");
            outline1073.append(getOtaUpdateArn());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getCreationDate() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("creationDate: ");
            outline1074.append(getCreationDate());
            outline107.append(outline1074.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public OTAUpdateSummary withCreationDate(Date date) {
        this.creationDate = date;
        return this;
    }

    public OTAUpdateSummary withOtaUpdateArn(String str) {
        this.otaUpdateArn = str;
        return this;
    }

    public OTAUpdateSummary withOtaUpdateId(String str) {
        this.otaUpdateId = str;
        return this;
    }
}
