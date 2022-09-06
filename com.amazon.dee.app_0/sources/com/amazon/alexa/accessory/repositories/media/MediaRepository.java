package com.amazon.alexa.accessory.repositories.media;

import com.amazon.alexa.accessory.protocol.Media;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
/* loaded from: classes6.dex */
public interface MediaRepository {
    Completable issueMediaControl(Media.MediaControl mediaControl);

    Observable<Media.MediaControl> queryMediaControls();

    Observable<Media.RegisterForMediaEvents> queryRegisterForMediaEvents();

    Completable setPlaybackStatus(Media.PlaybackStatus playbackStatus);
}
