package com.amazonaws.services.logs.model.transform;

import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.delivery.DefaultDeliveryClient;
import com.amazonaws.services.logs.model.GetLogEventsResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
public class GetLogEventsResultJsonUnmarshaller implements Unmarshaller<GetLogEventsResult, JsonUnmarshallerContext> {
    private static GetLogEventsResultJsonUnmarshaller instance;

    public static GetLogEventsResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new GetLogEventsResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public GetLogEventsResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        GetLogEventsResult getLogEventsResult = new GetLogEventsResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals(DefaultDeliveryClient.EVENTS_DIRECTORY)) {
                getLogEventsResult.setEvents(new ListUnmarshaller(OutputLogEventJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("nextForwardToken")) {
                getLogEventsResult.setNextForwardToken(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("nextBackwardToken")) {
                getLogEventsResult.setNextBackwardToken(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return getLogEventsResult;
    }
}
