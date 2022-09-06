package com.amazon.identity.auth.device;

import android.os.Bundle;
import com.amazon.identity.auth.device.api.Callback;
import com.amazon.identity.auth.device.api.DefaultCallback;
import com.amazon.identity.auth.device.api.MAPCallbackErrorException;
import com.amazon.identity.auth.device.api.MAPFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class bl implements Callback, MAPFuture<Bundle> {
    private static final String TAG = "com.amazon.identity.auth.device.bl";
    private final CountDownLatch dH;
    private Callback g;
    private Bundle gF;
    private int mState;

    public bl() {
        this(null);
    }

    private synchronized Bundle bu() throws MAPCallbackErrorException {
        int i = this.mState;
        if (i != 1) {
            if (i != 2) {
                throw new IllegalStateException();
            }
            throw new MAPCallbackErrorException(this.gF);
        }
        return this.gF;
    }

    private void bv() {
        if (!ji.gP()) {
            return;
        }
        throw new IllegalStateException("Cannot call get on the main thread, unless you want ANRs");
    }

    public static bl f(Callback callback) {
        if (callback instanceof bl) {
            return (bl) callback;
        }
        return new bl(callback);
    }

    @Override // com.amazon.identity.auth.device.api.MAPFuture
    /* renamed from: a */
    public Bundle get(long j, TimeUnit timeUnit) throws MAPCallbackErrorException, InterruptedException, ExecutionException, TimeoutException {
        bv();
        if (this.dH.await(j, timeUnit)) {
            return bu();
        }
        io.e(TAG, "Timed out waiting for result!");
        throw new TimeoutException("Timed out waiting for result!");
    }

    @Override // com.amazon.identity.auth.device.api.MAPFuture
    /* renamed from: bt */
    public Bundle get() throws MAPCallbackErrorException, InterruptedException, ExecutionException {
        bv();
        this.dH.await();
        return bu();
    }

    @Override // com.amazon.identity.auth.device.api.Callback
    public void onError(Bundle bundle) {
        a(2, bundle);
    }

    @Override // com.amazon.identity.auth.device.api.Callback
    public void onSuccess(Bundle bundle) {
        a(1, bundle);
    }

    public bl(Callback callback) {
        this.g = DefaultCallback.nullToDefault(callback);
        this.dH = new CountDownLatch(1);
        this.mState = 0;
    }

    private void a(int i, Bundle bundle) {
        synchronized (this) {
            if (this.mState != 0) {
                io.w(TAG, "Callback was called twice", new IllegalStateException());
                return;
            }
            this.gF = bundle;
            this.mState = i;
            Callback callback = this.g;
            this.g = null;
            this.dH.countDown();
            a(i, bundle, callback);
        }
    }

    private static void a(int i, Bundle bundle, Callback callback) {
        if (callback == null) {
            return;
        }
        if (i == 1) {
            callback.onSuccess(bundle);
        } else if (i == 2) {
            callback.onError(bundle);
        } else {
            throw new IllegalStateException();
        }
    }
}
