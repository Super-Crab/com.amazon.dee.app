package com.amazon.alexa.accessorykit.interprocess;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessory.internal.util.JsonObjectSerializable;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.google.common.base.Optional;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes6.dex */
public class InterprocessPrivacyModeReceiver extends BroadcastReceiver {
    @VisibleForTesting
    static final String EVENT_TYPE = "accessory::privacy_status";
    static final String INTENT_ACTION_PRIVACY_MODE = "com.amazon.alexa.accessorykit.action.privacymode.status";
    private static final String KEY_DEVICE_TYPE = "device_type";
    private static final String KEY_INITIAL = "initial";
    private static final String KEY_PRIVACY_STATUS = "privacy_status";
    static final String PRIVACY_STATUS_PAYLOAD_JSON_KEY = "privacyStatusPayloadJson";
    private static final String TAG = "InterprocessPrivacyModeReceiver:";
    private static final long goAsyncMillis = 2000;

    /* loaded from: classes6.dex */
    static class PrivacyStatusPayload implements JsonObjectSerializable {
        private final String deviceType;
        private final boolean initial;
        private final boolean privacyStatus;

        public PrivacyStatusPayload(boolean z, String str, boolean z2) {
            this.privacyStatus = z;
            this.deviceType = str;
            this.initial = z2;
        }

        public String getDeviceType() {
            return this.deviceType;
        }

        public boolean getInitial() {
            return this.initial;
        }

        public boolean getPrivacyStatus() {
            return this.privacyStatus;
        }

        @Override // com.amazon.alexa.accessory.internal.util.JsonObjectSerializable
        public JSONObject toJsonObject() throws JSONException {
            return new JSONObject().put("device_type", this.deviceType).put("privacy_status", this.privacyStatus).put("initial", this.initial);
        }

        public String toString() {
            return String.format(Locale.US, "privacyStatus: %b, initial: %b, deviceType: %s", Boolean.valueOf(this.privacyStatus), Boolean.valueOf(this.initial), this.deviceType);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$onReceive$0(BroadcastReceiver.PendingResult pendingResult) {
        try {
            try {
                Thread.sleep(2000L);
            } catch (InterruptedException unused) {
                Logger.e("%s Thread was interrupted before finishing goAsync", TAG);
            }
        } finally {
            pendingResult.finish();
        }
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String stringExtra;
        if (INTENT_ACTION_PRIVACY_MODE.equals(intent.getAction()) && (stringExtra = intent.getStringExtra(PRIVACY_STATUS_PAYLOAD_JSON_KEY)) != null) {
            Optional optional = ComponentRegistry.getInstance().get(EventBus.class);
            if (!optional.isPresent()) {
                Logger.e("%s EventBus is not available, ignoring received intent", TAG);
                return;
            }
            Message build = new Message.Builder().setEventType(EVENT_TYPE).setPayload(stringExtra).build();
            ((EventBus) optional.get()).publish(build);
            Logger.d("%s Message sent for privacy status. eventType is %s, payload is %s", TAG, build.getEventType(), build.getPayloadAsString());
            final BroadcastReceiver.PendingResult goAsync = goAsync();
            new Thread(new Runnable() { // from class: com.amazon.alexa.accessorykit.interprocess.-$$Lambda$InterprocessPrivacyModeReceiver$sbBE09dGyVtF2e3XdW7keXe-8Ek
                @Override // java.lang.Runnable
                public final void run() {
                    InterprocessPrivacyModeReceiver.lambda$onReceive$0(goAsync);
                }
            }).start();
        }
    }
}
