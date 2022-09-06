package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.LogTargetConfiguration;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
class LogTargetConfigurationJsonUnmarshaller implements Unmarshaller<LogTargetConfiguration, JsonUnmarshallerContext> {
    private static LogTargetConfigurationJsonUnmarshaller instance;

    LogTargetConfigurationJsonUnmarshaller() {
    }

    public static LogTargetConfigurationJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new LogTargetConfigurationJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public LogTargetConfiguration unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        LogTargetConfiguration logTargetConfiguration = new LogTargetConfiguration();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("logTarget")) {
                logTargetConfiguration.setLogTarget(LogTargetJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("logLevel")) {
                logTargetConfiguration.setLogLevel(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return logTargetConfiguration;
    }
}
