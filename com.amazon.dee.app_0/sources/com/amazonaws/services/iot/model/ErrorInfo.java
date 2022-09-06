package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class ErrorInfo implements Serializable {
    private String code;
    private String message;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ErrorInfo)) {
            return false;
        }
        ErrorInfo errorInfo = (ErrorInfo) obj;
        if ((errorInfo.getCode() == null) ^ (getCode() == null)) {
            return false;
        }
        if (errorInfo.getCode() != null && !errorInfo.getCode().equals(getCode())) {
            return false;
        }
        if ((errorInfo.getMessage() == null) ^ (getMessage() == null)) {
            return false;
        }
        return errorInfo.getMessage() == null || errorInfo.getMessage().equals(getMessage());
    }

    public String getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getCode() == null ? 0 : getCode().hashCode()) + 31) * 31;
        if (getMessage() != null) {
            i = getMessage().hashCode();
        }
        return hashCode + i;
    }

    public void setCode(String str) {
        this.code = str;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getCode() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("code: ");
            outline1072.append(getCode());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getMessage() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("message: ");
            outline1073.append(getMessage());
            outline107.append(outline1073.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public ErrorInfo withCode(String str) {
        this.code = str;
        return this;
    }

    public ErrorInfo withMessage(String str) {
        this.message = str;
        return this;
    }
}
