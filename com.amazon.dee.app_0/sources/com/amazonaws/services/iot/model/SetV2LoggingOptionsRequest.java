package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class SetV2LoggingOptionsRequest extends AmazonWebServiceRequest implements Serializable {
    private String defaultLogLevel;
    private Boolean disableAllLogs;
    private String roleArn;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof SetV2LoggingOptionsRequest)) {
            return false;
        }
        SetV2LoggingOptionsRequest setV2LoggingOptionsRequest = (SetV2LoggingOptionsRequest) obj;
        if ((setV2LoggingOptionsRequest.getRoleArn() == null) ^ (getRoleArn() == null)) {
            return false;
        }
        if (setV2LoggingOptionsRequest.getRoleArn() != null && !setV2LoggingOptionsRequest.getRoleArn().equals(getRoleArn())) {
            return false;
        }
        if ((setV2LoggingOptionsRequest.getDefaultLogLevel() == null) ^ (getDefaultLogLevel() == null)) {
            return false;
        }
        if (setV2LoggingOptionsRequest.getDefaultLogLevel() != null && !setV2LoggingOptionsRequest.getDefaultLogLevel().equals(getDefaultLogLevel())) {
            return false;
        }
        if ((setV2LoggingOptionsRequest.getDisableAllLogs() == null) ^ (getDisableAllLogs() == null)) {
            return false;
        }
        return setV2LoggingOptionsRequest.getDisableAllLogs() == null || setV2LoggingOptionsRequest.getDisableAllLogs().equals(getDisableAllLogs());
    }

    public String getDefaultLogLevel() {
        return this.defaultLogLevel;
    }

    public Boolean getDisableAllLogs() {
        return this.disableAllLogs;
    }

    public String getRoleArn() {
        return this.roleArn;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((getRoleArn() == null ? 0 : getRoleArn().hashCode()) + 31) * 31) + (getDefaultLogLevel() == null ? 0 : getDefaultLogLevel().hashCode())) * 31;
        if (getDisableAllLogs() != null) {
            i = getDisableAllLogs().hashCode();
        }
        return hashCode + i;
    }

    public Boolean isDisableAllLogs() {
        return this.disableAllLogs;
    }

    public void setDefaultLogLevel(String str) {
        this.defaultLogLevel = str;
    }

    public void setDisableAllLogs(Boolean bool) {
        this.disableAllLogs = bool;
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
        if (getDefaultLogLevel() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("defaultLogLevel: ");
            outline1073.append(getDefaultLogLevel());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getDisableAllLogs() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("disableAllLogs: ");
            outline1074.append(getDisableAllLogs());
            outline107.append(outline1074.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public SetV2LoggingOptionsRequest withDefaultLogLevel(String str) {
        this.defaultLogLevel = str;
        return this;
    }

    public SetV2LoggingOptionsRequest withDisableAllLogs(Boolean bool) {
        this.disableAllLogs = bool;
        return this;
    }

    public SetV2LoggingOptionsRequest withRoleArn(String str) {
        this.roleArn = str;
        return this;
    }

    public void setDefaultLogLevel(LogLevel logLevel) {
        this.defaultLogLevel = logLevel.toString();
    }

    public SetV2LoggingOptionsRequest withDefaultLogLevel(LogLevel logLevel) {
        this.defaultLogLevel = logLevel.toString();
        return this;
    }
}
