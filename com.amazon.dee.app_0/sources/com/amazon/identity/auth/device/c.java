package com.amazon.identity.auth.device;

import android.os.Bundle;
import com.amazon.identity.auth.device.api.Callback;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class c {
    private static final String TAG = "com.amazon.identity.auth.device.c";
    private final el e = new el(Executors.newSingleThreadExecutor(ji.dG("MAP-AccountAuthenticatorQueueThread")));

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    static final class a extends bk {
        private static final long f = jj.d(5, TimeUnit.SECONDS);
        private final Callback g;
        private final b h;
        private final String i;

        public a(b bVar, Callback callback, String str) {
            this.g = callback;
            this.h = bVar;
            this.i = str;
        }

        @Override // com.amazon.identity.auth.device.bk
        public void onTimeout() {
            throw new RuntimeException("Possible AccountManager Deadlock Detected!");
        }

        @Override // com.amazon.identity.auth.device.bk, java.lang.Runnable
        public synchronized void run() {
            super.run(Long.valueOf(f), TimeUnit.SECONDS, this.i);
        }

        @Override // com.amazon.identity.auth.device.bk
        public void startAsyncOperation() {
            Callback callback = new Callback() { // from class: com.amazon.identity.auth.device.c.a.1
                @Override // com.amazon.identity.auth.device.api.Callback
                public void onError(Bundle bundle) {
                    io.a(c.TAG, "onError Popping task %s off AccountAuthenticatorQueue.", a.this.i);
                    a.this.asyncOperationComplete();
                    a.this.g.onError(bundle);
                }

                @Override // com.amazon.identity.auth.device.api.Callback
                public void onSuccess(Bundle bundle) {
                    io.a(c.TAG, "onSuccess Popping task %s off AccountAuthenticatorQueue.", a.this.i);
                    a.this.asyncOperationComplete();
                    a.this.g.onSuccess(bundle);
                }
            };
            io.a(c.TAG, "Pushing task %s on AccountAuthenticatorQueue.", this.i);
            Bundle a = this.h.a(callback);
            if (a != null) {
                callback.onSuccess(a);
            }
        }
    }

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public interface b {
        Bundle a(Callback callback);
    }

    public void a(b bVar, Callback callback, String str) {
        this.e.execute(new a(bVar, callback, str));
    }
}
