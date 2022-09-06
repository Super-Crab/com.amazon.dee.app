package com.amazon.alexa;

import com.amazon.alexa.Kqq;
import com.amazon.alexa.api.AlexaPlaybackState;
import com.google.auto.value.AutoValue;
/* compiled from: AudioPlaybackChangedEvent.java */
@AutoValue
/* loaded from: classes.dex */
public abstract class qZM extends Kqq.zZm {
    public static qZM zZm(AlexaPlaybackState alexaPlaybackState) {
        return new CYr(alexaPlaybackState);
    }
}
