package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.CreateBillingGroupResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
public class CreateBillingGroupResultJsonUnmarshaller implements Unmarshaller<CreateBillingGroupResult, JsonUnmarshallerContext> {
    private static CreateBillingGroupResultJsonUnmarshaller instance;

    public static CreateBillingGroupResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new CreateBillingGroupResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public CreateBillingGroupResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        CreateBillingGroupResult createBillingGroupResult = new CreateBillingGroupResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("billingGroupName")) {
                createBillingGroupResult.setBillingGroupName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("billingGroupArn")) {
                createBillingGroupResult.setBillingGroupArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("billingGroupId")) {
                createBillingGroupResult.setBillingGroupId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return createBillingGroupResult;
    }
}
