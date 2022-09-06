package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class LoggingOptionsPayload implements Serializable {
    private String logLevel;
    private String roleArn;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof LoggingOptionsPayload)) {
            return false;
        }
        LoggingOptionsPayload loggingOptionsPayload = (LoggingOptionsPayload) obj;
        if ((loggingOptionsPayload.getRoleArn() == null) ^ (getRoleArn() == null)) {
            return false;
        }
        if (loggingOptionsPayload.getRoleArn() != null && !loggingOptionsPayload.getRoleArn().equals(getRoleArn())) {
            return false;
        }
        if ((loggingOptionsPayload.getLogLevel() == null) ^ (getLogLevel() == null)) {
            return false;
        }
        return loggingOptionsPayload.getLogLevel() == null || loggingOptionsPayload.getLogLevel().equals(getLogLevel());
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

    public LoggingOptionsPayload withLogLevel(String str) {
        this.logLevel = str;
        return this;
    }

    public LoggingOptionsPayload withRoleArn(String str) {
        this.roleArn = str;
        return this;
    }

    public void setLogLevel(LogLevel logLevel) {
        this.logLevel = logLevel.toString();
    }

    public LoggingOptionsPayload withLogLevel(LogLevel logLevel) {
        this.logLevel = logLevel.toString();
        return this;
    }
}
