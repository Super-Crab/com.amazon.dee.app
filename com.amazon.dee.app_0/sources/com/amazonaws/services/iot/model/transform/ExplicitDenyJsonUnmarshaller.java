package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.ExplicitDeny;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
class ExplicitDenyJsonUnmarshaller implements Unmarshaller<ExplicitDeny, JsonUnmarshallerContext> {
    private static ExplicitDenyJsonUnmarshaller instance;

    ExplicitDenyJsonUnmarshaller() {
    }

    public static ExplicitDenyJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ExplicitDenyJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public ExplicitDeny unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        ExplicitDeny explicitDeny = new ExplicitDeny();
        reader.beginObject();
        while (reader.hasNext()) {
            if (reader.nextName().equals("policies")) {
                explicitDeny.setPolicies(new ListUnmarshaller(PolicyJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return explicitDeny;
    }
}
