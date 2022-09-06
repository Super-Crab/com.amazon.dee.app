package com.amazon.alexa.voice.ui.onedesign.tta;

import android.text.TextUtils;
import com.amazon.alexa.voice.ui.onedesign.tta.TtaScreenContract;
import com.amazon.alexa.voice.ui.onedesign.tta.items.PillResultTtaItem;
import com.amazon.alexa.voice.ui.onedesign.tta.items.ThinkingTtaItem;
import com.amazon.alexa.voice.ui.onedesign.tta.metrics.TtaUiEvent;
import com.amazon.alexa.voice.ui.onedesign.tta.metrics.TtaUiEventWithCount;
import com.amazon.alexa.voice.ui.onedesign.util.Resources;
import com.amazon.alexa.voice.ui.tta.metrics.TtaEventSender;
import com.amazon.alexa.voice.ui.util.BooleanProperty;
import com.android.tools.r8.GeneratedOutlineSupport1;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.subjects.PublishSubject;
import java.util.List;
import java.util.concurrent.TimeUnit;
/* loaded from: classes11.dex */
public class TtaScreenPresenter implements TtaScreenContract.Presenter, UserInputWatcher {
    private static final int DEBOUNCE_TIMEOUT = 100;
    private static final int DEFAULT_THINKING_DELAY = 100;
    private static final int SUGGESTION_MINIMUM_TEXT_COUNT = 3;
    private static final String TAG = "TtaScreenPresenter";
    private final TtaEventSender eventSender;
    private CharSequence inputText;
    private final TtaScreenContract.Interactor interactor;
    private final Resources resources;
    private Disposable timerDisposable;
    private final TtaScreenContract.View view;
    private final CompositeDisposable disposables = new CompositeDisposable();
    private final PublishSubject<CharSequence> onTextChanged = PublishSubject.create();

    public TtaScreenPresenter(TtaScreenContract.View view, TtaScreenContract.Interactor interactor, Resources resources, TtaEventSender ttaEventSender) {
        this.view = view;
        this.interactor = interactor;
        this.resources = resources;
        this.eventSender = ttaEventSender;
    }

