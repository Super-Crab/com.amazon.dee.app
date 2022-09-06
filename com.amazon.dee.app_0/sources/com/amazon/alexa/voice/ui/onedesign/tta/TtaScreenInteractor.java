package com.amazon.alexa.voice.ui.onedesign.tta;

import android.content.Context;
import android.util.Log;
import com.amazon.alexa.voice.ui.onedesign.tta.TtaScreenContract;
import com.amazon.alexa.voice.ui.onedesign.tta.items.AlexaResponseTtaItem;
import com.amazon.alexa.voice.ui.onedesign.tta.items.CardTtaItem;
import com.amazon.alexa.voice.ui.onedesign.tta.items.InChatHintTtaItem;
import com.amazon.alexa.voice.ui.onedesign.tta.items.InChatLinkTtaItem;
import com.amazon.alexa.voice.ui.onedesign.tta.items.PillResultTtaItem;
import com.amazon.alexa.voice.ui.onedesign.tta.items.UserInputTtaItem;
import com.amazon.alexa.voice.ui.onedesign.tta.metrics.TtaUiEvent;
import com.amazon.alexa.voice.ui.onedesign.tta.metrics.TtaUiEventWithType;
import com.amazon.alexa.voice.ui.suggestions.SuggestionItem;
import com.amazon.alexa.voice.ui.suggestions.SuggestionsHandler;
import com.amazon.alexa.voice.ui.tta.TtaConversationHistoryListener;
import com.amazon.alexa.voice.ui.tta.TtaMessage;
import com.amazon.alexa.voice.ui.tta.TtaMessageHandler;
import com.amazon.alexa.voice.ui.tta.TtaMessageRequestListener;
import com.amazon.alexa.voice.ui.tta.TtaMessageResponseListener;
import com.amazon.alexa.voice.ui.tta.TtaNavigator;
import com.amazon.alexa.voice.ui.tta.TtaRequestMessage;
import com.amazon.alexa.voice.ui.tta.TtaResponseCard;
import com.amazon.alexa.voice.ui.tta.TtaResponseMessage;
import com.amazon.alexa.voice.ui.tta.TtaSuggestionsListener;
import com.amazon.alexa.voice.ui.tta.metrics.TtaEventSender;
import com.amazon.alexa.voice.ui.tta.search.PillResultItem;
import com.amazon.alexa.voice.ui.tta.search.ResultItem;
import com.amazon.alexa.voice.ui.tta.search.TtaInChatResultCard;
import com.amazon.alexa.voice.ui.tta.search.TtaResultHandler;
import com.amazon.alexa.voice.ui.tta.search.TtaResultListener;
import com.amazon.alexa.voice.ui.util.BaseProperty;
import com.amazon.alexa.voice.ui.util.BooleanProperty;
import com.android.tools.r8.GeneratedOutlineSupport1;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import io.reactivex.rxjava3.subjects.ReplaySubject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes11.dex */
public class TtaScreenInteractor implements TtaScreenContract.Interactor, TtaMessageResponseListener, TtaMessageRequestListener, TtaConversationHistoryListener, TtaResultListener, TtaSuggestionsListener {
    private static final String TAG = "TtaScreenInteractor";
    private final Context context;
    private BaseProperty.OnChangedListener listenerForThinkingState;
    private final TtaScreenContract.Mediator mediator;
    private final TtaMessageHandler messageHandler;
    private final SuggestionsHandler suggestionsHandler;
    private BooleanProperty thinkingStateListeningProperty;
    private final TtaEventSender ttaEventSender;
    private final TtaNavigator ttaNavigator;
    private final TtaResultHandler ttaResultHandler;
    private final TtaScreenParametersModel ttaScreenParameters;
    private BehaviorSubject<BooleanProperty> thinkingStateObservable = BehaviorSubject.create();
    private final ReplaySubject<Object> conversationObservable = ReplaySubject.create();
    private final BehaviorSubject<Boolean> hideKeyboardObservable = BehaviorSubject.create();
    private BehaviorSubject<String> clearConversationObservable = BehaviorSubject.create();
    private final ReplaySubject<List<Object>> pillResultsObservable = ReplaySubject.create();
    private final BehaviorSubject<List<Object>> suggestionsObservable = BehaviorSubject.create();

