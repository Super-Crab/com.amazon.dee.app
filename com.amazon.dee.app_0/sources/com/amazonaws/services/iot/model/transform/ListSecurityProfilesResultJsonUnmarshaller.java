package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.ListSecurityProfilesResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
public class ListSecurityProfilesResultJsonUnmarshaller implements Unmarshaller<ListSecurityProfilesResult, JsonUnmarshallerContext> {
    private static ListSecurityProfilesResultJsonUnmarshaller instance;

    public static ListSecurityProfilesResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ListSecurityProfilesResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public ListSecurityProfilesResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        ListSecurityProfilesResult listSecurityProfilesResult = new ListSecurityProfilesResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("securityProfileIdentifiers")) {
                listSecurityProfilesResult.setSecurityProfileIdentifiers(new ListUnmarshaller(SecurityProfileIdentifierJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("nextToken")) {
                listSecurityProfilesResult.setNextToken(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return listSecurityProfilesResult;
    }
}
