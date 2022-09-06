package com.amazonaws.services.logs.model.transform;

import com.amazonaws.services.logs.model.SearchedLogStream;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
class SearchedLogStreamJsonUnmarshaller implements Unmarshaller<SearchedLogStream, JsonUnmarshallerContext> {
    private static SearchedLogStreamJsonUnmarshaller instance;

    SearchedLogStreamJsonUnmarshaller() {
    }

    public static SearchedLogStreamJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new SearchedLogStreamJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public SearchedLogStream unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        SearchedLogStream searchedLogStream = new SearchedLogStream();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("logStreamName")) {
                searchedLogStream.setLogStreamName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("searchedCompletely")) {
                searchedLogStream.setSearchedCompletely(SimpleTypeJsonUnmarshallers.BooleanJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return searchedLogStream;
    }
}
