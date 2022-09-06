package com.amazon.alexa.voice.ui.player;

import io.reactivex.rxjava3.core.Observable;
/* loaded from: classes11.dex */
public interface PlayerContract {

    /* loaded from: classes11.dex */
    public interface Collapsible {
        void collapse();
    }

    /* loaded from: classes11.dex */
    public interface Interactor extends Collapsible {
        void close();

        void dismiss();

        Observable<PlayerCardModel> getCard();

        long getDuration();

        Observable<Long> getPosition();

        Observable<Boolean> isPlaying();

        void next();

        void pause();

        void play();

        void previous();

        void setPosition(long j);

        Observable<Boolean> shouldMinimize();

        void startListeningForCardUpdates();

        void stopListeningForCardUpdates();
    }

    /* loaded from: classes11.dex */
    public interface Mediator {
        void close();

        void dismiss();
    }

    /* loaded from: classes11.dex */
    public interface Presenter {
        void closeClicked();

        void dismiss();

        void nextClicked();

        void playPauseClicked();

        void positionChanged(int i);

        void previousClicked();

        void start();

        void stop();
    }

    /* loaded from: classes11.dex */
    public interface View {
        void collapse();

        void expand();

        void setTrackInformation(TrackInformationModel trackInformationModel);

        void setTrackPlaying(boolean z);

        void setTrackPosition(CharSequence charSequence, int i);
    }
}
