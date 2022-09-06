package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.Allowed;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
class AllowedJsonUnmarshaller implements Unmarshaller<Allowed, JsonUnmarshallerContext> {
    private static AllowedJsonUnmarshaller instance;

    AllowedJsonUnmarshaller() {
    }

    public static AllowedJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new AllowedJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public Allowed unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        Allowed allowed = new Allowed();
        reader.beginObject();
        while (reader.hasNext()) {
            if (reader.nextName().equals("policies")) {
                allowed.setPolicies(new ListUnmarshaller(PolicyJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return allowed;
    }
}
