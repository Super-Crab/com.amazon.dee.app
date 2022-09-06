package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.ListTargetsForSecurityProfileResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
public class ListTargetsForSecurityProfileResultJsonUnmarshaller implements Unmarshaller<ListTargetsForSecurityProfileResult, JsonUnmarshallerContext> {
    private static ListTargetsForSecurityProfileResultJsonUnmarshaller instance;

    public static ListTargetsForSecurityProfileResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ListTargetsForSecurityProfileResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public ListTargetsForSecurityProfileResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        ListTargetsForSecurityProfileResult listTargetsForSecurityProfileResult = new ListTargetsForSecurityProfileResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("securityProfileTargets")) {
                listTargetsForSecurityProfileResult.setSecurityProfileTargets(new ListUnmarshaller(SecurityProfileTargetJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("nextToken")) {
                listTargetsForSecurityProfileResult.setNextToken(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return listTargetsForSecurityProfileResult;
    }
}
