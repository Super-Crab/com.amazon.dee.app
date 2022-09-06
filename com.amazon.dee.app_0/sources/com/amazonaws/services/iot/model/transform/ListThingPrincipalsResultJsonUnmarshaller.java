package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.ListThingPrincipalsResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
public class ListThingPrincipalsResultJsonUnmarshaller implements Unmarshaller<ListThingPrincipalsResult, JsonUnmarshallerContext> {
    private static ListThingPrincipalsResultJsonUnmarshaller instance;

    public static ListThingPrincipalsResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ListThingPrincipalsResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public ListThingPrincipalsResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        ListThingPrincipalsResult listThingPrincipalsResult = new ListThingPrincipalsResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            if (reader.nextName().equals("principals")) {
                listThingPrincipalsResult.setPrincipals(new ListUnmarshaller(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return listThingPrincipalsResult;
    }
}
