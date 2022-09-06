package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class DeleteOTAUpdateRequest extends AmazonWebServiceRequest implements Serializable {
    private Boolean deleteStream;
    private Boolean forceDeleteAWSJob;
    private String otaUpdateId;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DeleteOTAUpdateRequest)) {
            return false;
        }
        DeleteOTAUpdateRequest deleteOTAUpdateRequest = (DeleteOTAUpdateRequest) obj;
        if ((deleteOTAUpdateRequest.getOtaUpdateId() == null) ^ (getOtaUpdateId() == null)) {
            return false;
        }
        if (deleteOTAUpdateRequest.getOtaUpdateId() != null && !deleteOTAUpdateRequest.getOtaUpdateId().equals(getOtaUpdateId())) {
            return false;
        }
        if ((deleteOTAUpdateRequest.getDeleteStream() == null) ^ (getDeleteStream() == null)) {
            return false;
        }
        if (deleteOTAUpdateRequest.getDeleteStream() != null && !deleteOTAUpdateRequest.getDeleteStream().equals(getDeleteStream())) {
            return false;
        }
        if ((deleteOTAUpdateRequest.getForceDeleteAWSJob() == null) ^ (getForceDeleteAWSJob() == null)) {
            return false;
        }
        return deleteOTAUpdateRequest.getForceDeleteAWSJob() == null || deleteOTAUpdateRequest.getForceDeleteAWSJob().equals(getForceDeleteAWSJob());
    }

    public Boolean getDeleteStream() {
        return this.deleteStream;
    }

    public Boolean getForceDeleteAWSJob() {
        return this.forceDeleteAWSJob;
    }

    public String getOtaUpdateId() {
        return this.otaUpdateId;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((getOtaUpdateId() == null ? 0 : getOtaUpdateId().hashCode()) + 31) * 31) + (getDeleteStream() == null ? 0 : getDeleteStream().hashCode())) * 31;
        if (getForceDeleteAWSJob() != null) {
            i = getForceDeleteAWSJob().hashCode();
        }
        return hashCode + i;
    }

    public Boolean isDeleteStream() {
        return this.deleteStream;
    }

    public Boolean isForceDeleteAWSJob() {
        return this.forceDeleteAWSJob;
    }

    public void setDeleteStream(Boolean bool) {
        this.deleteStream = bool;
    }

    public void setForceDeleteAWSJob(Boolean bool) {
        this.forceDeleteAWSJob = bool;
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
        if (getDeleteStream() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("deleteStream: ");
            outline1073.append(getDeleteStream());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getForceDeleteAWSJob() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("forceDeleteAWSJob: ");
            outline1074.append(getForceDeleteAWSJob());
            outline107.append(outline1074.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public DeleteOTAUpdateRequest withDeleteStream(Boolean bool) {
        this.deleteStream = bool;
        return this;
    }

    public DeleteOTAUpdateRequest withForceDeleteAWSJob(Boolean bool) {
        this.forceDeleteAWSJob = bool;
        return this;
    }

    public DeleteOTAUpdateRequest withOtaUpdateId(String str) {
        this.otaUpdateId = str;
        return this;
    }
}
