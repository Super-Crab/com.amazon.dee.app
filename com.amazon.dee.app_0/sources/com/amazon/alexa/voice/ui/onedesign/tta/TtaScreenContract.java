package com.amazon.alexa.voice.ui.onedesign.tta;

import com.amazon.alexa.voice.ui.onedesign.tta.items.PillResultTtaItem;
import com.amazon.alexa.voice.ui.util.BooleanProperty;
import io.reactivex.rxjava3.core.Observable;
import java.util.List;
/* loaded from: classes11.dex */
public interface TtaScreenContract {

    /* loaded from: classes11.dex */
    public interface Interactor {
        void close();

        Observable<Object> getConversationUpdate();

        Observable<Boolean> getHideKeyboardUpdate();

        void getSuggestions(CharSequence charSequence);

        Observable<BooleanProperty> getThinkingStateUpdate();

        TtaScreenParametersModel getTtaScreenParameters();

        Observable<String> onClearConversation();

        Observable<List<Object>> onPillResults();

        void onPillSelected(PillResultTtaItem pillResultTtaItem);

        Observable<List<Object>> onSuggestionResults();

        void onSuggestionSelected(String str, String str2, String str3);

        void openVoiceIngress();

        void sendInput(CharSequence charSequence);

        void stop();
    }

    /* loaded from: classes11.dex */
    public interface Mediator {
        void close();

        void openVoiceScrim();
    }

    /* loaded from: classes11.dex */
    public interface Presenter {
        void closeButtonClicked();

        void pillSelected(PillResultTtaItem pillResultTtaItem);

        void screenVisible();

        void sendButtonClicked();

        void start();

        void stop();

        void suggestionSelected(String str, String str2, String str3, int i);

        void voiceIngressButtonClicked();
    }

    /* loaded from: classes11.dex */
    public interface View {
        void add(Object obj);

        void clear();

        void enableSendButton(boolean z);

        void hideKeyboard();

        void hidePills();

        void hideSuggestions();

        void remove(Object obj);

        void removeUserInputWatcher();

        void setInputTextValue(CharSequence charSequence);

        void setUserInputWatcher(UserInputWatcher userInputWatcher);

        void showKeyboard();

        void showPills(List<Object> list);

        void showSuggestions(List<Object> list);
    }
}
