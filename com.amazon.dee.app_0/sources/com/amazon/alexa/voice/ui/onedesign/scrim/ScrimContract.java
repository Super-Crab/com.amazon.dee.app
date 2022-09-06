package com.amazon.alexa.voice.ui.onedesign.scrim;

import androidx.annotation.FloatRange;
import com.amazon.alexa.voice.ui.util.AlexaState;
import io.reactivex.rxjava3.core.Observable;
/* loaded from: classes11.dex */
public interface ScrimContract {

    /* loaded from: classes11.dex */
    public interface Interactor {
        Observable<AlexaState> alexaState();

        void close();

        ScrimParametersModel getScrimParameters();

        Observable<Float> getSoundLevel();

        void openTTA();

        void stop();
    }

    /* loaded from: classes11.dex */
    public interface Mediator {
        void close();

        void openTTA();
    }

    /* loaded from: classes11.dex */
    public interface Presenter {
        void cancelClicked();

        void start();

        void stop();

        void ttaIngressClicked();
    }

    /* loaded from: classes11.dex */
    public interface View {
        void setChromeLevel(@FloatRange(from = 0.0d, to = 1.0d) float f);

        void setChromeLocked(boolean z);

        void setHint(CharSequence charSequence, CharSequence charSequence2);

        void setHintVisible(boolean z);

        void showCancelButton();
    }
}
