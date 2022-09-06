package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.ListTargetsForPolicyResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
public class ListTargetsForPolicyResultJsonUnmarshaller implements Unmarshaller<ListTargetsForPolicyResult, JsonUnmarshallerContext> {
    private static ListTargetsForPolicyResultJsonUnmarshaller instance;

    public static ListTargetsForPolicyResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ListTargetsForPolicyResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public ListTargetsForPolicyResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        ListTargetsForPolicyResult listTargetsForPolicyResult = new ListTargetsForPolicyResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("targets")) {
                listTargetsForPolicyResult.setTargets(new ListUnmarshaller(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("nextMarker")) {
                listTargetsForPolicyResult.setNextMarker(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return listTargetsForPolicyResult;
    }
}