    public TtaScreenInteractor(TtaScreenContract.Mediator mediator, TtaScreenParametersModel ttaScreenParametersModel, TtaNavigator ttaNavigator, Context context, TtaEventSender ttaEventSender, TtaHandlerProvider ttaHandlerProvider) {
        this.mediator = mediator;
        this.messageHandler = ttaHandlerProvider.getTtaMessageHandler();
        this.suggestionsHandler = ttaHandlerProvider.getSuggestionsHandler();
        this.ttaScreenParameters = ttaScreenParametersModel;
        this.ttaNavigator = ttaNavigator;
        this.ttaEventSender = ttaEventSender;
        this.messageHandler.setRequestListener(this);
        this.messageHandler.setResponseListener(this);
        this.messageHandler.setConversationHistoryListener(this);
        this.ttaResultHandler = ttaHandlerProvider.getTtaResultHandler();
        this.ttaResultHandler.setResultListener(this);
        this.ttaResultHandler.setResponseListener(this);
        this.suggestionsHandler.setSuggestionListener(this);
        this.context = context;
        this.thinkingStateListeningProperty = this.messageHandler.isThinking();
        this.listenerForThinkingState = new BaseProperty.OnChangedListener() { // from class: com.amazon.alexa.voice.ui.onedesign.tta.-$$Lambda$TtaScreenInteractor$GvCtesf87bokFawh9ydu_wnLe4E
            @Override // com.amazon.alexa.voice.ui.util.BaseProperty.OnChangedListener
            public final void onPropertyChanged() {
                TtaScreenInteractor.this.lambda$new$0$TtaScreenInteractor();
            }
        };
        this.thinkingStateListeningProperty.addOnChangedListener(this.listenerForThinkingState);
    }

