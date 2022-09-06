package com.amazon.alexa.wakeword.pryon;

import androidx.annotation.Nullable;
import com.amazon.alexa.api.AlexaWakeWord;
/* loaded from: classes11.dex */
public interface WakeWordDetectionCallbacks {
    void onClassificationEvent(byte[] bArr, int i, byte[] bArr2);

    void onEnrollmentExampleEvent(AlexaWakeWord alexaWakeWord, short[] sArr, byte[] bArr);

    void onWakeWordDetected(AlexaWakeWord alexaWakeWord, @Nullable byte[] bArr);
}
