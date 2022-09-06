package com.amazon.alexa.api;

import android.os.ConditionVariable;
import android.os.RemoteException;
import android.util.Log;
import com.amazon.alexa.client.annotations.Nullable;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.concurrent.Callable;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes6.dex */
public abstract class at<T> implements Callable<T> {
    private static final String TAG = "at";
    private final ConditionVariable condition = new ConditionVariable();
    private final T defaultValue;
    private T result;
    private final long timeout;

    /* JADX INFO: Access modifiers changed from: package-private */
    public at(long j, @Nullable T t) {
        this.timeout = j;
        this.defaultValue = t;
    }

    @Override // java.util.concurrent.Callable
    public synchronized T call() throws RemoteException {
        if (this.result != null) {
            return this.result;
        }
        String str = TAG;
        Log.i(str, "[" + Thread.currentThread() + "] executing");
        execute();
        this.condition.block(this.timeout);
        if (this.result == null) {
            return this.defaultValue;
        }
        return this.result;
    }

    protected abstract void execute() throws RemoteException;

    public void setResult(T t) {
        this.result = t;
        this.condition.open();
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("[");
        outline107.append(Thread.currentThread());
        outline107.append("] got a result");
        Log.i(str, outline107.toString());
    }
}
