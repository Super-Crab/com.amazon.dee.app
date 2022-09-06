package com.amazon.alexa.voice.ui.onedesign.scrim;

import com.amazon.alexa.voice.ui.onedesign.scrim.ScrimContract;
import com.amazon.alexa.voice.ui.onedesign.util.Properties;
import com.amazon.alexa.voice.ui.speech.AttentionSystemContract;
import com.amazon.alexa.voice.ui.speech.SpeechController;
import com.amazon.alexa.voice.ui.speech.SpeechRecognizer;
import com.amazon.alexa.voice.ui.util.AlexaState;
import com.amazon.alexa.voice.ui.util.BaseProperty;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
/* loaded from: classes11.dex */
public final class ScrimInteractor implements ScrimContract.Interactor {
    private final BehaviorSubject<AlexaState> alexaState = BehaviorSubject.create();
    private BaseProperty alexaStateProperty;
    private BaseProperty.OnChangedListener listenerForAlexaState;
    private BaseProperty.OnChangedListener listenerForListening;
    private BaseProperty.OnChangedListener listenerForProcessing;
    private final ScrimContract.Mediator mediator;
    private ScrimParametersModel scrimParameters;
    private final Observable<Float> soundLevel;
    private BaseProperty speechControllerListeningProperty;
    private BaseProperty speechControllerProcessingProperty;
    private SpeechRecognizer speechRecognizer;

    public ScrimInteractor(final AttentionSystemContract attentionSystemContract, ScrimContract.Mediator mediator, ScrimParametersModel scrimParametersModel, SpeechRecognizer speechRecognizer) {
        this.mediator = mediator;
        this.scrimParameters = scrimParametersModel;
        this.soundLevel = Properties.toObservable(attentionSystemContract.soundLevel());
        this.speechRecognizer = speechRecognizer;
        this.alexaStateProperty = attentionSystemContract.alexaState();
        this.listenerForAlexaState = new BaseProperty.OnChangedListener() { // from class: com.amazon.alexa.voice.ui.onedesign.scrim.-$$Lambda$ScrimInteractor$G0skq5k3EC4FmmzKL3NO2cikxvE
            @Override // com.amazon.alexa.voice.ui.util.BaseProperty.OnChangedListener
            public final void onPropertyChanged() {
                ScrimInteractor.this.lambda$new$0$ScrimInteractor(attentionSystemContract);
            }
        };
        this.alexaStateProperty.addOnChangedListener(this.listenerForAlexaState);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.scrim.ScrimContract.Interactor
    public Observable<AlexaState> alexaState() {
        return this.alexaState;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.scrim.ScrimContract.Interactor
    public void close() {
        this.mediator.close();
        this.speechRecognizer.cancel();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.scrim.ScrimContract.Interactor
    public ScrimParametersModel getScrimParameters() {
        return this.scrimParameters;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.scrim.ScrimContract.Interactor
    public Observable<Float> getSoundLevel() {
        return this.soundLevel;
    }

    public /* synthetic */ void lambda$new$0$ScrimInteractor(AttentionSystemContract attentionSystemContract) {
        this.alexaState.onNext(attentionSystemContract.alexaState().get());
    }

    public /* synthetic */ void lambda$new$1$ScrimInteractor(SpeechController speechController) {
        if (speechController.isListening().get()) {
            this.alexaState.onNext(AlexaState.LISTENING);
        } else if (speechController.isProcessing().get()) {
        } else {
            this.alexaState.onNext(AlexaState.IDLE);
        }
    }

    public /* synthetic */ void lambda$new$2$ScrimInteractor(SpeechController speechController) {
        if (speechController.isProcessing().get()) {
            this.alexaState.onNext(AlexaState.THINKING);
        } else if (speechController.isListening().get()) {
        } else {
            this.alexaState.onNext(AlexaState.IDLE);
        }
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.scrim.ScrimContract.Interactor
    public void openTTA() {
        this.mediator.openTTA();
        this.speechRecognizer.cancel();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.scrim.ScrimContract.Interactor
    public void stop() {
        BaseProperty.OnChangedListener onChangedListener;
        BaseProperty.OnChangedListener onChangedListener2;
        BaseProperty.OnChangedListener onChangedListener3;
        BaseProperty baseProperty = this.speechControllerListeningProperty;
        if (baseProperty != null && (onChangedListener3 = this.listenerForListening) != null) {
            baseProperty.removeOnChangedListener(onChangedListener3);
        }
        BaseProperty baseProperty2 = this.speechControllerProcessingProperty;
        if (baseProperty2 != null && (onChangedListener2 = this.listenerForProcessing) != null) {
            baseProperty2.removeOnChangedListener(onChangedListener2);
        }
        BaseProperty baseProperty3 = this.alexaStateProperty;
        if (baseProperty3 == null || (onChangedListener = this.listenerForAlexaState) == null) {
            return;
        }
        baseProperty3.removeOnChangedListener(onChangedListener);
    }

    public ScrimInteractor(final SpeechController speechController, ScrimContract.Mediator mediator, ScrimParametersModel scrimParametersModel) {
        this.mediator = mediator;
        this.scrimParameters = scrimParametersModel;
        this.soundLevel = Properties.toObservable(speechController.getSoundLevel());
        this.speechControllerListeningProperty = speechController.isListening();
        this.speechControllerProcessingProperty = speechController.isProcessing();
        this.listenerForListening = new BaseProperty.OnChangedListener() { // from class: com.amazon.alexa.voice.ui.onedesign.scrim.-$$Lambda$ScrimInteractor$P2x0g2Gh4M73EgwfaxoFUHvhu00
            @Override // com.amazon.alexa.voice.ui.util.BaseProperty.OnChangedListener
            public final void onPropertyChanged() {
                ScrimInteractor.this.lambda$new$1$ScrimInteractor(speechController);
            }
        };
        this.speechControllerListeningProperty.addOnChangedListener(this.listenerForListening);
        this.listenerForProcessing = new BaseProperty.OnChangedListener() { // from class: com.amazon.alexa.voice.ui.onedesign.scrim.-$$Lambda$ScrimInteractor$BWo45jwsKa7eF5N5IBS5Nc_jooo
            @Override // com.amazon.alexa.voice.ui.util.BaseProperty.OnChangedListener
            public final void onPropertyChanged() {
                ScrimInteractor.this.lambda$new$2$ScrimInteractor(speechController);
            }
        };
        this.speechControllerProcessingProperty.addOnChangedListener(this.listenerForProcessing);
    }
}
