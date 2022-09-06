package com.amazon.alexa.presence.eventbus;

import android.content.Context;
import android.util.Log;
import androidx.annotation.NonNull;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.Subscriber;
import com.amazon.alexa.presence.Presence;
import com.amazon.alexa.presence.PresenceController;
import com.amazon.alexa.presence.models.EventBusNotificationModel;
import com.amazon.alexa.presence.models.NotificationStateModel;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.metrics.MetricsServiceV2;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.util.UUID;
import javax.inject.Inject;
/* loaded from: classes9.dex */
public class PushNotificationSubscriber implements Subscriber {
    private static final String EVENT_ACTION_PRESENCE_DISABLE = "disable";
    private static final String EVENT_ACTION_PRESENCE_ENABLE = "enable";
    public static final String EVENT_FILTER = "presence::cloud::message::v1";
    private final Context context;
    private final EventMessageFilter eventMessageFilter;
    private final MetricsServiceV2 metricsServiceV2;
    private final PresenceController presenceController;
    private final UUID subscriberId = UUID.randomUUID();
    private static final String TAG = Presence.tag();
    private static final Gson GSON = new Gson();

    @Inject
    public PushNotificationSubscriber(Context context, EventMessageFilter eventMessageFilter, MetricsServiceV2 metricsServiceV2, PresenceController presenceController) {
        this.context = context;
        this.eventMessageFilter = eventMessageFilter;
        this.metricsServiceV2 = metricsServiceV2;
        this.presenceController = presenceController;
    }

    private String getRequestedPresenceStateFromNotificationEvent(String str) {
        try {
            for (EventBusNotificationModel eventBusNotificationModel : (EventBusNotificationModel[]) GSON.fromJson(str, (Class<Object>) EventBusNotificationModel[].class)) {
                NotificationStateModel state = eventBusNotificationModel.getState();
                if (state != null) {
                    return state.getOriginal_uri();
                }
            }
            return "";
        } catch (JsonSyntaxException e) {
            Log.w(TAG, String.format("Incompatible JSON syntax %s provided in input payload. Ignoring.", str), e);
            return "";
        }
    }

