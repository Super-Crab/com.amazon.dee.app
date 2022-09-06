package com.amazonaws.services.mobileanalytics.model.transform;

import com.amazon.alexa.voice.tta.Constants;
import com.amazonaws.services.mobileanalytics.model.Session;
import com.amazonaws.util.json.AwsJsonWriter;
@Deprecated
/* loaded from: classes13.dex */
class SessionJsonMarshaller {
    private static SessionJsonMarshaller instance;

    SessionJsonMarshaller() {
    }

    public static SessionJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new SessionJsonMarshaller();
        }
        return instance;
    }

    public void marshall(Session session, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (session.getId() != null) {
            String id = session.getId();
            awsJsonWriter.name("id");
            awsJsonWriter.value(id);
        }
        if (session.getDuration() != null) {
            Long duration = session.getDuration();
            awsJsonWriter.name("duration");
            awsJsonWriter.value(duration);
        }
        if (session.getStartTimestamp() != null) {
            String startTimestamp = session.getStartTimestamp();
            awsJsonWriter.name(Constants.IntentParameters.START_TIMESTAMP);
            awsJsonWriter.value(startTimestamp);
        }
        if (session.getStopTimestamp() != null) {
            String stopTimestamp = session.getStopTimestamp();
            awsJsonWriter.name("stopTimestamp");
            awsJsonWriter.value(stopTimestamp);
        }
        awsJsonWriter.endObject();
    }
}
