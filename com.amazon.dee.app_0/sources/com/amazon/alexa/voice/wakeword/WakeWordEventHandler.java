package com.amazon.alexa.voice.wakeword;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageHandler;
import com.amazon.alexa.protocols.lifecycle.ApplicationLifecycleObserver;
import com.amazon.alexa.protocols.lifecycle.ApplicationLifecycleService;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.util.UUID;
/* loaded from: classes11.dex */
public class WakeWordEventHandler implements ApplicationLifecycleObserver {
    @VisibleForTesting
    static final String COMMS_AUDIO_RECORDING_PREFIX = "audio-recording::";
    private static final String TAG = "WakeWordEventHandler";
    @VisibleForTesting
    static final String VOICE_WAKEWORD_SUPPRESSION_EVENT_TYPE = "voice::wakewordSuppression";
    @VisibleForTesting
    static final long WAKEWORD_DELAY_COMMS_INTERACTION_DURATION = 42000;
    @VisibleForTesting
    static final long WAKEWORD_DELAY_DURATION = 3000;
    private EventBus eventBus;
    private Handler handler = new Handler(Looper.getMainLooper());
    private UUID subscription;
    @Nullable
    private WakeWordEventObserver wakeWordEventObserver;

    public WakeWordEventHandler(EventBus eventBus, ApplicationLifecycleService applicationLifecycleService) {
        this.eventBus = eventBus;
        applicationLifecycleService.addObserver(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void suppressWakeword(Message message) {
        if (extractStringFromMessagePayload(message, "featureID", "other").equals("comms")) {
            handleCommsInteraction(message);
        } else {
            disableForFixedDuration(3000L);
        }
    }

    @VisibleForTesting
    void disableForFixedDuration(long j) {
        GeneratedOutlineSupport1.outline153("wakeword disabled for ", j);
        this.handler.removeCallbacksAndMessages(null);
        this.handler.post(new Runnable() { // from class: com.amazon.alexa.voice.wakeword.-$$Lambda$WakeWordEventHandler$_ys7OUtWwkwtfl7GozX5VeBuzYo
            @Override // java.lang.Runnable
            public final void run() {
                WakeWordEventHandler.this.lambda$disableForFixedDuration$1$WakeWordEventHandler();
            }
        });
        this.handler.postDelayed(new Runnable() { // from class: com.amazon.alexa.voice.wakeword.-$$Lambda$WakeWordEventHandler$fE4x7aN8iJSJZzOh7jqlj0Y0PTk
            @Override // java.lang.Runnable
            public final void run() {
                WakeWordEventHandler.this.lambda$disableForFixedDuration$2$WakeWordEventHandler();
            }
        }, j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    /* renamed from: disableWakeWord */
    public void lambda$disableForFixedDuration$1$WakeWordEventHandler() {
        WakeWordEventObserver wakeWordEventObserver = this.wakeWordEventObserver;
        if (wakeWordEventObserver != null) {
            wakeWordEventObserver.onWakeWordDisableEvent();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    /* renamed from: enableWakeWord */
    public void lambda$disableForFixedDuration$2$WakeWordEventHandler() {
        WakeWordEventObserver wakeWordEventObserver = this.wakeWordEventObserver;
        if (wakeWordEventObserver != null) {
            wakeWordEventObserver.onWakeWordEnableEvent();
        }
    }

    @VisibleForTesting
    String extractStringFromMessagePayload(Message message, String str, String str2) {
        String payloadAsString = message.getPayloadAsString();
        if (payloadAsString.isEmpty()) {
            String str3 = TAG;
            Log.i(str3, "Cannot extract " + str + " from message, payload is empty");
            return str2;
        }
        try {
            JsonObject jsonObject = (JsonObject) new Gson().fromJson(payloadAsString, (Class<Object>) JsonObject.class);
            return (jsonObject == null || !jsonObject.has(str)) ? str2 : jsonObject.get(str).getAsString();
        } catch (Exception unused) {
            String str4 = TAG;
            Log.w(str4, "Failed to parse " + str + " from message. Return default.");
            return str2;
        }
    }

    @VisibleForTesting
    void handleCommsInteraction(Message message) {
        String extractStringFromMessagePayload = extractStringFromMessagePayload(message, "featureType", null);
        if (extractStringFromMessagePayload != null && extractStringFromMessagePayload.startsWith(COMMS_AUDIO_RECORDING_PREFIX)) {
            String replace = extractStringFromMessagePayload.replace(COMMS_AUDIO_RECORDING_PREFIX, "");
            if ("start".equals(replace)) {
                disableForFixedDuration(WAKEWORD_DELAY_COMMS_INTERACTION_DURATION);
                return;
            } else if (!"stop".equals(replace)) {
                return;
            } else {
                this.handler.removeCallbacksAndMessages(null);
                lambda$disableForFixedDuration$2$WakeWordEventHandler();
                return;
            }
        }
        disableForFixedDuration(3000L);
    }

    @Override // com.amazon.alexa.protocols.lifecycle.ApplicationLifecycleObserver
    public void onStart() {
        this.subscription = this.eventBus.getSubscriber().subscribe($$Lambda$WakeWordEventHandler$jVD3krKsm2pmklVzvTVj57pc7nA.INSTANCE, new MessageHandler() { // from class: com.amazon.alexa.voice.wakeword.-$$Lambda$WakeWordEventHandler$4cHK9zww_zmfcyz7Fsf14WTQ28k
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                WakeWordEventHandler.this.suppressWakeword(message);
            }
        });
    }

    @Override // com.amazon.alexa.protocols.lifecycle.ApplicationLifecycleObserver
    public void onStop() {
        UUID uuid = this.subscription;
        if (uuid != null) {
            this.eventBus.unsubscribe(uuid);
        }
    }

    public void setWakeWordEventObserver(WakeWordEventObserver wakeWordEventObserver) {
        this.wakeWordEventObserver = wakeWordEventObserver;
    }
}
