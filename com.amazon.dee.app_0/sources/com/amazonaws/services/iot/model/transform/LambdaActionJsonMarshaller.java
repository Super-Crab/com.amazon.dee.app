package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.LambdaAction;
import com.amazonaws.util.json.AwsJsonWriter;
/* loaded from: classes13.dex */
class LambdaActionJsonMarshaller {
    private static LambdaActionJsonMarshaller instance;

    LambdaActionJsonMarshaller() {
    }

    public static LambdaActionJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new LambdaActionJsonMarshaller();
        }
        return instance;
    }

    public void marshall(LambdaAction lambdaAction, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (lambdaAction.getFunctionArn() != null) {
            String functionArn = lambdaAction.getFunctionArn();
            awsJsonWriter.name("functionArn");
            awsJsonWriter.value(functionArn);
        }
        awsJsonWriter.endObject();
    }
}
