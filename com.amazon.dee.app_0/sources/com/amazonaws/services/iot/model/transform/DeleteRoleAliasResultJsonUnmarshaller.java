package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.DeleteRoleAliasResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;
/* loaded from: classes13.dex */
public class DeleteRoleAliasResultJsonUnmarshaller implements Unmarshaller<DeleteRoleAliasResult, JsonUnmarshallerContext> {
    private static DeleteRoleAliasResultJsonUnmarshaller instance;

    public static DeleteRoleAliasResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new DeleteRoleAliasResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public DeleteRoleAliasResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        return new DeleteRoleAliasResult();
    }
}
