package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.StartThingRegistrationTaskResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
public class StartThingRegistrationTaskResultJsonUnmarshaller implements Unmarshaller<StartThingRegistrationTaskResult, JsonUnmarshallerContext> {
    private static StartThingRegistrationTaskResultJsonUnmarshaller instance;

    public static StartThingRegistrationTaskResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new StartThingRegistrationTaskResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public StartThingRegistrationTaskResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        StartThingRegistrationTaskResult startThingRegistrationTaskResult = new StartThingRegistrationTaskResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            if (reader.nextName().equals("taskId")) {
                startThingRegistrationTaskResult.setTaskId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return startThingRegistrationTaskResult;
    }
}