    private void emitInChatTtaItem(TtaInChatResultCard ttaInChatResultCard) {
        int type = ttaInChatResultCard.getType();
        if (type == 1) {
            this.ttaEventSender.sendEvent(TtaUiEvent.VOX_SIMBA_INCHAT_SHOWN);
            this.conversationObservable.onNext(InChatLinkTtaItem.fromTtaInChatResultCard(ttaInChatResultCard));
        } else if (type != 2) {
            Log.e(TAG, "Received unsupported type.");
        } else {
            this.ttaEventSender.sendEvent(TtaUiEvent.VOX_SIMBA_HINT_SHOWN);
            this.conversationObservable.onNext(InChatHintTtaItem.fromTtaInChatResultCard(ttaInChatResultCard));
        }
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.tta.TtaScreenContract.Interactor
    public void close() {
        this.mediator.close();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.tta.TtaScreenContract.Interactor
    public Observable<Object> getConversationUpdate() {
        return this.conversationObservable;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.tta.TtaScreenContract.Interactor
    public Observable<Boolean> getHideKeyboardUpdate() {
        return this.hideKeyboardObservable;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.tta.TtaScreenContract.Interactor
    public void getSuggestions(CharSequence charSequence) {
        this.suggestionsHandler.getSuggestions(charSequence.toString());
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.tta.TtaScreenContract.Interactor
    public Observable<BooleanProperty> getThinkingStateUpdate() {
        return this.thinkingStateObservable;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.tta.TtaScreenContract.Interactor
    public TtaScreenParametersModel getTtaScreenParameters() {
        return this.ttaScreenParameters;
    }

    public /* synthetic */ void lambda$new$0$TtaScreenInteractor() {
        this.thinkingStateObservable.onNext(this.thinkingStateListeningProperty);
    }

    @Override // com.amazon.alexa.voice.ui.tta.TtaConversationHistoryListener
    public void onClear(String str) {
        this.clearConversationObservable.onNext(str);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.tta.TtaScreenContract.Interactor
    public Observable<String> onClearConversation() {
        return this.clearConversationObservable;
    }

    @Override // com.amazon.alexa.voice.ui.tta.TtaMessageResponseListener
    public void onMessage(TtaResponseMessage ttaResponseMessage) {
        String str = "onMessage(): " + ttaResponseMessage;
        this.conversationObservable.onNext(AlexaResponseTtaItem.builder().itemText(ttaResponseMessage.getMessage()).itemId(ttaResponseMessage.getId()).build());
    }

    @Override // com.amazon.alexa.voice.ui.tta.TtaConversationHistoryListener
    public void onMessageHistory(List<TtaMessage> list) {
        for (TtaMessage ttaMessage : list) {
            if (ttaMessage instanceof TtaInChatResultCard) {
                emitInChatTtaItem((TtaInChatResultCard) ttaMessage);
            } else if (ttaMessage instanceof TtaResponseCard) {
                this.conversationObservable.onNext(CardTtaItem.fromTtaResponseCard((TtaResponseCard) ttaMessage));
            } else if (ttaMessage instanceof TtaRequestMessage) {
                this.conversationObservable.onNext(UserInputTtaItem.builder().itemText(ttaMessage.getMessage()).build());
            } else if (ttaMessage instanceof TtaResponseMessage) {
                this.conversationObservable.onNext(AlexaResponseTtaItem.builder().itemText(ttaMessage.getMessage()).itemId(((TtaResponseMessage) ttaMessage).getId()).build());
            } else {
                Log.e(TAG, "Received unsupported TtaMessage.");
            }
        }
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.tta.TtaScreenContract.Interactor
    public Observable<List<Object>> onPillResults() {
        return this.pillResultsObservable;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.tta.TtaScreenContract.Interactor
    public void onPillSelected(PillResultTtaItem pillResultTtaItem) {
        this.ttaResultHandler.onResultSelected(TextPillResultItem.create(pillResultTtaItem));
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.tta.TtaScreenContract.Interactor
    public Observable<List<Object>> onSuggestionResults() {
        return this.suggestionsObservable;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.tta.TtaScreenContract.Interactor
    public void onSuggestionSelected(String str, String str2, String str3) {
        this.suggestionsHandler.onSuggestionSelected(str, str2, str3);
    }

    @Override // com.amazon.alexa.voice.ui.tta.TtaSuggestionsListener
    public void onSuggestionsList(List<SuggestionItem> list) {
        ArrayList arrayList = new ArrayList();
        for (SuggestionItem suggestionItem : list) {
            arrayList.add(suggestionItem);
        }
        this.suggestionsObservable.onNext(arrayList);
    }

    @Override // com.amazon.alexa.voice.ui.tta.search.TtaResultListener
    public void onUpdate(List<ResultItem> list) {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onUpdate(): list with total items - ");
        outline107.append(list.size());
        outline107.toString();
        if (!list.isEmpty()) {
            if (list.get(0) instanceof PillResultItem) {
                ArrayList arrayList = new ArrayList();
                Iterator<ResultItem> it2 = list.iterator();
                while (it2.hasNext()) {
                    arrayList.add(PillResultTtaItem.fromPillResultItem((PillResultItem) it2.next()));
                }
                this.pillResultsObservable.onNext(arrayList);
                return;
            }
            Log.e(TAG, "Received unsupported ResultItem.");
        }
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.tta.TtaScreenContract.Interactor
    public void openVoiceIngress() {
        this.mediator.openVoiceScrim();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.tta.TtaScreenContract.Interactor
    public void sendInput(final CharSequence charSequence) {
        TtaMessageHandler ttaMessageHandler = this.messageHandler;
        charSequence.getClass();
        ttaMessageHandler.send(new TtaRequestMessage() { // from class: com.amazon.alexa.voice.ui.onedesign.tta.-$$Lambda$6oLYBBq9BMuT8kW1sFBxmQkjjVs
            @Override // com.amazon.alexa.voice.ui.tta.TtaRequestMessage, com.amazon.alexa.voice.ui.tta.TtaMessage
            public final String getMessage() {
                return charSequence.toString();
            }
        });
        this.conversationObservable.onNext(UserInputTtaItem.builder().itemText(charSequence).build());
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.tta.TtaScreenContract.Interactor
    public void stop() {
        BaseProperty.OnChangedListener onChangedListener;
        BooleanProperty booleanProperty = this.thinkingStateListeningProperty;
        if (booleanProperty == null || (onChangedListener = this.listenerForThinkingState) == null) {
            return;
        }
        booleanProperty.removeOnChangedListener(onChangedListener);
    }

    @Override // com.amazon.alexa.voice.ui.tta.TtaMessageResponseListener
    public void onMessage(List<TtaInChatResultCard> list) {
        this.hideKeyboardObservable.onNext(true);
        for (TtaInChatResultCard ttaInChatResultCard : list) {
            emitInChatTtaItem(ttaInChatResultCard);
        }
    }

    @Override // com.amazon.alexa.voice.ui.tta.TtaMessageResponseListener
    public void onMessage(TtaResponseCard ttaResponseCard) {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onMessage(): ");
        outline107.append(ttaResponseCard.getDescription());
        outline107.toString();
        this.ttaEventSender.sendEvent(new TtaUiEventWithType(TtaUiEvent.VOX_TTA_IN_CHAT_LINK_GENERATED, ttaResponseCard.getCardType()));
        this.conversationObservable.onNext(CardTtaItem.fromTtaResponseCard(ttaResponseCard));
    }

    @Override // com.amazon.alexa.voice.ui.tta.TtaMessageRequestListener
    public void onMessage(TtaRequestMessage ttaRequestMessage) {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onMessage() request message is : ");
        outline107.append(ttaRequestMessage.getMessage());
        outline107.toString();
        this.conversationObservable.onNext(UserInputTtaItem.builder().itemText(ttaRequestMessage.getMessage()).build());
    }
}
