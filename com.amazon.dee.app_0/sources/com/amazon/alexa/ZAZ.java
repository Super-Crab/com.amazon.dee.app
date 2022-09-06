package com.amazon.alexa;

import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.Kqq;
import com.google.auto.value.AutoValue;
import okhttp3.Response;
/* compiled from: ResponseReceivedEvent.java */
@AutoValue
/* loaded from: classes.dex */
public abstract class ZAZ extends Kqq.zZm {

    /* compiled from: ResponseReceivedEvent.java */
    @AutoValue
    /* loaded from: classes.dex */
    public static abstract class zZm {
        public static zZm zZm(boolean z, boolean z2) {
            return new knl(z, z2);
        }
    }

    @VisibleForTesting
    public static ZAZ zZm(Response response, zZm zzm, eOP eop, @Nullable TtM ttM) {
        if (ttM == null) {
            ttM = UcG.INSTANCE;
        }
        return new UuN(zzm, response, eop, ttM);
    }
}