    private void sendSuggestionSelectedMetrics(int i, int i2) {
        this.eventSender.sendEvent(TtaUiEvent.SUGGESTION_TAPPED);
        this.eventSender.sendEvent(new TtaUiEventWithCount(TtaUiEvent.VOX_CHARS_BEFORE_SUGGESTIONS, i2));
        this.eventSender.sendEvent(new TtaUiEventWithCount(TtaUiEvent.VOX_SIMBA_SUGGESTION_SELECTED_INDEX, i));
        this.eventSender.sendEvent(TtaUiEvent.VOX_SIMBA_ITEM_SELECTED);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showPills(List<Object> list) {
        if (list.size() <= 0) {
            return;
        }
        Object obj = list.get(0);
        if (obj instanceof PillResultTtaItem) {
            PillResultTtaItem pillResultTtaItem = (PillResultTtaItem) obj;
            if (pillResultTtaItem.getItemType() == 0) {
                this.eventSender.sendEvent(TtaUiEvent.SHOW_MORE_DISPLAYED);
            } else if (pillResultTtaItem.isFromSimba()) {
                this.eventSender.sendEvent(TtaUiEvent.PILL_SHOWN);
            } else {
                this.eventSender.sendEvent(TtaUiEvent.VOX_TTA_LOCAL_PILL_SHOWN);
            }
        }
        this.view.showPills(list);
    }

    private void stopThinkingDelayTimer() {
        Disposable disposable = this.timerDisposable;
        if (disposable == null || disposable.isDisposed()) {
            return;
        }
        this.timerDisposable.dispose();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.tta.TtaScreenContract.Presenter
    public void closeButtonClicked() {
        this.interactor.close();
    }

    Disposable getTimerDisposable() {
        return this.timerDisposable;
    }

    public /* synthetic */ void lambda$null$2$TtaScreenPresenter(ThinkingTtaItem thinkingTtaItem) throws Throwable {
        this.view.add(thinkingTtaItem);
    }

    public /* synthetic */ void lambda$start$0$TtaScreenPresenter(Object obj) throws Throwable {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("received item ");
        outline107.append(obj.toString());
        outline107.toString();
        this.view.add(obj);
    }

    public /* synthetic */ void lambda$start$1$TtaScreenPresenter(Boolean bool) throws Throwable {
        String str = "received hideKeyboard " + bool;
        this.view.hideKeyboard();
    }

    public /* synthetic */ void lambda$start$3$TtaScreenPresenter(BooleanProperty booleanProperty) throws Throwable {
        final ThinkingTtaItem create = ThinkingTtaItem.create();
        if (booleanProperty.get()) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("received item ");
            outline107.append(create.toString());
            outline107.toString();
            this.timerDisposable = Completable.timer(100L, TimeUnit.MILLISECONDS).subscribe(new Action() { // from class: com.amazon.alexa.voice.ui.onedesign.tta.-$$Lambda$TtaScreenPresenter$IU48pCJsNAaEM_m0QxYwYgLkY8s
                @Override // io.reactivex.rxjava3.functions.Action
                public final void run() {
                    TtaScreenPresenter.this.lambda$null$2$TtaScreenPresenter(create);
                }
            });
            return;
        }
        stopThinkingDelayTimer();
        this.view.remove(create);
    }

    public /* synthetic */ void lambda$start$4$TtaScreenPresenter(String str) throws Throwable {
        this.view.clear();
    }

    public /* synthetic */ void lambda$start$5$TtaScreenPresenter(CharSequence charSequence) throws Throwable {
        this.interactor.getSuggestions(charSequence);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.tta.UserInputWatcher
    public void onTextChanged(CharSequence charSequence) {
        this.inputText = charSequence.toString().trim();
        this.view.enableSendButton(this.inputText.length() != 0);
        if (this.inputText.length() >= 3) {
            this.onTextChanged.onNext(this.inputText);
        } else {
            this.view.hideSuggestions();
        }
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.tta.TtaScreenContract.Presenter
    public void pillSelected(PillResultTtaItem pillResultTtaItem) {
        if (pillResultTtaItem.getItemType() != 1) {
            this.view.hidePills();
        }
        if (pillResultTtaItem.isFromSimba()) {
            this.eventSender.sendEvent(TtaUiEvent.PILL_TAPPED);
        } else {
            this.eventSender.sendEvent(TtaUiEvent.VOX_TTA_LOCAL_PILL_SELECTED);
        }
        if (pillResultTtaItem.getItemType() == 0) {
            this.eventSender.sendEvent(TtaUiEvent.VOX_SIMBA_SHOW_MORE_TAPPED);
        }
        this.interactor.onPillSelected(pillResultTtaItem);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.tta.TtaScreenContract.Presenter
    public void screenVisible() {
        this.eventSender.sendEvent(TtaUiEvent.TYPING_SCREEN_VISIBLE);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.tta.TtaScreenContract.Presenter
    public void sendButtonClicked() {
        CharSequence charSequence = this.inputText;
        if (charSequence != null && charSequence.length() > 0) {
            this.interactor.sendInput(this.inputText);
        }
        this.view.setInputTextValue("");
        this.view.hidePills();
        this.view.hideSuggestions();
        this.eventSender.sendEvent(TtaUiEvent.MESSAGE_SENT);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.tta.TtaScreenContract.Presenter
    public void start() {
        this.view.setUserInputWatcher(this);
        TtaScreenParametersModel ttaScreenParameters = this.interactor.getTtaScreenParameters();
        String hintText = ttaScreenParameters.getHintText();
        if (!TextUtils.isEmpty(hintText)) {
            this.view.setInputTextValue(hintText);
        } else {
            this.view.setInputTextValue("");
        }
        this.disposables.add(this.interactor.getConversationUpdate().subscribe(new Consumer() { // from class: com.amazon.alexa.voice.ui.onedesign.tta.-$$Lambda$TtaScreenPresenter$F4r2MIUHegdm0drVSQ0YaCMhuRE
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                TtaScreenPresenter.this.lambda$start$0$TtaScreenPresenter(obj);
            }
        }));
        this.disposables.add(this.interactor.getHideKeyboardUpdate().subscribe(new Consumer() { // from class: com.amazon.alexa.voice.ui.onedesign.tta.-$$Lambda$TtaScreenPresenter$Z7OeeM_1b7GPuTmQneKvrM80Lxw
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                TtaScreenPresenter.this.lambda$start$1$TtaScreenPresenter((Boolean) obj);
            }
        }));
        this.disposables.add(this.interactor.getThinkingStateUpdate().subscribe(new Consumer() { // from class: com.amazon.alexa.voice.ui.onedesign.tta.-$$Lambda$TtaScreenPresenter$9jZ7P-MuZXZtWMdZ_BfSCea3sjI
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                TtaScreenPresenter.this.lambda$start$3$TtaScreenPresenter((BooleanProperty) obj);
            }
        }));
        this.disposables.add(this.interactor.onClearConversation().subscribe(new Consumer() { // from class: com.amazon.alexa.voice.ui.onedesign.tta.-$$Lambda$TtaScreenPresenter$Z-5YjlL_M4NE6qCgRIbDF9d8f54
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                TtaScreenPresenter.this.lambda$start$4$TtaScreenPresenter((String) obj);
            }
        }));
        this.disposables.add(this.onTextChanged.debounce(100L, TimeUnit.MILLISECONDS).subscribe(new Consumer() { // from class: com.amazon.alexa.voice.ui.onedesign.tta.-$$Lambda$TtaScreenPresenter$ud-6JnLCv6fKO347hp5pZcxaJvg
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                TtaScreenPresenter.this.lambda$start$5$TtaScreenPresenter((CharSequence) obj);
            }
        }));
        if (ttaScreenParameters.getAppSearchEnabled()) {
            this.disposables.add(this.interactor.onPillResults().subscribe(new Consumer() { // from class: com.amazon.alexa.voice.ui.onedesign.tta.-$$Lambda$TtaScreenPresenter$GwJUqq_9DrDEMF_A25-JxjRHVuk
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    TtaScreenPresenter.this.showPills((List) obj);
                }
            }));
        }
        this.view.showKeyboard();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.tta.TtaScreenContract.Presenter
    public void stop() {
        this.view.removeUserInputWatcher();
        this.disposables.clear();
        this.interactor.stop();
        stopThinkingDelayTimer();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.tta.TtaScreenContract.Presenter
    public void suggestionSelected(String str, String str2, String str3, int i) {
        sendSuggestionSelectedMetrics(i, this.inputText.length());
        this.view.setInputTextValue("");
        this.interactor.onSuggestionSelected(str, str2, str3);
        this.view.hideSuggestions();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.tta.TtaScreenContract.Presenter
    public void voiceIngressButtonClicked() {
        this.interactor.openVoiceIngress();
        this.eventSender.sendEvent(TtaUiEvent.SWITCHED_TO_SCRIM);
    }
}
