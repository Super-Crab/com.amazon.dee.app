package com.amazonaws.services.logs.model.transform;

import com.amazonaws.services.logs.model.DescribeExportTasksResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
public class DescribeExportTasksResultJsonUnmarshaller implements Unmarshaller<DescribeExportTasksResult, JsonUnmarshallerContext> {
    private static DescribeExportTasksResultJsonUnmarshaller instance;

    public static DescribeExportTasksResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new DescribeExportTasksResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public DescribeExportTasksResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        DescribeExportTasksResult describeExportTasksResult = new DescribeExportTasksResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("exportTasks")) {
                describeExportTasksResult.setExportTasks(new ListUnmarshaller(ExportTaskJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("nextToken")) {
                describeExportTasksResult.setNextToken(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return describeExportTasksResult;
    }
}
