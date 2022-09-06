package com.amazon.alexa.mobilytics.event.serializer.protobufhandlers;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.mobilytics.event.EventType;
import com.amazon.alexa.mobilytics.event.MobilyticsEvent;
import com.amazon.alexa.mobilytics.event.operational.MobilyticsOperationalEvent;
import com.amazon.alexa.mobilytics.protobuf.MobilyticsMessageProto;
import com.amazon.alexa.mobilytics.protobuf.SessionProto;
import com.amazon.alexa.mobilytics.session.MobilyticsSession;
import com.amazon.alexa.mobilytics.session.SessionManager;
import com.amazon.alexa.mobilytics.util.Log;
import com.google.common.base.Preconditions;
import javax.inject.Inject;
import javax.inject.Singleton;
@Singleton
/* loaded from: classes9.dex */
public class SessionProtobufHandler implements ProtobufHandler {
    private static final String TAG = Log.tag(SessionProtobufHandler.class);
    private final SessionManager sessionManager;

    @Inject
    public SessionProtobufHandler(@NonNull SessionManager sessionManager) {
        this.sessionManager = (SessionManager) Preconditions.checkNotNull(sessionManager);
    }

    @Override // com.amazon.alexa.mobilytics.event.serializer.protobufhandlers.ProtobufHandler
    @Nullable
    public MobilyticsMessageProto serialize(@NonNull MobilyticsEvent mobilyticsEvent) {
        MobilyticsSession session = mobilyticsEvent.session();
        if (session == null || TextUtils.isEmpty(session.id())) {
            session = this.sessionManager.session();
        }
        SessionProto.Builder newBuilder = SessionProto.newBuilder();
        newBuilder.setStartTimestamp(session.startTime()).setDuration(session.elapsedTime());
        String id = session.id();
        if (id != null) {
            newBuilder.setSessionId(id);
        } else {
            Log.w(TAG, "MobilyticsSession id is null for event [%s]", mobilyticsEvent.getEventName());
        }
        Log.d(TAG, "Added sessionID [%s] to event [%s]", id, mobilyticsEvent.getEventName());
        if (mobilyticsEvent.getEventType().equals(EventType.OPERATIONAL)) {
            if (((MobilyticsOperationalEvent) mobilyticsEvent).getOperationalEventType().equals("session") && (session.state() == 0 || session.state() == 2)) {
                newBuilder.setStopTimestamp(session.stopTime());
            }
        } else {
            newBuilder.clearStopTimestamp();
        }
        return MobilyticsMessageProto.newBuilder().setSession(newBuilder).mo10084build();
    }
}
