package com.amazonaws.services.logs.model.transform;

import com.amazonaws.services.logs.model.SearchedLogStream;
import com.amazonaws.util.json.AwsJsonWriter;
/* loaded from: classes13.dex */
class SearchedLogStreamJsonMarshaller {
    private static SearchedLogStreamJsonMarshaller instance;

    SearchedLogStreamJsonMarshaller() {
    }

    public static SearchedLogStreamJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new SearchedLogStreamJsonMarshaller();
        }
        return instance;
    }

    public void marshall(SearchedLogStream searchedLogStream, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (searchedLogStream.getLogStreamName() != null) {
            String logStreamName = searchedLogStream.getLogStreamName();
            awsJsonWriter.name("logStreamName");
            awsJsonWriter.value(logStreamName);
        }
        if (searchedLogStream.getSearchedCompletely() != null) {
            Boolean searchedCompletely = searchedLogStream.getSearchedCompletely();
            awsJsonWriter.name("searchedCompletely");
            awsJsonWriter.value(searchedCompletely.booleanValue());
        }
        awsJsonWriter.endObject();
    }
}
