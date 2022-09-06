package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.UpdateRoleAliasResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
public class UpdateRoleAliasResultJsonUnmarshaller implements Unmarshaller<UpdateRoleAliasResult, JsonUnmarshallerContext> {
    private static UpdateRoleAliasResultJsonUnmarshaller instance;

    public static UpdateRoleAliasResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new UpdateRoleAliasResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public UpdateRoleAliasResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        UpdateRoleAliasResult updateRoleAliasResult = new UpdateRoleAliasResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("roleAlias")) {
                updateRoleAliasResult.setRoleAlias(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("roleAliasArn")) {
                updateRoleAliasResult.setRoleAliasArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return updateRoleAliasResult;
    }
}
