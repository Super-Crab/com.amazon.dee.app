package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.Configuration;
import com.amazonaws.util.json.AwsJsonWriter;
/* loaded from: classes13.dex */
class ConfigurationJsonMarshaller {
    private static ConfigurationJsonMarshaller instance;

    ConfigurationJsonMarshaller() {
    }

    public static ConfigurationJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new ConfigurationJsonMarshaller();
        }
        return instance;
    }

    public void marshall(Configuration configuration, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (configuration.getEnabled() != null) {
            Boolean enabled = configuration.getEnabled();
            awsJsonWriter.name("Enabled");
            awsJsonWriter.value(enabled.booleanValue());
        }
        awsJsonWriter.endObject();
    }
}
