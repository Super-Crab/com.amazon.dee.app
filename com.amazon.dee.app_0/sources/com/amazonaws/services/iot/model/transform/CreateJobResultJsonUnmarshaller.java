package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.CreateJobResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
public class CreateJobResultJsonUnmarshaller implements Unmarshaller<CreateJobResult, JsonUnmarshallerContext> {
    private static CreateJobResultJsonUnmarshaller instance;

    public static CreateJobResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new CreateJobResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public CreateJobResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        CreateJobResult createJobResult = new CreateJobResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("jobArn")) {
                createJobResult.setJobArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("jobId")) {
                createJobResult.setJobId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("description")) {
                createJobResult.setDescription(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return createJobResult;
    }
}
