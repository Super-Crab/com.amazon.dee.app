package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class SetV2LoggingLevelRequest extends AmazonWebServiceRequest implements Serializable {
    private String logLevel;
    private LogTarget logTarget;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof SetV2LoggingLevelRequest)) {
            return false;
        }
        SetV2LoggingLevelRequest setV2LoggingLevelRequest = (SetV2LoggingLevelRequest) obj;
        if ((setV2LoggingLevelRequest.getLogTarget() == null) ^ (getLogTarget() == null)) {
            return false;
        }
        if (setV2LoggingLevelRequest.getLogTarget() != null && !setV2LoggingLevelRequest.getLogTarget().equals(getLogTarget())) {
            return false;
        }
        if ((setV2LoggingLevelRequest.getLogLevel() == null) ^ (getLogLevel() == null)) {
            return false;
        }
        return setV2LoggingLevelRequest.getLogLevel() == null || setV2LoggingLevelRequest.getLogLevel().equals(getLogLevel());
    }

    public String getLogLevel() {
        return this.logLevel;
    }

    public LogTarget getLogTarget() {
        return this.logTarget;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getLogTarget() == null ? 0 : getLogTarget().hashCode()) + 31) * 31;
        if (getLogLevel() != null) {
            i = getLogLevel().hashCode();
        }
        return hashCode + i;
    }

    public void setLogLevel(String str) {
        this.logLevel = str;
    }

    public void setLogTarget(LogTarget logTarget) {
        this.logTarget = logTarget;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getLogTarget() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("logTarget: ");
            outline1072.append(getLogTarget());
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

    public SetV2LoggingLevelRequest withLogLevel(String str) {
        this.logLevel = str;
        return this;
    }

    public SetV2LoggingLevelRequest withLogTarget(LogTarget logTarget) {
        this.logTarget = logTarget;
        return this;
    }

    public void setLogLevel(LogLevel logLevel) {
        this.logLevel = logLevel.toString();
    }

    public SetV2LoggingLevelRequest withLogLevel(LogLevel logLevel) {
        this.logLevel = logLevel.toString();
        return this;
    }
}
