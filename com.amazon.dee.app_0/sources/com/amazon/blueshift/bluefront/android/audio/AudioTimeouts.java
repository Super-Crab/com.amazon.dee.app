package com.amazon.blueshift.bluefront.android.audio;

import com.google.common.base.Preconditions;
/* loaded from: classes11.dex */
public final class AudioTimeouts {
    private static final int DEFAULT_MAX_SPEECH_TIMEOUT = 5000;
    private static final int DEFAULT_NO_SPEECH_TIMEOUT = 5000;
    private final int mMaxSpeechTimeout;
    private final int mNoSpeechTimeout;

    public AudioTimeouts() {
        this(5000, 5000);
    }

    public int getMaxSpeechTimeout() {
        return this.mMaxSpeechTimeout;
    }

    public int getNoSpeechTimeout() {
        return this.mNoSpeechTimeout;
    }

    public AudioTimeouts(int i, int i2) {
        boolean z = true;
        Preconditions.checkArgument(i > 0, "No input timeout must be greater than 0");
        Preconditions.checkArgument(i2 <= 0 ? false : z, "Max speech timeout must be greater than 0");
        this.mNoSpeechTimeout = i;
        this.mMaxSpeechTimeout = i2;
    }
}
