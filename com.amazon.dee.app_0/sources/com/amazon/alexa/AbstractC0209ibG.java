package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.speechrecognizer.payload.AutoValue_WakeWordInitiator;
import com.amazon.alexa.client.alexaservice.speechrecognizer.payload.AutoValue_WakeWordInitiatorPayload;
import com.google.auto.value.AutoValue;
/* compiled from: WakeWordInitiator.java */
@AutoValue
/* renamed from: com.amazon.alexa.ibG  reason: case insensitive filesystem */
/* loaded from: classes.dex */
public abstract class AbstractC0209ibG {

    /* compiled from: WakeWordInitiator.java */
    /* renamed from: com.amazon.alexa.ibG$zZm */
    /* loaded from: classes.dex */
    public enum zZm {
        PRESS_AND_HOLD,
        TAP,
        WAKEWORD
    }

    public static AbstractC0209ibG zZm(zZm zzm) {
        return zZm(zzm, new AutoValue_WakeWordInitiatorPayload(null, null));
    }

    public static AbstractC0209ibG zZm(zZm zzm, kbU kbu) {
        return new AutoValue_WakeWordInitiator(zzm, kbu);
    }
}
