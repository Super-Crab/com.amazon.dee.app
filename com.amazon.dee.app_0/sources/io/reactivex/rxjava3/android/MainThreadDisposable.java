package io.reactivex.rxjava3.android;

import android.os.Looper;
import com.android.tools.r8.GeneratedOutlineSupport1;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import java.util.concurrent.atomic.AtomicBoolean;
/* loaded from: classes3.dex */
public abstract class MainThreadDisposable implements Disposable {
    private final AtomicBoolean unsubscribed = new AtomicBoolean();

    public static void verifyMainThread() {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            return;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Expected to be called on the main thread but was ");
        outline107.append(Thread.currentThread().getName());
        throw new IllegalStateException(outline107.toString());
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public final void dispose() {
        if (this.unsubscribed.compareAndSet(false, true)) {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                onDispose();
            } else {
                AndroidSchedulers.mainThread().scheduleDirect(new Runnable() { // from class: io.reactivex.rxjava3.android.-$$Lambda$JjYHyZW94QYUxhWgKUqPd5cDIWE
                    @Override // java.lang.Runnable
                    public final void run() {
                        MainThreadDisposable.this.onDispose();
                    }
                });
            }
        }
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public final boolean isDisposed() {
        return this.unsubscribed.get();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract void onDispose();
}
