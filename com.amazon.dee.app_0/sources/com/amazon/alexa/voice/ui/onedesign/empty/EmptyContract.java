package com.amazon.alexa.voice.ui.onedesign.empty;

import androidx.annotation.NonNull;
import com.amazon.alexa.voice.ui.onedesign.list.ListType;
/* loaded from: classes11.dex */
public interface EmptyContract {

    /* loaded from: classes11.dex */
    public interface Interactor {
        void close();

        void dismiss();

        EmptyCard getCard();

        void navigateToManageDestination();
    }

    /* loaded from: classes11.dex */
    public interface Mediator {
        void close();

        void dismiss();

        void navigateTo(String str);

        void navigateToList(@NonNull ListType listType);
    }

    /* loaded from: classes11.dex */
    public interface Presenter {
        void closeClicked();

        void dismiss();

        void interacted();

        void manageButtonClicked();

        void start();

        void viewDestroyed();
    }

    /* loaded from: classes11.dex */
    public interface View {
        void floodBackgroundToStatusBar();

        void setContent(CharSequence charSequence);

        void setIconResourceId(int i);

        void setTitle(CharSequence charSequence);

        void setUserHint(CharSequence charSequence);

        void showManageButton();
    }
}
