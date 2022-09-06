package com.amazon.blueshift.bluefront.android.vad;

import java.io.Closeable;
/* loaded from: classes11.dex */
public interface VoiceActivityDetector extends Closeable {

    /* loaded from: classes11.dex */
    public enum VADState {
        NOT_STARTPOINTED,
        STARTPOINTED,
        ENDPOINTED
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    void close();

    VADState processSamples(short[] sArr, int i) throws VADException;
}
