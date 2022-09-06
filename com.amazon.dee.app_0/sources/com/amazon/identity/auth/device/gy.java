package com.amazon.identity.auth.device;

import android.os.Bundle;
import java.util.concurrent.atomic.AtomicBoolean;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public abstract class gy {
    private AtomicBoolean mHasBeenCalled = new AtomicBoolean(false);

    public abstract void finish(Bundle bundle);

    public void onFinish(Bundle bundle) {
        if (this.mHasBeenCalled.compareAndSet(false, true)) {
            finish(bundle);
        }
    }
}
