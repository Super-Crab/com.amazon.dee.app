package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.SalesforceAction;
import com.amazonaws.util.json.AwsJsonWriter;
/* loaded from: classes13.dex */
class SalesforceActionJsonMarshaller {
    private static SalesforceActionJsonMarshaller instance;

    SalesforceActionJsonMarshaller() {
    }

    public static SalesforceActionJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new SalesforceActionJsonMarshaller();
        }
        return instance;
    }

    public void marshall(SalesforceAction salesforceAction, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (salesforceAction.getToken() != null) {
            String token = salesforceAction.getToken();
            awsJsonWriter.name("token");
            awsJsonWriter.value(token);
        }
        if (salesforceAction.getUrl() != null) {
            String url = salesforceAction.getUrl();
            awsJsonWriter.name("url");
            awsJsonWriter.value(url);
        }
        awsJsonWriter.endObject();
    }
}
