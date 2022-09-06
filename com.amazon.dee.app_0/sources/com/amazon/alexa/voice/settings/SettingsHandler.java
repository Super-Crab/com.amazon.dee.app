package com.amazon.alexa.voice.settings;

import com.amazon.alexa.eventbus.api.MessageHandler;
import java.util.UUID;
/* loaded from: classes11.dex */
public interface SettingsHandler extends MessageHandler {
    UUID getSubscriptionId();

    boolean isEventTypeSupported(String str);

    void startSubscription(UUID uuid);

    void stopSubscription();
}
