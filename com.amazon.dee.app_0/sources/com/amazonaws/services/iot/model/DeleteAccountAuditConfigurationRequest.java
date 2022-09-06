package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class DeleteAccountAuditConfigurationRequest extends AmazonWebServiceRequest implements Serializable {
    private Boolean deleteScheduledAudits;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DeleteAccountAuditConfigurationRequest)) {
            return false;
        }
        DeleteAccountAuditConfigurationRequest deleteAccountAuditConfigurationRequest = (DeleteAccountAuditConfigurationRequest) obj;
        if ((deleteAccountAuditConfigurationRequest.getDeleteScheduledAudits() == null) ^ (getDeleteScheduledAudits() == null)) {
            return false;
        }
        return deleteAccountAuditConfigurationRequest.getDeleteScheduledAudits() == null || deleteAccountAuditConfigurationRequest.getDeleteScheduledAudits().equals(getDeleteScheduledAudits());
    }

    public Boolean getDeleteScheduledAudits() {
        return this.deleteScheduledAudits;
    }

    public int hashCode() {
        return 31 + (getDeleteScheduledAudits() == null ? 0 : getDeleteScheduledAudits().hashCode());
    }

    public Boolean isDeleteScheduledAudits() {
        return this.deleteScheduledAudits;
    }

    public void setDeleteScheduledAudits(Boolean bool) {
        this.deleteScheduledAudits = bool;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getDeleteScheduledAudits() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("deleteScheduledAudits: ");
            outline1072.append(getDeleteScheduledAudits());
            outline107.append(outline1072.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public DeleteAccountAuditConfigurationRequest withDeleteScheduledAudits(Boolean bool) {
        this.deleteScheduledAudits = bool;
        return this;
    }
}
