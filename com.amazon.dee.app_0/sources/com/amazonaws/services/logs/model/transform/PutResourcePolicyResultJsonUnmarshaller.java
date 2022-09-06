package com.amazonaws.services.logs.model.transform;

import com.amazonaws.services.logs.model.PutResourcePolicyResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
public class PutResourcePolicyResultJsonUnmarshaller implements Unmarshaller<PutResourcePolicyResult, JsonUnmarshallerContext> {
    private static PutResourcePolicyResultJsonUnmarshaller instance;

    public static PutResourcePolicyResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new PutResourcePolicyResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public PutResourcePolicyResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        PutResourcePolicyResult putResourcePolicyResult = new PutResourcePolicyResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            if (reader.nextName().equals("resourcePolicy")) {
                putResourcePolicyResult.setResourcePolicy(ResourcePolicyJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return putResourcePolicyResult;
    }
}
