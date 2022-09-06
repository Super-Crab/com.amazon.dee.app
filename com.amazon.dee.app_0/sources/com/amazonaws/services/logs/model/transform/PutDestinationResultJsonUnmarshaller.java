package com.amazonaws.services.logs.model.transform;

import com.amazonaws.services.logs.model.PutDestinationResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
public class PutDestinationResultJsonUnmarshaller implements Unmarshaller<PutDestinationResult, JsonUnmarshallerContext> {
    private static PutDestinationResultJsonUnmarshaller instance;

    public static PutDestinationResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new PutDestinationResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public PutDestinationResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        PutDestinationResult putDestinationResult = new PutDestinationResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            if (reader.nextName().equals("destination")) {
                putDestinationResult.setDestination(DestinationJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return putDestinationResult;
    }
}
