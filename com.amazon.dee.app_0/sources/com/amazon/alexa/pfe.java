package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.externalmediaplayer.AutoValue_PlayerError;
import com.google.auto.value.AutoValue;
/* compiled from: PlayerError.java */
@AutoValue
/* loaded from: classes.dex */
public abstract class pfe {
    public static pfe zZm(Iof iof, String str, long j, boolean z) {
        return new AutoValue_PlayerError(iof, j, z, false, str);
    }
}
