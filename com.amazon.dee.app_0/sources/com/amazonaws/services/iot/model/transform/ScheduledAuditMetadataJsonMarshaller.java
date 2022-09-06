package com.amazonaws.services.iot.model.transform;

import com.amazon.alexa.audiopersonalization.constants.EventBusConstants;
import com.amazonaws.services.iot.model.ScheduledAuditMetadata;
import com.amazonaws.util.json.AwsJsonWriter;
/* loaded from: classes13.dex */
class ScheduledAuditMetadataJsonMarshaller {
    private static ScheduledAuditMetadataJsonMarshaller instance;

    ScheduledAuditMetadataJsonMarshaller() {
    }

    public static ScheduledAuditMetadataJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new ScheduledAuditMetadataJsonMarshaller();
        }
        return instance;
    }

    public void marshall(ScheduledAuditMetadata scheduledAuditMetadata, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (scheduledAuditMetadata.getScheduledAuditName() != null) {
            String scheduledAuditName = scheduledAuditMetadata.getScheduledAuditName();
            awsJsonWriter.name("scheduledAuditName");
            awsJsonWriter.value(scheduledAuditName);
        }
        if (scheduledAuditMetadata.getScheduledAuditArn() != null) {
            String scheduledAuditArn = scheduledAuditMetadata.getScheduledAuditArn();
            awsJsonWriter.name("scheduledAuditArn");
            awsJsonWriter.value(scheduledAuditArn);
        }
        if (scheduledAuditMetadata.getFrequency() != null) {
            String frequency = scheduledAuditMetadata.getFrequency();
            awsJsonWriter.name(EventBusConstants.FREQUENCY_KEY);
            awsJsonWriter.value(frequency);
        }
        if (scheduledAuditMetadata.getDayOfMonth() != null) {
            String dayOfMonth = scheduledAuditMetadata.getDayOfMonth();
            awsJsonWriter.name("dayOfMonth");
            awsJsonWriter.value(dayOfMonth);
        }
        if (scheduledAuditMetadata.getDayOfWeek() != null) {
            String dayOfWeek = scheduledAuditMetadata.getDayOfWeek();
            awsJsonWriter.name("dayOfWeek");
            awsJsonWriter.value(dayOfWeek);
        }
        awsJsonWriter.endObject();
    }
}
