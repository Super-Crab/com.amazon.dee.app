package com.amazon.alexa.voice.ui.player;

import com.amazon.alexa.voice.ui.player.PlayerContract;
import com.amazon.alexa.voice.ui.player.TrackInformation;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.functions.Consumer;
import java.util.Locale;
/* loaded from: classes11.dex */
public final class PlayerPresenter implements PlayerContract.Presenter {
    private final CompositeDisposable disposable = new CompositeDisposable();
    private final PlayerContract.Interactor interactor;
    private final PlayerContract.View view;

    public PlayerPresenter(PlayerContract.View view, PlayerContract.Interactor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    private CharSequence formatTime(long j) {
        return String.format(Locale.US, "%d:%02d", Long.valueOf(j / 60), Long.valueOf(j % 60));
    }

    @Override // com.amazon.alexa.voice.ui.player.PlayerContract.Presenter
    public void closeClicked() {
        this.interactor.close();
    }

    @Override // com.amazon.alexa.voice.ui.player.PlayerContract.Presenter
    public void dismiss() {
        this.interactor.dismiss();
    }

    public /* synthetic */ void lambda$playPauseClicked$3$PlayerPresenter(Boolean bool) throws Throwable {
        if (bool.booleanValue()) {
            this.interactor.pause();
        } else {
            this.interactor.play();
        }
    }

    public /* synthetic */ void lambda$start$0$PlayerPresenter(PlayerCardModel playerCardModel) throws Throwable {
        this.view.setTrackInformation(new TrackInformation.Builder().title(playerCardModel.getTitle()).description(playerCardModel.getDescription()).details(playerCardModel.getDetails()).artImageUrl(playerCardModel.getArtImageUrl()).providerIconId(playerCardModel.getProviderLogoId()).providerName(playerCardModel.getProviderName()).totalTime(formatTime(this.interactor.getDuration())).maxPosition((int) this.interactor.getDuration()).seekable(playerCardModel.isProgressVisible()).audioItemId(playerCardModel.getAudioItemId()).build());
    }

    public /* synthetic */ void lambda$start$1$PlayerPresenter(Long l) throws Throwable {
        this.view.setTrackPosition(formatTime(l.longValue()), l.intValue());
    }

    public /* synthetic */ void lambda$start$2$PlayerPresenter(Boolean bool) throws Throwable {
        if (bool.booleanValue()) {
            this.view.collapse();
        }
    }

    @Override // com.amazon.alexa.voice.ui.player.PlayerContract.Presenter
    public void nextClicked() {
        this.interactor.next();
    }

    @Override // com.amazon.alexa.voice.ui.player.PlayerContract.Presenter
    public void playPauseClicked() {
        this.interactor.isPlaying().take(1L).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.amazon.alexa.voice.ui.player.-$$Lambda$PlayerPresenter$P90ILEBA24vWS2NefHFTQiSLWws
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                PlayerPresenter.this.lambda$playPauseClicked$3$PlayerPresenter((Boolean) obj);
            }
        });
    }

    @Override // com.amazon.alexa.voice.ui.player.PlayerContract.Presenter
    public void positionChanged(int i) {
        long j = i;
        this.interactor.setPosition(j);
        this.view.setTrackPosition(formatTime(j), i);
    }

    @Override // com.amazon.alexa.voice.ui.player.PlayerContract.Presenter
    public void previousClicked() {
        this.interactor.previous();
    }

    @Override // com.amazon.alexa.voice.ui.player.PlayerContract.Presenter
    public void start() {
        this.disposable.add(this.interactor.getCard().observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.amazon.alexa.voice.ui.player.-$$Lambda$PlayerPresenter$NxWJPDkY87BYTEZ2CyVG3E72lTc
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                PlayerPresenter.this.lambda$start$0$PlayerPresenter((PlayerCardModel) obj);
            }
        }));
        CompositeDisposable compositeDisposable = this.disposable;
        Observable<Boolean> observeOn = this.interactor.isPlaying().observeOn(AndroidSchedulers.mainThread());
        final PlayerContract.View view = this.view;
        view.getClass();
        compositeDisposable.add(observeOn.subscribe(new Consumer() { // from class: com.amazon.alexa.voice.ui.player.-$$Lambda$h1YnS7TGf2NTTxL5hf5Ii6Lnjtg
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                PlayerContract.View.this.setTrackPlaying(((Boolean) obj).booleanValue());
            }
        }));
        this.disposable.add(this.interactor.getPosition().observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.amazon.alexa.voice.ui.player.-$$Lambda$PlayerPresenter$hLrXiXInPAW8h7zwYfa3vbJLOOQ
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                PlayerPresenter.this.lambda$start$1$PlayerPresenter((Long) obj);
            }
        }));
        this.disposable.add(this.interactor.shouldMinimize().observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.amazon.alexa.voice.ui.player.-$$Lambda$PlayerPresenter$5IavVJNs2TM6fQr4dmu5rNX0_uo
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                PlayerPresenter.this.lambda$start$2$PlayerPresenter((Boolean) obj);
            }
        }));
        this.interactor.startListeningForCardUpdates();
    }

    @Override // com.amazon.alexa.voice.ui.player.PlayerContract.Presenter
    public void stop() {
        this.disposable.clear();
        this.interactor.stopListeningForCardUpdates();
    }
}
