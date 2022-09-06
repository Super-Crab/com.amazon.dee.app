package com.amazonaws.services.kinesis.model.transform;

import com.amazonaws.services.kinesis.model.EnhancedMetrics;
import com.amazonaws.util.json.AwsJsonWriter;
import java.util.List;
/* loaded from: classes13.dex */
class EnhancedMetricsJsonMarshaller {
    private static EnhancedMetricsJsonMarshaller instance;

    EnhancedMetricsJsonMarshaller() {
    }

    public static EnhancedMetricsJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new EnhancedMetricsJsonMarshaller();
        }
        return instance;
    }

    public void marshall(EnhancedMetrics enhancedMetrics, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (enhancedMetrics.getShardLevelMetrics() != null) {
            List<String> shardLevelMetrics = enhancedMetrics.getShardLevelMetrics();
            awsJsonWriter.name("ShardLevelMetrics");
            awsJsonWriter.beginArray();
            for (String str : shardLevelMetrics) {
                if (str != null) {
                    awsJsonWriter.value(str);
                }
            }
            awsJsonWriter.endArray();
        }
        awsJsonWriter.endObject();
    }
}
