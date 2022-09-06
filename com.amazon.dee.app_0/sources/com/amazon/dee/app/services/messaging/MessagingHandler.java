package com.amazon.dee.app.services.messaging;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.identity.api.UserIdentity;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.mobilytics.event.MobilyticsEvent;
import com.amazon.alexa.mobilytics.event.userinteraction.MobilyticsUserInteractionEvent;
import com.amazon.alexa.protocols.messaging.Message;
import com.amazon.alexa.protocols.messaging.MessagingReceiver;
import com.amazon.dee.app.services.metrics.AlexaMetricsConstants;
import com.amazon.dee.app.util.Utils;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes12.dex */
public class MessagingHandler {
    private static final String ADM_PAYLOAD_KEY = "ADMPayload";
    private static final String ALEXA_ID_KEY = "alexaId";
    private static final String CID_HASH_KEY = "cidHash";
    private static final String CRYPTO_KEY = "crypto";
    private static final String DICT_KEY = "dict";
    private static final String EXPERIMENT_ID_KEY = "experimentId";
    private static final String MESSAGE_KEY = "message";
    private static final String PAYLOAD_KEY = "payload";
    private static final String SCHEMA_KEY = "key";
    private static final String SCHEMA_TYPE_KEY = "schemaType";
    private static final String SENDER_KEY = "sender";
    private static final String TAG = "com.amazon.dee.app.services.messaging.MessagingHandler";
    private static final String TYPE_KEY = "type";
    private static final String UUID_KEY = "uuid";
    private Lazy<FeatureServiceV2> featureServiceV2Lazy;
    private final IdentityService identityService;
    private final MessageCrypto messageCrypto;
    private final Lazy<Mobilytics> mobilytics;
    private final List<MessagingReceiver> receivers;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public static class BundleMessage implements Message {
        volatile boolean canceled;
        Bundle message;

        BundleMessage(Bundle bundle) {
            this.message = bundle;
        }

        @Override // com.amazon.alexa.protocols.messaging.Message
        public void cancel() {
            this.canceled = true;
        }

        @Override // com.amazon.alexa.protocols.messaging.Message
        @NonNull
        public Bundle get() {
            return this.message;
        }

        @Override // com.amazon.alexa.protocols.messaging.Message
        public boolean isCanceled() {
            return this.canceled;
        }
    }

    /* loaded from: classes12.dex */
    public @interface SenderAPI {
        public static final int SERVICE_PUSH_MESSAGE = 2;
        public static final int SERVICE_SEND_TO_PHONE = 1;
    }

    public MessagingHandler(IdentityService identityService, MessageCrypto messageCrypto, Set<MessagingReceiver> set, Lazy<Mobilytics> lazy, Lazy<FeatureServiceV2> lazy2) {
        this(identityService, messageCrypto, new LinkedList(), lazy, lazy2);
        this.receivers.addAll(set);
    }

    private Bundle decryptAndFlattenBundle(@NonNull Bundle bundle) throws JSONException {
        String str;
        String payLoadKey = getPayLoadKey(bundle);
        String string = bundle.getString(payLoadKey);
        if (!TextUtils.isEmpty(string)) {
            JSONObject jSONObject = new JSONObject(string);
            if (jSONObject.has(CRYPTO_KEY)) {
                str = this.messageCrypto.decrypt(jSONObject.getString("message"), jSONObject.getString(CRYPTO_KEY).split(":", 2)[1]);
            } else {
                str = new String(Base64.decode(jSONObject.getString("message"), 0));
            }
            if (!TextUtils.isEmpty(str)) {
                return transformMessage(bundle, str, payLoadKey);
            }
            throw new JSONException("Message cannot be empty");
        }
        throw new JSONException("Payload cannot be empty");
    }

    public static void extractAndSetMetricsPayload(MobilyticsEvent mobilyticsEvent, Bundle bundle) {
        String string;
        if (bundle == null || bundle.isEmpty()) {
            return;
        }
        if (bundle.containsKey(EXPERIMENT_ID_KEY)) {
            mobilyticsEvent.setContentVersion(bundle.getString(EXPERIMENT_ID_KEY));
        }
        if (bundle.containsKey("sender")) {
            string = bundle.getString("sender");
        } else {
            string = bundle.containsKey("type") ? bundle.getString("type") : "Default";
        }
        if (string != null) {
            mobilyticsEvent.setContentProvider(string);
        }
        if (bundle.containsKey("uuid")) {
            mobilyticsEvent.setContentId(bundle.getString("uuid"));
        }
        String str = null;
        if (bundle.containsKey("key")) {
            str = bundle.getString("key");
        } else if (bundle.containsKey(SCHEMA_TYPE_KEY)) {
            str = bundle.getString(SCHEMA_TYPE_KEY);
        }
        if (str == null) {
            return;
        }
        mobilyticsEvent.setContentType(str);
    }

