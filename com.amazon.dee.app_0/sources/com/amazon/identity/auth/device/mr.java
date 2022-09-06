package com.amazon.identity.auth.device;

import android.text.TextUtils;
import android.util.Log;
import com.amazon.identity.auth.device.mv;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class mr extends ms {
    private final String mTag;

    /* JADX INFO: Access modifiers changed from: package-private */
    public mr() {
        if (TextUtils.isEmpty(null)) {
            this.mTag = "PeriodicLoggingMetricsCollector";
        } else {
            this.mTag = null;
        }
    }

    @Override // com.amazon.identity.auth.device.ms
    public void bA(String str) {
        Log.i(this.mTag, "Increment counter : ".concat(String.valueOf(str)));
    }

    @Override // com.amazon.identity.auth.device.ms
    public mv eN(String str) {
        return new mv.b();
    }
}
