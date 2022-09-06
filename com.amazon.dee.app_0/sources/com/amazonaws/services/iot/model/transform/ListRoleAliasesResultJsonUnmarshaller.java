package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.ListRoleAliasesResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
public class ListRoleAliasesResultJsonUnmarshaller implements Unmarshaller<ListRoleAliasesResult, JsonUnmarshallerContext> {
    private static ListRoleAliasesResultJsonUnmarshaller instance;

    public static ListRoleAliasesResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ListRoleAliasesResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public ListRoleAliasesResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        ListRoleAliasesResult listRoleAliasesResult = new ListRoleAliasesResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("roleAliases")) {
                listRoleAliasesResult.setRoleAliases(new ListUnmarshaller(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("nextMarker")) {
                listRoleAliasesResult.setNextMarker(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return listRoleAliasesResult;
    }
}
