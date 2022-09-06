package com.amazon.alexa.utils.audio;
/* loaded from: classes10.dex */
public class NoOpAcousticEchoCancelerWrapper implements AcousticEchoCancelerWrapper {
    private static final String TAG = "NoOpAcousticEchoCancelerWrapper";

    @Override // com.amazon.alexa.utils.audio.AcousticEchoCancelerWrapper
    public boolean getEnabled() {
        return false;
    }

    @Override // com.amazon.alexa.utils.audio.AcousticEchoCancelerWrapper
    public boolean isAvailable() {
        return false;
    }

    @Override // com.amazon.alexa.utils.audio.AcousticEchoCancelerWrapper
    public void release() {
    }

    @Override // com.amazon.alexa.utils.audio.AcousticEchoCancelerWrapper
    public void setEnabled(boolean z) {
    }
}
