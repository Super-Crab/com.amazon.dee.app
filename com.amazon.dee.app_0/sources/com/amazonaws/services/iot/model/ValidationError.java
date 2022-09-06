package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class ValidationError implements Serializable {
    private String errorMessage;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ValidationError)) {
            return false;
        }
        ValidationError validationError = (ValidationError) obj;
        if ((validationError.getErrorMessage() == null) ^ (getErrorMessage() == null)) {
            return false;
        }
        return validationError.getErrorMessage() == null || validationError.getErrorMessage().equals(getErrorMessage());
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public int hashCode() {
        return 31 + (getErrorMessage() == null ? 0 : getErrorMessage().hashCode());
    }

    public void setErrorMessage(String str) {
        this.errorMessage = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getErrorMessage() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("errorMessage: ");
            outline1072.append(getErrorMessage());
            outline107.append(outline1072.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public ValidationError withErrorMessage(String str) {
        this.errorMessage = str;
        return this;
    }
}
