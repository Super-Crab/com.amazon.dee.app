package com.amazonaws.services.iot.model.transform;

import com.amazon.identity.auth.map.device.token.MAPCookie;
import com.amazonaws.services.iot.model.Tag;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
class TagJsonUnmarshaller implements Unmarshaller<Tag, JsonUnmarshallerContext> {
    private static TagJsonUnmarshaller instance;

    TagJsonUnmarshaller() {
    }

    public static TagJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new TagJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public Tag unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        Tag tag = new Tag();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("Key")) {
                tag.setKey(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals(MAPCookie.KEY_VALUE)) {
                tag.setValue(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return tag;
    }
}
