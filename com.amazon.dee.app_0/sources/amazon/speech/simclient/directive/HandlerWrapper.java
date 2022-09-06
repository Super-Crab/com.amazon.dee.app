package amazon.speech.simclient.directive;

import android.os.Handler;
/* loaded from: classes.dex */
public class HandlerWrapper {
    private final Handler mHandler;

    public HandlerWrapper(Handler handler) {
        this.mHandler = handler;
    }

    public boolean post(Runnable runnable) {
        return this.mHandler.post(runnable);
    }

    public boolean postDelayed(Runnable runnable, long j) {
        return this.mHandler.postDelayed(runnable, j);
    }

    public void removeCallbacks(Runnable runnable) {
        this.mHandler.removeCallbacks(runnable);
    }
}
