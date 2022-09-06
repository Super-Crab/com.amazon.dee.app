package com.amazon.alexa.voice.tta.utils;

import android.os.Handler;
import android.os.HandlerThread;
import androidx.annotation.NonNull;
/* loaded from: classes11.dex */
public final class AsyncWorker implements Worker {
    Handler handler;
    @NonNull
    final HandlerThread handlerThread = new HandlerThread(AsyncWorker.class.getSimpleName(), 10);

    private AsyncWorker() {
    }

    @NonNull
    public static AsyncWorker spawn() {
        AsyncWorker asyncWorker = new AsyncWorker();
        asyncWorker.handlerThread.start();
        asyncWorker.handler = new Handler(asyncWorker.handlerThread.getLooper());
        return asyncWorker;
    }

    @Override // com.amazon.alexa.voice.tta.utils.Worker
    public void post(Runnable runnable) {
        this.handler.post(runnable);
    }

    @Override // com.amazon.alexa.voice.tta.utils.Worker
    public void quit() {
        this.handlerThread.quit();
    }
}
