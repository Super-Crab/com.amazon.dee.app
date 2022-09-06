package com.amazon.alexa.voiceui.cards;

import com.amazon.alexa.voice.ui.cards.CardCreationEventListener;
import com.amazon.alexa.voice.ui.provider.CardCreationEventListenerProvider;
import com.amazon.alexa.voice.ui.provider.CardMode;
import javax.inject.Singleton;
@Singleton
/* loaded from: classes11.dex */
public class DefaultCardCreationEventListenerProvider implements CardCreationEventListenerProvider {
    private DriveModeCardCreationEventListener driveModeCardCreationEventListener;
    private StandardCardCreationEventListener standardCardCreationEventListener;

    public DefaultCardCreationEventListenerProvider(StandardCardCreationEventListener standardCardCreationEventListener, DriveModeCardCreationEventListener driveModeCardCreationEventListener) {
        this.standardCardCreationEventListener = standardCardCreationEventListener;
        this.driveModeCardCreationEventListener = driveModeCardCreationEventListener;
    }

    @Override // com.amazon.alexa.voice.ui.provider.CardCreationEventListenerProvider
    public CardCreationEventListener getCreationEventListener(CardMode cardMode) {
        if (cardMode.equals(CardMode.DRIVE_MODE)) {
            return this.driveModeCardCreationEventListener;
        }
        return this.standardCardCreationEventListener;
    }
}
