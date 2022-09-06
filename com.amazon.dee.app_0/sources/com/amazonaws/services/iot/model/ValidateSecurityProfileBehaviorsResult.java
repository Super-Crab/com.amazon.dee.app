package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/* loaded from: classes13.dex */
public class ValidateSecurityProfileBehaviorsResult implements Serializable {
    private Boolean valid;
    private List<ValidationError> validationErrors;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ValidateSecurityProfileBehaviorsResult)) {
            return false;
        }
        ValidateSecurityProfileBehaviorsResult validateSecurityProfileBehaviorsResult = (ValidateSecurityProfileBehaviorsResult) obj;
        if ((validateSecurityProfileBehaviorsResult.getValid() == null) ^ (getValid() == null)) {
            return false;
        }
        if (validateSecurityProfileBehaviorsResult.getValid() != null && !validateSecurityProfileBehaviorsResult.getValid().equals(getValid())) {
            return false;
        }
        if ((validateSecurityProfileBehaviorsResult.getValidationErrors() == null) ^ (getValidationErrors() == null)) {
            return false;
        }
        return validateSecurityProfileBehaviorsResult.getValidationErrors() == null || validateSecurityProfileBehaviorsResult.getValidationErrors().equals(getValidationErrors());
    }

    public Boolean getValid() {
        return this.valid;
    }

    public List<ValidationError> getValidationErrors() {
        return this.validationErrors;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getValid() == null ? 0 : getValid().hashCode()) + 31) * 31;
        if (getValidationErrors() != null) {
            i = getValidationErrors().hashCode();
        }
        return hashCode + i;
    }

    public Boolean isValid() {
        return this.valid;
    }

    public void setValid(Boolean bool) {
        this.valid = bool;
    }

    public void setValidationErrors(Collection<ValidationError> collection) {
        if (collection == null) {
            this.validationErrors = null;
        } else {
            this.validationErrors = new ArrayList(collection);
        }
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getValid() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("valid: ");
            outline1072.append(getValid());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getValidationErrors() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("validationErrors: ");
            outline1073.append(getValidationErrors());
            outline107.append(outline1073.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public ValidateSecurityProfileBehaviorsResult withValid(Boolean bool) {
        this.valid = bool;
        return this;
    }

    public ValidateSecurityProfileBehaviorsResult withValidationErrors(ValidationError... validationErrorArr) {
        if (getValidationErrors() == null) {
            this.validationErrors = new ArrayList(validationErrorArr.length);
        }
        for (ValidationError validationError : validationErrorArr) {
            this.validationErrors.add(validationError);
        }
        return this;
    }

    public ValidateSecurityProfileBehaviorsResult withValidationErrors(Collection<ValidationError> collection) {
        setValidationErrors(collection);
        return this;
    }
}
