package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.PresignedUrlConfig;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
class PresignedUrlConfigJsonUnmarshaller implements Unmarshaller<PresignedUrlConfig, JsonUnmarshallerContext> {
    private static PresignedUrlConfigJsonUnmarshaller instance;

    PresignedUrlConfigJsonUnmarshaller() {
    }

    public static PresignedUrlConfigJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new PresignedUrlConfigJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public PresignedUrlConfig unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        PresignedUrlConfig presignedUrlConfig = new PresignedUrlConfig();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("roleArn")) {
                presignedUrlConfig.setRoleArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("expiresInSec")) {
                presignedUrlConfig.setExpiresInSec(SimpleTypeJsonUnmarshallers.LongJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return presignedUrlConfig;
    }
}