    private String getPayLoadKey(Bundle bundle) {
        return bundle.containsKey("payload") ? "payload" : bundle.containsKey(ADM_PAYLOAD_KEY) ? ADM_PAYLOAD_KEY : "";
    }

    @SenderAPI
    private int getSenderAPI(Bundle bundle) {
        return !TextUtils.isEmpty(getPayLoadKey(bundle)) ? 2 : 1;
    }

    private boolean isIntendedForCurrentUserId(String str) {
        String id;
        UserIdentity user = this.identityService.getUser(TAG);
        if (user == null || (id = user.getId()) == null) {
            return false;
        }
        return Utils.sha256Hex(id).equals(str);
    }

    private void notifyReceivers(@NonNull Bundle bundle) {
        ArrayList arrayList;
        synchronized (this.receivers) {
            String str = "receivers = " + this.receivers + ", of size = " + this.receivers.size();
            arrayList = new ArrayList(this.receivers);
        }
        BundleMessage bundleMessage = new BundleMessage(bundle);
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            MessagingReceiver messagingReceiver = (MessagingReceiver) it2.next();
            String str2 = "Calling a onReceive() on a receiver: " + messagingReceiver;
            messagingReceiver.onReceive(bundleMessage);
            if (bundleMessage.isCanceled()) {
                return;
            }
        }
    }

    private void recordEvent(@NonNull String str, Bundle bundle) {
        MobilyticsUserInteractionEvent createUserInteractionEvent = this.mobilytics.mo358get().createUserInteractionEvent(str, "view", "PushNotifications", AlexaMetricsConstants.MetricsComponents.NOTIFICATIONS_RECEIVER_SUBCOMPONENT, "1235005e-4e8f-4ef2-82bc-34157415015b");
        createUserInteractionEvent.setChannelName("coreapp");
        extractAndSetMetricsPayload(createUserInteractionEvent, bundle);
        this.mobilytics.mo358get().recordUserInteractionEvent(createUserInteractionEvent);
    }

    private void recordPercentOccurrence(@NonNull String str, boolean z) {
        this.mobilytics.mo358get().recordPercentOccurrence(str, z, "PushNotifications", AlexaMetricsConstants.MetricsComponents.NOTIFICATIONS_RECEIVER_SUBCOMPONENT, "1235005e-4e8f-4ef2-82bc-34157415015b");
    }

    private void recordPushMessageReception(@NonNull Bundle bundle) {
        if (this.featureServiceV2Lazy.mo358get() == null || !this.featureServiceV2Lazy.mo358get().hasAccess("DMPS_RELIABILITY_METRICS", false)) {
            return;
        }
        recordEvent(AlexaMetricsConstants.MetricEvents.NOTIFICATION_RECEIVED_PUSH_MESSAGE_API, bundle);
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0078, code lost:
        if (isIntendedForCurrentUserId(r8.getString(com.amazon.dee.app.services.messaging.MessagingHandler.CID_HASH_KEY)) == false) goto L12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x008a, code lost:
        if (isIntendedForCurrentUserId(r8.getString(com.amazon.dee.app.services.messaging.MessagingHandler.ALEXA_ID_KEY)) == false) goto L12;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void handleMessage(@androidx.annotation.NonNull android.os.Bundle r8) {
        /*
            r7 = this;
            java.lang.String r0 = "MESSAGE_ERROR_PERCENT"
            java.lang.String r1 = "uuid"
            boolean r2 = r8.containsKey(r1)
            if (r2 == 0) goto L1c
            java.lang.String r2 = "Received Message with UUID: "
            java.lang.StringBuilder r2 = com.android.tools.r8.GeneratedOutlineSupport1.outline107(r2)
            java.lang.String r1 = r8.getString(r1)
            r2.append(r1)
            r2.toString()
            goto L43
        L1c:
            java.lang.String r1 = "sender"
            boolean r2 = r8.containsKey(r1)
            if (r2 == 0) goto L3c
            java.lang.String r2 = com.amazon.dee.app.services.messaging.MessagingHandler.TAG
            java.lang.String r3 = "Incoming message missing UUID from "
            java.lang.StringBuilder r3 = com.android.tools.r8.GeneratedOutlineSupport1.outline107(r3)
            java.lang.String r1 = r8.getString(r1)
            r3.append(r1)
            java.lang.String r1 = r3.toString()
            com.amazon.dee.app.services.logging.Log.w(r2, r1)
            goto L43
        L3c:
            java.lang.String r1 = com.amazon.dee.app.services.messaging.MessagingHandler.TAG
            java.lang.String r2 = "Incoming message missing both Sender and UUID"
            com.amazon.dee.app.services.logging.Log.e(r1, r2)
        L43:
            int r1 = r7.getSenderAPI(r8)
            java.lang.String r2 = "NOTIFICATION_RECEIVED"
            r3 = 2
            r4 = 0
            r5 = 1
            if (r1 != r3) goto L5e
            android.os.Bundle r6 = r7.decryptAndFlattenBundle(r8)     // Catch: org.json.JSONException -> L57
            r7.recordPercentOccurrence(r0, r4)     // Catch: org.json.JSONException -> L57
            r8 = r6
            goto L5e
        L57:
            r7.recordEvent(r2, r8)
            r7.recordPercentOccurrence(r0, r5)
            return
        L5e:
            r7.recordEvent(r2, r8)
            if (r1 != r5) goto L7b
            java.lang.String r0 = "NOTIFICATION_RECEIVED_SEND_TO_PHONE_API"
            r7.recordEvent(r0, r8)
            java.lang.String r0 = "cidHash"
            boolean r1 = r8.containsKey(r0)
            if (r1 == 0) goto L8d
            java.lang.String r0 = r8.getString(r0)
            boolean r0 = r7.isIntendedForCurrentUserId(r0)
            if (r0 != 0) goto L8d
            goto L8e
        L7b:
            if (r1 != r3) goto L8d
            r7.recordPushMessageReception(r8)
            java.lang.String r0 = "alexaId"
            java.lang.String r0 = r8.getString(r0)
            boolean r0 = r7.isIntendedForCurrentUserId(r0)
            if (r0 != 0) goto L8d
            goto L8e
        L8d:
            r4 = r5
        L8e:
            if (r4 == 0) goto L94
            r7.notifyReceivers(r8)
            goto La0
        L94:
            java.lang.String r0 = com.amazon.dee.app.services.messaging.MessagingHandler.TAG
            java.lang.String r1 = "Received notification failed signature verification."
            com.amazon.dee.app.services.logging.Log.w(r0, r1)
            java.lang.String r0 = "NOTIFICATION_RECEIVED_USER_MISSMATCH_ERROR"
            r7.recordEvent(r0, r8)
        La0:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.dee.app.services.messaging.MessagingHandler.handleMessage(android.os.Bundle):void");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void registerReceiver(@NonNull MessagingReceiver messagingReceiver) {
        synchronized (this.receivers) {
            this.receivers.add(messagingReceiver);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void registerReceiverWithHigherPriority(@NonNull MessagingReceiver messagingReceiver) {
        synchronized (this.receivers) {
            this.receivers.add(0, messagingReceiver);
        }
    }

    @VisibleForTesting
    Bundle transformMessage(Bundle bundle, String str, String str2) throws JSONException {
        JSONObject jSONObject = new JSONObject(str);
        Bundle bundle2 = new Bundle();
        if (jSONObject.has("key")) {
            bundle2.putString("key", jSONObject.getString("key"));
        }
        if (jSONObject.has(SCHEMA_TYPE_KEY)) {
            bundle2.putString(SCHEMA_TYPE_KEY, jSONObject.getString(SCHEMA_TYPE_KEY));
        }
        if (jSONObject.has(ALEXA_ID_KEY)) {
            bundle2.putString(ALEXA_ID_KEY, jSONObject.getString(ALEXA_ID_KEY));
        }
        if (jSONObject.has("sender")) {
            bundle2.putString("sender", jSONObject.getString("sender"));
        }
        if (jSONObject.has("type")) {
            bundle2.putString("type", jSONObject.getString("type"));
        }
        if (jSONObject.has(EXPERIMENT_ID_KEY)) {
            bundle2.putString(EXPERIMENT_ID_KEY, jSONObject.getString(EXPERIMENT_ID_KEY));
        }
        if (bundle != null && bundle.containsKey("uuid")) {
            bundle2.putString("uuid", bundle.getString("uuid"));
        }
        JSONObject jSONObject2 = new JSONObject(new String(Base64.decode(jSONObject.getString(DICT_KEY), 0)));
        Iterator keys = jSONObject2.keys();
        while (keys.hasNext()) {
            String str3 = (String) keys.next();
            bundle2.putString(str3, jSONObject2.getString(str3));
        }
        String str4 = "Processed a PushMessage payload with message = " + bundle2;
        return bundle2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void unregisterReceiver(@NonNull MessagingReceiver messagingReceiver) {
        synchronized (this.receivers) {
            this.receivers.remove(messagingReceiver);
        }
    }

    @VisibleForTesting
    MessagingHandler(IdentityService identityService, MessageCrypto messageCrypto, List<MessagingReceiver> list, Lazy<Mobilytics> lazy, Lazy<FeatureServiceV2> lazy2) {
        this.mobilytics = lazy;
        this.identityService = identityService;
        this.messageCrypto = messageCrypto;
        this.receivers = list;
        this.featureServiceV2Lazy = lazy2;
    }
}
