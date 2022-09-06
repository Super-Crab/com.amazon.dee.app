package com.amazon.alexa.voiceui.cards;

import android.os.Bundle;
import com.amazon.alexa.api.AlexaMetadataBundleKey;
import com.amazon.alexa.api.UiEventName;
import com.amazon.alexa.voice.ui.metrics.CardMetricsInteractor;
import com.amazon.alexa.voice.ui.provider.CardMode;
import com.amazon.alexa.voiceui.events.UiEventReporter;
import javax.inject.Inject;
/* loaded from: classes11.dex */
class StandardCardMetricsInteractor implements CardMetricsInteractor {
    private final UiEventReporter uiEventReporter;

    /* JADX INFO: Access modifiers changed from: package-private */
    @Inject
    public StandardCardMetricsInteractor(UiEventReporter uiEventReporter) {
        this.uiEventReporter = uiEventReporter;
    }

    private void reportEvent(UiEventName uiEventName) {
        reportEvent(uiEventName, new Bundle());
    }

    @Override // com.amazon.alexa.voice.ui.metrics.CardMetricsInteractor
    public void reportCardInteracted(String str, boolean z) {
        Bundle bundle = new Bundle();
        bundle.putBoolean(AlexaMetadataBundleKey.CARD_WAS_INTERACTED_WITH.name(), z);
        bundle.putString(AlexaMetadataBundleKey.CARD_NAME.name(), str);
        reportEvent(UiEventName.CARD_INTERACTED, bundle);
    }

    @Override // com.amazon.alexa.voice.ui.metrics.CardMetricsInteractor
    public void reportCardShown(String str) {
        reportEvent(UiEventName.CARD_SHOWN, str);
    }

    @Override // com.amazon.alexa.voice.ui.metrics.CardMetricsInteractor
    public void reportNavigationToExternalLink(String str) {
        reportEvent(UiEventName.NAVIGATION_TO_EXTERNAL_LINK, str);
    }

    @Override // com.amazon.alexa.voice.ui.metrics.CardMetricsInteractor
    public void reportNavigationToInternalCard(String str) {
        reportEvent(UiEventName.NAVIGATION_TO_INTERNAL_CARD, str);
    }

    @Override // com.amazon.alexa.voice.ui.metrics.CardMetricsInteractor
    public void startTimerWhenTappingIngressButtonOnCard() {
        reportEvent(UiEventName.CARD_INGRESS_TAPPED);
    }

    private void reportEvent(UiEventName uiEventName, String str) {
        Bundle bundle = new Bundle();
        bundle.putString(AlexaMetadataBundleKey.CARD_NAME.name(), str);
        reportEvent(uiEventName, bundle);
    }

    private void reportEvent(UiEventName uiEventName, Bundle bundle) {
        bundle.putString("cardMode", CardMode.STANDARD.name());
        this.uiEventReporter.sendEvent(uiEventName, bundle);
    }
}
