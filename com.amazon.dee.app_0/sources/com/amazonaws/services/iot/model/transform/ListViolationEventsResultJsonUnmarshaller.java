package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.ListViolationEventsResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
public class ListViolationEventsResultJsonUnmarshaller implements Unmarshaller<ListViolationEventsResult, JsonUnmarshallerContext> {
    private static ListViolationEventsResultJsonUnmarshaller instance;

    public static ListViolationEventsResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ListViolationEventsResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public ListViolationEventsResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        ListViolationEventsResult listViolationEventsResult = new ListViolationEventsResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("violationEvents")) {
                listViolationEventsResult.setViolationEvents(new ListUnmarshaller(ViolationEventJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("nextToken")) {
                listViolationEventsResult.setNextToken(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return listViolationEventsResult;
    }
}
