package com.amazon.alexa.utils.concurrent;

import android.util.Log;
import com.amazon.alexa.client.annotations.Nullable;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes10.dex */
public class e {
    private Throwable a;

    private static String a() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("TEL:");
        outline107.append(Thread.currentThread().getName());
        return outline107.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(Runnable runnable, @Nullable Throwable th) {
        if (th == null && (runnable instanceof Future)) {
            try {
                Future future = (Future) runnable;
                if (future.isDone()) {
                    future.get();
                }
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
            } catch (CancellationException unused2) {
                a();
            } catch (RuntimeException e) {
                th = e;
            } catch (ExecutionException e2) {
                th = e2.getCause();
            }
        }
        if (th != null) {
            Log.e(a(), th.getMessage(), th);
        }
        this.a = th;
    }
}
