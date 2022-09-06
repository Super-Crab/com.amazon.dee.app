package com.amazonaws.services.mobileanalytics.model.transform;

import com.amazon.alexa.voice.tta.Constants;
import com.amazonaws.services.mobileanalytics.model.Session;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
@Deprecated
/* loaded from: classes13.dex */
class SessionJsonUnmarshaller implements Unmarshaller<Session, JsonUnmarshallerContext> {
    private static SessionJsonUnmarshaller instance;

    SessionJsonUnmarshaller() {
    }

    public static SessionJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new SessionJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public Session unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        Session session = new Session();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("id")) {
                session.setId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("duration")) {
                session.setDuration(SimpleTypeJsonUnmarshallers.LongJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals(Constants.IntentParameters.START_TIMESTAMP)) {
                session.setStartTimestamp(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("stopTimestamp")) {
                session.setStopTimestamp(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return session;
    }
}
