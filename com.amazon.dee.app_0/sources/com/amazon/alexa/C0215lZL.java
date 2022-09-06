package com.amazon.alexa;

import android.media.AudioManager;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.utils.TimeProvider;
import com.amazon.alexa.wakeword.pryon.AudioPlaybackConfigurationHelper;
import com.amazon.alexa.wakeword.pryon.WakeWordDetectorProvider;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: ExternalAudioWakeWordDetector_Factory.java */
/* renamed from: com.amazon.alexa.lZL  reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class C0215lZL implements Factory<uqp> {
    public final Provider<WakeWordDetectorProvider> BIo;
    public final Provider<AudioPlaybackConfigurationHelper> Qle;
    public final Provider<FdV> jiA;
    public final Provider<TimeProvider> zQM;
    public final Provider<AudioManager> zZm;
    public final Provider<AlexaClientEventBus> zyO;

    public C0215lZL(Provider<AudioManager> provider, Provider<WakeWordDetectorProvider> provider2, Provider<TimeProvider> provider3, Provider<AlexaClientEventBus> provider4, Provider<FdV> provider5, Provider<AudioPlaybackConfigurationHelper> provider6) {
        this.zZm = provider;
        this.BIo = provider2;
        this.zQM = provider3;
        this.zyO = provider4;
        this.jiA = provider5;
        this.Qle = provider6;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new uqp(this.zZm.mo10268get(), this.BIo.mo10268get(), this.zQM.mo10268get(), this.zyO.mo10268get(), this.jiA.mo10268get(), this.Qle.mo10268get());
    }
}
