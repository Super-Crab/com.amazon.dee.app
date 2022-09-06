package com.amazon.alexa.api;

import android.app.PendingIntent;
import com.amazon.alexa.client.annotations.NonNull;
/* loaded from: classes6.dex */
public interface MediaPlaybackControllerApi {
    void deregisterAlexaAudioPlaybackStatusListener(@NonNull AlexaAudioPlaybackStatusListener alexaAudioPlaybackStatusListener);

    void deregisterMediaPlaybackListener(@NonNull AlexaMediaPlaybackListener alexaMediaPlaybackListener);

    void next();

    void pause();

    void play();

    void previous();

    void registerAlexaAudioPlaybackStatusListener(@NonNull AlexaAudioPlaybackStatusListener alexaAudioPlaybackStatusListener);

    void registerMediaPlaybackListener(@NonNull AlexaMediaPlaybackListener alexaMediaPlaybackListener);

    void setMediaNotificationContentIntent(@NonNull PendingIntent pendingIntent);

    void stop();
}
