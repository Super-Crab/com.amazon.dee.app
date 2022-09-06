package com.amazon.alexa;

import com.amazon.alexa.client.core.configuration.ClientConfiguration;
import com.amazon.alexa.client.core.configuration.Feature;
import com.amazon.alexa.wakeword.davs.MultiWakeWordFeatureEnabledProvider;
import dagger.Lazy;
/* compiled from: WakeWordModule.java */
/* loaded from: classes.dex */
public class kKu implements MultiWakeWordFeatureEnabledProvider {
    public final /* synthetic */ gSO BIo;
    public final /* synthetic */ Lazy zZm;

    public kKu(iPU ipu, Lazy lazy, gSO gso) {
        this.zZm = lazy;
        this.BIo = gso;
    }

    @Override // com.amazon.alexa.wakeword.davs.MultiWakeWordFeatureEnabledProvider
    public boolean isEnabled() {
        return ((ClientConfiguration) this.zZm.mo358get()).getMultiWwEnabled() != null ? ((ClientConfiguration) this.zZm.mo358get()).getMultiWwEnabled().booleanValue() : this.BIo.zZm(Feature.ALEXA_VOX_ANDROID_MULTI_WW);
    }
}
