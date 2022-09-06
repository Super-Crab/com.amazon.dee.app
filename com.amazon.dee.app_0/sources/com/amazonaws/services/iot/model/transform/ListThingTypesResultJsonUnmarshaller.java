package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.ListThingTypesResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
public class ListThingTypesResultJsonUnmarshaller implements Unmarshaller<ListThingTypesResult, JsonUnmarshallerContext> {
    private static ListThingTypesResultJsonUnmarshaller instance;

    public static ListThingTypesResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ListThingTypesResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public ListThingTypesResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        ListThingTypesResult listThingTypesResult = new ListThingTypesResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("thingTypes")) {
                listThingTypesResult.setThingTypes(new ListUnmarshaller(ThingTypeDefinitionJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("nextToken")) {
                listThingTypesResult.setNextToken(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return listThingTypesResult;
    }
}
