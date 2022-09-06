package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class SecurityProfileIdentifier implements Serializable {
    private String arn;
    private String name;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof SecurityProfileIdentifier)) {
            return false;
        }
        SecurityProfileIdentifier securityProfileIdentifier = (SecurityProfileIdentifier) obj;
        if ((securityProfileIdentifier.getName() == null) ^ (getName() == null)) {
            return false;
        }
        if (securityProfileIdentifier.getName() != null && !securityProfileIdentifier.getName().equals(getName())) {
            return false;
        }
        if ((securityProfileIdentifier.getArn() == null) ^ (getArn() == null)) {
            return false;
        }
        return securityProfileIdentifier.getArn() == null || securityProfileIdentifier.getArn().equals(getArn());
    }

    public String getArn() {
        return this.arn;
    }

    public String getName() {
        return this.name;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getName() == null ? 0 : getName().hashCode()) + 31) * 31;
        if (getArn() != null) {
            i = getArn().hashCode();
        }
        return hashCode + i;
    }

    public void setArn(String str) {
        this.arn = str;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getName() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("name: ");
            outline1072.append(getName());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getArn() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("arn: ");
            outline1073.append(getArn());
            outline107.append(outline1073.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public SecurityProfileIdentifier withArn(String str) {
        this.arn = str;
        return this;
    }

    public SecurityProfileIdentifier withName(String str) {
        this.name = str;
        return this;
    }
}
