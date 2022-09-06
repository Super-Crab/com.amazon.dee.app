package com.here.sdk.core.threading;

import android.os.Handler;
import android.os.Looper;
import androidx.annotation.NonNull;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class MainThreadTaskRunner implements TaskHandle {
    private Handler mHandler;
    private final java.lang.Runnable mRunnable;
    private final int PENDING = 0;
    private final int RUNNING = 1;
    private final int DONE = 2;
    private final int CANCELLED = 3;
    private AtomicInteger mState = new AtomicInteger(0);

    private MainThreadTaskRunner(@NonNull final Runnable runnable) {
        this.mRunnable = new java.lang.Runnable() { // from class: com.here.sdk.core.threading.-$$Lambda$MainThreadTaskRunner$93nD-I9pqERpDgqXkXMj7C0moag
            @Override // java.lang.Runnable
            public final void run() {
                MainThreadTaskRunner.this.a(runnable);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(Runnable runnable) {
        if (this.mState.compareAndSet(0, 1)) {
            runnable.run();
            this.mState.compareAndSet(1, 2);
        }
    }

    public static MainThreadTaskRunner post(@NonNull Runnable runnable, long j) {
        MainThreadTaskRunner mainThreadTaskRunner = new MainThreadTaskRunner(runnable);
        mainThreadTaskRunner.post(j);
        return mainThreadTaskRunner;
    }

    private void post(long j) {
        Handler handler = new Handler(Looper.getMainLooper());
        this.mHandler = handler;
        if (j != 0) {
            handler.postDelayed(this.mRunnable, j);
        } else {
            handler.post(this.mRunnable);
        }
    }

    public static MainThreadTaskRunner run(@NonNull Runnable runnable) {
        if (Objects.equals(Looper.myLooper(), Looper.getMainLooper())) {
            MainThreadTaskRunner mainThreadTaskRunner = new MainThreadTaskRunner(runnable);
            mainThreadTaskRunner.runTask();
            return mainThreadTaskRunner;
        }
        return post(runnable, 0L);
    }

    private void runTask() {
        this.mRunnable.run();
    }

    @Override // com.here.sdk.core.threading.TaskHandle
    public boolean cancel() {
        Handler handler;
        if (this.mState.get() == 3) {
            return false;
        }
        if (this.mState.compareAndSet(0, 3) && (handler = this.mHandler) != null) {
            handler.removeCallbacks(this.mRunnable);
        }
        this.mState.compareAndSet(1, 3);
        return isCancelled();
    }

    @Override // com.here.sdk.core.threading.TaskHandle
    public boolean isCancelled() {
        return this.mState.get() == 3;
    }

    @Override // com.here.sdk.core.threading.TaskHandle
    public boolean isFinished() {
        return this.mState.get() == 2 || this.mState.get() == 3;
    }
}
