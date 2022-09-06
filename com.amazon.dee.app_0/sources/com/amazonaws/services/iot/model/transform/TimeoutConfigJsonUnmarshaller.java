package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.TimeoutConfig;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
class TimeoutConfigJsonUnmarshaller implements Unmarshaller<TimeoutConfig, JsonUnmarshallerContext> {
    private static TimeoutConfigJsonUnmarshaller instance;

    TimeoutConfigJsonUnmarshaller() {
    }

    public static TimeoutConfigJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new TimeoutConfigJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public TimeoutConfig unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        TimeoutConfig timeoutConfig = new TimeoutConfig();
        reader.beginObject();
        while (reader.hasNext()) {
            if (reader.nextName().equals("inProgressTimeoutInMinutes")) {
                timeoutConfig.setInProgressTimeoutInMinutes(SimpleTypeJsonUnmarshallers.LongJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return timeoutConfig;
    }
}
