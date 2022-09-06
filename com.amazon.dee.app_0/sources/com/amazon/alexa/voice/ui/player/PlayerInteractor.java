package com.amazon.alexa.voice.ui.player;

import com.amazon.alexa.voice.ui.player.PlaybackController;
import com.amazon.alexa.voice.ui.player.PlayerContract;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Cancellable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import java.util.concurrent.TimeUnit;
/* loaded from: classes11.dex */
public final class PlayerInteractor implements PlayerContract.Interactor {
    private final BehaviorSubject<PlayerCardModel> cardObservable;
    private Disposable cardUpdateSubscription;
    private final PlayerContract.Mediator mediator;
    private final BehaviorSubject<Boolean> minimize = BehaviorSubject.createDefault(Boolean.FALSE);
    private final PlaybackController playbackController;
    private final Observable<Boolean> playing;
    private final PlayerCardUpdater updater;

    public PlayerInteractor(PlayerCardModel playerCardModel, PlayerContract.Mediator mediator, final PlaybackController playbackController, PlayerCardUpdater playerCardUpdater) {
        this.playbackController = playbackController;
        this.mediator = mediator;
        this.updater = playerCardUpdater;
        this.cardObservable = BehaviorSubject.createDefault(playerCardModel);
        this.playing = Observable.create(new ObservableOnSubscribe() { // from class: com.amazon.alexa.voice.ui.player.-$$Lambda$PlayerInteractor$DoK_CcN4K1FckBPv5A3ox-OkV8g
            @Override // io.reactivex.rxjava3.core.ObservableOnSubscribe
            public final void subscribe(ObservableEmitter observableEmitter) {
                PlayerInteractor.this.lambda$new$1$PlayerInteractor(playbackController, observableEmitter);
            }
        }).distinctUntilChanged();
    }

    @Override // com.amazon.alexa.voice.ui.player.PlayerContract.Interactor
    public void close() {
        this.playbackController.stop();
        this.mediator.close();
    }

    @Override // com.amazon.alexa.voice.ui.player.PlayerContract.Collapsible
    public void collapse() {
        this.minimize.onNext(Boolean.TRUE);
    }

    @Override // com.amazon.alexa.voice.ui.player.PlayerContract.Interactor
    public void dismiss() {
        this.playbackController.stop();
        this.mediator.dismiss();
    }

    @Override // com.amazon.alexa.voice.ui.player.PlayerContract.Interactor
    public Observable<PlayerCardModel> getCard() {
        return this.cardObservable;
    }

    @Override // com.amazon.alexa.voice.ui.player.PlayerContract.Interactor
    public long getDuration() {
        return this.playbackController.getDuration();
    }

    @Override // com.amazon.alexa.voice.ui.player.PlayerContract.Interactor
    public Observable<Long> getPosition() {
        return Observable.interval(0L, 500L, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread()).map(new Function() { // from class: com.amazon.alexa.voice.ui.player.-$$Lambda$PlayerInteractor$7fcNp-68gyfQEE1hFtDX2syFRw8
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return PlayerInteractor.this.lambda$getPosition$2$PlayerInteractor((Long) obj);
            }
        }).distinctUntilChanged();
    }

    @Override // com.amazon.alexa.voice.ui.player.PlayerContract.Interactor
    public Observable<Boolean> isPlaying() {
        return this.playing;
    }

    public /* synthetic */ Long lambda$getPosition$2$PlayerInteractor(Long l) throws Throwable {
        return Long.valueOf(this.playbackController.getCurrentPosition() / 1000);
    }

    public /* synthetic */ void lambda$new$1$PlayerInteractor(final PlaybackController playbackController, final ObservableEmitter observableEmitter) throws Throwable {
        final PlaybackController.Callback callback = new PlaybackController.Callback() { // from class: com.amazon.alexa.voice.ui.player.PlayerInteractor.1
            @Override // com.amazon.alexa.voice.ui.player.PlaybackController.Callback
            public void onPlaybackPaused() {
                observableEmitter.onNext(false);
            }

            @Override // com.amazon.alexa.voice.ui.player.PlaybackController.Callback
            public void onPlaybackStarted() {
                observableEmitter.onNext(true);
            }

            @Override // com.amazon.alexa.voice.ui.player.PlaybackController.Callback
            public void onPlaybackStopped() {
                observableEmitter.onNext(false);
            }
        };
        playbackController.addCallback(callback);
        observableEmitter.setCancellable(new Cancellable() { // from class: com.amazon.alexa.voice.ui.player.-$$Lambda$PlayerInteractor$RMPTqVBcZi5U2fhnx4I4u9wPYmg
            @Override // io.reactivex.rxjava3.functions.Cancellable
            public final void cancel() {
                PlaybackController.this.removeCallback(callback);
            }
        });
        observableEmitter.onNext(Boolean.valueOf(playbackController.isPlaying()));
    }

    @Override // com.amazon.alexa.voice.ui.player.PlayerContract.Interactor
    public void next() {
        this.playbackController.next();
    }

    @Override // com.amazon.alexa.voice.ui.player.PlayerContract.Interactor
    public void pause() {
        this.playbackController.pause();
    }

    @Override // com.amazon.alexa.voice.ui.player.PlayerContract.Interactor
    public void play() {
        this.playbackController.play();
    }

    @Override // com.amazon.alexa.voice.ui.player.PlayerContract.Interactor
    public void previous() {
        this.playbackController.previous();
    }

    @Override // com.amazon.alexa.voice.ui.player.PlayerContract.Interactor
    public void setPosition(long j) {
        this.playbackController.seek(j * 1000);
    }

    @Override // com.amazon.alexa.voice.ui.player.PlayerContract.Interactor
    public Observable<Boolean> shouldMinimize() {
        return this.minimize;
    }

    @Override // com.amazon.alexa.voice.ui.player.PlayerContract.Interactor
    public void startListeningForCardUpdates() {
        Observable<PlayerCardModel> observeOn = this.updater.subscribeToCardUpdate().observeOn(AndroidSchedulers.mainThread());
        final BehaviorSubject<PlayerCardModel> behaviorSubject = this.cardObservable;
        behaviorSubject.getClass();
        this.cardUpdateSubscription = observeOn.subscribe(new Consumer() { // from class: com.amazon.alexa.voice.ui.player.-$$Lambda$8xHgNxHaMwWfQYvFuIJDnyonbBc
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                BehaviorSubject.this.onNext((PlayerCardModel) obj);
            }
        });
    }

    @Override // com.amazon.alexa.voice.ui.player.PlayerContract.Interactor
    public void stopListeningForCardUpdates() {
        Disposable disposable = this.cardUpdateSubscription;
        if (disposable == null || disposable.isDisposed()) {
            return;
        }
        this.cardUpdateSubscription.dispose();
    }
}
