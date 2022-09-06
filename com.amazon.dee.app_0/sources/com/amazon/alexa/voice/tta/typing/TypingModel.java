package com.amazon.alexa.voice.tta.typing;

import android.content.Context;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.api.AlexaPlayerInfoState;
import com.amazon.alexa.voice.tta.R;
import com.amazon.alexa.voice.tta.statemachine.AlexaMediator;
import com.amazon.alexa.voice.ui.tta.TtaMessage;
import com.amazon.alexa.voice.ui.tta.TtaRequestMessage;
import com.amazon.alexa.voice.ui.tta.TtaResponseCard;
import com.amazon.alexa.voice.ui.tta.TtaResponseMessage;
import com.android.tools.r8.GeneratedOutlineSupport1;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import io.reactivex.rxjava3.subjects.ReplaySubject;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
@Singleton
/* loaded from: classes11.dex */
public class TypingModel {
    private static final String PLACEHOLDER_CLEAR_STRING = "CLEAR";
    private static final String TAG = "TypingModel";
    private static boolean hasAudioMessageBeenDisplayed = false;
    private final AlexaMediator alexaMediator;
    private final Context context;
    private final MessageHistoryManager messageHistoryManager;
    private final ReplaySubject<TtaRequestMessage> ttaRequest = ReplaySubject.create();
    private final BehaviorSubject<String> conversationClear = BehaviorSubject.create();
    private final BehaviorSubject<List<TtaMessage>> conversationHistory = BehaviorSubject.create();
    private final MessageHistoryListener messageHistoryListener = new MessageHistoryListener();
    private final CompositeDisposable disposable = new CompositeDisposable();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public class MessageHistoryListener {
        MessageHistoryListener() {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void enableAudioMessage() {
            boolean unused = TypingModel.hasAudioMessageBeenDisplayed = false;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void onClearConversationUi() {
            TypingModel.this.clearConversationUi();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void onMessageHistory(List<TtaMessage> list) {
            TypingModel.this.showConversationHistory(list);
        }
    }

    @Inject
    public TypingModel(AlexaMediator alexaMediator, MessageHistoryManager messageHistoryManager, Context context) {
        this.messageHistoryManager = messageHistoryManager;
        this.alexaMediator = alexaMediator;
        this.context = context;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearConversationUi() {
        this.conversationClear.onNext(PLACEHOLDER_CLEAR_STRING);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showConversationHistory(List<TtaMessage> list) {
        this.conversationHistory.onNext(list);
    }

    public void addSearchResultToHistory(List<TtaResponseMessage> list) {
        if (!list.isEmpty()) {
            for (TtaResponseMessage ttaResponseMessage : list) {
                this.messageHistoryManager.appendToMessageHistory(ttaResponseMessage);
            }
        }
    }

    public Observable<String> conversationClear() {
        return this.conversationClear;
    }

    public Observable<List<TtaMessage>> conversationHistory() {
        return this.conversationHistory;
    }

    public void destroy() {
        this.alexaMediator.destroy();
    }

    public void initialize(boolean z) {
        this.alexaMediator.initialize(z);
        this.messageHistoryManager.checkIfUserLoggedOut();
        this.messageHistoryManager.setMessageHistoryListener(this.messageHistoryListener);
        this.messageHistoryManager.clearConversationUi();
        this.messageHistoryManager.showMessageHistory(z);
        CompositeDisposable compositeDisposable = this.disposable;
        Observable<TtaResponseMessage> onResponse = this.alexaMediator.onResponse();
        final MessageHistoryManager messageHistoryManager = this.messageHistoryManager;
        messageHistoryManager.getClass();
        compositeDisposable.add(onResponse.subscribe(new Consumer() { // from class: com.amazon.alexa.voice.tta.typing.-$$Lambda$3PNeXbzGcgC0ViT7xE89SyvlPSA
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                MessageHistoryManager.this.updateMessageHistoryOnResponse((TtaResponseMessage) obj);
            }
        }));
        this.disposable.add(this.alexaMediator.onAlexaPlayerInfoState().subscribe(new Consumer() { // from class: com.amazon.alexa.voice.tta.typing.-$$Lambda$y6l-NUpM2T2xx_vnZCs7eDJ41uU
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                TypingModel.this.onAudioMessage((AlexaPlayerInfoState) obj);
            }
        }));
        CompositeDisposable compositeDisposable2 = this.disposable;
        Observable<TtaResponseCard> onCardResponse = this.alexaMediator.onCardResponse();
        final MessageHistoryManager messageHistoryManager2 = this.messageHistoryManager;
        messageHistoryManager2.getClass();
        compositeDisposable2.add(onCardResponse.subscribe(new Consumer() { // from class: com.amazon.alexa.voice.tta.typing.-$$Lambda$1HujGxfsy_GPIKtexl4NKpK5xVI
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                MessageHistoryManager.this.updateMessageHistoryOnResponse((TtaResponseCard) obj);
            }
        }));
    }

    public Observable<TtaResponseCard> onAlexaCard() {
        return this.alexaMediator.onCardResponse();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    public void onAudioMessage(AlexaPlayerInfoState alexaPlayerInfoState) {
        TtaResponseMessage lastTtaResponse = this.messageHistoryManager.getLastTtaResponse();
        if (!hasAudioMessageBeenDisplayed) {
            if (lastTtaResponse != null) {
                this.alexaMediator.showResponse(new TextTtaResponseMessage(lastTtaResponse.getId(), lastTtaResponse.getMessage() + " " + this.context.getString(R.string.tta_audio_player_message)));
            } else {
                this.alexaMediator.showResponse(new TextTtaResponseMessage(this.context.getString(R.string.tta_audio_player_message)));
            }
            hasAudioMessageBeenDisplayed = true;
        }
    }

    public Observable<Boolean> onListening() {
        return this.alexaMediator.onListening();
    }

    public Observable<Boolean> onThinking() {
        return this.alexaMediator.onThinking();
    }

    public void release() {
        this.alexaMediator.release();
        this.disposable.clear();
        this.messageHistoryManager.cleanUp();
    }

    public void send(TtaRequestMessage ttaRequestMessage, boolean z) {
        if (ttaRequestMessage.getMessage() == null || ttaRequestMessage.getMessage().isEmpty()) {
            return;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Sending request with message ");
        outline107.append(ttaRequestMessage.getMessage());
        outline107.toString();
        if (!z) {
            this.ttaRequest.onNext(ttaRequestMessage);
        }
        this.alexaMediator.onTextRequestReceived(ttaRequestMessage.getMessage());
        String str = "Adding user request to message history " + ttaRequestMessage.getMessage();
        this.messageHistoryManager.appendToMessageHistory(ttaRequestMessage);
        this.messageHistoryManager.setShouldClearMessageHistory(false);
    }

    public Observable<TtaRequestMessage> ttaRequest() {
        return this.ttaRequest;
    }

    public Observable<TtaResponseMessage> ttaResponse() {
        return this.alexaMediator.onResponse();
    }
}
