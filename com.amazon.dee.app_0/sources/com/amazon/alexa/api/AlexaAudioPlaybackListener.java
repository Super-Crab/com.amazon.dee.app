package com.amazon.alexa.api;

import com.amazon.alexa.client.annotations.NonNull;
import com.amazon.alexa.client.annotations.UiThread;
@Deprecated
/* loaded from: classes6.dex */
public interface AlexaAudioPlaybackListener {
    @UiThread
    void onAudioPlaybackChanged(@NonNull AlexaPlaybackState alexaPlaybackState);
}
