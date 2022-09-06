package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class RegistrationConfig implements Serializable {
    private String roleArn;
    private String templateBody;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof RegistrationConfig)) {
            return false;
        }
        RegistrationConfig registrationConfig = (RegistrationConfig) obj;
        if ((registrationConfig.getTemplateBody() == null) ^ (getTemplateBody() == null)) {
            return false;
        }
        if (registrationConfig.getTemplateBody() != null && !registrationConfig.getTemplateBody().equals(getTemplateBody())) {
            return false;
        }
        if ((registrationConfig.getRoleArn() == null) ^ (getRoleArn() == null)) {
            return false;
        }
        return registrationConfig.getRoleArn() == null || registrationConfig.getRoleArn().equals(getRoleArn());
    }

    public String getRoleArn() {
        return this.roleArn;
    }

    public String getTemplateBody() {
        return this.templateBody;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getTemplateBody() == null ? 0 : getTemplateBody().hashCode()) + 31) * 31;
        if (getRoleArn() != null) {
            i = getRoleArn().hashCode();
        }
        return hashCode + i;
    }

    public void setRoleArn(String str) {
        this.roleArn = str;
    }

    public void setTemplateBody(String str) {
        this.templateBody = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getTemplateBody() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("templateBody: ");
            outline1072.append(getTemplateBody());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getRoleArn() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("roleArn: ");
            outline1073.append(getRoleArn());
            outline107.append(outline1073.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public RegistrationConfig withRoleArn(String str) {
        this.roleArn = str;
        return this;
    }

    public RegistrationConfig withTemplateBody(String str) {
        this.templateBody = str;
        return this;
    }
}