    @Override // com.amazon.alexa.eventbus.api.Subscriber
    public UUID getUUID() {
        return this.subscriberId;
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0069, code lost:
        if (r0.equals(com.amazon.alexa.presence.eventbus.PushNotificationSubscriber.EVENT_ACTION_PRESENCE_DISABLE) == false) goto L20;
     */
    @Override // com.amazon.alexa.eventbus.api.Subscriber
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onMessageReceived(@androidx.annotation.NonNull com.amazon.alexa.eventbus.api.Message r11) {
        /*
            r10 = this;
            java.lang.String r11 = r11.getPayloadAsString()
            java.lang.String r0 = "On Message received "
            com.android.tools.r8.GeneratedOutlineSupport1.outline158(r0, r11)
            java.lang.String r0 = r10.getRequestedPresenceStateFromNotificationEvent(r11)
            com.dee.app.metrics.MetricsServiceV2 r1 = r10.metricsServiceV2
            java.lang.String r2 = "invalid_message_format"
            java.lang.String r3 = "notification_subscriber_message_received"
            com.amazon.alexa.presence.utils.MetricsUtil.recordZeroCount(r1, r2, r3)
            com.dee.app.metrics.MetricsServiceV2 r1 = r10.metricsServiceV2
            java.lang.String r4 = "notification_presence_disable_message"
            com.amazon.alexa.presence.utils.MetricsUtil.recordZeroCount(r1, r4, r3)
            com.dee.app.metrics.MetricsServiceV2 r1 = r10.metricsServiceV2
            java.lang.String r5 = "invalid_message"
            com.amazon.alexa.presence.utils.MetricsUtil.recordZeroCount(r1, r5, r3)
            com.dee.app.metrics.MetricsServiceV2 r1 = r10.metricsServiceV2
            java.lang.String r6 = "remove_presence_domain"
            java.lang.String r7 = "persistent_local_storage"
            com.amazon.alexa.presence.utils.MetricsUtil.recordZeroCount(r1, r6, r7)
            com.dee.app.metrics.MetricsServiceV2 r1 = r10.metricsServiceV2
            java.lang.String r6 = "stop_scanning_push_notification"
            java.lang.String r7 = "stop_scanning_workflow"
            com.amazon.alexa.presence.utils.MetricsUtil.recordZeroCount(r1, r6, r7)
            java.lang.String r1 = "::"
            java.lang.String[] r0 = r0.split(r1)
            int r1 = r0.length
            r6 = 2
            if (r1 == r6) goto L4d
            java.lang.String r11 = com.amazon.alexa.presence.eventbus.PushNotificationSubscriber.TAG
            java.lang.String r0 = "Incorrect event bus message format. No-op."
            android.util.Log.w(r11, r0)
            com.dee.app.metrics.MetricsServiceV2 r11 = r10.metricsServiceV2
            com.amazon.alexa.presence.utils.MetricsUtil.recordCount(r11, r2, r3)
            return
        L4d:
            r1 = 0
            r2 = r0[r1]
            r6 = 1
            r0 = r0[r6]
            r7 = -1
            int r8 = r0.hashCode()
            r9 = -1298848381(0xffffffffb2952583, float:-1.7362941E-8)
            if (r8 == r9) goto L6c
            r9 = 1671308008(0x639e22e8, float:5.8342016E21)
            if (r8 == r9) goto L63
            goto L76
        L63:
            java.lang.String r8 = "disable"
            boolean r0 = r0.equals(r8)
            if (r0 == 0) goto L76
            goto L77
        L6c:
            java.lang.String r1 = "enable"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L76
            r1 = r6
            goto L77
        L76:
            r1 = r7
        L77:
            if (r1 == 0) goto L9c
            if (r1 == r6) goto L86
            java.lang.String r0 = "No-op due to invalid action. Payload : "
            com.android.tools.r8.GeneratedOutlineSupport1.outline158(r0, r11)
            com.dee.app.metrics.MetricsServiceV2 r11 = r10.metricsServiceV2
            com.amazon.alexa.presence.utils.MetricsUtil.recordCount(r11, r5, r3)
            goto Laf
        L86:
            java.lang.String r11 = com.amazon.alexa.presence.eventbus.PushNotificationSubscriber.TAG
            java.lang.String r0 = "Start scan action event observed."
            android.util.Log.i(r11, r0)
            com.dee.app.metrics.MetricsServiceV2 r11 = r10.metricsServiceV2
            java.lang.String r0 = "notification_presence_enable_message"
            com.amazon.alexa.presence.utils.MetricsUtil.recordCount(r11, r0, r3)
            com.amazon.alexa.presence.PresenceController r11 = r10.presenceController
            android.content.Context r0 = r10.context
            r11.enablePresenceDomain(r0, r2)
            goto Laf
        L9c:
            java.lang.String r11 = com.amazon.alexa.presence.eventbus.PushNotificationSubscriber.TAG
            java.lang.String r0 = "Stop scan action event observed."
            android.util.Log.i(r11, r0)
            com.dee.app.metrics.MetricsServiceV2 r11 = r10.metricsServiceV2
            com.amazon.alexa.presence.utils.MetricsUtil.recordCount(r11, r4, r3)
            com.amazon.alexa.presence.PresenceController r11 = r10.presenceController
            android.content.Context r0 = r10.context
            r11.disablePresenceDomain(r0, r2)
        Laf:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.presence.eventbus.PushNotificationSubscriber.onMessageReceived(com.amazon.alexa.eventbus.api.Message):void");
    }

    @Override // com.amazon.alexa.eventbus.api.Subscriber
    public boolean supportsMessage(@NonNull Message message) {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Supports Message ");
        outline107.append(message.getPayloadAsString());
        outline107.toString();
        return this.eventMessageFilter.isMatch(message);
    }
}
