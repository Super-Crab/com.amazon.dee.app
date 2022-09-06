package com.amazon.alexa.voiceui.cards;

import android.os.Bundle;
import android.os.SystemClock;
import com.amazon.alexa.api.AlexaMetadataBundleKey;
import com.amazon.alexa.api.UiEventName;
import com.amazon.alexa.voice.ui.cards.CardCreationEventListener;
import com.amazon.alexa.voiceui.events.UiEventReporter;
/* loaded from: classes11.dex */
abstract class BaseCardCreationEventListener implements CardCreationEventListener {
    private long cardCreationStart = -1;
    private long jsonParsingStart = -1;
    private final UiEventReporter uiEventReporter;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BaseCardCreationEventListener(UiEventReporter uiEventReporter) {
        this.uiEventReporter = uiEventReporter;
    }

    private void reportEvent(UiEventName uiEventName, String str) {
        Bundle bundle = new Bundle();
        bundle.putString(AlexaMetadataBundleKey.CARD_NAME.name(), str);
        reportEvent(uiEventName, bundle);
    }

    @Override // com.amazon.alexa.voice.ui.cards.CardCreationEventListener
    public void cardControllerCreationFinished(String str, boolean z) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        long j = this.cardCreationStart;
        long j2 = elapsedRealtime - j;
        if (j <= 0) {
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putLong(AlexaMetadataBundleKey.LATENCY_REALTIME_MS.name(), j2);
        bundle.putString(AlexaMetadataBundleKey.CARD_NAME.name(), str);
        bundle.putBoolean(AlexaMetadataBundleKey.SUCCESS.name(), z);
        reportEvent(UiEventName.CARD_CREATION_LATENCY, bundle);
    }

    @Override // com.amazon.alexa.voice.ui.cards.CardCreationEventListener
    public void cardControllerCreationStart() {
        this.cardCreationStart = SystemClock.elapsedRealtime();
    }

    @Override // com.amazon.alexa.voice.ui.cards.CardCreationEventListener
    public void errorCreatingCard(String str, Exception exc) {
        Bundle bundle = new Bundle();
        bundle.putString(AlexaMetadataBundleKey.CARD_NAME.name(), str);
        bundle.putString(AlexaMetadataBundleKey.MESSAGE.name(), exc.getMessage());
        reportEvent(UiEventName.CARD_CREATION_ERROR, bundle);
    }

    @Override // com.amazon.alexa.voice.ui.cards.CardCreationEventListener
    public void errorCreatingCardController(String str, Exception exc) {
        Bundle bundle = new Bundle();
        bundle.putString(AlexaMetadataBundleKey.CARD_NAME.name(), str);
        bundle.putString(AlexaMetadataBundleKey.MESSAGE.name(), exc.getMessage());
        reportEvent(UiEventName.CARD_CONTROLLER_CREATION_ERROR, bundle);
    }

    @Override // com.amazon.alexa.voice.ui.cards.CardCreationEventListener
    public void errorParsingJson(CardCreationEventListener.ErrorType errorType, String str) {
        Bundle bundle = new Bundle();
        bundle.putString(AlexaMetadataBundleKey.ERROR_TYPE.name(), errorType.name());
        bundle.putString(AlexaMetadataBundleKey.MESSAGE.name(), str);
        reportEvent(UiEventName.JSON_PARSING_ERROR, bundle);
    }

    @Override // com.amazon.alexa.voice.ui.cards.CardCreationEventListener
    public void foundCardFactory(String str) {
        reportEvent(UiEventName.FOUND_CARD_FACTORY, str);
    }

    @Override // com.amazon.alexa.voice.ui.cards.CardCreationEventListener
    public void missingCardFactory(String str) {
        reportEvent(UiEventName.MISSING_CARD_FACTORY, str);
    }

    @Override // com.amazon.alexa.voice.ui.cards.CardCreationEventListener
    public void parsingJsonFinished(boolean z) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        long j = this.jsonParsingStart;
        long j2 = elapsedRealtime - j;
        if (j <= 0) {
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putLong(AlexaMetadataBundleKey.LATENCY_REALTIME_MS.name(), j2);
        bundle.putBoolean(AlexaMetadataBundleKey.SUCCESS.name(), z);
        reportEvent(UiEventName.JSON_PARSING_LATENCY, bundle);
    }

    @Override // com.amazon.alexa.voice.ui.cards.CardCreationEventListener
    public void parsingJsonStart() {
        this.jsonParsingStart = SystemClock.elapsedRealtime();
    }

    protected abstract void reportEvent(UiEventName uiEventName, Bundle bundle);
}
