package com.amazon.alexa;

import com.amazon.alexa.Kqq;
import com.google.auto.value.AutoValue;
/* compiled from: DownchannelEstablishmentFailureEvent.java */
@AutoValue
/* loaded from: classes.dex */
public abstract class PMW extends Kqq.zZm {

    /* compiled from: DownchannelEstablishmentFailureEvent.java */
    /* loaded from: classes.dex */
    public enum zZm {
        IO_EXCEPTION,
        AVS_FAILURE,
        NO_NETWORK,
        AUTHORIZATION
    }

    public static PMW zZm(zZm zzm, Integer num) {
        return new MqA(zzm, null, num);
    }
}
