package amazon.speech.util;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.text.TextUtils;
/* loaded from: classes.dex */
public class HandlerWrapper {
    private final Handler mHandler;

    public HandlerWrapper() {
        this.mHandler = new Handler(Looper.getMainLooper());
    }

    public Handler getHandler() {
        return this.mHandler;
    }

    public void post(Runnable runnable) {
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.post(runnable);
        }
    }

    public void postDelayed(Runnable runnable, long j) {
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.postDelayed(runnable, j);
        }
    }

    public void removeCallbacks(Runnable runnable) {
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.removeCallbacks(runnable);
        }
    }

    public HandlerWrapper(Handler handler) {
        this.mHandler = handler;
    }

    public HandlerWrapper(String str) {
        if (!TextUtils.isEmpty(str)) {
            HandlerThread handlerThread = new HandlerThread(str);
            handlerThread.start();
            this.mHandler = new Handler(handlerThread.getLooper());
            return;
        }
        throw new IllegalArgumentException();
    }
}
