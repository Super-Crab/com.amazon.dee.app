package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.BillingGroupMetadata;
import com.amazonaws.util.json.AwsJsonWriter;
import java.util.Date;
/* loaded from: classes13.dex */
class BillingGroupMetadataJsonMarshaller {
    private static BillingGroupMetadataJsonMarshaller instance;

    BillingGroupMetadataJsonMarshaller() {
    }

    public static BillingGroupMetadataJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new BillingGroupMetadataJsonMarshaller();
        }
        return instance;
    }

    public void marshall(BillingGroupMetadata billingGroupMetadata, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (billingGroupMetadata.getCreationDate() != null) {
            Date creationDate = billingGroupMetadata.getCreationDate();
            awsJsonWriter.name("creationDate");
            awsJsonWriter.value(creationDate);
        }
        awsJsonWriter.endObject();
    }
}
