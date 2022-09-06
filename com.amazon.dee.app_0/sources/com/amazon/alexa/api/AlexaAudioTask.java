package com.amazon.alexa.api;
/* loaded from: classes6.dex */
public interface AlexaAudioTask {
    AlexaAudioChannel getAlexaAudioChannel();

    AlexaAudioTaskChannelPriority getAlexaAudioTaskChannelPriority();

    String getAudioTaskComponentName();

    void onBackground();

    void onForeground();

    void onPause();

    void onResume();

    void onStop();
}
