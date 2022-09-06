package com.amazonaws.services.logs.model.transform;

import com.amazonaws.services.logs.model.PutLogEventsResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
public class PutLogEventsResultJsonUnmarshaller implements Unmarshaller<PutLogEventsResult, JsonUnmarshallerContext> {
    private static PutLogEventsResultJsonUnmarshaller instance;

    public static PutLogEventsResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new PutLogEventsResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public PutLogEventsResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        PutLogEventsResult putLogEventsResult = new PutLogEventsResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("nextSequenceToken")) {
                putLogEventsResult.setNextSequenceToken(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("rejectedLogEventsInfo")) {
                putLogEventsResult.setRejectedLogEventsInfo(RejectedLogEventsInfoJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return putLogEventsResult;
    }
}
