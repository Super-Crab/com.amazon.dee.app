package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class AuditCheckConfiguration implements Serializable {
    private Boolean enabled;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof AuditCheckConfiguration)) {
            return false;
        }
        AuditCheckConfiguration auditCheckConfiguration = (AuditCheckConfiguration) obj;
        if ((auditCheckConfiguration.getEnabled() == null) ^ (getEnabled() == null)) {
            return false;
        }
        return auditCheckConfiguration.getEnabled() == null || auditCheckConfiguration.getEnabled().equals(getEnabled());
    }

    public Boolean getEnabled() {
        return this.enabled;
    }

    public int hashCode() {
        return 31 + (getEnabled() == null ? 0 : getEnabled().hashCode());
    }

    public Boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(Boolean bool) {
        this.enabled = bool;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getEnabled() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("enabled: ");
            outline1072.append(getEnabled());
            outline107.append(outline1072.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public AuditCheckConfiguration withEnabled(Boolean bool) {
        this.enabled = bool;
        return this;
    }
}
