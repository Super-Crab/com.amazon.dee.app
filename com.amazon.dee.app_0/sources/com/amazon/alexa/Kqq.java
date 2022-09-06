package com.amazon.alexa;

import android.os.SystemClock;
import androidx.annotation.Nullable;
import com.amazon.alexa.client.core.configuration.Feature;
/* compiled from: Event.java */
/* loaded from: classes.dex */
public abstract class Kqq {
    public final long zZm = SystemClock.elapsedRealtime();

    /* compiled from: Event.java */
    /* loaded from: classes.dex */
    public static class zZm extends Kqq {
        @Override // com.amazon.alexa.Kqq
        @Nullable
        public Feature zZm() {
            return null;
        }
    }

    @Nullable
    public abstract Feature zZm();
}
