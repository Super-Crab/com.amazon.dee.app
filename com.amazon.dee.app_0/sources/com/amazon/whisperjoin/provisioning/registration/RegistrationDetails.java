package com.amazon.whisperjoin.provisioning.registration;

import com.amazon.whisperjoin.provisioning.bluetooth.request.serializers.Validatable;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes13.dex */
public class RegistrationDetails implements Validatable {
    public static final int COMPLETING_REGISTRATION = 1;
    public static final int NOT_REGISTERED = 0;
    public static final int REGISTRATION_COMPLETE = 2;
    public static final int REGISTRATION_FAILED_OTHER = -1;
    public static final int REGISTRATION_FAILURE_SERVER_ERROR = -5;
    public static final int REGISTRATION_FAILURE_SERVER_NOT_REACHABLE = -4;
    public static final int REGISTRATION_FAILURE_TOKEN_EXPIRED = -3;
    public static final int REGISTRATION_FAILURE_TOKEN_INVALID = -2;
    private Integer httpCode;
    @SerializedName("msg")
    private String message;
    private Integer state;

    protected boolean canEqual(Object obj) {
        return obj instanceof RegistrationDetails;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RegistrationDetails)) {
            return false;
        }
        RegistrationDetails registrationDetails = (RegistrationDetails) obj;
        if (!registrationDetails.canEqual(this)) {
            return false;
        }
        Integer state = getState();
        Integer state2 = registrationDetails.getState();
        if (state != null ? !state.equals(state2) : state2 != null) {
            return false;
        }
        String message = getMessage();
        String message2 = registrationDetails.getMessage();
        if (message != null ? !message.equals(message2) : message2 != null) {
            return false;
        }
        Integer httpCode = getHttpCode();
        Integer httpCode2 = registrationDetails.getHttpCode();
        return httpCode != null ? httpCode.equals(httpCode2) : httpCode2 == null;
    }

    public Integer getHttpCode() {
        return this.httpCode;
    }

    public String getMessage() {
        return this.message;
    }

    public Integer getState() {
        return this.state;
    }

    public int hashCode() {
        Integer state = getState();
        int i = 43;
        int hashCode = state == null ? 43 : state.hashCode();
        String message = getMessage();
        int hashCode2 = ((hashCode + 59) * 59) + (message == null ? 43 : message.hashCode());
        Integer httpCode = getHttpCode();
        int i2 = hashCode2 * 59;
        if (httpCode != null) {
            i = httpCode.hashCode();
        }
        return i2 + i;
    }

    public void setHttpCode(Integer num) {
        this.httpCode = num;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public void setState(Integer num) {
        this.state = num;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("RegistrationDetails(state=");
        outline107.append(getState());
        outline107.append(", message=");
        outline107.append(getMessage());
        outline107.append(", httpCode=");
        outline107.append(getHttpCode());
        outline107.append(")");
        return outline107.toString();
    }

    @Override // com.amazon.whisperjoin.provisioning.bluetooth.request.serializers.Validatable
    public void validate() {
        Integer num = this.httpCode;
        if (num == null || num.intValue() >= 0) {
            return;
        }
        throw new IllegalArgumentException("Http code cannot be negative. Please use InputValidator methods to validate parameters.");
    }
}
