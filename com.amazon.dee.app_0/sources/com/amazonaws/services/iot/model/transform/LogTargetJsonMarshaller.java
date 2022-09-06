package com.amazonaws.services.iot.model.transform;

import com.amazon.alexa.accessory.metrics.MetricsConstants;
import com.amazon.commscore.api.metrics.MobilyticsCustomEntries;
import com.amazonaws.services.iot.model.LogTarget;
import com.amazonaws.util.json.AwsJsonWriter;
/* loaded from: classes13.dex */
class LogTargetJsonMarshaller {
    private static LogTargetJsonMarshaller instance;

    LogTargetJsonMarshaller() {
    }

    public static LogTargetJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new LogTargetJsonMarshaller();
        }
        return instance;
    }

    public void marshall(LogTarget logTarget, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (logTarget.getTargetType() != null) {
            String targetType = logTarget.getTargetType();
            awsJsonWriter.name(MobilyticsCustomEntries.CommsMetadata.TARGET_TYPE);
            awsJsonWriter.value(targetType);
        }
        if (logTarget.getTargetName() != null) {
            String targetName = logTarget.getTargetName();
            awsJsonWriter.name(MetricsConstants.Firmware.TARGET_NAME);
            awsJsonWriter.value(targetName);
        }
        awsJsonWriter.endObject();
    }
}
