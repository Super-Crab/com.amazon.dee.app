package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.Destination;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
class DestinationJsonUnmarshaller implements Unmarshaller<Destination, JsonUnmarshallerContext> {
    private static DestinationJsonUnmarshaller instance;

    DestinationJsonUnmarshaller() {
    }

    public static DestinationJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new DestinationJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public Destination unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        Destination destination = new Destination();
        reader.beginObject();
        while (reader.hasNext()) {
            if (reader.nextName().equals("s3Destination")) {
                destination.setS3Destination(S3DestinationJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return destination;
    }
}
