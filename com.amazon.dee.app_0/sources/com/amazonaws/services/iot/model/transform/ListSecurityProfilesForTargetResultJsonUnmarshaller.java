package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.ListSecurityProfilesForTargetResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
public class ListSecurityProfilesForTargetResultJsonUnmarshaller implements Unmarshaller<ListSecurityProfilesForTargetResult, JsonUnmarshallerContext> {
    private static ListSecurityProfilesForTargetResultJsonUnmarshaller instance;

    public static ListSecurityProfilesForTargetResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ListSecurityProfilesForTargetResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public ListSecurityProfilesForTargetResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        ListSecurityProfilesForTargetResult listSecurityProfilesForTargetResult = new ListSecurityProfilesForTargetResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("securityProfileTargetMappings")) {
                listSecurityProfilesForTargetResult.setSecurityProfileTargetMappings(new ListUnmarshaller(SecurityProfileTargetMappingJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("nextToken")) {
                listSecurityProfilesForTargetResult.setNextToken(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return listSecurityProfilesForTargetResult;
    }
}
