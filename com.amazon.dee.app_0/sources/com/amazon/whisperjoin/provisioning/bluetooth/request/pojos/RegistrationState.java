package com.amazon.whisperjoin.provisioning.bluetooth.request.pojos;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.HashCodeBuilder;
/* loaded from: classes13.dex */
public class RegistrationState {
    public static final int COMPLETING_REGISTRATION = 1;
    public static final int NOT_REGISTERED = 0;
    public static final int REGISTRATION_COMPLETE = 2;
    public static final int REGISTRATION_FAILED_OTHER = -1;
    public static final int REGISTRATION_FAILURE_SERVER_NOT_REACHABLE = -4;
    public static final int REGISTRATION_FAILURE_SERVER_UNAVAILABLE = -5;
    public static final int REGISTRATION_FAILURE_TOKEN_EXPIRED = -3;
    public static final int REGISTRATION_FAILURE_TOKEN_INVALID = -2;
    public final Integer httpCode;
    @SerializedName("msg")
    public final String message;
    public final int state;

    public RegistrationState(int i, Integer num, String str) {
        this.state = i;
        this.httpCode = num;
        this.message = str;
    }

    public boolean equals(Object obj) {
        Integer num;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RegistrationState)) {
            return false;
        }
        RegistrationState registrationState = (RegistrationState) obj;
        if (this.state == registrationState.state && ((num = this.httpCode) != null ? num.equals(registrationState.httpCode) : registrationState.httpCode == null)) {
            String str = this.message;
            String str2 = registrationState.message;
            if (str == null) {
                if (str2 == null) {
                    return true;
                }
            } else if (str.equals(str2)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        HashCodeBuilder hashCodeBuilder = new HashCodeBuilder();
        hashCodeBuilder.append(this.state);
        Integer num = this.httpCode;
        if (num != null) {
            hashCodeBuilder.append(num);
        }
        String str = this.message;
        if (str != null) {
            hashCodeBuilder.append(str);
        }
        return hashCodeBuilder.hashCode();
    }

    public String toString() {
        StringBuilder outline112 = GeneratedOutlineSupport1.outline112("RegistrationState(", "state=");
        outline112.append(this.state);
        if (this.httpCode != null) {
            outline112.append(", httpCode=");
            outline112.append(this.httpCode.intValue());
        }
        if (this.message != null) {
            outline112.append(", message=\"");
            outline112.append(this.message);
            outline112.append(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED);
        }
        outline112.append(")");
        return outline112.toString();
    }
}
