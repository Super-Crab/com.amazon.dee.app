package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.AwsJobExecutionsRolloutConfig;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
class AwsJobExecutionsRolloutConfigJsonUnmarshaller implements Unmarshaller<AwsJobExecutionsRolloutConfig, JsonUnmarshallerContext> {
    private static AwsJobExecutionsRolloutConfigJsonUnmarshaller instance;

    AwsJobExecutionsRolloutConfigJsonUnmarshaller() {
    }

    public static AwsJobExecutionsRolloutConfigJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new AwsJobExecutionsRolloutConfigJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public AwsJobExecutionsRolloutConfig unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        AwsJobExecutionsRolloutConfig awsJobExecutionsRolloutConfig = new AwsJobExecutionsRolloutConfig();
        reader.beginObject();
        while (reader.hasNext()) {
            if (reader.nextName().equals("maximumPerMinute")) {
                awsJobExecutionsRolloutConfig.setMaximumPerMinute(SimpleTypeJsonUnmarshallers.IntegerJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return awsJobExecutionsRolloutConfig;
    }
}
