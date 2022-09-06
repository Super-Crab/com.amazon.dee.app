package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.ListStreamsResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
public class ListStreamsResultJsonUnmarshaller implements Unmarshaller<ListStreamsResult, JsonUnmarshallerContext> {
    private static ListStreamsResultJsonUnmarshaller instance;

    public static ListStreamsResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ListStreamsResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public ListStreamsResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        ListStreamsResult listStreamsResult = new ListStreamsResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("streams")) {
                listStreamsResult.setStreams(new ListUnmarshaller(StreamSummaryJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("nextToken")) {
                listStreamsResult.setNextToken(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return listStreamsResult;
    }
}
