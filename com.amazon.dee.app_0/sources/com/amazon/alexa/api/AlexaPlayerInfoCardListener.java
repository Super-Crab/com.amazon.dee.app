package com.amazon.alexa.api;

import com.amazon.alexa.client.annotations.NonNull;
import com.amazon.alexa.client.annotations.UiThread;
/* loaded from: classes6.dex */
public interface AlexaPlayerInfoCardListener {
    @UiThread
    void onAudioItemStateChanged(@NonNull AlexaPlayerInfoState alexaPlayerInfoState, @NonNull String str, long j);

    @UiThread
    void onReceivedPlayerInfoCard(@NonNull String str, boolean z);
}
