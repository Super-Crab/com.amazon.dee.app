package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.AuthResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
class AuthResultJsonUnmarshaller implements Unmarshaller<AuthResult, JsonUnmarshallerContext> {
    private static AuthResultJsonUnmarshaller instance;

    AuthResultJsonUnmarshaller() {
    }

    public static AuthResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new AuthResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public AuthResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        AuthResult authResult = new AuthResult();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("authInfo")) {
                authResult.setAuthInfo(AuthInfoJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("allowed")) {
                authResult.setAllowed(AllowedJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("denied")) {
                authResult.setDenied(DeniedJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("authDecision")) {
                authResult.setAuthDecision(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("missingContextValues")) {
                authResult.setMissingContextValues(new ListUnmarshaller(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return authResult;
    }
}
