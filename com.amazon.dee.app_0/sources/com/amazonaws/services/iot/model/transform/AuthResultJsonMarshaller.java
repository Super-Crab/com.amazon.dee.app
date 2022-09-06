package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.Allowed;
import com.amazonaws.services.iot.model.AuthInfo;
import com.amazonaws.services.iot.model.AuthResult;
import com.amazonaws.services.iot.model.Denied;
import com.amazonaws.util.json.AwsJsonWriter;
import java.util.List;
/* loaded from: classes13.dex */
class AuthResultJsonMarshaller {
    private static AuthResultJsonMarshaller instance;

    AuthResultJsonMarshaller() {
    }

    public static AuthResultJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new AuthResultJsonMarshaller();
        }
        return instance;
    }

    public void marshall(AuthResult authResult, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (authResult.getAuthInfo() != null) {
            AuthInfo authInfo = authResult.getAuthInfo();
            awsJsonWriter.name("authInfo");
            AuthInfoJsonMarshaller.getInstance().marshall(authInfo, awsJsonWriter);
        }
        if (authResult.getAllowed() != null) {
            Allowed allowed = authResult.getAllowed();
            awsJsonWriter.name("allowed");
            AllowedJsonMarshaller.getInstance().marshall(allowed, awsJsonWriter);
        }
        if (authResult.getDenied() != null) {
            Denied denied = authResult.getDenied();
            awsJsonWriter.name("denied");
            DeniedJsonMarshaller.getInstance().marshall(denied, awsJsonWriter);
        }
        if (authResult.getAuthDecision() != null) {
            String authDecision = authResult.getAuthDecision();
            awsJsonWriter.name("authDecision");
            awsJsonWriter.value(authDecision);
        }
        if (authResult.getMissingContextValues() != null) {
            List<String> missingContextValues = authResult.getMissingContextValues();
            awsJsonWriter.name("missingContextValues");
            awsJsonWriter.beginArray();
            for (String str : missingContextValues) {
                if (str != null) {
                    awsJsonWriter.value(str);
                }
            }
            awsJsonWriter.endArray();
        }
        awsJsonWriter.endObject();
    }
}
