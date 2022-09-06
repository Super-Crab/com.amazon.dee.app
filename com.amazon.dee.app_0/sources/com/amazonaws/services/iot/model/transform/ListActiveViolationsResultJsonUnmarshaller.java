package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.ListActiveViolationsResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
public class ListActiveViolationsResultJsonUnmarshaller implements Unmarshaller<ListActiveViolationsResult, JsonUnmarshallerContext> {
    private static ListActiveViolationsResultJsonUnmarshaller instance;

    public static ListActiveViolationsResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ListActiveViolationsResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public ListActiveViolationsResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        ListActiveViolationsResult listActiveViolationsResult = new ListActiveViolationsResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("activeViolations")) {
                listActiveViolationsResult.setActiveViolations(new ListUnmarshaller(ActiveViolationJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("nextToken")) {
                listActiveViolationsResult.setNextToken(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return listActiveViolationsResult;
    }
}
