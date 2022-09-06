package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.ExplicitDeny;
import com.amazonaws.services.iot.model.Policy;
import com.amazonaws.util.json.AwsJsonWriter;
import java.util.List;
/* loaded from: classes13.dex */
class ExplicitDenyJsonMarshaller {
    private static ExplicitDenyJsonMarshaller instance;

    ExplicitDenyJsonMarshaller() {
    }

    public static ExplicitDenyJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new ExplicitDenyJsonMarshaller();
        }
        return instance;
    }

    public void marshall(ExplicitDeny explicitDeny, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (explicitDeny.getPolicies() != null) {
            List<Policy> policies = explicitDeny.getPolicies();
            awsJsonWriter.name("policies");
            awsJsonWriter.beginArray();
            for (Policy policy : policies) {
                if (policy != null) {
                    PolicyJsonMarshaller.getInstance().marshall(policy, awsJsonWriter);
                }
            }
            awsJsonWriter.endArray();
        }
        awsJsonWriter.endObject();
    }
}
