package com.amazonaws.services.iot.model.transform;

import com.amazon.identity.auth.map.device.token.MAPCookie;
import com.amazonaws.services.iot.model.Tag;
import com.amazonaws.util.json.AwsJsonWriter;
/* loaded from: classes13.dex */
class TagJsonMarshaller {
    private static TagJsonMarshaller instance;

    TagJsonMarshaller() {
    }

    public static TagJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new TagJsonMarshaller();
        }
        return instance;
    }

    public void marshall(Tag tag, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (tag.getKey() != null) {
            String key = tag.getKey();
            awsJsonWriter.name("Key");
            awsJsonWriter.value(key);
        }
        if (tag.getValue() != null) {
            String value = tag.getValue();
            awsJsonWriter.name(MAPCookie.KEY_VALUE);
            awsJsonWriter.value(value);
        }
        awsJsonWriter.endObject();
    }
}
