package com.amazon.alexa.voice.tta.utils;

import androidx.annotation.NonNull;
/* loaded from: classes11.dex */
public interface Worker {

    /* loaded from: classes11.dex */
    public interface Spawner {
        @NonNull
        Worker spawn();
    }

    void post(Runnable runnable);

    void quit();
}
