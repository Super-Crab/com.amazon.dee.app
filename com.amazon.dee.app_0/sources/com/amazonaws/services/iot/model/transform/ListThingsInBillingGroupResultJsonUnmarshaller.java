package com.amazonaws.services.iot.model.transform;

import com.amazon.clouddrive.cdasdk.cds.common.PhotoSearchCategory;
import com.amazonaws.services.iot.model.ListThingsInBillingGroupResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
public class ListThingsInBillingGroupResultJsonUnmarshaller implements Unmarshaller<ListThingsInBillingGroupResult, JsonUnmarshallerContext> {
    private static ListThingsInBillingGroupResultJsonUnmarshaller instance;

    public static ListThingsInBillingGroupResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ListThingsInBillingGroupResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public ListThingsInBillingGroupResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        ListThingsInBillingGroupResult listThingsInBillingGroupResult = new ListThingsInBillingGroupResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals(PhotoSearchCategory.THINGS)) {
                listThingsInBillingGroupResult.setThings(new ListUnmarshaller(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("nextToken")) {
                listThingsInBillingGroupResult.setNextToken(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return listThingsInBillingGroupResult;
    }
}
