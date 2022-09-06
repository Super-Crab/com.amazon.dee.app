package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.GetLoggingOptionsResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
public class GetLoggingOptionsResultJsonUnmarshaller implements Unmarshaller<GetLoggingOptionsResult, JsonUnmarshallerContext> {
    private static GetLoggingOptionsResultJsonUnmarshaller instance;

    public static GetLoggingOptionsResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new GetLoggingOptionsResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public GetLoggingOptionsResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        GetLoggingOptionsResult getLoggingOptionsResult = new GetLoggingOptionsResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("roleArn")) {
                getLoggingOptionsResult.setRoleArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("logLevel")) {
                getLoggingOptionsResult.setLogLevel(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return getLoggingOptionsResult;
    }
}
