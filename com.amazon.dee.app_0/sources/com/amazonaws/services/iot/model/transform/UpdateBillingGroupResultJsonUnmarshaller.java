package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.UpdateBillingGroupResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
public class UpdateBillingGroupResultJsonUnmarshaller implements Unmarshaller<UpdateBillingGroupResult, JsonUnmarshallerContext> {
    private static UpdateBillingGroupResultJsonUnmarshaller instance;

    public static UpdateBillingGroupResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new UpdateBillingGroupResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public UpdateBillingGroupResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        UpdateBillingGroupResult updateBillingGroupResult = new UpdateBillingGroupResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            if (reader.nextName().equals("version")) {
                updateBillingGroupResult.setVersion(SimpleTypeJsonUnmarshallers.LongJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return updateBillingGroupResult;
    }
}
