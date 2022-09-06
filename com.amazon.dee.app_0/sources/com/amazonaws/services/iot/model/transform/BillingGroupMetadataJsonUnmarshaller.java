package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.BillingGroupMetadata;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
class BillingGroupMetadataJsonUnmarshaller implements Unmarshaller<BillingGroupMetadata, JsonUnmarshallerContext> {
    private static BillingGroupMetadataJsonUnmarshaller instance;

    BillingGroupMetadataJsonUnmarshaller() {
    }

    public static BillingGroupMetadataJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new BillingGroupMetadataJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public BillingGroupMetadata unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        BillingGroupMetadata billingGroupMetadata = new BillingGroupMetadata();
        reader.beginObject();
        while (reader.hasNext()) {
            if (reader.nextName().equals("creationDate")) {
                billingGroupMetadata.setCreationDate(SimpleTypeJsonUnmarshallers.DateJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return billingGroupMetadata;
    }
}
