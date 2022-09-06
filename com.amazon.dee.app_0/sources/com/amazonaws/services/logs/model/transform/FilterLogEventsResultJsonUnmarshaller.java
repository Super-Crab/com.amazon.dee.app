package com.amazonaws.services.logs.model.transform;

import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.delivery.DefaultDeliveryClient;
import com.amazonaws.services.logs.model.FilterLogEventsResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
public class FilterLogEventsResultJsonUnmarshaller implements Unmarshaller<FilterLogEventsResult, JsonUnmarshallerContext> {
    private static FilterLogEventsResultJsonUnmarshaller instance;

    public static FilterLogEventsResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new FilterLogEventsResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public FilterLogEventsResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        FilterLogEventsResult filterLogEventsResult = new FilterLogEventsResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals(DefaultDeliveryClient.EVENTS_DIRECTORY)) {
                filterLogEventsResult.setEvents(new ListUnmarshaller(FilteredLogEventJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("searchedLogStreams")) {
                filterLogEventsResult.setSearchedLogStreams(new ListUnmarshaller(SearchedLogStreamJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("nextToken")) {
                filterLogEventsResult.setNextToken(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return filterLogEventsResult;
    }
}
