package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.ListPrincipalPoliciesResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
public class ListPrincipalPoliciesResultJsonUnmarshaller implements Unmarshaller<ListPrincipalPoliciesResult, JsonUnmarshallerContext> {
    private static ListPrincipalPoliciesResultJsonUnmarshaller instance;

    public static ListPrincipalPoliciesResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ListPrincipalPoliciesResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public ListPrincipalPoliciesResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        ListPrincipalPoliciesResult listPrincipalPoliciesResult = new ListPrincipalPoliciesResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("policies")) {
                listPrincipalPoliciesResult.setPolicies(new ListUnmarshaller(PolicyJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("nextMarker")) {
                listPrincipalPoliciesResult.setNextMarker(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return listPrincipalPoliciesResult;
    }
}
