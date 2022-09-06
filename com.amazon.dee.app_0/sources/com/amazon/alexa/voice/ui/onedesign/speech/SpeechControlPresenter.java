package com.amazon.alexa.voice.ui.onedesign.speech;

import com.amazon.alexa.voice.ui.metrics.CardMetricsInteractor;
import com.amazon.alexa.voice.ui.onedesign.speech.SpeechControlContract;
/* loaded from: classes11.dex */
public final class SpeechControlPresenter implements SpeechControlContract.Presenter {
    private final CardMetricsInteractor cardMetricsInteractor;
    private final SpeechControlContract.Interactor interactor;
    private final SpeechControlContract.View view;

    public SpeechControlPresenter(SpeechControlContract.View view, SpeechControlContract.Interactor interactor, CardMetricsInteractor cardMetricsInteractor) {
        this.view = view;
        this.interactor = interactor;
        this.cardMetricsInteractor = cardMetricsInteractor;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.speech.SpeechControlContract.Presenter
    public void buttonClicked() {
        this.cardMetricsInteractor.startTimerWhenTappingIngressButtonOnCard();
        this.interactor.cancel();
        this.interactor.recognizeSpeech();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.speech.SpeechControlContract.Presenter
    public void start() {
        this.view.displayVoiceIngressButton(this.interactor.shouldEmbedVoiceIngress());
    }
}
