package com.amazon.alexa.voice.ui.onedesign.list;

import androidx.annotation.NonNull;
/* loaded from: classes11.dex */
public interface ListContract {

    /* loaded from: classes11.dex */
    public interface Interactor {
        void close();

        void dismiss();

        ListCard getCard();

        void openLists();
    }

    /* loaded from: classes11.dex */
    public interface Mediator {
        void close();

        void dismiss();

        void openSpecialList(@NonNull ListType listType);
    }

    /* loaded from: classes11.dex */
    public interface Presenter {
        void closeClicked();

        void dismiss();

        void end();

        void interacted();

        void manageButtonClicked();

        void start();

        void viewDestroyed();
    }

    /* loaded from: classes11.dex */
    public interface View {
        void add(ListItemModel listItemModel);

        void floodBackgroundToStatusBar();

        void setSubTitle(CharSequence charSequence);

        void setTitle(CharSequence charSequence);
    }
}
