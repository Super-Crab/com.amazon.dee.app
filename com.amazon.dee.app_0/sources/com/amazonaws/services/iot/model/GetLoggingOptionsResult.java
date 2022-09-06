package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class GetLoggingOptionsResult implements Serializable {
    private String logLevel;
    private String roleArn;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof GetLoggingOptionsResult)) {
            return false;
        }
        GetLoggingOptionsResult getLoggingOptionsResult = (GetLoggingOptionsResult) obj;
        if ((getLoggingOptionsResult.getRoleArn() == null) ^ (getRoleArn() == null)) {
            return false;
        }
        if (getLoggingOptionsResult.getRoleArn() != null && !getLoggingOptionsResult.getRoleArn().equals(getRoleArn())) {
            return false;
        }
        if ((getLoggingOptionsResult.getLogLevel() == null) ^ (getLogLevel() == null)) {
            return false;
        }
        return getLoggingOptionsResult.getLogLevel() == null || getLoggingOptionsResult.getLogLevel().equals(getLogLevel());
    }

    public String getLogLevel() {
        return this.logLevel;
    }

    public String getRoleArn() {
        return this.roleArn;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getRoleArn() == null ? 0 : getRoleArn().hashCode()) + 31) * 31;
        if (getLogLevel() != null) {
            i = getLogLevel().hashCode();
        }
        return hashCode + i;
    }

    public void setLogLevel(String str) {
        this.logLevel = str;
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
        if (getLogLevel() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("logLevel: ");
            outline1073.append(getLogLevel());
            outline107.append(outline1073.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public GetLoggingOptionsResult withLogLevel(String str) {
        this.logLevel = str;
        return this;
    }

    public GetLoggingOptionsResult withRoleArn(String str) {
        this.roleArn = str;
        return this;
    }

    public void setLogLevel(LogLevel logLevel) {
        this.logLevel = logLevel.toString();
    }

    public GetLoggingOptionsResult withLogLevel(LogLevel logLevel) {
        this.logLevel = logLevel.toString();
        return this;
    }
}
