package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.Denied;
import com.amazonaws.services.iot.model.ExplicitDeny;
import com.amazonaws.services.iot.model.ImplicitDeny;
import com.amazonaws.util.json.AwsJsonWriter;
/* loaded from: classes13.dex */
class DeniedJsonMarshaller {
    private static DeniedJsonMarshaller instance;

    DeniedJsonMarshaller() {
    }

    public static DeniedJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new DeniedJsonMarshaller();
        }
        return instance;
    }

    public void marshall(Denied denied, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (denied.getImplicitDeny() != null) {
            ImplicitDeny implicitDeny = denied.getImplicitDeny();
            awsJsonWriter.name("implicitDeny");
            ImplicitDenyJsonMarshaller.getInstance().marshall(implicitDeny, awsJsonWriter);
        }
        if (denied.getExplicitDeny() != null) {
            ExplicitDeny explicitDeny = denied.getExplicitDeny();
            awsJsonWriter.name("explicitDeny");
            ExplicitDenyJsonMarshaller.getInstance().marshall(explicitDeny, awsJsonWriter);
        }
        awsJsonWriter.endObject();
    }
}
