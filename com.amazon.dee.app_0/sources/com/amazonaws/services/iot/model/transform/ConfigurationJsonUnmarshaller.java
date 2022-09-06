package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.Configuration;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
class ConfigurationJsonUnmarshaller implements Unmarshaller<Configuration, JsonUnmarshallerContext> {
    private static ConfigurationJsonUnmarshaller instance;

    ConfigurationJsonUnmarshaller() {
    }

    public static ConfigurationJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ConfigurationJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public Configuration unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        Configuration configuration = new Configuration();
        reader.beginObject();
        while (reader.hasNext()) {
            if (reader.nextName().equals("Enabled")) {
                configuration.setEnabled(SimpleTypeJsonUnmarshallers.BooleanJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return configuration;
    }
}
