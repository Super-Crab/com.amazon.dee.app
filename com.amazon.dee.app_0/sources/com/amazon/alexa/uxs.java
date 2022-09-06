package com.amazon.alexa;

import com.amazon.alexa.Kqq;
import com.google.auto.value.AutoValue;
/* compiled from: PromptPlayerEvent.java */
@AutoValue
/* loaded from: classes.dex */
public abstract class uxs extends Kqq.zZm {

    /* compiled from: PromptPlayerEvent.java */
    /* loaded from: classes.dex */
    public enum zZm {
        NOT_CONNECTED,
        ALEXA_DOWN,
        LOST_CONNECTION,
        NETWORK_LOW_BANDWIDTH,
        NETWORK_TRANSITION_AUTO,
        NETWORK_TRANSITION_NON_AUTO,
        CONNECTIVITY_ISSUE_AUTO,
        CONNECTIVITY_ISSUE_NON_AUTO
    }

    public static uxs zZm(zZm zzm) {
        return new SHw(zzm);
    }
}
