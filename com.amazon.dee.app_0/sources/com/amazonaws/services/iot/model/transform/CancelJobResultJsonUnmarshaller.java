package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.CancelJobResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
public class CancelJobResultJsonUnmarshaller implements Unmarshaller<CancelJobResult, JsonUnmarshallerContext> {
    private static CancelJobResultJsonUnmarshaller instance;

    public static CancelJobResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new CancelJobResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public CancelJobResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        CancelJobResult cancelJobResult = new CancelJobResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("jobArn")) {
                cancelJobResult.setJobArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("jobId")) {
                cancelJobResult.setJobId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("description")) {
                cancelJobResult.setDescription(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return cancelJobResult;
    }
}
