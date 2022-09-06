package com.amazonaws.services.iot.model.transform;

import com.amazon.alexa.accessory.metrics.MetricsConstants;
import com.amazon.commscore.api.metrics.MobilyticsCustomEntries;
import com.amazonaws.services.iot.model.LogTarget;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
class LogTargetJsonUnmarshaller implements Unmarshaller<LogTarget, JsonUnmarshallerContext> {
    private static LogTargetJsonUnmarshaller instance;

    LogTargetJsonUnmarshaller() {
    }

    public static LogTargetJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new LogTargetJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public LogTarget unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        LogTarget logTarget = new LogTarget();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals(MobilyticsCustomEntries.CommsMetadata.TARGET_TYPE)) {
                logTarget.setTargetType(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals(MetricsConstants.Firmware.TARGET_NAME)) {
                logTarget.setTargetName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return logTarget;
    }
}
