package com.amazon.alexa.accessory.repositories.media;

import com.amazon.alexa.accessory.protocol.Media;
import io.reactivex.rxjava3.core.Observable;
/* loaded from: classes6.dex */
public interface MediaProvider {
    void provideMediaCommand(Media.MediaControl mediaControl);

    void provideRegisterForMediaEvents(Media.RegisterForMediaEvents registerForMediaEvents);

    Observable<Media.PlaybackStatus> queryPlaybackStatus();
}
