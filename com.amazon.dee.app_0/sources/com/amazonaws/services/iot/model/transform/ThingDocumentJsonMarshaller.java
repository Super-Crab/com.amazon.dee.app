package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.ThingConnectivity;
import com.amazonaws.services.iot.model.ThingDocument;
import com.amazonaws.util.json.AwsJsonWriter;
import java.util.List;
import java.util.Map;
/* loaded from: classes13.dex */
class ThingDocumentJsonMarshaller {
    private static ThingDocumentJsonMarshaller instance;

    ThingDocumentJsonMarshaller() {
    }

    public static ThingDocumentJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new ThingDocumentJsonMarshaller();
        }
        return instance;
    }

    public void marshall(ThingDocument thingDocument, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (thingDocument.getThingName() != null) {
            String thingName = thingDocument.getThingName();
            awsJsonWriter.name("thingName");
            awsJsonWriter.value(thingName);
        }
        if (thingDocument.getThingId() != null) {
            String thingId = thingDocument.getThingId();
            awsJsonWriter.name("thingId");
            awsJsonWriter.value(thingId);
        }
        if (thingDocument.getThingTypeName() != null) {
            String thingTypeName = thingDocument.getThingTypeName();
            awsJsonWriter.name("thingTypeName");
            awsJsonWriter.value(thingTypeName);
        }
        if (thingDocument.getThingGroupNames() != null) {
            List<String> thingGroupNames = thingDocument.getThingGroupNames();
            awsJsonWriter.name("thingGroupNames");
            awsJsonWriter.beginArray();
            for (String str : thingGroupNames) {
                if (str != null) {
                    awsJsonWriter.value(str);
                }
            }
            awsJsonWriter.endArray();
        }
        if (thingDocument.getAttributes() != null) {
            Map<String, String> attributes = thingDocument.getAttributes();
            awsJsonWriter.name("attributes");
            awsJsonWriter.beginObject();
            for (Map.Entry<String, String> entry : attributes.entrySet()) {
                String value = entry.getValue();
                if (value != null) {
                    awsJsonWriter.name(entry.getKey());
                    awsJsonWriter.value(value);
                }
            }
            awsJsonWriter.endObject();
        }
        if (thingDocument.getShadow() != null) {
            String shadow = thingDocument.getShadow();
            awsJsonWriter.name("shadow");
            awsJsonWriter.value(shadow);
        }
        if (thingDocument.getConnectivity() != null) {
            ThingConnectivity connectivity = thingDocument.getConnectivity();
            awsJsonWriter.name("connectivity");
            ThingConnectivityJsonMarshaller.getInstance().marshall(connectivity, awsJsonWriter);
        }
        awsJsonWriter.endObject();
    }
}
