package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.DescribeBillingGroupResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
public class DescribeBillingGroupResultJsonUnmarshaller implements Unmarshaller<DescribeBillingGroupResult, JsonUnmarshallerContext> {
    private static DescribeBillingGroupResultJsonUnmarshaller instance;

    public static DescribeBillingGroupResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new DescribeBillingGroupResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public DescribeBillingGroupResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        DescribeBillingGroupResult describeBillingGroupResult = new DescribeBillingGroupResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("billingGroupName")) {
                describeBillingGroupResult.setBillingGroupName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("billingGroupId")) {
                describeBillingGroupResult.setBillingGroupId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("billingGroupArn")) {
                describeBillingGroupResult.setBillingGroupArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("version")) {
                describeBillingGroupResult.setVersion(SimpleTypeJsonUnmarshallers.LongJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("billingGroupProperties")) {
                describeBillingGroupResult.setBillingGroupProperties(BillingGroupPropertiesJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("billingGroupMetadata")) {
                describeBillingGroupResult.setBillingGroupMetadata(BillingGroupMetadataJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return describeBillingGroupResult;
    }
}
