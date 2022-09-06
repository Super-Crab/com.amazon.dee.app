package com.amazon.alexa.voice.settings;

import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageFilter;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import org.json.JSONObject;
/* loaded from: classes11.dex */
public class VoiceSettings {
    private final EventBus eventBus;
    private final List<SettingsHandler> handlers;

    public VoiceSettings(EventBus eventBus, SettingsHandler... settingsHandlerArr) {
        this.eventBus = eventBus;
        this.handlers = Arrays.asList(settingsHandlerArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void publishPayload(EventBus eventBus, String str, JSONObject jSONObject) {
        eventBus.publish(new Message.Builder().setEventType(str).setPayload(jSONObject.toString()).setSource(Message.Source.Local).build());
    }

    @VisibleForTesting
    List<SettingsHandler> getHandlers() {
        return this.handlers;
    }

    public void onMainActivityStarted() {
        for (final SettingsHandler settingsHandler : this.handlers) {
            settingsHandler.startSubscription(this.eventBus.getSubscriber().subscribe(new MessageFilter() { // from class: com.amazon.alexa.voice.settings.-$$Lambda$VoiceSettings$GbxRcpHi9Dfarss_AzwIvo0GU04
                @Override // com.amazon.alexa.eventbus.api.MessageFilter
                public final boolean isMatch(Message message) {
                    return SettingsHandler.this.isEventTypeSupported(message.getEventType());
                }
            }, settingsHandler));
        }
    }

    public void onMainActivityStopped() {
        for (SettingsHandler settingsHandler : this.handlers) {
            UUID subscriptionId = settingsHandler.getSubscriptionId();
            if (subscriptionId != null) {
                this.eventBus.unsubscribe(subscriptionId);
                settingsHandler.stopSubscription();
            }
        }
    }
}
