package com.amazon.alexa.voice.tta.typing;

import android.app.Activity;
import android.content.Intent;
import androidx.annotation.Nullable;
import com.amazon.alexa.voice.tta.features.FeatureChecker;
import com.amazon.alexa.voice.tta.features.Features;
import com.amazon.alexa.voice.tta.metrics.EventTime;
import com.amazon.alexa.voice.tta.metrics.MetricEventProcessingService;
import com.amazon.alexa.voice.tta.metrics.TtaConversationEvent;
import com.amazon.alexa.voice.tta.metrics.TtaIngressEvent;
import com.amazon.alexa.voice.tta.typing.IngressParameters;
import com.amazon.alexa.voice.ui.onedesign.tta.TtaScreenParameters;
import com.amazon.alexa.voice.ui.tta.TtaConversationHistoryListener;
import com.amazon.alexa.voice.ui.tta.TtaMessage;
import com.amazon.alexa.voice.ui.tta.TtaMessageRequestListener;
import com.amazon.alexa.voice.ui.tta.TtaMessageResponseListener;
import com.amazon.alexa.voice.ui.tta.TtaRequestMessage;
import com.amazon.alexa.voice.ui.tta.TtaResponseCard;
import com.amazon.alexa.voice.ui.tta.TtaResponseMessage;
import com.amazon.alexa.voice.ui.util.BooleanProperty;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
@Singleton
/* loaded from: classes11.dex */
public class TypingInteractor {
    private static final String TAG = "TypingInteractor";
    private final Activity activity;
    private final DefaultTtaMessageHandler defaultTtaMessageHandler;
    private final MetricEventProcessingService eventProcessingService;
    private final FeatureChecker featureChecker;
    private final IngressParameters.Provider ingressParametersProvider;
    private final MessageHistoryManager messageHistoryManager;
    private BehaviorSubject<TtaScreenParameters> ttaScreenParametersObservable;
    private final TypingModel typingModel;
    private final CompositeDisposable disposable = new CompositeDisposable();
    private final BooleanProperty isThinkingProperty = new BooleanProperty(false);

