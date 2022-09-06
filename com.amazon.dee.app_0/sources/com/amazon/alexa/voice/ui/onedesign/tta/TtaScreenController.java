package com.amazon.alexa.voice.ui.onedesign.tta;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.alexa.voice.ui.locale.GetLocaleAuthority;
import com.amazon.alexa.voice.ui.onedesign.rv.RvActionHandler;
import com.amazon.alexa.voice.ui.onedesign.tta.TtaScreenContract;
import com.amazon.alexa.voice.ui.onedesign.tta.TtaScreenParameters;
import com.amazon.alexa.voice.ui.onedesign.tta.items.CardTtaItem;
import com.amazon.alexa.voice.ui.onedesign.tta.items.InChatHintTtaItem;
import com.amazon.alexa.voice.ui.onedesign.tta.items.InChatLinkTtaItem;
import com.amazon.alexa.voice.ui.onedesign.tta.metrics.TtaUiEvent;
import com.amazon.alexa.voice.ui.onedesign.tta.metrics.TtaUiEventWithCount;
import com.amazon.alexa.voice.ui.onedesign.tta.metrics.TtaUiEventWithType;
import com.amazon.alexa.voice.ui.onedesign.tta.viewholder.TtaCardViewHolder;
import com.amazon.alexa.voice.ui.onedesign.tta.viewholder.TtaInChatHintViewHolder;
import com.amazon.alexa.voice.ui.onedesign.tta.viewholder.TtaInChatLinkVewHolder;
import com.amazon.alexa.voice.ui.onedesign.util.AndroidResources;
import com.amazon.alexa.voice.ui.onedesign.util.ComponentUtils;
import com.amazon.alexa.voice.ui.onedesign.util.FontUtils;
import com.amazon.alexa.voice.ui.suggestions.SuggestionsHandler;
import com.amazon.alexa.voice.ui.tta.TtaMessageHandler;
import com.amazon.alexa.voice.ui.tta.TtaNavigator;
import com.amazon.alexa.voice.ui.tta.metrics.TtaEventSender;
import com.amazon.alexa.voice.ui.tta.search.TtaResultHandler;
import com.amazon.alexa.vox.ui.onedesign.R;
import com.amazon.regulator.Component;
import com.amazon.regulator.ViewController;
import com.android.tools.r8.GeneratedOutlineSupport1;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.functions.Consumer;
import java.util.List;
import java.util.Locale;
/* loaded from: classes11.dex */
public class TtaScreenController extends ViewController implements TtaScreenContract.View {
    private static final String TAG = "TtaScreenController";
    private static final String TTA_SCREEN_PARAMETERS = "ttaScreenParameters";
    protected RecyclerView conversationView;
    protected final CompositeDisposable disposables = new CompositeDisposable();
    protected TtaEventSender eventSender;
    protected TtaScreenInteractor interactor;
    protected TtaScreenContract.Presenter presenter;
    protected SuggestionsHandler suggestionsHandler;
    protected AndroidTextWatcher textWatcher;
    protected TtaConversationAdapter ttaConversationAdapter;
    protected TtaResultHandler ttaResultHandler;
    protected UserInputView userInputView;

    public static TtaScreenController create() {
        return create(getDefaultTtaScreenParameters());
    }

    public static TtaScreenParametersModel getDefaultTtaScreenParameters() {
        return new TtaScreenParameters.Builder().hintText(null).appSearchEnabled(false).build();
    }

