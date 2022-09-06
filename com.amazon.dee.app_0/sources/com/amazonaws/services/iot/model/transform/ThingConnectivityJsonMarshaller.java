package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.ThingConnectivity;
import com.amazonaws.util.json.AwsJsonWriter;
/* loaded from: classes13.dex */
class ThingConnectivityJsonMarshaller {
    private static ThingConnectivityJsonMarshaller instance;

    ThingConnectivityJsonMarshaller() {
    }

    public static ThingConnectivityJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new ThingConnectivityJsonMarshaller();
        }
        return instance;
    }

    public void marshall(ThingConnectivity thingConnectivity, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (thingConnectivity.getConnected() != null) {
            Boolean connected = thingConnectivity.getConnected();
            awsJsonWriter.name("connected");
            awsJsonWriter.value(connected.booleanValue());
        }
        if (thingConnectivity.getTimestamp() != null) {
            Long timestamp = thingConnectivity.getTimestamp();
            awsJsonWriter.name("timestamp");
            awsJsonWriter.value(timestamp);
        }
        awsJsonWriter.endObject();
    }
}
