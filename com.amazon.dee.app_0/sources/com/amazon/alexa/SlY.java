package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.audio.ScaledVolumeProcessor;
import dagger.internal.Factory;
/* compiled from: ScaledVolumeProcessor_Factory.java */
/* loaded from: classes.dex */
public final class SlY implements Factory<ScaledVolumeProcessor> {
    public static final SlY zZm = new SlY();

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new ScaledVolumeProcessor();
    }
}
