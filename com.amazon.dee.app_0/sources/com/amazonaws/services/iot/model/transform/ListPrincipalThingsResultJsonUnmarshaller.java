package com.amazonaws.services.iot.model.transform;

import com.amazon.clouddrive.cdasdk.cds.common.PhotoSearchCategory;
import com.amazonaws.services.iot.model.ListPrincipalThingsResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
public class ListPrincipalThingsResultJsonUnmarshaller implements Unmarshaller<ListPrincipalThingsResult, JsonUnmarshallerContext> {
    private static ListPrincipalThingsResultJsonUnmarshaller instance;

    public static ListPrincipalThingsResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ListPrincipalThingsResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public ListPrincipalThingsResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        ListPrincipalThingsResult listPrincipalThingsResult = new ListPrincipalThingsResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals(PhotoSearchCategory.THINGS)) {
                listPrincipalThingsResult.setThings(new ListUnmarshaller(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("nextToken")) {
                listPrincipalThingsResult.setNextToken(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return listPrincipalThingsResult;
    }
}
