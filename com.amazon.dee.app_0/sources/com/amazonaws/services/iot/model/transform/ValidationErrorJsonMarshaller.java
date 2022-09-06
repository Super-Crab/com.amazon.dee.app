package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.ValidationError;
import com.amazonaws.util.json.AwsJsonWriter;
/* loaded from: classes13.dex */
class ValidationErrorJsonMarshaller {
    private static ValidationErrorJsonMarshaller instance;

    ValidationErrorJsonMarshaller() {
    }

    public static ValidationErrorJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new ValidationErrorJsonMarshaller();
        }
        return instance;
    }

    public void marshall(ValidationError validationError, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (validationError.getErrorMessage() != null) {
            String errorMessage = validationError.getErrorMessage();
            awsJsonWriter.name("errorMessage");
            awsJsonWriter.value(errorMessage);
        }
        awsJsonWriter.endObject();
    }
}
