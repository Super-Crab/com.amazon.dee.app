package com.amazon.alexa.voiceui.cards;

import android.os.Bundle;
import com.amazon.alexa.api.UiEventName;
import com.amazon.alexa.voice.ui.provider.CardMode;
import com.amazon.alexa.voiceui.events.UiEventReporter;
import javax.inject.Inject;
import javax.inject.Singleton;
@Singleton
/* loaded from: classes11.dex */
class DriveModeCardCreationEventListener extends BaseCardCreationEventListener {
    private final UiEventReporter uiEventReporter;

    /* JADX INFO: Access modifiers changed from: package-private */
    @Inject
    public DriveModeCardCreationEventListener(UiEventReporter uiEventReporter) {
        super(uiEventReporter);
        this.uiEventReporter = uiEventReporter;
    }

    @Override // com.amazon.alexa.voiceui.cards.BaseCardCreationEventListener
    protected void reportEvent(UiEventName uiEventName, Bundle bundle) {
        bundle.putString("cardMode", CardMode.DRIVE_MODE.name());
        this.uiEventReporter.sendEvent(uiEventName, bundle);
    }
}
