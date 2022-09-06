package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class SecurityProfileTarget implements Serializable {
    private String arn;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof SecurityProfileTarget)) {
            return false;
        }
        SecurityProfileTarget securityProfileTarget = (SecurityProfileTarget) obj;
        if ((securityProfileTarget.getArn() == null) ^ (getArn() == null)) {
            return false;
        }
        return securityProfileTarget.getArn() == null || securityProfileTarget.getArn().equals(getArn());
    }

    public String getArn() {
        return this.arn;
    }

    public int hashCode() {
        return 31 + (getArn() == null ? 0 : getArn().hashCode());
    }

    public void setArn(String str) {
        this.arn = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getArn() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("arn: ");
            outline1072.append(getArn());
            outline107.append(outline1072.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public SecurityProfileTarget withArn(String str) {
        this.arn = str;
        return this;
    }
}
