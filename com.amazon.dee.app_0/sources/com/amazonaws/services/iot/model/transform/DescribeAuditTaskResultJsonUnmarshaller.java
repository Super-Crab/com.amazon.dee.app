package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.DescribeAuditTaskResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.MapUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
public class DescribeAuditTaskResultJsonUnmarshaller implements Unmarshaller<DescribeAuditTaskResult, JsonUnmarshallerContext> {
    private static DescribeAuditTaskResultJsonUnmarshaller instance;

    public static DescribeAuditTaskResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new DescribeAuditTaskResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public DescribeAuditTaskResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        DescribeAuditTaskResult describeAuditTaskResult = new DescribeAuditTaskResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("taskStatus")) {
                describeAuditTaskResult.setTaskStatus(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("taskType")) {
                describeAuditTaskResult.setTaskType(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("taskStartTime")) {
                describeAuditTaskResult.setTaskStartTime(SimpleTypeJsonUnmarshallers.DateJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("taskStatistics")) {
                describeAuditTaskResult.setTaskStatistics(TaskStatisticsJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("scheduledAuditName")) {
                describeAuditTaskResult.setScheduledAuditName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("auditDetails")) {
                describeAuditTaskResult.setAuditDetails(new MapUnmarshaller(AuditCheckDetailsJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return describeAuditTaskResult;
    }
}
