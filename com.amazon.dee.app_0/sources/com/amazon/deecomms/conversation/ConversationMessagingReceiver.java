package com.amazon.deecomms.conversation;

import android.os.Bundle;
import androidx.annotation.NonNull;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.protocols.messaging.Message;
import com.amazon.alexa.protocols.messaging.MessagingReceiver;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.api.CommsManager;
import com.amazon.deecomms.api.metrics.CounterMetric;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.EventBusEventType;
import com.amazon.deecomms.common.metrics.MetricsHelper;
/* loaded from: classes12.dex */
public class ConversationMessagingReceiver implements MessagingReceiver {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, ConversationMessagingReceiver.class);
    private final CommsManager commsManager;
    private final EventBus eventBus;

    public ConversationMessagingReceiver(@NonNull CommsManager commsManager, @NonNull EventBus eventBus) {
        this.commsManager = commsManager;
        this.eventBus = eventBus;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(8:4|(2:5|6)|(4:16|17|18|12)|8|9|11|12|2) */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x006a, code lost:
        r3 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x006b, code lost:
        r4 = com.amazon.deecomms.conversation.ConversationMessagingReceiver.LOG;
        r4.e("Couldn't add bundle key: " + r2, r3);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.lang.String bundleToJson(android.os.Bundle r8) {
        /*
            r7 = this;
            org.json.JSONObject r0 = new org.json.JSONObject
            r0.<init>()
            java.util.Set r1 = r8.keySet()
            java.util.Iterator r1 = r1.iterator()
        Ld:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L82
            java.lang.Object r2 = r1.next()
            java.lang.String r2 = (java.lang.String) r2
            java.lang.Object r3 = r8.get(r2)
            boolean r4 = r3 instanceof java.lang.String     // Catch: org.json.JSONException -> L2d
            if (r4 == 0) goto L66
            org.json.JSONObject r4 = new org.json.JSONObject     // Catch: org.json.JSONException -> L2d
            r5 = r3
            java.lang.String r5 = (java.lang.String) r5     // Catch: org.json.JSONException -> L2d
            r4.<init>(r5)     // Catch: org.json.JSONException -> L2d
            r0.put(r2, r4)     // Catch: org.json.JSONException -> L2d
            goto Ld
        L2d:
            com.amazon.comms.log.CommsLogger r4 = com.amazon.deecomms.conversation.ConversationMessagingReceiver.LOG
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "Couldn't convert the value to JSONObject, key = "
            r5.append(r6)
            r5.append(r2)
            java.lang.String r6 = " , adding value as String..."
            r5.append(r6)
            java.lang.String r5 = r5.toString()
            r4.i(r5)
            com.amazon.comms.log.CommsLogger r4 = com.amazon.deecomms.conversation.ConversationMessagingReceiver.LOG
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "Unconverted key = "
            r5.append(r6)
            r5.append(r2)
            java.lang.String r6 = ", value = "
            r5.append(r6)
            r5.append(r3)
            java.lang.String r5 = r5.toString()
            r4.d(r5)
        L66:
            r0.put(r2, r3)     // Catch: org.json.JSONException -> L6a
            goto Ld
        L6a:
            r3 = move-exception
            com.amazon.comms.log.CommsLogger r4 = com.amazon.deecomms.conversation.ConversationMessagingReceiver.LOG
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "Couldn't add bundle key: "
            r5.append(r6)
            r5.append(r2)
            java.lang.String r2 = r5.toString()
            r4.e(r2, r3)
            goto Ld
        L82:
            java.lang.String r8 = r0.toString()
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.deecomms.conversation.ConversationMessagingReceiver.bundleToJson(android.os.Bundle):java.lang.String");
    }

    @Override // com.amazon.alexa.protocols.messaging.MessagingReceiver
    public void onReceive(@NonNull Message message) {
        Bundle bundle = message.get();
        CommsLogger commsLogger = LOG;
        commsLogger.d("Received message: " + bundle);
        try {
            this.eventBus.publish(new Message.Builder().setEventType(EventBusEventType.NOTIFICATION_RECEIVED.toString()).setPayload(bundleToJson(bundle)).build());
            StringBuilder sb = new StringBuilder();
            sb.append("comms.eventbus.event.published.");
            sb.append(EventBusEventType.NOTIFICATION_RECEIVED.toString());
            MetricsHelper.recordCounterMetric(CounterMetric.generateOperational(sb.toString()), Double.valueOf(1.0d));
        } catch (RuntimeException unused) {
            LOG.e("RuntimeException encountered during parsing - Check your payload!", bundle);
        }
        if (this.commsManager.onPush(bundle)) {
            message.cancel();
        }
    }
}
