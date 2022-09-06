package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.ErrorInfo;
import com.amazonaws.util.json.AwsJsonWriter;
/* loaded from: classes13.dex */
class ErrorInfoJsonMarshaller {
    private static ErrorInfoJsonMarshaller instance;

    ErrorInfoJsonMarshaller() {
    }

    public static ErrorInfoJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new ErrorInfoJsonMarshaller();
        }
        return instance;
    }

    public void marshall(ErrorInfo errorInfo, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (errorInfo.getCode() != null) {
            String code = errorInfo.getCode();
            awsJsonWriter.name("code");
            awsJsonWriter.value(code);
        }
        if (errorInfo.getMessage() != null) {
            String message = errorInfo.getMessage();
            awsJsonWriter.name("message");
            awsJsonWriter.value(message);
        }
        awsJsonWriter.endObject();
    }
}
