package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.OTAUpdateSummary;
import com.amazonaws.util.json.AwsJsonWriter;
import java.util.Date;
/* loaded from: classes13.dex */
class OTAUpdateSummaryJsonMarshaller {
    private static OTAUpdateSummaryJsonMarshaller instance;

    OTAUpdateSummaryJsonMarshaller() {
    }

    public static OTAUpdateSummaryJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new OTAUpdateSummaryJsonMarshaller();
        }
        return instance;
    }

    public void marshall(OTAUpdateSummary oTAUpdateSummary, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (oTAUpdateSummary.getOtaUpdateId() != null) {
            String otaUpdateId = oTAUpdateSummary.getOtaUpdateId();
            awsJsonWriter.name("otaUpdateId");
            awsJsonWriter.value(otaUpdateId);
        }
        if (oTAUpdateSummary.getOtaUpdateArn() != null) {
            String otaUpdateArn = oTAUpdateSummary.getOtaUpdateArn();
            awsJsonWriter.name("otaUpdateArn");
            awsJsonWriter.value(otaUpdateArn);
        }
        if (oTAUpdateSummary.getCreationDate() != null) {
            Date creationDate = oTAUpdateSummary.getCreationDate();
            awsJsonWriter.name("creationDate");
            awsJsonWriter.value(creationDate);
        }
        awsJsonWriter.endObject();
    }
}
