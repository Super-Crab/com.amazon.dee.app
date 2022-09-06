package com.amazon.alexa.accessory.repositories.media;

import com.amazon.alexa.accessory.internal.repositories.BaseProducer;
import com.amazon.alexa.accessory.internal.repositories.CompletableResult;
import com.amazon.alexa.accessory.internal.repositories.ResultCallable;
import com.amazon.alexa.accessory.internal.util.ObservableUtils;
import com.amazon.alexa.accessory.protocol.Media;
import com.amazon.alexa.accessory.repositories.Producer;
import com.amazon.alexa.accessory.repositories.media.MediaProducer;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import io.reactivex.rxjava3.subjects.PublishSubject;
import io.reactivex.rxjava3.subjects.Subject;
/* loaded from: classes6.dex */
public class MemoryMediaRepository extends BaseProducer<MediaProducer.ActionHandler> implements MediaProvider, MediaRepository, MediaProducer {
    private final Subject<Media.MediaControl> mediaControlSubject = PublishSubject.create().toSerialized();
    private final Subject<Media.RegisterForMediaEvents> mediaEventRegistrationSubject = BehaviorSubject.create().toSerialized();
    private final Subject<Media.PlaybackStatus> playbackStatusSubject = BehaviorSubject.create().toSerialized();

    public MemoryMediaRepository() {
        this.playbackStatusSubject.onNext(Media.PlaybackStatus.newBuilder().setStatus(Media.PlayStatus.MEDIA_PLAY_STATUS_UNKNOWN).mo10084build());
    }

    @Override // com.amazon.alexa.accessory.repositories.media.MediaRepository
    public Completable issueMediaControl(final Media.MediaControl mediaControl) {
        return CompletableResult.create(new ResultCallable() { // from class: com.amazon.alexa.accessory.repositories.media.-$$Lambda$MemoryMediaRepository$ENm-6gk0bLwAs3MMTBPZjH5H2UM
            @Override // com.amazon.alexa.accessory.internal.repositories.ResultCallable
            public final void call(Producer.Result result) {
                MemoryMediaRepository.this.lambda$issueMediaControl$0$MemoryMediaRepository(mediaControl, result);
            }
        });
    }

    public /* synthetic */ void lambda$issueMediaControl$0$MemoryMediaRepository(Media.MediaControl mediaControl, Producer.Result result) {
        getHandler().handleIssueMediaControl(mediaControl, result);
    }

    public /* synthetic */ void lambda$setPlaybackStatus$1$MemoryMediaRepository(Media.PlaybackStatus playbackStatus, Producer.Result result) {
        this.playbackStatusSubject.onNext(playbackStatus);
    }

    @Override // com.amazon.alexa.accessory.repositories.media.MediaProvider
    public void provideMediaCommand(Media.MediaControl mediaControl) {
        this.mediaControlSubject.onNext(mediaControl);
    }

    @Override // com.amazon.alexa.accessory.repositories.media.MediaProvider
    public void provideRegisterForMediaEvents(Media.RegisterForMediaEvents registerForMediaEvents) {
        this.mediaEventRegistrationSubject.onNext(registerForMediaEvents);
    }

    @Override // com.amazon.alexa.accessory.repositories.media.MediaRepository
    public Observable<Media.MediaControl> queryMediaControls() {
        return this.mediaControlSubject;
    }

    @Override // com.amazon.alexa.accessory.repositories.media.MediaProvider
    public Observable<Media.PlaybackStatus> queryPlaybackStatus() {
        return this.playbackStatusSubject;
    }

    @Override // com.amazon.alexa.accessory.repositories.media.MediaRepository
    public Observable<Media.RegisterForMediaEvents> queryRegisterForMediaEvents() {
        return this.mediaEventRegistrationSubject;
    }

    public void release() {
        ObservableUtils.release(this.mediaControlSubject);
        ObservableUtils.release(this.mediaEventRegistrationSubject);
        ObservableUtils.release(this.playbackStatusSubject);
    }

    @Override // com.amazon.alexa.accessory.repositories.media.MediaRepository
    public Completable setPlaybackStatus(final Media.PlaybackStatus playbackStatus) {
        return CompletableResult.create(new ResultCallable() { // from class: com.amazon.alexa.accessory.repositories.media.-$$Lambda$MemoryMediaRepository$I9ymbEkLEC2pe8H78biNg89S_GY
            @Override // com.amazon.alexa.accessory.internal.repositories.ResultCallable
            public final void call(Producer.Result result) {
                MemoryMediaRepository.this.lambda$setPlaybackStatus$1$MemoryMediaRepository(playbackStatus, result);
            }
        });
    }
}
