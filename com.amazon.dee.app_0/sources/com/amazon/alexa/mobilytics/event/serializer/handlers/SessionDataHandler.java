package com.amazon.alexa.mobilytics.event.serializer.handlers;

import android.text.TextUtils;
import android.util.Pair;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.client.metrics.kinesis.session.client.AppDefaultSessionClient;
import com.amazon.alexa.mobilytics.event.EventType;
import com.amazon.alexa.mobilytics.event.MobilyticsEvent;
import com.amazon.alexa.mobilytics.event.operational.MobilyticsOperationalEvent;
import com.amazon.alexa.mobilytics.session.MobilyticsSession;
import com.amazon.alexa.mobilytics.session.SessionManager;
import com.amazon.alexa.mobilytics.util.Log;
import com.amazon.alexa.voice.tta.Constants;
import com.google.common.base.Preconditions;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.json.JSONException;
import org.json.JSONObject;
@Singleton
/* loaded from: classes9.dex */
public class SessionDataHandler implements DataHandler {
    private static final String KEY = "session";
    private static final String TAG = Log.tag(SessionDataHandler.class);
    private final SessionManager sessionManager;

    @Inject
    public SessionDataHandler(@NonNull SessionManager sessionManager) {
        this.sessionManager = (SessionManager) Preconditions.checkNotNull(sessionManager);
    }

    @Override // com.amazon.alexa.mobilytics.event.serializer.handlers.DataHandler
    @Nullable
    public Pair<String, JSONObject> process(@NonNull MobilyticsEvent mobilyticsEvent) throws JSONException {
        MobilyticsSession session = mobilyticsEvent.session();
        if (session == null || TextUtils.isEmpty(session.id())) {
            session = this.sessionManager.session();
        }
        JSONObject put = new JSONObject().put(Constants.IntentParameters.START_TIMESTAMP, session.startTime()).put("duration", session.elapsedTime()).put(AppDefaultSessionClient.CRASH_REPORTER_SESSION_ID_KEY, session.id());
        if (mobilyticsEvent.getEventType().equals(EventType.OPERATIONAL) && ((MobilyticsOperationalEvent) mobilyticsEvent).getOperationalEventType().equals("session") && (session.state() == 0 || session.state() == 2)) {
            put.put("stopTimestamp", session.stopTime());
        }
        Log.d(TAG, "Added sessionID [%s] to event [%s]", session.id(), mobilyticsEvent.getEventName());
        return Pair.create("session", put);
    }
}
