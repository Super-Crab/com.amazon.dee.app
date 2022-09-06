package com.amazon.alexa.api;
@Deprecated
/* loaded from: classes6.dex */
public interface AlexaAudioInteraction {
    AlexaAudioChannel getAlexaAudioChannel();

    String getInteractionComponentName();

    void onBackground();

    void onForeground();

    void onPause();

    void onResume();

    void onStop();
}
