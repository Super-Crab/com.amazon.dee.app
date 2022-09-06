package com.amazon.alexa.voice.ui.driveMode.local.search;

import com.amazon.alexa.voice.ui.onedesign.local.LocalCard;
import com.amazon.alexa.voice.ui.onedesign.local.LocalCardModel;
import com.amazon.alexa.voice.ui.onedesign.local.search.LocalSearchItemModel;
/* loaded from: classes11.dex */
public interface DriveModeLocalSearchContract {

    /* loaded from: classes11.dex */
    public interface Interactor {
        void close();

        LocalCard getCard();

        void showLocation(LocalCardModel.BusinessModel businessModel);
    }

    /* loaded from: classes11.dex */
    public interface Presenter {
        void closeClicked();

        void end();

        void interacted();

        void itemClicked(LocalSearchItemModel localSearchItemModel);

        void start();

        void viewDestroyed();
    }

    /* loaded from: classes11.dex */
    public interface View {
        void add(Object obj);

        void floodBackgroundToStatusBar();

        void setSubTitle(CharSequence charSequence);

        void setTitle(CharSequence charSequence);
    }
}
