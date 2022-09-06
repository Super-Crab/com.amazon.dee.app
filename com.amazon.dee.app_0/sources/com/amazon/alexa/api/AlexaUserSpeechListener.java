package com.amazon.alexa.api;

import com.amazon.alexa.client.annotations.UiThread;
/* loaded from: classes6.dex */
public interface AlexaUserSpeechListener {
    @UiThread
    void onAlexaUserSpeechVolumeChanged(float f);
}
