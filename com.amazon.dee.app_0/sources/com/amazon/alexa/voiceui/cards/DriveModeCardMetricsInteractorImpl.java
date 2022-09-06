package com.amazon.alexa.voiceui.cards;

import android.os.Bundle;
import com.amazon.alexa.api.AlexaMetadataBundleKey;
import com.amazon.alexa.api.UiEventName;
import com.amazon.alexa.voice.ui.driveMode.metrics.DriveModeCardMetricsInteractor;
import com.amazon.alexa.voice.ui.provider.CardMode;
import com.amazon.alexa.voiceui.events.UiEventReporter;
import javax.inject.Inject;
/* loaded from: classes11.dex */
class DriveModeCardMetricsInteractorImpl implements DriveModeCardMetricsInteractor {
    private final UiEventReporter uiEventReporter;

    /* JADX INFO: Access modifiers changed from: package-private */
    @Inject
    public DriveModeCardMetricsInteractorImpl(UiEventReporter uiEventReporter) {
        this.uiEventReporter = uiEventReporter;
    }

    private void reportEvent(UiEventName uiEventName, String str) {
        Bundle bundle = new Bundle();
        bundle.putString(AlexaMetadataBundleKey.CARD_NAME.name(), str);
        reportEvent(uiEventName, bundle);
    }

    @Override // com.amazon.alexa.voice.ui.driveMode.metrics.DriveModeCardMetricsInteractor
    public void reportCardInteracted(String str, boolean z) {
        Bundle bundle = new Bundle();
        bundle.putBoolean(AlexaMetadataBundleKey.CARD_WAS_INTERACTED_WITH.name(), z);
        bundle.putString(AlexaMetadataBundleKey.CARD_NAME.name(), str);
        reportEvent(UiEventName.CARD_INTERACTED, bundle);
    }

    @Override // com.amazon.alexa.voice.ui.driveMode.metrics.DriveModeCardMetricsInteractor
    public void reportCardShown(String str) {
        reportEvent(UiEventName.CARD_SHOWN, str);
    }

    @Override // com.amazon.alexa.voice.ui.driveMode.metrics.DriveModeCardMetricsInteractor
    public void reportNavigationToExternalLink(String str) {
        reportEvent(UiEventName.NAVIGATION_TO_EXTERNAL_LINK, str);
    }

    @Override // com.amazon.alexa.voice.ui.driveMode.metrics.DriveModeCardMetricsInteractor
    public void reportNavigationToInternalCard(String str) {
        reportEvent(UiEventName.NAVIGATION_TO_INTERNAL_CARD, str);
    }

    private void reportEvent(UiEventName uiEventName, Bundle bundle) {
        bundle.putString("cardMode", CardMode.DRIVE_MODE.name());
        this.uiEventReporter.sendEvent(uiEventName, bundle);
    }
}
