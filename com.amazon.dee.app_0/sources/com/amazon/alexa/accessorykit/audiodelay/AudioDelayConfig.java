package com.amazon.alexa.accessorykit.audiodelay;

import com.google.gson.annotations.Expose;
/* loaded from: classes6.dex */
public final class AudioDelayConfig {
    @Expose
    private final int delay;

    public AudioDelayConfig(int i) {
        this.delay = i;
    }

    public int getDelay() {
        return this.delay;
    }
}
