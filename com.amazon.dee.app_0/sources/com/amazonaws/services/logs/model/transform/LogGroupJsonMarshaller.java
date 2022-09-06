package com.amazonaws.services.logs.model.transform;

import com.amazonaws.services.logs.model.LogGroup;
import com.amazonaws.util.json.AwsJsonWriter;
/* loaded from: classes13.dex */
class LogGroupJsonMarshaller {
    private static LogGroupJsonMarshaller instance;

    LogGroupJsonMarshaller() {
    }

    public static LogGroupJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new LogGroupJsonMarshaller();
        }
        return instance;
    }

    public void marshall(LogGroup logGroup, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (logGroup.getLogGroupName() != null) {
            String logGroupName = logGroup.getLogGroupName();
            awsJsonWriter.name("logGroupName");
            awsJsonWriter.value(logGroupName);
        }
        if (logGroup.getCreationTime() != null) {
            Long creationTime = logGroup.getCreationTime();
            awsJsonWriter.name("creationTime");
            awsJsonWriter.value(creationTime);
        }
        if (logGroup.getRetentionInDays() != null) {
            Integer retentionInDays = logGroup.getRetentionInDays();
            awsJsonWriter.name("retentionInDays");
            awsJsonWriter.value(retentionInDays);
        }
        if (logGroup.getMetricFilterCount() != null) {
            Integer metricFilterCount = logGroup.getMetricFilterCount();
            awsJsonWriter.name("metricFilterCount");
            awsJsonWriter.value(metricFilterCount);
        }
        if (logGroup.getArn() != null) {
            String arn = logGroup.getArn();
            awsJsonWriter.name("arn");
            awsJsonWriter.value(arn);
        }
        if (logGroup.getStoredBytes() != null) {
            Long storedBytes = logGroup.getStoredBytes();
            awsJsonWriter.name("storedBytes");
            awsJsonWriter.value(storedBytes);
        }
        if (logGroup.getKmsKeyId() != null) {
            String kmsKeyId = logGroup.getKmsKeyId();
            awsJsonWriter.name("kmsKeyId");
            awsJsonWriter.value(kmsKeyId);
        }
        awsJsonWriter.endObject();
    }
}
