package com.amazon.alexa.presence.eventbus;

import android.content.Context;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.Subscriber;
import com.amazon.alexa.presence.Presence;
import com.amazon.alexa.presence.PresenceController;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.metrics.MetricsServiceV2;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import javax.inject.Inject;
/* loaded from: classes9.dex */
public class PresenceSubscriber implements Subscriber {
    @VisibleForTesting
    static final String EVENT_ACTION_PRESENCE_DISABLE = "disable";
    @VisibleForTesting
    static final String EVENT_ACTION_PRESENCE_ENABLE = "enable";
    private static final String PROFILE_SELECTION_MESSAGE_TYPE = "identity:profile:changed";
    private static final String SIGN_IN_MESSAGE_TYPE = "oobe:profile:selected";
    private static final String SIGN_OUT_MESSAGE_TYPE = "identity:signOut:success";
    private final Context context;
    private final PresenceController controller;
    private final EventMessageFilter eventMessageFilter;
    private final MetricsServiceV2 metricsServiceV2;
    private final UUID subscriberId = UUID.randomUUID();
    private static final String TAG = Presence.tag();
    @VisibleForTesting
    public static final String EVENT_FILTER = "presence::message";
    private static final List<String> INTERESTING_EVENT_TYPES = Arrays.asList(EVENT_FILTER, "identity:signOut:success", "oobe:profile:selected", "identity:profile:changed");

