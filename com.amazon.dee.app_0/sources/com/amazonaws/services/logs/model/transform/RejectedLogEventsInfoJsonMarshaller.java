package com.amazonaws.services.logs.model.transform;

import com.amazonaws.services.logs.model.RejectedLogEventsInfo;
import com.amazonaws.util.json.AwsJsonWriter;
/* loaded from: classes13.dex */
class RejectedLogEventsInfoJsonMarshaller {
    private static RejectedLogEventsInfoJsonMarshaller instance;

    RejectedLogEventsInfoJsonMarshaller() {
    }

    public static RejectedLogEventsInfoJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new RejectedLogEventsInfoJsonMarshaller();
        }
        return instance;
    }

    public void marshall(RejectedLogEventsInfo rejectedLogEventsInfo, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (rejectedLogEventsInfo.getTooNewLogEventStartIndex() != null) {
            Integer tooNewLogEventStartIndex = rejectedLogEventsInfo.getTooNewLogEventStartIndex();
            awsJsonWriter.name("tooNewLogEventStartIndex");
            awsJsonWriter.value(tooNewLogEventStartIndex);
        }
        if (rejectedLogEventsInfo.getTooOldLogEventEndIndex() != null) {
            Integer tooOldLogEventEndIndex = rejectedLogEventsInfo.getTooOldLogEventEndIndex();
            awsJsonWriter.name("tooOldLogEventEndIndex");
            awsJsonWriter.value(tooOldLogEventEndIndex);
        }
        if (rejectedLogEventsInfo.getExpiredLogEventEndIndex() != null) {
            Integer expiredLogEventEndIndex = rejectedLogEventsInfo.getExpiredLogEventEndIndex();
            awsJsonWriter.name("expiredLogEventEndIndex");
            awsJsonWriter.value(expiredLogEventEndIndex);
        }
        awsJsonWriter.endObject();
    }
}
