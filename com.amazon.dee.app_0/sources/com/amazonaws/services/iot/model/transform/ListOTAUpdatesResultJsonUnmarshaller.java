package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.ListOTAUpdatesResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
public class ListOTAUpdatesResultJsonUnmarshaller implements Unmarshaller<ListOTAUpdatesResult, JsonUnmarshallerContext> {
    private static ListOTAUpdatesResultJsonUnmarshaller instance;

    public static ListOTAUpdatesResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ListOTAUpdatesResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public ListOTAUpdatesResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        ListOTAUpdatesResult listOTAUpdatesResult = new ListOTAUpdatesResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("otaUpdates")) {
                listOTAUpdatesResult.setOtaUpdates(new ListUnmarshaller(OTAUpdateSummaryJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("nextToken")) {
                listOTAUpdatesResult.setNextToken(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return listOTAUpdatesResult;
    }
}
