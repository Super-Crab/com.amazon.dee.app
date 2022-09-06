package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.GetEffectivePoliciesResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
public class GetEffectivePoliciesResultJsonUnmarshaller implements Unmarshaller<GetEffectivePoliciesResult, JsonUnmarshallerContext> {
    private static GetEffectivePoliciesResultJsonUnmarshaller instance;

    public static GetEffectivePoliciesResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new GetEffectivePoliciesResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public GetEffectivePoliciesResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        GetEffectivePoliciesResult getEffectivePoliciesResult = new GetEffectivePoliciesResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            if (reader.nextName().equals("effectivePolicies")) {
                getEffectivePoliciesResult.setEffectivePolicies(new ListUnmarshaller(EffectivePolicyJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return getEffectivePoliciesResult;
    }
}
