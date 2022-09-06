package com.amazonaws.services.kinesis.model.transform;

import com.amazonaws.services.kinesis.model.DisableEnhancedMonitoringResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
public class DisableEnhancedMonitoringResultJsonUnmarshaller implements Unmarshaller<DisableEnhancedMonitoringResult, JsonUnmarshallerContext> {
    private static DisableEnhancedMonitoringResultJsonUnmarshaller instance;

    public static DisableEnhancedMonitoringResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new DisableEnhancedMonitoringResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public DisableEnhancedMonitoringResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        DisableEnhancedMonitoringResult disableEnhancedMonitoringResult = new DisableEnhancedMonitoringResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("StreamName")) {
                disableEnhancedMonitoringResult.setStreamName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("CurrentShardLevelMetrics")) {
                disableEnhancedMonitoringResult.setCurrentShardLevelMetrics(new ListUnmarshaller(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("DesiredShardLevelMetrics")) {
                disableEnhancedMonitoringResult.setDesiredShardLevelMetrics(new ListUnmarshaller(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return disableEnhancedMonitoringResult;
    }
}
