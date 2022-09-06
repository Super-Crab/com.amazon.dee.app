package com.amazon.alexa.api;
/* loaded from: classes6.dex */
class ApiType_MediaPlaybackListenerWrapper extends MediaPlaybackListener implements AlexaMediaPlaybackListener {
    private final AlexaMediaPlaybackListener alexaMediaPlaybackListener;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ApiType_MediaPlaybackListenerWrapper(AlexaMediaPlaybackListener alexaMediaPlaybackListener) {
        this.alexaMediaPlaybackListener = alexaMediaPlaybackListener;
    }

    @Override // com.amazon.alexa.api.MediaPlaybackListener, com.amazon.alexa.api.AlexaMediaPlaybackListener
    public void onMediaMetadata(AlexaMediaPlaybackMetadata alexaMediaPlaybackMetadata) {
        this.alexaMediaPlaybackListener.onMediaMetadata(alexaMediaPlaybackMetadata);
    }

    @Override // com.amazon.alexa.api.MediaPlaybackListener, com.amazon.alexa.api.AlexaMediaPlaybackListener
    public void onMediaPlaybackStateUpdate(AlexaMediaPlaybackState alexaMediaPlaybackState) {
        this.alexaMediaPlaybackListener.onMediaPlaybackStateUpdate(alexaMediaPlaybackState);
    }
}
