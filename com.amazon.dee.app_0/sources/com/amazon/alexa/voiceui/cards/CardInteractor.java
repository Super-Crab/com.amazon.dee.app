package com.amazon.alexa.voiceui.cards;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.api.AlexaMetadataBundleKey;
import com.amazon.alexa.api.UiEventName;
import com.amazon.alexa.voice.ui.provider.CardMode;
import com.amazon.alexa.voiceui.BackButtonPressHandler;
import com.amazon.alexa.voiceui.SaveInstanceStateHandler;
import com.amazon.alexa.voiceui.events.UiEventReporter;
import com.amazon.alexa.voiceui.externalCards.ExternalCardBroadcastReceiver;
import com.amazon.alexa.voiceui.externalCards.ExternalCardListener;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
@Singleton
/* loaded from: classes11.dex */
public class CardInteractor {
    private final BackButtonPressHandler backButtonPressHandler;
    private final CardModel cardModel;
    private ExternalCardBroadcastReceiver externalCardBroadcastReceiver;
    private final SaveInstanceStateHandler saveInstanceStateHandler;
    private final UiEventReporter uiEventReporter;
    private final BehaviorSubject<String> cardObservable = BehaviorSubject.create();
    private final List<CardDismissedListener> cardDismissedListeners = new ArrayList();
    @VisibleForTesting
    final CardListener cardListener = new CardListener();
    private final BehaviorSubject<Boolean> driveModeEnabledObservable = BehaviorSubject.create();
    @VisibleForTesting
    final BehaviorSubject<String> displayingExternalCardObservable = BehaviorSubject.create();

    @VisibleForTesting
    /* loaded from: classes11.dex */
    class CardListener implements com.amazon.alexa.voiceui.cards.CardListener {
        CardListener() {
        }

        @Override // com.amazon.alexa.voiceui.cards.CardListener
        public void onAlexaCard(String str) {
            CardInteractor.this.cardObservable.onNext(str);
        }
    }

    @Inject
    public CardInteractor(CardModel cardModel, UiEventReporter uiEventReporter, @Named("cardsBackPress") BackButtonPressHandler backButtonPressHandler, @Named("cardsSaveHandler") SaveInstanceStateHandler saveInstanceStateHandler) {
        this.cardModel = cardModel;
        this.uiEventReporter = uiEventReporter;
        this.backButtonPressHandler = backButtonPressHandler;
        this.saveInstanceStateHandler = saveInstanceStateHandler;
    }

    public boolean backButtonPressed() {
        return this.backButtonPressHandler.onBackButtonPressed();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void cardDismissed() {
        synchronized (this.cardDismissedListeners) {
            for (CardDismissedListener cardDismissedListener : this.cardDismissedListeners) {
                cardDismissedListener.onCardDismissed();
            }
        }
    }

    public void deregisterForCardUpdates() {
        this.cardModel.removeCardListener(this.cardListener);
        this.cardModel.release();
    }

    public void deregisterForExternalCardUpdates() {
        this.externalCardBroadcastReceiver.unregister();
    }

    public Observable<String> getDisplayingExternalCard() {
        return this.displayingExternalCardObservable;
    }

    public Observable<Boolean> getDriveModeEnabledObservable() {
        return this.driveModeEnabledObservable;
    }

    public void hideCard() {
        this.cardObservable.onNext("");
    }

    public boolean isCardVisible() {
        return this.cardObservable.hasValue();
    }

    public /* synthetic */ void lambda$registerForExternalCardUpdates$0$CardInteractor() {
        this.displayingExternalCardObservable.onNext("");
    }

    public Observable<String> onAlexaCard() {
        return this.cardObservable;
    }

    public void recordCardCreationFailure(String str, CardMode cardMode) {
        Bundle bundle = new Bundle();
        bundle.putString(AlexaMetadataBundleKey.MESSAGE.name(), str);
        bundle.putString("cardMode", cardMode.name());
        this.uiEventReporter.sendEvent(UiEventName.CARD_CREATION_ERROR, bundle);
    }

    public void recordCardCreationLatency(long j, boolean z, CardMode cardMode) {
        Bundle bundle = new Bundle();
        bundle.putLong(AlexaMetadataBundleKey.LATENCY_REALTIME_MS.name(), j);
        bundle.putBoolean(AlexaMetadataBundleKey.SUCCESS.name(), z);
        bundle.putString("cardMode", cardMode.name());
        this.uiEventReporter.sendEvent(UiEventName.CARD_RENDER_LATENCY, bundle);
    }

    public void recordCardViewInflationLatency(long j, boolean z, CardMode cardMode) {
        Bundle bundle = new Bundle();
        bundle.putLong(AlexaMetadataBundleKey.LATENCY_REALTIME_MS.name(), j);
        bundle.putBoolean(AlexaMetadataBundleKey.SUCCESS.name(), z);
        bundle.putString("cardMode", cardMode.name());
        this.uiEventReporter.sendEvent(UiEventName.CARD_VIEWS_CREATED_LATENCY, bundle);
    }

    public void registerCardDismissedListener(CardDismissedListener cardDismissedListener) {
        synchronized (this.cardDismissedListeners) {
            this.cardDismissedListeners.add(cardDismissedListener);
        }
    }

    public void registerForCardUpdates() {
        this.cardModel.addCardListener(this.cardListener);
        this.cardModel.initialize();
    }

    public void registerForExternalCardUpdates(Context context) {
        this.externalCardBroadcastReceiver = new ExternalCardBroadcastReceiver(context, new ExternalCardListener() { // from class: com.amazon.alexa.voiceui.cards.-$$Lambda$CardInteractor$F33FuQZQ4JYzTUv0400JmI4ZhFI
            @Override // com.amazon.alexa.voiceui.externalCards.ExternalCardListener
            public final void onExternalCardRendered() {
                CardInteractor.this.lambda$registerForExternalCardUpdates$0$CardInteractor();
            }
        });
        this.externalCardBroadcastReceiver.register();
    }

    public void saveInstanceState(Bundle bundle) {
        Bundle bundle2 = new Bundle();
        this.saveInstanceStateHandler.onSaveInstanceState(bundle2);
        bundle.putParcelable("cardsRouter", bundle2);
    }

    public void setDriveModeEnabled(boolean z) {
        this.driveModeEnabledObservable.onNext(Boolean.valueOf(z));
    }
}
