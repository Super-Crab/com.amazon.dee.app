package com.amazon.alexa.api;

import com.amazon.alexa.client.annotations.NonNull;
/* loaded from: classes6.dex */
abstract class MediaPlaybackListener {
    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void onMediaMetadata(@NonNull AlexaMediaPlaybackMetadata alexaMediaPlaybackMetadata);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void onMediaPlaybackStateUpdate(@NonNull AlexaMediaPlaybackState alexaMediaPlaybackState);
}
