package com.amazonaws.services.iot.model.transform;

import com.amazon.alexa.handsfree.metrics.caching.JsonFields;
import com.amazonaws.services.iot.model.AuthInfo;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
class AuthInfoJsonUnmarshaller implements Unmarshaller<AuthInfo, JsonUnmarshallerContext> {
    private static AuthInfoJsonUnmarshaller instance;

    AuthInfoJsonUnmarshaller() {
    }

    public static AuthInfoJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new AuthInfoJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public AuthInfo unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        AuthInfo authInfo = new AuthInfo();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals(JsonFields.ACTION_TYPE)) {
                authInfo.setActionType(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("resources")) {
                authInfo.setResources(new ListUnmarshaller(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return authInfo;
    }
}
