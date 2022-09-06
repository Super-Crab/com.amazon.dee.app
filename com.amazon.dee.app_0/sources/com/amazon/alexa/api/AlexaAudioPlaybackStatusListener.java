package com.amazon.alexa.api;

import java.util.Map;
/* loaded from: classes6.dex */
public interface AlexaAudioPlaybackStatusListener {
    void onAudioPlaybackStatusChanged(Map<AlexaAudioChannel, Boolean> map);
}
