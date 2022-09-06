package com.amazon.rtcsc.common;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
/* loaded from: classes13.dex */
public class AsyncServiceConnectionManager extends ServiceConnectionManager {
    private static final Executor SINGLE_THREAD_EXECUTOR = Executors.newSingleThreadExecutor();
    private volatile boolean mBound;
    private final Executor mExecutor;
    private final HandlerWrapper mHandler;

    public AsyncServiceConnectionManager() {
        this(SINGLE_THREAD_EXECUTOR, new HandlerWrapper(new Handler(Looper.getMainLooper())));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void postBindComplete(final boolean z) {
        this.mHandler.post(new Runnable() { // from class: com.amazon.rtcsc.common.AsyncServiceConnectionManager.3
            @Override // java.lang.Runnable
            public void run() {
                AsyncServiceConnectionManager.this.bindComplete(z);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void postUnbindComplete() {
        this.mHandler.post(new Runnable() { // from class: com.amazon.rtcsc.common.AsyncServiceConnectionManager.4
            @Override // java.lang.Runnable
            public void run() {
                AsyncServiceConnectionManager.this.unbindComplete();
            }
        });
    }

    @Override // com.amazon.rtcsc.common.ServiceConnectionManager
    protected void performBind(final Context context, final ServiceConnection serviceConnection, final Intent intent, final String str) {
        this.mExecutor.execute(new Runnable() { // from class: com.amazon.rtcsc.common.AsyncServiceConnectionManager.1
            @Override // java.lang.Runnable
            public void run() {
                AsyncServiceConnectionManager.this.mBound = ServiceConnectionManager.bindCommon(context, serviceConnection, intent, str);
                AsyncServiceConnectionManager asyncServiceConnectionManager = AsyncServiceConnectionManager.this;
                asyncServiceConnectionManager.postBindComplete(asyncServiceConnectionManager.mBound);
            }
        });
    }

    @Override // com.amazon.rtcsc.common.ServiceConnectionManager
    protected void performUnbind(final Context context, final ServiceConnection serviceConnection) {
        this.mExecutor.execute(new Runnable() { // from class: com.amazon.rtcsc.common.AsyncServiceConnectionManager.2
            @Override // java.lang.Runnable
            public void run() {
                if (AsyncServiceConnectionManager.this.mBound) {
                    ServiceConnectionManager.unbindCommon(context, serviceConnection);
                    AsyncServiceConnectionManager.this.mBound = false;
                }
                AsyncServiceConnectionManager.this.postUnbindComplete();
            }
        });
    }

    AsyncServiceConnectionManager(Executor executor, HandlerWrapper handlerWrapper) {
        this.mBound = false;
        this.mExecutor = executor;
        this.mHandler = handlerWrapper;
    }
}
