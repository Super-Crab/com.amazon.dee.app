package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.ListJobExecutionsForJobResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
public class ListJobExecutionsForJobResultJsonUnmarshaller implements Unmarshaller<ListJobExecutionsForJobResult, JsonUnmarshallerContext> {
    private static ListJobExecutionsForJobResultJsonUnmarshaller instance;

    public static ListJobExecutionsForJobResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ListJobExecutionsForJobResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public ListJobExecutionsForJobResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        ListJobExecutionsForJobResult listJobExecutionsForJobResult = new ListJobExecutionsForJobResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("executionSummaries")) {
                listJobExecutionsForJobResult.setExecutionSummaries(new ListUnmarshaller(JobExecutionSummaryForJobJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("nextToken")) {
                listJobExecutionsForJobResult.setNextToken(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return listJobExecutionsForJobResult;
    }
}
