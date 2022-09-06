package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class LogTarget implements Serializable {
    private String targetName;
    private String targetType;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof LogTarget)) {
            return false;
        }
        LogTarget logTarget = (LogTarget) obj;
        if ((logTarget.getTargetType() == null) ^ (getTargetType() == null)) {
            return false;
        }
        if (logTarget.getTargetType() != null && !logTarget.getTargetType().equals(getTargetType())) {
            return false;
        }
        if ((logTarget.getTargetName() == null) ^ (getTargetName() == null)) {
            return false;
        }
        return logTarget.getTargetName() == null || logTarget.getTargetName().equals(getTargetName());
    }

    public String getTargetName() {
        return this.targetName;
    }

    public String getTargetType() {
        return this.targetType;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getTargetType() == null ? 0 : getTargetType().hashCode()) + 31) * 31;
        if (getTargetName() != null) {
            i = getTargetName().hashCode();
        }
        return hashCode + i;
    }

    public void setTargetName(String str) {
        this.targetName = str;
    }

    public void setTargetType(String str) {
        this.targetType = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getTargetType() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("targetType: ");
            outline1072.append(getTargetType());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getTargetName() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("targetName: ");
            outline1073.append(getTargetName());
            outline107.append(outline1073.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public LogTarget withTargetName(String str) {
        this.targetName = str;
        return this;
    }

    public LogTarget withTargetType(String str) {
        this.targetType = str;
        return this;
    }

    public void setTargetType(LogTargetType logTargetType) {
        this.targetType = logTargetType.toString();
    }

    public LogTarget withTargetType(LogTargetType logTargetType) {
        this.targetType = logTargetType.toString();
        return this;
    }
}
