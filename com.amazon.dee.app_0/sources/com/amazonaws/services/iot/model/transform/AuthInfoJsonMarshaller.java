package com.amazonaws.services.iot.model.transform;

import com.amazon.alexa.handsfree.metrics.caching.JsonFields;
import com.amazonaws.services.iot.model.AuthInfo;
import com.amazonaws.util.json.AwsJsonWriter;
import java.util.List;
/* loaded from: classes13.dex */
class AuthInfoJsonMarshaller {
    private static AuthInfoJsonMarshaller instance;

    AuthInfoJsonMarshaller() {
    }

    public static AuthInfoJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new AuthInfoJsonMarshaller();
        }
        return instance;
    }

    public void marshall(AuthInfo authInfo, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (authInfo.getActionType() != null) {
            String actionType = authInfo.getActionType();
            awsJsonWriter.name(JsonFields.ACTION_TYPE);
            awsJsonWriter.value(actionType);
        }
        if (authInfo.getResources() != null) {
            List<String> resources = authInfo.getResources();
            awsJsonWriter.name("resources");
            awsJsonWriter.beginArray();
            for (String str : resources) {
                if (str != null) {
                    awsJsonWriter.value(str);
                }
            }
            awsJsonWriter.endArray();
        }
        awsJsonWriter.endObject();
    }
}
