package com.amazonaws.services.iot.model.transform;

import com.amazon.alexa.audiopersonalization.constants.EventBusConstants;
import com.amazonaws.services.iot.model.ScheduledAuditMetadata;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
class ScheduledAuditMetadataJsonUnmarshaller implements Unmarshaller<ScheduledAuditMetadata, JsonUnmarshallerContext> {
    private static ScheduledAuditMetadataJsonUnmarshaller instance;

    ScheduledAuditMetadataJsonUnmarshaller() {
    }

    public static ScheduledAuditMetadataJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ScheduledAuditMetadataJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public ScheduledAuditMetadata unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        ScheduledAuditMetadata scheduledAuditMetadata = new ScheduledAuditMetadata();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("scheduledAuditName")) {
                scheduledAuditMetadata.setScheduledAuditName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("scheduledAuditArn")) {
                scheduledAuditMetadata.setScheduledAuditArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals(EventBusConstants.FREQUENCY_KEY)) {
                scheduledAuditMetadata.setFrequency(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("dayOfMonth")) {
                scheduledAuditMetadata.setDayOfMonth(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("dayOfWeek")) {
                scheduledAuditMetadata.setDayOfWeek(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return scheduledAuditMetadata;
    }
}
