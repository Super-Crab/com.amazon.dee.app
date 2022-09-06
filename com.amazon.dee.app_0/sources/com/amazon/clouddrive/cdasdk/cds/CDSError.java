package com.amazon.clouddrive.cdasdk.cds;

import com.amazon.alexa.mobilytics.event.LogLevel;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)
/* loaded from: classes11.dex */
public class CDSError {
    @JsonProperty("code")
    private String code;
    @JsonProperty(LogLevel.INFO)
    private CDSErrorInfo info;
    @JsonProperty("message")
    private String message;

    protected boolean canEqual(Object obj) {
        return obj instanceof CDSError;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CDSError)) {
            return false;
        }
        CDSError cDSError = (CDSError) obj;
        if (!cDSError.canEqual(this)) {
            return false;
        }
        String code = getCode();
        String code2 = cDSError.getCode();
        if (code != null ? !code.equals(code2) : code2 != null) {
            return false;
        }
        String message = getMessage();
        String message2 = cDSError.getMessage();
        if (message != null ? !message.equals(message2) : message2 != null) {
            return false;
        }
        CDSErrorInfo info = getInfo();
        CDSErrorInfo info2 = cDSError.getInfo();
        return info != null ? info.equals(info2) : info2 == null;
    }

    public String getCode() {
        return this.code;
    }

    public CDSErrorInfo getInfo() {
        return this.info;
    }

    public String getMessage() {
        return this.message;
    }

    public int hashCode() {
        String code = getCode();
        int i = 43;
        int hashCode = code == null ? 43 : code.hashCode();
        String message = getMessage();
        int hashCode2 = ((hashCode + 59) * 59) + (message == null ? 43 : message.hashCode());
        CDSErrorInfo info = getInfo();
        int i2 = hashCode2 * 59;
        if (info != null) {
            i = info.hashCode();
        }
        return i2 + i;
    }

    @JsonProperty("code")
    public void setCode(String str) {
        this.code = str;
    }

    @JsonProperty(LogLevel.INFO)
    public void setInfo(CDSErrorInfo cDSErrorInfo) {
        this.info = cDSErrorInfo;
    }

    @JsonProperty("message")
    public void setMessage(String str) {
        this.message = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("CDSError(code=");
        outline107.append(getCode());
        outline107.append(", message=");
        outline107.append(getMessage());
        outline107.append(", info=");
        outline107.append(getInfo());
        outline107.append(")");
        return outline107.toString();
    }
}
