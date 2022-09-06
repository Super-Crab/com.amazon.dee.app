package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.ListIndicesResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
public class ListIndicesResultJsonUnmarshaller implements Unmarshaller<ListIndicesResult, JsonUnmarshallerContext> {
    private static ListIndicesResultJsonUnmarshaller instance;

    public static ListIndicesResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ListIndicesResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public ListIndicesResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        ListIndicesResult listIndicesResult = new ListIndicesResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("indexNames")) {
                listIndicesResult.setIndexNames(new ListUnmarshaller(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("nextToken")) {
                listIndicesResult.setNextToken(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return listIndicesResult;
    }
}
