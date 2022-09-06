package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.ErrorInfo;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
class ErrorInfoJsonUnmarshaller implements Unmarshaller<ErrorInfo, JsonUnmarshallerContext> {
    private static ErrorInfoJsonUnmarshaller instance;

    ErrorInfoJsonUnmarshaller() {
    }

    public static ErrorInfoJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ErrorInfoJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public ErrorInfo unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        ErrorInfo errorInfo = new ErrorInfo();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("code")) {
                errorInfo.setCode(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("message")) {
                errorInfo.setMessage(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return errorInfo;
    }
}