    @Inject
    public TypingInteractor(Activity activity, TypingModel typingModel, DefaultTtaMessageHandler defaultTtaMessageHandler, MessageHistoryManager messageHistoryManager, MetricEventProcessingService metricEventProcessingService, IngressParameters.Provider provider, FeatureChecker featureChecker) {
        this.activity = activity;
        this.typingModel = typingModel;
        this.defaultTtaMessageHandler = defaultTtaMessageHandler;
        this.messageHistoryManager = messageHistoryManager;
        this.eventProcessingService = metricEventProcessingService;
        this.ingressParametersProvider = provider;
        this.featureChecker = featureChecker;
        CompositeDisposable compositeDisposable = this.disposable;
        Observable<Boolean> onThinking = typingModel.onThinking();
        final BooleanProperty booleanProperty = this.isThinkingProperty;
        booleanProperty.getClass();
        compositeDisposable.add(onThinking.subscribe(new Consumer() { // from class: com.amazon.alexa.voice.tta.typing.-$$Lambda$PKdrZZRJrlvEBFdyHutChiNQ9Qc
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                BooleanProperty.this.set(((Boolean) obj).booleanValue());
            }
        }));
        this.disposable.add(typingModel.onListening().subscribe(new Consumer() { // from class: com.amazon.alexa.voice.tta.typing.-$$Lambda$TypingInteractor$Udzukp7WPS_OqIlCeCI3GIrzK8Y
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                TypingInteractor.this.lambda$new$0$TypingInteractor((Boolean) obj);
            }
        }));
        this.ttaScreenParametersObservable = BehaviorSubject.create();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: clearMessageHistoryInVoiceMode */
    public void lambda$new$0$TypingInteractor(Boolean bool) {
        if (bool == null || !bool.booleanValue()) {
            return;
        }
        if (!this.messageHistoryManager.isMessageHistoryEmpty()) {
            this.eventProcessingService.processEvent(TtaConversationEvent.CLEAR_SCREEN_ON_VOICE, EventTime.now());
            this.messageHistoryManager.clearConversationUi();
            this.messageHistoryManager.clearMessageHistory();
        }
        finishActivity();
    }

    private void recordIngressEvent() {
        IngressParameters ingressParameters = this.ingressParametersProvider.getIngressParameters();
        this.eventProcessingService.processEvent(TtaIngressEvent.create(ingressParameters.getReferrer()), ingressParameters.getStartTime());
    }

    private void setScreenParameters(String str, boolean z) {
        this.ttaScreenParametersObservable.onNext(new TtaScreenParameters.Builder().hintText(str).appSearchEnabled(z).build());
    }

    public void addSearchResultToHistory(List<TtaResponseMessage> list) {
        this.typingModel.addSearchResultToHistory(list);
    }

    public void connectToAlexaService() {
        this.typingModel.initialize(isAppSearchEnabled());
    }

    public void disconnectFromAlexaService() {
        this.typingModel.release();
    }

    public void finishActivity() {
        this.activity.finish();
    }

    public Observable<TtaScreenParameters> getTtaScreenParametersObservable() {
        return this.ttaScreenParametersObservable;
    }

    public void initializeTtaMessageHandler() {
        this.defaultTtaMessageHandler.initialize(this);
    }

    public boolean isActivityConfigurationChanging() {
        return this.activity.isChangingConfigurations();
    }

    public boolean isAppSearchEnabled() {
        return this.featureChecker.isFeatureEnabled(Features.APP_SEARCH);
    }

    public BooleanProperty isThinking() {
        return this.isThinkingProperty;
    }

    public void releaseFromAlexaService() {
        this.typingModel.destroy();
    }

    public void releaseSubscription() {
        this.disposable.clear();
    }

    public void send(TtaRequestMessage ttaRequestMessage, boolean z) {
        this.typingModel.send(ttaRequestMessage, z);
    }

    public void setConversationHistoryListener(final TtaConversationHistoryListener ttaConversationHistoryListener) {
        CompositeDisposable compositeDisposable = this.disposable;
        Observable<String> conversationClear = this.typingModel.conversationClear();
        ttaConversationHistoryListener.getClass();
        compositeDisposable.add(conversationClear.subscribe(new Consumer() { // from class: com.amazon.alexa.voice.tta.typing.-$$Lambda$U0202V6iUr8E1yH1DSc1W2b26Vc
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                TtaConversationHistoryListener.this.onClear((String) obj);
            }
        }));
        CompositeDisposable compositeDisposable2 = this.disposable;
        Observable<List<TtaMessage>> conversationHistory = this.typingModel.conversationHistory();
        ttaConversationHistoryListener.getClass();
        compositeDisposable2.add(conversationHistory.subscribe(new Consumer() { // from class: com.amazon.alexa.voice.tta.typing.-$$Lambda$TX_Y3V4VZqbQbw33TW-wHP81XMk
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                TtaConversationHistoryListener.this.onMessageHistory((List) obj);
            }
        }));
    }

    public void setRequestListener(final TtaMessageRequestListener ttaMessageRequestListener) {
        CompositeDisposable compositeDisposable = this.disposable;
        Observable<TtaRequestMessage> ttaRequest = this.typingModel.ttaRequest();
        ttaMessageRequestListener.getClass();
        compositeDisposable.add(ttaRequest.subscribe(new Consumer() { // from class: com.amazon.alexa.voice.tta.typing.-$$Lambda$p237g-PXXw9y23OArlHo-_0ST-c
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                TtaMessageRequestListener.this.onMessage((TtaRequestMessage) obj);
            }
        }));
    }

    public void setResponseListener(final TtaMessageResponseListener ttaMessageResponseListener) {
        CompositeDisposable compositeDisposable = this.disposable;
        Observable<TtaResponseMessage> ttaResponse = this.typingModel.ttaResponse();
        ttaMessageResponseListener.getClass();
        compositeDisposable.add(ttaResponse.subscribe(new Consumer() { // from class: com.amazon.alexa.voice.tta.typing.-$$Lambda$XF9JKUFh9A6cHt5o2j41AO7-Kzo
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                TtaMessageResponseListener.this.onMessage((TtaResponseMessage) obj);
            }
        }));
        CompositeDisposable compositeDisposable2 = this.disposable;
        Observable<TtaResponseCard> onAlexaCard = this.typingModel.onAlexaCard();
        ttaMessageResponseListener.getClass();
        compositeDisposable2.add(onAlexaCard.subscribe(new Consumer() { // from class: com.amazon.alexa.voice.tta.typing.-$$Lambda$MVu2y-_5Ek4OZf2m75iEh_j1Wdo
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                TtaMessageResponseListener.this.onMessage((TtaResponseCard) obj);
            }
        }));
    }

    public void updateLaunchParameters(@Nullable Intent intent) {
        if (intent != null) {
            setScreenParameters(intent.getStringExtra("hint"), isAppSearchEnabled());
        }
        this.ingressParametersProvider.update(intent);
        recordIngressEvent();
    }
}
