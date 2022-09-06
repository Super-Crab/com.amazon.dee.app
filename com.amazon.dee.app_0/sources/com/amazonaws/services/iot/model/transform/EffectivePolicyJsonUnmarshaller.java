package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.EffectivePolicy;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
class EffectivePolicyJsonUnmarshaller implements Unmarshaller<EffectivePolicy, JsonUnmarshallerContext> {
    private static EffectivePolicyJsonUnmarshaller instance;

    EffectivePolicyJsonUnmarshaller() {
    }

    public static EffectivePolicyJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new EffectivePolicyJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public EffectivePolicy unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        EffectivePolicy effectivePolicy = new EffectivePolicy();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("policyName")) {
                effectivePolicy.setPolicyName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("policyArn")) {
                effectivePolicy.setPolicyArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("policyDocument")) {
                effectivePolicy.setPolicyDocument(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return effectivePolicy;
    }
}
