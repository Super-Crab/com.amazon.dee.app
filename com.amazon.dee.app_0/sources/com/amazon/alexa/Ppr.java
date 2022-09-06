package com.amazon.alexa;

import com.amazon.alexa.Kqq;
import com.google.auto.value.AutoValue;
import javax.annotation.Nullable;
/* compiled from: SettingsUpdateSentEvent.java */
@AutoValue
/* loaded from: classes.dex */
public abstract class Ppr extends Kqq.zZm {

    /* compiled from: SettingsUpdateSentEvent.java */
    /* loaded from: classes.dex */
    public enum zZm {
        TIME_ZONE,
        LOCALE,
        SUPPORTS_MOBILE_DOWNCHANNEL
    }

    public static Ppr zZm(zZm zzm) {
        return new IlB(zzm, true, null);
    }

    public static Ppr zZm(zZm zzm, @Nullable Integer num) {
        return new IlB(zzm, false, num);
    }
}
