package com.amazon.alexa.audiopersonalization.components;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
/* loaded from: classes6.dex */
public class TonePlayer {
    private static final String TAG = "TonePlayer";
    private Future<?> mCurrentToneFuture;
    private final ExecutorService mToneExecutor;

    public TonePlayer(ExecutorService executorService) {
        this.mToneExecutor = executorService;
    }

    public synchronized void play(Tone tone) {
        String str = "Submitting tone task for tone with id: " + tone.requestId;
        if (this.mCurrentToneFuture != null) {
            this.mCurrentToneFuture.cancel(true);
        }
        this.mCurrentToneFuture = this.mToneExecutor.submit(new ToneTask(tone));
    }

    public void shutdown() {
        this.mToneExecutor.shutdownNow();
    }

    public synchronized void stop() {
        if (this.mCurrentToneFuture != null) {
            this.mCurrentToneFuture.cancel(true);
            this.mCurrentToneFuture = null;
        }
    }
}
