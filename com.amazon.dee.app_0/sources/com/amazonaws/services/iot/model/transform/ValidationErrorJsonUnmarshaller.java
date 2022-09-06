package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.ValidationError;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
class ValidationErrorJsonUnmarshaller implements Unmarshaller<ValidationError, JsonUnmarshallerContext> {
    private static ValidationErrorJsonUnmarshaller instance;

    ValidationErrorJsonUnmarshaller() {
    }

    public static ValidationErrorJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ValidationErrorJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public ValidationError unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        ValidationError validationError = new ValidationError();
        reader.beginObject();
        while (reader.hasNext()) {
            if (reader.nextName().equals("errorMessage")) {
                validationError.setErrorMessage(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return validationError;
    }
}
