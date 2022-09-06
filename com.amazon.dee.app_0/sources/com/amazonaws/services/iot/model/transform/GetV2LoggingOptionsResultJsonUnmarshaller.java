package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.GetV2LoggingOptionsResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
public class GetV2LoggingOptionsResultJsonUnmarshaller implements Unmarshaller<GetV2LoggingOptionsResult, JsonUnmarshallerContext> {
    private static GetV2LoggingOptionsResultJsonUnmarshaller instance;

    public static GetV2LoggingOptionsResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new GetV2LoggingOptionsResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public GetV2LoggingOptionsResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        GetV2LoggingOptionsResult getV2LoggingOptionsResult = new GetV2LoggingOptionsResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("roleArn")) {
                getV2LoggingOptionsResult.setRoleArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("defaultLogLevel")) {
                getV2LoggingOptionsResult.setDefaultLogLevel(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("disableAllLogs")) {
                getV2LoggingOptionsResult.setDisableAllLogs(SimpleTypeJsonUnmarshallers.BooleanJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return getV2LoggingOptionsResult;
    }
}