    @Inject
    public PresenceSubscriber(Context context, EventMessageFilter eventMessageFilter, PresenceController presenceController, MetricsServiceV2 metricsServiceV2) {
        this.context = context;
        this.eventMessageFilter = eventMessageFilter;
        this.controller = presenceController;
        this.metricsServiceV2 = metricsServiceV2;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void handleMessage(Message message) {
        char c;
        logMessage(message);
        String eventType = message.getEventType();
        switch (eventType.hashCode()) {
            case -1717819618:
                if (eventType.equals("identity:signOut:success")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 660199207:
                if (eventType.equals("identity:profile:changed")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 1468051907:
                if (eventType.equals("oobe:profile:selected")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 1582964268:
                if (eventType.equals(EVENT_FILTER)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c == 0) {
            handlePresenceEvent(message);
        } else if (c == 1 || c == 2) {
            this.controller.restorePresenceState(this.context);
        } else if (c != 3) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("No-op, unknown event type passed filter : ");
            outline107.append(message.getEventType());
            outline107.toString();
        } else {
            this.controller.clearPresenceDomains(this.context);
            this.controller.disablePresence(this.context, false);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0081, code lost:
        if (r0.equals(com.amazon.alexa.presence.eventbus.PresenceSubscriber.EVENT_ACTION_PRESENCE_ENABLE) != false) goto L13;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void handlePresenceEvent(com.amazon.alexa.eventbus.api.Message r10) {
        /*
            r9 = this;
            java.lang.String r10 = r10.getPayloadAsString()
            com.dee.app.metrics.MetricsServiceV2 r0 = r9.metricsServiceV2
            java.lang.String r1 = "invalid_message_format"
            java.lang.String r2 = "presence_subscriber_message_received"
            com.amazon.alexa.presence.utils.MetricsUtil.recordZeroCount(r0, r1, r2)
            com.dee.app.metrics.MetricsServiceV2 r0 = r9.metricsServiceV2
            java.lang.String r3 = "presence_enable_message"
            com.amazon.alexa.presence.utils.MetricsUtil.recordZeroCount(r0, r3, r2)
            com.dee.app.metrics.MetricsServiceV2 r0 = r9.metricsServiceV2
            java.lang.String r3 = "presence_disable_message"
            com.amazon.alexa.presence.utils.MetricsUtil.recordZeroCount(r0, r3, r2)
            com.dee.app.metrics.MetricsServiceV2 r0 = r9.metricsServiceV2
            java.lang.String r3 = "invalid_message"
            com.amazon.alexa.presence.utils.MetricsUtil.recordZeroCount(r0, r3, r2)
            com.dee.app.metrics.MetricsServiceV2 r0 = r9.metricsServiceV2
            java.lang.String r4 = "persistent_local_storage"
            java.lang.String r5 = "add_presence_domain"
            com.amazon.alexa.presence.utils.MetricsUtil.recordZeroCount(r0, r5, r4)
            com.dee.app.metrics.MetricsServiceV2 r0 = r9.metricsServiceV2
            java.lang.String r5 = "remove_presence_domain"
            com.amazon.alexa.presence.utils.MetricsUtil.recordZeroCount(r0, r5, r4)
            com.dee.app.metrics.MetricsServiceV2 r0 = r9.metricsServiceV2
            java.lang.String r4 = "start_scanning_event_bus"
            java.lang.String r5 = "start_scanning_workflow"
            com.amazon.alexa.presence.utils.MetricsUtil.recordZeroCount(r0, r4, r5)
            com.dee.app.metrics.MetricsServiceV2 r0 = r9.metricsServiceV2
            java.lang.String r4 = "stop_scanning_event_bus"
            java.lang.String r5 = "stop_scanning_workflow"
            com.amazon.alexa.presence.utils.MetricsUtil.recordZeroCount(r0, r4, r5)
            java.lang.String r0 = "::"
            java.lang.String[] r0 = r10.split(r0)
            int r4 = r0.length
            r5 = 2
            if (r4 == r5) goto L5b
            java.lang.String r10 = com.amazon.alexa.presence.eventbus.PresenceSubscriber.TAG
            java.lang.String r0 = "Incorrect event bus message format. No-op."
            android.util.Log.w(r10, r0)
            com.dee.app.metrics.MetricsServiceV2 r10 = r9.metricsServiceV2
            com.amazon.alexa.presence.utils.MetricsUtil.recordCount(r10, r1, r2)
            return
        L5b:
            r1 = 0
            r4 = r0[r1]
            r5 = 1
            r0 = r0[r5]
            r6 = -1
            int r7 = r0.hashCode()
            r8 = -1298848381(0xffffffffb2952583, float:-1.7362941E-8)
            if (r7 == r8) goto L7b
            r1 = 1671308008(0x639e22e8, float:5.8342016E21)
            if (r7 == r1) goto L71
            goto L84
        L71:
            java.lang.String r1 = "disable"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L84
            r1 = r5
            goto L85
        L7b:
            java.lang.String r7 = "enable"
            boolean r0 = r0.equals(r7)
            if (r0 == 0) goto L84
            goto L85
        L84:
            r1 = r6
        L85:
            if (r1 == 0) goto La3
            if (r1 == r5) goto L94
            java.lang.String r0 = "No-op due to invalid action. Payload : "
            com.android.tools.r8.GeneratedOutlineSupport1.outline158(r0, r10)
            com.dee.app.metrics.MetricsServiceV2 r10 = r9.metricsServiceV2
            com.amazon.alexa.presence.utils.MetricsUtil.recordCount(r10, r3, r2)
            goto Lb1
        L94:
            java.lang.String r10 = com.amazon.alexa.presence.eventbus.PresenceSubscriber.TAG
            java.lang.String r0 = "Stop scan action event observed."
            android.util.Log.i(r10, r0)
            com.amazon.alexa.presence.PresenceController r10 = r9.controller
            android.content.Context r0 = r9.context
            r10.disablePresenceDomain(r0, r4)
            goto Lb1
        La3:
            java.lang.String r10 = com.amazon.alexa.presence.eventbus.PresenceSubscriber.TAG
            java.lang.String r0 = "Start scan action event observed."
            android.util.Log.i(r10, r0)
            com.amazon.alexa.presence.PresenceController r10 = r9.controller
            android.content.Context r0 = r9.context
            r10.enablePresenceDomain(r0, r4)
        Lb1:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.presence.eventbus.PresenceSubscriber.handlePresenceEvent(com.amazon.alexa.eventbus.api.Message):void");
    }

    private static void logMessage(@NonNull Message message) {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Message event type: ");
        outline107.append(message.getEventType());
        outline107.toString();
        String str = "Message source name: " + message.getSource().name();
        String str2 = "Message payload: " + message.getPayloadAsString();
    }

    @Override // com.amazon.alexa.eventbus.api.Subscriber
    public UUID getUUID() {
        return this.subscriberId;
    }

    @Override // com.amazon.alexa.eventbus.api.Subscriber
    public void onMessageReceived(@NonNull Message message) {
        try {
            handleMessage(message);
        } catch (Throwable th) {
            Log.e(TAG, "Error handling message", th);
        }
    }

    @Override // com.amazon.alexa.eventbus.api.Subscriber
    public boolean supportsMessage(@NonNull Message message) {
        return this.eventMessageFilter.isMatch(message) || INTERESTING_EVENT_TYPES.contains(message.getEventType());
    }
}
