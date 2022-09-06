package com.amazon.alexa.devices.speechrecognizer;

import com.amazon.alexa.devices.AlexaException;
/* loaded from: classes6.dex */
public interface SpeechRecognizerCallback<N> {
    void onFailure(AlexaException alexaException);

    void onSuccess(N n);
}
