package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.CreateRoleAliasResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
public class CreateRoleAliasResultJsonUnmarshaller implements Unmarshaller<CreateRoleAliasResult, JsonUnmarshallerContext> {
    private static CreateRoleAliasResultJsonUnmarshaller instance;

    public static CreateRoleAliasResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new CreateRoleAliasResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public CreateRoleAliasResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        CreateRoleAliasResult createRoleAliasResult = new CreateRoleAliasResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("roleAlias")) {
                createRoleAliasResult.setRoleAlias(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("roleAliasArn")) {
                createRoleAliasResult.setRoleAliasArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return createRoleAliasResult;
    }
}
