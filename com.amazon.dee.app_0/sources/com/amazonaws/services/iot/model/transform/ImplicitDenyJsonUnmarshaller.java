package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.ImplicitDeny;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
class ImplicitDenyJsonUnmarshaller implements Unmarshaller<ImplicitDeny, JsonUnmarshallerContext> {
    private static ImplicitDenyJsonUnmarshaller instance;

    ImplicitDenyJsonUnmarshaller() {
    }

    public static ImplicitDenyJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ImplicitDenyJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public ImplicitDeny unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        ImplicitDeny implicitDeny = new ImplicitDeny();
        reader.beginObject();
        while (reader.hasNext()) {
            if (reader.nextName().equals("policies")) {
                implicitDeny.setPolicies(new ListUnmarshaller(PolicyJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return implicitDeny;
    }
}
