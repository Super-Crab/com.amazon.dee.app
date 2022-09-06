package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.CreateStreamResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
public class CreateStreamResultJsonUnmarshaller implements Unmarshaller<CreateStreamResult, JsonUnmarshallerContext> {
    private static CreateStreamResultJsonUnmarshaller instance;

    public static CreateStreamResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new CreateStreamResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public CreateStreamResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        CreateStreamResult createStreamResult = new CreateStreamResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("streamId")) {
                createStreamResult.setStreamId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("streamArn")) {
                createStreamResult.setStreamArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("description")) {
                createStreamResult.setDescription(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("streamVersion")) {
                createStreamResult.setStreamVersion(SimpleTypeJsonUnmarshallers.IntegerJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return createStreamResult;
    }
}
