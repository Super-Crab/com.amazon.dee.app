package com.amazon.alexa.voice.ui.onedesign.local.search;

import android.graphics.drawable.Drawable;
import com.amazon.alexa.voice.ui.onedesign.local.LocalCard;
import com.amazon.alexa.voice.ui.onedesign.local.LocalCardModel;
/* loaded from: classes11.dex */
public interface LocalSearchContract {

    /* loaded from: classes11.dex */
    public interface Interactor {
        void close();

        LocalCard getCard();

        void showBusiness(LocalCardModel.BusinessModel businessModel);
    }

    /* loaded from: classes11.dex */
    public interface Mediator {
        void openBusiness(LocalCardModel.BusinessModel businessModel);
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

        void setImageAttribution(Drawable drawable);

        void setSubTitle(CharSequence charSequence);

        void setTitle(CharSequence charSequence);
    }
}
