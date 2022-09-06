package com.amazon.alexa.voice.tta.typing;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.voice.tta.ViewDismissedListener;
import com.amazon.alexa.voice.tta.metrics.EventClock;
import com.amazon.alexa.voice.tta.metrics.MetricEventProcessingService;
import com.amazon.alexa.voice.tta.metrics.TtaInternalEvent;
import com.amazon.alexa.voice.tta.search.SearchInteractor;
import com.amazon.alexa.voice.tta.suggestions.TtaSuggestionsInteractor;
import com.amazon.alexa.voice.ui.onedesign.tta.TtaScreenParameters;
import com.amazon.alexa.voice.ui.tta.TtaRequestMessage;
import com.amazon.alexa.voice.ui.tta.search.TtaInChatResultCard;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Consumer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
@Singleton
/* loaded from: classes11.dex */
public class TypingPresenter {
    private static final String TAG = "TypingPresenter";
    private final EventClock eventClock;
    private final MetricEventProcessingService eventProcessingService;
    private final PersistentStorage persistentStorage;
    private final SearchInteractor searchInteractor;
    private final TtaSuggestionsInteractor suggestionsInteractor;
    private final TypingInteractor typingInteractor;
    private final TypingView typingView;

    @VisibleForTesting
    /* loaded from: classes11.dex */
    static class TypingDismissedListener implements ViewDismissedListener {
        private final TypingPresenter typingPresenter;

        TypingDismissedListener(TypingPresenter typingPresenter) {
            this.typingPresenter = typingPresenter;
        }

        @Override // com.amazon.alexa.voice.tta.ViewDismissedListener
        public void onViewDismissed() {
            this.typingPresenter.typingViewDismissed();
        }
    }

    @Inject
    public TypingPresenter(final TypingView typingView, PersistentStorage persistentStorage, MetricEventProcessingService metricEventProcessingService, EventClock eventClock, TtaInteractorProvider ttaInteractorProvider) {
        this.typingInteractor = ttaInteractorProvider.getTypingInteractor();
        this.typingView = typingView;
        this.searchInteractor = ttaInteractorProvider.getSearchInteractor();
        this.suggestionsInteractor = ttaInteractorProvider.getSuggestionsInteractor();
        this.persistentStorage = persistentStorage;
        this.eventProcessingService = metricEventProcessingService;
        this.eventClock = eventClock;
        Observable<TtaScreenParameters> ttaScreenParametersObservable = this.typingInteractor.getTtaScreenParametersObservable();
        typingView.getClass();
        ttaScreenParametersObservable.subscribe(new Consumer() { // from class: com.amazon.alexa.voice.tta.typing.-$$Lambda$FV1vTPrxf7gNWdkSn_8z6o4sajI
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                TypingView.this.setScreenParameters((TtaScreenParameters) obj);
            }
        });
        this.searchInteractor.onTextRequest().subscribe(new Consumer() { // from class: com.amazon.alexa.voice.tta.typing.-$$Lambda$TypingPresenter$enz_uBqqpREiktw1vWY_elTsRU8
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                TypingPresenter.this.lambda$new$1$TypingPresenter((String) obj);
            }
        });
        Observable<R> map = this.searchInteractor.onSearchResults().map($$Lambda$TypingPresenter$81e1z6Zuz8WBqz13sDGBjV7ZU.INSTANCE);
        final TypingInteractor typingInteractor = this.typingInteractor;
        typingInteractor.getClass();
        map.subscribe(new Consumer() { // from class: com.amazon.alexa.voice.tta.typing.-$$Lambda$o3DtId5EqPWkH4WK6NsQH_JFh1Q
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                TypingInteractor.this.addSearchResultToHistory((List) obj);
            }
        });
        this.suggestionsInteractor.onSuggestionUtterance().subscribe(new Consumer() { // from class: com.amazon.alexa.voice.tta.typing.-$$Lambda$TypingPresenter$YYSxtIbGVA5uYK0H62A6RXXe30I
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                TypingPresenter.this.lambda$new$4$TypingPresenter((String) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ List lambda$new$2(List list) throws Throwable {
        ArrayList arrayList = new ArrayList();
        Iterator it2 = list.iterator();
        while (it2.hasNext()) {
            arrayList.add((TtaInChatResultCard) it2.next());
        }
        return arrayList;
    }

    static /* synthetic */ String lambda$null$0(String str) {
        return str;
    }

    static /* synthetic */ String lambda$null$3(String str) {
        return str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void typingViewDismissed() {
        if (!this.typingView.isVisible()) {
            this.persistentStorage.setIsUserOnTtaScreen(false);
            this.typingInteractor.finishActivity();
        }
    }

    public boolean backButtonPressed() {
        return this.typingView.backButtonPressed();
    }

    public void destroy() {
        if (this.typingInteractor.isActivityConfigurationChanging()) {
            this.typingView.prepareForConfigurationChange();
            return;
        }
        this.typingView.cleanup();
        this.typingInteractor.releaseFromAlexaService();
        this.typingInteractor.releaseSubscription();
        this.searchInteractor.releaseSubscription();
        this.suggestionsInteractor.release();
        this.eventProcessingService.stop(this.eventClock.now());
    }

    public void initialize() {
        this.eventProcessingService.start(this.eventClock.now());
        this.typingInteractor.initializeTtaMessageHandler();
        this.searchInteractor.initializeHandler();
        this.suggestionsInteractor.initializeHandler(this.typingInteractor.isAppSearchEnabled());
        this.typingView.registerViewDismissedListener(new TypingDismissedListener(this));
    }

    public /* synthetic */ void lambda$new$1$TypingPresenter(final String str) throws Throwable {
        this.typingInteractor.send(new TtaRequestMessage() { // from class: com.amazon.alexa.voice.tta.typing.-$$Lambda$TypingPresenter$UQLJXEgj5w5vmj_pb0WqBhdvMAU
            @Override // com.amazon.alexa.voice.ui.tta.TtaRequestMessage, com.amazon.alexa.voice.ui.tta.TtaMessage
            public final String getMessage() {
                return str;
            }
        }, false);
    }

    public /* synthetic */ void lambda$new$4$TypingPresenter(final String str) throws Throwable {
        this.typingInteractor.send(new TtaRequestMessage() { // from class: com.amazon.alexa.voice.tta.typing.-$$Lambda$TypingPresenter$4h758QqChrWNpZwb53tmgXKFz2k
            @Override // com.amazon.alexa.voice.ui.tta.TtaRequestMessage, com.amazon.alexa.voice.ui.tta.TtaMessage
            public final String getMessage() {
                return str;
            }
        }, false);
    }

    public void launched(@Nullable Intent intent) {
        this.persistentStorage.setIsUserOnTtaScreen(true);
        this.typingInteractor.updateLaunchParameters(intent);
        if (this.typingInteractor.isAppSearchEnabled()) {
            this.searchInteractor.initializeData();
        }
    }

    public void pause() {
        this.eventProcessingService.processEvent(TtaInternalEvent.ACTIVITY_PAUSED, this.eventClock.now());
    }

    public void resume() {
        this.eventProcessingService.processEvent(TtaInternalEvent.ACTIVITY_RESUMED, this.eventClock.now());
    }

    public void saveInstanceState(Bundle bundle) {
        this.typingView.saveInstanceState(bundle);
    }

    public void start() {
        this.typingInteractor.connectToAlexaService();
        this.eventProcessingService.processEvent(TtaInternalEvent.SCREEN_LAUNCHED, this.eventClock.now());
        this.typingView.showTypingView();
    }

    public void stop() {
        this.typingInteractor.disconnectFromAlexaService();
    }
}
