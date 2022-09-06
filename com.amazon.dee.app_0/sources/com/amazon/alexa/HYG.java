package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.comms.payload.AutoValue_PhoneCallControllerCallingFeature;
import com.google.auto.value.AutoValue;
/* compiled from: PhoneCallControllerCallingFeature.java */
@AutoValue
/* loaded from: classes.dex */
public abstract class HYG {

    /* compiled from: PhoneCallControllerCallingFeature.java */
    /* loaded from: classes.dex */
    public enum zZm {
        VIDEO_SUPPORTED
    }

    public static HYG zZm(String str, boolean z) {
        return new AutoValue_PhoneCallControllerCallingFeature(str, z);
    }
}
