package com.amazon.alexa.voice.pryon.asr;

import com.amazon.pryon.android.asr.PryonLite5000;
/* loaded from: classes11.dex */
public interface WakeWordDetector {
    boolean isActive();

    void release();

    void removePryonLiteCallbacks();

    void setPryonLiteCallbacks(PryonLite5000.Callbacks callbacks);

    int setSensitivityThreshold(int i);

    void start();

    void stop();
}
