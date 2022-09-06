package com.amazon.alexa.voice.settings;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.voice.tta.TypeToAlexaFeatureEnabler;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes11.dex */
public class TtaAvailabilityHandler implements SettingsHandler {
    @VisibleForTesting
    static final String KEY_ENABLED = "enabled";
    private static final String TAG = "TtaAvailabilityHandler";
    @VisibleForTesting
    static final String TTA_REQUEST_EVENT = "voice::typeToAlexaAvailabilityRequest";
    @VisibleForTesting
    static final String TTA_RESPONSE_EVENT = "voice::typeToAlexaAvailabilityResponse";
    private final EventBus eventBus;
    private UUID subscriptionId;
    private final TypeToAlexaFeatureEnabler ttaFeatureEnabler;

    public TtaAvailabilityHandler(EventBus eventBus, TypeToAlexaFeatureEnabler typeToAlexaFeatureEnabler) {
        this.eventBus = eventBus;
        this.ttaFeatureEnabler = typeToAlexaFeatureEnabler;
    }

    private void publishTtaAvailabilityInfo() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("enabled", this.ttaFeatureEnabler.isTypeToAlexaEnabled());
            VoiceSettings.publishPayload(this.eventBus, TTA_RESPONSE_EVENT, jSONObject);
        } catch (JSONException e) {
            Log.e(TAG, "Error constructing tta availability info", e);
        }
    }

    @Override // com.amazon.alexa.voice.settings.SettingsHandler
    @Nullable
    public UUID getSubscriptionId() {
        return this.subscriptionId;
    }

    @Override // com.amazon.alexa.eventbus.api.MessageHandler
    public void handle(@NonNull Message message) {
        publishTtaAvailabilityInfo();
    }

    @Override // com.amazon.alexa.voice.settings.SettingsHandler
    public boolean isEventTypeSupported(String str) {
        return TTA_REQUEST_EVENT.equals(str);
    }

    @Override // com.amazon.alexa.voice.settings.SettingsHandler
    public void startSubscription(UUID uuid) {
        this.subscriptionId = uuid;
    }

    @Override // com.amazon.alexa.voice.settings.SettingsHandler
    public void stopSubscription() {
        this.subscriptionId = null;
    }
}
