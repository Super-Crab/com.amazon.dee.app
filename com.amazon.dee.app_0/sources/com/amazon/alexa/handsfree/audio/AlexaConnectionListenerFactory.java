package com.amazon.alexa.handsfree.audio;

import androidx.annotation.NonNull;
import com.amazon.alexa.handsfree.audio.api.AudioReader;
/* loaded from: classes8.dex */
public class AlexaConnectionListenerFactory {
    @NonNull
    public AlexaConnectionListener getAlexaConnectionListener(@NonNull AudioReader audioReader) {
        return new AlexaConnectionListener(audioReader);
    }
}
