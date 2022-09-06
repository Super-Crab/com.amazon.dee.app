package com.amazon.clouddrive.cdasdk.cdus;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)
/* loaded from: classes11.dex */
public class CDUSError {
    @JsonProperty("errorCode")
    private String errorCode;
    @JsonProperty("errorDetails")
    private CDUSErrorDetails errorDetails;
    @JsonProperty("message")
    private String message;
    @JsonProperty("requestId")
    private String requestId;
    @JsonProperty("timeStamp")
    private String timeStamp;

    protected boolean canEqual(Object obj) {
        return obj instanceof CDUSError;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CDUSError)) {
            return false;
        }
        CDUSError cDUSError = (CDUSError) obj;
        if (!cDUSError.canEqual(this)) {
            return false;
        }
        String message = getMessage();
        String message2 = cDUSError.getMessage();
        if (message != null ? !message.equals(message2) : message2 != null) {
            return false;
        }
        String errorCode = getErrorCode();
        String errorCode2 = cDUSError.getErrorCode();
        if (errorCode != null ? !errorCode.equals(errorCode2) : errorCode2 != null) {
            return false;
        }
        String timeStamp = getTimeStamp();
        String timeStamp2 = cDUSError.getTimeStamp();
        if (timeStamp != null ? !timeStamp.equals(timeStamp2) : timeStamp2 != null) {
            return false;
        }
        String requestId = getRequestId();
        String requestId2 = cDUSError.getRequestId();
        if (requestId != null ? !requestId.equals(requestId2) : requestId2 != null) {
            return false;
        }
        CDUSErrorDetails errorDetails = getErrorDetails();
        CDUSErrorDetails errorDetails2 = cDUSError.getErrorDetails();
        return errorDetails != null ? errorDetails.equals(errorDetails2) : errorDetails2 == null;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public CDUSErrorDetails getErrorDetails() {
        return this.errorDetails;
    }

    public String getMessage() {
        return this.message;
    }

    public String getRequestId() {
        return this.requestId;
    }

    public String getTimeStamp() {
        return this.timeStamp;
    }

    public int hashCode() {
        String message = getMessage();
        int i = 43;
        int hashCode = message == null ? 43 : message.hashCode();
        String errorCode = getErrorCode();
        int hashCode2 = ((hashCode + 59) * 59) + (errorCode == null ? 43 : errorCode.hashCode());
        String timeStamp = getTimeStamp();
        int hashCode3 = (hashCode2 * 59) + (timeStamp == null ? 43 : timeStamp.hashCode());
        String requestId = getRequestId();
        int hashCode4 = (hashCode3 * 59) + (requestId == null ? 43 : requestId.hashCode());
        CDUSErrorDetails errorDetails = getErrorDetails();
        int i2 = hashCode4 * 59;
        if (errorDetails != null) {
            i = errorDetails.hashCode();
        }
        return i2 + i;
    }

    @JsonProperty("errorCode")
    public void setErrorCode(String str) {
        this.errorCode = str;
    }

    @JsonProperty("errorDetails")
    public void setErrorDetails(CDUSErrorDetails cDUSErrorDetails) {
        this.errorDetails = cDUSErrorDetails;
    }

    @JsonProperty("message")
    public void setMessage(String str) {
        this.message = str;
    }

    @JsonProperty("requestId")
    public void setRequestId(String str) {
        this.requestId = str;
    }

    @JsonProperty("timeStamp")
    public void setTimeStamp(String str) {
        this.timeStamp = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("CDUSError(message=");
        outline107.append(getMessage());
        outline107.append(", errorCode=");
        outline107.append(getErrorCode());
        outline107.append(", timeStamp=");
        outline107.append(getTimeStamp());
        outline107.append(", requestId=");
        outline107.append(getRequestId());
        outline107.append(", errorDetails=");
        outline107.append(getErrorDetails());
        outline107.append(")");
        return outline107.toString();
    }
}