    private InputMethodManager getInputMethodManager() {
        return (InputMethodManager) getContext().getSystemService("input_method");
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.tta.TtaScreenContract.View
    public void add(final Object obj) {
        this.conversationView.post(new Runnable() { // from class: com.amazon.alexa.voice.ui.onedesign.tta.-$$Lambda$TtaScreenController$MtfTOvfs6byQGvqW3v7r2qXC8Po
            @Override // java.lang.Runnable
            public final void run() {
                TtaScreenController.this.lambda$add$7$TtaScreenController(obj);
            }
        });
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.tta.TtaScreenContract.View
    public void clear() {
        this.ttaConversationAdapter.clear();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.tta.TtaScreenContract.View
    public void enableSendButton(boolean z) {
        GeneratedOutlineSupport1.outline172("enableSendButton: ", z);
        this.userInputView.setSendEnabled(z);
    }

    protected int getLayoutId() {
        return R.layout.text_ui_od_tta_screen;
    }

    protected int getThemeId() {
        return R.style.Theme_Alexa_Text_V1;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.tta.TtaScreenContract.View
    public void hideKeyboard() {
        this.userInputView.postDelayed(new Runnable() { // from class: com.amazon.alexa.voice.ui.onedesign.tta.-$$Lambda$TtaScreenController$LxBb90bqhSy7jOP0yEpk7vLpmIw
            @Override // java.lang.Runnable
            public final void run() {
                TtaScreenController.this.lambda$hideKeyboard$10$TtaScreenController();
            }
        }, 100L);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.tta.TtaScreenContract.View
    public void hidePills() {
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.tta.TtaScreenContract.View
    public void hideSuggestions() {
    }

    public /* synthetic */ void lambda$add$7$TtaScreenController(Object obj) {
        this.ttaConversationAdapter.add(obj);
        this.conversationView.getLayoutManager().scrollToPosition(this.ttaConversationAdapter.getItemCount() - 1);
    }

    public /* synthetic */ void lambda$hideKeyboard$10$TtaScreenController() {
        getInputMethodManager().hideSoftInputFromWindow(this.userInputView.getTextInputView().getWindowToken(), 0);
    }

    public /* synthetic */ void lambda$onCreate$0$TtaScreenController(TtaNavigator ttaNavigator, int i, int i2, Object obj, View view, Bundle bundle) {
        CardTtaItem cardTtaItem = (CardTtaItem) obj;
        this.eventSender.sendEvent(new TtaUiEventWithType(TtaUiEvent.VOX_TTA_IN_CHAT_LINK_TAPPED, cardTtaItem.getCardType()));
        ttaNavigator.navigateToExternalUrl(getContext(), cardTtaItem.getLinkUrl());
    }

    public /* synthetic */ void lambda$onCreate$1$TtaScreenController(int i, int i2, Object obj, View view, Bundle bundle) {
        this.eventSender.sendEvent(TtaUiEvent.HINT_RESULT_TAPPED);
        this.eventSender.sendEvent(TtaUiEvent.VOX_SIMBA_ITEM_SELECTED);
        this.eventSender.sendEvent(new TtaUiEventWithCount(TtaUiEvent.VOX_SIMBA_RESULT_SELECTED_INDEX, i2));
        this.ttaResultHandler.onResultSelected(SearchResultItem.create((InChatHintTtaItem) obj));
    }

    public /* synthetic */ void lambda$onCreate$2$TtaScreenController(int i, int i2, Object obj, View view, Bundle bundle) {
        this.eventSender.sendEvent(TtaUiEvent.LINK_RESULT_TAPPED);
        this.eventSender.sendEvent(TtaUiEvent.VOX_SIMBA_ITEM_SELECTED);
        this.ttaResultHandler.onResultSelected(SearchResultItem.create((InChatLinkTtaItem) obj));
    }

    public /* synthetic */ void lambda$onCreateView$3$TtaScreenController(View view) {
        this.presenter.closeButtonClicked();
    }

    public /* synthetic */ void lambda$onCreateView$4$TtaScreenController(Object obj) throws Throwable {
        this.presenter.sendButtonClicked();
    }

    public /* synthetic */ void lambda$onCreateView$5$TtaScreenController(Object obj) throws Throwable {
        this.presenter.voiceIngressButtonClicked();
    }

    public /* synthetic */ void lambda$onCreateView$6$TtaScreenController(View view) {
        if (view.isShown()) {
            this.presenter.screenVisible();
            scrollConversationToEnd();
        }
    }

    public /* synthetic */ void lambda$remove$8$TtaScreenController(Object obj) {
        this.ttaConversationAdapter.remove(obj);
        this.conversationView.getLayoutManager().scrollToPosition(this.ttaConversationAdapter.getItemCount() - 1);
    }

    public /* synthetic */ void lambda$scrollConversationToEnd$11$TtaScreenController() {
        int itemCount = this.ttaConversationAdapter.getItemCount() - 1;
        if (itemCount >= 0) {
            this.conversationView.smoothScrollToPosition(itemCount);
        }
    }

    public /* synthetic */ void lambda$showKeyboard$9$TtaScreenController() {
        this.userInputView.getTextInputView().requestFocus();
        getInputMethodManager().showSoftInput(this.userInputView.getTextInputView(), 1);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.regulator.ViewController
    public void onAttach(View view) {
        this.presenter.start();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.regulator.ViewController
    public void onCreate() {
        Locale locale;
        Bundle arguments = getArguments();
        TtaScreenParametersModel ttaScreenParametersModel = arguments != null ? (TtaScreenParametersModel) arguments.getParcelable(TTA_SCREEN_PARAMETERS) : null;
        if (ttaScreenParametersModel == null) {
            ttaScreenParametersModel = getDefaultTtaScreenParameters();
        }
        TtaScreenParametersModel ttaScreenParametersModel2 = ttaScreenParametersModel;
        overrideTheme(getThemeId());
        TtaScreenMediator ttaScreenMediator = new TtaScreenMediator(this);
        Component component = getComponent();
        final TtaNavigator ttaNavigator = (TtaNavigator) component.get(TtaNavigator.class);
        this.eventSender = (TtaEventSender) component.get(TtaEventSender.class);
        this.ttaResultHandler = (TtaResultHandler) component.get(TtaResultHandler.class);
        this.suggestionsHandler = (SuggestionsHandler) component.get(SuggestionsHandler.class);
        this.interactor = new TtaScreenInteractor(ttaScreenMediator, ttaScreenParametersModel2, ttaNavigator, getContext(), this.eventSender, new TtaHandlerProvider((TtaMessageHandler) component.get(TtaMessageHandler.class), this.ttaResultHandler, this.suggestionsHandler));
        if (component.isRegistered(GetLocaleAuthority.class)) {
            locale = ComponentUtils.getLocale(component);
        } else {
            locale = Locale.US;
        }
        this.presenter = new TtaScreenPresenter(this, this.interactor, new AndroidResources(getContext(), locale), this.eventSender);
        this.ttaConversationAdapter = new TtaConversationAdapter(this.eventSender);
        this.ttaConversationAdapter.addActionHandler(TtaCardViewHolder.class, new RvActionHandler() { // from class: com.amazon.alexa.voice.ui.onedesign.tta.-$$Lambda$TtaScreenController$jkkPQF6s15S36Y38vHGX_WI2cuU
            @Override // com.amazon.alexa.voice.ui.onedesign.rv.RvActionHandler
            public final void handle(int i, int i2, Object obj, View view, Bundle bundle) {
                TtaScreenController.this.lambda$onCreate$0$TtaScreenController(ttaNavigator, i, i2, obj, view, bundle);
            }
        });
        this.ttaConversationAdapter.addActionHandler(TtaInChatHintViewHolder.class, new RvActionHandler() { // from class: com.amazon.alexa.voice.ui.onedesign.tta.-$$Lambda$TtaScreenController$Yev4wuctthPmYNOBJcfIgn4anRI
            @Override // com.amazon.alexa.voice.ui.onedesign.rv.RvActionHandler
            public final void handle(int i, int i2, Object obj, View view, Bundle bundle) {
                TtaScreenController.this.lambda$onCreate$1$TtaScreenController(i, i2, obj, view, bundle);
            }
        });
        this.ttaConversationAdapter.addActionHandler(TtaInChatLinkVewHolder.class, new RvActionHandler() { // from class: com.amazon.alexa.voice.ui.onedesign.tta.-$$Lambda$TtaScreenController$uc7FBNyOqR0h8eVjQeUekDLEuw4
            @Override // com.amazon.alexa.voice.ui.onedesign.rv.RvActionHandler
            public final void handle(int i, int i2, Object obj, View view, Bundle bundle) {
                TtaScreenController.this.lambda$onCreate$2$TtaScreenController(i, i2, obj, view, bundle);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.regulator.ViewController
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @NonNull ViewGroup viewGroup) {
        final View inflate = layoutInflater.inflate(getLayoutId(), viewGroup, false);
        inflate.findViewById(R.id.close).setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.voice.ui.onedesign.tta.-$$Lambda$TtaScreenController$WjWRIHUoIXcLY0wHFxx51ZphQj8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TtaScreenController.this.lambda$onCreateView$3$TtaScreenController(view);
            }
        });
        this.userInputView = (UserInputView) inflate.findViewById(R.id.user_input_container);
        this.disposables.add(this.userInputView.onSend().subscribe(new Consumer() { // from class: com.amazon.alexa.voice.ui.onedesign.tta.-$$Lambda$TtaScreenController$Pz7MiBD04h69r0d_TV96OfigpDM
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                TtaScreenController.this.lambda$onCreateView$4$TtaScreenController(obj);
            }
        }));
        this.disposables.add(this.userInputView.onVoiceIngress().subscribe(new Consumer() { // from class: com.amazon.alexa.voice.ui.onedesign.tta.-$$Lambda$TtaScreenController$4APtmIFUGBtKxebYyN1yf3iTHEU
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                TtaScreenController.this.lambda$onCreateView$5$TtaScreenController(obj);
            }
        }));
        this.conversationView = (RecyclerView) inflate.findViewById(R.id.alexa_conversation_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(1);
        this.conversationView.setLayoutManager(linearLayoutManager);
        this.conversationView.setAdapter(this.ttaConversationAdapter);
        inflate.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.amazon.alexa.voice.ui.onedesign.tta.-$$Lambda$TtaScreenController$FtBpo5XLC2OY5pZ--emlEs20YSc
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public final void onGlobalLayout() {
                TtaScreenController.this.lambda$onCreateView$6$TtaScreenController(inflate);
            }
        });
        FontUtils.apply(FontUtils.getFontFromTheme(inflate.getContext(), R.attr.textUiBodyFont), this.userInputView.getTextInputView());
        return inflate;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.regulator.ViewController
    public void onDetach(@NonNull View view) {
        this.disposables.dispose();
        this.presenter.stop();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.tta.TtaScreenContract.View
    public void remove(final Object obj) {
        this.conversationView.post(new Runnable() { // from class: com.amazon.alexa.voice.ui.onedesign.tta.-$$Lambda$TtaScreenController$8PosIjGKuLJXWDsfWfsywvk6hJY
            @Override // java.lang.Runnable
            public final void run() {
                TtaScreenController.this.lambda$remove$8$TtaScreenController(obj);
            }
        });
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.tta.TtaScreenContract.View
    public void removeUserInputWatcher() {
        if (this.textWatcher != null) {
            this.userInputView.getTextInputView().removeTextChangedListener(this.textWatcher);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void scrollConversationToEnd() {
        this.conversationView.post(new Runnable() { // from class: com.amazon.alexa.voice.ui.onedesign.tta.-$$Lambda$TtaScreenController$R7aKgZPCMp06GLU0P_VM9sscgpA
            @Override // java.lang.Runnable
            public final void run() {
                TtaScreenController.this.lambda$scrollConversationToEnd$11$TtaScreenController();
            }
        });
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.tta.TtaScreenContract.View
    public void setInputTextValue(CharSequence charSequence) {
        String str = "setInputTextValue: " + ((Object) charSequence);
        this.userInputView.getTextInputView().setText(charSequence);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.tta.TtaScreenContract.View
    public void setUserInputWatcher(UserInputWatcher userInputWatcher) {
        removeUserInputWatcher();
        this.textWatcher = new AndroidTextWatcher(userInputWatcher);
        this.userInputView.getTextInputView().addTextChangedListener(this.textWatcher);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.tta.TtaScreenContract.View
    public void showKeyboard() {
        this.userInputView.postDelayed(new Runnable() { // from class: com.amazon.alexa.voice.ui.onedesign.tta.-$$Lambda$TtaScreenController$NxWr4fmJE_A3QeUTfnIyR6GgfRE
            @Override // java.lang.Runnable
            public final void run() {
                TtaScreenController.this.lambda$showKeyboard$9$TtaScreenController();
            }
        }, 100L);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.tta.TtaScreenContract.View
    public void showPills(List<Object> list) {
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.tta.TtaScreenContract.View
    public void showSuggestions(List<Object> list) {
    }

    public static TtaScreenController create(@NonNull TtaScreenParametersModel ttaScreenParametersModel) {
        TtaScreenController ttaScreenController;
        Bundle bundle = new Bundle();
        bundle.putParcelable(TTA_SCREEN_PARAMETERS, ttaScreenParametersModel);
        if (ttaScreenParametersModel.getAppSearchEnabled()) {
            ttaScreenController = new TtaScreenV2Controller();
        } else {
            ttaScreenController = new TtaScreenController();
        }
        ttaScreenController.setArguments(bundle);
        return ttaScreenController;
    }
}
