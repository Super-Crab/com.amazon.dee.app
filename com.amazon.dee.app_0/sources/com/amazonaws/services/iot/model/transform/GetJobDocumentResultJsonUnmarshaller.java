package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.GetJobDocumentResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
public class GetJobDocumentResultJsonUnmarshaller implements Unmarshaller<GetJobDocumentResult, JsonUnmarshallerContext> {
    private static GetJobDocumentResultJsonUnmarshaller instance;

    public static GetJobDocumentResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new GetJobDocumentResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public GetJobDocumentResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        GetJobDocumentResult getJobDocumentResult = new GetJobDocumentResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            if (reader.nextName().equals("document")) {
                getJobDocumentResult.setDocument(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return getJobDocumentResult;
    }
}
