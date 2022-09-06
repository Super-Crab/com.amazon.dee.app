package com.amazonaws.services.logs.model.transform;

import com.amazonaws.services.logs.model.ListTagsLogGroupResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.MapUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
public class ListTagsLogGroupResultJsonUnmarshaller implements Unmarshaller<ListTagsLogGroupResult, JsonUnmarshallerContext> {
    private static ListTagsLogGroupResultJsonUnmarshaller instance;

    public static ListTagsLogGroupResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ListTagsLogGroupResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public ListTagsLogGroupResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        ListTagsLogGroupResult listTagsLogGroupResult = new ListTagsLogGroupResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            if (reader.nextName().equals("tags")) {
                listTagsLogGroupResult.setTags(new MapUnmarshaller(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return listTagsLogGroupResult;
    }
}
