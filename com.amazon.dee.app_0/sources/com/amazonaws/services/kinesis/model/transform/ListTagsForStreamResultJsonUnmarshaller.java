package com.amazonaws.services.kinesis.model.transform;

import com.amazonaws.services.kinesis.model.ListTagsForStreamResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
public class ListTagsForStreamResultJsonUnmarshaller implements Unmarshaller<ListTagsForStreamResult, JsonUnmarshallerContext> {
    private static ListTagsForStreamResultJsonUnmarshaller instance;

    public static ListTagsForStreamResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ListTagsForStreamResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public ListTagsForStreamResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        ListTagsForStreamResult listTagsForStreamResult = new ListTagsForStreamResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("Tags")) {
                listTagsForStreamResult.setTags(new ListUnmarshaller(TagJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("HasMoreTags")) {
                listTagsForStreamResult.setHasMoreTags(SimpleTypeJsonUnmarshallers.BooleanJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return listTagsForStreamResult;
    }
}
