package amazon.speech.simclient.common;

import android.os.Handler;
import android.os.Looper;
/* loaded from: classes.dex */
public class HandlerWrapper {
    private final Handler mHandler;

    public HandlerWrapper(Handler handler) {
        this.mHandler = handler;
    }

    public Looper getLooper() {
        return this.mHandler.getLooper();
    }

    public boolean post(Runnable runnable) {
        return this.mHandler.post(runnable);
    }
}
