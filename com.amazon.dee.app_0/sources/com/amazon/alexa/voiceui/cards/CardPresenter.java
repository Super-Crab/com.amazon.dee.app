package com.amazon.alexa.voiceui.cards;

import android.annotation.SuppressLint;
import android.os.SystemClock;
import androidx.annotation.MainThread;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.voice.ui.provider.CardMode;
import com.amazon.alexa.voiceui.cards.CardView;
import io.reactivex.rxjava3.functions.Consumer;
import javax.inject.Inject;
import javax.inject.Singleton;
@Singleton
/* loaded from: classes11.dex */
public class CardPresenter {

    @VisibleForTesting
    /* loaded from: classes11.dex */
    static class CardDismissedListener implements CardView.CardDismissedListener {
        private final CardInteractor cardInteractor;

        @Override // com.amazon.alexa.voiceui.cards.CardView.CardDismissedListener
        public void onCardDismissed() {
            this.cardInteractor.cardDismissed();
        }

        private CardDismissedListener(CardInteractor cardInteractor) {
            this.cardInteractor = cardInteractor;
        }
    }

    @VisibleForTesting
    /* loaded from: classes11.dex */
    static class CardLatencyListener implements CardView.CardLatencyListener {
        private long cardInflationTimer;
        private final CardInteractor cardInteractor;
        private long cardRenderTimer;

        @Override // com.amazon.alexa.voiceui.cards.CardView.CardLatencyListener
        @MainThread
        public void onCardCreationCompleted(boolean z, CardMode cardMode) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            long j = this.cardRenderTimer;
            if (j > 0) {
                this.cardInteractor.recordCardCreationLatency(elapsedRealtime - j, z, cardMode);
            }
            long j2 = this.cardInflationTimer;
            if (j2 > 0) {
                this.cardInteractor.recordCardViewInflationLatency(elapsedRealtime - j2, z, cardMode);
            }
            this.cardRenderTimer = -1L;
            this.cardInflationTimer = -1L;
        }

        @Override // com.amazon.alexa.voiceui.cards.CardView.CardLatencyListener
        @MainThread
        public void onCardCreationFailed(String str, CardMode cardMode) {
            this.cardRenderTimer = -1L;
            this.cardInflationTimer = -1L;
            this.cardInteractor.recordCardCreationFailure(str, cardMode);
        }

        @Override // com.amazon.alexa.voiceui.cards.CardView.CardLatencyListener
        public void onCardCreationInitiated() {
            this.cardRenderTimer = SystemClock.elapsedRealtime();
        }

        @Override // com.amazon.alexa.voiceui.cards.CardView.CardLatencyListener
        public void onCardViewInflationInitiated() {
            this.cardInflationTimer = SystemClock.elapsedRealtime();
        }

        private CardLatencyListener(CardInteractor cardInteractor) {
            this.cardInteractor = cardInteractor;
        }
    }

    @Inject
    @SuppressLint({"CheckResult"})
    public CardPresenter(CardInteractor cardInteractor, final CardView cardView) {
        cardView.addCardDismissedListener(new CardDismissedListener(cardInteractor));
        cardView.addCardLatencyListener(new CardLatencyListener(cardInteractor));
        cardInteractor.getDriveModeEnabledObservable().subscribe(new Consumer<Boolean>() { // from class: com.amazon.alexa.voiceui.cards.CardPresenter.1
            @Override // io.reactivex.rxjava3.functions.Consumer
            public void accept(Boolean bool) throws Exception {
                cardView.setDriveModeEnabled(bool == null ? false : bool.booleanValue());
            }
        });
    }
}
